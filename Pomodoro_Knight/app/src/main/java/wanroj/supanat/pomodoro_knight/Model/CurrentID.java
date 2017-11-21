package wanroj.supanat.pomodoro_knight.Model;

/**
 * Created by mild supanat on 21/11/2560.
 */

public class CurrentID {
    private static CurrentID currentIDInstance;
    private String idUser, nameUser;

    public static CurrentID getCurrentIDInstance(){
        if(currentIDInstance == null){
            currentIDInstance = new CurrentID();
        }
        return currentIDInstance;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
