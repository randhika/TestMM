// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkinSearch extends ApiBase
{

    private ArrayList arySkin;
    private int nDestinyDpi;

    public SkinSearch(Context context)
    {
        super(context);
        arySkin = null;
        nDestinyDpi = 320;
        setUri(context.getString(0x7f0d056a));
    }

    protected void analyze(String s)
    {
        if (!CountdownUtil.isEmpty(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        boolean flag;
        String s1 = (new StringBuilder()).append(context.getString(0x7f0d04f1)).append(Integer.toString(nDestinyDpi)).toString();
        JSONArray jsonarray;
        SkinMetaData skinmetadata;
        JSONObject jsonobject1;
        try
        {
            JSONObject jsonobject = new JSONObject(s.substring(s.indexOf("{"), 1 + s.lastIndexOf("}")));
            arySkin = new ArrayList();
            jsonarray = jsonobject.optJSONArray("theme");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        i = 0;
_L4:
        if (i >= jsonarray.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        skinmetadata = new SkinMetaData();
        jsonobject1 = jsonarray.optJSONObject(i);
        if (jsonobject1 == null)
        {
            break MISSING_BLOCK_LABEL_352;
        }
        skinmetadata.sId = jsonobject1.optString("id");
        skinmetadata.sPath = jsonobject1.optString("path");
        skinmetadata.sTitle = jsonobject1.optString("title");
        skinmetadata.sDescription = jsonobject1.optString("detail");
        skinmetadata.sDownloadUrl = jsonobject1.optString(s1);
        if (CountdownUtil.isEmpty(skinmetadata.sDownloadUrl))
        {
            skinmetadata.sDownloadUrl = jsonobject1.optString("url_and_320");
        }
        skinmetadata.sThumbnailUrl = jsonobject1.optString("thumbnail");
        skinmetadata.sIconUrl = jsonobject1.optString("icon");
        skinmetadata.nCount = jsonobject1.optInt("count");
        skinmetadata.nInterval = jsonobject1.optInt("interval");
        skinmetadata.nSpaceid = jsonobject1.optInt("spaceid_and");
        skinmetadata.sStartDate = jsonobject1.optString("startdate");
        skinmetadata.sEndDate = jsonobject1.optString("enddate");
        skinmetadata.sUpdateDate = jsonobject1.optString("update");
        if (jsonobject1.optInt("isScale", 1) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        skinmetadata.isScaleble = flag;
        arySkin.add(skinmetadata);
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

    public ArrayList getSkin()
    {
        return arySkin;
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

    public void setDisplayDpi(int i)
    {
        nDestinyDpi = i;
    }
}
