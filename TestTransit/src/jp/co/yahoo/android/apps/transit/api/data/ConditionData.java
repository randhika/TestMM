// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;

public class ConditionData
    implements Serializable, Cloneable
{

    private static final long serialVersionUID = 0xc9efa335e8430daL;
    public boolean afterFinal;
    public boolean airplane;
    public int currentPage;
    public int day;
    public boolean express;
    public boolean ferry;
    public String goalAddr;
    public String goalCode;
    public String goalLat;
    public String goalLon;
    public String goalName;
    public boolean highwayBus;
    public int hour;
    public String imakokoName;
    public boolean keyCurrent;
    public boolean localBus;
    public boolean midnightBus;
    public int minite;
    public int month;
    public int mtf;
    public String paramMode;
    public String paramProp;
    public String passtype;
    public int resultId;
    public int searchType;
    public int seatKind;
    public int sort;
    public String startAddr;
    public String startCode;
    public String startLat;
    public String startLon;
    public String startName;
    public boolean superExpress;
    public String ticket;
    public int type;
    public ArrayList viaCode;
    public ArrayList viaName;
    public int walkSpeed;
    public int year;

    public ConditionData()
    {
        type = 99;
        sort = 0;
        walkSpeed = 2;
        seatKind = 1;
        ticket = "normal";
        passtype = "Bussiness";
        superExpress = true;
        express = true;
        airplane = true;
        highwayBus = true;
        localBus = true;
        ferry = true;
        midnightBus = false;
        afterFinal = false;
        searchType = 1;
        mtf = -1;
        resultId = -1;
    }

    public Object clone()
    {
        Object obj;
        try
        {
            obj = super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError(clonenotsupportedexception.toString());
        }
        return obj;
    }
}
