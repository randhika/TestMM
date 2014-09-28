// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import jp.co.yahoo.android.maps.GeoPoint;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorData

public static class _cls9
{

    public GeoPoint geoPoints[];

    public String toString()
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer();
        stringbuffer.append("Shape={");
        stringbuffer.append("\n");
        if (geoPoints == null)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        stringbuffer.append((new StringBuilder("GeoPoint[].length=")).append(geoPoints.length).toString());
        stringbuffer.append("\n");
        stringbuffer.append("GeoPoint[]={");
        stringbuffer.append("\n");
        i = 0;
_L3:
        if (i < geoPoints.length) goto _L2; else goto _L1
_L1:
        stringbuffer.append("}");
        stringbuffer.append("\n");
_L4:
        stringbuffer.append("}");
        return stringbuffer.toString();
_L2:
        if (geoPoints[i] != null)
        {
            if (i != 0)
            {
                stringbuffer.append(",");
            }
            stringbuffer.append(geoPoints[i].getLatitudeE6());
            stringbuffer.append(",");
            stringbuffer.append(geoPoints[i].getLongitudeE6());
        }
        i++;
          goto _L3
        stringbuffer.append("GeoPoint[]=null");
        stringbuffer.append("\n");
          goto _L4
    }

    public _cls9()
    {
    }
}
