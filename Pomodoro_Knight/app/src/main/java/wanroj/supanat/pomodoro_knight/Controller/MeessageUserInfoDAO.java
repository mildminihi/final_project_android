package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.UserInfo;

/**
 * Created by mild supanat on 21/11/2560.
 */
@Dao
interface MeessageUserInfoDAO {

    @Query("SELECT * FROM USERINFO")
    List<UserInfo> findAll();
}
