// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.net.Uri;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HolidaySearch extends ApiBase
{

    String sExpired;
    String sHoliday;

    public HolidaySearch(Context context)
    {
        super(context);
        sHoliday = null;
        sExpired = null;
        setUri(context.getString(0x7f0d054d));
    }

    protected void analyze(String s)
    {
        if (!CountdownUtil.isEmpty(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JSONArray jsonarray;
        try
        {
            JSONObject jsonobject = new JSONObject(s.substring(s.indexOf("{"), 1 + s.lastIndexOf("}")));
            sExpired = jsonobject.optString("expired");
            jsonarray = jsonobject.optJSONArray("holidays");
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return;
        }
        if (jsonarray == null) goto _L1; else goto _L3
_L3:
        if (jsonarray.length() <= 0) goto _L1; else goto _L4
_L4:
        sHoliday = jsonarray.toString();
        return;
    }

    public String getExpired()
    {
        return sExpired;
    }

    public String getHoliday()
    {
        return sHoliday;
    }

    public String request()
    {
        String s = Uri.decode(uriBuilder.build().toString());
        objResult = (new HttpClient()).doGetString(s);
        Object obj = objResult;
        String s1 = null;
        if (obj != null)
        {
            s1 = objResult.toString();
        }
        analyze(s1);
        return s1;
    }
}
