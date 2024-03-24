package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView messageText = findViewById(R.id.tV2);
        messageText.setTextColor(Color.BLUE);
        messageText.setTextSize(20);
        messageText.setPadding(132, 16, 16, 16);
        messageText.setShadowLayer(2, 2, 2, Color.GRAY);

        MyObject myObjectInput = (MyObject) getIntent().getSerializableExtra("myObject");
        if(myObjectInput!=null){
            messageText.setText("Name: " + myObjectInput.getName() + "\nAge: " + myObjectInput.getAge() +
                    "\nGroup: " + myObjectInput.getGroup() + "\nGrade: " + myObjectInput.getGrade()  );

        }


    }

}