// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.internal.gh;

// Referenced classes of package com.google.android.gms.cast:
//            Cast

class zV extends zV
{

    final String zU;
    final String zV;
    final N zW;

    protected volatile void a(com.google.android.gms.common.api. )
        throws RemoteException
    {
        a((gh));
    }

    protected void a(gh gh1)
        throws RemoteException
    {
        try
        {
            gh1.a(zU, zV, this);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            N(2001);
            return;
        }
        catch (IllegalStateException illegalstateexception)
        {
            N(2001);
        }
    }

    ( , String s, String s1)
    {
        zW = ;
        zU = s;
        zV = s1;
        super(null);
    }
}
