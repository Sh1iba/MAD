package com.example.project_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView Texttest = findViewById(R.id.tV2);
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            String T = arg.get("T").toString();
            String T2 = arg.get("T2").toString();
            String T3 = arg.get("T3").toString();
            String T4 = arg.get("T4").toString();
            Texttest.setText("Name: "+ T + "\nAge: " + T2 + "\nGroup: " + T3 + "\nGrade: " + T4 );
        }


    }
}