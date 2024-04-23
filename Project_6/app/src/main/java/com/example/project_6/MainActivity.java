package com.example.project_6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project_6.Fragments.First_Fragment;
import com.example.project_6.Fragments.Fourth_Fragment;
import com.example.project_6.Fragments.Second_Fragment;
import com.example.project_6.Fragments.Third_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#191970"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.products) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        First_Fragment.class, null).commit();
            } else if (item.getItemId() == R.id.chats) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        Second_Fragment.class, null).commit();
            } else if (item.getItemId() == R.id.video) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        Third_Fragment.class, null).commit();
            } else if  (item.getItemId() == R.id.photo) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        Fourth_Fragment.class, null).commit();
            }
            else {
                Intent intent = new Intent(this,Second_Activity.class);
                startActivity(intent);
            }

            actionBar.setTitle(item.getTitle());
            return true;
        });

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.drawer_open,R.string.drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);

        }
        toggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            Toast.makeText(MainActivity.this, "нажато",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}