package com.busfilter.busfileter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.busfilter.busfileter.Model.Bus;
import com.busfilter.busfileter.Model.BusRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListViewAdapter adapter;
    String jsonObject = "{\"status\":200,\"data\":[{\"bus_type\":\"non-ac\",\"bus_name\":\"Saini Travels\",\"bus_seating\":\"sleeper\",\"price\":750},{\"bus_type\":\"non-ac\",\"bus_name\":\"Maharashtra Travels\",\"bus_seating\":\"non-sleeper\",\"price\":500},{\"bus_type\":\"ac\",\"bus_name\":\"Mahendra Travels\",\"bus_seating\":\"sleeper\",\"price\":650},{\"bus_type\":\"ac\",\"bus_name\":\"Raj Travels\",\"bus_seating\":\"sleeper\",\"price\":650},{\"bus_type\":\"non-ac\",\"bus_name\":\"Jp Travels\",\"bus_seating\":\"non-sleeper\",\"price\":400},{\"bus_type\":\"ac\",\"bus_name\":\"Purple Travlers\",\"bus_seating\":\"sleeper\",\"price\":600},{\"bus_type\":\"ac\",\"bus_name\":\"Jd Travlers\",\"bus_seating\":\"sleeper\",\"price\":600},{\"bus_type\":\"ac\",\"bus_name\":\"Rahul Travels\",\"bus_seating\":\"sleeper\",\"price\":600}]}";
    ListView list;
    FloatingActionButton fab_ac,fab_sleeping;
    Boolean ac_favicon=false;
    Boolean sleeper_favicon=false;
    ArrayList<HashMap<String, String>> titles;
    private Button name_asc_button,name_desc_button,age_asc_button,age_desc_button;
    private List<Bus> busDetails;

    BusRecord busRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_ac = (FloatingActionButton) findViewById(R.id.fab_ac);
        fab_sleeping = (FloatingActionButton) findViewById(R.id.fab_sleeping);
        list = (ListView) findViewById(R.id.theatre_list_view);

        fab_ac.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
        fab_sleeping.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
        name_asc_button = (Button) findViewById(R.id.name_asc_button);
        name_desc_button = (Button) findViewById(R.id.name_desc_button);

        age_asc_button = (Button) findViewById(R.id.age_asc_button);
        age_desc_button = (Button) findViewById(R.id.age_desc_button);
        busRecord=new BusRecord();
        try {
            JSONObject jObj = new JSONObject(jsonObject);
            int status = Integer.parseInt(jObj.getString("status"));
            String get_data = jObj.getString("data");
            //JSONObject jObj1 = new JSONObject(get_data);
            if (status == 200) {
                JSONArray jarray = new JSONArray(get_data);
                for (int i = 0; i < jarray.length(); ++i) {
                    JSONObject rec = jarray.getJSONObject(i);
                    busRecord.setItems(rec.getString("bus_name"), rec.getString("price"), rec.getString("bus_type"), rec.getString("bus_seating"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        busDetails= busRecord.getItems();
        adapter = new ListViewAdapter(this, busDetails);
        list.setAdapter(adapter);




        fab_sleeping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ac_favicon=false;
                fab_ac.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
                list = (ListView) findViewById(R.id.theatre_list_view);
                try {
                    JSONObject jObj = new JSONObject(jsonObject);
                    int status = Integer.parseInt(jObj.getString("status"));
                    String get_data = jObj.getString("data");
                    if (status == 200) {
                        JSONArray jarray = new JSONArray(get_data);
                        busRecord=new BusRecord();
                        if (!sleeper_favicon) {
                            sleeper_favicon = true;
                            fab_sleeping.setBackgroundTintList(getResources().getColorStateList(R.color.faviconSelectedColor));
                            for (int i = 0; i < jarray.length(); ++i) {
                                JSONObject rec = jarray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();
                                if (rec.getString("bus_seating").equals("sleeper")) {
                                    busRecord.setItems(rec.getString("bus_name"), rec.getString("price"), rec.getString("bus_type"), rec.getString("bus_seating"));
                                }
                            }
                        } else {
                            sleeper_favicon = false;
                            fab_sleeping.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
                            for (int i = 0; i < jarray.length(); ++i) {
                                JSONObject rec = jarray.getJSONObject(i);
                                busRecord.setItems(rec.getString("bus_name"), rec.getString("price"), rec.getString("bus_type"), rec.getString("bus_seating"));
                            }
                        }
                        busDetails= busRecord.getItems();
                        adapter = new ListViewAdapter(MainActivity.this, busDetails);
                        list.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        fab_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_sleeping.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
                sleeper_favicon = false;
                list = (ListView) findViewById(R.id.theatre_list_view);
                try {
                    JSONObject jObj = new JSONObject(jsonObject);
                    int status = Integer.parseInt(jObj.getString("status"));
                    String get_data = jObj.getString("data");
                    if (status == 200) {
                        JSONArray jarray = new JSONArray(get_data);
                        busRecord=new BusRecord();
                        if (!ac_favicon) {
                            ac_favicon = true;
                            fab_ac.setBackgroundTintList(getResources().getColorStateList(R.color.faviconSelectedColor));
                            for (int i = 0; i < jarray.length(); ++i) {
                                JSONObject rec = jarray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();
                                if (rec.getString("bus_type").equals("ac")) {
                                    busRecord.setItems(rec.getString("bus_name"), rec.getString("price"), rec.getString("bus_type"), rec.getString("bus_seating"));
                                }
                            }
                        } else {
                            ac_favicon = false;
                            fab_ac.setBackgroundTintList(getResources().getColorStateList(R.color.faviconColor));
                            for (int i = 0; i < jarray.length(); ++i) {
                                JSONObject rec = jarray.getJSONObject(i);
                                busRecord.setItems(rec.getString("bus_name"), rec.getString("price"), rec.getString("bus_type"), rec.getString("bus_seating"));
                            }
                        }
                        busDetails= busRecord.getItems();
                        adapter = new ListViewAdapter(MainActivity.this, busDetails);
                        list.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        age_asc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(busDetails, StringAgeAscComparator);
                adapter.notifyDataSetChanged();
            }
        });
        age_desc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(busDetails, StringAgeDescComparator);
                adapter.notifyDataSetChanged();
            }
        });
        name_asc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(busDetails, StringNameAscComparator);
                adapter.notifyDataSetChanged();
            }
        });
        name_desc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(busDetails, StringNameDescComparator);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public static Comparator<Bus> StringAgeAscComparator = new Comparator<Bus>() {
        public int compare(Bus app1, Bus app2) {
            //Toast.makeText(context, "Ascending" + Integer.valueOf(Integer.valueOf(app1.getAge()).compareTo(Integer.valueOf(app2.getAge()))), Toast.LENGTH_LONG).show();
            return  Integer.valueOf(Integer.valueOf(app1.getBusPrice()).compareTo(Integer.valueOf(app2.getBusPrice())));
        }
    };
    //Comparator for Descending Order
    public static Comparator<Bus> StringAgeDescComparator = new Comparator<Bus>() {
        public int compare(Bus app1, Bus app2) {
            //Toast.makeText(context, "Descending" + Integer.valueOf(Integer.valueOf(app2.getSalery()).compareTo(Integer.valueOf(app1.getSalery()))), Toast.LENGTH_LONG).show();
            return  Integer.valueOf(Integer.valueOf(app2.getBusPrice()).compareTo(Integer.valueOf(app1.getBusPrice())));
        }
    };
    public static Comparator<Bus> StringNameAscComparator = new Comparator<Bus>() {
        public int compare(Bus app1, Bus app2) {
            return app1.getBusName().compareToIgnoreCase(app2.getBusName());
        }
    };
    //Comparator for Descending Order
    public static Comparator<Bus> StringNameDescComparator = new Comparator<Bus>() {
        public int compare(Bus app1, Bus app2) {
            //Toast.makeText(context, "Descending" + Integer.valueOf(Integer.valueOf(app2.getSalery()).compareTo(Integer.valueOf(app1.getSalery()))), Toast.LENGTH_LONG).show();
            return app2.getBusName().compareToIgnoreCase(app1.getBusName());

        }
    };
}
