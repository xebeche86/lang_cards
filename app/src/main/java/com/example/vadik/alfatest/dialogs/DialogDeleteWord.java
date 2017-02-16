package com.example.vadik.alfatest.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vadik.alfatest.R;
import com.example.vadik.alfatest.SupportClasses.DBHelper;
import com.example.vadik.alfatest.SupportClasses.DataItem;

import java.util.ArrayList;

public class DialogDeleteWord extends DialogFragment{
    Spinner spCategoryDeleteWord,spWordsDeleteWord;
    ArrayAdapter<String> arrayAdapter4words;
    ArrayAdapter<String> adapter4SpinnerCtgrDelWord;
   // DataItem dataItem = new DataItem();
    ArrayList<String> arrayList4spinnerCategoryDeleteWord;
    Button btnDelete,btnCancel;
    public interface DeleteWordInterface {
        public void deleteWord(String wordToDelete);
    }

    DeleteWordInterface deleteWordInterfaceInstance;

    public DialogDeleteWord(){
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        arrayList4spinnerCategoryDeleteWord.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DataItem dataItem = new DataItem(context);
        arrayList4spinnerCategoryDeleteWord = dataItem.getSpinnerStrings();

        try {
            Activity activity = (Activity)context;
            deleteWordInterfaceInstance = (DeleteWordInterface)activity;
        } catch (ClassCastException e)
        {throw new ClassCastException(getActivity().toString()+"Must implement DeleteWordInterface"); }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.final_delete_word_spinners, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        spCategoryDeleteWord = (Spinner)view.findViewById(R.id.spCategoryDeleteWord);
        spWordsDeleteWord =  (Spinner)view.findViewById(R.id.spWordsDeleteWord);
        adapter4SpinnerCtgrDelWord = new ArrayAdapter<>(getContext(),R.layout.custom_spinner_item,arrayList4spinnerCategoryDeleteWord);
        spCategoryDeleteWord.setAdapter(adapter4SpinnerCtgrDelWord);
        spCategoryDeleteWord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getArrayWords();
                arrayAdapter4words.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCancel = (Button)view.findViewById(R.id.btnCancelWordDelete);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnDelete = (Button)view.findViewById(R.id.btnconfirmWordDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteWordInterfaceInstance.deleteWord(spWordsDeleteWord.getSelectedItem().toString());
                    getArrayWords();

                } catch (NullPointerException e) {
                    dismiss();
                }
                //dismiss();
            }
        });

        return view;
    }

    private void getArrayWords(){
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] forQuery = {spCategoryDeleteWord.getSelectedItem().toString()};
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select distinct col_rus FROM my_table WHERE col_rus is  not null and col_category =?" ,new String[]{spCategoryDeleteWord.getSelectedItem().toString()});
       // Cursor cursor = database.query(DBHelper.TABLE_NAME,new String[]{DBHelper.COL_RUS},"col_category = ?",forQuery,null,null,"col_rus ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_RUS)));
            cursor.moveToNext();
        }
        cursor.close();
        arrayAdapter4words = new ArrayAdapter<>(getContext(),R.layout.custom_spinner_item,arrayList);
        spWordsDeleteWord.setAdapter(arrayAdapter4words);
    }

    @Override
    public void onStart() {
        super.onStart();

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        int dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

    }

}

