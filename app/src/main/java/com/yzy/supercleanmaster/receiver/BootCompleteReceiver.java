package com.yzy.supercleanmaster.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yzy.supercleanmaster.service.CoreService;

public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, CoreService.class);
        context.startService(i);


    }

}
