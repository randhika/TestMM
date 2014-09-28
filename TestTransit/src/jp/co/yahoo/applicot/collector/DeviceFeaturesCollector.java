// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import jp.co.yahoo.applicot.Applicot;

// Referenced classes of package jp.co.yahoo.applicot.collector:
//            Compatibility

final class DeviceFeaturesCollector
{

    DeviceFeaturesCollector()
    {
    }

    public static String getFeatures(Context context)
    {
        StringBuilder stringbuilder;
        if (Compatibility.getAPILevel() < 5)
        {
            return "Data available only with API Level >= 5";
        }
        stringbuilder = new StringBuilder();
        Object aobj[];
        int i;
        PackageManager packagemanager = context.getPackageManager();
        aobj = (Object[])(Object[])android/content/pm/PackageManager.getMethod("getSystemAvailableFeatures", (Class[])null).invoke(packagemanager, new Object[0]);
        i = aobj.length;
        int j = 0;
_L3:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_211;
        }
        Object obj;
        String s;
        obj = aobj[j];
        s = (String)obj.getClass().getField("name").get(obj);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_119;
        }
        stringbuilder.append(s);
_L1:
        stringbuilder.append("\n");
        j++;
        continue; /* Loop/switch isn't completed */
        String s1 = (String)obj.getClass().getMethod("getGlEsVersion", (Class[])null).invoke(obj, new Object[0]);
        stringbuilder.append("glEsVersion = ");
        stringbuilder.append(s1);
          goto _L1
        Throwable throwable;
        throwable;
        Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Couldn't retrieve DeviceFeatures for ").append(context.getPackageName()).toString(), throwable);
        stringbuilder.append("Could not retrieve data: ");
        stringbuilder.append(throwable.getMessage());
        return stringbuilder.toString();
        if (true) goto _L3; else goto _L2
_L2:
    }
}
