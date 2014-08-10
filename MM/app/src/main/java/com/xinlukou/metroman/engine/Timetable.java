package com.xinlukou.metroman.engine;

public class Timetable {
    public Integer id;
    public Integer linkID;
    public Integer depTime;
    public Integer arrTime;

    public Timetable (Integer theID, String str) {
        String[] array = str.split(",");
        this.id = theID;
        this.linkID = Integer.getInteger(array[0]);
        this.depTime = Integer.getInteger(array[1]);
        this.arrTime = Integer.getInteger(array[2]);
    }
}
