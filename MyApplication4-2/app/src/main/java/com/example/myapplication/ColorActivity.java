package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class ColorActivity extends Activity {

    private String selectedColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        setRandomColor();
        selectedColor = null;
    }

    private void setRandomColor() {
        ImageButton button = findViewById(R.id.randomButton);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        button.setColorFilter(color);

        button.setTag("#" + Integer.toHexString(color));
    }

    public void onClickImageButton(View view) {
        ImageButton btn = (ImageButton) view;
        selectedColor = ((String) btn.getTag()).substring(0);
    }

    public void onClick(View view) {

        Intent intent = new Intent(ColorActivity.this, AnimationActivity.class);
        intent.putExtra("color", selectedColor);
        startActivity(intent);
    }
}
