package com.example.background.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.background.Constants;
import com.example.background.R;

import java.io.FileNotFoundException;

public class BlurWorker extends Worker {
    public BlurWorker(
            @NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private static final String TAG = BlurWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        String resourceUri = getInputData().getString(Constants.KEY_IMAGE_URI);

        try {
            Bitmap picture = BitmapFactory.decodeResource(
                    applicationContext.getResources(),
                    R.drawable.test
            );
            Bitmap output = WorkerUtils.blurBitmap(picture, applicationContext);

            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext,output);
            WorkerUtils.makeStatusNotification("Output is "
            + outputUri.toString(), applicationContext);

            return Result.success();
        } catch (Throwable throwable) {
            Log .e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}
