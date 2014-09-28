// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KakaoLink
{
    public static class Builder
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
            return new KakaoLink(this);
        }

        public Builder setAPIVersion(String s)
        {
            apiVer = s;
            return this;
        }

        public Builder setAppId(String s)
        {
            appId = s;
            return this;
        }

        public Builder setAppName(String s)
        {
            appName = s;
            return this;
        }

        public Builder setAppVersion(String s)
        {
            appVersion = s;
            return this;
        }

        public Builder setClientToken(String s)
        {
            clientToken = s;
            return this;
        }

        public Builder setEncoding(String s)
        {
            encoding = s;
            return this;
        }

        public Builder setMessage(String s)
        {
            msg = s;
            return this;
        }

        public Builder setMetadata(ArrayList arraylist)
        {
            arrMetaInfo = arraylist;
            return this;
        }

        public Builder setObjectData(String s, String s1)
        {
            objData = s;
            mimeType = s1;
            return this;
        }

        public Builder setType(String s)
        {
            type = s;
            return this;
        }

        public Builder setUrl(String s)
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

        public Builder(Context context1)
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

    public static class BuilderV1 extends Builder
    {

        public Builder setAppId(String s)
        {
            return super.setAppId(s);
        }

        public Builder setAppName(String s)
        {
            return super.setAppName(s);
        }

        public Builder setAppVersion(String s)
        {
            return super.setAppVersion(s);
        }

        public Builder setEncoding(String s)
        {
            return super.setEncoding(s);
        }

        public Builder setMessage(String s)
        {
            return super.setMessage(s);
        }

        public Builder setUrl(String s)
        {
            return super.setUrl(s);
        }

        public BuilderV1(Context context1)
        {
            super(context1);
            setV1();
        }
    }

    public static class BuilderV2 extends Builder
    {

        public Builder setAppId(String s)
        {
            return super.setAppId(s);
        }

        public Builder setAppName(String s)
        {
            return super.setAppName(s);
        }

        public Builder setAppVersion(String s)
        {
            return super.setAppVersion(s);
        }

        public Builder setEncoding(String s)
        {
            return super.setEncoding(s);
        }

        public Builder setMessage(String s)
        {
            return super.setMessage(s);
        }

        public Builder setMetadata(ArrayList arraylist)
        {
            return super.setMetadata(arraylist);
        }

        public BuilderV2(Context context1)
        {
            super(context1);
            setV2();
        }
    }

    public static class BuilderV2_1 extends Builder
    {

        public Builder setAppId(String s)
        {
            return super.setAppId(s);
        }

        public Builder setAppName(String s)
        {
            return super.setAppName(s);
        }

        public Builder setAppVersion(String s)
        {
            return super.setAppVersion(s);
        }

        public Builder setClientToken(String s)
        {
            return super.setClientToken(s);
        }

        public Builder setEncoding(String s)
        {
            return super.setEncoding(s);
        }

        public Builder setObjectData(String s, String s1)
        {
            return super.setObjectData(s, s1);
        }

        public BuilderV2_1(Context context1)
        {
            super(context1);
            setV2_1();
        }
    }

    public static class BuilderV2_2 extends Builder
    {

        public Builder setAppId(String s)
        {
            return super.setAppId(s);
        }

        public Builder setAppName(String s)
        {
            return super.setAppName(s);
        }

        public Builder setAppVersion(String s)
        {
            return super.setAppVersion(s);
        }

        public Builder setClientToken(String s)
        {
            return super.setClientToken(s);
        }

        public Builder setEncoding(String s)
        {
            return super.setEncoding(s);
        }

        public Builder setMessage(String s)
        {
            return super.setMessage(s);
        }

        public Builder setMetadata(ArrayList arraylist)
        {
            return super.setMetadata(arraylist);
        }

        public BuilderV2_2(Context context1)
        {
            super(context1);
            setV2_2();
        }
    }


    static final Charset kakaoLinkCharset;
    static final String kakaoLinkEncoding;
    private String apiVer;
    private String appId;
    private String appName;
    private String appVersion;
    private ArrayList arrMetaInfo;
    private String clientToken;
    private Context context;
    private Uri data;
    private String encoding;
    private Intent intent;
    private String mimeType;
    private String msg;
    private String objData;
    private String type;
    private String url;

    private KakaoLink(Builder builder)
        throws UnsupportedEncodingException
    {
        arrMetaInfo = null;
        context = builder.context;
        appId = builder.appId;
        appVersion = builder.appVersion;
        url = builder.url;
        msg = builder.msg;
        type = builder.type;
        apiVer = builder.apiVer;
        appName = builder.appName;
        encoding = builder.encoding;
        arrMetaInfo = builder.arrMetaInfo;
        clientToken = builder.clientToken;
        objData = builder.objData;
        mimeType = builder.mimeType;
        if (appId == null && appVersion == null && url == null && msg == null && type == null && apiVer == null && appName == null && encoding == null && arrMetaInfo == null && objData == null && mimeType == null && clientToken != null)
        {
            data = createOpenChatRoomData();
        } else
        if (arrMetaInfo == null)
        {
            data = createObjectLinkData();
        } else
        {
            data = createAppLinkData();
        }
        intent = new Intent("android.intent.action.SEND", data);
    }


    private Uri createAppLinkData()
        throws UnsupportedEncodingException
    {
        if (isEmptyString(msg) || isEmptyString(appId) || isEmptyString(type) || isEmptyString(appName))
        {
            throw new IllegalArgumentException();
        }
        if (arrMetaInfo == null || arrMetaInfo.size() <= 0)
        {
            throw new IllegalArgumentException();
        }
        Charset charset = Charset.forName(encoding);
        if (!kakaoLinkCharset.equals(charset) && !isEmptyString(msg))
        {
            msg = new String(msg.getBytes(charset.name()), kakaoLinkEncoding);
        }
        StringBuilder stringbuilder = new StringBuilder("kakaolink://sendurl?");
        stringbuilder.append("&msg=").append(URLEncoder.encode(msg, kakaoLinkEncoding));
        if (!isEmptyString(url))
        {
            stringbuilder.append("&url=").append(URLEncoder.encode(url, kakaoLinkEncoding));
        }
        stringbuilder.append("&appid=").append(URLEncoder.encode(appId, kakaoLinkEncoding));
        if (!isEmptyString(appVersion))
        {
            stringbuilder.append("&appver=").append(URLEncoder.encode(appVersion, kakaoLinkEncoding));
        }
        stringbuilder.append("&type=").append(URLEncoder.encode(type, kakaoLinkEncoding));
        stringbuilder.append("&apiver=").append(URLEncoder.encode(apiVer, kakaoLinkEncoding));
        stringbuilder.append("&appname=").append(URLEncoder.encode(appName, kakaoLinkEncoding));
        if (!isEmptyString(clientToken))
        {
            stringbuilder.append("&clientToken=").append(URLEncoder.encode(clientToken, kakaoLinkEncoding));
        }
        String s = makeJsonMetaInfo();
        if (!isEmptyString(s))
        {
            stringbuilder.append("&metainfo=").append(URLEncoder.encode(s, kakaoLinkEncoding));
        }
        Log.i("TEST", (new StringBuilder()).append("++ sb.toString() : ").append(stringbuilder.toString()).toString());
        return Uri.parse(stringbuilder.toString());
    }

    private Uri createObjectLinkData()
        throws UnsupportedEncodingException
    {
        if (isEmptyString(appId) || isEmptyString(type) || isEmptyString(appName) || isEmptyString(clientToken) || isEmptyString(objData) || isEmptyString(mimeType))
        {
            throw new IllegalArgumentException();
        }
        Charset charset = Charset.forName(encoding);
        StringBuilder stringbuilder = new StringBuilder("kakaolink://sendurl?");
        if (!kakaoLinkCharset.equals(charset) && !isEmptyString(msg))
        {
            msg = new String(msg.getBytes(charset.name()), kakaoLinkEncoding);
            stringbuilder.append("&msg=").append(URLEncoder.encode(msg, kakaoLinkEncoding));
        }
        if (!isEmptyString(url))
        {
            stringbuilder.append("&url=").append(URLEncoder.encode(url, kakaoLinkEncoding));
        }
        stringbuilder.append("&appid=").append(URLEncoder.encode(appId, kakaoLinkEncoding));
        if (!isEmptyString(appVersion))
        {
            stringbuilder.append("&appver=").append(URLEncoder.encode(appVersion, kakaoLinkEncoding));
        }
        stringbuilder.append("&type=").append(URLEncoder.encode(type, kakaoLinkEncoding));
        stringbuilder.append("&apiver=").append(URLEncoder.encode(apiVer, kakaoLinkEncoding));
        stringbuilder.append("&appname=").append(URLEncoder.encode(appName, kakaoLinkEncoding));
        stringbuilder.append("&clientToken=").append(URLEncoder.encode(clientToken, kakaoLinkEncoding));
        stringbuilder.append("&objData=").append(URLEncoder.encode(objData, kakaoLinkEncoding));
        stringbuilder.append("&mimeType=").append(URLEncoder.encode(mimeType, kakaoLinkEncoding));
        Log.i("TEST", (new StringBuilder()).append("++ sb.toString() : ").append(stringbuilder.toString()).toString());
        return Uri.parse(stringbuilder.toString());
    }

    private Uri createOpenChatRoomData()
        throws UnsupportedEncodingException
    {
        if (clientToken == null || clientToken.trim().length() <= 0)
        {
            return null;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder("kakaolink://sendurl?");
            stringbuilder.append("clientToken=").append(URLEncoder.encode(clientToken, kakaoLinkEncoding));
            return Uri.parse(stringbuilder.toString());
        }
    }

    private static boolean isAvailableIntent(Context context1, Intent intent1)
    {
        for (List list = context1.getPackageManager().queryIntentActivities(intent1, 0x10000); list == null || list.size() <= 0;)
        {
            return false;
        }

        return true;
    }

    private static boolean isEmptyString(String s)
    {
        return s == null || s.trim().length() == 0;
    }

    private String makeJsonMetaInfo()
        throws UnsupportedEncodingException
    {
        JSONObject jsonobject;
        JSONArray jsonarray;
        jsonobject = new JSONObject();
        jsonarray = new JSONArray();
        Iterator iterator = arrMetaInfo.iterator();
_L1:
        JSONException jsonexception;
        JSONObject jsonobject1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_132;
        }
        Map map = (Map)iterator.next();
        jsonobject1 = new JSONObject();
        String s;
        for (Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext(); jsonobject1.put(s, map.get(s)))
        {
            s = (String)iterator1.next();
        }

        break MISSING_BLOCK_LABEL_122;
        try
        {
            jsonobject.put("metainfo", jsonarray);
        }
        // Misplaced declaration of an exception variable
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return jsonobject.toString();
        jsonarray.put(jsonobject1);
          goto _L1
    }

    public Uri getData()
    {
        return data;
    }

    public Intent getIntent()
    {
        return intent;
    }

    public boolean isAvailable()
    {
        return isAvailableIntent(context, intent);
    }

    static 
    {
        kakaoLinkCharset = Charset.forName("UTF-8");
        kakaoLinkEncoding = kakaoLinkCharset.name();
    }
}
