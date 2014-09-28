// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.Vector;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            MapYmlLayer

public class MapYmlPackage
{

    private String mId;
    private Vector mLayerList;

    public MapYmlPackage(String s)
    {
        mLayerList = new Vector();
        mId = null;
        mId = s;
    }

    public void addMapYmlLayer(MapYmlLayer mapymllayer)
    {
        mLayerList.add(mapymllayer);
    }

    public MapYmlLayer getMapYmlLayerByScale(int i)
    {
        int j = 0;
_L6:
        if (j < mLayerList.size()) goto _L2; else goto _L1
_L1:
        MapYmlLayer mapymllayer = null;
_L4:
        return mapymllayer;
_L2:
        mapymllayer = (MapYmlLayer)mLayerList.elementAt(j);
        if (mapymllayer.getScale() == i) goto _L4; else goto _L3
_L3:
        j++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public MapYmlLayer getMapYmlLayerMax()
    {
        if (mLayerList.size() > 0)
        {
            return (MapYmlLayer)mLayerList.get(0);
        } else
        {
            return null;
        }
    }

    public MapYmlLayer getMapYmlLayerMin()
    {
        if (mLayerList.size() > 0)
        {
            return (MapYmlLayer)mLayerList.get(-1 + mLayerList.size());
        } else
        {
            return null;
        }
    }
}
