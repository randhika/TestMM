// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ApplicotConfiguration;

// Referenced classes of package jp.co.yahoo.applicot.collector:
//            Compatibility

final class DropBoxCollector
{

    private static final String NO_RESULT = "N/A";
    private static final String SYSTEM_TAGS[] = {
        "system_app_anr", "system_app_wtf", "system_app_crash", "system_server_anr", "system_server_wtf", "system_server_crash", "BATTERY_DISCHARGE_INFO", "SYSTEM_RECOVERY_LOG", "SYSTEM_BOOT", "SYSTEM_LAST_KMSG", 
        "APANIC_CONSOLE", "APANIC_THREADS", "SYSTEM_RESTART", "SYSTEM_TOMBSTONE", "data_app_strictmode"
    };

    DropBoxCollector()
    {
    }

    public static String read(Context context, String as[])
    {
        String s = Compatibility.getDropBoxServiceName();
        if (s == null)
        {
            return "N/A";
        }
        Object obj;
        Method method;
        obj = context.getSystemService(s);
        Class class1 = obj.getClass();
        Class aclass[] = new Class[2];
        aclass[0] = java/lang/String;
        aclass[1] = Long.TYPE;
        method = class1.getMethod("getNextEntry", aclass);
        if (method == null)
        {
            return "";
        }
        Time time;
        long l;
        ArrayList arraylist;
        time = new Time();
        time.setToNow();
        time.minute = time.minute - Applicot.getConfig().dropboxCollectionMinutes();
        time.normalize(false);
        l = time.toMillis(false);
        arraylist = new ArrayList();
        if (Applicot.getConfig().includeDropBoxSystemTags())
        {
            arraylist.addAll(Arrays.asList(SYSTEM_TAGS));
        }
        if (as == null)
        {
            break MISSING_BLOCK_LABEL_165;
        }
        if (as.length > 0)
        {
            arraylist.addAll(Arrays.asList(as));
        }
        if (arraylist.isEmpty())
        {
            return "No tag configured for collection.";
        }
        StringBuilder stringbuilder;
        Iterator iterator;
        stringbuilder = new StringBuilder();
        iterator = arraylist.iterator();
_L5:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        String s2;
        Object obj1;
        s2 = (String)iterator.next();
        stringbuilder.append("Tag: ").append(s2).append('\n');
        Object aobj[] = new Object[2];
        aobj[0] = s2;
        aobj[1] = Long.valueOf(l);
        obj1 = method.invoke(obj, aobj);
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        stringbuilder.append("Nothing.").append('\n');
          goto _L5
        SecurityException securityexception;
        securityexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
_L9:
        return "N/A";
_L4:
        Method method1;
        Method method2;
        Method method3;
        Class class2 = obj1.getClass();
        Class aclass1[] = new Class[1];
        aclass1[0] = Integer.TYPE;
        method1 = class2.getMethod("getText", aclass1);
        method2 = obj1.getClass().getMethod("getTimeMillis", (Class[])null);
        method3 = obj1.getClass().getMethod("close", (Class[])null);
_L7:
        if (obj1 == null) goto _L5; else goto _L6
_L6:
        long l1;
        String s3;
        l1 = ((Long)method2.invoke(obj1, (Object[])null)).longValue();
        time.set(l1);
        stringbuilder.append("@").append(time.format2445()).append('\n');
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(500);
        s3 = (String)method1.invoke(obj1, aobj1);
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_518;
        }
        stringbuilder.append("Text: ").append(s3).append('\n');
_L8:
        method3.invoke(obj1, (Object[])null);
        Object aobj2[] = new Object[2];
        aobj2[0] = s2;
        aobj2[1] = Long.valueOf(l1);
        obj1 = method.invoke(obj, aobj2);
          goto _L7
          goto _L5
        stringbuilder.append("Not Text!").append('\n');
          goto _L8
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
          goto _L9
_L2:
        String s1 = stringbuilder.toString();
        return s1;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
          goto _L9
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
          goto _L9
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
          goto _L9
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        Log.i(Applicot.LOG_TAG, "DropBoxManager not available.");
          goto _L9
    }

}
