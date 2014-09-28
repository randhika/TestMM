// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.text.TextUtils;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.wallet:
//            NotifyTransactionStatusRequest

public final class <init>
{

    final NotifyTransactionStatusRequest ajS;

    public NotifyTransactionStatusRequest build()
    {
        boolean flag = true;
        boolean flag1;
        if (!TextUtils.isEmpty(ajS.aiQ))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.b(flag1, "googleTransactionId is required");
        if (ajS.status < flag || ajS.status > 8)
        {
            flag = false;
        }
        hm.b(flag, "status is an unrecognized value");
        return ajS;
    }

    public ajS setDetailedReason(String s)
    {
        ajS.ajR = s;
        return this;
    }

    public ajS setGoogleTransactionId(String s)
    {
        ajS.aiQ = s;
        return this;
    }

    public ajS setStatus(int i)
    {
        ajS.status = i;
        return this;
    }

    private (NotifyTransactionStatusRequest notifytransactionstatusrequest)
    {
        ajS = notifytransactionstatusrequest;
        super();
    }

    ajS(NotifyTransactionStatusRequest notifytransactionstatusrequest, ajS ajs)
    {
        this(notifytransactionstatusrequest);
    }
}
