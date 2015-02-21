package com.example.bguise.simpletimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {

    private TextView timeDisp;

    private IntentFilter filter;
    private BroadcastReceiver receiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeDisp = (TextView)findViewById(R.id.time_disp);

        /* Setup our BroadcastReceiver */
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                /* Extract String of current time from Intent */
                String currTime = intent.getStringExtra("time");
                /* Update the display with current time String */
                timeDisp.setText(currTime);
            }
        };



        filter = new IntentFilter();
        filter.addAction("TIME_UPDATE");

        registerReceiver(receiver, filter);

        startService(new Intent(MainActivity.this, TimerService.class));
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent (MainActivity.this, TimerService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
