package com.cxmax.androidjunit.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cxmax.androidjunit.service.SampleIntentService;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */

public class AlarmManagerReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context, SampleIntentService.class);
        context.startService(intentService);
    }
}
