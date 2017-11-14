package wanroj.supanat.pomodoro_knight.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import wanroj.supanat.pomodoro_knight.R;

public class TimerActivity extends AppCompatActivity {
    public int seconds = 3;
    public int minutes = 3;
    Button s_button, pause_button;
    private int checkStart = 0, checkPause = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        getSupportActionBar().setTitle("Timer");
        s_button = (Button) findViewById(R.id.buttonStop_Start);
        pause_button = (Button) findViewById(R.id.buttonPause);

    }

    public void onS(View view) {
        timer();

    }

    public void onPause(View view) {
        checkPause = 0;
        pause_button.setText("Pause");


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
                            TextView tv = (TextView) findViewById(R.id.textViewTimer);
                            tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                            if (seconds != 0) seconds -= 1;
                            tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

                            if (seconds == 0) {
                                tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

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
                                tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
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


}
