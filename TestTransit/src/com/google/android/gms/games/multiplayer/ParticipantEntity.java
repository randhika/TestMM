// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            Participant, ParticipantEntityCreator, ParticipantResult

public final class ParticipantEntity extends GamesDowngradeableSafeParcel
    implements Participant
{
    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator
    {

        public ParticipantEntity bm(Parcel parcel)
        {
            boolean flag = true;
            if (ParticipantEntity.b(ParticipantEntity.gR()) || ParticipantEntity.aQ(com/google/android/gms/games/multiplayer/ParticipantEntity.getCanonicalName()))
            {
                return super.bm(parcel);
            }
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            Uri uri;
            String s3;
            Uri uri1;
            int i;
            String s4;
            boolean flag1;
            PlayerEntity playerentity;
            if (s2 == null)
            {
                uri = null;
            } else
            {
                uri = Uri.parse(s2);
            }
            s3 = parcel.readString();
            if (s3 == null)
            {
                uri1 = null;
            } else
            {
                uri1 = Uri.parse(s3);
            }
            i = parcel.readInt();
            s4 = parcel.readString();
            if (parcel.readInt() > 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (parcel.readInt() <= 0)
            {
                flag = false;
            }
            if (flag)
            {
                playerentity = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(parcel);
            } else
            {
                playerentity = null;
            }
            return new ParticipantEntity(3, s, s1, uri, uri1, i, s4, flag1, playerentity, 7, null, null, null);
        }

        public Object createFromParcel(Parcel parcel)
        {
            return bm(parcel);
        }

        ParticipantEntityCreatorCompat()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new ParticipantEntityCreatorCompat();
    private final int Ap;
    private final String Ln;
    private final String MC;
    private final String MD;
    private final Uri Mr;
    private final Uri Ms;
    private final PlayerEntity Nj;
    private final String Nn;
    private final String Oq;
    private final int Tb;
    private final boolean Tc;
    private final ParticipantResult Td;
    private final int xM;

    ParticipantEntity(int i, String s, String s1, Uri uri, Uri uri1, int j, String s2, 
            boolean flag, PlayerEntity playerentity, int k, ParticipantResult participantresult, String s3, String s4)
    {
        xM = i;
        Oq = s;
        Ln = s1;
        Mr = uri;
        Ms = uri1;
        Tb = j;
        Nn = s2;
        Tc = flag;
        Nj = playerentity;
        Ap = k;
        Td = participantresult;
        MC = s3;
        MD = s4;
    }

    public ParticipantEntity(Participant participant)
    {
        xM = 3;
        Oq = participant.getParticipantId();
        Ln = participant.getDisplayName();
        Mr = participant.getIconImageUri();
        Ms = participant.getHiResImageUri();
        Tb = participant.getStatus();
        Nn = participant.gW();
        Tc = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        PlayerEntity playerentity;
        if (player == null)
        {
            playerentity = null;
        } else
        {
            playerentity = new PlayerEntity(player);
        }
        Nj = playerentity;
        Ap = participant.getCapabilities();
        Td = participant.getResult();
        MC = participant.getIconImageUrl();
        MD = participant.getHiResImageUrl();
    }

    static int a(Participant participant)
    {
        Object aobj[] = new Object[10];
        aobj[0] = participant.getPlayer();
        aobj[1] = Integer.valueOf(participant.getStatus());
        aobj[2] = participant.gW();
        aobj[3] = Boolean.valueOf(participant.isConnectedToRoom());
        aobj[4] = participant.getDisplayName();
        aobj[5] = participant.getIconImageUri();
        aobj[6] = participant.getHiResImageUri();
        aobj[7] = Integer.valueOf(participant.getCapabilities());
        aobj[8] = participant.getResult();
        aobj[9] = participant.getParticipantId();
        return hk.hashCode(aobj);
    }

    static boolean a(Participant participant, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof Participant))
        {
            flag = false;
        } else
        if (participant != obj)
        {
            Participant participant1 = (Participant)obj;
            if (!hk.equal(participant1.getPlayer(), participant.getPlayer()) || !hk.equal(Integer.valueOf(participant1.getStatus()), Integer.valueOf(participant.getStatus())) || !hk.equal(participant1.gW(), participant.gW()) || !hk.equal(Boolean.valueOf(participant1.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) || !hk.equal(participant1.getDisplayName(), participant.getDisplayName()) || !hk.equal(participant1.getIconImageUri(), participant.getIconImageUri()) || !hk.equal(participant1.getHiResImageUri(), participant.getHiResImageUri()) || !hk.equal(Integer.valueOf(participant1.getCapabilities()), Integer.valueOf(participant.getCapabilities())) || !hk.equal(participant1.getResult(), participant.getResult()) || !hk.equal(participant1.getParticipantId(), participant.getParticipantId()))
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

    static String b(Participant participant)
    {
        return hk.e(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", Integer.valueOf(participant.getStatus())).a("ClientAddress", participant.gW()).a("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(participant.getCapabilities())).a("Result", participant.getResult()).toString();
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

    public Participant freeze()
    {
        return this;
    }

    public volatile Object freeze()
    {
        return freeze();
    }

    public String gW()
    {
        return Nn;
    }

    public int getCapabilities()
    {
        return Ap;
    }

    public String getDisplayName()
    {
        if (Nj == null)
        {
            return Ln;
        } else
        {
            return Nj.getDisplayName();
        }
    }

    public void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        if (Nj == null)
        {
            ik.b(Ln, chararraybuffer);
            return;
        } else
        {
            Nj.getDisplayName(chararraybuffer);
            return;
        }
    }

    public Uri getHiResImageUri()
    {
        if (Nj == null)
        {
            return Ms;
        } else
        {
            return Nj.getHiResImageUri();
        }
    }

    public String getHiResImageUrl()
    {
        if (Nj == null)
        {
            return MD;
        } else
        {
            return Nj.getHiResImageUrl();
        }
    }

    public Uri getIconImageUri()
    {
        if (Nj == null)
        {
            return Mr;
        } else
        {
            return Nj.getIconImageUri();
        }
    }

    public String getIconImageUrl()
    {
        if (Nj == null)
        {
            return MC;
        } else
        {
            return Nj.getIconImageUrl();
        }
    }

    public String getParticipantId()
    {
        return Oq;
    }

    public Player getPlayer()
    {
        return Nj;
    }

    public ParticipantResult getResult()
    {
        return Td;
    }

    public int getStatus()
    {
        return Tb;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        return a(this);
    }

    public boolean isConnectedToRoom()
    {
        return Tc;
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
            ParticipantEntityCreator.a(this, parcel, i);
        } else
        {
            parcel.writeString(Oq);
            parcel.writeString(Ln);
            String s;
            Uri uri;
            String s1;
            int j;
            PlayerEntity playerentity;
            int k;
            if (Mr == null)
            {
                s = null;
            } else
            {
                s = Mr.toString();
            }
            parcel.writeString(s);
            uri = Ms;
            s1 = null;
            if (uri != null)
            {
                s1 = Ms.toString();
            }
            parcel.writeString(s1);
            parcel.writeInt(Tb);
            parcel.writeString(Nn);
            if (Tc)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
            playerentity = Nj;
            k = 0;
            if (playerentity != null)
            {
                k = 1;
            }
            parcel.writeInt(k);
            if (Nj != null)
            {
                Nj.writeToParcel(parcel, i);
                return;
            }
        }
    }

}
