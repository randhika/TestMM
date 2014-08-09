package com.xinlukou.metroman.engine;

public class SrcConfig {
    public String VersionNO;
    public Integer SearchResultType;
    public Integer SearchResultFormat;
    public Integer RequestTerminal;
    public Integer ResultRouteCount;
    public Integer SearchType;
    public String DataID;
    public Integer SupportFlag;
    public Integer DTType;

    public SrcConfig (String str) {
        String[] array = str.split(",");
        this.VersionNO = array[0];
        this.SearchResultType = Integer.getInteger(array[1]);
        this.SearchResultFormat = Integer.getInteger(array[2]);
        this.RequestTerminal = Integer.getInteger(array[3]);
        this.ResultRouteCount = Integer.getInteger(array[4]);
        this.SearchType = Integer.getInteger(array[5]);
        this.DataID = array[6];
        this.SupportFlag = Integer.getInteger(array[7]);
        this.DTType = Integer.getInteger(array[8]);
    }
}
