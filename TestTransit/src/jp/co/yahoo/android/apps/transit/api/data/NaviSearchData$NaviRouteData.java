// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            NaviSearchData

public static class edges
    implements Serializable
{

    private static final long serialVersionUID = 0x2eefba9f82ab1a04L;
    public String Teiki1;
    public String Teiki3;
    public String Teiki6;
    public boolean cheap;
    public int distance;
    public boolean easy;
    public ArrayList edgeExpPrice;
    public ArrayList edgePrice;
    public ArrayList edges;
    public String farePrice;
    public boolean fast;
    public String goalTime;
    public boolean previousTaxFareTeiki1;
    public boolean previousTaxFareTeiki3;
    public boolean previousTaxFareTeiki6;
    public String startTime;
    public ArrayList teikiDetials;
    public String totalExpPrice;
    public boolean totalPreviousTaxFare;
    public String totalPrice;
    public int totaldistance;
    public int totaltime;
    public int transfercount;
    public ArrayList types;

    public ()
    {
        totaltime = 0;
        totaldistance = 0;
        teikiDetials = null;
        cheap = false;
        easy = false;
        fast = false;
        distance = 0;
        transfercount = 0;
        types = null;
        edges = null;
    }
}
