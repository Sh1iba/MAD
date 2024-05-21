package com.example.project_2;


import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    public void onNextActivity2(View View) {
        // получаем ссылки на виджеты по их идентификаторам
        EditText Ttext = findViewById(R.id.T);
        EditText T2text = findViewById(R.id.T2);
        EditText T3text = findViewById(R.id.T3);
        EditText T4text = findViewById(R.id.T4);
        //преобразуем полученную информацию в нужный формат
        String T = Ttext.getText().toString();
        String T2 = T2text.getText().toString();
        String T3 = T3text.getText().toString();
        String T4 = T4text.getText().toString();
        //передаем данные в другую активность
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("T", T);
        intent.putExtra("T2", T2);
        intent.putExtra("T3", T3);
        intent.putExtra("T4", T4);
        startActivity(intent);
    }

    protected void onNextActivity(View View) {
        // получаем ссылки на виджеты по их идентификаторам
        EditText Ttext = findViewById(R.id.T);
        EditText T2text = findViewById(R.id.T2);
        EditText T3text = findViewById(R.id.T3);
        EditText T4text = findViewById(R.id.T4);
        //преобразуем полученную информацию в нужный формат
        String T = Ttext.getText().toString();
        String T2 = T2text.getText().toString();
        String T3 = T3text.getText().toString();
        String T4 = T4text.getText().toString();
        //передаем данные в другую активность
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("T", T);
        intent.putExtra("T2", T2);
        intent.putExtra("T3", T3);
        intent.putExtra("T4", T4);
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
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}