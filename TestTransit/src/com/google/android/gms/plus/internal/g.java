// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.hm;
import com.google.android.gms.plus.PlusOneDummyView;

// Referenced classes of package com.google.android.gms.plus.internal:
//            c

public final class g
{
    public static class a extends Exception
    {

        public a(String s)
        {
            super(s);
        }
    }


    private static Context aaj;
    private static c abS;

    private static c K(Context context)
        throws a
    {
        hm.f(context);
        if (abS == null)
        {
            if (aaj == null)
            {
                aaj = GooglePlayServicesUtil.getRemoteContext(context);
                if (aaj == null)
                {
                    throw new a("Could not get remote context.");
                }
            }
            ClassLoader classloader = aaj.getClassLoader();
            try
            {
                abS = c.a.bl((IBinder)classloader.loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
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
        return abS;
    }

    public static View a(Context context, int i, int j, String s, int k)
    {
        if (s == null)
        {
            try
            {
                throw new NullPointerException();
            }
            catch (Exception exception)
            {
                return new PlusOneDummyView(context, i);
            }
        }
        View view = (View)e.e(K(context).a(e.h(context), i, j, s, k));
        return view;
    }
}
