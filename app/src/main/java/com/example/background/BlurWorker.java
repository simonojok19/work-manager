package com.example.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BlurWorker extends Worker {
    public BlurWorker(
            @NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Bitmap picture = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.test
        );
        return null;
    }
}
