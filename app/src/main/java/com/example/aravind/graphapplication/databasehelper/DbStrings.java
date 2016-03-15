package com.example.aravind.graphapplication.databasehelper;

/**
 * Created by Aravind on 3/7/2016.
 */
public class DbStrings {

    public static String DATABASE_NAME = "GraphApplication.db";
    public static final int DATABASE_VERSION = 1;

    public static String TABLE_Name_ID_Age_Sex = "Name_ID_Age_Sex";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME_STAMP = "timeStamp";
    public static final String COLUMN_X = "x_values";
    public static final String COLUMN_Y = "y_values";
    public static final String COLUMN_Z = "z_values";

    public static final String CREATE_TABLE_Name_ID_Age_Sex_ = "create table "
            + TABLE_Name_ID_Age_Sex + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TIME_STAMP
            + " text, " + COLUMN_X
            + " real, " + COLUMN_Y
            + " real, " + COLUMN_Z
            + " real);";

    public static final String CREATE_TRIGGER_FOR_10_ENTRIES = "CREATE TRIGGER delete_till_10 INSERT ON "+TABLE_Name_ID_Age_Sex+" WHEN (select count(*) from "+TABLE_Name_ID_Age_Sex+")>10 " +
            "BEGIN" +
            "    DELETE FROM "+TABLE_Name_ID_Age_Sex+" where _id NOT IN (SELECT _id from "+TABLE_Name_ID_Age_Sex+" ORDER BY "+COLUMN_TIME_STAMP+" DESC LIMIT 10);"+
            "END;";

    public static void setTableName(String tableName){
        TABLE_Name_ID_Age_Sex = tableName;
    }

    public static void setDatabaseName(String databaseName){
        DATABASE_NAME = databaseName;
    }
}
