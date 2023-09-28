package com.example.recyclerviewdialogdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Button search;
    private EditText enter_name;
    private Dialog dialog;
    private ArrayList<String> fuzzyMatching;
    private DialogListAdapter dialogListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        enter_name = findViewById(R.id.enter_name);

        fuzzyMatching = new ArrayList<>();
        Collections.addAll(fuzzyMatching,getResources().getStringArray(R.array.fruits_name));
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        recyclerView = dialog.findViewById(R.id.recyclerView);

        setEditText();

        search.setOnClickListener(view -> setDialog(fuzzyMatching));

    }

    private void setDialog(ArrayList<String> filterData) {
        dialogListAdapter = new DialogListAdapter(filterData,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(dialogListAdapter);
        dialog.show();
    }

    private void setEditText() {
        enter_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString().toLowerCase();
                fuzzyMatching.clear();
                for (String data : getResources().getStringArray(R.array.fruits_name)){
                    if (data.toLowerCase().contains(filterText)){
                        fuzzyMatching.add(data);
                    }
                }
            }
        });
    }
    public void receiveData(String data){
        enter_name.setText(data);
        dialog.dismiss();
    }
}