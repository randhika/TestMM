// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.maps.GeoPoint;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            NaviSearch

public class NaviRenderingSearch extends NaviSearch
{

    public static GeoPoint routeinfo[] = null;

    public NaviRenderingSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setParameter("mode", "rendering");
    }

    protected void analyzeDictionaly(JSONObject jsonobject)
    {
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        ArrayList arraylist;
        int i;
        if (jsonarray == null)
        {
            return;
        }
        arraylist = new ArrayList();
        i = 0;
_L7:
        if (i >= jsonarray.length()) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject = jsonarray.optJSONObject(i);
        if (jsonobject != null) goto _L4; else goto _L3
_L4:
        String as[] = jsonobject.optJSONObject("Geometry").optString("Coordinates").split("\\s");
        int j = 0;
_L5:
        if (j >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        String as1[] = as[j].split(",");
        if (as1.length > 1)
        {
            int k = TransitUtil.getLatLngInt(as1[0]);
            arraylist.add(new GeoPoint(TransitUtil.getLatLngInt(as1[1]), k));
        }
        j++;
        if (true) goto _L5; else goto _L3
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L3
_L2:
        routeinfo = (GeoPoint[])arraylist.toArray(new GeoPoint[0]);
        return;
_L3:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public GeoPoint[] getRoute()
    {
        return routeinfo;
    }

    public void setCondition(ConditionData conditiondata)
    {
        super.setCondition(conditiondata);
        if (conditiondata.resultId != -1)
        {
            setStart(1 + conditiondata.resultId);
        }
    }

}
