// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

public final class AdMobExtras
    implements NetworkExtras
{

    private final Bundle mExtras;

    public AdMobExtras(Bundle bundle)
    {
        Bundle bundle1;
        if (bundle != null)
        {
            bundle1 = new Bundle(bundle);
        } else
        {
            bundle1 = null;
        }
        mExtras = bundle1;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }
}
