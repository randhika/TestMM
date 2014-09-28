// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            NaviSearchData

public static class teiki6
    implements Serializable
{

    private static final long serialVersionUID = 0x311f13c9c4df4427L;
    public int edgeFrom;
    public int edgeTo;
    public boolean previous;
    public String teiki1;
    public String teiki3;
    public String teiki6;

    public ()
    {
        edgeFrom = 0;
        edgeTo = 0;
        previous = false;
        teiki1 = "";
        teiki3 = "";
        teiki6 = "";
    }
}
