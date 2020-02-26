package com.example.cse118asg02.images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Download an image and return the bitmap.
 */
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    public Bitmap downloadImageBitmap(String url) {
        Bitmap bitmap = null;
        try {
            Log.d("[Download Image]", "Trying to download...");
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch(Exception e) {
            Log.d("[Download Image]", "Failed.", e);
        }
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return downloadImageBitmap(strings[0]);
    }

    protected void onPostExecute(Bitmap result) {
        Log.d("[Download Image]", "Post Execute");
    }
}
