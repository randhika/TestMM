// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;

public class StationData
    implements Serializable
{

    public static final int SETTYPE_GOAL = 2;
    public static final int SETTYPE_HOME = 1;
    public static final int SETTYPE_TEMP = 3;
    public static final int TYPE_BUSSTOP = 2;
    public static final int TYPE_ETC = 3;
    public static final int TYPE_NAVI_AIRPORT = 2;
    public static final int TYPE_NAVI_BUSSTOP = 32;
    public static final int TYPE_NAVI_HIWAYBUS = 64;
    public static final int TYPE_NAVI_LANDMARK = 128;
    public static final int TYPE_NAVI_PORT = 8;
    public static final int TYPE_NAVI_SHUTTLEBUS = 4;
    public static final int TYPE_NAVI_STATION = 1;
    public static final int TYPE_STATION = 1;
    private static final long serialVersionUID = 0x11e9bdd9aa81a365L;
    private String address;
    private Bundle aryTimetable;
    private boolean bSetting;
    private String dirid;
    private String dirname;
    private String governmentCode;
    private String id;
    private String lat;
    private String lon;
    private int nNaviType;
    private int nSettingType;
    private int nType;
    private String name;
    private ArrayList railDirection;
    private String railid;
    private String railname;
    private String sUpdateDate;
    private String stationId;

    public StationData()
    {
        nType = 1;
        nNaviType = 1;
        sUpdateDate = "";
        bSetting = false;
        aryTimetable = null;
        railDirection = null;
        nSettingType = 1;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDirid()
    {
        return dirid;
    }

    public String getDirname()
    {
        return dirname;
    }

    public String getGovernmentCode()
    {
        return governmentCode;
    }

    public String getId()
    {
        return id;
    }

    public String getLat()
    {
        return lat;
    }

    public String getLon()
    {
        return lon;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList getRailDirection()
    {
        return railDirection;
    }

    public String getRailid()
    {
        return railid;
    }

    public String getRailname()
    {
        return railname;
    }

    public int getSettingType()
    {
        return nSettingType;
    }

    public String getStationId()
    {
        return stationId;
    }

    public Bundle getTimetable()
    {
        return aryTimetable;
    }

    public int getType()
    {
        return nType;
    }

    public String getUpdateDate()
    {
        return sUpdateDate;
    }

    public int getnNaviType()
    {
        return nNaviType;
    }

    public boolean isSetting()
    {
        return bSetting;
    }

    public void setAddress(String s)
    {
        address = s;
    }

    public void setDirid(String s)
    {
        dirid = s;
    }

    public void setDirname(String s)
    {
        dirname = s;
    }

    public void setGovernmentCode(String s)
    {
        governmentCode = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setLat(String s)
    {
        lat = s;
    }

    public void setLon(String s)
    {
        lon = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setRailDirection(ArrayList arraylist)
    {
        railDirection = arraylist;
    }

    public void setRailid(String s)
    {
        railid = s;
    }

    public void setRailname(String s)
    {
        railname = s;
    }

    public void setSetting(boolean flag)
    {
        bSetting = flag;
    }

    public void setSettingType(int i)
    {
        nSettingType = i;
    }

    public void setStationId(String s)
    {
        stationId = s;
    }

    public void setTimetable(Bundle bundle)
    {
        aryTimetable = bundle;
    }

    public void setType(int i)
    {
        nType = i;
    }

    public void setUpdateDate(String s)
    {
        sUpdateDate = s;
    }

    public void setnNaviType(int i)
    {
        nNaviType = i;
        if (nNaviType == 1)
        {
            setType(1);
            return;
        }
        if (nNaviType == 32 || nNaviType == 4 || nNaviType == 64)
        {
            setType(2);
            return;
        } else
        {
            setType(3);
            return;
        }
    }

    public String toString()
    {
        return name;
    }
}
