package com.example.vadik.alfatest.activitys;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etrussian,etenglish,etcategory;
    Button saveBtn;
    DBHelper dbHelper;
    Spinner spinner;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etrussian = (EditText)findViewById(R.id.editText);
        etenglish = (EditText)findViewById(R.id.editText2);
        etcategory = (EditText)findViewById(R.id.editText3);
        saveBtn = (Button)findViewById(R.id.btnSave);
        spinner = (Spinner)findViewById(R.id.spinner);

        String [] cat_name = getResources().getStringArray(R.array.Category_Name_Array);
        List<String> list_cat_name = Arrays.asList(cat_name);
        List<String> categoryArrayList = new ArrayList<>(list_cat_name);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categoryArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);











        saveBtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                ContentValues contentValues = new ContentValues();
                String rus = etrussian.getText().toString();
                String eng = etenglish.getText().toString();
                String cat = etcategory.getText().toString();
                SQLiteDatabase database = dbHelper.getWritableDatabase();


                contentValues.put(DBHelper.COL_CATEGORY,cat);
                contentValues.put(DBHelper.COL_RUS,rus);
                contentValues.put(DBHelper.COL_ENG,eng);
                database.insert(DBHelper.TABLE_NAME,null,contentValues);
                saveBtn.setBackgroundColor(Color.BLUE);
                break;


        }


    }
}