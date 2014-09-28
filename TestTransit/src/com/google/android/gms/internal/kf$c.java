// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            kh, kf

private static final class yR extends yR
{

    private final com.google.android.gms.common.api.s yR;

    public void a(int i, Bundle bundle, int j, Intent intent)
    {
        PendingIntent pendingintent;
        Status status;
        if (bundle != null)
        {
            pendingintent = (PendingIntent)bundle.getParcelable("pendingIntent");
        } else
        {
            pendingintent = null;
        }
        status = new Status(i, null, pendingintent);
        yR.(new kh(status, intent));
    }

    public (com.google.android.gms.common.api.s s)
    {
        yR = s;
    }
}
