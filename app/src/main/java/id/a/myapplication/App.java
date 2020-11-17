package id.a.myapplication;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private PlatDao platDao;
    private static App app;
    private PlatDatabase platDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        platDatabase=Room.databaseBuilder(this,PlatDatabase.class,"plats")
                .fallbackToDestructiveMigration()
                .build();
        platDao=platDatabase.platDao();
        app=this;
    }
    public PlatDatabase getPlatDatabase(){
        return platDatabase;
    }
    public PlatDao getPlatDao(){
        return platDao;
    }
    public synchronized static App getInstance(){
        return app;
    }
}
