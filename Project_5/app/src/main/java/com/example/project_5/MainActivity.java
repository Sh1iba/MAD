package com.example.project_5;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] items = { "Breast", "Biceps", "Triceps", "Legs", "Trapezoids",
            "Back", "Shoulders", "Abs", "Forearm", "Whole body" };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // находим представление списка
        ListView usersListView = (ListView) findViewById(R.id.my_list_view);
        // создаем адаптер
        ArrayAdapter<String> usersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        // устанавливаем адаптер для списка
        usersListView.setAdapter(usersAdapter);


        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                // по позиции получаем выбранный элемент
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.putExtra("items", items[position]);
                startActivity(intent);
            }
        });
    }
    public void OnNextActivitySpinner(View view){
        Intent intent = new Intent(this, Spiner_Activity.class);
        startActivity(intent);
    }
    public void OnNextActivityScrollView(View view){
        Intent intent = new Intent(this, ScrollView_Activity.class);
        startActivity(intent);
    }
    public void OnNextActivityRecyclerView(View view){
        Intent intent = new Intent(this, Recycler_Activity.class);
        startActivity(intent);
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
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
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