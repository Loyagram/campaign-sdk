package com.loyagram.android.campaignsdk.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import static android.view.View.VISIBLE;

/**
 * Fetches logo image from server in the background.
 */
@SuppressWarnings("WeakerAccess")
public class AsyncTaskGetImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    TextView brandName;

    /**
     * @param bmImage   brand logo image view

     * @param brandName brand name text view
     */
    public AsyncTaskGetImage(ImageView bmImage, TextView brandName) {
        this.bmImage = bmImage;
        this.brandName = brandName;
    }

    /**
     * fetch image from server in background thread
     *
     * @param urls url to fetch logo
     * @return brand logo
     */
    protected Bitmap doInBackground(String... urls) {
        Bitmap mIcon11 = null;
        try {
            String urldisplay = urls[0];
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
           // Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    /**
     * Sets logo to image view and brand name text view will be set to visible
     *
     * @param result brand logo
     */
    protected void onPostExecute(Bitmap result) {
        if (result != null && !isCancelled()) {
            bmImage.setImageBitmap(result);
            bmImage.setVisibility(VISIBLE);
        }
        brandName.setVisibility(VISIBLE);
    }
}