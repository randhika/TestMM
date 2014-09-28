// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase

public class RevGeocoder extends YolpApiBase
{

    private Bundle results;

    public RevGeocoder(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setUri(getContext().getString(0x7f0d004b));
        setParameter("output", "json");
        setParameter("appid", getContext().getString(0x7f0d004c));
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        if (jsonarray == null || jsonarray.length() < 1)
        {
            return;
        }
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
        results = new Bundle();
        results.putStringArray(getContext().getString(0x7f0d01a2), as);
    }

    public Bundle getResults()
    {
        return results;
    }
}
