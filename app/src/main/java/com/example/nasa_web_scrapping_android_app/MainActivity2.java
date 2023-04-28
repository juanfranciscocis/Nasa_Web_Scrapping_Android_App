package com.example.nasa_web_scrapping_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //init Data Singleton
        Data data = Data.getInstance();

        //init all elements
        ListView listView = findViewById(R.id.listView);
        FloatingActionButton returnScreen = findViewById(R.id.returnButton);
        //ArrayAdapter
        MainActivity2Adapter adapter;

        //go back to the main screen
        returnScreen.setOnClickListener(v -> {
            finish();
        });

        //nasa list, get the values from the Data singleton Hashmap and set them to the adapter
        HashMap<String,Nasa> nasaList = data.getMapaImagenes();
        // get only the values from the hashmap
        List<Nasa> nasaListValues = new ArrayList<>(nasaList.values());
        //set the adapter
        adapter = new MainActivity2Adapter(this,nasaListValues);
        listView.setAdapter(adapter);

    }
}