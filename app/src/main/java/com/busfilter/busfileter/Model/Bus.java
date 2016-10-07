package com.busfilter.busfileter.Model;

/**
 * Created by User on 03/10/2016.
 */
public class Bus {
    private String busName;
    private String busPrice;
    private String busType;
    private String busSeating;
    public Bus(String busName, String busPrice, String busType, String busSeating) {
        this.busName = busName;
        this.busPrice = busPrice;
        this.busType = busType;
        this.busSeating = busSeating;
    }

    public String getBusName() {
        return busName;
    }

    public String getBusPrice() {
        return busPrice;
    }

    public String getBusType() {
        return busType;
    }
    public String getBusSeating() {
        return busSeating;
    }

    @Override
    public String toString() {
        return this.busName;
    }

}
