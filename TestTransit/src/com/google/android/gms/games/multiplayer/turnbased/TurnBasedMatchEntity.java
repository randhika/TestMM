// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.multiplayer.turnbased:
//            TurnBasedMatch, TurnBasedMatchEntityCreator

public final class TurnBasedMatchEntity
    implements SafeParcelable, TurnBasedMatch
{

    public static final TurnBasedMatchEntityCreator CREATOR = new TurnBasedMatchEntityCreator();
    private final String Mp;
    private final String Oi;
    private final GameEntity Rt;
    private final long SU;
    private final ArrayList SX;
    private final int SY;
    private final String TA;
    private final long TB;
    private final String TC;
    private final int TD;
    private final int TE;
    private final byte TF[];
    private final String TG;
    private final byte TH[];
    private final int TI;
    private final int TJ;
    private final boolean TK;
    private final String TL;
    private final Bundle To;
    private final String Ts;
    private final int xM;

    TurnBasedMatchEntity(int i, GameEntity gameentity, String s, String s1, long l, String s2, 
            long l1, String s3, int j, int k, int i1, byte abyte0[], 
            ArrayList arraylist, String s4, byte abyte1[], int j1, Bundle bundle, int k1, boolean flag, 
            String s5, String s6)
    {
        xM = i;
        Rt = gameentity;
        Oi = s;
        Ts = s1;
        SU = l;
        TA = s2;
        TB = l1;
        TC = s3;
        TD = j;
        TJ = k1;
        SY = k;
        TE = i1;
        TF = abyte0;
        SX = arraylist;
        TG = s4;
        TH = abyte1;
        TI = j1;
        To = bundle;
        TK = flag;
        Mp = s5;
        TL = s6;
    }

    public TurnBasedMatchEntity(TurnBasedMatch turnbasedmatch)
    {
        xM = 2;
        Rt = new GameEntity(turnbasedmatch.getGame());
        Oi = turnbasedmatch.getMatchId();
        Ts = turnbasedmatch.getCreatorId();
        SU = turnbasedmatch.getCreationTimestamp();
        TA = turnbasedmatch.getLastUpdaterId();
        TB = turnbasedmatch.getLastUpdatedTimestamp();
        TC = turnbasedmatch.getPendingParticipantId();
        TD = turnbasedmatch.getStatus();
        TJ = turnbasedmatch.getTurnStatus();
        SY = turnbasedmatch.getVariant();
        TE = turnbasedmatch.getVersion();
        TG = turnbasedmatch.getRematchId();
        TI = turnbasedmatch.getMatchNumber();
        To = turnbasedmatch.getAutoMatchCriteria();
        TK = turnbasedmatch.isLocallyModified();
        Mp = turnbasedmatch.getDescription();
        TL = turnbasedmatch.getDescriptionParticipantId();
        byte abyte0[] = turnbasedmatch.getData();
        byte abyte1[];
        ArrayList arraylist;
        int i;
        if (abyte0 == null)
        {
            TF = null;
        } else
        {
            TF = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, TF, 0, abyte0.length);
        }
        abyte1 = turnbasedmatch.getPreviousMatchData();
        if (abyte1 == null)
        {
            TH = null;
        } else
        {
            TH = new byte[abyte1.length];
            System.arraycopy(abyte1, 0, TH, 0, abyte1.length);
        }
        arraylist = turnbasedmatch.getParticipants();
        i = arraylist.size();
        SX = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            SX.add((ParticipantEntity)(ParticipantEntity)((Participant)arraylist.get(j)).freeze());
        }

    }

    static int a(TurnBasedMatch turnbasedmatch)
    {
        Object aobj[] = new Object[18];
        aobj[0] = turnbasedmatch.getGame();
        aobj[1] = turnbasedmatch.getMatchId();
        aobj[2] = turnbasedmatch.getCreatorId();
        aobj[3] = Long.valueOf(turnbasedmatch.getCreationTimestamp());
        aobj[4] = turnbasedmatch.getLastUpdaterId();
        aobj[5] = Long.valueOf(turnbasedmatch.getLastUpdatedTimestamp());
        aobj[6] = turnbasedmatch.getPendingParticipantId();
        aobj[7] = Integer.valueOf(turnbasedmatch.getStatus());
        aobj[8] = Integer.valueOf(turnbasedmatch.getTurnStatus());
        aobj[9] = turnbasedmatch.getDescription();
        aobj[10] = Integer.valueOf(turnbasedmatch.getVariant());
        aobj[11] = Integer.valueOf(turnbasedmatch.getVersion());
        aobj[12] = turnbasedmatch.getParticipants();
        aobj[13] = turnbasedmatch.getRematchId();
        aobj[14] = Integer.valueOf(turnbasedmatch.getMatchNumber());
        aobj[15] = turnbasedmatch.getAutoMatchCriteria();
        aobj[16] = Integer.valueOf(turnbasedmatch.getAvailableAutoMatchSlots());
        aobj[17] = Boolean.valueOf(turnbasedmatch.isLocallyModified());
        return hk.hashCode(aobj);
    }

    static int a(TurnBasedMatch turnbasedmatch, String s)
    {
        ArrayList arraylist = turnbasedmatch.getParticipants();
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            Participant participant = (Participant)arraylist.get(j);
            if (participant.getParticipantId().equals(s))
            {
                return participant.getStatus();
            }
        }

        throw new IllegalStateException((new StringBuilder()).append("Participant ").append(s).append(" is not in match ").append(turnbasedmatch.getMatchId()).toString());
    }

    static boolean a(TurnBasedMatch turnbasedmatch, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof TurnBasedMatch))
        {
            flag = false;
        } else
        if (turnbasedmatch != obj)
        {
            TurnBasedMatch turnbasedmatch1 = (TurnBasedMatch)obj;
            if (!hk.equal(turnbasedmatch1.getGame(), turnbasedmatch.getGame()) || !hk.equal(turnbasedmatch1.getMatchId(), turnbasedmatch.getMatchId()) || !hk.equal(turnbasedmatch1.getCreatorId(), turnbasedmatch.getCreatorId()) || !hk.equal(Long.valueOf(turnbasedmatch1.getCreationTimestamp()), Long.valueOf(turnbasedmatch.getCreationTimestamp())) || !hk.equal(turnbasedmatch1.getLastUpdaterId(), turnbasedmatch.getLastUpdaterId()) || !hk.equal(Long.valueOf(turnbasedmatch1.getLastUpdatedTimestamp()), Long.valueOf(turnbasedmatch.getLastUpdatedTimestamp())) || !hk.equal(turnbasedmatch1.getPendingParticipantId(), turnbasedmatch.getPendingParticipantId()) || !hk.equal(Integer.valueOf(turnbasedmatch1.getStatus()), Integer.valueOf(turnbasedmatch.getStatus())) || !hk.equal(Integer.valueOf(turnbasedmatch1.getTurnStatus()), Integer.valueOf(turnbasedmatch.getTurnStatus())) || !hk.equal(turnbasedmatch1.getDescription(), turnbasedmatch.getDescription()) || !hk.equal(Integer.valueOf(turnbasedmatch1.getVariant()), Integer.valueOf(turnbasedmatch.getVariant())) || !hk.equal(Integer.valueOf(turnbasedmatch1.getVersion()), Integer.valueOf(turnbasedmatch.getVersion())) || !hk.equal(turnbasedmatch1.getParticipants(), turnbasedmatch.getParticipants()) || !hk.equal(turnbasedmatch1.getRematchId(), turnbasedmatch.getRematchId()) || !hk.equal(Integer.valueOf(turnbasedmatch1.getMatchNumber()), Integer.valueOf(turnbasedmatch.getMatchNumber())) || !hk.equal(turnbasedmatch1.getAutoMatchCriteria(), turnbasedmatch.getAutoMatchCriteria()) || !hk.equal(Integer.valueOf(turnbasedmatch1.getAvailableAutoMatchSlots()), Integer.valueOf(turnbasedmatch.getAvailableAutoMatchSlots())) || !hk.equal(Boolean.valueOf(turnbasedmatch1.isLocallyModified()), Boolean.valueOf(turnbasedmatch.isLocallyModified())))
            {
                return false;
            }
        }
        return flag;
    }

    static String b(TurnBasedMatch turnbasedmatch)
    {
        return hk.e(turnbasedmatch).a("Game", turnbasedmatch.getGame()).a("MatchId", turnbasedmatch.getMatchId()).a("CreatorId", turnbasedmatch.getCreatorId()).a("CreationTimestamp", Long.valueOf(turnbasedmatch.getCreationTimestamp())).a("LastUpdaterId", turnbasedmatch.getLastUpdaterId()).a("LastUpdatedTimestamp", Long.valueOf(turnbasedmatch.getLastUpdatedTimestamp())).a("PendingParticipantId", turnbasedmatch.getPendingParticipantId()).a("MatchStatus", Integer.valueOf(turnbasedmatch.getStatus())).a("TurnStatus", Integer.valueOf(turnbasedmatch.getTurnStatus())).a("Description", turnbasedmatch.getDescription()).a("Variant", Integer.valueOf(turnbasedmatch.getVariant())).a("Data", turnbasedmatch.getData()).a("Version", Integer.valueOf(turnbasedmatch.getVersion())).a("Participants", turnbasedmatch.getParticipants()).a("RematchId", turnbasedmatch.getRematchId()).a("PreviousData", turnbasedmatch.getPreviousMatchData()).a("MatchNumber", Integer.valueOf(turnbasedmatch.getMatchNumber())).a("AutoMatchCriteria", turnbasedmatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", Integer.valueOf(turnbasedmatch.getAvailableAutoMatchSlots())).a("LocallyModified", Boolean.valueOf(turnbasedmatch.isLocallyModified())).a("DescriptionParticipantId", turnbasedmatch.getDescriptionParticipantId()).toString();
    }

    static String b(TurnBasedMatch turnbasedmatch, String s)
    {
        ArrayList arraylist = turnbasedmatch.getParticipants();
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            Participant participant = (Participant)arraylist.get(j);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s))
            {
                return participant.getParticipantId();
            }
        }

        return null;
    }

    static Participant c(TurnBasedMatch turnbasedmatch, String s)
    {
        ArrayList arraylist = turnbasedmatch.getParticipants();
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            Participant participant = (Participant)arraylist.get(j);
            if (participant.getParticipantId().equals(s))
            {
                return participant;
            }
        }

        throw new IllegalStateException((new StringBuilder()).append("Participant ").append(s).append(" is not in match ").append(turnbasedmatch.getMatchId()).toString());
    }

    static ArrayList c(TurnBasedMatch turnbasedmatch)
    {
        ArrayList arraylist = turnbasedmatch.getParticipants();
        int i = arraylist.size();
        ArrayList arraylist1 = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            arraylist1.add(((Participant)arraylist.get(j)).getParticipantId());
        }

        return arraylist1;
    }

    public boolean canRematch()
    {
        return TD == 2 && TG == null;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public TurnBasedMatch freeze()
    {
        return this;
    }

    public volatile Object freeze()
    {
        return freeze();
    }

    public Bundle getAutoMatchCriteria()
    {
        return To;
    }

    public int getAvailableAutoMatchSlots()
    {
        if (To == null)
        {
            return 0;
        } else
        {
            return To.getInt("max_automatch_players");
        }
    }

    public long getCreationTimestamp()
    {
        return SU;
    }

    public String getCreatorId()
    {
        return Ts;
    }

    public byte[] getData()
    {
        return TF;
    }

    public String getDescription()
    {
        return Mp;
    }

    public void getDescription(CharArrayBuffer chararraybuffer)
    {
        ik.b(Mp, chararraybuffer);
    }

    public Participant getDescriptionParticipant()
    {
        return getParticipant(getDescriptionParticipantId());
    }

    public String getDescriptionParticipantId()
    {
        return TL;
    }

    public Game getGame()
    {
        return Rt;
    }

    public long getLastUpdatedTimestamp()
    {
        return TB;
    }

    public String getLastUpdaterId()
    {
        return TA;
    }

    public String getMatchId()
    {
        return Oi;
    }

    public int getMatchNumber()
    {
        return TI;
    }

    public Participant getParticipant(String s)
    {
        return c(this, s);
    }

    public String getParticipantId(String s)
    {
        return b(this, s);
    }

    public ArrayList getParticipantIds()
    {
        return c(this);
    }

    public int getParticipantStatus(String s)
    {
        return a(this, s);
    }

    public ArrayList getParticipants()
    {
        return new ArrayList(SX);
    }

    public String getPendingParticipantId()
    {
        return TC;
    }

    public byte[] getPreviousMatchData()
    {
        return TH;
    }

    public String getRematchId()
    {
        return TG;
    }

    public int getStatus()
    {
        return TD;
    }

    public int getTurnStatus()
    {
        return TJ;
    }

    public int getVariant()
    {
        return SY;
    }

    public int getVersion()
    {
        return TE;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        return a(this);
    }

    public boolean isDataValid()
    {
        return true;
    }

    public boolean isLocallyModified()
    {
        return TK;
    }

    public String toString()
    {
        return b(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        TurnBasedMatchEntityCreator.a(this, parcel, i);
    }

}
