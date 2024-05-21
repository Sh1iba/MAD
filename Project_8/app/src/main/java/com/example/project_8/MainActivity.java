package com.example.project_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    Button bStart, btJustDoIt;
    public ImageView imageView;
    private final String jsonUrl = "https://random.dog/woof.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        btJustDoIt = findViewById(R.id.btJustDoIt);
        bStart = findViewById(R.id.btStart);
        // устанавливаем обработчик на кнопку "Начать в потоке"
        btJustDoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OneTimeWorkRequest work2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setInitialDelay(0, TimeUnit.SECONDS)
                        .build();
                WorkManager.getInstance(getApplicationContext()).enqueue(work2);
                OneTimeWorkRequest work =
                        new OneTimeWorkRequest.Builder(MyWorker.class).build();
                WorkManager.getInstance(getApplicationContext()).enqueue(work);
            }
        });


        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OneTimeWorkRequest work =
                        new OneTimeWorkRequest.Builder(MyWorker.class).build();

                OneTimeWorkRequest work2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setInitialDelay(0, TimeUnit.SECONDS)
                        .build();

                OneTimeWorkRequest work3 =
                        new OneTimeWorkRequest.Builder(MyWorker.class).build();


                WorkManager.getInstance(getApplicationContext()).beginWith(work).then(work2).then(work3).enqueue();
            }
        });


    }
    public void onDisplayDog(View view)
    {
        new Thread(() -> {
            final Bitmap bitmap = JsonToImg.getImageFromUrl(JsonToImg.getDogUrl(jsonUrl));
            runOnUiThread(() -> imageView.setImageBitmap(bitmap));
        }).start();
    }

}

