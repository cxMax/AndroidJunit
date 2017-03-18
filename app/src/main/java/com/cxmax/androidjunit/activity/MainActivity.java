package com.cxmax.androidjunit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxmax.androidjunit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Deckard");
    }
}
