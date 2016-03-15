package com.example.aravind.graphapplication.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aravind.graphapplication.Classes.AccelerometerEntry;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Aravind on 3/7/2016.
 */
public class DatabaseMethods {


    private SQLiteDatabase database;
    private SQLiteHelperClass dbhelper;

    private String[] allColoumnns_Accelerator = {DbStrings.COLUMN_ID, DbStrings.COLUMN_TIME_STAMP, DbStrings.COLUMN_X, DbStrings.COLUMN_Y, DbStrings.COLUMN_Z};

    public DatabaseMethods(Context context){
        dbhelper = SQLiteHelperClass.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        dbhelper.close();
    }

    public void AddAccelerometerValues(AccelerometerEntry obj){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DbStrings.COLUMN_TIME_STAMP, obj.timeStamp.toString());
        contentValue.put(DbStrings.COLUMN_X, obj.x);
        contentValue.put(DbStrings.COLUMN_Y, obj.y);
        contentValue.put(DbStrings.COLUMN_Y, obj.z);
        long insertId = database.insert(DbStrings.TABLE_Name_ID_Age_Sex, null, contentValue);
    }

    public int GetAccelerometerCount(){
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM "+DbStrings.TABLE_Name_ID_Age_Sex, null);

        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public ArrayList<AccelerometerEntry> GetAccelerometerValues() {
        ArrayList<AccelerometerEntry> values = new ArrayList<>();
        Cursor cursor = database.query(DbStrings.TABLE_Name_ID_Age_Sex, allColoumnns_Accelerator,null,null,null,null,null);

        try {
            cursor = database.rawQuery("select * from (select * from Name_ID_Age_Sex order by _id ASC limit 10) order by _id DESC", null);
        } catch (Exception ex){

        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            AccelerometerEntry obj = new AccelerometerEntry(Timestamp.valueOf(cursor.getString(1)), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4));
            values.add(obj);
            cursor.moveToNext();
        }

        return values;
    }

    public void DeleteAllAccelerometerEntries(){
        try{
            database.execSQL("delete from "+ DbStrings.TABLE_Name_ID_Age_Sex);
        }
        catch (Exception ex){

        }
    }
}
