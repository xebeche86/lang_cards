package com.example.vadik.alfatest.SupportClasses;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vadik.alfatest.activitys.MainActivity;

import java.util.ArrayList;

public class DataItem {
    Context mContext;
    String category_name;

    public DataItem(Context context){
        this.mContext = context ;
    }
    
    public  ArrayList<String> getSpinnerStrings() {
        ArrayList<String> arrayListForSpinner = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct col_category from my_table ", null);
        // Cursor cursor = db.query("my_table", new String[]{"col_category"}, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String forSpinner;
            forSpinner = cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY));
            arrayListForSpinner.add(forSpinner);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayListForSpinner;
    }

    public DataItem() {}

    public DataItem(String category_name){
        this.category_name = category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

//    public   ArrayList<DataItem> createMenu() {
//        DBHelper dbHelper = new DBHelper(mContext);
//        Cursor cursor;
//        SQLiteDatabase db;
//        db = dbHelper.getWritableDatabase();
//        ArrayList<DataItem> newArrayCreating = new ArrayList<>();
//        cursor = db.query("my_table", new String[]{"col_category"},null,null,null,null,null,null);
//              cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                DataItem dataItem = new DataItem();
//                dataItem.setCategory_name(cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY)));
//                newArrayCreating.add(dataItem);
//                cursor.moveToNext();
//            }
//            cursor.close();
//
//        return newArrayCreating;
//    }


//    public  ArrayList<DataItem> getMenuCreatingArray (){
//       ArrayList<DataItem> newArrayCreating = new ArrayList<>();
//        DBHelper dbHelper = new DBHelper(mContext);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBHelper.COL_CATEGORY, "check");
//        db.insert(DBHelper.TABLE_NAME,null,contentValues);
//       Cursor cursor = db.query("my_table", new String[]{"col_category"},null,null,null,null,null,null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            DataItem menuItem = new DataItem();
//            menuItem.setCategory_name(cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY)));
//            newArrayCreating.add(menuItem);
//        }
//        cursor.close();
//        return newArrayCreating;
//    }



}


