package com.xinlukou.metroman.engine;

public class Transfer {
    public Integer id;
    public Integer fromLinkID;
    public Integer toLinkID;
    public Integer transTime;
    public Integer transDistance;
    public Integer transType;

    public Transfer (Integer theID, String str) {
        String[] array = str.split(",");
        this.id = theID;
        this.fromLinkID = Integer.getInteger(array[0]);
        this.toLinkID = Integer.getInteger(array[1]);
        this.transTime = Integer.getInteger(array[2]);
        this.transDistance = Integer.getInteger(array[3]);
        this.transType = Integer.getInteger(array[4]);
    }
}
