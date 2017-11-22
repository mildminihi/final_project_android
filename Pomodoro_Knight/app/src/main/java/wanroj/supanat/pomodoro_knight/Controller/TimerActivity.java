package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import wanroj.supanat.pomodoro_knight.Model.CurrentID;
import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.Model.TaskToDo;
import wanroj.supanat.pomodoro_knight.R;

public class TimerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public int seconds = 0;
    public int minutes ;
    private TextView taskName, taskTarget, tv;
    Button s_button, pause_button;
    private int checkStart = 0, checkPause = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TaskToDo taskToDo;
    private String secondConvert = "0";
    private TaskInfo taskInfo;
    private CurrentID currentID;
    private String checkGo;
    private Intent intent;

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

        minutes = taskToDo.getWorkInterval();
        taskTarget.setText(taskToDo.getDone()+"/"+taskToDo.getTarget());
        checkIndexSecond(seconds);
        tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
        if (taskToDo.getTaskName() == null){
            doAlertDialog();
        }
        taskName.setText(taskToDo.getTaskName());

    }

    private void sureExit(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(TimerActivity.this);
        builder1.setMessage("You haven't finished your task yet. Do you want to leave without finishing?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Leave",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch (checkGo){
                            case "profile":
                                intent = new Intent(TimerActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case "add":
                                intent = new Intent(TimerActivity.this, CreateActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case "list":
                                intent = new Intent(TimerActivity.this, ListActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                        }
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Stay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkGo = "Stay";
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void doAlertDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(TimerActivity.this);
        builder1.setMessage("You Don't have tasks to do.\n" +
                "Please go to tasks list to select or create task");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Create task",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(TimerActivity.this, CreateActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Go to tasks list",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        Intent intent = new Intent(TimerActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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
        if (minutes + seconds != 0 && checkStart == 1){
            minutes = taskToDo.getWorkInterval();
            seconds = 0;
            checkStart=0;
            tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
            s_button.setText("Start");
            pause_button.setEnabled(false);
        }
        else {
            if (checkDone()){
                Toast.makeText(TimerActivity.this, "Task "+taskToDo.getTaskName()+" completed! Please choose another tasks.", Toast.LENGTH_LONG).show();
            }
            else timer();
        }

    }

    public void onPause(View view) {
        if (checkPause == 1){
            checkPause = 0;

            pause_button.setText("Pause");
        }
        else {
            pause_button.setText("PAUSE");
            checkStart = 0;
            timer();
        }

    }

    public void timer(){
        pause_button.setEnabled(true);
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

                            if (seconds == 0 && minutes != 0) {
                                tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));

                                seconds = 60;
                                minutes -= 1;

                            }
                            if (minutes == 0 && seconds == 0){
                                t.cancel();
                                t.purge();
                                minutes = taskToDo.getWorkInterval();
                                seconds = 0;
                                tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
                                taskToDo.setDone(taskToDo.getDone()+1);
                                taskTarget.setText(taskToDo.getDone()+"/"+taskToDo.getTarget());
                                Toast.makeText(TimerActivity.this, "Task: "+taskToDo.getTaskName()+" Done", Toast.LENGTH_LONG).show();
                                checkStart = 0;
                                checkPause = 0;
                                pause_button.setEnabled(false);
                                s_button.setText("START");
                                pause_button.setText("PAUSE");
                                if (checkDone()){
                                    Toast.makeText(TimerActivity.this, "Congratulation you reach the target of "+taskToDo.getTaskName()+" task!", Toast.LENGTH_LONG).show();
                                }
                                updateInTaskList();

                                try {
                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                    r.play();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            if (checkPause == 0) {
                                t.cancel();
                                t.purge();


                                pause_button.setText("Resume");
                                return;
                            }
                            if (checkStart == 0) {
                                t.cancel();
                                t.purge();
                                minutes = taskToDo.getWorkInterval();
                                seconds = 0;
                                checkStart=0;
                                tv.setText(String.valueOf(minutes) + ":" + checkIndexSecond(seconds));
                            }



                        }

                    });
                }

            }, 0, 1000);

            s_button.setText(R.string.stop);

        } else {


            checkStart = 0;
            s_button.setText(R.string.start);
            pause_button.setEnabled(false);

        }
    }

    private void updateInTaskList() {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "TASKLIST").build();
        new AsyncTask<Void, Void, List<TaskInfo>>() {
            @Override
            protected List<TaskInfo> doInBackground(Void... voids) {
                taskInfo = new TaskInfo();
                taskInfo.setId(taskToDo.getId());
                taskInfo.setTaskName(taskToDo.getTaskName());
                taskInfo.setTarget(taskToDo.getTarget());
                taskInfo.setWorkInterval(taskToDo.getWorkInterval());
                taskInfo.setDone(taskToDo.getDone());
                currentID = CurrentID.getCurrentIDInstance();
                taskInfo.setUserID(currentID.getIdUser());
                messageDB.getMessageInfoDAO().update(taskInfo);
                return null;
            }
        }.execute();
    }

    private boolean checkDone() {
        if (taskToDo.getDone() == taskToDo.getTarget()){
            return true;

        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{
                    checkGo = "profile";
                    sureExit();
                    break;
            }
            case R.id.timer:{
                    break;
            }
            case R.id.add:{
                    checkGo = "add";
                    sureExit();
                    break;
            }
            case R.id.listmenu: {
                    checkGo = "list";
                    sureExit();
                    break;
            }
        }
        drawerLayout.closeDrawers();

        return true;
    }
}
