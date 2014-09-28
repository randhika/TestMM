// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;

import java.util.Comparator;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap.data:
//            TileCacheManager

class leCacheFileWrapper
    implements Comparator
{

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((leCacheFileWrapper)obj, (leCacheFileWrapper)obj1);
    }

    public int compare(leCacheFileWrapper lecachefilewrapper, leCacheFileWrapper lecachefilewrapper1)
    {
        return (int)(lecachefilewrapper1.getLastAccessTime() - lecachefilewrapper.getLastAccessTime());
    }

    leCacheFileWrapper()
    {
    }
}
