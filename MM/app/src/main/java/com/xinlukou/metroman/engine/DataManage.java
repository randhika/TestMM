package com.xinlukou.metroman.engine;

import java.util.List;

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

    private static void LoadConfig() {

    }

    private static void LoadHoliday() {

    }

    private static void LoadUNO() {

    }

    private static void LoadFare() {

    }

    private static void LoadStation() {

    }

    private static void LoadLine() {

    }

    private static void LoadWay() {

    }

    private static void LoadLink() {

    }

    private static void LoadTransferFrom() {

    }

    private static void LoadTransferTo() {

    }

    private static void LoadWeekday() {

    }

    private static void LoadWeekend() {

    }
}
