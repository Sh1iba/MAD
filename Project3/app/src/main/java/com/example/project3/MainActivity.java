package com.example.project3;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import static android.content.ContentValues.TAG;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void onNextActivity2(View View){
        // получаем ссылки на виджеты по их идентификаторам
        // получаем ссылки на виджеты по их идентификаторам
        EditText nameText = findViewById(R.id.T);
        EditText ageText= findViewById(R.id.T2);
        EditText groupText = findViewById(R.id.T3);
        EditText gradeText = findViewById(R.id.T4);


        String name = nameText.getText().toString();
        String Group = groupText.getText().toString();
        int age, Grade;

        if (!ageText.getText().toString().isEmpty() && !groupText.getText().toString().isEmpty()){
            age = Integer.parseInt(ageText.getText().toString());
            Grade = Integer.parseInt(gradeText.getText().toString());
        }
        else{
            age = 0;
            Grade = 0;
        }

        MyObject myObject = new MyObject(name, age,Group,Grade);
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("myObject", myObject);
        startActivity(intent);
    }
    protected void onNextActivity(View View){
        // получаем ссылки на виджеты по их идентификаторам
        EditText nameText = findViewById(R.id.T);
        EditText ageText = findViewById(R.id.T2);
        EditText groupText = findViewById(R.id.T3);
        EditText gradeText = findViewById(R.id.T4);


        String name = nameText.getText().toString();
        String Group = nameText.getText().toString();
        int age, Grade;

        if (!ageText.getText().toString().isEmpty() && !groupText.getText().toString().isEmpty()){
            age = Integer.parseInt(ageText.getText().toString());
            Grade = Integer.parseInt(ageText.getText().toString());
        }
        else{
            age = 0;
            Grade = 0;
        }

        MyObject myObject = new MyObject(name, age,Group,Grade);
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("myObject", myObject);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity(button);


            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}