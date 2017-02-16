package com.example.vadik.alfatest.SupportClasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.activitys.CardActivity;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<DataItem> arrayList;
    Context context;

    public RecyclerAdapter(ArrayList<DataItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.correct,parent,false),context,arrayList) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataItem dataItem = arrayList.get(position);
        holder.textView.setText(dataItem.getCategory_name());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        Context context;
        ArrayList<DataItem> arrayList;

        public ViewHolder(View itemView,Context context, ArrayList<DataItem> arrayList) {
            super(itemView);
            this.context = context;
            this.arrayList = arrayList;
            itemView.setOnClickListener(this);
            textView = (TextView)itemView.findViewById(R.id.tvrecycler);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            DataItem dataItem = this.arrayList.get(position);
            Intent intent = new Intent(context, CardActivity.class);
            intent.putExtra("Category_name", dataItem.getCategory_name());
            this.context.startActivity(intent);


        }
    }
}


//    private ArrayList<DataItem> arrayList = new ArrayList<DataItem>();
//    Context context;
//
//    public RecyclerAdapter(ArrayList<DataItem> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//
//    }
//
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.correct,parent,false),context,arrayList) ;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        DataItem menuItem = arrayList.get(position);
//        holder.imageView.setImageResource(menuItem.getImg_res());
//        holder.textView.setText(menuItem.getCategory_name());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView textView;
//        ImageView imageView;
//       Context context;
//        ArrayList<DataItem> arrayList = new ArrayList<DataItem>();
//        public ViewHolder(View itemView,Context context, ArrayList<DataItem> arrayList) {
//            super(itemView);
//           this.context = context;
//           this.arrayList = arrayList;
//            itemView.setOnClickListener(this);
//            textView = (TextView)itemView.findViewById(R.id.tvrecycler);
//           imageView = (ImageView)itemView.findViewById(R.id.imgrecyc);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//            int position = getAdapterPosition();
//            DataItem menuItem = this.arrayList.get(position);
//            Intent intent = new Intent(context, CardActivity.class);
//            intent.putExtra("Category_name",menuItem.getCategory_name());
//            this.context.startActivity(intent);
//
//
//        }
//    }
//}
