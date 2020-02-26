package com.alihooman.asg3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Class to manage motion sensor data.
 * Sensors: Acceleration
 */
class SensorData implements SensorEventListener {
    private static String TAG = "[SensorData]";

    private Context context;
    private SensorManager sensorManager;
    private Sensor sensorAccel;
    private float initialX = Float.MAX_VALUE;
    private float initialY = Float.MAX_VALUE;
    private float initialZ = Float.MAX_VALUE;

    /**
     * Constructs a sensor manager and handles all sensor data
     * @param sensorMgr A sensor manager created by the calling thread.
     */
    SensorData(SensorManager sensorMgr, Context context) {
        this.context= context;
        this.sensorManager = sensorMgr;
        Log.d(TAG, "Sensor Manager created.");

        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        try {
            registerAccelListener();
            TimeKeeper.saveInitialTime(context);
        } catch (NullPointerException npe) {
            Log.d(TAG, "Sensor sensorAccel is null", npe);
        }
    }

    /**
     * Do things when X,Y,Z sensor values change.
     * @param event The current sensor's events
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float accelDirectionX = event.values[0];
        float accelDirectionY = event.values[1];
        float accelDirectionZ = event.values[2];

        if(initialX != Float.MAX_VALUE
            || initialY != Float.MAX_VALUE
            || initialZ != Float.MAX_VALUE) {
            initialX = accelDirectionX;
            initialY = accelDirectionY;
            initialZ = accelDirectionZ;
            setInitialAccels(initialX, initialY, initialZ);
        }

        long initialTime = TimeKeeper.getInitialTime(context);
        long finalTime = TimeKeeper.getFinalTime(context);
        long movedTime = TimeKeeper.getMovedTime(context);

        float[] initialValues = TimeKeeper.getInitialAccel(context);

        Log.d(TAG, "Current X: " + accelDirectionX);
        Log.d(TAG, "Current Y: " + accelDirectionY);
        Log.d(TAG, "Current Z: " + accelDirectionZ);
        Log.d(TAG, "Initial X: " + initialValues[0]);
        Log.d(TAG, "Initial Y: " + initialValues[1]);
        Log.d(TAG, "Initial Z: " + initialValues[2]);
        Log.d(TAG, "Initial time: " + initialTime);
        Log.d(TAG, "Final time: " + finalTime);

        if(initialValues != event.values
                && finalTime >= initialTime
                && movedTime < finalTime) {
            TimeKeeper.setDeviceMoved(context);
            TimeKeeper.deviceMoved(context);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * Registers the acceleration sensor listener using a UI (2) delay time.
     */
    private void registerAccelListener() {
        sensorManager.registerListener(this,
                sensorAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "Registered acceleration sensor listener.");
    }

    /**
     * Sets initial values
     * @param x Initial x value.
     * @param y Initial y value.
     * @param z Initial z value.
     */
    private void setInitialAccels(float x, float y, float z) {
        TimeKeeper.saveInitialAccel(context, x, y, z);
    }

    /**
     * Unregisters the acceleration sensor listener.
     */
    void unregisterAccelListener() {
        sensorManager.unregisterListener(this, sensorAccel);
        Log.d(TAG, "Unregistered acceleration sensor listener.");
    }
}
