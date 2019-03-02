package com.example.lab2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DigitalClock clock;
    LinearLayout window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        registerForContextMenu(clock);
        window = findViewById(R.id.window);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id)
        {
            case R.id.color__red:
                clock.setTextColor(Color.RED);
                Log.d("SET_color","red");
                Toast.makeText(this,"red", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.color__blue:
                clock.setTextColor(Color.BLUE);
                Log.d("SET_color","blue");
                Toast.makeText(this,"blue", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.color__green:
                clock.setTextColor(Color.GREEN);
                Log.d("SET_color","green");
                Toast.makeText(this,"green", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.size__small:
                clock.setTextSize(8);
                Log.d("SET_size","small");
                Toast.makeText(this,"small", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.size__medium:
                clock.setTextSize(16);
                Log.d("SET_size","medium");
                Toast.makeText(this,"medium", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.size__large:
                clock.setTextSize(32);
                Log.d("SET_size","large");
                Toast.makeText(this,"large", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.position__top_left:
                window.setGravity(Gravity.TOP);
                Log.d("SET_position","top_left");
                Toast.makeText(this,"top_left", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.position__center:
                window.setGravity(Gravity.CENTER);
                Log.d("SET_position","center");
                Toast.makeText(this,"center", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.position__bottom_right:
                window.setHorizontalGravity(Gravity.END);
                window.setVerticalGravity(Gravity.BOTTOM);
                Log.d("SET_position","bottom_right");
                Toast.makeText(this,"bottom_right", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    final int ALPHA = 7;
    final int ROTATE = 8;
    final int SCALE = 9;
    final int TRANSLATE = 10;
    final int COMBO = 11;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.clock:
                menu.add(0, ALPHA, 0, "ALPHA");
                menu.add(0, ROTATE, 0, "ROTATE");
                menu.add(0, SCALE, 0, "SCALE");
                menu.add(0, TRANSLATE, 0, "TRANSLATE");
                menu.add(0, COMBO, 0, "COMBO");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Animation anim = null;
        String animationType = "null";
        switch (item.getItemId()) {
            // пункты меню для clock
            case ALPHA:
                anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
                animationType = "alpha";
                break;
            case ROTATE:
                anim = AnimationUtils.loadAnimation(this,R.anim.rotate);
                animationType = "rotate";
                break;
            case SCALE:
                anim = AnimationUtils.loadAnimation(this,R.anim.scale);
                animationType = "scale";
                break;
            case TRANSLATE:
                anim = AnimationUtils.loadAnimation(this,R.anim.translate);
                animationType = "translate";
                break;
            case COMBO:
                anim = AnimationUtils.loadAnimation(this,R.anim.combo);
                animationType = "combo";
                break;
        }
        if (anim != null) {
            clock.startAnimation(anim);
        }
        Log.d("Animation_type", animationType);
        Toast.makeText(this, animationType, Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
