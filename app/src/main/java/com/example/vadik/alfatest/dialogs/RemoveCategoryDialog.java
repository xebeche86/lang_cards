package com.example.vadik.alfatest.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vadik.alfatest.activitys.MainActivity;
import com.example.vadik.alfatest.R;

import java.util.List;


public class RemoveCategoryDialog extends DialogFragment {
    public RemoveCategoryDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogdelete, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
        MainActivity activity = new MainActivity();
        List<String> stringList = activity.getCategoryList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        return view;
    }
}
