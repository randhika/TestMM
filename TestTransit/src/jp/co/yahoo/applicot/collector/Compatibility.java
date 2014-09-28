// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.Context;
import java.lang.reflect.Field;

public final class Compatibility
{

    public Compatibility()
    {
    }

    public static int getAPILevel()
    {
        int i;
        try
        {
            i = android/os/Build$VERSION.getField("SDK_INT").getInt(null);
        }
        catch (SecurityException securityexception)
        {
            return Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            return Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            return Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        return i;
    }

    public static String getDropBoxServiceName()
        throws NoSuchFieldException, IllegalAccessException
    {
        Field field = android/content/Context.getField("DROPBOX_SERVICE");
        String s = null;
        if (field != null)
        {
            s = (String)field.get(null);
        }
        return s;
    }
}
