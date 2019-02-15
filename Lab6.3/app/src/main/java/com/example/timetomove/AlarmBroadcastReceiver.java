package com.example.timetomove;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Hey! It's time to move!", Toast.LENGTH_LONG).show();
        Log.d("AlarmBroadCastReceiver", "onReceive Funktio.");

    }
}