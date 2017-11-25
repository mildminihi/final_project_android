package wanroj.supanat.pomodoro_knight;



import org.junit.Test;

import wanroj.supanat.pomodoro_knight.Model.AppUserInfo;
import wanroj.supanat.pomodoro_knight.Model.CurrentID;
import wanroj.supanat.pomodoro_knight.Model.Rank;
import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.Model.TaskToDo;

import static org.testng.Assert.assertEquals;

/**
 * Created by mild supanat on 25/11/2560.
 */

public class ModelTest {
    private CurrentID currentID;
    private TaskInfo taskInfo;
    private TaskToDo taskToDo;
    private Rank rank;
    private AppUserInfo appUserInfo;

    @Test
    public void getRankWhenTaskDoneis1(){
        rank = new Rank();
        assertEquals(rank.checkRank(1), "Most lazy villager");
        assertEquals(rank.checkNext(1), "Lazy villager");
    }

    @Test
    public void getRankWhenTaskDoneis7(){
        rank = new Rank();
        assertEquals(rank.checkRank(7), "Lazy villager");
        assertEquals(rank.checkNext(7), "Regular villager");
    }

    @Test
    public void getRankWhenTaskDoneis10(){
        rank = new Rank();
        assertEquals(rank.checkRank(10), "Regular villager");
        assertEquals(rank.checkNext(10), "Village leaders");
    }

    @Test
    public void getRankWhenTaskDoneis18(){
        rank = new Rank();
        assertEquals(rank.checkRank(18), "Village leaders");
        assertEquals(rank.checkNext(18), "Lord newbie");
    }

    @Test
    public void getRankWhenTaskDoneis20(){
        rank = new Rank();
        assertEquals(rank.checkRank(20), "Lord newbie");
        assertEquals(rank.checkNext(20), "Lord coffee");
    }

    @Test
    public void getRankWhenTaskDoneis24(){
        rank = new Rank();
        assertEquals(rank.checkRank(24), "Lord coffee");
        assertEquals(rank.checkNext(24), "Lord");
    }

    @Test
    public void getRankWhenTaskDoneis30(){
        rank = new Rank();
        assertEquals(rank.checkRank(30), "Lord");
        assertEquals(rank.checkNext(30), "Low class knight");
    }

    @Test
    public void getRankWhenTaskDoneis36(){
        rank = new Rank();
        assertEquals(rank.checkRank(36), "Low class knight");
        assertEquals(rank.checkNext(36), "The brave knight");
    }

    @Test
    public void getRankWhenTaskDoneis40(){
        rank = new Rank();
        assertEquals(rank.checkRank(40), "The brave knight");
        assertEquals(rank.checkNext(40), "Baron");
    }

    @Test
    public void getRankWhenTaskDoneis45(){
        rank = new Rank();
        assertEquals(rank.checkRank(45), "Baron");
        assertEquals(rank.checkNext(45), "Viscount");
    }

    @Test
    public void getRankWhenTaskDoneis50(){
        rank = new Rank();
        assertEquals(rank.checkRank(50), "Viscount");
        assertEquals(rank.checkNext(50), "Count");
    }

    @Test
    public void getRankWhenTaskDoneis52(){
        rank = new Rank();
        assertEquals(rank.checkRank(52), "Count");
        assertEquals(rank.checkNext(52), "Count luv ter naa");
    }

    @Test
    public void getRankWhenTaskDoneis60(){
        rank = new Rank();
        assertEquals(rank.checkRank(60), "Count luv ter naa");
        assertEquals(rank.checkNext(60), "Marquess");
    }

    @Test
    public void getRankWhenTaskDoneis65(){
        rank = new Rank();
        assertEquals(rank.checkRank(65), "Marquess");
        assertEquals(rank.checkNext(65), "Prince");
    }

    @Test
    public void getRankWhenTaskDoneis69(){
        rank = new Rank();
        assertEquals(rank.checkRank(69), "Prince");
        assertEquals(rank.checkNext(69), "Duke");
    }

    @Test
    public void getRankWhenTaskDoneis72(){
        rank = new Rank();
        assertEquals(rank.checkRank(72), "Duke");
        assertEquals(rank.checkNext(72), "Archduke");
    }

    @Test
    public void getRankWhenTaskDoneis77(){
        rank = new Rank();
        assertEquals(rank.checkRank(77), "Archduke");
        assertEquals(rank.checkNext(77), "Grand Prince");
    }

    @Test
    public void getRankWhenTaskDoneis80(){
        rank = new Rank();
        assertEquals(rank.checkRank(80), "Grand Prince");
        assertEquals(rank.checkNext(80), "Grand Duke");
    }

    @Test
    public void getRankWhenTaskDoneis88(){
        rank = new Rank();
        assertEquals(rank.checkRank(88), "Grand Duke");
        assertEquals(rank.checkNext(88), "King");
    }

    @Test
    public void getRankWhenTaskDoneis91(){
        rank = new Rank();
        assertEquals(rank.checkRank(91), "King");
        assertEquals(rank.checkNext(91), "Emperor");
    }

    @Test
    public void getRankWhenTaskDoneis99(){
        rank = new Rank();
        assertEquals(rank.checkRank(99), "Emperor");
        assertEquals(rank.checkNext(99), "God");
    }

    @Test
    public void getRankWhenTaskDoneis100(){
        rank = new Rank();
        assertEquals(rank.checkRank(100), "God");
        assertEquals(rank.checkNext(100), "God");
    }
}
