package com.example.myapplication;

        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.graphics.drawable.Drawable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;

        import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String selectedColor;
    private String selectedAnimationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRandomColor();
        selectedColor = null;
        selectedAnimationType = null;
    }

    public void onClickImageButton(View view) {
        ImageButton btn = (ImageButton) view;
        selectedColor = ((String) btn.getTag()).substring(0);
    }

    public void onClick(View view) {

        RadioGroup radioGroupAnimation = findViewById(R.id.radioGroupAnimation);
        RadioButton radio = findViewById(radioGroupAnimation.getCheckedRadioButtonId());

        if (radio != null) {
            selectedAnimationType = (String) radio.getText();
        }

        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        intent.putExtra("color", selectedColor);
        intent.putExtra("animationType", selectedAnimationType);
        startActivity(intent);
    }

    private void setRandomColor() {
        ImageButton button = findViewById(R.id.randomButton);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        button.setColorFilter(color);

        button.setTag("#" + Integer.toHexString(color));
    }
}
