// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.plus.internal:
//            a, e

final class abL extends a
{

    private final com.google.android.gms.common.api.aHolder abL;
    final e abM;

    public void a(DataHolder dataholder, String s, String s1)
    {
        PendingIntent pendingintent;
        Status status;
        DataHolder dataholder1;
        if (dataholder.eU() != null)
        {
            pendingintent = (PendingIntent)dataholder.eU().getParcelable("pendingIntent");
        } else
        {
            pendingintent = null;
        }
        status = new Status(dataholder.getStatusCode(), null, pendingintent);
        if (!status.isSuccess() && dataholder != null)
        {
            if (!dataholder.isClosed())
            {
                dataholder.close();
            }
            dataholder1 = null;
        } else
        {
            dataholder1 = dataholder;
        }
        abM.a(new <init>(abM, abL, status, dataholder1, s, s1));
    }

    public lder(e e1, com.google.android.gms.common.api. )
    {
        abM = e1;
        super();
        abL = ;
    }
}
