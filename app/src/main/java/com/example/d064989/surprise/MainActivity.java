package com.example.d064989.surprise;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable, SensorEventListener {

    private ConstraintLayout screen;
    private Handler timerHandler;
    private long delay;
    SensorManager m;
    TextView welcome;
    private int colorId;
    private ColorDrawable color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m.registerListener(this, m.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);

        screen = findViewById(R.id.Layout);
        welcome = findViewById(R.id.welcome);

    }

    public void onSetup(View button) {
        screen.setBackgroundColor(Color.YELLOW);
        welcome.setText("");

        getDelayInput();
    }

    public void setDelay(long delay, FragmentActivity that) {
       screen = that.findViewById(R.id.Layout);
        if (setTimer.isDismissed) {
            timerHandler = new Handler();
            timerHandler.postDelayed(this, delay);
        }
    }

    private void getDelayInput() {
        FragmentManager fm = getSupportFragmentManager();
        setTimer dialogFragment = new setTimer();
        dialogFragment.show(fm, "settimes_fragment");
    }

    @Override
    public void run() {
        screen.setBackgroundColor(Color.RED);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if ((screen != null) && (screen.getBackground() != null)) {
            color = (ColorDrawable) screen.getBackground();
            colorId = color.getColor();

            if ((colorId == Color.RED) && (event.values[0] > 250)) {
                screen.setBackgroundColor(Color.GREEN);
                welcome.setText("CONGRATULATIONS");
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

}
