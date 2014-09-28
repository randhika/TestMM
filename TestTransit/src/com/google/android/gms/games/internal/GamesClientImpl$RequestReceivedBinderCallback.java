// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            AbstractGamesCallbacks, GamesClientImpl

private final class Oz extends AbstractGamesCallbacks
{

    final GamesClientImpl NE;
    private final OnRequestReceivedListener Oz;

    public void o(DataHolder dataholder)
    {
        GameRequestBuffer gamerequestbuffer = new GameRequestBuffer(dataholder);
        int i = gamerequestbuffer.getCount();
        GameRequest gamerequest;
        gamerequest = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        gamerequest = (GameRequest)((GameRequest)gamerequestbuffer.get(0)).freeze();
        gamerequestbuffer.close();
        if (gamerequest != null)
        {
            NE.a(new (NE, Oz, gamerequest));
        }
        return;
        Exception exception;
        exception;
        gamerequestbuffer.close();
        throw exception;
    }

    public void onRequestRemoved(String s)
    {
        NE.a(new NE(NE, Oz, s));
    }

    Y(GamesClientImpl gamesclientimpl, OnRequestReceivedListener onrequestreceivedlistener)
    {
        NE = gamesclientimpl;
        super();
        Oz = onrequestreceivedlistener;
    }
}
