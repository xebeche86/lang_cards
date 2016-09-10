package com.example.vadik.alfatest.SupportClasses;


import com.example.vadik.alfatest.R;

import java.util.ArrayList;

public class MenuItem {


    String[] _category = {"society", "science", "technics"};
    int[] img_rest = {R.drawable.man_black, R.drawable.category_add, R.drawable.televisor};
    ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
    public MenuItem(){

    }


    public MenuItem(int img_res, String category_name){
        this.setImg_res(img_res);
        this.setCategory_name(category_name);
    }


    int img_res;
    String category_name;



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



    }