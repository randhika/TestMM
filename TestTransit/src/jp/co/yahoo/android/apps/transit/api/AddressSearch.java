// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase

public class AddressSearch extends YolpApiBase
{

    private Bundle objResult;

    public AddressSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        setUri(getContext().getString(0x7f0d0038));
        setParameter("sort", "address");
        setParameter("loco_mode", "false");
        setAppid(getContext().getString(0x7f0d004c));
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        objResult = new Bundle();
        if (jsonarray != null)
        {
            int i = 0;
            while (i < jsonarray.length()) 
            {
                try
                {
                    Bundle bundle = new Bundle();
                    JSONObject jsonobject = jsonarray.optJSONObject(i);
                    bundle.putString("address", jsonobject.optString("Name"));
                    String as[] = jsonobject.optJSONObject("Geometry").optString("Coordinates").split(",");
                    bundle.putString("lon", as[0]);
                    bundle.putString("lat", as[1]);
                    objResult.putBundle(Integer.toString(i), bundle);
                }
                catch (Exception exception) { }
                i++;
            }
        }
    }

    public Bundle getResults()
    {
        return objResult;
    }
}
