// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.internal.hm;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

// Referenced classes of package com.google.android.gms.wallet.fragment:
//            WalletFragmentInitParams

public final class <init>
{

    final WalletFragmentInitParams akB;

    public WalletFragmentInitParams build()
    {
        boolean flag = true;
        boolean flag1;
        if (WalletFragmentInitParams.a(akB) != null && WalletFragmentInitParams.b(akB) == null || WalletFragmentInitParams.a(akB) == null && WalletFragmentInitParams.b(akB) != null)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.a(flag1, "Exactly one of MaskedWallet or MaskedWalletRequest is required");
        if (WalletFragmentInitParams.c(akB) < 0)
        {
            flag = false;
        }
        hm.a(flag, "masked wallet request code is required and must be non-negative");
        return akB;
    }

    public akB setAccountName(String s)
    {
        WalletFragmentInitParams.a(akB, s);
        return this;
    }

    public akB setMaskedWallet(MaskedWallet maskedwallet)
    {
        WalletFragmentInitParams.a(akB, maskedwallet);
        return this;
    }

    public akB setMaskedWalletRequest(MaskedWalletRequest maskedwalletrequest)
    {
        WalletFragmentInitParams.a(akB, maskedwalletrequest);
        return this;
    }

    public akB setMaskedWalletRequestCode(int i)
    {
        WalletFragmentInitParams.a(akB, i);
        return this;
    }

    private (WalletFragmentInitParams walletfragmentinitparams)
    {
        akB = walletfragmentinitparams;
        super();
    }

    akB(WalletFragmentInitParams walletfragmentinitparams, akB akb)
    {
        this(walletfragmentinitparams);
    }
}
