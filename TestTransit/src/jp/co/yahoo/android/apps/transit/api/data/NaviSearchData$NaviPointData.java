// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            NaviSearchData

public static class 
    implements Serializable
{

    private static final long serialVersionUID = 0xa0674391d24d9c7dL;
    public int areaCode;
    public String lat;
    public String lon;
    public String nearStLat;
    public String nearStLon;
    public int position;
    public String shortAddress;
    public String stationCode;
    public String stationName;
    public String target;
    public int type;

    public ()
    {
    }
}
