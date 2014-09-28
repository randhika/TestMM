// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.internal:
//            em, dw, eu, du, 
//            ds, ay, bj, ee, 
//            dx, dr, ev

public abstract class dq extends em
{
    public static final class a extends dq
    {

        private final Context mContext;

        public void bs()
        {
        }

        public dw bt()
        {
            ay ay1 = new ay();
            return dx.a(mContext, ay1, new bj(), new ee());
        }

        public a(Context context, ds ds, dp.a a1)
        {
            super(ds, a1);
            mContext = context;
        }
    }

    public static final class b extends dq
        implements com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    {

        private final Object ls = new Object();
        private final dp.a pT;
        private final dr pU;

        public void bs()
        {
            synchronized (ls)
            {
                if (pU.isConnected() || pU.isConnecting())
                {
                    pU.disconnect();
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public dw bt()
        {
            obj;
            JVM INSTR monitorenter ;
            dw dw1;
            synchronized (ls)
            {
                dw1 = pU.bw();
            }
            return dw1;
            IllegalStateException illegalstateexception;
            illegalstateexception;
            obj;
            JVM INSTR monitorexit ;
            return null;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onConnected(Bundle bundle)
        {
            start();
        }

        public void onConnectionFailed(ConnectionResult connectionresult)
        {
            pT.a(new du(0));
        }

        public void onDisconnected()
        {
            eu.z("Disconnected from remote ad request service.");
        }

        public b(Context context, ds ds1, dp.a a1)
        {
            super(ds1, a1);
            pT = a1;
            pU = new dr(context, this, this, ds1.kQ.sy);
            pU.connect();
        }
    }


    private final ds ne;
    private final dp.a pT;

    public dq(ds ds, dp.a a1)
    {
        ne = ds;
        pT = a1;
    }

    private static du a(dw dw1, ds ds)
    {
        du du1;
        try
        {
            du1 = dw1.b(ds);
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not fetch ad response from ad request service.", remoteexception);
            return null;
        }
        catch (NullPointerException nullpointerexception)
        {
            eu.c("Could not fetch ad response from ad request service due to an Exception.", nullpointerexception);
            return null;
        }
        catch (SecurityException securityexception)
        {
            eu.c("Could not fetch ad response from ad request service due to an Exception.", securityexception);
            return null;
        }
        return du1;
    }

    public final void bh()
    {
        dw dw1 = bt();
        if (dw1 != null) goto _L2; else goto _L1
_L1:
        du du1 = new du(0);
_L4:
        bs();
        pT.a(du1);
        return;
_L2:
        du1 = a(dw1, ne);
        if (du1 != null) goto _L4; else goto _L3
_L3:
        du1 = new du(0);
          goto _L4
        Exception exception;
        exception;
        bs();
        throw exception;
    }

    public abstract void bs();

    public abstract dw bt();

    public final void onStop()
    {
        bs();
    }
}
