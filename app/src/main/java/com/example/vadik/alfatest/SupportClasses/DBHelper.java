package com.example.vadik.alfatest.SupportClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "my_table";
    public static final String COL_ID = "_id";





    public static final String COL_CATEGORY = "col_category";
    public static final String COL_ENG = "col_eng";
    public static final String COL_RUS = "col_rus";

    public static DBHelper mInstance = null;

    public static DBHelper getInstance(Context activityContext){
        if (mInstance == null) {
            mInstance = new DBHelper (activityContext.getApplicationContext());
        }

        return mInstance;
    }

  //  private DBHelper (Context applicationContext) {
   //     super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    //}

    public static final String SQL_SCRIPT = " CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_CATEGORY + " TEXT , " + COL_ENG + " TEXT , " + COL_RUS + " TEXT );";


  public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}