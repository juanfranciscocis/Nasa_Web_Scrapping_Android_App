package com.example.nasa_web_scrapping_android_app;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class WebScrapper1 extends AsyncTask<URL, Void, Void> {

    public Boolean isDone = false;

    private Document doc;
    private Data data = Data.getInstance();

    @Override
    protected Void doInBackground(URL... urls) {

        try{
            doc = Jsoup.connect(urls[0].toString()).get();
            //Log.d("WebScrapper Full ", doc.toString());

            Elements links = doc.select("#results a");
            for (Element link : links) {
                String href = link.attr("href");
                data.setLinksScrapped1(href);
                Log.d("WebScrapper1", href);
            }






            isDone = true;

        }catch (Exception e){
            e.printStackTrace();
        }








        return null;
    }
}
