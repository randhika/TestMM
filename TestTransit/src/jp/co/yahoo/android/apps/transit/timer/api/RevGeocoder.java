// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;
import jp.co.yahoo.android.yolp.common.YolpApiBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RevGeocoder extends YolpApiBase
{

    public RevGeocoder(Context context)
    {
        super(context);
        setUri(context.getString(0x7f0d004b));
        setAppid(context.getString(0x7f0d004c));
    }

    protected void analyze()
    {
        JSONArray jsonarray = getFeature();
        String as[] = new String[jsonarray.length()];
        int i = 0;
        while (i < jsonarray.length()) 
        {
            try
            {
                as[i] = jsonarray.getJSONObject(i).getJSONObject("Property").getString("Address");
            }
            catch (JSONException jsonexception) { }
            i++;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArray(context.getString(0x7f0d01a2), as);
        setResult(bundle);
    }

    public void setDatum(String s)
    {
        param.put("datum", s);
    }

    public void setLat(String s)
    {
        param.put("lat", s);
    }

    public void setLon(String s)
    {
        param.put("lon", s);
    }
}
