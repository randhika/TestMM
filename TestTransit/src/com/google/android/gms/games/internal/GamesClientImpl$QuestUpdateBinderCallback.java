// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            AbstractGamesCallbacks, GamesClientImpl

private final class Ou extends AbstractGamesCallbacks
{

    final GamesClientImpl NE;
    private final QuestUpdateListener Ou;

    private Quest R(DataHolder dataholder)
    {
        QuestBuffer questbuffer = new QuestBuffer(dataholder);
        int i = questbuffer.getCount();
        Quest quest;
        quest = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        quest = (Quest)((Quest)questbuffer.get(0)).freeze();
        questbuffer.close();
        return quest;
        Exception exception;
        exception;
        questbuffer.close();
        throw exception;
    }

    public void M(DataHolder dataholder)
    {
        Quest quest = R(dataholder);
        if (quest != null)
        {
            NE.a(new it>(NE, Ou, quest));
        }
    }

    (GamesClientImpl gamesclientimpl, QuestUpdateListener questupdatelistener)
    {
        NE = gamesclientimpl;
        super();
        Ou = questupdatelistener;
    }
}
