package com.example.course_project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.course_project.Fragments.AboutTheProgramFragment;
import com.example.course_project.Fragments.MainFragment;
import com.example.course_project.Fragments.MoneyFragment;
import com.example.course_project.Fragments.PayHistoryFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        PayHistoryFragment.class, null).commit();
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