package com.xinlukou.metroman.engine;

public class Link {
    public Integer ID;
    public Integer WayID;
    public Integer FromStationID;
    public Integer ToStationID;
    public Integer AverageTime;
    public Integer Distance;
    public Integer StartTransferFromID;
    public Integer EndTransferFromID;
    public Integer StartTransferToID;
    public Integer EndTransferToID;
    public Integer StartWeekdayID;
    public Integer EndWeekdayID;
    public Integer StartWeekendID;
    public Integer EmdWeekendID;

    public Link (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.WayID = Integer.getInteger(array[0]);
        this.FromStationID = Integer.getInteger(array[1]);
        this.ToStationID = Integer.getInteger(array[2]);
        this.AverageTime = Integer.getInteger(array[3]);
        this.Distance = Integer.getInteger(array[4]);
        this.StartTransferFromID = Integer.getInteger(array[5]);
        this.EndTransferFromID = Integer.getInteger(array[6]);
        this.StartTransferToID = Integer.getInteger(array[7]);
        this.EndTransferToID = Integer.getInteger(array[8]);
        this.StartWeekdayID = Integer.getInteger(array[9]);
        this.EndWeekdayID = Integer.getInteger(array[10]);
        this.StartWeekendID = Integer.getInteger(array[11]);
        this.EmdWeekendID = Integer.getInteger(array[12]);
    }
}
