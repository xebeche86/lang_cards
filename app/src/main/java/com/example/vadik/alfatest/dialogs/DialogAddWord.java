package com.example.vadik.alfatest.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.SupportClasses.MenuItem;

import java.util.ArrayList;

public class DialogAddWord extends android.support.v4.app.DialogFragment {
    EditText etNAtive,etForeign;
    Spinner spinner;
    Button btnSave,btnDone;
    AddWordInterface addWordInterface;
    ArrayList<String> arrayList4spinnerCategory = MenuItem.getSpinnerStrings();

    public  interface AddWordInterface {
         void addWordMethod (String native_lang, String foreign_lang, String Category_name);
    }

    public DialogAddWord() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.final_add_word, container, false);
       getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        etNAtive = (EditText)view.findViewById(R.id.etAddWordNative);
        etForeign = (EditText)view.findViewById(R.id.etAddWordForeign);
        spinner = (Spinner)view.findViewById(R.id.spAddWord);
        btnSave = (Button)view.findViewById(R.id.btnaddword);
        btnDone = (Button)view.findViewById(R.id.btnCancelAddWord);

       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.custom_spinner_item,arrayList4spinnerCategory);
        spinner.setAdapter(arrayAdapter);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String native_lang = etNAtive.getText().toString();
                String foreign_lang = etForeign.getText().toString();
                String category_name  = spinner.getSelectedItem().toString();
                addWordInterface.addWordMethod(native_lang,foreign_lang,category_name);
                etForeign.getText().clear();
                etNAtive.getText().clear();

            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
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
            addWordInterface = (AddWordInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement SellFragmentListener");
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        arrayList4spinnerCategory.clear();
    }
}





