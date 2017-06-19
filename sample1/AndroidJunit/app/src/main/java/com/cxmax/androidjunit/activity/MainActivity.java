package com.cxmax.androidjunit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cxmax.androidjunit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_jump)
    Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Deckard");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_jump)
    void jumpNewActivity(){
        Intent intent = new Intent(MainActivity.this, SampleActivity.class);
        startActivity(intent);
    }
}
