// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ApplicotConfiguration;

// Referenced classes of package jp.co.yahoo.applicot.collector:
//            Compatibility

final class SettingsCollector
{

    SettingsCollector()
    {
    }

    public static String collectGlobalSettings(Context context)
    {
        StringBuilder stringbuilder;
        if (Compatibility.getAPILevel() < 17)
        {
            return "";
        }
        stringbuilder = new StringBuilder();
        Field afield[];
        Method method;
        int i;
        Class class1 = Class.forName("android.provider.Settings$Global");
        afield = class1.getFields();
        method = class1.getMethod("getString", new Class[] {
            android/content/ContentResolver, java/lang/String
        });
        i = afield.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Field field;
        Object obj;
        field = afield[j];
        if (field.isAnnotationPresent(java/lang/Deprecated) || field.getType() != java/lang/String || !isAuthorized(field))
        {
            break MISSING_BLOCK_LABEL_173;
        }
        Object aobj[] = new Object[2];
        aobj[0] = context.getContentResolver();
        aobj[1] = (String)field.get(null);
        obj = method.invoke(null, aobj);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_173;
        }
        stringbuilder.append(field.getName()).append("=").append(obj).append("\n");
        j++;
        if (true) goto _L2; else goto _L1
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.w(Applicot.LOG_TAG, "Error : ", illegalargumentexception);
_L1:
        return stringbuilder.toString();
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.w(Applicot.LOG_TAG, "Error : ", illegalaccessexception);
        continue; /* Loop/switch isn't completed */
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        Log.w(Applicot.LOG_TAG, "Error : ", classnotfoundexception);
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        Log.w(Applicot.LOG_TAG, "Error : ", securityexception);
        continue; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        Log.w(Applicot.LOG_TAG, "Error : ", nosuchmethodexception);
        continue; /* Loop/switch isn't completed */
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Log.w(Applicot.LOG_TAG, "Error : ", invocationtargetexception);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static String collectSecureSettings(Context context)
    {
        StringBuilder stringbuilder;
        Field afield[];
        int i;
        int j;
        stringbuilder = new StringBuilder();
        afield = android/provider/Settings$Secure.getFields();
        i = afield.length;
        j = 0;
_L3:
        if (j >= i) goto _L2; else goto _L1
_L1:
        Field field;
        field = afield[j];
        if (field.isAnnotationPresent(java/lang/Deprecated) || field.getType() != java/lang/String || !isAuthorized(field))
        {
            continue; /* Loop/switch isn't completed */
        }
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), (String)field.get(null));
        if (s != null)
        {
            try
            {
                stringbuilder.append(field.getName()).append("=").append(s).append("\n");
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Log.w(Applicot.LOG_TAG, "Error : ", illegalargumentexception);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                Log.w(Applicot.LOG_TAG, "Error : ", illegalaccessexception);
            }
        }
        j++;
          goto _L3
_L2:
        return stringbuilder.toString();
    }

    public static String collectSystemSettings(Context context)
    {
        StringBuilder stringbuilder;
        Field afield[];
        int i;
        int j;
        stringbuilder = new StringBuilder();
        afield = android/provider/Settings$System.getFields();
        i = afield.length;
        j = 0;
_L3:
        if (j >= i) goto _L2; else goto _L1
_L1:
        Field field;
        field = afield[j];
        if (field.isAnnotationPresent(java/lang/Deprecated) || field.getType() != java/lang/String)
        {
            continue; /* Loop/switch isn't completed */
        }
        String s = android.provider.Settings.System.getString(context.getContentResolver(), (String)field.get(null));
        if (s != null)
        {
            try
            {
                stringbuilder.append(field.getName()).append("=").append(s).append("\n");
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Log.w(Applicot.LOG_TAG, "Error : ", illegalargumentexception);
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                Log.w(Applicot.LOG_TAG, "Error : ", illegalaccessexception);
            }
        }
        j++;
          goto _L3
_L2:
        return stringbuilder.toString();
    }

    private static boolean isAuthorized(Field field)
    {
        if (field != null && !field.getName().startsWith("WIFI_AP")) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        String as[] = Applicot.getConfig().excludeMatchingSettingsKeys();
        int i = as.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                String s = as[j];
                if (field.getName().matches(s))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }
}
