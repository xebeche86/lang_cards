package com.example.vadik.alfatest.SupportClasses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vadik.alfatest.R;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();

    public RecyclerAdapter(ArrayList<MenuItem> arrayList) {
        this.arrayList = arrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem menuItem = arrayList.get(position);
        holder.imageView.setImageResource(menuItem.getImg_res());
        holder.textView.setText(menuItem.getCategory_name());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tvrecycler);
            imageView = (ImageView)itemView.findViewById(R.id.imgrecyc);
        }
    }
}
