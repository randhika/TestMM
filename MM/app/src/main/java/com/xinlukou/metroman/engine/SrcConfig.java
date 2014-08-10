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
        String[] array = str.split(",", -1);
        this.versionNO = array[0];
        this.searchResultType = Integer.parseInt(array[1]);
        this.searchResultFormat = Integer.parseInt(array[2]);
        this.requestTerminal = Integer.parseInt(array[3]);
        this.resultRouteCount = Integer.parseInt(array[4]);
        this.searchType = Integer.parseInt(array[5]);
        this.dataID = array[6];
        this.supportFlag = Integer.parseInt(array[7]);
        this.dtType = Integer.parseInt(array[8]);
    }
}
