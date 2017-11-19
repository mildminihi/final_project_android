package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.TaskInfo;

/**
 * Created by mild supanat on 19/11/2560.
 */

@Dao
interface MessageInfoDAO {
    @Query("SELECT * FROM TASKLIST")
    List<TaskInfo> findAll();

    @Query("SELECT * FROM TASKLIST WHERE TASK_NAME LIKE :name")
    public abstract List<TaskInfo> findTaskByName(String name);

    @Query("DELETE FROM TASKLIST")
    void deleteAll();

    @Delete
    void deleteTask(TaskInfo taskInfo);

    @Insert
    void insert(TaskInfo taskInfo);
}

