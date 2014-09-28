// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebSettings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h

public class r
{

    private static Method a;
    private static Class b;
    private static Map c;

    public r()
    {
    }

    private static void a()
    {
        Class aclass[] = android/webkit/WebSettings.getClasses();
        int i = aclass.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j < i)
                {
                    Class class1 = aclass[j];
                    if (!"android.webkit.WebSettings$PluginState".equals(class1.getName()))
                    {
                        break label1;
                    }
                    b = class1;
                }
                c = new HashMap();
                Object aobj[] = b.getEnumConstants();
                int k = aobj.length;
                for (int l = 0; l < k; l++)
                {
                    Enum enum = (Enum)aobj[l];
                    c.put(enum.name(), enum);
                }

                break label0;
            }
            j++;
        } while (true);
        try
        {
            Class aclass1[] = new Class[1];
            aclass1[0] = b;
            a = android/webkit/WebSettings.getMethod("setPluginState", aclass1);
            return;
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            h.a(6, "not supported method for WebSettings.setPluginState.\n", nosuchmethodexception);
        }
    }

    private static void a(WebSettings websettings, Enum enum)
        throws RuntimeException
    {
        try
        {
            a.invoke(websettings, new Object[] {
                enum
            });
            return;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            }
            if (throwable instanceof Error)
            {
                throw (Error)throwable;
            } else
            {
                throw new RuntimeException(invocationtargetexception);
            }
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
    }

    public boolean a(WebSettings websettings)
    {
        if (a != null && b != null)
        {
            try
            {
                a(websettings, (Enum)c.get("ON"));
            }
            catch (RuntimeException runtimeexception)
            {
                h.a(5, "failed method for WebSettings.setPluginState.\n", runtimeexception);
                return false;
            }
            return true;
        } else
        {
            h.a(5, "not supported method for WebSettings.setPluginState.");
            return false;
        }
    }

    static 
    {
        a();
    }
}
