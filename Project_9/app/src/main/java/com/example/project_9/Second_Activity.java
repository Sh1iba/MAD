package com.example.project_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Second_Activity extends AppCompatActivity {
    TextView filetext;
    Context context;
    EditText file,contents;
    String filename, filecontents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void SavingToExternalStorage(View view){
        // Пример создания текстового файла в публичной директории "Documents"

        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();

        contents = findViewById(R.id.contents); // Получаем ссылку на xml поле для ввода содержания файла
        filecontents = contents.getText().toString();
        File storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!storageDir.exists()) {
            storageDir.mkdirs(); // Создаем директорию, если она не существует
        }
        File file = new File(storageDir, filename);
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile(); // Создаем файл, если он не существует
                if (created) {
                    // Записываем данные в файл
                    FileWriter writer = new FileWriter(file);
                    writer.append(filecontents);
                    writer.flush();
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Файл успешно сохранен", Toast.LENGTH_SHORT).show();

    }

    public void RemovingFromExternalStorage(View view){
        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();

        File storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageDir, filename);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                // Файл успешно удален
                Toast.makeText(getApplicationContext(), "Файл успешно удален", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Не удалось удалить файл", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void AddFile(View view){
        // Пример создания текстового файла в публичной директории "Documents"

        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();

        contents = findViewById(R.id.contents); // Получаем ссылку на xml поле для ввода содержания файла
        filecontents = contents.getText().toString();
        File storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!storageDir.exists()) {
            storageDir.mkdirs(); // Создаем директорию, если она не существует
        }
        File file = new File(storageDir, filename);
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile(); // Создаем файл, если он не существует
                if (created) {
                    // Записываем данные в файл
                    FileWriter writer = new FileWriter(file,true);
                    writer.append(filecontents);
                    writer.flush();
                    writer.close();
                }

            }
            else{
                // Записываем данные в файл
                FileWriter writer = new FileWriter(file,true);
                writer.append(filecontents);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}