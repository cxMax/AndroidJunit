package com.cxmax.androidjunit.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.cxmax.androidjunit.BuildConfig;
import com.cxmax.androidjunit.R;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import butterknife.BindView;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {


    private MainActivity mainActivity;
    @BindView(R.id.btn_jump)
    Button jump;

    @Before
    public void initField() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    /**
     * a first sample JUnit test
     */
    @Test
    public void titleIsCorrect() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        Assert.assertTrue(activity.getTitle().equals("Deckard"));
    }

    /**
     * JUnit test for jumping between activities
     * @throws Exception
     */
    @Test
    public void jumpNewActivity() throws Exception {
        jump.performClick();
        Intent excepted = new Intent(mainActivity, SampleActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(excepted , actual);
    }
}
