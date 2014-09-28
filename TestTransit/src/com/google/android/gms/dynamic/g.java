// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.hm;

public abstract class g
{
    public static class a extends Exception
    {

        public a(String s)
        {
            super(s);
        }

        public a(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }


    private final String Ml;
    private Object Mm;

    protected g(String s)
    {
        Ml = s;
    }

    protected final Object G(Context context)
        throws a
    {
        if (Mm == null)
        {
            hm.f(context);
            Context context1 = GooglePlayServicesUtil.getRemoteContext(context);
            if (context1 == null)
            {
                throw new a("Could not get remote context.");
            }
            ClassLoader classloader = context1.getClassLoader();
            try
            {
                Mm = d((IBinder)classloader.loadClass(Ml).newInstance());
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                throw new a("Could not load creator class.");
            }
            catch (InstantiationException instantiationexception)
            {
                throw new a("Could not instantiate creator.");
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                throw new a("Could not access creator.");
            }
        }
        return Mm;
    }

    protected abstract Object d(IBinder ibinder);
}
