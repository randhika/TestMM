// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ApplicotConfiguration;
import jp.co.yahoo.applicot.ApplicotConstants;
import jp.co.yahoo.applicot.ReportField;
import jp.co.yahoo.applicot.collector.CrashReportData;
import jp.co.yahoo.applicot.util.HttpRequest;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.applicot.sender:
//            ReportSender, ReportSenderException

public class HttpSender
    implements ReportSender
{
    public static final class Method extends Enum
    {

        private static final Method $VALUES[];
        public static final Method POST;
        public static final Method PUT;

        public static Method valueOf(String s)
        {
            return (Method)Enum.valueOf(jp/co/yahoo/applicot/sender/HttpSender$Method, s);
        }

        public static Method[] values()
        {
            return (Method[])$VALUES.clone();
        }

        static 
        {
            POST = new Method("POST", 0);
            PUT = new Method("PUT", 1);
            Method amethod[] = new Method[2];
            amethod[0] = POST;
            amethod[1] = PUT;
            $VALUES = amethod;
        }

        private Method(String s, int i)
        {
            super(s, i);
        }
    }

    public static abstract class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type FORM;
        public static final Type JSON;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(jp/co/yahoo/applicot/sender/HttpSender$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public abstract String getContentType();

        static 
        {
            FORM = new Type("FORM", 0) {

                public String getContentType()
                {
                    return "application/x-www-form-urlencoded";
                }

            };
            JSON = new Type("JSON", 1) {

                public String getContentType()
                {
                    return "application/json";
                }

            };
            Type atype[] = new Type[2];
            atype[0] = FORM;
            atype[1] = JSON;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }

    }


    private final Uri mFormUri;
    private final Map mMapping;
    private final Method mMethod;
    private final Type mType;

    public HttpSender(Method method, Type type, String s, Map map)
    {
        mMethod = method;
        mFormUri = Uri.parse(s);
        mMapping = map;
        mType = type;
    }

    public HttpSender(Method method, Type type, Map map)
    {
        mMethod = method;
        mFormUri = null;
        mMapping = map;
        mType = type;
    }

    private Map remap(Map map)
    {
        ReportField areportfield[] = Applicot.getConfig().customReportContent();
        if (areportfield.length == 0)
        {
            areportfield = ApplicotConstants.DEFAULT_REPORT_FIELDS;
        }
        HashMap hashmap = new HashMap(map.size());
        ReportField areportfield1[] = areportfield;
        int i = areportfield1.length;
        int j = 0;
        while (j < i) 
        {
            ReportField reportfield = areportfield1[j];
            if (mMapping == null || mMapping.get(reportfield) == null)
            {
                hashmap.put(reportfield.toString(), map.get(reportfield));
            } else
            {
                hashmap.put(mMapping.get(reportfield), map.get(reportfield));
            }
            j++;
        }
        return hashmap;
    }

    public void send(CrashReportData crashreportdata)
        throws ReportSenderException
    {
        if (mFormUri != null) goto _L2; else goto _L1
_L1:
        URL url = new URL(Applicot.getConfig().formUri());
_L12:
        Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Connect to ").append(url.toString()).toString());
        if (!ApplicotConfiguration.isNull(Applicot.getConfig().formUriBasicAuthLogin())) goto _L4; else goto _L3
_L3:
        String s = null;
_L13:
        String s1;
        HttpRequest httprequest;
        String s2;
        static class _cls1
        {

            static final int $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[];
            static final int $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[];

            static 
            {
                $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method = new int[Method.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[Method.POST.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Method[Method.PUT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type = new int[Type.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[Type.JSON.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$sender$HttpSender$Type[Type.FORM.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        boolean flag;
        try
        {
            flag = ApplicotConfiguration.isNull(Applicot.getConfig().formUriBasicAuthPassword());
        }
        catch (IOException ioexception)
        {
            throw new ReportSenderException((new StringBuilder()).append("Error while sending ").append(Applicot.getConfig().reportType()).append(" report via Http ").append(mMethod.name()).toString(), ioexception);
        }
        catch (jp.co.yahoo.applicot.util.JSONReportBuilder.JSONReportException jsonreportexception)
        {
            throw new ReportSenderException((new StringBuilder()).append("Error while sending ").append(Applicot.getConfig().reportType()).append(" report via Http ").append(mMethod.name()).toString(), jsonreportexception);
        }
        s1 = null;
        if (!flag) goto _L6; else goto _L5
_L5:
        httprequest = new HttpRequest();
        httprequest.setConnectionTimeOut(Applicot.getConfig().connectionTimeout());
        httprequest.setSocketTimeOut(Applicot.getConfig().socketTimeout());
        httprequest.setMaxNrRetries(Applicot.getConfig().maxNumberOfRequestRetries());
        httprequest.setLogin(s);
        httprequest.setPassword(s1);
        httprequest.setHeaders(Applicot.getConfig().getHttpHeaders());
        _cls1..SwitchMap.jp.co.yahoo.applicot.sender.HttpSender.Type[mType.ordinal()];
        JVM INSTR tableswitch 1 1: default 184
    //                   1 400;
           goto _L7 _L8
_L7:
        s2 = HttpRequest.getParamsAsFormString(remap(crashreportdata));
_L14:
        _cls1..SwitchMap.jp.co.yahoo.applicot.sender.HttpSender.Method[mMethod.ordinal()];
        JVM INSTR tableswitch 1 2: default 228
    //                   1 454
    //                   2 412;
           goto _L9 _L10 _L11
_L9:
        throw new UnsupportedOperationException((new StringBuilder()).append("Unknown method: ").append(mMethod.name()).toString());
_L2:
        url = new URL(mFormUri.toString());
          goto _L12
_L4:
        s = Applicot.getConfig().formUriBasicAuthLogin();
          goto _L13
_L6:
        s1 = Applicot.getConfig().formUriBasicAuthPassword();
          goto _L5
_L8:
        s2 = crashreportdata.toJSON().toString();
          goto _L14
_L11:
        url = new URL((new StringBuilder()).append(url.toString()).append("?appid=").append(crashreportdata.getProperty(ReportField.YJDN_APPID)).toString());
_L10:
        httprequest.send(url, mMethod, s2, mType);
        return;
          goto _L12
    }
}
