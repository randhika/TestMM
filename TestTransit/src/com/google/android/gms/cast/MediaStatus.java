// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.internal.gi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.cast:
//            MediaInfo

public final class MediaStatus
{

    public static final long COMMAND_PAUSE = 1L;
    public static final long COMMAND_SEEK = 2L;
    public static final long COMMAND_SET_VOLUME = 4L;
    public static final long COMMAND_SKIP_BACKWARD = 32L;
    public static final long COMMAND_SKIP_FORWARD = 16L;
    public static final long COMMAND_TOGGLE_MUTE = 8L;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN;
    private JSONObject AA;
    private MediaInfo AB;
    private long AJ;
    private double AK;
    private int AL;
    private int AM;
    private long AN;
    private long AO;
    private double AP;
    private boolean AQ;
    private long AR[];

    public MediaStatus(JSONObject jsonobject)
        throws JSONException
    {
        a(jsonobject, 0);
    }

    public int a(JSONObject jsonobject, int i)
        throws JSONException
    {
        int j = 2;
        int k = 1;
        long l = jsonobject.getLong("mediaSessionId");
        int i1;
        if (l != AJ)
        {
            AJ = l;
            i1 = k;
        } else
        {
            i1 = 0;
        }
        if (jsonobject.has("playerState"))
        {
            String s = jsonobject.getString("playerState");
            int j2;
            if (s.equals("IDLE"))
            {
                j2 = k;
            } else
            if (s.equals("PLAYING"))
            {
                j2 = j;
            } else
            if (s.equals("PAUSED"))
            {
                j2 = 3;
            } else
            if (s.equals("BUFFERING"))
            {
                j2 = 4;
            } else
            {
                j2 = 0;
            }
            if (j2 != AL)
            {
                AL = j2;
                i1 |= 2;
            }
            if (j2 == k && jsonobject.has("idleReason"))
            {
                String s1 = jsonobject.getString("idleReason");
                long al[];
                int j1;
                JSONObject jsonobject1;
                JSONArray jsonarray;
                int k1;
                long al1[];
                int l1;
                int i2;
                JSONObject jsonobject2;
                double d;
                boolean flag;
                long l2;
                long l3;
                double d1;
                if (!s1.equals("CANCELLED"))
                {
                    if (s1.equals("INTERRUPTED"))
                    {
                        j = 3;
                    } else
                    if (s1.equals("FINISHED"))
                    {
                        j = k;
                    } else
                    if (s1.equals("ERROR"))
                    {
                        j = 4;
                    } else
                    {
                        j = 0;
                    }
                }
                if (j != AM)
                {
                    AM = j;
                    i1 |= 2;
                }
            }
        }
        if (jsonobject.has("playbackRate"))
        {
            d1 = jsonobject.getDouble("playbackRate");
            if (AK != d1)
            {
                AK = d1;
                i1 |= 2;
            }
        }
        if (jsonobject.has("currentTime") && (i & 2) == 0)
        {
            l3 = gi.b(jsonobject.getDouble("currentTime"));
            if (l3 != AN)
            {
                AN = l3;
                i1 |= 2;
            }
        }
        if (jsonobject.has("supportedMediaCommands"))
        {
            l2 = jsonobject.getLong("supportedMediaCommands");
            if (l2 != AO)
            {
                AO = l2;
                i1 |= 2;
            }
        }
        if (jsonobject.has("volume") && (i & 1) == 0)
        {
            jsonobject2 = jsonobject.getJSONObject("volume");
            d = jsonobject2.getDouble("level");
            if (d != AP)
            {
                AP = d;
                i1 |= 2;
            }
            flag = jsonobject2.getBoolean("muted");
            if (flag != AQ)
            {
                AQ = flag;
                i1 |= 2;
            }
        }
        if (!jsonobject.has("activeTrackIds")) goto _L2; else goto _L1
_L1:
        jsonarray = jsonobject.getJSONArray("activeTrackIds");
        k1 = jsonarray.length();
        al1 = new long[k1];
        for (l1 = 0; l1 < k1; l1++)
        {
            al1[l1] = jsonarray.getLong(l1);
        }

          goto _L3
_L5:
        if (k != 0)
        {
            AR = al1;
        }
        j1 = k;
        al = al1;
_L6:
        if (j1 != 0)
        {
            AR = al;
            i1 |= 2;
        }
        if (jsonobject.has("customData"))
        {
            AA = jsonobject.getJSONObject("customData");
            i1 |= 2;
        }
        if (jsonobject.has("media"))
        {
            jsonobject1 = jsonobject.getJSONObject("media");
            AB = new MediaInfo(jsonobject1);
            i1 |= 2;
            if (jsonobject1.has("metadata"))
            {
                i1 |= 4;
            }
        }
        return i1;
_L3:
        if (AR == null || AR.length != k1) goto _L5; else goto _L4
_L4:
        for (i2 = 0; i2 < k1; i2++)
        {
            if (AR[i2] != al1[i2])
            {
                continue; /* Loop/switch isn't completed */
            }
        }

        k = 0;
        continue; /* Loop/switch isn't completed */
_L2:
        if (AR != null)
        {
            j1 = k;
            al = null;
        } else
        {
            al = null;
            j1 = 0;
        }
          goto _L6
        if (true) goto _L5; else goto _L7
_L7:
    }

    public long ea()
    {
        return AJ;
    }

    public long[] getActiveTrackIds()
    {
        return AR;
    }

    public JSONObject getCustomData()
    {
        return AA;
    }

    public int getIdleReason()
    {
        return AM;
    }

    public MediaInfo getMediaInfo()
    {
        return AB;
    }

    public double getPlaybackRate()
    {
        return AK;
    }

    public int getPlayerState()
    {
        return AL;
    }

    public long getStreamPosition()
    {
        return AN;
    }

    public double getStreamVolume()
    {
        return AP;
    }

    public boolean isMediaCommandSupported(long l)
    {
        return (l & AO) != 0L;
    }

    public boolean isMute()
    {
        return AQ;
    }
}
