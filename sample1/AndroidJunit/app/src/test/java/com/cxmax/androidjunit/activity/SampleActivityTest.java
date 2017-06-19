package com.cxmax.androidjunit.activity;

import android.app.AlertDialog;
import android.widget.Button;
import android.widget.CheckBox;

import com.cxmax.androidjunit.BuildConfig;
import com.cxmax.androidjunit.R;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowToast;

import butterknife.BindView;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SampleActivityTest {
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_toast)
    Button btnToast;
    @BindView(R.id.btn_inverse)
    Button btnInverse;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    @Test
    public void testToast() {
        btnToast.performClick();
        Assert.assertEquals(ShadowToast.getTextOfLatestToast() , "Robolectric JUnit Test");
    }

    @Test
    public void testDialog() {
        btnDialog.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        Assert.assertNotNull(dialog);
    }

    @Test
    public void testCheckBoxState() {
        Assert.assertTrue(btnInverse.isEnabled());

        checkbox.setChecked(true);
        btnInverse.performClick();
        Assert.assertTrue(!checkbox.isChecked());
        btnInverse.performClick();
        Assert.assertTrue(checkbox.isChecked());
    }
}
