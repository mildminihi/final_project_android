package wanroj.supanat.pomodoro_knight.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mild supanat on 21/11/2560.
 */

@Entity(tableName = "USERINFO")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "USERID")
    private String taskName;

    @ColumnInfo(name = "TARGET")
    private int target;

    @ColumnInfo(name = "DONE")
    private int done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
