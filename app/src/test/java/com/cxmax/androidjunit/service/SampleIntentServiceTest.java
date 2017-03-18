package com.cxmax.androidjunit.service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cxmax.androidjunit.BuildConfig;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboSharedPreferences;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleIntentServiceTest {
    @Test
    public void addsDataToSharedPreference() {
        RoboSharedPreferences preferences = (RoboSharedPreferences)RuntimeEnvironment.application.getSharedPreferences(
                "example", Context.MODE_PRIVATE);
        Intent intent = new Intent(RuntimeEnvironment.application , SampleIntentService.class);
        SampleIntentService sampleIntentService = new SampleIntentService();

        sampleIntentService.onHandleIntent(intent);

        Assert.assertNotSame(preferences.getString("SAMPLE_DATA", ""), "");
    }
}
