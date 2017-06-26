package com.cxmax.androidjunit.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.widget.Button;

import com.cxmax.androidjunit.BuildConfig;
import com.cxmax.androidjunit.R;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import butterknife.BindView;

import static org.robolectric.Shadows.shadowOf;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/18.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
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
        Assert.assertNotNull(activity);
    }

    /**
     * JUnit test for jumping between activities
     * @throws Exception
     */
    @Test
    public void jumpNewActivity() throws Exception {
        jump.performClick();
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
        ShadowActivity shadowActivity = shadowOf(activity);
        Assert.assertThat(shadowActivity.peekNextStartedActivityForResult().intent.getComponent(), CoreMatchers.equalTo(new ComponentName(activity , ShadowActivity.class)));
    }
}
