// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.location.Address;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpGet, YLog, YNumberUtils

public class YGeocoder
{
    public static final class SearchType extends Enum
    {

        private static final SearchType $VALUES[];
        public static final SearchType Contents;
        public static final SearchType Geo;

        public static SearchType valueOf(String s)
        {
            return (SearchType)Enum.valueOf(jp/co/yahoo/android/common/YGeocoder$SearchType, s);
        }

        public static SearchType[] values()
        {
            return (SearchType[])$VALUES.clone();
        }

        static 
        {
            Contents = new SearchType("Contents", 0);
            Geo = new SearchType("Geo", 1);
            SearchType asearchtype[] = new SearchType[2];
            asearchtype[0] = Contents;
            asearchtype[1] = Geo;
            $VALUES = asearchtype;
        }

        private SearchType(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int ERROR_NETWORK = 1;
    public static final int ERROR_NONE = 0;
    private static final String TAG = jp/co/yahoo/android/common/YGeocoder.getSimpleName();
    private static final String URL_CONTENTS = "http://contents.search.olp.yahooapis.jp/OpenLocalPlatform/V1/contentsGeoCoder";
    private static final String URL_GEO = "http://geo.search.olp.yahooapis.jp/OpenLocalPlatform/V1/geoCoder";
    private static final String URL_REVERSE = "http://reverse.search.olp.yahooapis.jp/OpenLocalPlatform/V1/reverseGeoCoder";
    private int _lastError;
    private final String mAppid;
    private int mPage;

    public YGeocoder(String s)
    {
        mPage = 1;
        _lastError = 0;
        mAppid = s;
    }

    private List getFromLocationNameByContents(String s, int i)
    {
        YHttpGet yhttpget;
        JSONObject jsonobject;
        Uri uri = Uri.parse("http://contents.search.olp.yahooapis.jp/OpenLocalPlatform/V1/contentsGeoCoder").buildUpon().appendQueryParameter("appid", mAppid).appendQueryParameter("query", s).appendQueryParameter("ei", "UTF-8").appendQueryParameter("category", "landmark").appendQueryParameter("results", Integer.toString(i)).appendQueryParameter("output", "json").build();
        yhttpget = new YHttpGet();
        jsonobject = yhttpget.getJson(uri);
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        Object obj;
        _lastError = 1;
        if (yhttpget.getResponse() != null)
        {
            YLog.e(TAG, yhttpget.getResponse().getStatusLine().toString());
        } else
        {
            YLog.e(TAG, "http error: maybe netowork unreachable");
        }
        obj = null;
_L6:
        return ((List) (obj));
_L2:
        obj = new ArrayList();
        int j;
        JSONArray jsonarray;
        int k;
        String as[];
        double d;
        double d1;
        Address address;
        try
        {
            j = jsonobject.getJSONObject("ResultInfo").getInt("Count");
        }
        catch (JSONException jsonexception)
        {
            YLog.e(TAG, jsonexception.getMessage());
            return ((List) (obj));
        }
        if (j <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonarray = jsonobject.getJSONArray("Feature");
        k = 0;
_L4:
        if (k >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        as = jsonarray.getJSONObject(k).getJSONObject("Geometry").getString("Coordinates").split(",");
        d = YNumberUtils.parseFloat(as[0], 0.0F);
        d1 = YNumberUtils.parseFloat(as[1], 0.0F);
        address = new Address(Locale.JAPANESE);
        address.setLongitude(d);
        address.setLatitude(d1);
        ((List) (obj)).add(address);
        k++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    private List getFromLocationNameByGeo(String s, int i)
    {
        YHttpGet yhttpget;
        JSONObject jsonobject;
        Uri uri = Uri.parse("http://geo.search.olp.yahooapis.jp/OpenLocalPlatform/V1/geoCoder").buildUpon().appendQueryParameter("appid", mAppid).appendQueryParameter("query", s).appendQueryParameter("ei", "UTF-8").appendQueryParameter("results", Integer.toString(i)).appendQueryParameter("page", Integer.toString(mPage)).appendQueryParameter("output", "json").build();
        yhttpget = new YHttpGet();
        jsonobject = yhttpget.getJson(uri);
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        Object obj;
        _lastError = 1;
        if (yhttpget.getResponse() != null)
        {
            YLog.e(TAG, yhttpget.getResponse().getStatusLine().toString());
        } else
        {
            YLog.e(TAG, "http error: maybe netowork unreachable");
        }
        obj = null;
_L6:
        return ((List) (obj));
_L2:
        obj = new ArrayList();
        int j;
        JSONArray jsonarray;
        int k;
        JSONObject jsonobject1;
        String s1;
        String as[];
        double d;
        double d1;
        Address address;
        try
        {
            j = jsonobject.getJSONObject("ResultInfo").getInt("Count");
        }
        catch (JSONException jsonexception)
        {
            YLog.e(TAG, jsonexception.getMessage());
            return ((List) (obj));
        }
        if (j <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonarray = jsonobject.getJSONArray("Feature");
        k = 0;
_L4:
        if (k >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonobject1 = jsonarray.getJSONObject(k);
        s1 = jsonobject1.getJSONObject("Property").getString("Address");
        as = jsonobject1.getJSONObject("Geometry").getString("Coordinates").split(",");
        d = YNumberUtils.parseFloat(as[0], 0.0F);
        d1 = YNumberUtils.parseFloat(as[1], 0.0F);
        address = new Address(Locale.JAPANESE);
        address.setAddressLine(0, s1);
        address.setLongitude(d);
        address.setLatitude(d1);
        ((List) (obj)).add(address);
        k++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public List getFromLocation(double d, double d1)
    {
        YHttpGet yhttpget;
        JSONObject jsonobject;
        Uri uri = Uri.parse("http://reverse.search.olp.yahooapis.jp/OpenLocalPlatform/V1/reverseGeoCoder").buildUpon().appendQueryParameter("appid", mAppid).appendQueryParameter("lat", Double.toString(d)).appendQueryParameter("lon", Double.toString(d1)).appendQueryParameter("output", "json").build();
        yhttpget = new YHttpGet();
        jsonobject = yhttpget.getJson(uri);
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        Object obj;
        _lastError = 1;
        if (yhttpget.getResponse() != null)
        {
            YLog.e(TAG, yhttpget.getResponse().getStatusLine().toString());
        } else
        {
            YLog.e(TAG, "http error: maybe netowork unreachable");
        }
        obj = null;
_L6:
        return ((List) (obj));
_L2:
        obj = new ArrayList();
        int i;
        JSONArray jsonarray;
        int j;
        String s;
        Address address;
        try
        {
            i = jsonobject.getJSONObject("ResultInfo").getInt("Count");
        }
        catch (JSONException jsonexception)
        {
            YLog.e(TAG, jsonexception.getMessage());
            return ((List) (obj));
        }
        if (i <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonarray = jsonobject.getJSONArray("Feature");
        j = 0;
_L4:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        s = jsonarray.getJSONObject(j).getJSONObject("Property").getString("Address");
        address = new Address(Locale.JAPANESE);
        address.setAddressLine(0, s);
        address.setLongitude(d1);
        address.setLatitude(d);
        ((List) (obj)).add(address);
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public List getFromLocationName(String s, int i)
    {
        return getFromLocationNameByContents(s, i);
    }

    public List getFromLocationName(String s, int i, SearchType searchtype)
    {
        static class _cls1
        {

            static final int $SwitchMap$jp$co$yahoo$android$common$YGeocoder$SearchType[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$common$YGeocoder$SearchType = new int[SearchType.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$common$YGeocoder$SearchType[SearchType.Contents.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$common$YGeocoder$SearchType[SearchType.Geo.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.jp.co.yahoo.android.common.YGeocoder.SearchType[searchtype.ordinal()])
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            return getFromLocationNameByContents(s, i);

        case 2: // '\002'
            return getFromLocationNameByGeo(s, i);
        }
    }

    public int getLastError()
    {
        return _lastError;
    }

    public YGeocoder setPage(int i)
    {
        mPage = i;
        return this;
    }

}
