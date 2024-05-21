package com.example.project_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_10.database.DataBase;

public class MainActivity extends AppCompatActivity {
EditText editText;
TextView textView;
String username,fake;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.user_name);
        textView = findViewById(R.id.textView2);
        sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
    }

    public void Save(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        username = editText.getText().toString();
        if(username.isEmpty() || username.contains(" ")){
            Toast.makeText(getApplicationContext(), "Имя пользователя не может быть пустым или содержать пробелы",
                    Toast.LENGTH_SHORT).show();
        }
        else {
    // Сохранение строкового значения
        editor.putString("username", username);
    // Сохранение измененийа
        editor.apply();
        Toast.makeText(getApplicationContext(), "Имя пользователя успешно сохранено",
                Toast.LENGTH_SHORT).show();
        }

    }

    public void Get (View view){
        // Получение данных
        // Чтение строкового значения
        fake = editText.getText().toString();
        if(fake.equals(username)){
            username = sharedPreferences.getString("username", "defaultUsername");
            textView.setText("Имя пользователя: " + username);
        }
        else {
            Toast.makeText(getApplicationContext(), "Пользователя не существует",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void Remove(View view){
        // Удаление данных
        fake = editText.getText().toString();
        if(fake.equals(username)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // Удаление данных по ключу
            editor.remove("username");
            // Удаление всех данных
            editor.clear();
            // Применение изменений
            editor.apply();
            Toast.makeText(getApplicationContext(), "Имя пользователя успешно удалено",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Пользователя не существует",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void Next(View view){
        Intent intent = new Intent(this, DataBase.class);
        startActivity(intent);
    }
}