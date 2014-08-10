package com.xinlukou.metroman.engine;

import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;

public class DataManage {
    public static List<SrcConfig> ConfigList = null;
    public static List<String> HolidayList = null;
    public static List<SrcUNO> UNOList = null;
    public static List<SrcFare> FareList = null;
    public static List<Station> StationList = null;
    public static List<Line> LineList = null;
    public static List<Way> WayList = null;
    public static List<Link> LinkList = null;
    public static List<Transfer> TransferFromList = null;
    public static List<Transfer> TransferToList = null;
    public static List<Timetable> WeekdayList = null;
    public static List<Timetable> WeekendList = null;

    public static void InitData() {
        LoadConfig();
        LoadHoliday();
        LoadUNO();
        LoadFare();
        LoadStation();
        LoadLine();
        LoadWay();
        LoadLink();
        LoadTransferFrom();
        LoadTransferTo();
        LoadWeekday();
        LoadWeekend();
    }

    public static void ReleaseData() {
        ConfigList = null;
        HolidayList = null;
        UNOList = null;
        FareList = null;
        StationList = null;
        LineList = null;
        WayList = null;
        LinkList = null;
        TransferFromList = null;
        TransferToList = null;
        WeekdayList = null;
        WeekendList = null;
        ConfigList = null;
    }

    private static String GetPathByCity(String fileName) {
        //TODO
        return "";
    }

    private static String[] GetCsvFileContents(String fileName) {
        String path = GetPathByCity(fileName);
        //TODO
        String content = "";
        return content.split("\r\n");
    }

    private static void LoadConfig() {
        if(ConfigList != null && ConfigList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("config");
        ConfigList = new ArrayList<SrcConfig>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            ConfigList.add(new SrcConfig(str));
        }
    }

    private static void LoadHoliday() {
        if(HolidayList != null && HolidayList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("holiday");
        HolidayList = new ArrayList<String>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            HolidayList.add(str);
        }
    }

    private static void LoadUNO() {
        if(UNOList != null && UNOList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("uno");
        UNOList = new ArrayList<SrcUNO>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            UNOList.add(new SrcUNO(str));
        }
    }

    private static void LoadFare() {
        if(FareList != null && FareList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("fare");
        FareList = new ArrayList<SrcFare>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            FareList.add(new SrcFare(str));
        }
    }

    private static void LoadStation() {
        if(StationList != null && StationList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("station");
        Integer curIndex = 0;
        StationList = new ArrayList<Station>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            StationList.add(new Station(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadLine() {
        if(LineList != null && LineList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("line");
        Integer curIndex = 0;
        LineList = new ArrayList<Line>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            LineList.add(new Line(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadWay() {
        if(WayList != null && WayList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("way");
        Integer curIndex = 0;
        WayList = new ArrayList<Way>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            WayList.add(new Way(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadLink() {
        if(LinkList != null && LinkList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("link");
        Integer curIndex = 0;
        LinkList = new ArrayList<Link>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            LinkList.add(new Link(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadTransferFrom() {
        if(TransferFromList != null && TransferFromList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("transferfrom");
        Integer curIndex = 0;
        TransferFromList = new ArrayList<Transfer>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            TransferFromList.add(new Transfer(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadTransferTo() {
        if(TransferToList != null && TransferToList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("transferto");
        Integer curIndex = 0;
        TransferToList = new ArrayList<Transfer>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            TransferToList.add(new Transfer(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadWeekday() {
        if(WeekdayList != null && WeekdayList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("weekday");
        Integer curIndex = 0;
        WeekdayList = new ArrayList<Timetable>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            WeekdayList.add(new Timetable(curIndex, str));
            curIndex++;
        }
    }

    private static void LoadWeekend() {
        if(WeekendList != null && WeekendList.size() > 0) return;
        String[] rowArray = GetCsvFileContents("weekend");
        Integer curIndex = 0;
        WeekendList = new ArrayList<Timetable>(rowArray.length);
        for (String str : rowArray) {
            if(TextUtils.isEmpty(str)) continue;
            WeekendList.add(new Timetable(curIndex, str));
            curIndex++;
        }
    }
}
