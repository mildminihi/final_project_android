package wanroj.supanat.pomodoro_knight.Controller;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.Model.TaskToDo;
import wanroj.supanat.pomodoro_knight.R;
import wanroj.supanat.pomodoro_knight.View.AdapterList;

public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView list;
    private TextView nameTask;
    private List<TaskInfo> taskInfosGlobal;
    private AdapterList adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlist);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListner();





        showResult();


    }



    private void showResult() {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "TASKLIST").build();
        new AsyncTask<Void, Void, List<TaskInfo>>(){

            @Override
            protected List<TaskInfo> doInBackground(Void... voids) {
                List<TaskInfo> result = messageDB.getMessageInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(final List<TaskInfo> taskInfos) {
                list = (ListView) findViewById(R.id.listtask);
                taskInfosGlobal = taskInfos;
                AdapterList adapterList = new AdapterList(ListActivity.this, R.layout.list, taskInfos);
                list.setAdapter(adapterList);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        doAlertDialog(position);

//
                    }
                });

            }
        }.execute();
    }

    private void doAlertDialog(final int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListActivity.this);
        builder1.setMessage("Please Choose Select or Delete");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteLine(position);
                        Toast.makeText(ListActivity.this, "Task Deleted", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Select",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        TaskToDo taskToDo = TaskToDo.getTaskToDoInstance();
                        taskToDo.setId(taskInfosGlobal.get(position).getId());
                        taskToDo.setTaskName(taskInfosGlobal.get(position).getTaskName());
                        taskToDo.setWorkInterval(taskInfosGlobal.get(position).getWorkInterval());
                        taskToDo.setTarget(taskInfosGlobal.get(position).getTarget());
                        taskToDo.setDone(taskInfosGlobal.get(position).getDone());
                        Intent intent = new Intent(ListActivity.this, TimerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void onDeleteAll(View view) {

        alertSureDelete();

    }

    private void alertSureDelete() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListActivity.this);
        builder1.setMessage("You want to delete all tasks");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                                MessageDB.class, "TASKLIST").build();
                        new AsyncTask<Void, Void, List<TaskInfo>>() {
                            @Override
                            protected List<TaskInfo> doInBackground(Void... voids) {
                                messageDB.getMessageInfoDAO().deleteAll();
                                return null;
                            }
                        }.execute();
                        showResult();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void onAddTask(View view){
        alertSureGoToAdd();

    }

    private void alertSureGoToAdd() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListActivity.this);
        builder1.setMessage("You want to create task");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ListActivity.this, CreateActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }
    private void deleteLine(final int position) {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "TASKLIST").build();
        new AsyncTask<Void, Void, List<TaskInfo>>() {
            @Override
            protected List<TaskInfo> doInBackground(Void... voids) {
                messageDB.getMessageInfoDAO().deleteTask(taskInfosGlobal.get(position));
                return null;
            }
        }.execute();
        showResult();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile: {
                Intent intent = new Intent(ListActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.timer: {
                Intent intent = new Intent(ListActivity.this, TimerActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.add: {
                Intent intent = new Intent(ListActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.listmenu: {
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }

            drawerLayout.closeDrawers();

            return true;

        }

}


