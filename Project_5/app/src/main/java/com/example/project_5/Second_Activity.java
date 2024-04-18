package com.example.project_5;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class Second_Activity extends AppCompatActivity {
    private String items;
    private List<String> exercise;
    private ListView listView2;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        items = getIntent().getStringExtra("items");
        exercise = new ArrayList<String>();

        listView2 = (ListView) findViewById(R.id.my_list_view2);
        // создаем адаптер
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercise);
        // устанавливаем адаптер для списка
        listView2.setAdapter(adapter);

        if (items.equals("Breast")) {
            exercise.add("Bench press");
            exercise.add("Information with dumbbells");
            exercise.add("Push-ups");
        } else if (items.equals("Biceps")) {
            exercise.add("Lifting the barbell on the biceps");
            exercise.add("Lifting the barbell on Scott's bench");
            exercise.add("Lifting dumbbells on biceps");
        } else if (items.equals("Triceps")) {
            exercise.add("Push-ups on the uneven bars");
            exercise.add("Dumbbell pull over your head");
            exercise.add("French bench press");
        }else if (items.equals("Legs")) {
            exercise.add("Squats");
            exercise.add("Lunges with dumbbells");
            exercise.add("Lifting the barbell on the calves");
        }else if (items.equals("Trapezoids")) {
            exercise.add("Scars");
        }else if (items.equals("Back")) {
            exercise.add("Pull-ups");
            exercise.add("Upper block thrust");
            exercise.add("Dumbbell pull to the belt");
        }else if (items.equals("Abs")) {
            exercise.add("Twisting");
            exercise.add("Cyclist");
            exercise.add("Book");
        }else if (items.equals("Shoulders")) {
            exercise.add("Dumbbell press over your head");
            exercise.add("Dumbbell press behind the head");
            exercise.add("Lifting dumbbells in front of you");
        }
        else if (items.equals("Forearm")) {
            exercise.add("Twisting dumbbells on the brush");
            exercise.add("Ispander");
            exercise.add("Vis on the horizontal bar");
        }else{
            exercise.add("Still in development");
        }


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // получаем нажатый элемент
                String pushedItem = adapter.getItem(position);
                if (listView2.isItemChecked(position))
                    exercise.add(pushedItem);
                else
                    exercise.remove(pushedItem);
            }
        });
    }
    public void add(View view){
        EditText exercise_name = findViewById(R.id.editText);
        String text = exercise_name.getText().toString();

        if(!text.isEmpty()){
            exercise.add(text);
            exercise_name.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void remove(View view){
        // получаем и удаляем выделенные элементы
        EditText exercise_name = findViewById(R.id.editText);
        String text = exercise_name.getText().toString();
        if(!text.isEmpty()) {
            adapter.remove(text);
            adapter.notifyDataSetChanged();
        }
    }
}

