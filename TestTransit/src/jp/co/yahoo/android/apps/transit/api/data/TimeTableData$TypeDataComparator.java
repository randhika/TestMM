// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.util.Comparator;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            TimeTableData

public static class 
    implements Comparator
{

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((compare)obj, (compare)obj1);
    }

    public int compare(compare compare1, compare compare2)
    {
        return Integer.parseInt(compare1.compare) >= Integer.parseInt(compare2.compare) ? 1 : -1;
    }

    public ()
    {
    }
}
