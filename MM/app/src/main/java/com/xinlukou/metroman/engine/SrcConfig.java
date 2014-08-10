package com.xinlukou.metroman.engine;

public class SrcConfig {
    public String versionNO;
    public Integer searchResultType;
    public Integer searchResultFormat;
    public Integer requestTerminal;
    public Integer resultRouteCount;
    public Integer searchType;
    public String dataID;
    public Integer supportFlag;
    public Integer dtType;

    public SrcConfig (String str) {
        String[] array = str.split(",");
        this.versionNO = array[0];
        this.searchResultType = Integer.getInteger(array[1]);
        this.searchResultFormat = Integer.getInteger(array[2]);
        this.requestTerminal = Integer.getInteger(array[3]);
        this.resultRouteCount = Integer.getInteger(array[4]);
        this.searchType = Integer.getInteger(array[5]);
        this.dataID = array[6];
        this.supportFlag = Integer.getInteger(array[7]);
        this.dtType = Integer.getInteger(array[8]);
    }
}
