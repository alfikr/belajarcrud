package id.a.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlat(Plat... plats);
    @Query("SELECT * from plat Where fileName= :fileName")
    public List<Plat> loadPlat(String fileName);
    @Query("Select * from plat where rowid match :plat")
    public List<Plat> limitPlat(String plat);
    @Delete
    public void hapusFile(Plat fileName);

}
