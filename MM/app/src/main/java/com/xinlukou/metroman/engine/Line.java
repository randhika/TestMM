package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.List;

public class Line {
    public Integer id;
    public String uno;
    public List<Integer> stationIDList;

    public Line (Integer theID, String str) {
        String[] array = str.split(",");
        this.id = theID;
        this.uno = array[0];
        this.stationIDList = new ArrayList<Integer>(array.length - 1);
        for (int i = 1; i < array.length; i++) {
            this.stationIDList.add(Integer.getInteger(array[i]));
        }
    }
}
