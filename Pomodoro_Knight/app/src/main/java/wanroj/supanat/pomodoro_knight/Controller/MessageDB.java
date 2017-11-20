package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import wanroj.supanat.pomodoro_knight.Model.TaskInfo;

/**
 * Created by mild supanat on 19/11/2560.
 */

@Database(entities = {TaskInfo.class}, version = 2)
public abstract class MessageDB extends RoomDatabase{
    public abstract MessageInfoDAO getMessageInfoDAO();
}


