package wanroj.supanat.pomodoro_knight.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "USERINFO")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "USERID")
    private String uid;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "TARGET")
    private int target;

    @ColumnInfo(name = "DONE")
    private int done;

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
