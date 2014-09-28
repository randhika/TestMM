// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            AbstractGamesCallbacks, GamesClientImpl

private final class NO extends AbstractGamesCallbacks
{

    final GamesClientImpl NE;
    private final OnInvitationReceivedListener NO;

    public void n(DataHolder dataholder)
    {
        InvitationBuffer invitationbuffer = new InvitationBuffer(dataholder);
        int i = invitationbuffer.getCount();
        Invitation invitation;
        invitation = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        invitation = (Invitation)((Invitation)invitationbuffer.get(0)).freeze();
        invitationbuffer.close();
        if (invitation != null)
        {
            NE.a(new (NE, NO, invitation));
        }
        return;
        Exception exception;
        exception;
        invitationbuffer.close();
        throw exception;
    }

    public void onInvitationRemoved(String s)
    {
        NE.a(new NE(NE, NO, s));
    }

    (GamesClientImpl gamesclientimpl, OnInvitationReceivedListener oninvitationreceivedlistener)
    {
        NE = gamesclientimpl;
        super();
        NO = oninvitationreceivedlistener;
    }
}
