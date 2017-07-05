package com.cxmax.espresso_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextView;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.changeTextBt).setOnClickListener(this);
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);
        findViewById(R.id.activityCalculatorBtn).setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.textToBeChanged);
        mEditText = (EditText) findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View view) {
        final String text = mEditText.getText().toString();

        switch (view.getId()) {
            case R.id.changeTextBt:
                mTextView.setText(text);
                break;
            case R.id.activityChangeTextBtn:
                Intent intent = ShowTextActivity.newStartIntent(this, text);
                startActivity(intent);
                break;
            case R.id.activityCalculatorBtn:
                Intent calculator = CalculatorActivity.newStartIntent(this);
                startActivity(calculator);
                break;
        }
    }
}
