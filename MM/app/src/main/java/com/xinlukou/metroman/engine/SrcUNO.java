package com.xinlukou.metroman.engine;

public class SrcUNO {
    public String uno;
    public String range;
    public String type;
    public String simplified;
    public String traditional;
    public String japanese;
    public String english;
    public String pinyin;
    public String py;
    public String latitude;
    public String longitude;
    public String pointX;
    public String pointY;
    public String wikiZH;
    public String wikiJA;
    public String wikiEN;

    public SrcUNO (String str) {
        String[] array = str.split(",");
        this.uno = array[0];
        this.range = array[1];
        this.type = array[2];
        this.simplified = array[3];
        this.traditional = array[4];
        this.japanese = array[5];
        this.english = array[6];
        this.pinyin = array[7];
        this.py = array[8];
        this.latitude = array[9];
        this.longitude = array[10];
        this.pointX = array[11];
        this.pointY = array[12];
        this.wikiZH = array[13];
        this.wikiJA = array[14];
        this.wikiEN = array[15];
    }
}
