package com.example.myapplication;

        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.TextView;

        import java.sql.Array;
        import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        db.execSQL("DELETE FROM Cars");

        db.execSQL("CREATE TABLE IF NOT EXISTS Cars (id INTEGER PRIMARY KEY ASC,type TEXT,manufacturer TEXT,model TEXT,trunkSize INTEGER,ABS TEXT,saftyPillows INTEGER,fuelConsumption INTEGER)");


        Cursor query = db.rawQuery("SELECT * FROM Cars;", null);

        if(query.getCount() == 0) {

            initBusTable(db);

        }

        query.close();

        db.close();

    }

    private void initBusTable (SQLiteDatabase db) {

        String[] manufacturer = {"LiAZ", "PAZ", "Ikarus", "TrolZa", "Scania", "MAZ", "Bogdan"};

        String[]  type = {"Microcar", "subcompact car", "midi size car", "full size car", "luctury car", "Roadster"};

        String[]  model = {"Range Rover", "Nissan Pathfinder", " Subaru Outback", "Toyota Corolla", "Renault Logan", "Volkswagen Polo "};

        String[]  abss = {"Avalible", "Unavalible"};

        for (int i = 1; i < 15; i++) {
            addCars(db, manufacturer[(int) Math.random() * 6],type[(int) Math.random() * 6],model[(int) Math.random() * 6],(int) Math.random() * 6,abss[(int) Math.random() * 6], (int) Math.random() * 6, 20 + (int) Math.random() * 40);
        }

    }

    private void addCars(SQLiteDatabase db , String manufacturer  ,String type  ,String model ,Integer trunkSize ,String ABS  , Integer saftyPillows, Integer fuelConsumption) {
        Log.d("db", "adding data");
        ContentValues values = new ContentValues();
        values.put("manufacturer", manufacturer);
        values.put("model", model);
        values.put("type", type);
        values.put("trunkSize", trunkSize);
        values.put("ABS", ABS);
        values.put("saftyPillows", saftyPillows);
        values.put("fuelConsumption", fuelConsumption);
        Long id = db.insert("Cars", null, values);

        if (id < 0) {
            Log.e("db", "can't add data");
        }
    }

    protected void onResume() {
        super.onResume();
        applyChanges();
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
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