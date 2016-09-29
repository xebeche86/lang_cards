package com.example.vadik.alfatest.SupportClasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.activitys.CardActivity;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
    Context context;

    public RecyclerAdapter(ArrayList<MenuItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_new_item,parent,false),context,arrayList) ;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
       Context context;
        ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
        public ViewHolder(View itemView,Context context, ArrayList<MenuItem> arrayList) {
            super(itemView);
           this.context = context;
           this.arrayList = arrayList;
            itemView.setOnClickListener(this);
            textView = (TextView)itemView.findViewById(R.id.tvrecycler);
            imageView = (ImageView)itemView.findViewById(R.id.imgrecyc);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            MenuItem menuItem = this.arrayList.get(position);
            Intent intent = new Intent(context, CardActivity.class);
            intent.putExtra("Category_name",menuItem.getCategory_name());
            this.context.startActivity(intent);


        }
    }
}
