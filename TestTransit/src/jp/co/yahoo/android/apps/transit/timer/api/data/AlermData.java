// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api.data:
//            StationData

public class AlermData
    implements Serializable
{

    public static int TYPE_ALERT = 0;
    public static int TYPE_START = 0;
    public static int TYPE_UPDATE = 0;
    private static final long serialVersionUID = 0x5a3c356fe0952f76L;
    private boolean bSetting;
    private boolean bVibration;
    private int nAlermLength;
    private int nAlermVolume;
    private int nCountdownTime;
    private int nId;
    private int nStartTime;
    private int nTimetable;
    private int nType;
    private String sLine;
    private String sRepeat;
    private String sSound;
    private String sSoundUri;
    private StationData station;

    public AlermData()
    {
        nId = -1;
        sLine = "";
        nStartTime = -1;
        nCountdownTime = -1;
        sRepeat = "0";
        nType = 1;
        bSetting = true;
        bVibration = true;
        sSound = null;
        sSoundUri = null;
        nAlermLength = 5;
        nAlermVolume = 50;
    }

    public int getAlermLength()
    {
        return nAlermLength;
    }

    public int getAlermVolume()
    {
        return nAlermVolume;
    }

    public int getCountdownTime()
    {
        return nCountdownTime;
    }

    public int getId()
    {
        return nId;
    }

    public String getLine()
    {
        return sLine;
    }

    public String getRepeat()
    {
        return sRepeat;
    }

    public String getSound()
    {
        return sSound;
    }

    public String getSoundUri()
    {
        return sSoundUri;
    }

    public int getStartTime()
    {
        return nStartTime;
    }

    public StationData getStation()
    {
        return station;
    }

    public int getTimetableId()
    {
        return nTimetable;
    }

    public int getType()
    {
        return nType;
    }

    public boolean isSetting()
    {
        return bSetting;
    }

    public boolean isVibration()
    {
        return bVibration;
    }

    public void setAlermLength(int i)
    {
        nAlermLength = i;
    }

    public void setAlermVolume(int i)
    {
        nAlermVolume = i;
    }

    public void setCountdownTime(int i)
    {
        nCountdownTime = i;
    }

    public void setId(int i)
    {
        nId = i;
    }

    public void setLine(String s)
    {
        sLine = s;
    }

    public void setRepeat(String s)
    {
        sRepeat = s;
    }

    public void setSetting(boolean flag)
    {
        bSetting = flag;
    }

    public void setSound(String s)
    {
        sSound = s;
    }

    public void setSoundUri(String s)
    {
        sSoundUri = s;
    }

    public void setStartTime(int i)
    {
        nStartTime = i;
    }

    public void setStation(StationData stationdata)
    {
        station = stationdata;
    }

    public void setTimetableId(int i)
    {
        nTimetable = i;
    }

    public void setType(int i)
    {
        nType = i;
    }

    public void setVibration(boolean flag)
    {
        bVibration = flag;
    }

    static 
    {
        TYPE_START = 1;
        TYPE_ALERT = 2;
        TYPE_UPDATE = 3;
    }
}
