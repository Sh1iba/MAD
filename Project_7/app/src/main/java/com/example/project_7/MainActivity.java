package com.example.project_7;

import static android.app.Service.START_STICKY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MusicService";
    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent startIntent = new Intent(this, MyService.class);
        // Остановка сервиса и музыки при уничтожении активности
        Intent stopIntent = new Intent(this, MyService.class);

        Button play_button = (Button)this.findViewById(R.id.playButton);
        play_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                count+=1;
                if (count % 2 ==0) {
                    startService(startIntent);
                }
                else {
                    stopService(stopIntent);
                }
                }

        });

    }
    public void OnNextActivity(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);

    }
}