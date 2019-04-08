package com.example.simpleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;


public class SimpleIntentService extends IntentService {

    public static final String ACTION_1 = "MY_ACTION_1";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(SimpleIntentService.ACTION_1);

        for(int i = 0; i <= 100; i++){
            broadcastIntent.putExtra("percel",i);
            sendBroadcast(broadcastIntent);
            SystemClock.sleep(100);
        }
    }

}
