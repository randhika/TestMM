package com.xinlukou.metroman.engine;

public class Station {
    public Integer id;
    public String uno;
    public Integer multiLine;
    public Integer multiWay;

    public Station (Integer theID, String str) {
        String[] array = str.split(",", -1);
        this.id = theID;
        this.uno = array[0];
        this.multiLine = Integer.parseInt(array[1]);
        this.multiWay = Integer.parseInt(array[2]);
    }
}
