// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ReportField;
import jp.co.yahoo.applicot.collector.CrashReportData;
import jp.co.yahoo.applicot.log.ApplicotLog;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReportBuilder
{
    public static class JSONReportException extends Exception
    {

        private static final long serialVersionUID = 0xf65bfc8fa41205d5L;

        public JSONReportException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }


    public JSONReportBuilder()
    {
    }

    private static void addJSONFromProperty(JSONObject jsonobject, String s)
        throws JSONException
    {
        int i = s.indexOf('=');
        if (i > 0)
        {
            String s1 = s.substring(0, i).trim();
            Object obj = guessType(s.substring(i + 1).trim());
            if (obj instanceof String)
            {
                obj = ((String)obj).replaceAll("\\\\n", "\n");
            }
            String as[] = s1.split("\\.");
            if (as.length > 1)
            {
                addJSONSubTree(jsonobject, as, obj);
                return;
            } else
            {
                jsonobject.accumulate(s1, obj);
                return;
            }
        } else
        {
            jsonobject.put(s.trim(), true);
            return;
        }
    }

    private static void addJSONSubTree(JSONObject jsonobject, String as[], Object obj)
        throws JSONException
    {
        int i = 0;
        while (i < as.length) 
        {
            String s = as[i];
            if (i < -1 + as.length)
            {
                JSONObject jsonobject1;
                if (jsonobject.isNull(s))
                {
                    jsonobject1 = new JSONObject();
                    jsonobject.accumulate(s, jsonobject1);
                } else
                {
                    jsonobject1 = jsonobject.getJSONObject(s);
                }
                jsonobject = jsonobject1;
            } else
            {
                jsonobject.accumulate(s, obj);
            }
            i++;
        }
    }

    public static JSONObject buildJSONReport(CrashReportData crashreportdata)
        throws JSONReportException
    {
        JSONObject jsonobject;
        Iterator iterator;
        jsonobject = new JSONObject();
        iterator = crashreportdata.keySet().iterator();
_L3:
        ReportField reportfield;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        reportfield = (ReportField)iterator.next();
        JSONObject jsonobject1;
        BufferedReader bufferedreader;
        if (!reportfield.containsKeyValuePairs())
        {
            break MISSING_BLOCK_LABEL_187;
        }
        jsonobject1 = new JSONObject();
        bufferedreader = new BufferedReader(new StringReader(crashreportdata.getProperty(reportfield)), 1024);
_L1:
        String s = bufferedreader.readLine();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        addJSONFromProperty(jsonobject1, s);
          goto _L1
        IOException ioexception;
        ioexception;
        Applicot.log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Error while converting ").append(reportfield.name()).append(" to JSON.").toString(), ioexception);
        JSONException jsonexception;
        jsonobject.accumulate(reportfield.name(), jsonobject1);
        continue; /* Loop/switch isn't completed */
        try
        {
            jsonobject.accumulate(reportfield.name(), guessType(crashreportdata.getProperty(reportfield)));
        }
        // Misplaced declaration of an exception variable
        catch (JSONException jsonexception)
        {
            throw new JSONReportException((new StringBuilder()).append("Could not create JSON object for key ").append(reportfield).toString(), jsonexception);
        }
        if (true) goto _L3; else goto _L2
_L2:
        return jsonobject;
    }

    private static Object guessType(String s)
    {
        NumberFormat numberformat;
        if (s.equalsIgnoreCase("true"))
        {
            return Boolean.valueOf(true);
        }
        if (s.equalsIgnoreCase("false"))
        {
            return Boolean.valueOf(false);
        }
        if (!s.matches("(?:^|\\s)([1-9](?:\\d*|(?:\\d{0,2})(?:,\\d{3})*)(?:\\.\\d*[1-9])?|0?\\.\\d*[1-9]|0)(?:\\s|$)"))
        {
            break MISSING_BLOCK_LABEL_53;
        }
        numberformat = NumberFormat.getInstance(Locale.US);
        Number number = numberformat.parse(s);
        return number;
        ParseException parseexception;
        parseexception;
        return s;
    }
}
