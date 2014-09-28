// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class in
{

    private static final Pattern Hw = Pattern.compile("\\\\.");
    private static final Pattern Hx = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    public static String aK(String s)
    {
        if (TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        Matcher matcher;
        StringBuffer stringbuffer;
        matcher = Hx.matcher(s);
        stringbuffer = null;
_L5:
        if (matcher.find())
        {
            if (stringbuffer == null)
            {
                stringbuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0))
            {
            case 8: // '\b'
                matcher.appendReplacement(stringbuffer, "\\\\b");
                break;

            case 34: // '"'
                matcher.appendReplacement(stringbuffer, "\\\\\\\"");
                break;

            case 92: // '\\'
                matcher.appendReplacement(stringbuffer, "\\\\\\\\");
                break;

            case 47: // '/'
                matcher.appendReplacement(stringbuffer, "\\\\/");
                break;

            case 12: // '\f'
                matcher.appendReplacement(stringbuffer, "\\\\f");
                break;

            case 10: // '\n'
                matcher.appendReplacement(stringbuffer, "\\\\n");
                break;

            case 13: // '\r'
                matcher.appendReplacement(stringbuffer, "\\\\r");
                break;

            case 9: // '\t'
                matcher.appendReplacement(stringbuffer, "\\\\t");
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (stringbuffer != null) goto _L3; else goto _L2
_L2:
        return s;
_L3:
        matcher.appendTail(stringbuffer);
        return stringbuffer.toString();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean d(Object obj, Object obj1)
    {
        if (!(obj instanceof JSONObject) || !(obj1 instanceof JSONObject)) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject;
        JSONObject jsonobject1;
        jsonobject = (JSONObject)obj;
        jsonobject1 = (JSONObject)obj1;
        if (jsonobject.length() == jsonobject1.length()) goto _L4; else goto _L3
_L3:
        return false;
_L4:
        for (Iterator iterator = jsonobject.keys(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if (!jsonobject1.has(s))
            {
                continue; /* Loop/switch isn't completed */
            }
            JSONArray jsonarray;
            JSONArray jsonarray1;
            int i;
            JSONException jsonexception;
            boolean flag;
            boolean flag1;
            try
            {
                flag1 = d(jsonobject.get(s), jsonobject1.get(s));
            }
            catch (JSONException jsonexception1)
            {
                return false;
            }
            if (!flag1)
            {
                return false;
            }
        }

        return true;
_L2:
        if (!(obj instanceof JSONArray) || !(obj1 instanceof JSONArray))
        {
            break MISSING_BLOCK_LABEL_185;
        }
        jsonarray = (JSONArray)obj;
        jsonarray1 = (JSONArray)obj1;
        if (jsonarray.length() != jsonarray1.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
        do
        {
            if (i >= jsonarray.length())
            {
                break; /* Loop/switch isn't completed */
            }
            try
            {
                flag = d(jsonarray.get(i), jsonarray1.get(i));
            }
            // Misplaced declaration of an exception variable
            catch (JSONException jsonexception)
            {
                return false;
            }
            if (!flag)
            {
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while (true);
        if (true) goto _L3; else goto _L5
_L5:
        return true;
        return obj.equals(obj1);
    }

}
