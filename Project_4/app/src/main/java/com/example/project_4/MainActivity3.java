package com.example.project_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void sendMessage(View view) {

        EditText dayEdit = findViewById(R.id.editTextDay);
        EditText timeEdit = findViewById(R.id.editTextTime);
        EditText commentEdit = findViewById(R.id.editTextComment);

        String day = dayEdit.getText().toString();
        String time = timeEdit.getText().toString();
        String comment = commentEdit.getText().toString();

        Intent intent = new Intent();

            intent.putExtra(MainActivity2.ACCESS_MESSAGE, "Data: " + day + " "+ time  +" '"+comment+"'");
            setResult(RESULT_OK, intent);
            finish();
            Toast.makeText(getApplicationContext(), "Сообщение передано", Toast.LENGTH_SHORT).show();


    }

}