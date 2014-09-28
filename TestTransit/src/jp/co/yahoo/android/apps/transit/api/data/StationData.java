// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.maps.GeoPoint;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            DiainfoData, RailDirectionData

public class StationData
    implements Serializable
{

    public static final int TYPE_ADDRESS = 4;
    public static final int TYPE_BUSSTOP = 2;
    public static final int TYPE_ETC = 3;
    public static final int TYPE_NAVI_AIRPORT = 2;
    public static final int TYPE_NAVI_BUSSTOP = 32;
    public static final int TYPE_NAVI_HIWAYBUS = 64;
    public static final int TYPE_NAVI_LANDMARK = 128;
    public static final int TYPE_NAVI_PORT = 8;
    public static final int TYPE_NAVI_SHUTTLEBUS = 4;
    public static final int TYPE_NAVI_STATION = 1;
    public static final int TYPE_NOADDRESS = 5;
    public static final int TYPE_STATION = 1;
    private static final long serialVersionUID = 0xdf4d4c3b4be0ea1cL;
    private String address;
    private boolean bExit;
    private boolean bFacility;
    private DiainfoData diainfo;
    private String governmentCode;
    private String id;
    private String kananame;
    private String lat;
    private String lon;
    private int nNaviType;
    private int nType;
    private String name;
    private ArrayList railDirection;
    private String uid;

    public StationData()
    {
        nType = 1;
        nNaviType = 1;
        bExit = false;
        bFacility = false;
        railDirection = null;
        diainfo = null;
    }

    public String getAddress()
    {
        return address;
    }

    public DiainfoData getDiainfo()
    {
        if (diainfo == null)
        {
            diainfo = new DiainfoData();
        }
        return diainfo;
    }

    public GeoPoint getGeopoint()
    {
        return new GeoPoint((int)(1000000D * Double.parseDouble(lat)), (int)(1000000D * Double.parseDouble(lon)));
    }

    public String getGovernmentCode()
    {
        return governmentCode;
    }

    public String getId()
    {
        return id;
    }

    public String getKananame()
    {
        return kananame;
    }

    public String getLat()
    {
        return lat;
    }

    public String getLon()
    {
        return lon;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList getRailDirection()
    {
        return railDirection;
    }

    public ArrayList getTagertDirection()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = railDirection.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            RailDirectionData raildirectiondata = (RailDirectionData)iterator.next();
            if (raildirectiondata.getRailTartget() == 1)
            {
                arraylist.add(raildirectiondata);
            }
        } while (true);
        return arraylist;
    }

    public int getType()
    {
        return nType;
    }

    public String getUid()
    {
        return uid;
    }

    public int getnNaviType()
    {
        return nNaviType;
    }

    public boolean isExit()
    {
        return bExit;
    }

    public boolean isFacility()
    {
        return bFacility;
    }

    public void setAddress(String s)
    {
        address = s;
    }

    public void setDiainfo(DiainfoData diainfodata)
    {
        diainfo = diainfodata;
    }

    public void setExit(boolean flag)
    {
        bExit = flag;
    }

    public void setFacility(boolean flag)
    {
        bFacility = flag;
    }

    public void setGovernmentCode(String s)
    {
        governmentCode = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setKananame(String s)
    {
        kananame = s;
    }

    public void setLat(String s)
    {
        lat = s;
    }

    public void setLon(String s)
    {
        lon = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setRailDirection(ArrayList arraylist)
    {
        railDirection = arraylist;
    }

    public void setType(int i)
    {
        nType = i;
    }

    public void setUid(String s)
    {
        uid = s;
    }

    public void setnNaviType(int i)
    {
        nNaviType = i;
        if (nNaviType == 1)
        {
            setType(1);
            return;
        }
        if (nNaviType == 32 || nNaviType == 4 || nNaviType == 64)
        {
            setType(2);
            return;
        } else
        {
            setType(3);
            return;
        }
    }

    public String toString()
    {
        return name;
    }
}
