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
//            eu, cu, de, dd

public final class di extends g
{
    private static final class a extends Exception
    {

        public a(String s)
        {
            super(s);
        }
    }


    private static final di pv = new di();

    private di()
    {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    private static boolean b(Activity activity)
        throws a
    {
        Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar"))
        {
            throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
        } else
        {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
        }
    }

    public static dd d(Activity activity)
    {
        dd dd;
        try
        {
            if (b(activity))
            {
                eu.z("Using AdOverlay from the client jar.");
                return new cu(activity);
            }
            dd = pv.e(activity);
        }
        catch (a a1)
        {
            eu.D(a1.getMessage());
            return null;
        }
        return dd;
    }

    private dd e(Activity activity)
    {
        dd dd;
        try
        {
            com.google.android.gms.dynamic.d d1 = com.google.android.gms.dynamic.e.h(activity);
            dd = com.google.android.gms.internal.dd.a.r(((de)G(activity)).b(d1));
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not create remote InAppPurchaseManager.", remoteexception);
            return null;
        }
        catch (com.google.android.gms.dynamic.g.a a1)
        {
            eu.c("Could not create remote InAppPurchaseManager.", a1);
            return null;
        }
        return dd;
    }

    protected Object d(IBinder ibinder)
    {
        return v(ibinder);
    }

    protected de v(IBinder ibinder)
    {
        return de.a.s(ibinder);
    }

}
