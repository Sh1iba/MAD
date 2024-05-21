package com.example.project_8;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {
    public final String TAG = "MY_TAG";
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }
    @NonNull
    @Override
    public Result doWork() {
        Log.v(TAG, "Work is in progress");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Work finished");
        return Worker.Result.;
    }
}
