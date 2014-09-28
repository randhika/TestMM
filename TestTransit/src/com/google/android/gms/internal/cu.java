// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            cx, cq, cw, dg, 
//            eu, cy, cz, db, 
//            cr, cv

public class cu extends dd.a
    implements ServiceConnection
{

    private dg oX;
    private cr oY;
    private final cx oZ;
    private final Activity og;
    private cz pb;
    private Context ph;
    private db pi;
    private cv pj;
    private String pk;

    public cu(Activity activity)
    {
        pk = null;
        og = activity;
        oZ = cx.k(og.getApplicationContext());
    }

    public static void a(Context context, boolean flag, cq cq1)
    {
        Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", flag);
        cq.a(intent, cq1);
        context.startActivity(intent);
    }

    private void a(String s, boolean flag, int i, Intent intent)
    {
        try
        {
            oX.a(new cw(ph, s, flag, i, intent, pj));
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.D("Fail to invoke PlayStorePurchaseListener.");
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 1001) goto _L2; else goto _L1
_L1:
        int k = cy.c(intent);
        if (j != -1 || k != 0)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        if (!pb.a(pk, j, intent)) goto _L4; else goto _L3
_L3:
        a(pi.getProductId(), true, j, intent);
_L5:
        pi.recordPlayBillingResolution(k);
        pk = null;
        og.finish();
_L2:
        return;
_L4:
        a(pi.getProductId(), false, j, intent);
          goto _L5
        RemoteException remoteexception;
        remoteexception;
        eu.D("Fail to process purchase result.");
        pk = null;
        og.finish();
        return;
        oZ.a(pj);
        a(pi.getProductId(), false, j, intent);
          goto _L5
        Exception exception;
        exception;
        pk = null;
        og.finish();
        throw exception;
    }

    public void onCreate()
    {
        cq cq1 = cq.b(og.getIntent());
        oX = cq1.kX;
        pb = cq1.kZ;
        pi = cq1.oT;
        oY = new cr(og.getApplicationContext());
        ph = cq1.oU;
        Activity activity = og;
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        og.getApplicationContext();
        activity.bindService(intent, this, 1);
    }

    public void onDestroy()
    {
        og.unbindService(this);
        oY.destroy();
    }

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        oY.o(ibinder);
        Bundle bundle;
        PendingIntent pendingintent;
        pk = pb.bm();
        bundle = oY.a(og.getPackageName(), pi.getProductId(), pk);
        pendingintent = (PendingIntent)bundle.getParcelable("BUY_INTENT");
        if (pendingintent != null)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        int i = cy.a(bundle);
        pi.recordPlayBillingResolution(i);
        a(pi.getProductId(), false, i, null);
        og.finish();
        return;
        pj = new cv(pi.getProductId(), pk);
        oZ.b(pj);
        og.startIntentSenderForResult(pendingintent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        return;
        Object obj;
        obj;
_L2:
        eu.c("Error when connecting in-app billing service", ((Throwable) (obj)));
        og.finish();
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        eu.B("In-app billing service disconnected.");
        oY.destroy();
    }
}
