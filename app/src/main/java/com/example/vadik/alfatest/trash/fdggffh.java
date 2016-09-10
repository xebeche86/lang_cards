package com.example.vadik.alfatest.trash;


public class fdggffh {

    /*

     *import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;





 MyDialog dialog = new MyDialog();
              android.app.FragmentManager fragmentManager = getFragmentManager();
                dialog.show(fragmentManager,"dsd");


public class MyDialog extends DialogFragment {
    EditText editText;

    MyInterface anInterface;

    public interface MyInterface{
        public void onCkickInterface(String value);
    }

   public MyDialog(){
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        Button btn = (Button)view.findViewById(R.id.btnCustomDialog);
        editText = (EditText)view.findViewById(R.id.etCustomDialog);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();
                anInterface.onCkickInterface(value);
                dismiss();

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            anInterface = (MyInterface) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }

    }





    * import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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





import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView recyclerView;
    Button button, btnRemove;
    RecyclerAdapter recyclerAdapter2;
    String[] _category = {"society", "science", "technics"};
    int[] img_res = {R.drawable.man_black, R.drawable.star, R.drawable.televisor};
    ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapter2 = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter2);


        button = (Button) findViewById(R.id.buttonCallAddAct);
        button.setOnClickListener(this);

        btnRemove = (Button) findViewById(R.id.btnremove);
        btnRemove.setOnClickListener(this);


        int i = 0;
        for (int img : img_res) {
            MenuItem menuItem = new MenuItem(img, _category[i]);
            arrayList.add(menuItem);
            i++;
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonCallAddAct:
                String str = "office";
                MenuItem menuItem = new MenuItem(R.drawable.man_black, str);
                arrayList.add(menuItem);
                recyclerAdapter2.notifyDataSetChanged();

                break;
            case R.id.btnremove:
                for (MenuItem m : arrayList) {
                    if (m.getCategory_name().equals("office")) {
                        arrayList.remove(m);
                        recyclerAdapter2.notifyDataSetChanged();
                        break;



                    }

                }
        }
    }
}

/**

 }

 */



}
