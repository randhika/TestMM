package com.xinlukou.metroman.engine;

public class Timetable {
    public Integer ID;
    public Integer LinkID;
    public Integer DepTime;
    public Integer ArrTime;

    public Timetable (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.LinkID = Integer.getInteger(array[0]);
        this.DepTime = Integer.getInteger(array[1]);
        this.ArrTime = Integer.getInteger(array[2]);
    }
}
