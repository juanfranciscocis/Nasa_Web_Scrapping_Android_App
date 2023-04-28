package com.example.nasa_web_scrapping_android_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity2Adapter extends ArrayAdapter<Nasa> {

    // class for reusing views as list items scroll off and onto the screen
    private static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView urlTextView;
    }

    // stores already downloaded Bitmaps for reuse
    private Data data = Data.getInstance();
    private Map<String, Bitmap> bitmaps = data.getBitmaps();

    // constructor to initialize superclass inherited members
    public MainActivity2Adapter(Context context, List<Nasa> nasa) {
        super(context, -1, nasa);
    }

    // creates the custom views for the ListView's items
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get Weather object for this specified ListView position
        Nasa nasa = getItem(position);

        ViewHolder viewHolder; // object that reference's list item's views

        // check for reusable ViewHolder from a ListView item that scrolled
        // offscreen; otherwise, create a new ViewHolder
        if (convertView == null) { // no reusable ViewHolder, so create one
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView =
                    inflater.inflate(R.layout.item, parent, false);
            viewHolder.imageView =
                    (ImageView) convertView.findViewById(R.id.imageItem);
            viewHolder.titleTextView =
                    (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.urlTextView =
                    (TextView) convertView.findViewById(R.id.urlTextView);
            convertView.setTag(viewHolder);
        }
        else { // reuse existing ViewHolder stored as the list item's tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // if nasa image already downloaded, use it;
        // otherwise, download icon in a separate thread
        if (bitmaps.containsKey(nasa.getTitle())) {
            viewHolder.imageView.setImageBitmap(
                    bitmaps.get(nasa.getTitle()));
        }
        else {
            //TODO: LOAD NO IMAGE
        }

        // get other data from Weather object and place into views
        Context context = getContext(); // for loading String resources
        viewHolder.titleTextView.setText(nasa.getTitle());
        viewHolder.urlTextView.setText(nasa.getHttp());

        return convertView; // return completed list item to display
    }





}
