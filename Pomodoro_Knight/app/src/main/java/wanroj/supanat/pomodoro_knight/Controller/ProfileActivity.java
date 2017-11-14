package wanroj.supanat.pomodoro_knight.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import wanroj.supanat.pomodoro_knight.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textEmail;
    private TextView textId;
    private ImageView imageProfile;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");



        textName = (TextView)findViewById(R.id.textName);
        textEmail = (TextView)findViewById(R.id.textEmail);
        textId = (TextView)findViewById(R.id.textId);
        imageProfile =(ImageView)findViewById(R.id.imageProfile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
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
    public void onGo(View view){
        Intent intent = new Intent(ProfileActivity.this, TimerActivity.class);
        startActivity(intent);
    }
}
