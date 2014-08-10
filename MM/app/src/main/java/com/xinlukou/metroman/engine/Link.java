package com.xinlukou.metroman.engine;

public class Link {
    public Integer id;
    public Integer wayID;
    public Integer fromStationID;
    public Integer toStationID;
    public Integer averageTime;
    public Integer distance;
    public Integer startTransferFromID;
    public Integer endTransferFromID;
    public Integer startTransferToID;
    public Integer endTransferToID;
    public Integer startWeekdayID;
    public Integer endWeekdayID;
    public Integer startWeekendID;
    public Integer endWeekendID;

    public Link (Integer theID, String str) {
        String[] array = str.split(",");
        this.id = theID;
        this.wayID = Integer.getInteger(array[0]);
        this.fromStationID = Integer.getInteger(array[1]);
        this.toStationID = Integer.getInteger(array[2]);
        this.averageTime = Integer.getInteger(array[3]);
        this.distance = Integer.getInteger(array[4]);
        this.startTransferFromID = Integer.getInteger(array[5]);
        this.endTransferFromID = Integer.getInteger(array[6]);
        this.startTransferToID = Integer.getInteger(array[7]);
        this.endTransferToID = Integer.getInteger(array[8]);
        this.startWeekdayID = Integer.getInteger(array[9]);
        this.endWeekdayID = Integer.getInteger(array[10]);
        this.startWeekendID = Integer.getInteger(array[11]);
        this.endWeekendID = Integer.getInteger(array[12]);
    }
}
