package com.xinlukou.metroman.engine;

import java.util.Arrays;
import java.util.List;

public class SrcFare {
    public String fareType;
    public String wayUNOList;
    public String commonFare;
    public String fileFare;
    public String stationUNOList;
    public List<String> wayList;
    public List<String> unoList;
    public String fileFareContent;
    public List<String> fareList;

    public SrcFare (String str) {
        String[] array = str.split(",");
        this.fareType = array[0];
        this.wayUNOList = array[1];
        this.commonFare = array[2];
        this.fileFare = array[3];
        this.stationUNOList = array[4];
        this.wayList = Arrays.asList(this.wayUNOList.split("|"));
        this.unoList = Arrays.asList(this.stationUNOList.split("|"));
        if(!this.fileFare.isEmpty()) {
            //TODO
        }
    }
}
