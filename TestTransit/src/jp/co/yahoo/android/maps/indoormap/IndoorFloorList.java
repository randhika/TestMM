// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap;

import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap:
//            IndoorFloor

class IndoorFloorList
{

    private ArrayList list;

    IndoorFloorList()
    {
        list = new ArrayList();
    }

    void addFloor(int i, int j, String s)
    {
        list.add(new IndoorFloor(i, j, s));
    }

    String getStyleString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if (i >= list.size())
            {
                return stringbuffer.toString();
            }
            IndoorFloor indoorfloor = (IndoorFloor)list.get(i);
            if (i != 0)
            {
                stringbuffer.append("|");
            }
            stringbuffer.append((new StringBuilder("id.")).append(indoorfloor.indoorId).append(".").append(indoorfloor.floorId).append(":").append(indoorfloor.mapType).toString());
            i++;
        } while (true);
    }

    void removeAllFloors()
    {
        list.clear();
    }

    void removeFloor(int i, int j)
    {
        int k = 0;
        do
        {
            if (k >= list.size())
            {
                return;
            }
            IndoorFloor indoorfloor = (IndoorFloor)list.get(k);
            if (i == indoorfloor.indoorId && j == indoorfloor.floorId)
            {
                list.remove(k);
                return;
            }
            k++;
        } while (true);
    }

    void removeFloors(int i)
    {
        int j = 0;
        do
        {
            if (j >= list.size())
            {
                return;
            }
            if (i == ((IndoorFloor)list.get(j)).indoorId)
            {
                list.remove(j);
                j--;
            }
            j++;
        } while (true);
    }

    int size()
    {
        return list.size();
    }
}
