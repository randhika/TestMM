// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;

// Referenced classes of package com.google.android.gms.internal:
//            eu, u, ev, ar, 
//            al, bs, aq

public final class ah extends g
{

    private static final ah lR = new ah();

    private ah()
    {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public static aq a(Context context, al al, String s, bs bs)
    {
        Object obj;
label0:
        {
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
            {
                obj = lR.b(context, al, s, bs);
                if (obj != null)
                {
                    break label0;
                }
            }
            eu.z("Using AdManager from the client jar.");
            obj = new u(context, al, s, bs, new ev(0x4da6e8, 0x4da6e8, true));
        }
        return ((aq) (obj));
    }

    private aq b(Context context, al al, String s, bs bs)
    {
        aq aq;
        try
        {
            com.google.android.gms.dynamic.d d1 = e.h(context);
            aq = com.google.android.gms.internal.aq.a.f(((ar)G(context)).a(d1, al, s, bs, 0x4da6e8));
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not create remote AdManager.", remoteexception);
            return null;
        }
        catch (com.google.android.gms.dynamic.g.a a1)
        {
            eu.c("Could not create remote AdManager.", a1);
            return null;
        }
        return aq;
    }

    protected ar c(IBinder ibinder)
    {
        return com.google.android.gms.internal.ar.a.g(ibinder);
    }

    protected Object d(IBinder ibinder)
    {
        return c(ibinder);
    }

}
