package id.a.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(version = 1, entities = {Plat.class})
public abstract class PlatDatabase extends RoomDatabase {
    public abstract PlatDao platDao();
}
