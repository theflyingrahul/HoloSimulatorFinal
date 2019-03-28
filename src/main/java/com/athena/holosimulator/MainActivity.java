package com.athena.holosimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vuforia(View view) {
        Intent toVuforia = new Intent(MainActivity.this, UnityPlayerActivity.class);
        startActivity(toVuforia);
    }

    public void sensorgraph(View view) {
        Intent toSensor = new Intent(MainActivity.this, SensorActivity.class);
        startActivity(toSensor);
    }

    public void trajectory(View view) {
        Intent toTrajectory = new Intent(MainActivity.this, TrajectoryActivity.class);
        startActivity(toTrajectory);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
