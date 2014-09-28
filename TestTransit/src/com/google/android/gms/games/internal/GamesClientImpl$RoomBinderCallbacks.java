// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.games.internal:
//            AbstractGamesCallbacks, GamesClientImpl

private final class OI extends AbstractGamesCallbacks
{

    final GamesClientImpl NE;
    private final RoomUpdateListener OG;
    private final RoomStatusUpdateListener OH;
    private final RealTimeMessageReceivedListener OI;

    public void A(DataHolder dataholder)
    {
        NE.a(new Callback(NE, OH, dataholder));
    }

    public void a(DataHolder dataholder, String as[])
    {
        NE.a(new lback(NE, OH, dataholder, as));
    }

    public void b(DataHolder dataholder, String as[])
    {
        NE.a(new ck(NE, OH, dataholder, as));
    }

    public void c(DataHolder dataholder, String as[])
    {
        NE.a(new (NE, OH, dataholder, as));
    }

    public void d(DataHolder dataholder, String as[])
    {
        NE.a(new (NE, OH, dataholder, as));
    }

    public void e(DataHolder dataholder, String as[])
    {
        NE.a(new k(NE, OH, dataholder, as));
    }

    public void f(DataHolder dataholder, String as[])
    {
        NE.a(new back(NE, OH, dataholder, as));
    }

    public void onLeftRoom(int i, String s)
    {
        NE.a(new it>(NE, OG, i, s));
    }

    public void onP2PConnected(String s)
    {
        NE.a(new (NE, OH, s));
    }

    public void onP2PDisconnected(String s)
    {
        NE.a(new ack(NE, OH, s));
    }

    public void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
    {
        NE.a(new ack(NE, OI, realtimemessage));
    }

    public void u(DataHolder dataholder)
    {
        NE.a(new <init>(NE, OG, dataholder));
    }

    public void v(DataHolder dataholder)
    {
        NE.a(new init>(NE, OG, dataholder));
    }

    public void w(DataHolder dataholder)
    {
        NE.a(new ck(NE, OH, dataholder));
    }

    public void x(DataHolder dataholder)
    {
        NE.a(new back(NE, OH, dataholder));
    }

    public void y(DataHolder dataholder)
    {
        NE.a(new k(NE, OG, dataholder));
    }

    public void z(DataHolder dataholder)
    {
        NE.a(new ack(NE, OH, dataholder));
    }

    public istener(GamesClientImpl gamesclientimpl, RoomUpdateListener roomupdatelistener)
    {
        NE = gamesclientimpl;
        super();
        OG = (RoomUpdateListener)hm.b(roomupdatelistener, "Callbacks must not be null");
        OH = null;
        OI = null;
    }

    public istener(GamesClientImpl gamesclientimpl, RoomUpdateListener roomupdatelistener, RoomStatusUpdateListener roomstatusupdatelistener, RealTimeMessageReceivedListener realtimemessagereceivedlistener)
    {
        NE = gamesclientimpl;
        super();
        OG = (RoomUpdateListener)hm.b(roomupdatelistener, "Callbacks must not be null");
        OH = roomstatusupdatelistener;
        OI = realtimemessagereceivedlistener;
    }
}
