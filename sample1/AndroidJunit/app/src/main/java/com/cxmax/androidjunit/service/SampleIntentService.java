package com.cxmax.androidjunit.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */

public class SampleIntentService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SampleIntentService() {
        super(SampleIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(
                "example", Context.MODE_PRIVATE).edit();
        editor.putString("SAMPLE_DATA","sample data");
        editor.apply();
    }
}
