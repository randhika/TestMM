package com.xinlukou.metroman.engine;

public class Station {
    public Integer ID;
    public String UNO;
    public Integer MultiLine;
    public Integer MultiWay;

    public Station (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.UNO = array[0];
        this.MultiLine = Integer.getInteger(array[1]);
        this.MultiWay = Integer.getInteger(array[2]);
    }
}
