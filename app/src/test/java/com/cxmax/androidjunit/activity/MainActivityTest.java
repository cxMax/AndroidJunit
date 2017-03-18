package com.cxmax.androidjunit.activity;

import android.app.Activity;

import com.cxmax.androidjunit.BuildConfig;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
public class MainActivityTest {

    @Test
    public void titleIsCorrect() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        Assert.assertTrue(activity.getTitle().equals("Deckard"));
    }
}
