package com.example.aravind.graphapplication.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aravind on 3/7/2016.
 */
public class SQLiteHelperClass extends SQLiteOpenHelper {

    private static SQLiteHelperClass mInstance = null;

    public static SQLiteHelperClass getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new SQLiteHelperClass(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private SQLiteHelperClass(Context context) {
        super(context, DbStrings.DATABASE_NAME, null, DbStrings.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        DbStrings dbs = new DbStrings();
        db.execSQL(dbs.CREATE_TABLE_Name_ID_Age_Sex_);
        try {
            db.execSQL(dbs.CREATE_TRIGGER_FOR_10_ENTRIES);
        }
        catch (Exception ex){
            Log.v("SQL error",ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
