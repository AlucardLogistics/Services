package com.android.alucard.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    startMusic();
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    stopMusic();
                }

                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out);
            }
        };
        thread.start();
    }

    private void startMusic() {
        Intent i = new Intent(SplashActivity.this, MyService.class);
        startService(i);
    }

    private void stopMusic() {
        Intent i = new Intent(SplashActivity.this, MyService.class);
        stopService(i);
    }
}
