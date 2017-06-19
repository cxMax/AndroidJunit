package com.cxmax.androidjunit.receiver;

import android.app.Application;
import android.content.Intent;

import com.cxmax.androidjunit.BuildConfig;
import com.cxmax.androidjunit.service.SampleIntentService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AlarmManagerReceiverTest {
    @Test
    public void startServiceForTheScheduledAlarm(){
        Application application = RuntimeEnvironment.application;
        Intent expectedService = new Intent(application , SampleIntentService.class);

        AlarmManagerReceiver alarmManagerReceiver = new AlarmManagerReceiver();
        alarmManagerReceiver.onReceive(application , expectedService);

        Intent serviceIntent = Shadows.shadowOf(application).getNextStartedService();
        Assert.assertNotNull(serviceIntent);
        Assert.assertEquals(serviceIntent.getComponent(), serviceIntent.getComponent());
    }
}
