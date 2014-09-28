// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import android.app.Fragment;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.internal.ls;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            WalletFragment, WalletFragmentOptions, WalletFragmentStyle

private class <init> extends a
    implements android.view.ner
{

    final WalletFragment akz;

    protected void a(FrameLayout framelayout)
    {
        Button button = new Button(com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity());
        button.setText(com.google.android.gms.uy_button_place_holder);
        int i = -1;
        int j = -2;
        if (WalletFragment.e(akz) != null)
        {
            WalletFragmentStyle walletfragmentstyle = WalletFragment.e(akz).getFragmentStyle();
            if (walletfragmentstyle != null)
            {
                android.util.DisplayMetrics displaymetrics = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getResources().getDisplayMetrics();
                i = walletfragmentstyle.a("buyButtonWidth", displaymetrics, i);
                j = walletfragmentstyle.a("buyButtonHeight", displaymetrics, j);
            }
        }
        button.setLayoutParams(new android.view.arams(i, j));
        button.setOnClickListener(this);
        framelayout.addView(button);
    }

    protected void a(f f1)
    {
        android.app.Activity activity = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity();
        if (WalletFragment.b(akz) == null && WalletFragment.c(akz) && activity != null)
        {
            try
            {
                com.google.android.gms.internal.lk lk = ls.a(activity, WalletFragment.d(akz), WalletFragment.e(akz), com.google.android.gms.wallet.fragment.WalletFragment.f(akz));
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, new <init>(lk, null));
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
            }
            catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
            {
                return;
            }
            f1.a(WalletFragment.b(akz));
            if (WalletFragment.g(akz) != null)
            {
                com.google.android.gms.wallet.fragment.a(WalletFragment.b(akz), WalletFragment.g(akz));
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
            }
            if (WalletFragment.h(akz) != null)
            {
                com.google.android.gms.wallet.fragment.a(WalletFragment.b(akz), WalletFragment.h(akz));
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
            }
            if (WalletFragment.i(akz) != null)
            {
                com.google.android.gms.wallet.fragment.a(WalletFragment.b(akz), WalletFragment.i(akz));
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
            }
            if (WalletFragment.j(akz) != null)
            {
                com.google.android.gms.wallet.fragment.a(WalletFragment.b(akz), WalletFragment.j(akz).booleanValue());
                com.google.android.gms.wallet.fragment.WalletFragment.a(akz, null);
            }
        }
    }

    public void onClick(View view)
    {
        android.app.Activity activity = com.google.android.gms.wallet.fragment.WalletFragment.a(akz).getActivity();
        GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
    }

    private ableException(WalletFragment walletfragment)
    {
        akz = walletfragment;
        super();
    }

    akz(WalletFragment walletfragment, akz akz1)
    {
        this(walletfragment);
    }
}
