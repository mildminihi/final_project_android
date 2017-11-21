package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import wanroj.supanat.pomodoro_knight.Model.CurrentID;
import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.R;

public class CreateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private EditText editTextName, editTextWork, editTextTarget;
    private CurrentID currentID;

    private Validate validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().setTitle("Add Task");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextWork = (EditText) findViewById(R.id.editTextWork);
        editTextTarget = (EditText) findViewById(R.id.editTextTarget);
        validate = new Validate();


        drawerLayout = (DrawerLayout)findViewById(R.id.draweradd);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListner();

    }

    public void onAdd(View view){
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "TASKLIST").build();

        new AsyncTask<Void, Void, TaskInfo>(){

            @Override
            protected TaskInfo doInBackground(Void... voids) {

                TaskInfo taskInfo = new TaskInfo();

                if (doValidate()){
                    taskInfo.setTaskName(editTextName.getText().toString());
                    taskInfo.setWorkInterval(Integer.parseInt(editTextWork.getText().toString()));
                    taskInfo.setTarget(Integer.parseInt(editTextTarget.getText().toString()));
                    taskInfo.setDone(0);
                    currentID = CurrentID.getCurrentIDInstance();
                    taskInfo.setUserID(currentID.getIdUser());
                    messageDB.getMessageInfoDAO().insert(taskInfo);
                    return null;
                }
                else

                    //showToast();

            return null;
            }
        }.execute();
        if (doValidate()) {
            Intent intent = new Intent(CreateActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        }
        else showToast();

    }

    private void showToast() {
        Toast.makeText(CreateActivity.this, validate.validateTaskName(editTextName.getText().toString())+"\n"
                +validate.validateWork(editTextWork.getText().toString())+"\n"+
                validate.validateTarget(editTextTarget.getText().toString())+"\n"+"Please try again!",Toast.LENGTH_LONG).show();
    }

    private boolean doValidate() {
        if (validate.validateTaskName(editTextName.getText().toString()) == "Validate Task Name Complete" &&
                validate.validateWork(editTextWork.getText().toString()) == "Validate Work Interval Complete" &&
                validate.validateTarget(editTextTarget.getText().toString())=="Validate Target Complete"){
            return true;
        }
        return false;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{
                Intent intent = new Intent(CreateActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.timer:{
                Intent intent = new Intent(CreateActivity.this, TimerActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.add:{
                Intent intent = new Intent(CreateActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.listmenu: {
                Intent intent = new Intent(CreateActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        drawerLayout.closeDrawers();

        return true;
    }



}
