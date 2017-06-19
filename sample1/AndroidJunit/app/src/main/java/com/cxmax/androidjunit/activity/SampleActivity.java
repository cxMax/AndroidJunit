package com.cxmax.androidjunit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.cxmax.androidjunit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */

public class SampleActivity extends AppCompatActivity {

    @BindView(R.id.sample_demo)
    Button sampleDemo;
    @BindView(R.id.btn_forward)
    Button btnForward;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_toast)
    Button btnToast;
    @BindView(R.id.btn_inverse)
    Button btnInverse;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_lifecycle_label)
    TextView tvLifecycleLabel;
    @BindView(R.id.tv_lifecycle_value)
    TextView tvLifecycleValue;
    @BindView(R.id.btn_delay_task)
    Button btnDelayTask;
    @BindView(R.id.btn_callback)
    Button btnCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.sample_demo, R.id.btn_forward, R.id.btn_dialog, R.id.btn_toast, R.id.btn_inverse, R.id.checkbox, R.id.tv_lifecycle_label, R.id.tv_lifecycle_value, R.id.btn_delay_task, R.id.btn_callback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample_demo:
                break;
            case R.id.btn_forward:
                break;
            case R.id.btn_dialog:
                showDialog(view);
                break;
            case R.id.btn_toast:
                showToast(view);
                break;
            case R.id.btn_inverse:
                inverse(view);
                break;
            case R.id.checkbox:
                break;
            case R.id.tv_lifecycle_label:
                break;
            case R.id.tv_lifecycle_value:
                break;
            case R.id.btn_delay_task:
                break;
            case R.id.btn_callback:
                callback(view);
                break;
        }
    }

    private void showDialog(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.sample_dialog_message)
                .setTitle(R.string.sample_dialog_title).create();
        alertDialog.show();
    }

    private void showToast(View view){
        Toast.makeText(this,"Robolectric JUnit Test",Toast.LENGTH_LONG).show();
    }

    private void inverse(View view) {
        checkbox.setChecked(!checkbox.isChecked());
    }

    private void callback(View view) {
        startActivity(new Intent(this,HttpRequestActivity.class));
    }
}
