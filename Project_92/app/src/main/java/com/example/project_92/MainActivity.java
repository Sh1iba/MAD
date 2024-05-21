package com.example.project_92;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView filetext;
    EditText file;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();
    }

    public void onRead(View view)
    {

            ActivityCompat.requestPermissions( this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE
                    }, 1
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager())
                {
                    ReadFileFromExternalStorage();
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }

        }



    public void ReadFileFromExternalStorage(){
        file = findViewById(R.id.file_name); // Получаем ссылку на xml поле для ввода названия файла
        filename = file.getText().toString();

        filetext = findViewById(R.id.fileText);

        File storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageDir, filename);

            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileContent = text.toString();
            // Используйте содержимое файла как необходимо
            if(!fileContent.isEmpty()){
                filetext.setText(fileContent);
            }




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