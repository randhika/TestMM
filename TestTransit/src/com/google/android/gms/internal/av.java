// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

// Referenced classes of package com.google.android.gms.internal:
//            ak, bs, al, ah, 
//            ag, aq, an, dh, 
//            dl, at, eu

public final class av
{

    private AdListener lQ;
    private InAppPurchaseListener mB;
    private PlayStorePurchaseListener mC;
    private final Context mContext;
    private AppEventListener mh;
    private String mj;
    private final bs mw;
    private final ak mx;
    private aq my;
    private String mz;

    public av(Context context)
    {
        this(context, ak.aF());
    }

    public av(Context context, ak ak1)
    {
        mw = new bs();
        mContext = context;
        mx = ak1;
    }

    private void k(String s)
        throws RemoteException
    {
        if (mj == null)
        {
            l(s);
        }
        my = ah.a(mContext, new al(), mj, mw);
        if (lQ != null)
        {
            my.a(new ag(lQ));
        }
        if (mh != null)
        {
            my.a(new an(mh));
        }
        if (mB != null)
        {
            my.a(new dh(mB));
        }
        if (mC != null)
        {
            my.a(new dl(mC), mz);
        }
    }

    private void l(String s)
    {
        if (my == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("The ad unit ID must be set on InterstitialAd before ").append(s).append(" is called.").toString());
        } else
        {
            return;
        }
    }

    public void a(at at1)
    {
        try
        {
            if (my == null)
            {
                k("loadAd");
            }
            if (my.a(mx.a(mContext, at1)))
            {
                mw.c(at1.aI());
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to load ad.", remoteexception);
        }
    }

    public AdListener getAdListener()
    {
        return lQ;
    }

    public String getAdUnitId()
    {
        return mj;
    }

    public AppEventListener getAppEventListener()
    {
        return mh;
    }

    public InAppPurchaseListener getInAppPurchaseListener()
    {
        return mB;
    }

    public boolean isLoaded()
    {
        if (my == null)
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = my.isReady();
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to check if ad is ready.", remoteexception);
            return false;
        }
        return flag;
    }

    public void setAdListener(AdListener adlistener)
    {
        lQ = adlistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (adlistener == null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        ag ag1 = new ag(adlistener);
_L3:
        aq1.a(ag1);
_L2:
        return;
        ag1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the AdListener.", remoteexception);
        return;
    }

    public void setAdUnitId(String s)
    {
        if (mj != null)
        {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        } else
        {
            mj = s;
            return;
        }
    }

    public void setAppEventListener(AppEventListener appeventlistener)
    {
        mh = appeventlistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (appeventlistener == null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        an an1 = new an(appeventlistener);
_L3:
        aq1.a(an1);
_L2:
        return;
        an1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the AppEventListener.", remoteexception);
        return;
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inapppurchaselistener)
    {
        if (mC != null)
        {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        mB = inapppurchaselistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (inapppurchaselistener == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        dh dh1 = new dh(inapppurchaselistener);
_L3:
        aq1.a(dh1);
_L2:
        return;
        dh1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the InAppPurchaseListener.", remoteexception);
        return;
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playstorepurchaselistener, String s)
    {
        mC = playstorepurchaselistener;
        if (my == null) goto _L2; else goto _L1
_L1:
        aq aq1 = my;
        if (playstorepurchaselistener == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        dl dl1 = new dl(playstorepurchaselistener);
_L3:
        aq1.a(dl1, s);
_L2:
        return;
        dl1 = null;
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        eu.c("Failed to set the play store purchase parameter.", remoteexception);
        return;
    }

    public void show()
    {
        try
        {
            l("show");
            my.showInterstitial();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Failed to show interstitial.", remoteexception);
        }
    }
}
