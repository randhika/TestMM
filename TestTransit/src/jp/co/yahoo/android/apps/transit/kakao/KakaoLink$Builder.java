// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.content.Context;
import android.content.Intent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            KakaoLink

public static class context
{

    String apiVer;
    String appId;
    String appName;
    String appVersion;
    ArrayList arrMetaInfo;
    String clientToken;
    Context context;
    String encoding;
    Intent intent;
    String mimeType;
    String msg;
    String objData;
    String type;
    String url;

    public KakaoLink build()
        throws UnsupportedEncodingException
    {
        return new KakaoLink(this, null);
    }

    public context setAPIVersion(String s)
    {
        apiVer = s;
        return this;
    }

    public apiVer setAppId(String s)
    {
        appId = s;
        return this;
    }

    public appId setAppName(String s)
    {
        appName = s;
        return this;
    }

    public appName setAppVersion(String s)
    {
        appVersion = s;
        return this;
    }

    public appVersion setClientToken(String s)
    {
        clientToken = s;
        return this;
    }

    public clientToken setEncoding(String s)
    {
        encoding = s;
        return this;
    }

    public encoding setMessage(String s)
    {
        msg = s;
        return this;
    }

    public msg setMetadata(ArrayList arraylist)
    {
        arrMetaInfo = arraylist;
        return this;
    }

    public arrMetaInfo setObjectData(String s, String s1)
    {
        objData = s;
        mimeType = s1;
        return this;
    }

    public mimeType setType(String s)
    {
        type = s;
        return this;
    }

    public type setUrl(String s)
    {
        url = s;
        return this;
    }

    protected void setV1()
    {
        type = "link";
        apiVer = "1.0";
    }

    protected void setV2()
    {
        type = "app";
        apiVer = "2.0";
    }

    protected void setV2_1()
    {
        type = "app";
        apiVer = "2.1";
    }

    protected void setV2_2()
    {
        type = "app";
        apiVer = "2.2";
    }

    public (Context context1)
    {
        context = null;
        intent = null;
        appId = null;
        appVersion = null;
        url = null;
        msg = null;
        encoding = null;
        type = null;
        apiVer = null;
        appName = null;
        arrMetaInfo = null;
        context = context1;
    }
}
