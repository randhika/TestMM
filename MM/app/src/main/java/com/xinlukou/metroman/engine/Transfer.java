package com.xinlukou.metroman.engine;

public class Transfer {
    public Integer ID;
    public Integer FromLinkID;
    public Integer ToLinkID;
    public Integer TransTime;
    public Integer TransDistance;
    public Integer TransType;

    public Transfer (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.FromLinkID = Integer.getInteger(array[0]);
        this.ToLinkID = Integer.getInteger(array[1]);
        this.TransTime = Integer.getInteger(array[2]);
        this.TransDistance = Integer.getInteger(array[3]);
        this.TransType = Integer.getInteger(array[4]);
    }
}
