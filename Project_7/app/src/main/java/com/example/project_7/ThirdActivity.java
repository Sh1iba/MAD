package com.example.project_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView text;
    Bundle arg;
    String getText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        text = findViewById(R.id.textView2);
        arg = getIntent().getExtras();
        if (arg != null) {
            getText = arg.get("key").toString();
            text.setText(getText);
        }
    }
}