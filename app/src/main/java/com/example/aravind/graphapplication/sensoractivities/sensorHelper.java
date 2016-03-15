package com.example.aravind.graphapplication.sensoractivities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.aravind.graphapplication.Classes.AccelerometerEntry;
import com.example.aravind.graphapplication.databasehelper.DatabaseMethods;

import java.security.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Aravind on 3/7/2016.
 */
public class sensorHelper implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context context;
    private static ArrayList<AccelerometerEntry> list;
    private static java.sql.Timestamp prev = null;

    public sensorHelper(Context context1){
        context = context1;
        DatabaseMethods obj = new DatabaseMethods(context);

        try {
            obj.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, 100000000);
        list = new ArrayList<AccelerometerEntry>();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        DatabaseMethods obj = new DatabaseMethods(context);

        try {
            obj.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = true;
        if(prev != null){
            long diff = currentTimestamp.getTime() - prev.getTime();
            ok = diff/1000 >= 1? true:false;
        }
        if(ok) {
            AccelerometerEntry accelerometerEntry = new AccelerometerEntry(currentTimestamp, event.values[0], event.values[1], event.values[2]);
            obj.AddAccelerometerValues(accelerometerEntry);
            list.add(accelerometerEntry);
            prev = currentTimestamp;

            //Log.v("SQL error",Integer.toString(obj.GetAccelerometerCount()));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
