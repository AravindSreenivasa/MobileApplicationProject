package com.example.aravind.graphapplication.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aravind on 3/7/2016.
 */
public class SQLiteHelperClass extends SQLiteOpenHelper {

    public SQLiteHelperClass(Context context) {
        super(context, DbStrings.DATABASE_NAME, null, DbStrings.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        DbStrings dbs = new DbStrings();
        db.execSQL(dbs.CREATE_TABLE_Name_ID_Age_Sex_);
        /*try {
            db.execSQL(dbs.CREATE_TRIGGER_FOR_10_ENTRIES);
        }
        catch (Exception ex){
            Log.v("SQL error",ex.getMessage());
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}