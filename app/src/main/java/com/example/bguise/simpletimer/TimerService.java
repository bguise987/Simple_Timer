package com.example.bguise.simpletimer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import java.util.Calendar;

public class TimerService extends Service {
    private Handler handler = new Handler();  //Handles recurring time queries


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startService();
        return START_STICKY;
    }

    private void startService() {
        /* Have our handler run this after 0 ms */
        handler.postDelayed(updateTime, 0);

    }

    private Runnable updateTime = new Runnable() {
        private int hours, minutes, seconds;
        private String currTime;
        private Calendar cal; //  = Calendar.getInstance();

        @Override
        public void run() {
            /* Code to handle getting the current time and packing it into a String */
            cal = Calendar.getInstance();
            hours = cal.get(Calendar.HOUR_OF_DAY);
            minutes = cal.get(Calendar.MINUTE);
            seconds = cal.get(Calendar.SECOND);
            currTime = hours + ":" + minutes + ":" + seconds;

            Intent i = new Intent("TIME_UPDATE");
            i.putExtra("time", currTime);
            sendBroadcast(i);
            /* Have our handler run this Runnable() object after 5 sec */
            handler.postDelayed(this, 5000);
        }
    };

    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        /* This service won't support binding */
        return null;
    }
}
