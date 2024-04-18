package com.example.project_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recycler_Activity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private SimpleAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rycycler);
            //Находим элемент RecycleView
            recyclerView = findViewById(R.id.recycler_view);
            //Устанавливает макет отображения - гориозонтально
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Пример списка изображения и строки
            List<Data> data = new ArrayList<>();
            data.add(new Data(R.drawable.image1, "Image 1"));
            data.add(new Data(R.drawable.image2, "Image 2"));
            data.add(new Data(R.drawable.image3, "Image 3"));
            data.add(new Data(R.drawable.image4, "Image 4"));
            data.add(new Data(R.drawable.image5, "Image 5"));

            adapter = new SimpleAdapter(data);
            recyclerView.setAdapter(adapter);
        }
}