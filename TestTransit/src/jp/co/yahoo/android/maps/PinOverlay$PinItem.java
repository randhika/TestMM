// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.drawable.Drawable;

// Referenced classes of package jp.co.yahoo.android.maps:
//            PinOverlay, GeoPoint

static class mdata
{

    Drawable marker;
    final Object mdata;
    final String mess;
    final int mflg;
    final GeoPoint point;
    final String snippet;
    final String title;

    (GeoPoint geopoint, String s, String s1, String s2, int i, Object obj)
    {
        point = geopoint;
        title = s;
        snippet = s1;
        mess = s2;
        mflg = i;
        mdata = obj;
    }
}
