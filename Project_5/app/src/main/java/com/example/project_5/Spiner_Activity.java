package com.example.project_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spiner_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner);

        Spinner spinner_name = findViewById(R.id.my_spinner);

        String[] items = { "Apple", "Banana", "Peach", "Lemon", "Blueberry",
                "Mango", "Strawberry"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_name.setAdapter(adapter);
    }
}