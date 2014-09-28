// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;
import com.google.android.gms.internal.ik;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            LeaderboardScore

public final class LeaderboardScoreEntity
    implements LeaderboardScore
{

    private final Uri SA;
    private final Uri SB;
    private final PlayerEntity SC;
    private final String SD;
    private final String SE;
    private final String SF;
    private final long Su;
    private final String Sv;
    private final String Sw;
    private final long Sx;
    private final long Sy;
    private final String Sz;

    public LeaderboardScoreEntity(LeaderboardScore leaderboardscore)
    {
        Su = leaderboardscore.getRank();
        Sv = (String)hm.f(leaderboardscore.getDisplayRank());
        Sw = (String)hm.f(leaderboardscore.getDisplayScore());
        Sx = leaderboardscore.getRawScore();
        Sy = leaderboardscore.getTimestampMillis();
        Sz = leaderboardscore.getScoreHolderDisplayName();
        SA = leaderboardscore.getScoreHolderIconImageUri();
        SB = leaderboardscore.getScoreHolderHiResImageUri();
        Player player = leaderboardscore.getScoreHolder();
        PlayerEntity playerentity;
        if (player == null)
        {
            playerentity = null;
        } else
        {
            playerentity = (PlayerEntity)player.freeze();
        }
        SC = playerentity;
        SD = leaderboardscore.getScoreTag();
        SE = leaderboardscore.getScoreHolderIconImageUrl();
        SF = leaderboardscore.getScoreHolderHiResImageUrl();
    }

    static int a(LeaderboardScore leaderboardscore)
    {
        Object aobj[] = new Object[9];
        aobj[0] = Long.valueOf(leaderboardscore.getRank());
        aobj[1] = leaderboardscore.getDisplayRank();
        aobj[2] = Long.valueOf(leaderboardscore.getRawScore());
        aobj[3] = leaderboardscore.getDisplayScore();
        aobj[4] = Long.valueOf(leaderboardscore.getTimestampMillis());
        aobj[5] = leaderboardscore.getScoreHolderDisplayName();
        aobj[6] = leaderboardscore.getScoreHolderIconImageUri();
        aobj[7] = leaderboardscore.getScoreHolderHiResImageUri();
        aobj[8] = leaderboardscore.getScoreHolder();
        return hk.hashCode(aobj);
    }

    static boolean a(LeaderboardScore leaderboardscore, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof LeaderboardScore))
        {
            flag = false;
        } else
        if (leaderboardscore != obj)
        {
            LeaderboardScore leaderboardscore1 = (LeaderboardScore)obj;
            if (!hk.equal(Long.valueOf(leaderboardscore1.getRank()), Long.valueOf(leaderboardscore.getRank())) || !hk.equal(leaderboardscore1.getDisplayRank(), leaderboardscore.getDisplayRank()) || !hk.equal(Long.valueOf(leaderboardscore1.getRawScore()), Long.valueOf(leaderboardscore.getRawScore())) || !hk.equal(leaderboardscore1.getDisplayScore(), leaderboardscore.getDisplayScore()) || !hk.equal(Long.valueOf(leaderboardscore1.getTimestampMillis()), Long.valueOf(leaderboardscore.getTimestampMillis())) || !hk.equal(leaderboardscore1.getScoreHolderDisplayName(), leaderboardscore.getScoreHolderDisplayName()) || !hk.equal(leaderboardscore1.getScoreHolderIconImageUri(), leaderboardscore.getScoreHolderIconImageUri()) || !hk.equal(leaderboardscore1.getScoreHolderHiResImageUri(), leaderboardscore.getScoreHolderHiResImageUri()) || !hk.equal(leaderboardscore1.getScoreHolder(), leaderboardscore.getScoreHolder()) || !hk.equal(leaderboardscore1.getScoreTag(), leaderboardscore.getScoreTag()))
            {
                return false;
            }
        }
        return flag;
    }

    static String b(LeaderboardScore leaderboardscore)
    {
        com.google.android.gms.internal.hk.a a1 = hk.e(leaderboardscore).a("Rank", Long.valueOf(leaderboardscore.getRank())).a("DisplayRank", leaderboardscore.getDisplayRank()).a("Score", Long.valueOf(leaderboardscore.getRawScore())).a("DisplayScore", leaderboardscore.getDisplayScore()).a("Timestamp", Long.valueOf(leaderboardscore.getTimestampMillis())).a("DisplayName", leaderboardscore.getScoreHolderDisplayName()).a("IconImageUri", leaderboardscore.getScoreHolderIconImageUri()).a("IconImageUrl", leaderboardscore.getScoreHolderIconImageUrl()).a("HiResImageUri", leaderboardscore.getScoreHolderHiResImageUri()).a("HiResImageUrl", leaderboardscore.getScoreHolderHiResImageUrl());
        Player player;
        if (leaderboardscore.getScoreHolder() == null)
        {
            player = null;
        } else
        {
            player = leaderboardscore.getScoreHolder();
        }
        return a1.a("Player", player).a("ScoreTag", leaderboardscore.getScoreTag()).toString();
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Object freeze()
    {
        return iC();
    }

    public String getDisplayRank()
    {
        return Sv;
    }

    public void getDisplayRank(CharArrayBuffer chararraybuffer)
    {
        ik.b(Sv, chararraybuffer);
    }

    public String getDisplayScore()
    {
        return Sw;
    }

    public void getDisplayScore(CharArrayBuffer chararraybuffer)
    {
        ik.b(Sw, chararraybuffer);
    }

    public long getRank()
    {
        return Su;
    }

    public long getRawScore()
    {
        return Sx;
    }

    public Player getScoreHolder()
    {
        return SC;
    }

    public String getScoreHolderDisplayName()
    {
        if (SC == null)
        {
            return Sz;
        } else
        {
            return SC.getDisplayName();
        }
    }

    public void getScoreHolderDisplayName(CharArrayBuffer chararraybuffer)
    {
        if (SC == null)
        {
            ik.b(Sz, chararraybuffer);
            return;
        } else
        {
            SC.getDisplayName(chararraybuffer);
            return;
        }
    }

    public Uri getScoreHolderHiResImageUri()
    {
        if (SC == null)
        {
            return SB;
        } else
        {
            return SC.getHiResImageUri();
        }
    }

    public String getScoreHolderHiResImageUrl()
    {
        if (SC == null)
        {
            return SF;
        } else
        {
            return SC.getHiResImageUrl();
        }
    }

    public Uri getScoreHolderIconImageUri()
    {
        if (SC == null)
        {
            return SA;
        } else
        {
            return SC.getIconImageUri();
        }
    }

    public String getScoreHolderIconImageUrl()
    {
        if (SC == null)
        {
            return SE;
        } else
        {
            return SC.getIconImageUrl();
        }
    }

    public String getScoreTag()
    {
        return SD;
    }

    public long getTimestampMillis()
    {
        return Sy;
    }

    public int hashCode()
    {
        return a(this);
    }

    public LeaderboardScore iC()
    {
        return this;
    }

    public boolean isDataValid()
    {
        return true;
    }

    public String toString()
    {
        return b(this);
    }
}
