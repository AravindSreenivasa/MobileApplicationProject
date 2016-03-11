package com.example.aravind.graphapplication.databasehelper;

/**
 * Created by Aravind on 3/7/2016.
 */
public class DbStrings {

    public static final String DATABASE_NAME = "GraphApplication.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_Name_ID_Age_Sex = "Name_ID_Age_Sex";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME_STAMP = "timeStamp";
    public static final String COLUMN_X = "x_values";
    public static final String COLUMN_Y = "y_values";
    public static final String COLUMN_Z = "z_values";

    public static final String CREATE_TABLE_Name_ID_Age_Sex_ = "create table "
            + TABLE_Name_ID_Age_Sex + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TIME_STAMP
            + " text not null, " + COLUMN_X
            + " text, " + COLUMN_Y
            + " text, " + COLUMN_Z
            + " text);";

    public static final String CREATE_TRIGGER_FOR_10_ENTRIES = "CREATE TRIGGER delete_till_10 INSERT ON "+TABLE_Name_ID_Age_Sex+" WHEN (select count(*) from \"+TABLE_Name_ID_Age_Sex+\")>10 " +
            "BEGIN" +
            "    DELETE FROM "+CREATE_TABLE_Name_ID_Age_Sex_+" where _id NOT IN (SELECT _id from "+CREATE_TABLE_Name_ID_Age_Sex_+" ORDER BY insertion_date DESC LIMIT 10)"+
            "END;";
}
