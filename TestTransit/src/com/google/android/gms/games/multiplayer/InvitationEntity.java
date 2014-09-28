// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            Invitation, Participant, ParticipantEntity, InvitationEntityCreator

public final class InvitationEntity extends GamesDowngradeableSafeParcel
    implements Invitation
{
    static final class InvitationEntityCreatorCompat extends InvitationEntityCreator
    {

        public InvitationEntity bl(Parcel parcel)
        {
            if (InvitationEntity.b(InvitationEntity.gR()) || InvitationEntity.aQ(com/google/android/gms/games/multiplayer/InvitationEntity.getCanonicalName()))
            {
                return super.bl(parcel);
            }
            GameEntity gameentity = (GameEntity)GameEntity.CREATOR.createFromParcel(parcel);
            String s = parcel.readString();
            long l = parcel.readLong();
            int i = parcel.readInt();
            ParticipantEntity participantentity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(parcel);
            int j = parcel.readInt();
            ArrayList arraylist = new ArrayList(j);
            for (int k = 0; k < j; k++)
            {
                arraylist.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }

            return new InvitationEntity(2, gameentity, s, l, i, participantentity, arraylist, -1, 0);
        }

        public Object createFromParcel(Parcel parcel)
        {
            return bl(parcel);
        }

        InvitationEntityCreatorCompat()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new InvitationEntityCreatorCompat();
    private final String NQ;
    private final GameEntity Rt;
    private final long SU;
    private final int SV;
    private final ParticipantEntity SW;
    private final ArrayList SX;
    private final int SY;
    private final int SZ;
    private final int xM;

    InvitationEntity(int i, GameEntity gameentity, String s, long l, int j, ParticipantEntity participantentity, 
            ArrayList arraylist, int k, int i1)
    {
        xM = i;
        Rt = gameentity;
        NQ = s;
        SU = l;
        SV = j;
        SW = participantentity;
        SX = arraylist;
        SY = k;
        SZ = i1;
    }

    InvitationEntity(Invitation invitation)
    {
        xM = 2;
        Rt = new GameEntity(invitation.getGame());
        NQ = invitation.getInvitationId();
        SU = invitation.getCreationTimestamp();
        SV = invitation.getInvitationType();
        SY = invitation.getVariant();
        SZ = invitation.getAvailableAutoMatchSlots();
        String s = invitation.getInviter().getParticipantId();
        Participant participant = null;
        ArrayList arraylist = invitation.getParticipants();
        int i = arraylist.size();
        SX = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            Participant participant1 = (Participant)arraylist.get(j);
            if (participant1.getParticipantId().equals(s))
            {
                participant = participant1;
            }
            SX.add((ParticipantEntity)participant1.freeze());
        }

        hm.b(participant, "Must have a valid inviter!");
        SW = (ParticipantEntity)participant.freeze();
    }

    static int a(Invitation invitation)
    {
        Object aobj[] = new Object[8];
        aobj[0] = invitation.getGame();
        aobj[1] = invitation.getInvitationId();
        aobj[2] = Long.valueOf(invitation.getCreationTimestamp());
        aobj[3] = Integer.valueOf(invitation.getInvitationType());
        aobj[4] = invitation.getInviter();
        aobj[5] = invitation.getParticipants();
        aobj[6] = Integer.valueOf(invitation.getVariant());
        aobj[7] = Integer.valueOf(invitation.getAvailableAutoMatchSlots());
        return hk.hashCode(aobj);
    }

    static boolean a(Invitation invitation, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof Invitation))
        {
            flag = false;
        } else
        if (invitation != obj)
        {
            Invitation invitation1 = (Invitation)obj;
            if (!hk.equal(invitation1.getGame(), invitation.getGame()) || !hk.equal(invitation1.getInvitationId(), invitation.getInvitationId()) || !hk.equal(Long.valueOf(invitation1.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) || !hk.equal(Integer.valueOf(invitation1.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) || !hk.equal(invitation1.getInviter(), invitation.getInviter()) || !hk.equal(invitation1.getParticipants(), invitation.getParticipants()) || !hk.equal(Integer.valueOf(invitation1.getVariant()), Integer.valueOf(invitation.getVariant())) || !hk.equal(Integer.valueOf(invitation1.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots())))
            {
                return false;
            }
        }
        return flag;
    }

    static boolean aQ(String s)
    {
        return aA(s);
    }

    static String b(Invitation invitation)
    {
        return hk.e(invitation).a("Game", invitation.getGame()).a("InvitationId", invitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(invitation.getInvitationType())).a("Inviter", invitation.getInviter()).a("Participants", invitation.getParticipants()).a("Variant", Integer.valueOf(invitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    static boolean b(Integer integer)
    {
        return c(integer);
    }

    static Integer gR()
    {
        return fq();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Invitation freeze()
    {
        return this;
    }

    public volatile Object freeze()
    {
        return freeze();
    }

    public int getAvailableAutoMatchSlots()
    {
        return SZ;
    }

    public long getCreationTimestamp()
    {
        return SU;
    }

    public Game getGame()
    {
        return Rt;
    }

    public String getInvitationId()
    {
        return NQ;
    }

    public int getInvitationType()
    {
        return SV;
    }

    public Participant getInviter()
    {
        return SW;
    }

    public ArrayList getParticipants()
    {
        return new ArrayList(SX);
    }

    public int getVariant()
    {
        return SY;
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

    public String toString()
    {
        return b(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if (!fr())
        {
            InvitationEntityCreator.a(this, parcel, i);
        } else
        {
            Rt.writeToParcel(parcel, i);
            parcel.writeString(NQ);
            parcel.writeLong(SU);
            parcel.writeInt(SV);
            SW.writeToParcel(parcel, i);
            int j = SX.size();
            parcel.writeInt(j);
            int k = 0;
            while (k < j) 
            {
                ((ParticipantEntity)SX.get(k)).writeToParcel(parcel, i);
                k++;
            }
        }
    }

}
