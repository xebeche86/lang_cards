package com.example.vadik.alfatest.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Использую эту либу https://github.com/Diolor/Swipecards

public class CardActivity extends AppCompatActivity {

    Cursor cursor;
    SQLiteDatabase db;
    Map<String, Object> m;
    TextView count_percent;
    final String ATTRIBUTE_ENG_TRANSLATE = "eng";
    final String  ATTRIBUTE_RUS_TRANSLATE= "rus";
    String[] from;

    static double correct = 0;
    static double incorrect = 0;




    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_cards_layout);
        Toast.makeText(this,"Swipe left if you know",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Swipe right if you dont know",Toast.LENGTH_SHORT).show();

        count_percent = (TextView) findViewById(R.id.count_percent);

        if (MainActivity.switchState == true){
            from = new String[] {ATTRIBUTE_RUS_TRANSLATE, ATTRIBUTE_ENG_TRANSLATE};
        } else {
            from = new String[] {ATTRIBUTE_ENG_TRANSLATE, ATTRIBUTE_RUS_TRANSLATE };
        }


        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        db = DBHelper.getInstance(getApplicationContext()).getReadableDatabase();

        Intent intent = getIntent();
        String string = intent.getStringExtra("Category_name");
        String[] selectionArgs = new String[]{string};
        String[] forQuery = new String[]{"_id", "col_rus", "col_eng"};
        cursor = db.query("my_table", forQuery, "col_category =?", selectionArgs, null, null, "col_rus ASC");
        final ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_RUS_TRANSLATE,cursor.getString(cursor.getColumnIndex(DBHelper.COL_RUS)));
            m.put(ATTRIBUTE_ENG_TRANSLATE,cursor.getString(cursor.getColumnIndex(DBHelper.COL_ENG)));
            data.add(m);
            cursor.moveToNext();
        }
        cursor.close();

        final int[] to = new int[]{R.id.helloText,R.id.textView3};
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.item,from,to);
        flingContainer.setAdapter(simpleAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                data.remove(0);
                simpleAdapter.notifyDataSetChanged();
            }

            public String count(){
                double count = (correct/(correct+incorrect))*100;
                String pattern = "##0.0";
                DecimalFormat decimalFormat = new DecimalFormat(pattern);
                String format = decimalFormat.format(count);
                return format;
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                CardActivity.correct++;
                count_percent.setText(count() + "% Correct");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                CardActivity.incorrect++;
                count_percent.setText(count() + "% Correct");

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

                simpleAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
            }

            @Override
            public void onScroll(float v) {

            }
        });


        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
               if(MainActivity.switchState ==false){
                   String str = String.valueOf(data.get(itemPosition).get(ATTRIBUTE_RUS_TRANSLATE));
                   Toast.makeText(CardActivity.this, str, Toast.LENGTH_LONG).show();
               } else {
                   String str = String.valueOf(data.get(itemPosition).get(ATTRIBUTE_ENG_TRANSLATE));
                   Toast.makeText(CardActivity.this, str, Toast.LENGTH_LONG).show();
               }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        correct=0;
        incorrect=0;

    }
}
