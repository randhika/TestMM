// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

public class TimeTableItemData
    implements Serializable
{

    private static final long serialVersionUID = 0x4d9ee6fabd94b64aL;
    private boolean bExtraLine;
    private boolean bFirstStation;
    private String destmark;
    private String desttype;
    private int minute;
    private String strHHMM;
    private int trafific;
    private String trainmark;
    private String traintype;

    public TimeTableItemData()
    {
    }

    public String getDestmark()
    {
        return destmark;
    }

    public String getDesttype()
    {
        return desttype;
    }

    public String getHourMin()
    {
        return strHHMM;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getTrafific()
    {
        return trafific;
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

    public void setDestmark(String s)
    {
        destmark = s;
    }

    public void setDesttype(String s)
    {
        desttype = s;
    }

    public void setExtraLine(boolean flag)
    {
        bExtraLine = flag;
    }

    public void setFirstStation(boolean flag)
    {
        bFirstStation = flag;
    }

    public void setHourMin(String s)
    {
        strHHMM = s;
    }

    public void setMinute(int i)
    {
        minute = i;
    }

    public void setTrafific(int i)
    {
        trafific = i;
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
