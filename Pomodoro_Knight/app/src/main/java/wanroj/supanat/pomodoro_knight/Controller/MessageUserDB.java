package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import wanroj.supanat.pomodoro_knight.Model.UserInfo;

/**
 * Created by mild supanat on 21/11/2560.
 */

@Database(entities = {UserInfo.class}, version = 1)
public abstract class MessageUserDB extends RoomDatabase {
    public abstract MeessageUserInfoDAO getMessageInfoDAO();
}
