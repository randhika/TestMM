package com.xinlukou.metroman.engine;

public class Transfer {
    public Integer id;
    public Integer fromLinkID;
    public Integer toLinkID;
    public Integer transTime;
    public Integer transDistance;
    public Integer transType;

    public Transfer (Integer theID, String str) {
        String[] array = str.split(",", -1);
        this.id = theID;
        this.fromLinkID = Integer.parseInt(array[0]);
        this.toLinkID = Integer.parseInt(array[1]);
        this.transTime = Integer.parseInt(array[2]);
        this.transDistance = Integer.parseInt(array[3]);
        this.transType = Integer.parseInt(array[4]);
    }
}
