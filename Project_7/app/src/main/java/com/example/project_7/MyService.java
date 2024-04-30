package com.example.project_7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        // Инициализация медиаплеера
        mediaPlayer = MediaPlayer.create(this, R.raw.sick);
        mediaPlayer.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d(TAG, "Музыка начала играть");
        }
        else{
            mediaPlayer.stop();
            Log.d(TAG, "Музыка остановлена и ресурсы освобождены");
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.d(TAG, "Музыка остановлена и ресурсы освобождены");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null; // Для сервисов без привязки возвращаем null
    }
}
