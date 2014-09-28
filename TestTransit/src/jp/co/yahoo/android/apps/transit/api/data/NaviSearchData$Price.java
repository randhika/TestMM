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

    private static final long serialVersionUID = 0x4e717ee0ed55f81fL;
    public int edgeFrom;
    public int edgeTo;
    public boolean previousTaxFare;
    public String ticketType;
    public String type;
    public String value;

    public ()
    {
    }
}
