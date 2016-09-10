package com.example.vadik.alfatest.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardActivity extends AppCompatActivity {

    TextView tvInviz;
    Cursor cursor;
    SQLiteDatabase db;
    DBHelper dbHelper;
    String[] selectionArgs = null;
    Map<String, Object> m;

    final String ATTRIBUTE_ENG_TRANSLATE = "engl";
    final String  ATTRIBUTE_RUS_TRANSLATE= "russ";

   // final String ATTRIBUTE_CATEGORY = "category_att";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        tvInviz = (TextView)findViewById(R.id.textView3);


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        Intent intent1 = getIntent();
        selectionArgs = intent1.getStringArrayExtra("selection_args");
        String [] forQuery  = new String[] {"_id","col_rus","col_eng"};
        cursor = db.query("my_table",forQuery,"col_category =?",selectionArgs,null,null,null);
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

        String[] from = new String[]{ATTRIBUTE_RUS_TRANSLATE,ATTRIBUTE_ENG_TRANSLATE} ;
        int[] to = new int[]{R.id.helloText,R.id.textView3};

         final SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.item,from,to);
        flingContainer.setAdapter(simpleAdapter);

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {


                // Toast.makeText(CardActivity.this, tvInviz.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                data.remove(0);

                simpleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                Toast.makeText(CardActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(CardActivity.this, "Right!", Toast.LENGTH_SHORT).show();
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
            public void onItemClicked(int itemPosition, Object dataObject){
              String str = String.valueOf(data.get(itemPosition).get(ATTRIBUTE_ENG_TRANSLATE));
                 Toast.makeText(CardActivity.this, str,Toast.LENGTH_LONG).show();
            }
        });


    }
}