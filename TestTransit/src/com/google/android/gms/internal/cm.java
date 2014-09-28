// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;

// Referenced classes of package com.google.android.gms.internal:
//            eu, cf, co, cn

public final class cm extends g
{
    private static final class a extends Exception
    {

        public a(String s)
        {
            super(s);
        }
    }


    private static final cm oS = new cm();

    private cm()
    {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public static cn a(Activity activity)
    {
        cn cn;
        try
        {
            if (b(activity))
            {
                eu.z("Using AdOverlay from the client jar.");
                return new cf(activity);
            }
            cn = oS.c(activity);
        }
        catch (a a1)
        {
            eu.D(a1.getMessage());
            return null;
        }
        return cn;
    }

    private static boolean b(Activity activity)
        throws a
    {
        Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar"))
        {
            throw new a("Ad overlay requires the useClientJar flag in intent extras.");
        } else
        {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
    }

    private cn c(Activity activity)
    {
        cn cn;
        try
        {
            com.google.android.gms.dynamic.d d1 = e.h(activity);
            cn = com.google.android.gms.internal.cn.a.m(((co)G(activity)).a(d1));
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not create remote AdOverlay.", remoteexception);
            return null;
        }
        catch (com.google.android.gms.dynamic.g.a a1)
        {
            eu.c("Could not create remote AdOverlay.", a1);
            return null;
        }
        return cn;
    }

    protected Object d(IBinder ibinder)
    {
        return l(ibinder);
    }

    protected co l(IBinder ibinder)
    {
        return co.a.n(ibinder);
    }

}
