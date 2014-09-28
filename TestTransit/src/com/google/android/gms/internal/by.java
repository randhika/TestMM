// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

// Referenced classes of package com.google.android.gms.internal:
//            hm, eu, bv

public final class by
    implements MediationBannerListener, MediationInterstitialListener
{

    private final bv nT;

    public by(bv bv1)
    {
        nT = bv1;
    }

    public void onAdClicked(MediationBannerAdapter mediationbanneradapter)
    {
        hm.ay("onAdClicked must be called on the main UI thread.");
        eu.z("Adapter called onAdClicked.");
        try
        {
            nT.onAdClicked();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClicked.", remoteexception);
        }
    }

    public void onAdClicked(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        hm.ay("onAdClicked must be called on the main UI thread.");
        eu.z("Adapter called onAdClicked.");
        try
        {
            nT.onAdClicked();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClicked.", remoteexception);
        }
    }

    public void onAdClosed(MediationBannerAdapter mediationbanneradapter)
    {
        hm.ay("onAdClosed must be called on the main UI thread.");
        eu.z("Adapter called onAdClosed.");
        try
        {
            nT.onAdClosed();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClosed.", remoteexception);
        }
    }

    public void onAdClosed(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        hm.ay("onAdClosed must be called on the main UI thread.");
        eu.z("Adapter called onAdClosed.");
        try
        {
            nT.onAdClosed();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdClosed.", remoteexception);
        }
    }

    public void onAdFailedToLoad(MediationBannerAdapter mediationbanneradapter, int i)
    {
        hm.ay("onAdFailedToLoad must be called on the main UI thread.");
        eu.z((new StringBuilder()).append("Adapter called onAdFailedToLoad with error. ").append(i).toString());
        try
        {
            nT.onAdFailedToLoad(i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public void onAdFailedToLoad(MediationInterstitialAdapter mediationinterstitialadapter, int i)
    {
        hm.ay("onAdFailedToLoad must be called on the main UI thread.");
        eu.z((new StringBuilder()).append("Adapter called onAdFailedToLoad with error ").append(i).append(".").toString());
        try
        {
            nT.onAdFailedToLoad(i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdFailedToLoad.", remoteexception);
        }
    }

    public void onAdLeftApplication(MediationBannerAdapter mediationbanneradapter)
    {
        hm.ay("onAdLeftApplication must be called on the main UI thread.");
        eu.z("Adapter called onAdLeftApplication.");
        try
        {
            nT.onAdLeftApplication();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public void onAdLeftApplication(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        hm.ay("onAdLeftApplication must be called on the main UI thread.");
        eu.z("Adapter called onAdLeftApplication.");
        try
        {
            nT.onAdLeftApplication();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLeftApplication.", remoteexception);
        }
    }

    public void onAdLoaded(MediationBannerAdapter mediationbanneradapter)
    {
        hm.ay("onAdLoaded must be called on the main UI thread.");
        eu.z("Adapter called onAdLoaded.");
        try
        {
            nT.onAdLoaded();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLoaded.", remoteexception);
        }
    }

    public void onAdLoaded(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        hm.ay("onAdLoaded must be called on the main UI thread.");
        eu.z("Adapter called onAdLoaded.");
        try
        {
            nT.onAdLoaded();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdLoaded.", remoteexception);
        }
    }

    public void onAdOpened(MediationBannerAdapter mediationbanneradapter)
    {
        hm.ay("onAdOpened must be called on the main UI thread.");
        eu.z("Adapter called onAdOpened.");
        try
        {
            nT.onAdOpened();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdOpened.", remoteexception);
        }
    }

    public void onAdOpened(MediationInterstitialAdapter mediationinterstitialadapter)
    {
        hm.ay("onAdOpened must be called on the main UI thread.");
        eu.z("Adapter called onAdOpened.");
        try
        {
            nT.onAdOpened();
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not call onAdOpened.", remoteexception);
        }
    }
}
