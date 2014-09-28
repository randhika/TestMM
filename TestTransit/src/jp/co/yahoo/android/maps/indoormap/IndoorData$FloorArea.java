// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;


// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorData

public static class 
{

    public String areaName;
    public int floorId;

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("FloorArea={");
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("floorId=")).append(floorId).toString());
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("areaName=")).append(areaName).toString());
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public ()
    {
    }
}
