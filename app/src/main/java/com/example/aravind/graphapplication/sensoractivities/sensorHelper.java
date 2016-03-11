package com.example.aravind.graphapplication.sensoractivities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.aravind.graphapplication.databasehelper.DatabaseMethods;

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

    public sensorHelper(Context context1){
        context = context1;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        obj.AddAccelerometerValues(currentTimestamp.toString(), Double.toString(event.values[0]), Double.toString(event.values[1]), Double.toString(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
