package com.xinlukou.metroman.engine;

public class Timetable {
    public Integer id;
    public Integer linkID;
    public Integer depTime;
    public Integer arrTime;

    public Timetable (Integer theID, String str) {
        String[] array = str.split(",", -1);
        this.id = theID;
        this.linkID = Integer.parseInt(array[0]);
        this.depTime = Integer.parseInt(array[1]);
        this.arrTime = Integer.parseInt(array[2]);
    }
}
