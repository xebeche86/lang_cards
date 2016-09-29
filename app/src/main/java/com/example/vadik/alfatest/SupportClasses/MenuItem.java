package com.example.vadik.alfatest.SupportClasses;


import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.example.vadik.alfatest.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
//класс данных для RecyclerView
public class MenuItem {

    int img_res;
    String category_name;
    public static ArrayList<String> spinnerList = new ArrayList<>();
    public static ArrayList<MenuItem> arrayListRecycler= new ArrayList<>();


     static String[] category_names = new String[] {"Society","Food","Nature","Party","Science"};
     static int [] drawables = new int[] {R.drawable.man_black,R.drawable.food2,R.drawable.tree,R.drawable.party2,R.drawable.science};


    public static   ArrayList<MenuItem> getCreateArrayListForRecycler(){
        if(arrayListRecycler.isEmpty()){
        int i = 0;
        for (String str:category_names){
            MenuItem m = new MenuItem(drawables[i],str);
            arrayListRecycler.add(m);
            i++;
        }
        }
        return arrayListRecycler;
    }

    public MenuItem(){

    }

    public MenuItem(int img_res, String category_name){
        this.setImg_res(img_res);
        this.setCategory_name(category_name);
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public int getImg_res() {
        return img_res;
    }

    public void addItem(MenuItem menuItem){

        arrayListRecycler.add(menuItem);

    }


     public static  void  createArray(){
        int i = 0;
        for (String str:category_names){
            MenuItem m = new MenuItem(drawables[i],str);
            arrayListRecycler.add(m);
            i++;
        }

    };

    public static ArrayList<String> getSpinnerStrings() {
     //if (spinnerList.isEmpty()) {
         for (int i = 0; i < arrayListRecycler.size(); i++) {
             MenuItem m = arrayListRecycler.get(i);
             String str = m.getCategory_name();
             spinnerList.add(str);
         }

       return spinnerList;
    }

    }


/*
    String[] _category = {"society", "science", "technics"};
    int[] img_rest = {R.drawable.man_black, R.drawable.category_add, R.drawable.televisor};
    ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
* */