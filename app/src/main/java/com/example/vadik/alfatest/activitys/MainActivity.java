package com.example.vadik.alfatest.activitys;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.SupportClasses.DataItem;
import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.SupportClasses.RecyclerAdapter;
import com.example.vadik.alfatest.dialogs.DialogAddCategory;
import com.example.vadik.alfatest.dialogs.DialogAddWord;
import com.example.vadik.alfatest.dialogs.DialogDeleteWord;
import com.example.vadik.alfatest.dialogs.RemoveCategoryDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements DialogAddCategory.AddCategoryInterface,DialogAddWord.AddWordInterface,RemoveCategoryDialog.RemoveCategotyInterface,DialogDeleteWord.DeleteWordInterface {

    RelativeLayout relativeLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter2;
    static boolean switchState;

    ArrayList<DataItem> newArrayCreating;
    SQLiteDatabase db;
    Cursor cursor;
    DBHelper dbHelper;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3709730817967165~3626710133");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutMain);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean("firstRun", true)) {
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.COL_CATEGORY,"Example");
            cv.put(DBHelper.COL_RUS, "Native");
            cv.put(DBHelper.COL_ENG,"Studied");
            db.insert("my_table",null,cv);
            prefs.edit().putBoolean("firstRun", false).commit();
        }
        onUpdateMenu();

        }


    private void onUpdateMenu(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerAdapter2 = new RecyclerAdapter(newArrayCreating,this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter2);
        newArrayCreating = new ArrayList<>();
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        // cursor = db.query("my_table", new String[]{"col_category"},null,null,null,null,null,null);
        Cursor cursor = db.rawQuery("select distinct col_category from my_table ", null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            DataItem dataItem = new DataItem();

            dataItem.setCategory_name(cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY)));
            newArrayCreating.add(dataItem);
            cursor.moveToNext();
        }
        cursor.close();
        recyclerAdapter2 = new RecyclerAdapter(newArrayCreating,this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter2);
    }

//    private  void createMenu() {
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        newArrayCreating = new ArrayList<>();
//        dbHelper = new DBHelper(this);
//        db = dbHelper.getWritableDatabase();
//        cursor = db.query("my_table", new String[]{"col_category"},null,null,null,null,null,null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
//            DataItem dataItem = new DataItem();
//            dataItem.setCategory_name(cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY)));
//            newArrayCreating.add(dataItem);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        recyclerAdapter2 = new RecyclerAdapter(newArrayCreating,this);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(recyclerAdapter2);
//    }

//    public  ArrayList<DataItem> getMenuCreatingArray (){
//        ArrayList<DataItem> newArrayCreating = new ArrayList<>();
//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        db.insert(DBHelper.TABLE_NAME,null,contentValues);
//        Cursor cursor = db.query("my_table", new String[]{"col_category"},null,null,null,null,null,null);
//        cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//                DataItem menuItem = new DataItem();
//                menuItem.setCategory_name(cursor.getString(cursor.getColumnIndex(DBHelper.COL_CATEGORY)));
//                newArrayCreating.add(menuItem);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        return newArrayCreating;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
         MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
      final android.view.MenuItem switchItem = menu.findItem(R.id.myswitch);
        final SwitchCompat mySwitch = (SwitchCompat)switchItem.getActionView();
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mySwitch.isChecked()){
                    switchState = true;
                    Toast.makeText(MainActivity.this, "Learning mode changed", Toast.LENGTH_SHORT).show();
                }else {
                    switchState = false;
                    Toast.makeText(MainActivity.this, "Learning mode changed", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int option_id = item.getItemId();
        if (option_id == R.id.add_word) {
            DialogAddWord dialogAddWord = new DialogAddWord();
            dialogAddWord.show(fragmentManager,"DAW");
        }

        if (option_id == R.id.delete_word) {

            DialogDeleteWord dialogDeleteWord = new DialogDeleteWord();
            dialogDeleteWord.show(fragmentManager,"DDW");

        }
            if (option_id == R.id.add_category) {
                DialogAddCategory addCategory = new DialogAddCategory();
                addCategory.show(fragmentManager,"DAC");
        }

        if (option_id == R.id.remove_category) {

            RemoveCategoryDialog dialog = new RemoveCategoryDialog();
            dialog.show(fragmentManager,"DDC");

        }


            return super.onOptionsItemSelected(item);
    }

    @Override
    public void addCategory(String value) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_CATEGORY,value);
        db.insert(DBHelper.TABLE_NAME,null,cv);
        onUpdateMenu();

        recyclerAdapter2.notifyDataSetChanged();


}

    @Override
    public void removeCategory(String category_name) {
        DBHelper dbHelper1 = new DBHelper(this);
        SQLiteDatabase database = dbHelper1.getWritableDatabase();
        database.delete("my_table", "col_category = ?", new String[]{category_name});
        onUpdateMenu();


    }

    @Override
    public void addWordMethod(String native_lang, String foreign_lang, String Category_name) {
        ContentValues contentValues = new ContentValues();
        DBHelper dbHelper1 = new DBHelper(this);
        SQLiteDatabase database = dbHelper1.getWritableDatabase();
        //SQLiteDatabase database = DBHelper.getInstance(getApplicationContext()).getWritableDatabase();
        contentValues.put(DBHelper.COL_CATEGORY,Category_name);
        contentValues.put(DBHelper.COL_RUS,native_lang);
        contentValues.put(DBHelper.COL_ENG,foreign_lang);
        database.insert(DBHelper.TABLE_NAME,null,contentValues);
    }


    @Override
    public void deleteWord(String wordToDelete) {
        SQLiteDatabase database = DBHelper.getInstance(getApplicationContext()).getWritableDatabase();
        database.delete(DBHelper.TABLE_NAME,DBHelper.COL_RUS+ "= ?",new String[]{wordToDelete});

    }



}


