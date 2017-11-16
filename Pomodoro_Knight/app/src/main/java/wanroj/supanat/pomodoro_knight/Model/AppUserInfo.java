package wanroj.supanat.pomodoro_knight.Model;

import android.net.Uri;

/**
 * Created by mild supanat on 17/11/2560.
 */

public class AppUserInfo {
    private String name, email, uid;
    private Uri photoUrl;

    public AppUserInfo(String name, String email, Uri photoUrl, String uid) {
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }
}
