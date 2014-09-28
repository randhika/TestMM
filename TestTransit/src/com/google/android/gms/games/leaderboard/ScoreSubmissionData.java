// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;
import java.util.HashMap;

public final class ScoreSubmissionData
{
    public static final class Result
    {

        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public String toString()
        {
            return hk.e(this).a("RawScore", Long.valueOf(rawScore)).a("FormattedScore", formattedScore).a("ScoreTag", scoreTag).a("NewBest", Boolean.valueOf(newBest)).toString();
        }

        public Result(long l, String s, String s1, boolean flag)
        {
            rawScore = l;
            formattedScore = s;
            scoreTag = s1;
            newBest = flag;
        }
    }


    private static final String Sn[] = {
        "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"
    };
    private int CT;
    private String MS;
    private HashMap ST;
    private String Sp;

    public ScoreSubmissionData(DataHolder dataholder)
    {
        CT = dataholder.getStatusCode();
        ST = new HashMap();
        int i = dataholder.getCount();
        boolean flag;
        if (i == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.C(flag);
        for (int j = 0; j < i; j++)
        {
            int k = dataholder.ae(j);
            if (j == 0)
            {
                Sp = dataholder.c("leaderboardId", j, k);
                MS = dataholder.c("playerId", j, k);
            }
            if (dataholder.d("hasResult", j, k))
            {
                a(new Result(dataholder.a("rawScore", j, k), dataholder.c("formattedScore", j, k), dataholder.c("scoreTag", j, k), dataholder.d("newBest", j, k)), dataholder.b("timeSpan", j, k));
            }
        }

    }

    private void a(Result result, int i)
    {
        ST.put(Integer.valueOf(i), result);
    }

    public String getLeaderboardId()
    {
        return Sp;
    }

    public String getPlayerId()
    {
        return MS;
    }

    public Result getScoreResult(int i)
    {
        return (Result)ST.get(Integer.valueOf(i));
    }

    public String toString()
    {
        com.google.android.gms.internal.hk.a a1 = hk.e(this).a("PlayerId", MS).a("StatusCode", Integer.valueOf(CT));
        int i = 0;
        while (i < 3) 
        {
            Result result = (Result)ST.get(Integer.valueOf(i));
            a1.a("TimesSpan", TimeSpan.cm(i));
            String s;
            if (result == null)
            {
                s = "null";
            } else
            {
                s = result.toString();
            }
            a1.a("Result", s);
            i++;
        }
        return a1.toString();
    }

}
