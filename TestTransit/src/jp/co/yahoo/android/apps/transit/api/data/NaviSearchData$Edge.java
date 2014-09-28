// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            NaviSearchData

public static class carType
    implements Serializable
{

    private static final long serialVersionUID = 0xed5768779dfc0098L;
    public String AirportDPLinkSP;
    public String AirportDPLinkSPDisp;
    public String AirportTicketLinkSP;
    public ArrayList RidingPosition;
    public ArrayList StopStations;
    public String arrivalDatetime;
    public String arrivalTrackNumber;
    public int attentionId;
    public String attentionText;
    public ArrayList carType;
    public int color;
    public String departureDatetime;
    public String departureTrackNumber;
    public int distance;
    public int edgeid;
    public int fromState;
    public String goalPointTarget;
    public int passStCount;
    public int price;
    public int priceTo;
    public String railDispName;
    public String railname;
    public String startPointTarget;
    public int time;
    public String timeType;
    public int toState;
    public int traffic;
    public String trainId;

    public ()
    {
        edgeid = 0;
        passStCount = 0;
        time = 0;
        traffic = 1;
        fromState = 0;
        toState = 0;
        color = 0;
        distance = 0;
        StopStations = null;
        RidingPosition = null;
        carType = null;
    }
}
