package com.example.nasa_web_scrapping_android_app;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WebScrapperImageSet extends AsyncTask<String,Void, Bitmap> {
    private Data data = Data.getInstance();
    private Document doc;
    Boolean error = false;
    private ImageView imageView; // displays the thumbnail

    // store ImageView on which to set the downloaded Bitmap
    // Constructor
    public WebScrapperImageSet(ImageView imageView) {
        this.imageView = imageView;
    }



    @Override
    protected Bitmap doInBackground(String... url) {







        //Loading the image
        AssetManager assetManager = imageView.getContext().getAssets();
        try {
            InputStream ims = assetManager.open("Loading_Image.jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (Exception e) {
            error = true;
        }



        URL urlFinal = null;
        String title = null;

        // GET THE TITLE AND IMAGE URL
            try {
                doc = Jsoup.connect(url[0].toString()).get();
                //IMAGE LINK
                Element downloadLink = doc.select("a:contains(Download JPG)").first();
                String downloadUrl = downloadLink.attr("href");
                //IMAGE URL
                urlFinal = new URL(downloadUrl);
                //IMAGE TITLE
                title = doc.title();
                //ADD TO THE SINGLETON
                data.setMapaImagenes(new Nasa(title,downloadUrl,urlFinal));
                Log.d("Download  from Data: ",data.getMapaImagenes().toString());

            }catch (Exception e) {
                error = true;
            }



        // GET THE IMAGE AND SET IT TO THE IMAGEVIEW
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        Map<String,Bitmap> bitmaps = data.getBitmaps();
        // if the bitmap is not in the cache, download the bitmap
        if (bitmaps.containsKey(title)) {
            bitmap = bitmaps.get(title);
            Log.d("Bitmap from Data: ","CHECK!!!");
            return bitmap;
        }




        try {

            // open an HttpURLConnection, get its InputStream
            // and download the image
            connection = (HttpURLConnection) urlFinal.openConnection();

            try (InputStream inputStream = connection.getInputStream()) {
                bitmap = BitmapFactory.decodeStream(inputStream);
                Data.getInstance().setBitmaps(title, bitmap); // cache for later use
                Log.d("Bitmap added to Data: ",Data.getInstance().getBitmaps().toString());
            }
            catch (Exception e) {
                error = true;
            }
        }
        catch (Exception e) {
            error = true;
        }
        try {

            connection.disconnect();

        }catch (Exception e) {
            error = true;
        }



        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(error){
            //Loading the image
            AssetManager assetManager = imageView.getContext().getAssets();
            if (error){
                //Error
                try {
                    InputStream ims = assetManager.open("Error_Loading.jpg");
                    Drawable d = Drawable.createFromStream(ims, null);
                    imageView.setImageDrawable(d);
                } catch (Exception e) {
                    error = true;
                }
            }
            return;
        }
        imageView.setImageBitmap(bitmap);
    }


    // create a new instance of this class and execute it on a separate thread
    public static void executeOnThreadPool(ImageView imageView, String... url) {
        WebScrapperImageSet task = new WebScrapperImageSet(imageView);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

}
