package com.busfilter.busfileter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.busfilter.busfileter.Model.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 01/10/2016.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    private static int mResource = R.layout.listview_item;
    List<Bus> list_item;
    private LayoutInflater vi;
    public ListViewAdapter(Context context,
                           final List<Bus> items) {
        this.context = context;

        list_item = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list_item.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder {
        TextView bus_name,bus_type,bus_seating,price;

        public ViewHolder(View v) {
            bus_name = (TextView) v.findViewById(R.id.bus_name);
            bus_type = (TextView) v.findViewById(R.id.bus_type);
            bus_seating = (TextView) v.findViewById(R.id.bus_seating);
            price = (TextView) v.findViewById(R.id.bus_price);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = vi.inflate(mResource, null);
//            holder = new ViewHolder(convertView);
//            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TextView bus_name,bus_type,bus_seating,price;
        // Get the position
        final Bus item = list_item.get(position);
        // Locate the TextViews in listview_item.xml
        if (item != null) {
            bus_name = (TextView) convertView.findViewById(R.id.bus_name);
            bus_name.setText(item.getBusName());
            bus_type = (TextView) convertView.findViewById(R.id.bus_type);
            bus_type.setText(item.getBusType());
            bus_seating = (TextView) convertView.findViewById(R.id.bus_seating);
            bus_seating.setText(item.getBusSeating());
            price = (TextView) convertView.findViewById(R.id.bus_price);
            price.setText(item.getBusPrice());
        }
        return convertView;
    }
}
