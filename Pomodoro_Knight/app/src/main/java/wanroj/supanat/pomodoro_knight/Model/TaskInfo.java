package wanroj.supanat.pomodoro_knight.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mild supanat on 18/11/2560.
 */

@Entity(tableName = "TASKLIST")
public class TaskInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TASK_NAME")
    private String taskName;

    @ColumnInfo(name = "WORK_INTERVAL")
    private int workInterval;

    @ColumnInfo(name = "TARGET")
    private int target;

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

    public int getWorkInterval() {
        return workInterval;
    }

    public void setWorkInterval(int workInterval) {
        this.workInterval = workInterval;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
