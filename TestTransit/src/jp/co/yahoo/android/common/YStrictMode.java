// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package jp.co.yahoo.android.common:
//            YApplicationBase, YLog

public class YStrictMode
{

    private static final String TAG = jp/co/yahoo/android/common/YStrictMode.getSimpleName();

    public YStrictMode()
    {
    }

    public static void enableDefaults()
    {
        if (!isAvailable())
        {
            return;
        }
        if (!YApplicationBase.isDebugSignature())
        {
            permitAll();
            return;
        }
        try
        {
            Class class1 = Class.forName("android.os.StrictMode");
            class1.getMethod("enableDefaults", (Class[])null).invoke(class1, (Object[])null);
            YLog.i(TAG, "StrictMode#enableDefaults()");
            return;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            YLog.e(TAG, classnotfoundexception.getMessage());
            classnotfoundexception.printStackTrace();
            return;
        }
        catch (SecurityException securityexception)
        {
            YLog.e(TAG, securityexception.getMessage());
            securityexception.printStackTrace();
            return;
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            YLog.e(TAG, nosuchmethodexception.getMessage());
            nosuchmethodexception.printStackTrace();
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            YLog.e(TAG, illegalargumentexception.getMessage());
            illegalargumentexception.printStackTrace();
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            YLog.e(TAG, illegalaccessexception.getMessage());
            illegalaccessexception.printStackTrace();
            return;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            YLog.e(TAG, invocationtargetexception.getMessage());
            invocationtargetexception.printStackTrace();
            return;
        }
    }

    private static boolean isAvailable()
    {
        return android.os.Build.VERSION.SDK_INT > 8;
    }

    public static void permitAll()
    {
        if (!isAvailable())
        {
            return;
        }
        try
        {
            Class class1 = Class.forName("android.os.StrictMode");
            Class class2 = Class.forName("android.os.StrictMode$ThreadPolicy");
            Class class3 = Class.forName("android.os.StrictMode$ThreadPolicy$Builder");
            Object obj = class3.newInstance();
            class3.getMethod("permitAll", (Class[])null).invoke(obj, (Object[])null);
            Object obj1 = class3.getMethod("build", (Class[])null).invoke(obj, (Object[])null);
            class1.getMethod("setThreadPolicy", new Class[] {
                class2
            }).invoke(class1, new Object[] {
                obj1
            });
            YLog.i(TAG, "StrictMode#permitAll()");
            return;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            YLog.e(TAG, classnotfoundexception.getMessage());
            classnotfoundexception.printStackTrace();
            return;
        }
        catch (SecurityException securityexception)
        {
            YLog.e(TAG, securityexception.getMessage());
            securityexception.printStackTrace();
            return;
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            YLog.e(TAG, nosuchmethodexception.getMessage());
            nosuchmethodexception.printStackTrace();
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            YLog.e(TAG, illegalaccessexception.getMessage());
            illegalaccessexception.printStackTrace();
            return;
        }
        catch (InstantiationException instantiationexception)
        {
            YLog.e(TAG, instantiationexception.getMessage());
            instantiationexception.printStackTrace();
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            YLog.e(TAG, illegalargumentexception.getMessage());
            illegalargumentexception.printStackTrace();
            return;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            YLog.e(TAG, invocationtargetexception.getMessage());
            invocationtargetexception.printStackTrace();
            return;
        }
    }

}
