package com.busfilter.busfileter.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 03/10/2016.
 */
public class BusRecord {
    List<Bus> list =new ArrayList<Bus>() ;
//    public void BusRecord(){
//        list = ;
//    }

    public void setItems(String busName, String busPrice, String busType, String busSetting){
        list.add(new Bus(busName,busPrice,busType,busSetting));
    }
    public List<Bus> getItems(){
        return list;
    }
}
