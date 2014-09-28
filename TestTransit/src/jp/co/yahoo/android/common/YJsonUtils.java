// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YJsonUtils
{

    public YJsonUtils()
    {
    }

    public static final void log(int i, String s, JSONObject jsonobject)
    {
        if (jsonobject == null)
        {
            YLog.println(i, s, "null");
            return;
        }
        try
        {
            YLog.println(i, s, jsonobject.toString(2));
            return;
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static final void logd(String s, JSONObject jsonobject)
    {
        log(3, s, jsonobject);
    }

    public static final void loge(String s, JSONObject jsonobject)
    {
        log(6, s, jsonobject);
    }
}
