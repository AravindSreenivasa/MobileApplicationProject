package com.example.aravind.graphapplication.Classes;

import java.sql.Timestamp;

/**
 * Created by Aravind on 3/10/2016.
 */
public class AccelerometerEntry {
    public Timestamp timeStamp;
    public double x;
    public double y;
    public double z;

    public AccelerometerEntry(){

    }
    public AccelerometerEntry(Timestamp _timestamp, double _x,double _y, double _z){
        timeStamp = _timestamp;
        x = _x;
        y = _y;
        z = _z;
    }
    public AccelerometerEntry(double _x,double _y, double _z){
        timeStamp = null;
        x = _x;
        y = _y;
        z = _z;
    }
}
