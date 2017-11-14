package wanroj.supanat.pomodoro_knight.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import wanroj.supanat.pomodoro_knight.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().setTitle("Create Task");
    }
}
