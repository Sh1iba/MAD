package com.example.project_9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    TextView filetext;
    Context context;
    EditText file,contents;
    String filename, filecontents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SaveFile(View view) {
        context = view.getContext(); // Получаем контекст из переданного View

        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();

        contents = findViewById(R.id.contents); // Получаем ссылку на xml поле для ввода содержания файла
        filecontents = contents.getText().toString();

        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(filecontents.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Файл успешно сохранен", Toast.LENGTH_SHORT).show();

    }

    public void ReadFile (View view) {

        context = view.getContext(); // Получаем контекст из переданного View

        file = findViewById(R.id.file_name);//Получаем ссылку на xml поле для ввода названия файла || Название файла
        filename = file.getText().toString();

        filetext = findViewById(R.id.fileText);

        try (FileInputStream fis = context.openFileInput(filename)) {
            InputStreamReader inputStreamReader = new
                    InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new
                    BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String contents = stringBuilder.toString();
            if(!contents.isEmpty()){
                filetext.setText(contents);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RemoveFile (View view) {
        // Указываем имя файла, который хотим удалить
        file = findViewById(R.id.file_name);//Получаем ссылку на xml поле для ввода названия файла || Название файла
        filename = file.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Установка заголовка и сообщения диалогового окна
        builder.setTitle("Подтверждение");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Вы уверены, что хотите удалить файл " + filename +" ?");
        // Установка кнопки "Да" и ее обработчика
        builder.setPositiveButton("Да", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Получаем файловый объект для файла из внутреннего хранилища
                        File dir = getFilesDir();
                        File fileremove = new File(dir, filename);
                        // Удаляем файл
                        boolean deleted = fileremove.delete();
                        Toast.makeText(getApplicationContext(), "Файл успешно удален", Toast.LENGTH_SHORT).show();
                    }
                });
        // Установка кнопки "Отмена" и ее обработчика
        builder.setNegativeButton("Отмена", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Действие отменено", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        // Создание и отображение AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void AddFile(View view){
//Context – это объект, который предоставляет доступ к базовым функциям приложения:
// доступ к ресурсам, к файловой системе, вызов активности и т.д.
        context = view.getContext(); // Получаем контекст из переданного View
        file = findViewById(R.id.file_name);//Получаем ссылку на xml поле для ввода названия файла || Название файла
        filename = file.getText().toString();

        contents = findViewById(R.id.contents);//Получаем ссылку на xml поле для ввода содержания файла || Текст внутри файла
        filecontents = contents.getText().toString();

        //Открываем поток для записи. Если документ не создан, то он будетсоздан автоматически
        try (FileOutputStream fos = context.openFileOutput(filename,
                Context.MODE_APPEND)) {
//Записываем текст в файл, переведя его в массив байт
            fos.write(filecontents.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnNextActivity(View view){
        Intent intent = new Intent(this,Second_Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохраняем значение строковой переменной
        filetext = findViewById(R.id.fileText);
        String text = filetext.getText().toString();
        outState.putString("KEY_STATE", text);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Восстанавливаем сохраненное состояние
        String state = savedInstanceState.getString("KEY_STATE");
        // Используем сохраненное значение для восстановления состояния UI или других компонентов
        filetext = findViewById(R.id.fileText);
        filetext.setText(state);

    }



}