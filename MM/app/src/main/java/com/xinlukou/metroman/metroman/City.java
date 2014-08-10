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
        String[] array = str.split(",");
        this.cityID = Integer.getInteger(array[0]);
        this.cityKey = array[1];
        this.linkCount = Integer.getInteger(array[2]);
        this.transferCount = Integer.getInteger(array[3]);
        this.weekdayCount = Integer.getInteger(array[4]);
        this.weekendCount = Integer.getInteger(array[5]);
        this.appID = array[6];
        this.latitude = Integer.getInteger(array[7]);
        this.longitude = Integer.getInteger(array[8]);
    }
}
