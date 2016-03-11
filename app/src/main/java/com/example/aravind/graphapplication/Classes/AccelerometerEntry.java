package com.example.aravind.graphapplication.Classes;

/**
 * Created by Aravind on 3/10/2016.
 */
public class AccelerometerEntry {
    public String timeStamp;
    public String x;
    public String y;
    public String z;

    public AccelerometerEntry(){

    }
    public AccelerometerEntry(String _timeStamp,String _x,String _y, String _z){
        timeStamp = _timeStamp;
        x = _x;
        y = _y;
        z = _z;
    }
}
