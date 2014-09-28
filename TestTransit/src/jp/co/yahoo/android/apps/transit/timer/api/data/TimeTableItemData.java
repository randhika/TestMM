// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;

public class TimeTableItemData
    implements Serializable
{

    private static final long serialVersionUID = 0x4d9ee6fabd94b64aL;
    private boolean bExtraLine;
    private boolean bFirstStation;
    private boolean bLastStation;
    private boolean bStartStation;
    private String destinfo;
    private String destmark;
    private String desttyep;
    private int hour;
    private int minute;
    private int nIndex;
    private int trafific;
    private String traininfo;
    private String trainmark;
    private String traintype;

    public TimeTableItemData()
    {
        nIndex = 0;
    }

    public String getDestinfo()
    {
        return destinfo;
    }

    public String getDestmark()
    {
        return destmark;
    }

    public String getDesttype()
    {
        return desttyep;
    }

    public int getHour()
    {
        return hour;
    }

    public int getIndex()
    {
        return nIndex;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getTime()
    {
        return 60 * hour + minute;
    }

    public int getTrafific()
    {
        return trafific;
    }

    public String getTraininfo()
    {
        return traininfo;
    }

    public String getTrainmark()
    {
        return trainmark;
    }

    public String getTraintype()
    {
        return traintype;
    }

    public boolean isExtraLine()
    {
        return bExtraLine;
    }

    public boolean isFirstStation()
    {
        return bFirstStation;
    }

    public boolean isLastStation()
    {
        return bLastStation;
    }

    public boolean isStartStation()
    {
        return bStartStation;
    }

    public void setDestinfo(String s)
    {
        destinfo = s;
    }

    public void setDestmark(String s)
    {
        destmark = s;
    }

    public void setDesttyep(String s)
    {
        desttyep = s;
    }

    public void setExtraLine(boolean flag)
    {
        bExtraLine = flag;
    }

    public void setFirstStation(boolean flag)
    {
        bFirstStation = flag;
    }

    public void setHour(int i)
    {
        hour = i;
    }

    public void setIndex(int i)
    {
        nIndex = i;
    }

    public void setLastStation(boolean flag)
    {
        bLastStation = flag;
    }

    public void setMinute(int i)
    {
        minute = i;
    }

    public void setStartStation(boolean flag)
    {
        bStartStation = flag;
    }

    public void setTrafific(int i)
    {
        trafific = i;
    }

    public void setTraininfo(String s)
    {
        traininfo = s;
    }

    public void setTrainmark(String s)
    {
        trainmark = s;
    }

    public void setTraintype(String s)
    {
        traintype = s;
    }
}
