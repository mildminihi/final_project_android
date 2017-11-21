package wanroj.supanat.pomodoro_knight.Model;

/**
 * Created by mild supanat on 21/11/2560.
 */

public class Rank {
    String[] rank = {"Most lazy villager","Lazy villager","Regular villager", "Village leaders",
    "Lord newbie", "Lord coffee", "Lord", "Low class knight", "The brave knight", "Baron", "Viscount",
    "Count", "Count luv ter naa", "Marquess", "Prince", "Duke", "Archduke", "Grand Prince",
    "Grand Duke", "King", "Emperor", "God"};


    public String checkRank(int done){
        if (done <= 3) return rank[0];
        else if (done <= 7) return rank[1];
        else if (done <= 11) return rank[2];
        else if (done <= 18) return rank[3];
        else if (done <= 22) return rank[4];
        else if (done <= 28) return rank[5];
        else if (done <= 35) return rank[6];
        else if (done <= 38) return rank[7];
        else if (done <= 40) return rank[8];
        else if (done <= 46) return rank[9];
        else if (done <= 50) return rank[10];
        else if (done <= 53) return rank[11];
        else if (done <= 64) return rank[12];
        else if (done <= 68) return rank[13];
        else if (done <= 71) return rank[14];
        else if (done <= 73) return rank[15];
        else if (done <= 78) return rank[16];
        else if (done <= 80) return rank[17];
        else if (done <= 88) return rank[18];
        else if (done <= 92) return rank[19];
        else if (done <= 99) return rank[20];
        else return rank[21];
    }
    public String checkNext(int done) {
        if (done <= 3) return rank[1];
        else if (done <= 7) return rank[2];
        else if (done <= 11) return rank[3];
        else if (done <= 18) return rank[4];
        else if (done <= 22) return rank[5];
        else if (done <= 28) return rank[6];
        else if (done <= 35) return rank[7];
        else if (done <= 38) return rank[8];
        else if (done <= 40) return rank[9];
        else if (done <= 46) return rank[10];
        else if (done <= 50) return rank[11];
        else if (done <= 53) return rank[12];
        else if (done <= 64) return rank[13];
        else if (done <= 68) return rank[14];
        else if (done <= 71) return rank[15];
        else if (done <= 73) return rank[16];
        else if (done <= 78) return rank[17];
        else if (done <= 80) return rank[18];
        else if (done <= 88) return rank[19];
        else if (done <= 92) return rank[20];
        else return rank[21];
    }

}
