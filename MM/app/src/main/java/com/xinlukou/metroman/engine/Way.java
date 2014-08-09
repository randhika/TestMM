package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.List;

public class Way {
    public Integer ID;
    public String UNO;
    public Integer LineID;
    public Integer WaitTime;
    public List<Integer> StationIDList;

    public Way (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.UNO = array[0];
        this.LineID = Integer.getInteger(array[1]);
        this.WaitTime = Integer.getInteger(array[2]);
        this.StationIDList = new ArrayList<Integer>(array.length - 3);
        for (int i = 3; i < array.length; i++) {
            this.StationIDList.add(Integer.getInteger(array[i]));
        }
    }
}
