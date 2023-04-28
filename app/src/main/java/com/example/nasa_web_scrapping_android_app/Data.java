package com.example.nasa_web_scrapping_android_app;

import java.util.ArrayList;

public class Data {
    //Singleton to store data
    //Private members
    private ArrayList<String> linksScrapped1 = new ArrayList<>();

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


}
