package com.example.nasa_web_scrapping_android_app;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    //Singleton to store data
    //Private members
    private ArrayList<String> linksScrapped1 = new ArrayList<>();
    private HashMap<String,Nasa> mapaImagenes = new HashMap<>();

    // stores already downloaded Bitmaps for reuse
    private Map<String, Bitmap> bitmaps = new HashMap<>();

    //Singleton
    private static Data instance = new Data();
    // Get the singleton instance
    public static Data getInstance() {
        return instance;
    }

    //Getters and setters
    public ArrayList<String> getLinksScrapped1() {
        return linksScrapped1;
    }

    public void setLinksScrapped1(String link) {
        this.linksScrapped1.add(link);
    }

    public HashMap<String, Nasa> getMapaImagenes() {
        return mapaImagenes;
    }

    public void setMapaImagenes(Nasa nasa) {
        String key = nasa.getTitle();
        this.mapaImagenes.put(key, nasa);
    }

    public Map<String, Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(String key, Bitmap bitmap) {
        this.bitmaps.put(key, bitmap);
    }


}
