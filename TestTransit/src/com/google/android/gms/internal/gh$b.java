// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.internal:
//            gh

private class <init>
    implements com.google.android.gms.common.api.eApiClient.OnConnectionFailedListener
{

    final gh Cc;

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
        gh.j(Cc);
    }

    private ionResult(gh gh1)
    {
        Cc = gh1;
        super();
    }

    Cc(gh gh1, Cc cc)
    {
        this(gh1);
    }
}
