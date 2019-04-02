package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AnimationActivity extends Activity {

    private String selectedColor;
    private String selectedAnimationType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        selectedAnimationType = null;
    }

    protected void onResume(){
        super.onResume();
        applyColor();
    }


    private void applyColor() {

        if(getIntent().getExtras() != null) {
            selectedColor = getIntent().getExtras().getString("color");
        }
    }

    public void onClick(View view) {

        RadioGroup radioGroupAnimation = findViewById(R.id.radioGroupAnimation);
        RadioButton radio = findViewById(radioGroupAnimation.getCheckedRadioButtonId());

        if (radio != null) {
            selectedAnimationType = (String) radio.getText();
        }

        Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
        intent.putExtra("color", selectedColor);
        intent.putExtra("animationType", selectedAnimationType);
        startActivity(intent);
    }
}
