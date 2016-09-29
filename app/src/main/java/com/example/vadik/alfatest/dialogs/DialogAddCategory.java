package com.example.vadik.alfatest.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.vadik.alfatest.R;


public class DialogAddCategory extends DialogFragment {
    EditText editText;
    Button btnAdd,btnCancel;

    AddCategoryInterface addCategoryInterface;

    public interface AddCategoryInterface{
         void addCategory (String value);
    }

    public DialogAddCategory(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.final_add_category,container,false);
        editText = (EditText)view.findViewById(R.id.etAddCategory);
        btnCancel = (Button)view.findViewById(R.id.btnCancelAddCategory);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        btnAdd = (Button)view.findViewById(R.id.btnAddCategory) ;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();
                addCategoryInterface.addCategory(value);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        int dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Activity activity = (Activity)context;
            addCategoryInterface = (AddCategoryInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement SellFragmentListener");
        }
    }
}



/*
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
*/


