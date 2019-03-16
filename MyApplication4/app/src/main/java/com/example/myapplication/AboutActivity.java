package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String user = "";
        String gift = "";
        user = getIntent().getExtras().getString("username");
        gift = getIntent().getExtras().getString("gift");
        TextView infoTextView =
                (TextView)findViewById(R.id.textViewInfo);
        infoTextView.setText(user + " , вам передали " + gift);

    }
}