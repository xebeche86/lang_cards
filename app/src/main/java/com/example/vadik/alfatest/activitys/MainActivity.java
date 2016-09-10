package com.example.vadik.alfatest.activitys;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.vadik.alfatest.SupportClasses.MenuItem;
import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.SupportClasses.RecyclerAdapter;
import com.example.vadik.alfatest.dialogs.DialogAddCategory;
import com.example.vadik.alfatest.dialogs.RemoveCategoryDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogAddCategory.MyInterface{


    RelativeLayout  relativeLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter2;
    ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
    String[] _category = {"society", "science", "technics"};
    int[] img_res = {R.drawable.man_black, R.drawable.category_add, R.drawable.televisor};

    List<String> list  = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayoutMain);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
         recyclerAdapter2 = new RecyclerAdapter(arrayList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter2);


        int i = 0;
        for (int img : img_res) {
            MenuItem menuItem = new MenuItem(img, _category[i]);
            arrayList.add(menuItem);
            i++;
            recyclerAdapter2.notifyDataSetChanged();
        }
    }

    public ArrayList addItem (int image,String name){
        MenuItem menuItem = new MenuItem(image,name);
        arrayList.add(menuItem);
        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int option_id = item.getItemId();
        if (option_id == R.id.add_word) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);

        }
        if (option_id == R.id.delete_word) {

        }
            if (option_id == R.id.add_category) {
                DialogAddCategory dialog = new DialogAddCategory();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                dialog.show(fragmentManager,"dfdf");

        }

        if (option_id == R.id.remove_category) {

            RemoveCategoryDialog dialog = new RemoveCategoryDialog();
            FragmentManager manager = getSupportFragmentManager();
            dialog.show(manager,"hsjdh");



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCkickInterface(String value) {
        MenuItem menuItem = new MenuItem(R.drawable.man_black,value);
        arrayList.add(menuItem);
        recyclerAdapter2.notifyDataSetChanged();


    }

    public List getCategoryList() {

        for(int i=0; i<arrayList.size();i++){
            MenuItem m = arrayList.get(i);
            list.add(m.getCategory_name());
        }
        list.add("ff");
        list.add("fff");
        return list;
    }
}



/* for(int i=0; i<arrayList.size();i++){
               MenuItem m = arrayList.get(i);
                if(m.getCategory_name().equals("jjj")){
                    arrayList.remove(m);
                    }
                    }
                    */


