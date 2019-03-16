package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String selectedColor = " ";
        String selectedAnimationType = " ";

        selectedColor = getIntent().getExtras().getString("color");
        selectedAnimationType = getIntent().getExtras().getString("animationType");

        TextView infoTextView =
                (TextView)findViewById(R.id.textViewInfo);
        infoTextView.setText(selectedColor + " " + selectedAnimationType);

        infoTextView.setBackgroundColor(Color.parseColor(selectedColor));

    }
}