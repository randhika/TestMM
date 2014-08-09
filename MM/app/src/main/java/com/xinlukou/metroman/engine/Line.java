package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.List;

public class Line {
    public Integer ID;
    public String UNO;
    public List<Integer> StationIDList;

    public Line (Integer theID, String str) {
        String[] array = str.split(",");
        this.ID = theID;
        this.UNO = array[0];
        this.StationIDList = new ArrayList<Integer>(array.length - 1);
        for (int i = 1; i < array.length; i++) {
            this.StationIDList.add(Integer.getInteger(array[i]));
        }
    }
}
