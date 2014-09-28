// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.games.internal.events.EventIncrementCache;

// Referenced classes of package com.google.android.gms.games.internal:
//            GamesClientImpl, IGamesService, GamesLog

private class NE extends EventIncrementCache
{

    final GamesClientImpl NE;

    protected void o(String s, int i)
    {
        try
        {
            ((IGamesService)NE.ft()).l(s, i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Y(GamesClientImpl gamesclientimpl)
    {
        NE = gamesclientimpl;
        super(gamesclientimpl.getContext().getMainLooper(), 1000);
    }
}
