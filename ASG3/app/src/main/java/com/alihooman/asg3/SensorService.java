package com.alihooman.asg3;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Background service for capturing sensor data.
 */
public class SensorService extends IntentService {
    String TAG = "[SensorService]";

    SensorData sensorData;
    Context context;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public SensorService() {
        super("Sensor_Service");
        //context = getApplicationContext();
        Log.d(TAG, "Sensor Service created.");
    }

    /**
     * Create sensor manager and a new sensor listener.
     * @param intent IntentService being called.
     * @param flags Flags are not used.
     * @param startId startId is not used.
     * @return START_STICKY to persist service.
     */
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //context = getApplicationContext();
        context = this;
        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorData = new SensorData(sensorMgr, context);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }
}
