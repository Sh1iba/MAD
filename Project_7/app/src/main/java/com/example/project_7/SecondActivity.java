package com.example.project_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private int hour,minute,year,month,day;
    Intent intent1;
    String edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent1 = new Intent(this,ThirdActivity.class);
    }

    public void Alertfunc (View view){
    // Создание строителя диалоговых окон
    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);

    // Установка заголовка и сообщения диалогового окна
    builder.setTitle("Подтверждение");
    builder.setIcon(android.R.drawable.ic_dialog_alert);
        EditText text = findViewById(R.id.editText);
        edittext = text.getText().toString();
        builder.setMessage("Вы уверены, что хотите ввести " + edittext +" ?");
    // Установка кнопки "Да" и ее обработчика
    builder.setPositiveButton("Да", new
            DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent1.putExtra("key",edittext);
                    startActivity(intent1);
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

    public void Timefunc (View view){
        // Создание и отображение TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Обработка выбранного времени
                // Пример: установка времени в TextView
                TextView textView = findViewById(R.id.text_for_time);
                textView.setText(hourOfDay + ":" +minute);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                        FirstFragment.class, null).commit();
                    }
                },
                hour, minute, true); // Использование 24-часового формата
        timePickerDialog.show();


    }

    public void Datefunc (View view){
        // Создание обработчика выбора даты
        DatePickerDialog.OnDateSetListener dateSetListener;
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                TextView textView = findViewById(R.id.text_for_data);
                textView.setText(dayOfMonth + "." + month + "." + year);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                        SecondFragment.class, null).commit();
            }
        };
        // Создание и отображение DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SecondActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();

    }

    public void Customfunc (View view){
        // Создание диалога
        Dialog dialog = new Dialog(SecondActivity.this);
        // Установка макета для диалогового окна
        dialog.setContentView(R.layout.custom_dialog);
        // Настройка элементов в макете
        Button button1 = dialog.findViewById(R.id.button1);
        Button button = dialog.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtview = findViewById(R.id.textView4);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                        ThirdFragment.class, null).commit();
                txtview.setText("Ты нажал на эту кнопку, теперь ты гений,миллиардер,плейбой,филинтроп");
                Toast.makeText(getApplicationContext(), "Красавчик", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtview = findViewById(R.id.textView4);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                        FourthFragment.class, null).commit();
                txtview.setText("Ты нажал на эту кнопку, теперь ты Александр Невский");
                Toast.makeText(getApplicationContext(), "Лев", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        // Отображение диалогового окна
        dialog.show();
    }


}