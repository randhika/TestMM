package com.xinlukou.metroman.engine;

public class SrcUNO {
    public String UNO;
    public String Range;
    public String Type;
    public String Simplified;
    public String Traditional;
    public String Japanese;
    public String English;
    public String Pinyin;
    public String PY;
    public String Latitude;
    public String Longitude;
    public String PointX;
    public String PointY;
    public String WikiZH;
    public String WikiJA;
    public String WikiEN;

    public SrcUNO (String str) {
        String[] array = str.split(",");
        this.UNO = array[0];
        this.Range = array[1];
        this.Type = array[2];
        this.Simplified = array[3];
        this.Traditional = array[4];
        this.Japanese = array[5];
        this.English = array[6];
        this.Pinyin = array[7];
        this.PY = array[8];
        this.Latitude = array[9];
        this.Longitude = array[10];
        this.PointX = array[11];
        this.PointY = array[12];
        this.WikiZH = array[13];
        this.WikiJA = array[14];
        this.WikiEN = array[15];
    }
}
