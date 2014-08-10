package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SrcFare {
    public String FareType;
    public String WayUNOList;
    public String CommonFare;
    public String FileFare;
    public String StationUNOList;
    public List<String> WayList;
    public List<String> UNOList;
    public String FileFareContent;
    public List<String> FareList;

    public SrcFare (String str) {
        String[] array = str.split(",");
        this.FareType = array[0];
        this.WayUNOList = array[1];
        this.CommonFare = array[2];
        this.FileFare = array[3];
        this.StationUNOList = array[4];
        this.WayList = Arrays.asList(this.WayUNOList.split("|"));
        this.UNOList = Arrays.asList(this.StationUNOList.split("|"));
        if(!this.FileFare.isEmpty()) {
            //TODO
        }
    }
}
