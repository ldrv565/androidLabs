package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String selectedColor = getIntent().getExtras().getString("color");
        String selectedAnimationType = getIntent().getExtras().getString("animationType");

        TextView infoTextView =
                (TextView)findViewById(R.id.textViewInfo);
        infoTextView.setText(selectedColor + " " + selectedAnimationType);

        if(selectedColor != null) {
            infoTextView.setBackgroundColor(Color.parseColor(selectedColor));
        }

        if(selectedAnimationType != null) {
            Animation anim = null;
            switch (selectedAnimationType) {
                // пункты меню для clock
                case "alpha":
                    anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
                    break;
                case "rotate":
                    anim = AnimationUtils.loadAnimation(this,R.anim.rotate);
                    break;
                case "scale":
                    anim = AnimationUtils.loadAnimation(this,R.anim.scale);
                    break;
                case "translate":
                    anim = AnimationUtils.loadAnimation(this,R.anim.translate);
                    break;
            }
            if (anim != null) {
                infoTextView.startAnimation(anim);
            }
            Log.d("Animation_type", selectedAnimationType);
        }
    }
}