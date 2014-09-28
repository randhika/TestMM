// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.identity.intents.UserAddressRequest;

// Referenced classes of package com.google.android.gms.internal:
//            hb, iy, hi

public class iw extends hb
{
    public static final class a extends ix.a
    {

        private final int FW;
        private Activity og;

        static void a(a a1, Activity activity)
        {
            a1.setActivity(activity);
        }

        private void setActivity(Activity activity)
        {
            og = activity;
        }

        public void g(int i, Bundle bundle)
        {
            if (i != 1) goto _L2; else goto _L1
_L1:
            PendingIntent pendingintent;
            Intent intent = new Intent();
            intent.putExtras(bundle);
            pendingintent = og.createPendingResult(FW, intent, 0x40000000);
            if (pendingintent != null) goto _L4; else goto _L3
_L3:
            return;
_L4:
            try
            {
                pendingintent.send(1);
                return;
            }
            catch (android.app.PendingIntent.CanceledException canceledexception)
            {
                Log.w("AddressClientImpl", "Exception settng pending result", canceledexception);
            }
            return;
_L2:
            PendingIntent pendingintent1 = null;
            if (bundle != null)
            {
                pendingintent1 = (PendingIntent)bundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
            }
            ConnectionResult connectionresult = new ConnectionResult(i, pendingintent1);
            if (connectionresult.hasResolution())
            {
                try
                {
                    connectionresult.startResolutionForResult(og, FW);
                    return;
                }
                catch (android.content.IntentSender.SendIntentException sendintentexception)
                {
                    Log.w("AddressClientImpl", "Exception starting pending intent", sendintentexception);
                }
                return;
            }
            PendingIntent pendingintent2 = og.createPendingResult(FW, new Intent(), 0x40000000);
            if (pendingintent2 != null)
            {
                try
                {
                    pendingintent2.send(1);
                    return;
                }
                catch (android.app.PendingIntent.CanceledException canceledexception1)
                {
                    Log.w("AddressClientImpl", "Exception setting pending result", canceledexception1);
                }
                return;
            }
            if (true) goto _L3; else goto _L5
_L5:
        }

        public a(int i, Activity activity)
        {
            FW = i;
            og = activity;
        }
    }


    private a UG;
    private final int mTheme;
    private Activity og;
    private final String yQ;

    public iw(Activity activity, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String s, int i)
    {
        super(activity, looper, connectioncallbacks, onconnectionfailedlistener, new String[0]);
        yQ = s;
        og = activity;
        mTheme = i;
    }

    public void a(UserAddressRequest useraddressrequest, int i)
    {
        iU();
        UG = new a(i, og);
        try
        {
            Bundle bundle = new Bundle();
            bundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", getContext().getPackageName());
            if (!TextUtils.isEmpty(yQ))
            {
                bundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(yQ, "com.google"));
            }
            bundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", mTheme);
            iT().a(UG, useraddressrequest, bundle);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.e("AddressClientImpl", "Exception requesting user address", remoteexception);
        }
        Bundle bundle1 = new Bundle();
        bundle1.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
        UG.g(1, bundle1);
    }

    protected void a(hi hi1, hb.e e)
        throws RemoteException
    {
        hi1.d(e, 0x4da6e8, getContext().getPackageName());
    }

    protected iy an(IBinder ibinder)
    {
        return iy.a.ap(ibinder);
    }

    protected String bu()
    {
        return "com.google.android.gms.identity.service.BIND";
    }

    protected String bv()
    {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }

    public void disconnect()
    {
        super.disconnect();
        if (UG != null)
        {
            a.a(UG, null);
            UG = null;
        }
    }

    protected iy iT()
    {
        return (iy)super.ft();
    }

    protected void iU()
    {
        super.cn();
    }

    protected IInterface x(IBinder ibinder)
    {
        return an(ibinder);
    }
}
