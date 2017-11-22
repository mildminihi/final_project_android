package wanroj.supanat.pomodoro_knight.Controller;

import android.Manifest;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.AppUserInfo;
import wanroj.supanat.pomodoro_knight.Model.CurrentID;
import wanroj.supanat.pomodoro_knight.Model.Rank;
import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.R;
import wanroj.supanat.pomodoro_knight.View.Screenshot;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private TextView textName, sumTargetDis, sumDoneDis, displayState, displayNext;
    private String uid;
    private ImageView imageProfile, imageView;
    private DrawerLayout drawerLayout;
    private CurrentID currentID;
    private int sumTarget, sumDone;
    private TextView displayName;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;
    private View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        rootView = (View)drawerLayout.getRootView();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sumDoneDis = (TextView)findViewById(R.id.sumDone);
        sumTargetDis = (TextView)findViewById(R.id.sumTarget);
        displayState = (TextView)findViewById(R.id.displayState);
        displayNext = (TextView)findViewById(R.id.displayNext);
        textName = (TextView)findViewById(R.id.textName);
        displayName = (TextView)findViewById(R.id.textNameChar);
        imageProfile =(ImageView)findViewById(R.id.imageProfile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        getInformationProfile(user);
        setNavigationViewListner();
        currentID = CurrentID.getCurrentIDInstance();
        currentID.setIdUser(uid);
        showResult();
        if (currentID.getNameUser() != null){
            displayName.setText(currentID.getNameUser());
        }

    }

    private void showResult() {
        final MessageDB messageDB = Room.databaseBuilder(getApplicationContext(),
                MessageDB.class, "TASKLIST").build();
        new AsyncTask<Void, Void, List<TaskInfo>>(){

            @Override
            protected List<TaskInfo> doInBackground(Void... voids) {
                currentID = CurrentID.getCurrentIDInstance();
                List<TaskInfo> result = messageDB.getMessageInfoDAO().findTaskByUid(currentID.getIdUser());
                return result;
            }

            @Override
            protected void onPostExecute(final List<TaskInfo> taskInfos) {
                sumDone = 0;
                sumTarget = 0;
                for (TaskInfo table: taskInfos) {
                    sumTarget += table.getTarget();
                    sumDone += table.getDone();
                }
                Rank rank = new Rank();
                sumTargetDis.setText(String.valueOf(sumTarget));
                sumDoneDis.setText(String.valueOf(sumDone));
                displayState.setText("Now you're the : "+rank.checkRank(sumDone));
                displayNext.setText("Next Rank is : "+rank.checkNext(sumDone));

            }
        }.execute();
    }

    private void showNameDialog(final TextView displayName) {
        final Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        final EditText editText = (EditText)dialog.findViewById(R.id.nameChar);
        Button button = (Button)dialog.findViewById(R.id.buttonOK);
        dialog.setCancelable(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayName.setText(editText.getText());
                currentID.setNameUser(editText.getText().toString());

                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public void onEdit(View view){
        showNameDialog(displayName);
    }



    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getInformationProfile(FirebaseUser user){
        if (user != null) {
            AppUserInfo appUserInfo = new AppUserInfo(user.getDisplayName(), user.getEmail(), user.getPhotoUrl(), user.getUid());
            String name = appUserInfo.getName();
//            String email = appUserInfo.getEmail();
            Uri photoUrl = appUserInfo.getPhotoUrl();
            uid = appUserInfo.getUid();
            textName.setText(name);
//            textEmail.setText(email);
//            textId.setText(uid);
            Glide.with(ProfileActivity.this).load(photoUrl.toString()).into(imageProfile);

        } else {
            goLoginScreen();
        }
    }



    private void goLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{
                break;
            }
            case R.id.timer:{
                Intent intent = new Intent(ProfileActivity.this, TimerActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.add:{
                Intent intent = new Intent(ProfileActivity.this, CreateActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.listmenu: {
                Intent intent = new Intent(ProfileActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        drawerLayout.closeDrawers();

        return true;
    }


    //--------------------------Shared

    private void createShareIntent(Uri uriImage) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
        try {
            startActivity(Intent.createChooser(shareIntent, " How do you want to share? "));
        } catch (ActivityNotFoundException e) {
        }
    }
    public void onCheck(View view) {
        if (askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_REQUEST_CODE)) {
            Bitmap image = Screenshot.takescreenshotOfRootView(rootView);
            Uri screenshotUri = Screenshot.getImageUri(this.getApplicationContext(), image);

            createShareIntent(screenshotUri);
        }
    }
    private boolean askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(ProfileActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{permission}, requestCode);
            return false;
        } else {
            Toast.makeText(ProfileActivity.this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ProfileActivity.this, "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
