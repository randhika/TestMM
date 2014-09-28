// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            LeaderboardVariant

public final class LeaderboardVariantEntity
    implements LeaderboardVariant
{

    private final int SH;
    private final int SI;
    private final boolean SJ;
    private final long SK;
    private final String SL;
    private final long SM;
    private final String SN;
    private final String SO;
    private final long SP;
    private final String SQ;
    private final String SR;
    private final String SS;

    public LeaderboardVariantEntity(LeaderboardVariant leaderboardvariant)
    {
        SH = leaderboardvariant.getTimeSpan();
        SI = leaderboardvariant.getCollection();
        SJ = leaderboardvariant.hasPlayerInfo();
        SK = leaderboardvariant.getRawPlayerScore();
        SL = leaderboardvariant.getDisplayPlayerScore();
        SM = leaderboardvariant.getPlayerRank();
        SN = leaderboardvariant.getDisplayPlayerRank();
        SO = leaderboardvariant.getPlayerScoreTag();
        SP = leaderboardvariant.getNumScores();
        SQ = leaderboardvariant.iD();
        SR = leaderboardvariant.iE();
        SS = leaderboardvariant.iF();
    }

    static int a(LeaderboardVariant leaderboardvariant)
    {
        Object aobj[] = new Object[11];
        aobj[0] = Integer.valueOf(leaderboardvariant.getTimeSpan());
        aobj[1] = Integer.valueOf(leaderboardvariant.getCollection());
        aobj[2] = Boolean.valueOf(leaderboardvariant.hasPlayerInfo());
        aobj[3] = Long.valueOf(leaderboardvariant.getRawPlayerScore());
        aobj[4] = leaderboardvariant.getDisplayPlayerScore();
        aobj[5] = Long.valueOf(leaderboardvariant.getPlayerRank());
        aobj[6] = leaderboardvariant.getDisplayPlayerRank();
        aobj[7] = Long.valueOf(leaderboardvariant.getNumScores());
        aobj[8] = leaderboardvariant.iD();
        aobj[9] = leaderboardvariant.iF();
        aobj[10] = leaderboardvariant.iE();
        return hk.hashCode(aobj);
    }

    static boolean a(LeaderboardVariant leaderboardvariant, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof LeaderboardVariant))
        {
            flag = false;
        } else
        if (leaderboardvariant != obj)
        {
            LeaderboardVariant leaderboardvariant1 = (LeaderboardVariant)obj;
            if (!hk.equal(Integer.valueOf(leaderboardvariant1.getTimeSpan()), Integer.valueOf(leaderboardvariant.getTimeSpan())) || !hk.equal(Integer.valueOf(leaderboardvariant1.getCollection()), Integer.valueOf(leaderboardvariant.getCollection())) || !hk.equal(Boolean.valueOf(leaderboardvariant1.hasPlayerInfo()), Boolean.valueOf(leaderboardvariant.hasPlayerInfo())) || !hk.equal(Long.valueOf(leaderboardvariant1.getRawPlayerScore()), Long.valueOf(leaderboardvariant.getRawPlayerScore())) || !hk.equal(leaderboardvariant1.getDisplayPlayerScore(), leaderboardvariant.getDisplayPlayerScore()) || !hk.equal(Long.valueOf(leaderboardvariant1.getPlayerRank()), Long.valueOf(leaderboardvariant.getPlayerRank())) || !hk.equal(leaderboardvariant1.getDisplayPlayerRank(), leaderboardvariant.getDisplayPlayerRank()) || !hk.equal(Long.valueOf(leaderboardvariant1.getNumScores()), Long.valueOf(leaderboardvariant.getNumScores())) || !hk.equal(leaderboardvariant1.iD(), leaderboardvariant.iD()) || !hk.equal(leaderboardvariant1.iF(), leaderboardvariant.iF()) || !hk.equal(leaderboardvariant1.iE(), leaderboardvariant.iE()))
            {
                return false;
            }
        }
        return flag;
    }

    static String b(LeaderboardVariant leaderboardvariant)
    {
        com.google.android.gms.internal.hk.a a1 = hk.e(leaderboardvariant).a("TimeSpan", TimeSpan.cm(leaderboardvariant.getTimeSpan())).a("Collection", LeaderboardCollection.cm(leaderboardvariant.getCollection()));
        Object obj;
        com.google.android.gms.internal.hk.a a2;
        String s;
        com.google.android.gms.internal.hk.a a3;
        Object obj1;
        com.google.android.gms.internal.hk.a a4;
        String s1;
        if (leaderboardvariant.hasPlayerInfo())
        {
            obj = Long.valueOf(leaderboardvariant.getRawPlayerScore());
        } else
        {
            obj = "none";
        }
        a2 = a1.a("RawPlayerScore", obj);
        if (leaderboardvariant.hasPlayerInfo())
        {
            s = leaderboardvariant.getDisplayPlayerScore();
        } else
        {
            s = "none";
        }
        a3 = a2.a("DisplayPlayerScore", s);
        if (leaderboardvariant.hasPlayerInfo())
        {
            obj1 = Long.valueOf(leaderboardvariant.getPlayerRank());
        } else
        {
            obj1 = "none";
        }
        a4 = a3.a("PlayerRank", obj1);
        if (leaderboardvariant.hasPlayerInfo())
        {
            s1 = leaderboardvariant.getDisplayPlayerRank();
        } else
        {
            s1 = "none";
        }
        return a4.a("DisplayPlayerRank", s1).a("NumScores", Long.valueOf(leaderboardvariant.getNumScores())).a("TopPageNextToken", leaderboardvariant.iD()).a("WindowPageNextToken", leaderboardvariant.iF()).a("WindowPagePrevToken", leaderboardvariant.iE()).toString();
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Object freeze()
    {
        return iG();
    }

    public int getCollection()
    {
        return SI;
    }

    public String getDisplayPlayerRank()
    {
        return SN;
    }

    public String getDisplayPlayerScore()
    {
        return SL;
    }

    public long getNumScores()
    {
        return SP;
    }

    public long getPlayerRank()
    {
        return SM;
    }

    public String getPlayerScoreTag()
    {
        return SO;
    }

    public long getRawPlayerScore()
    {
        return SK;
    }

    public int getTimeSpan()
    {
        return SH;
    }

    public boolean hasPlayerInfo()
    {
        return SJ;
    }

    public int hashCode()
    {
        return a(this);
    }

    public String iD()
    {
        return SQ;
    }

    public String iE()
    {
        return SR;
    }

    public String iF()
    {
        return SS;
    }

    public LeaderboardVariant iG()
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
