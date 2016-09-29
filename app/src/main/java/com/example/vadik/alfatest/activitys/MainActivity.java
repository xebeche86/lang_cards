package com.example.vadik.alfatest.activitys;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.SupportClasses.MenuItem;
import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.SupportClasses.RecyclerAdapter;
import com.example.vadik.alfatest.dialogs.DialogAddCategory;
import com.example.vadik.alfatest.dialogs.DialogAddWord;
import com.example.vadik.alfatest.dialogs.DialogDeleteWord;
import com.example.vadik.alfatest.dialogs.RemoveCategoryDialog;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


//имплементирую интерфейсы , для обработки данных в диалогах
public class MainActivity extends AppCompatActivity implements DialogAddCategory.AddCategoryInterface,DialogAddWord.AddWordInterface,RemoveCategoryDialog.RemoveCategotyInterface,DialogDeleteWord.DeleteWordInterface {

    RelativeLayout relativeLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter2;
    ArrayList<MenuItem> arrayList;
    static boolean switchState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutMain);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        arrayList= new ArrayList<>();
        arrayList = MenuItem.getCreateArrayListForRecycler();
        recyclerAdapter2 = new RecyclerAdapter(arrayList,this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
      final android.view.MenuItem switchItem = menu.findItem(R.id.myswitch);
        final SwitchCompat mySwitch = (SwitchCompat)switchItem.getActionView();
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mySwitch.isChecked()){
                    switchState = true;
                }else {
                    switchState = false;
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
        MenuItem menuItem = new MenuItem(R.drawable.star,value);
       MenuItem.arrayListRecycler.add(menuItem);
       // menuItem.addItem(menuItem);
        recyclerAdapter2.notifyDataSetChanged();

    }

    @Override
    public void removeCategory(String category_name) {

        for(int i=0; i<arrayList.size();i++){
            MenuItem m = arrayList.get(i);
            if(m.getCategory_name().equals(category_name)){
                arrayList.remove(m);
            }
        }
        recyclerAdapter2.notifyDataSetChanged();

    }

    @Override
    public void addWordMethod(String native_lang, String foreign_lang, String Category_name) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = DBHelper.getInstance(getApplicationContext()).getWritableDatabase();

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



