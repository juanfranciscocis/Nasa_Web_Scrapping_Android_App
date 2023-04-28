package com.example.nasa_web_scrapping_android_app;

import java.net.URL;

public class Nasa {

    private String title;
    private String http;
    private URL url;

    public Nasa(String title, String http, URL url) {
        this.title = title;
        this.http = http;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getHttp() {
        return http;
    }

    public URL getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public void setUrl(URL url) {
        this.url = url;
    }





}
