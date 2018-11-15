package com.example.lenovo.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2018/10/6.
 */
public class SplashActivity extends Activity {
    static double DELAY = 3*1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(task,1*1000);
    }

}
