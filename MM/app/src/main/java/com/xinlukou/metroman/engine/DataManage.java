package com.xinlukou.metroman.engine;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.text.TextUtils;

import org.apache.http.util.EncodingUtils;

public class DataManage {
    public static AssetManager assetManager = null;
    public static List<SrcConfig> configList = null;
    public static List<String> holidayList = null;
    public static List<SrcUNO> unoList = null;
    public static List<SrcFare> fareList = null;
    public static List<Station> stationList = null;
    public static List<Line> lineList = null;
    public static List<Way> wayList = null;
    public static List<Link> linkList = null;
    public static List<Transfer> transferFromList = null;
    public static List<Transfer> transferToList = null;
    public static List<Timetable> weekdayList = null;
    public static List<Timetable> weekendList = null;

    public static void initData() {
        loadConfig();
        loadHoliday();
        loadUNO();
        loadFare();
        loadStation();
        loadLine();
        loadWay();
        loadLink();
        loadTransferFrom();
        loadTransferTo();
        loadWeekday();
        loadWeekend();
    }

    public static void releaseData() {
        configList = null;
        holidayList = null;
        unoList = null;
        fareList = null;
        stationList = null;
        lineList = null;
        wayList = null;
        linkList = null;
        transferFromList = null;
        transferToList = null;
        weekdayList = null;
        weekendList = null;
        configList = null;
    }

    private static String getPathByCity(String fileName) {
        //TODO
        return String.format("data/%s/%s.csv", "sh", fileName);
    }

    private static String[] getCsvFileContents(String fileName) {
        String[] rowArray = null;
        try {
            String path = getPathByCity(fileName);
            InputStream is = assetManager.open(path);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            String content = EncodingUtils.getString(bytes, "utf-8");
            rowArray = content.split("\r\n");
        } catch (Exception ex) {
        }
        return rowArray;
    }

    private static void loadConfig() {
        if(configList != null && configList.size() > 0) return;
        String[] rowArray = getCsvFileContents("config");
        configList = new ArrayList<SrcConfig>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            configList.add(new SrcConfig(str));
        }
    }

    private static void loadHoliday() {
        if(holidayList != null && holidayList.size() > 0) return;
        String[] rowArray = getCsvFileContents("holiday");
        holidayList = new ArrayList<String>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            holidayList.add(str);
        }
    }

    private static void loadUNO() {
        if(unoList != null && unoList.size() > 0) return;
        String[] rowArray = getCsvFileContents("uno");
        unoList = new ArrayList<SrcUNO>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            unoList.add(new SrcUNO(str));
        }
    }

    private static void loadFare() {
        if(fareList != null && fareList.size() > 0) return;
        String[] rowArray = getCsvFileContents("fare");
        fareList = new ArrayList<SrcFare>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            fareList.add(new SrcFare(str));
        }
    }

    private static void loadStation() {
        if(stationList != null && stationList.size() > 0) return;
        String[] rowArray = getCsvFileContents("station");
        Integer curIndex = 0;
        stationList = new ArrayList<Station>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            stationList.add(new Station(curIndex, str));
            curIndex++;
        }
    }

    private static void loadLine() {
        if(lineList != null && lineList.size() > 0) return;
        String[] rowArray = getCsvFileContents("line");
        Integer curIndex = 0;
        lineList = new ArrayList<Line>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            lineList.add(new Line(curIndex, str));
            curIndex++;
        }
    }

    private static void loadWay() {
        if(wayList != null && wayList.size() > 0) return;
        String[] rowArray = getCsvFileContents("way");
        Integer curIndex = 0;
        wayList = new ArrayList<Way>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            wayList.add(new Way(curIndex, str));
            curIndex++;
        }
    }

    private static void loadLink() {
        if(linkList != null && linkList.size() > 0) return;
        String[] rowArray = getCsvFileContents("link");
        Integer curIndex = 0;
        linkList = new ArrayList<Link>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            linkList.add(new Link(curIndex, str));
            curIndex++;
        }
    }

    private static void loadTransferFrom() {
        if(transferFromList != null && transferFromList.size() > 0) return;
        String[] rowArray = getCsvFileContents("transferfrom");
        Integer curIndex = 0;
        transferFromList = new ArrayList<Transfer>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            transferFromList.add(new Transfer(curIndex, str));
            curIndex++;
        }
    }

    private static void loadTransferTo() {
        if(transferToList != null && transferToList.size() > 0) return;
        String[] rowArray = getCsvFileContents("transferto");
        Integer curIndex = 0;
        transferToList = new ArrayList<Transfer>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            transferToList.add(new Transfer(curIndex, str));
            curIndex++;
        }
    }

    private static void loadWeekday() {
        if(weekdayList != null && weekdayList.size() > 0) return;
        String[] rowArray = getCsvFileContents("weekday");
        Integer curIndex = 0;
        weekdayList = new ArrayList<Timetable>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            weekdayList.add(new Timetable(curIndex, str));
            curIndex++;
        }
    }

    private static void loadWeekend() {
        if(weekendList != null && weekendList.size() > 0) return;
        String[] rowArray = getCsvFileContents("weekend");
        Integer curIndex = 0;
        weekendList = new ArrayList<Timetable>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            weekendList.add(new Timetable(curIndex, str));
            curIndex++;
        }
    }
}
