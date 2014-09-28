// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.multiplayer.realtime:
//            Room, RoomEntityCreator

public final class RoomEntity extends GamesDowngradeableSafeParcel
    implements Room
{
    static final class RoomEntityCreatorCompat extends RoomEntityCreator
    {

        public RoomEntity bo(Parcel parcel)
        {
            if (RoomEntity.b(RoomEntity.gR()) || RoomEntity.aQ(com/google/android/gms/games/multiplayer/realtime/RoomEntity.getCanonicalName()))
            {
                return super.bo(parcel);
            }
            String s = parcel.readString();
            String s1 = parcel.readString();
            long l = parcel.readLong();
            int i = parcel.readInt();
            String s2 = parcel.readString();
            int j = parcel.readInt();
            Bundle bundle = parcel.readBundle();
            int k = parcel.readInt();
            ArrayList arraylist = new ArrayList(k);
            for (int i1 = 0; i1 < k; i1++)
            {
                arraylist.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }

            return new RoomEntity(2, s, s1, l, i, s2, j, bundle, arraylist, -1);
        }

        public Object createFromParcel(Parcel parcel)
        {
            return bo(parcel);
        }

        RoomEntityCreatorCompat()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new RoomEntityCreatorCompat();
    private final String Mp;
    private final String NS;
    private final long SU;
    private final ArrayList SX;
    private final int SY;
    private final Bundle To;
    private final String Ts;
    private final int Tt;
    private final int Tu;
    private final int xM;

    RoomEntity(int i, String s, String s1, long l, int j, String s2, 
            int k, Bundle bundle, ArrayList arraylist, int i1)
    {
        xM = i;
        NS = s;
        Ts = s1;
        SU = l;
        Tt = j;
        Mp = s2;
        SY = k;
        To = bundle;
        SX = arraylist;
        Tu = i1;
    }

    public RoomEntity(Room room)
    {
        xM = 2;
        NS = room.getRoomId();
        Ts = room.getCreatorId();
        SU = room.getCreationTimestamp();
        Tt = room.getStatus();
        Mp = room.getDescription();
        SY = room.getVariant();
        To = room.getAutoMatchCriteria();
        ArrayList arraylist = room.getParticipants();
        int i = arraylist.size();
        SX = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            SX.add((ParticipantEntity)((Participant)arraylist.get(j)).freeze());
        }

        Tu = room.getAutoMatchWaitEstimateSeconds();
    }

    static int a(Room room)
    {
        Object aobj[] = new Object[9];
        aobj[0] = room.getRoomId();
        aobj[1] = room.getCreatorId();
        aobj[2] = Long.valueOf(room.getCreationTimestamp());
        aobj[3] = Integer.valueOf(room.getStatus());
        aobj[4] = room.getDescription();
        aobj[5] = Integer.valueOf(room.getVariant());
        aobj[6] = room.getAutoMatchCriteria();
        aobj[7] = room.getParticipants();
        aobj[8] = Integer.valueOf(room.getAutoMatchWaitEstimateSeconds());
        return hk.hashCode(aobj);
    }

    static int a(Room room, String s)
    {
        ArrayList arraylist = room.getParticipants();
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            Participant participant = (Participant)arraylist.get(j);
            if (participant.getParticipantId().equals(s))
            {
                return participant.getStatus();
            }
        }

        throw new IllegalStateException((new StringBuilder()).append("Participant ").append(s).append(" is not in room ").append(room.getRoomId()).toString());
    }

    static boolean a(Room room, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof Room))
        {
            flag = false;
        } else
        if (room != obj)
        {
            Room room1 = (Room)obj;
            if (!hk.equal(room1.getRoomId(), room.getRoomId()) || !hk.equal(room1.getCreatorId(), room.getCreatorId()) || !hk.equal(Long.valueOf(room1.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) || !hk.equal(Integer.valueOf(room1.getStatus()), Integer.valueOf(room.getStatus())) || !hk.equal(room1.getDescription(), room.getDescription()) || !hk.equal(Integer.valueOf(room1.getVariant()), Integer.valueOf(room.getVariant())) || !hk.equal(room1.getAutoMatchCriteria(), room.getAutoMatchCriteria()) || !hk.equal(room1.getParticipants(), room.getParticipants()) || !hk.equal(Integer.valueOf(room1.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())))
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

    static String b(Room room)
    {
        return hk.e(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).a("RoomStatus", Integer.valueOf(room.getStatus())).a("Description", room.getDescription()).a("Variant", Integer.valueOf(room.getVariant())).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())).toString();
    }

    static String b(Room room, String s)
    {
        ArrayList arraylist = room.getParticipants();
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

    static boolean b(Integer integer)
    {
        return c(integer);
    }

    static Participant c(Room room, String s)
    {
        ArrayList arraylist = room.getParticipants();
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
        {
            Participant participant = (Participant)arraylist.get(j);
            if (participant.getParticipantId().equals(s))
            {
                return participant;
            }
        }

        throw new IllegalStateException((new StringBuilder()).append("Participant ").append(s).append(" is not in match ").append(room.getRoomId()).toString());
    }

    static ArrayList c(Room room)
    {
        ArrayList arraylist = room.getParticipants();
        int i = arraylist.size();
        ArrayList arraylist1 = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            arraylist1.add(((Participant)arraylist.get(j)).getParticipantId());
        }

        return arraylist1;
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

    public Room freeze()
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

    public int getAutoMatchWaitEstimateSeconds()
    {
        return Tu;
    }

    public long getCreationTimestamp()
    {
        return SU;
    }

    public String getCreatorId()
    {
        return Ts;
    }

    public String getDescription()
    {
        return Mp;
    }

    public void getDescription(CharArrayBuffer chararraybuffer)
    {
        ik.b(Mp, chararraybuffer);
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

    public String getRoomId()
    {
        return NS;
    }

    public int getStatus()
    {
        return Tt;
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
            RoomEntityCreator.a(this, parcel, i);
        } else
        {
            parcel.writeString(NS);
            parcel.writeString(Ts);
            parcel.writeLong(SU);
            parcel.writeInt(Tt);
            parcel.writeString(Mp);
            parcel.writeInt(SY);
            parcel.writeBundle(To);
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
