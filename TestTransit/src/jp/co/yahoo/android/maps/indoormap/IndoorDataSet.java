// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import java.util.ArrayList;
import jp.co.yahoo.android.maps.viewlayer.Coordinate;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorData

public class IndoorDataSet
{

    private ArrayList dataList;
    public final Coordinate leftUpPoint;
    public final Coordinate point;
    public final Coordinate rightBottomPoint;

    public IndoorDataSet()
    {
        dataList = new ArrayList();
        point = null;
        leftUpPoint = null;
        rightBottomPoint = null;
    }

    public IndoorDataSet(Coordinate coordinate, Coordinate coordinate1, Coordinate coordinate2)
    {
        dataList = new ArrayList();
        point = coordinate;
        leftUpPoint = coordinate1;
        rightBottomPoint = coordinate2;
    }

    public void addIndoorData(IndoorData indoordata)
    {
        dataList.add(indoordata);
    }

    public void clear()
    {
        dataList.clear();
    }

    public IndoorData getIndoorData(int i)
    {
        return (IndoorData)dataList.get(i);
    }

    public int getIndoorDataLength()
    {
        return dataList.size();
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("IndoorDataSet");
        stringbuffer.append("\n");
        stringbuffer.append((new StringBuilder("dataListSize=")).append(dataList.size()).toString());
        stringbuffer.append("\n");
        int i = 0;
        do
        {
            if (i >= dataList.size())
            {
                return stringbuffer.toString();
            }
            stringbuffer.append(((IndoorData)dataList.get(i)).toString());
            stringbuffer.append("\n");
            i++;
        } while (true);
    }
}
