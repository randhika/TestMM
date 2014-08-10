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
        String[] array = str.split(",", -1);
        this.id = theID;
        this.wayID = Integer.parseInt(array[0]);
        this.fromStationID = Integer.parseInt(array[1]);
        this.toStationID = Integer.parseInt(array[2]);
        this.averageTime = Integer.parseInt(array[3]);
        this.distance = Integer.parseInt(array[4]);
        this.startTransferFromID = Integer.parseInt(array[5]);
        this.endTransferFromID = Integer.parseInt(array[6]);
        this.startTransferToID = Integer.parseInt(array[7]);
        this.endTransferToID = Integer.parseInt(array[8]);
        this.startWeekdayID = Integer.parseInt(array[9]);
        this.endWeekdayID = Integer.parseInt(array[10]);
        this.startWeekendID = Integer.parseInt(array[11]);
        this.endWeekendID = Integer.parseInt(array[12]);
    }
}
