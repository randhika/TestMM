package com.xinlukou.metroman.engine;

import java.util.List;

public class DataManage {
    public List<SrcConfig> ConfigList = null;
    public List<String> HolidayList = null;
    public List<SrcUNO> UNOList = null;
    public List<SrcFare> FareList = null;
    public List<Station> StationList = null;
    public List<Line> LineList = null;
    public List<Way> WayList = null;
    public List<Transfer> TransferFromList = null;
    public List<Transfer> TransferToList = null;
    public List<Timetable> WeekdayList = null;
    public List<Timetable> WeekendList = null;

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
