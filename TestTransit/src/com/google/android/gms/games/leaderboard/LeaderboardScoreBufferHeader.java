// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader
{
    public static final class Builder
    {

        private Builder()
        {
        }
    }


    private final Bundle HM;

    public LeaderboardScoreBufferHeader(Bundle bundle)
    {
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        HM = bundle;
    }

    public Bundle iB()
    {
        return HM;
    }
}
