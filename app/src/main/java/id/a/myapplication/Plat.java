package id.a.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;

@Fts4
@Entity(tableName = "plat")
public class Plat {
    @ColumnInfo(name = "nopol")
    private String nopol;
    @ColumnInfo(name = "fileName")
    private String fileName;

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
