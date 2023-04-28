package com.example.nasa_web_scrapping_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init Data Singleton
        Data data = Data.getInstance();

        //init all elements
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView6 = findViewById(R.id.imageView6);
        FloatingActionButton nextScreen = findViewById(R.id.floating_button);
        FloatingActionButton debug = findViewById(R.id.debug);

        // add image views to an arrayList
        ImageView[] imageViews = {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6};
        //read image No_Image_Available from assets folder, and set it to all imageViews
        AssetManager assetManager = getAssets();
        try {
            InputStream ims = assetManager.open("No_Image_Available.jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            for (ImageView imageView : imageViews) {
                imageView.setImageDrawable(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // WebScraper 1 -> Getting the links of the articles
        WebScrapper1 webScrapper1 = new WebScrapper1();
        URL url = null;
        try {
            url = new URL("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        webScrapper1.execute(url);


        //TODO:DELETE THIS BUTTON


        // when debug button is clicked, print the links scrapped from the Data singleton

        /*
        debug.setOnClickListener(v -> {
            ArrayList<String> linksScrapped1 = data.getLinksScrapped1();
            for (String link : linksScrapped1) {
                Log.d("Data read", link);
            }
        });

         */

        debug.setOnClickListener(v->{
            //WebScraper 2 -> Getting the images links from the articles
            WebScrapper2 webScrapper2 = new WebScrapper2();
            try {
                webScrapper2.execute(new URL(data.getLinksScrapped1().get(0)));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        });

        //TODO: WHEN A IMAGE IS CLICKED, LOAD THE IMAGE FROM THE INTERNET AND SHOW IT IN THE SAME IMAGE VIEW
        //TODO WHEN A IMAGE IS CLICKED SHOW LOADING ANIMATION

        //TODO:ACTIVITY 2...






    }



}