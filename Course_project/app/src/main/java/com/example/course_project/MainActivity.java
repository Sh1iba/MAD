package com.example.course_project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.course_project.Fragments.AboutTheProgramFragment;
import com.example.course_project.Fragments.MainFragment;
import com.example.course_project.Fragments.MoneyFragment;
import com.example.course_project.Fragments.PayFragment;
import com.example.course_project.Fragments.PayHistoryFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    private ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(MainActivity.this);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#103fa0"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.drawer_open,R.string.drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);

        }
        toggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Главная");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                MainFragment.class, null).commit();
        sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");

        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        MainFragment.class, null).commit();

            } else if (item.getItemId() == R.id.money) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        MoneyFragment.class, null).commit();

            } else if (item.getItemId() == R.id.info) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        AboutTheProgramFragment.class, null).commit();

            }
            else   {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        PayFragment.class, null).commit();

            }

            drawer.closeDrawer(GravityCompat.START); // Закрытие выдвижного меню после выбора раздела
            actionBar.setTitle(item.getTitle());
            return true;
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void gitClick(View view){

        Uri uri = Uri.parse("https://github.com/Sh1iba/MAD/tree/master/Course_project");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void tgClick(View view){

        Uri uri = Uri.parse("https://t.me/b0neIess");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void getWarm(View view){
        EditText editText = findViewById(R.id.warm_editText);
        String warm_str = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView textView = findViewById(R.id.warm_textView);

        float warmcof = (float) 243.16F;
        int kol = 0;
        float res;
        if(warm_str.isEmpty() || warm_str.contains(" ")){
            Toast.makeText(getApplicationContext(), "Строка не должна быть пустой или содержать пробел", Toast.LENGTH_SHORT).show();
        }
        if (!warm_str.isEmpty()) {
            try {
                kol = Integer.parseInt(warm_str);
                res = warmcof * kol;
                editor.putFloat("warm", res);
                editor.apply();
                textView.setText("Итого потрачено воды на: " + res+" рублей");
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Вводимое значение должно быть числом", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void ShowWarm(View view){
        float res = sharedPreferences.getFloat("warm", 0);
        TextView warm = findViewById(R.id.warm_textView);
        warm.setText("Итого потрачено воды на: " + res + " рублей");
    }
    public void payWarm(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        float balance = sharedPreferences.getFloat("balance", 0);
        float warm = sharedPreferences.getFloat("warm", 0);


        if(balance >= warm){
            // Установка заголовка и сообщения диалогового окна
            builder.setTitle("Подтверждение");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Вы уверены, что хотите оплатить счёт на " + warm +" рублей ?");
            // Установка кнопки "Да" и ее обработчика
            builder.setPositiveButton("Да", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            float balance = sharedPreferences.getFloat("balance", 0);
                            TextView warmview = findViewById(R.id.warm_textView);
                            float warm = sharedPreferences.getFloat("warm", 0);

                            balance -= warm;
                            editor.putFloat("balance", balance);
                            editor.apply();

                            warm = 0.0F;
                            editor.putFloat("warm",warm);
                            editor.apply();
                            warmview.setText("Итого потрачено воды на: " + warm + " рублей");
                            Toast.makeText(getApplicationContext(), "Счёт успешно оплачен", Toast.LENGTH_SHORT).show();
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

        } else {
            Toast.makeText(getApplicationContext(), "У вас недостаточно средств для оплаты", Toast.LENGTH_SHORT).show();
        }


    }


    public void getCold(View view){
        EditText editText = findViewById(R.id.cold_editText);
        String warm_str = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView textView = findViewById(R.id.cold_textView);

        float coldcof = (float) 50.93F;
        int kol = 0;
        float res;
        if(warm_str.isEmpty() || warm_str.contains(" ")){
            Toast.makeText(getApplicationContext(), "Строка не должна быть пустой или содержать пробел", Toast.LENGTH_SHORT).show();
        }
        if (!warm_str.isEmpty()) {
            try {
                kol = Integer.parseInt(warm_str);
                res = coldcof * kol;
                editor.putFloat("cold", res);
                editor.apply();
                textView.setText("Итого потрачено воды на: " + res+" рублей");
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Вводимое значение должно быть числом", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ShowCold(View view){
        float res = sharedPreferences.getFloat("cold", 0);
        TextView warm = findViewById(R.id.cold_textView);
        warm.setText("Итого потрачено воды на: " + res + " рублей");
    }
    public void payCold(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        float balance = sharedPreferences.getFloat("balance", 0);
        float cold = sharedPreferences.getFloat("cold", 0);


        if(balance >= cold){
            // Установка заголовка и сообщения диалогового окна
            builder.setTitle("Подтверждение");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Вы уверены, что хотите оплатить счёт на " + cold +" рублей ?");
            // Установка кнопки "Да" и ее обработчика
            builder.setPositiveButton("Да", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            float balance = sharedPreferences.getFloat("balance", 0);
                            TextView coldview = findViewById(R.id.cold_textView);
                            float cold = sharedPreferences.getFloat("cold", 0);

                            balance -= cold;
                            editor.putFloat("balance", balance);
                            editor.apply();

                            cold = 0.0F;
                            editor.putFloat("cold",cold);
                            editor.apply();
                            coldview.setText("Итого потрачено воды на: " + cold + " рублей");
                            Toast.makeText(getApplicationContext(), "Счёт успешно оплачен", Toast.LENGTH_SHORT).show();
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

        } else {
            Toast.makeText(getApplicationContext(), "У вас недостаточно средств для оплаты", Toast.LENGTH_SHORT).show();
        }


    }


    public void getGaz(View view){
        EditText editText = findViewById(R.id.gaz_editText);
        String warm_str = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView textView = findViewById(R.id.gaz_textView);

        float coldcof = (float) 7.46F;
        int kol = 0;
        float res;
        if(warm_str.isEmpty() || warm_str.contains(" ")){
            Toast.makeText(getApplicationContext(), "Строка не должна быть пустой или содержать пробел", Toast.LENGTH_SHORT).show();
        }
        if (!warm_str.isEmpty()) {
            try {
                kol = Integer.parseInt(warm_str);
                res = coldcof * kol;
                editor.putFloat("gaz", res);
                editor.apply();
                textView.setText("Итого потрачено газа на: " + res+" рублей");
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Вводимое значение должно быть числом", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ShowGaz(View view){
        float res = sharedPreferences.getFloat("gaz", 0);
        TextView warm = findViewById(R.id.gaz_textView);
        warm.setText("Итого потрачено газа на: " + res + " рублей");
    }
    public void payGaz(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        float balance = sharedPreferences.getFloat("balance", 0);
        float gaz = sharedPreferences.getFloat("gaz", 0);


        if(balance >= gaz){
            // Установка заголовка и сообщения диалогового окна
            builder.setTitle("Подтверждение");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Вы уверены, что хотите оплатить счёт на " + gaz +" рублей ?");
            // Установка кнопки "Да" и ее обработчика
            builder.setPositiveButton("Да", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            float balance = sharedPreferences.getFloat("balance", 0);
                            TextView gazview = findViewById(R.id.gaz_textView);
                            float gaz = sharedPreferences.getFloat("gaz", 0);

                            balance -= gaz;
                            editor.putFloat("balance", balance);
                            editor.apply();

                            gaz = 0.0F;
                            editor.putFloat("gaz",gaz);
                            editor.apply();
                            gazview.setText("Итого потрачено газа на: " + gaz + " рублей");
                            Toast.makeText(getApplicationContext(), "Счёт успешно оплачен", Toast.LENGTH_SHORT).show();
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

        } else {
            Toast.makeText(getApplicationContext(), "У вас недостаточно средств для оплаты", Toast.LENGTH_SHORT).show();
        }


    }


    public void showAll(View view){
        float gaz = sharedPreferences.getFloat("gaz", 0);
        float warm = sharedPreferences.getFloat("warm", 0);
        float cold = sharedPreferences.getFloat("cold", 0);
        TextView allview = findViewById(R.id.AlltextView);
        float res = gaz + warm + cold;
        allview.setText("Всего счетов на: " + res + " рублей");
    }
    public void payAll(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        float balance = sharedPreferences.getFloat("balance", 0);
        float gaz = sharedPreferences.getFloat("gaz", 0);
        float warm = sharedPreferences.getFloat("warm", 0);
        float cold = sharedPreferences.getFloat("cold", 0);


        if(balance >= gaz + warm + cold){
            // Установка заголовка и сообщения диалогового окна
            builder.setTitle("Подтверждение");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            float res = gaz + warm + cold;
            builder.setMessage("Вы уверены, что хотите оплатить счета на " + res +" рублей ?");
            // Установка кнопки "Да" и ее обработчика
            builder.setPositiveButton("Да", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            float balance = sharedPreferences.getFloat("balance", 0);
                            TextView gazview = findViewById(R.id.gaz_textView);
                            TextView warmview = findViewById(R.id.warm_textView);
                            TextView coldview = findViewById(R.id.cold_textView);
                            TextView allview = findViewById(R.id.AlltextView);
                            float gaz = sharedPreferences.getFloat("gaz", 0);
                            float warm = sharedPreferences.getFloat("warm", 0);
                            float cold = sharedPreferences.getFloat("cold", 0);

                            balance = balance - (gaz + warm + cold) ;
                            editor.putFloat("balance", balance);
                            editor.apply();

                            gaz = 0.0F;
                            warm = 0.0F;
                            cold = 0.0F;
                            editor.putFloat("gaz",gaz);
                            editor.apply();
                            gazview.setText("Итого потрачено газа на: " + gaz + " рублей");

                            editor.putFloat("warm",warm);
                            editor.apply();
                            warmview.setText("Итого потрачено воды на: " + warm + " рублей");

                            editor.putFloat("cold",cold);
                            editor.apply();
                            coldview.setText("Итого потрачено воды на: " + cold + " рублей");

                            allview.setText("Всего счетов на: " + cold + " рублей");
                            Toast.makeText(getApplicationContext(), "Счёт успешно оплачен", Toast.LENGTH_SHORT).show();
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

        } else {
            Toast.makeText(getApplicationContext(), "У вас недостаточно средств для оплаты", Toast.LENGTH_SHORT).show();
        }


    }


    public void addMoney(View view){
        EditText editText = findViewById(R.id.moneyEdittext);
        String warm_str = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView textView = findViewById(R.id.balance_textview);

        float balance = (float) 0;
        if(warm_str.isEmpty() || warm_str.contains(" ")){
            Toast.makeText(getApplicationContext(), "Строка не должна быть пустой или содержать пробел", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!warm_str.isEmpty()) {
            try {
                float addbalance = sharedPreferences.getFloat("balance", 0);
                balance = Integer.parseInt(warm_str);
                addbalance+=balance;
                editor.putFloat("balance",addbalance);
                editor.apply();
                textView.setText(""+addbalance);
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Вводимое значение должно быть числом", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ShowBalance(View view){
        TextView warm = findViewById(R.id.balance_textview);
        float res = sharedPreferences.getFloat("balance", 0);
        warm.setText(""+res);

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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}