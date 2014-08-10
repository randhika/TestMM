package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.List;

public class Way {
    public Integer id;
    public String uno;
    public Integer lineID;
    public Integer waitTime;
    public List<Integer> stationIDList;

    public Way (Integer theID, String str) {
        String[] array = str.split(",");
        this.id = theID;
        this.uno = array[0];
        this.lineID = Integer.getInteger(array[1]);
        this.waitTime = Integer.getInteger(array[2]);
        this.stationIDList = new ArrayList<Integer>(array.length - 3);
        for (int i = 3; i < array.length; i++) {
            this.stationIDList.add(Integer.getInteger(array[i]));
        }
    }
}
