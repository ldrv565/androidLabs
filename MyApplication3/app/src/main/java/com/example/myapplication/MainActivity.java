package com.example.myapplication;

import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setText(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String inputTextString = editText.getText().toString();
        TextView text = findViewById(R.id.text);
        text.setText(inputTextString);
    }

    public void setSize(View view) {
        EditText editSize = (EditText) findViewById(R.id.editSize);
        String[] inputSize = editSize.getText().toString().split(" ");
        int width = Integer.valueOf((inputSize[0]));
        int height = Integer.valueOf((inputSize[1]));

        TextView text = findViewById(R.id.text);
        text.setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
        text.layout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
    }
}
