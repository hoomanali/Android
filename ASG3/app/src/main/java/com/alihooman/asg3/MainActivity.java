package com.alihooman.asg3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button reset;
    Button exit;
    TextView movedStatus;
    WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.resetButton);
        exit = findViewById(R.id.exitButton);
        movedStatus = findViewById(R.id.movedStatusText);

        checkMoved();

        Intent sensorIntent = new Intent(this, SensorService.class);
        startService(sensorIntent);

        // Keep the device awake
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "ASG3:wakelock");
        wakeLock.acquire();
    }

    /**
     * Set initial time according to pause.
     */
    @Override
    public void onPause() {
        super.onPause();
        TimeKeeper.saveInitialTime(this);
    }

    /**
     * Check if device has moved and update text view.
     */
    @Override
    public void onResume() {
        super.onResume();
        checkMoved();
    }

    /**
     * Check if device has moved and update text view.
     */
    public void checkMoved() {
        if(TimeKeeper.deviceMoved(this)) {
            movedStatus.setText(R.string.device_moved);
        } else {
            movedStatus.setText(R.string.device_not_moved);
        }
    }

    /**
     * Resets the intial time, acceleration values, and moved flag.
     * @param view The current application view.
     */
    public void resetButtonOnClick(View view) {
        SensorManager mgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        SensorData sensorData = new SensorData(mgr, this);
        TimeKeeper.initDeviceMoved(view.getContext());
        checkMoved();
        TimeKeeper.resetData(view.getContext());
    }

    /**
     * Reset all shared preferences and exit the application.
     * @param view The current view.
     */
    public void exitButtonOnClick(View view) {
        resetButtonOnClick(view);
        wakeLock.release();
        finish();
        System.exit(0);
    }
}
