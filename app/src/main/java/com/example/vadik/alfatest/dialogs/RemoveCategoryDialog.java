package com.example.vadik.alfatest.dialogs;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.vadik.alfatest.SupportClasses.DataItem;
import com.example.vadik.alfatest.R;

import java.util.ArrayList;


public class RemoveCategoryDialog extends DialogFragment {

    DialogAddWord.AddWordInterface addWordInterface;
    ArrayList<String> arrayList;


    RemoveCategotyInterface removeCategotyInterface;

    public RemoveCategoryDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.final_delete_category, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spDeleteCategory);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_item, arrayList);
      spinner.setAdapter(arrayAdapter);
        Button btnRemove = (Button) view.findViewById(R.id.btnDeleteCategory);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category_name = spinner.getSelectedItem().toString();
                removeCategotyInterface.removeCategory(category_name);
                dismiss();
            }
        });
        Button btnCancel = (Button)view.findViewById(R.id.btnCancelDeleteCategory);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DataItem dataItem = new DataItem(getContext());
        arrayList = dataItem.getSpinnerStrings();
        try {
            Activity activity = (Activity) context;
            removeCategotyInterface = (RemoveCategotyInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement SellFragmentListener");
        }
    }

    public interface RemoveCategotyInterface {
        void removeCategory(String category_name);
    }

    @Override
    public void onStart() {
        super.onStart();

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        int dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        arrayList.clear();
    }

}


