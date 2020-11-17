package id.a.myapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.OnConflictStrategy;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.IOException;
import java.util.Random;

public class FirstFragment extends Fragment {
    private Context context;
    private TextView tv;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv=view.findViewById(R.id.textview_first);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ABC().execute();
            }
        });
        view.findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupportSQLiteDatabase database =App.getInstance().getPlatDatabase().getOpenHelper().getWritableDatabase();
                database.execSQL("DROP TABLE IF EXISTS plat");
                database.execSQL("CREATE VIRTUAL TABLE plat USING FTS4(nopol, fileName)");
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    class ABC extends AsyncTask<Void,Pair<Long,Long>,Long>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        }

        @Override
        protected void onProgressUpdate(Pair<Long, Long>... values) {
            super.onProgressUpdate(values);
            tv.setText("data diupdate "+values[0].first);
        }

        @Override
        protected Long doInBackground(Void... voids) {
            SupportSQLiteDatabase database =App.getInstance().getPlatDatabase().getOpenHelper().getWritableDatabase();
            database.beginTransaction();
            try {
                for (int i=0;i<1000;i++){
                    for(int j=0;j<5000;j++){
                        ContentValues cv = new ContentValues();
                        cv.put("nopol",getPlatRandom());
                        cv.put("fileName","file_"+i);
                        database.insert("plat", OnConflictStrategy.REPLACE,cv);
                        Log.d("Insert data ","plat "+j);
                    }
                    Log.i("ganti file","file_"+i);
                    publishProgress(new Pair<Long, Long>(Long.valueOf(i),5000L));
                }
            }catch (Exception e){
                if (database.inTransaction()){
                    database.endTransaction();
                }
            }finally {
                database.endTransaction();
                try {
                    database.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            return null;
        }


        private String getPlatRandom(){
            String area="BL,BB,BK,BA,BM,BH,BD,BP,BG,BN,BE," +
                    "A,B,D,E,F,T,Z,G,H,K,R,AA,AB,AD,L,M,N," +
                    "P,S,W,AG,AE,DK,DR,EA,DH,EB,ED,KAB,DA,KH,KT," +
                    "TU,DB,DL,DM,DN,DT,DD,DC,DE,DG,PA,PB";
            String alphabet="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P," +
                    "Q,R,S,T,U,V,W,X,Y,Z";
            String areaPlat[] = area.split(",");
            String alp[]=alphabet.split(",");
            StringBuilder sb = new StringBuilder();
            Random random =new Random();
            sb.append(areaPlat[random.nextInt(areaPlat.length)]);
            sb.append(random.nextInt(10000));
            sb.append(alp[random.nextInt(alp.length)]);
            sb.append(alp[random.nextInt(alp.length)]);
            sb.append(alp[random.nextInt(alp.length)]);
            return sb.toString();
        }
    }
}