package com.example.project_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project_6.Fragments.First_Fragment;
import com.example.project_6.Fragments.Fourth_Fragment;
import com.example.project_6.Fragments.Second_Fragment;
import com.example.project_6.Fragments.Third_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;




public class Second_Activity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#191970"));
            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);

            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Товары"); // Установить заголовок

            bottomNavigationView = findViewById(R.id.bottom_navigation);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    First_Fragment.class, null).commit();
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    item -> {
                        if (item.getItemId() == R.id.products) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                                    First_Fragment.class, null).commit();
                        } else if (item.getItemId() == R.id.chats) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                                    Second_Fragment.class, null).commit();
                        } else if (item.getItemId() == R.id.video) {

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                                    Third_Fragment.class, null).commit();
                        } else {

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                                    Fourth_Fragment.class, null).commit();
                        }
                        actionBar.setTitle(item.getTitle());
                        return true;
                    });
        }


    }

    //возвращает объект MenuInflater, у которого вызывается
    //метод inflate(). Этот метод в качестве первого параметра принимает ресурс,
    //представляющий наше описание меню в xml, и наполняет им объект menu,
    //переданный в качестве второго параметра.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        bottomNavigationView.setSelectedItemId(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}
