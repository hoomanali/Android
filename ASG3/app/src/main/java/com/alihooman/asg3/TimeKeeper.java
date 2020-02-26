package com.alihooman.asg3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.Calendar;

/**
 * Static helper class to keep initial accel values and time.
 */
class TimeKeeper {
    private static String TAG = "[TimeKeeper]";
    private static long SECOND = 1000;

    /**
     * Save the current time to shared preferences with 15 seconds added.
     * @param context Current application context.
     */
    static void saveInitialTime(Context context) {
        Editor editor = getEditor(context);

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis() + (15 * SECOND);

        editor.putLong("InitialTime", time);
        editor.commit();

        String logmsg = "Initial time (" + time + ") save.";
        Log.d(TAG, logmsg);
    }

    /**
     * Save the initial X, Y, and Z acceleration values.
     * @param context Current application context.
     * @param x Initial X acceleration.
     * @param y Initial Y acceleration.
     * @param z Initial Z acceleration.
     */
    static void saveInitialAccel(Context context, float x, float y, float z) {
        Editor editor = getEditor(context);

        editor.putFloat("InitialX", x);
        editor.putFloat("InitialY", y);
        editor.putFloat("InitialZ", z);
        editor.commit();

        String logmsg = "Initial acceleration values (" + x + ", " + y + ", " + z + ") saved.";
        Log.d(TAG, logmsg);
    }

    /**
     * Retrieves the initial sensor service start time in milliseconds.
     * @param context Current application context.
     * @return The initial time in milliseconds.
     */
    static long getInitialTime(Context context) {
        Log.d(TAG, "Fetching initial time");
        SharedPreferences sharedPrefs = context.getSharedPreferences("prefs", 0);
        return sharedPrefs.getLong("InitialTime", -1);
    }

    /**
     * Retrieves the moved time in milliseconds.
     * @param context Current application context.
     * @return The device moved time in milliseconds.
     */
    static long getMovedTime(Context context) {
        Log.d(TAG, "Fetching moved time");
        SharedPreferences sharedPrefs = context.getSharedPreferences("prefs", 0);
        return sharedPrefs.getLong("MovedTime", -1);
    }

    /**
     * Return current time device is being checked, minus 15 seconds.
     * @param context Current application context.
     * @return The current time minus 15 seconds.
     */
    static long getFinalTime(Context context) {
        Log.d(TAG, "Fetching final time");
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() - (15 * SECOND);
    }

    /**
     * Return current time.
     * @param context Current application context.
     * @return The current time.
     */
    static long getCurrentTime(Context context) {
        Log.d(TAG, "Fetching current time");
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }



    /**
     * Retrieves an array [X,Y,Z] holding the initial acceleration values.
     * @param context Current application context.
     * @return An Array [X,Y,Z] holding the initial acceleration values.
     */
    static float[] getInitialAccel(Context context) {
        float[] XYZaccel;
        XYZaccel = new float[3];

        SharedPreferences sharedPrefs = context.getSharedPreferences("prefs", 0);

        XYZaccel[0] = sharedPrefs.getFloat("InitialX", -1);
        XYZaccel[1] = sharedPrefs.getFloat("InitialY", -1);
        XYZaccel[2] = sharedPrefs.getFloat("InitialZ", -1);

        String logmsg = "Getting initial acceleration values: "
                + XYZaccel[0] + ", "
                + XYZaccel[1] + ", "
                + XYZaccel[2] + ".";
        Log.d(TAG, logmsg);

        return XYZaccel;
    }

    /**
     * Set device moved flag to false;
     * @param context Current application context.
     */
    static void initDeviceMoved(Context context) {
        Editor editor = getEditor(context);
        editor.putBoolean("DeviceMoved", false);
        editor.commit();

        String logmsg = "DeviceMoved flag set to false.";
        Log.d(TAG, logmsg);
    }

    /**
     * Set the device moved flag to true.
     * @param context Current application context.
     */
    static void setDeviceMoved(Context context) {
        if(deviceMoved(context)) {
            String logmsg = "Device has already moved.";
            Log.d(TAG, logmsg);
        } else {
            long time = getCurrentTime(context);
            Editor editor = getEditor(context);
            editor.putBoolean("DeviceMoved", true);
            editor.putLong("MovedTime", time);
            editor.commit();
            String logmsg = "Set device moved to true.";
            Log.d(TAG, logmsg);
        }
    }

    /**
     * Determine if device moved flag was flipped to true.
     * @param context Current application context.
     * @return True if device has moved.
     */
    static Boolean deviceMoved(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("prefs", 0);

        long movedTime = getMovedTime(context);
        long currentTime = getCurrentTime(context);

        if(sharedPrefs.getBoolean("DeviceMoved", false)
            && movedTime < currentTime - (15 * SECOND)) {
            String logmsg = "Device has moved.";
            Log.d(TAG, logmsg);
            return true;
        } else {
            String logmsg = "Device has not moved.";
            Log.d(TAG, logmsg);
            return false;
        }
    }

    static void resetData(Context context) {
        Editor editor = getEditor(context);
        editor.putLong("InitialTime", 0);
        editor.putFloat("InitialX", 0);
        editor.putFloat("InitialY", 0);
        editor.putFloat("InitialZ", 0);
        editor.commit();

        String logmsg = "Initial time, X, Y, and Z values reset to 0.";
        Log.d(TAG, logmsg);
    }

    /**
     * Return an editor for shared preferences.
     * @param context Current application context.
     * @return A SharedPreferences Editor.
     */
    private static Editor getEditor(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("prefs", 0);
        return sharedPrefs.edit();
    }
}
