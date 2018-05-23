package com.swdm.mp.movieharmonymaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    Context context;
    private ArrayList<MovieItem> items;
    private int layout;
    LayoutInflater layoutInflater;

    MovieAdapter(Context context, int layout, ArrayList<MovieItem> items) {
        this.context = context;
        this.items = items;
        this.layout = layout;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, null);
        }

        MovieItem listViewItem = items.get(position);

        ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
        image.setImageResource(listViewItem.image);

        TextView name = (TextView)convertView.findViewById(R.id.textView);
        name.setText(listViewItem.name);

        return convertView;
    }
}
