package com.example.aravind.graphapplication.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aravind on 3/7/2016.
 */
public class DatabaseMethods {

    private SQLiteDatabase database;
    private SQLiteHelperClass dbhelper;

    private String[] allColoumnns_Accelerator = {DbStrings.COLUMN_ID, DbStrings.COLUMN_ID, DbStrings.COLUMN_TIME_STAMP, DbStrings.COLUMN_X, DbStrings.COLUMN_Y, DbStrings.COLUMN_Z};

    public DatabaseMethods(Context context){
        dbhelper = new SQLiteHelperClass(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void AddAccelerometerValues(String timeStamp, String x_values, String y_values, String z_values){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DbStrings.COLUMN_TIME_STAMP, timeStamp);
        contentValue.put(DbStrings.COLUMN_X, x_values);
        contentValue.put(DbStrings.COLUMN_Y, y_values);
        contentValue.put(DbStrings.COLUMN_Y, z_values);
        long insertId = database.insert(DbStrings.TABLE_Name_ID_Age_Sex, null, contentValue);
    }

    public ArrayList<String> GetAccelerometerValues() {
        ArrayList<String> values = new ArrayList<String>();
        Cursor cursor = database.query(DbStrings.TABLE_Name_ID_Age_Sex, allColoumnns_Accelerator,null,null,null,null,null);
        //Cursor cursor = null;
        try {
            cursor = database.rawQuery("select * from (select * from Name_ID_Age_Sex order by _id ASC limit 10) order by _id DESC", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                values.add(cursor.getString(2));
                cursor.moveToNext();
            }
        }
        catch (Exception ex){

        }finally {
            cursor.close();
        }


        return values;
    }
}
