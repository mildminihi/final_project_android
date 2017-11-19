package wanroj.supanat.pomodoro_knight.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import wanroj.supanat.pomodoro_knight.Model.TaskToDo;
import wanroj.supanat.pomodoro_knight.R;

public class TimerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public int seconds = 0;
    public int minutes = 3;
    private TextView taskName, taskTarget, tv;
    Button s_button, pause_button;
    private int checkStart = 0, checkPause = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TaskToDo taskToDo;
    private String secondConvert = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        getSupportActionBar().setTitle("Timer");
        s_button = (Button) findViewById(R.id.buttonStop_Start);
        pause_button = (Button) findViewById(R.id.buttonPause);
        taskName = (TextView)findViewById(R.id.textViewTask);
        taskTarget = (TextView)findViewById(R.id.textViewRound);
        tv = (TextView) findViewById(R.id.textViewTimer);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawertimer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListner();
        taskToDo = TaskToDo.getTaskToDoInstance();
        taskName.setText(taskToDo.getTaskName());
        minutes = taskToDo.getWorkInterval();
        taskTarget.setText("0/"+taskToDo.getTarget());
        checkIndexSecond(seconds);
        tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));



    }

    private String checkIndexSecond(int seconds) {
        if (seconds < 10){
            secondConvert = "0";
            return secondConvert+seconds+"";
        }
        return secondConvert = seconds+"";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    public void onS(View view) {
        timer();

    }

    public void onPause(View view) {
        if (checkPause == 1){

            checkPause = 0;
            pause_button.setText("Pause");
        }
        else timer();

    }

    public void timer(){
        final Timer t = new Timer();
        if (checkStart == 0) {
            checkStart = 1;
            checkPause = 1;

            //Set the schedule function and rate
            t.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
                            if (seconds != 0) seconds -= 1;
                            tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));

                            if (seconds == 0) {
                                tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));

                                seconds = 60;
                                minutes -= 1;

                            }
                            if (checkPause == 0) {
                                t.cancel();
                                t.purge();
                                pause_button.setText("Resume");
                            }
                            if (checkStart == 0) {
                                t.cancel();
                                t.purge();
                                minutes = 3;
                                seconds = 0;
                                tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
                            }


                        }

                    });
                }

            }, 0, 1000);

            s_button.setText("Stop");

        } else {


            checkStart = 0;
            s_button.setText("Start");

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{
                Intent intent = new Intent(TimerActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.timer:{
                Intent intent = new Intent(TimerActivity.this, TimerActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.add:{
                Intent intent = new Intent(TimerActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.listmenu: {
                Intent intent = new Intent(TimerActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        drawerLayout.closeDrawers();

        return true;
    }
}
