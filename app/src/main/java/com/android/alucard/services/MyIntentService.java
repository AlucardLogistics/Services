package com.android.alucard.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    int time = 0;

    public MyIntentService() {
        super("myIntentServiceThread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //will get executed on a separate thread
        Log.d(TAG, "onHandleIntent: started");
        try {
            Toast.makeText(this, "Intent Service onHandleIntent", Toast.LENGTH_SHORT).show();
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: started");
        Toast.makeText(this, "Intent Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        String myName = intent.getStringExtra("drago");
        Log.d(TAG, "onStart: started");
        Toast.makeText(this, "Intent Service Started: " + myName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: started");
        Toast.makeText(this, "Intent Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}
