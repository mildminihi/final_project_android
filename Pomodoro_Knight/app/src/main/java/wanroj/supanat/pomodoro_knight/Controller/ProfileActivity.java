package wanroj.supanat.pomodoro_knight.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import wanroj.supanat.pomodoro_knight.Model.AppUserInfo;
import wanroj.supanat.pomodoro_knight.R;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private NavigationView navigationView;
    private TextView textName, textEmail, textId;

    private ImageView imageProfile;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textName = (TextView)findViewById(R.id.textName);
        textEmail = (TextView)findViewById(R.id.textEmail);
        textId = (TextView)findViewById(R.id.textId);
        imageProfile =(ImageView)findViewById(R.id.imageProfile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        getInformationProfile(user);
        setNavigationViewListner();




    }

    private void setNavigationViewListner() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
            String email = appUserInfo.getEmail();
            Uri photoUrl = appUserInfo.getPhotoUrl();
            String uid = appUserInfo.getUid();
            textName.setText(name);
            textEmail.setText(email);
            textId.setText(uid);
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
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
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
}
