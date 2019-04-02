package com.example.myapplication;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onResume() {
        super.onResume();
        applyChanges();
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    protected void applyChanges() {
        String selectedColor = null;
        String selectedAnimationType = null;

        if(getIntent().getExtras() != null) {
            selectedColor = getIntent().getExtras().getString("color");
            selectedAnimationType = getIntent().getExtras().getString("animationType");
        }

        TextView infoTextView = findViewById(R.id.textViewInfo);

        if(selectedColor != null) {
            infoTextView.setBackgroundColor(Color.parseColor(selectedColor));
        } else {
            selectedColor = "color did not selected";
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
        } else {
            selectedAnimationType = "animation did not selected";
        }

        infoTextView.setText(selectedColor + " " + selectedAnimationType);
    }
}