package com.xinlukou.metroman.metroman;

public class City {
    public Integer cityID;
    public String cityKey;
    public Integer linkCount;
    public Integer transferCount;
    public Integer weekdayCount;
    public Integer weekendCount;
    public String appID;
    public double latitude;
    public double longitude;

    public City (String str) {
        String[] array = str.split(",", -1);
        this.cityID = Integer.parseInt(array[0]);
        this.cityKey = array[1];
        this.linkCount = Integer.parseInt(array[2]);
        this.transferCount = Integer.parseInt(array[3]);
        this.weekdayCount = Integer.parseInt(array[4]);
        this.weekendCount = Integer.parseInt(array[5]);
        this.appID = array[6];
        this.latitude = Double.parseDouble(array[7]);
        this.longitude = Double.parseDouble(array[8]);
    }
}
