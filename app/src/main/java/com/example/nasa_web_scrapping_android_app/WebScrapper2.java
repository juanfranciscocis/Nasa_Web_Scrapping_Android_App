package com.example.nasa_web_scrapping_android_app;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class WebScrapper2 extends AsyncTask<URL,Void, Void> {
    private Boolean isDone = false;
    private Data data = Data.getInstance();
    private Document doc;

    @Override
    protected Void doInBackground(URL... urls) {
        try {

            //TODO: RECIVE THE ARRAY OF LINKS FROM THE FIRST WEBSCRAPPER, AND THEN ITERATE OVER THEM TO GET THE JPG LINKS

            doc = Jsoup.connect(urls[0].toString()).get();
            Element downloadLink = doc.select("a:contains(Download JPG)").first();
            String downloadUrl = downloadLink.attr("href");
            //TODO: ADD THE JPG LINK TO THE DATA SINGLETON
            Log.d("Download JPG: ",downloadUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Log.d("WebScrapper Full 2 ", doc.toString());
        return null;
    }
}
