package com.example.lab2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DigitalClock;

public class MainActivity extends AppCompatActivity {

    DigitalClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = (DigitalClock) findViewById(R.id.clock);
        registerForContextMenu(clock);
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
                return true;
            case R.id.color__blue:
                clock.setTextColor(Color.BLUE);
                return true;
            case R.id.color__green:
                clock.setTextColor(Color.GREEN);
                return true;
            case R.id.size__small:
                clock.setTextSize(8);
            case R.id.size__medium:
                clock.setTextSize(16);
            case R.id.size__large:
                clock.setTextSize(32);
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
        switch (item.getItemId()) {
            // пункты меню для clock
            case ALPHA:
                anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
                break;
            case ROTATE:
                anim = AnimationUtils.loadAnimation(this,R.anim.rotate);
                break;
            case SCALE:
                anim = AnimationUtils.loadAnimation(this,R.anim.scale);
                break;
            case TRANSLATE:
                anim = AnimationUtils.loadAnimation(this,R.anim.translate);
                break;
            case COMBO:
                anim = AnimationUtils.loadAnimation(this,R.anim.combo);
                break;
        }
        if (anim != null) {
            clock.startAnimation(anim);
        }
        return super.onContextItemSelected(item);
    }
}
