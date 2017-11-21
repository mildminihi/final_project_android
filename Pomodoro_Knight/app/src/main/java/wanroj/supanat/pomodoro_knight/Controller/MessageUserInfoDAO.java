package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.UserInfo;

/**
 * Created by mild supanat on 21/11/2560.
 */

@Dao
interface MessageUserInfoDAO {

    @Query("SELECT * FROM USERINFO")
    List<UserInfo> findAll();

    @Query("SELECT NAME FROM USERINFO WHERE USERID LIKE :uid")
    String  findNameById(String uid);

    @Insert
    void insert(UserInfo userInfo);
}
