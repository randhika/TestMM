// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            gg, gi, gr, gn, 
//            gq

public class go extends gg
{
    private class a
        implements Runnable
    {

        final go CI;

        public void run()
        {
            boolean flag;
            flag = false;
            go.a(CI, false);
            long l = SystemClock.elapsedRealtime();
            for (Iterator iterator = go.a(CI).iterator(); iterator.hasNext(); ((gr)iterator.next()).d(l, 3)) { }
            Object obj = gr.CN;
            obj;
            JVM INSTR monitorenter ;
_L2:
            boolean flag1;
            for (Iterator iterator1 = go.a(CI).iterator(); iterator1.hasNext();)
            {
                Exception exception;
                if (((gr)iterator1.next()).ev())
                {
                    flag1 = true;
                } else
                {
                    flag1 = flag;
                }
                break MISSING_BLOCK_LABEL_135;
            }

            obj;
            JVM INSTR monitorexit ;
            go.b(CI, flag);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            flag = flag1;
            if (true) goto _L2; else goto _L1
_L1:
        }

        private a()
        {
            CI = go.this;
            super();
        }

    }


    private static final long Cp;
    private static final long Cq;
    private static final long Cr;
    private static final long Cs;
    private static final String NAMESPACE = gi.al("com.google.cast.media");
    private final gr CA;
    private final gr CB;
    private final gr CC;
    private final gr CD;
    private final gr CE;
    private final List CF;
    private final Runnable CG;
    private boolean CH;
    private long Ct;
    private MediaStatus Cu;
    private final gr Cv;
    private final gr Cw;
    private final gr Cx;
    private final gr Cy;
    private final gr Cz;
    private final Handler mHandler;

    public go()
    {
        this(null);
    }

    public go(String s)
    {
        super(NAMESPACE, "MediaControlChannel", s);
        mHandler = new Handler(Looper.getMainLooper());
        CG = new a();
        CF = new ArrayList();
        Cv = new gr(Cq);
        CF.add(Cv);
        Cw = new gr(Cp);
        CF.add(Cw);
        Cx = new gr(Cp);
        CF.add(Cx);
        Cy = new gr(Cp);
        CF.add(Cy);
        Cz = new gr(Cr);
        CF.add(Cz);
        CA = new gr(Cp);
        CF.add(CA);
        CB = new gr(Cp);
        CF.add(CB);
        CC = new gr(Cp);
        CF.add(CC);
        CD = new gr(Cp);
        CF.add(CD);
        CE = new gr(Cp);
        CF.add(CE);
        et();
    }

    static List a(go go1)
    {
        return go1.CF;
    }

    private void a(long l, JSONObject jsonobject)
        throws JSONException
    {
        boolean flag = true;
        boolean flag1 = Cv.p(l);
        boolean flag2;
        int i;
        int j;
        if (Cz.ev() && !Cz.p(l))
        {
            flag2 = flag;
        } else
        {
            flag2 = false;
        }
        if ((!CA.ev() || CA.p(l)) && (!CB.ev() || CB.p(l)))
        {
            flag = false;
        }
        if (flag2)
        {
            i = 2;
        } else
        {
            i = 0;
        }
        if (flag)
        {
            i |= 1;
        }
        if (flag1 || Cu == null)
        {
            Cu = new MediaStatus(jsonobject);
            Ct = SystemClock.elapsedRealtime();
            j = 7;
        } else
        {
            j = Cu.a(jsonobject, i);
        }
        if ((j & 1) != 0)
        {
            Ct = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((j & 2) != 0)
        {
            Ct = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((j & 4) != 0)
        {
            onMetadataUpdated();
        }
        for (Iterator iterator = CF.iterator(); iterator.hasNext(); ((gr)iterator.next()).c(l, 0)) { }
    }

    static boolean a(go go1, boolean flag)
    {
        go1.CH = flag;
        return flag;
    }

    static void b(go go1, boolean flag)
    {
        go1.z(flag);
    }

    private void et()
    {
        z(false);
        Ct = 0L;
        Cu = null;
        Cv.clear();
        Cz.clear();
        CA.clear();
    }

    private void z(boolean flag)
    {
label0:
        {
            if (CH != flag)
            {
                CH = flag;
                if (!flag)
                {
                    break label0;
                }
                mHandler.postDelayed(CG, Cs);
            }
            return;
        }
        mHandler.removeCallbacks(CG);
    }

    public long a(gq gq)
        throws IOException
    {
        JSONObject jsonobject = new JSONObject();
        long l = ed();
        CC.a(l, gq);
        z(true);
        try
        {
            jsonobject.put("requestId", l);
            jsonobject.put("type", "GET_STATUS");
            if (Cu != null)
            {
                jsonobject.put("mediaSessionId", Cu.ea());
            }
        }
        catch (JSONException jsonexception) { }
        a(jsonobject.toString(), l, null);
        return l;
    }

    public long a(gq gq, double d, JSONObject jsonobject)
        throws IOException, IllegalStateException, IllegalArgumentException
    {
        JSONObject jsonobject1;
        long l;
        if (Double.isInfinite(d) || Double.isNaN(d))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Volume cannot be ").append(d).toString());
        }
        jsonobject1 = new JSONObject();
        l = ed();
        CA.a(l, gq);
        z(true);
        jsonobject1.put("requestId", l);
        jsonobject1.put("type", "SET_VOLUME");
        jsonobject1.put("mediaSessionId", ea());
        JSONObject jsonobject2 = new JSONObject();
        jsonobject2.put("level", d);
        jsonobject1.put("volume", jsonobject2);
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l, null);
        return l;
    }

    public long a(gq gq, long l, int i, JSONObject jsonobject)
        throws IOException, IllegalStateException
    {
        JSONObject jsonobject1;
        long l1;
        jsonobject1 = new JSONObject();
        l1 = ed();
        Cz.a(l1, gq);
        z(true);
        jsonobject1.put("requestId", l1);
        jsonobject1.put("type", "SEEK");
        jsonobject1.put("mediaSessionId", ea());
        jsonobject1.put("currentTime", gi.o(l));
        if (i != 1) goto _L2; else goto _L1
_L1:
        jsonobject1.put("resumeState", "PLAYBACK_START");
_L4:
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l1, null);
        return l1;
_L2:
        if (i != 2) goto _L4; else goto _L3
_L3:
        jsonobject1.put("resumeState", "PLAYBACK_PAUSE");
          goto _L4
    }

    public long a(gq gq, MediaInfo mediainfo, boolean flag, long l, JSONObject jsonobject)
        throws IOException
    {
        JSONObject jsonobject1;
        long l1;
        jsonobject1 = new JSONObject();
        l1 = ed();
        Cv.a(l1, gq);
        z(true);
        jsonobject1.put("requestId", l1);
        jsonobject1.put("type", "LOAD");
        jsonobject1.put("media", mediainfo.dZ());
        jsonobject1.put("autoplay", flag);
        jsonobject1.put("currentTime", gi.o(l));
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l1, null);
        return l1;
    }

    public long a(gq gq, TextTrackStyle texttrackstyle)
        throws IOException
    {
        JSONObject jsonobject;
        long l;
        jsonobject = new JSONObject();
        l = ed();
        CE.a(l, gq);
        z(true);
        jsonobject.put("requestId", l);
        jsonobject.put("type", "EDIT_TRACKS_INFO");
        if (texttrackstyle == null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        jsonobject.put("textTrackStyle", texttrackstyle.dZ());
        jsonobject.put("mediaSessionId", ea());
_L2:
        a(jsonobject.toString(), l, null);
        return l;
        JSONException jsonexception;
        jsonexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public long a(gq gq, JSONObject jsonobject)
        throws IOException
    {
        JSONObject jsonobject1;
        long l;
        jsonobject1 = new JSONObject();
        l = ed();
        Cw.a(l, gq);
        z(true);
        jsonobject1.put("requestId", l);
        jsonobject1.put("type", "PAUSE");
        jsonobject1.put("mediaSessionId", ea());
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l, null);
        return l;
    }

    public long a(gq gq, boolean flag, JSONObject jsonobject)
        throws IOException, IllegalStateException
    {
        JSONObject jsonobject1;
        long l;
        jsonobject1 = new JSONObject();
        l = ed();
        CB.a(l, gq);
        z(true);
        jsonobject1.put("requestId", l);
        jsonobject1.put("type", "SET_VOLUME");
        jsonobject1.put("mediaSessionId", ea());
        JSONObject jsonobject2 = new JSONObject();
        jsonobject2.put("muted", flag);
        jsonobject1.put("volume", jsonobject2);
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l, null);
        return l;
    }

    public long a(gq gq, long al[])
        throws IOException
    {
        JSONObject jsonobject;
        long l;
        jsonobject = new JSONObject();
        l = ed();
        CD.a(l, gq);
        z(true);
        JSONArray jsonarray;
        jsonobject.put("requestId", l);
        jsonobject.put("type", "EDIT_TRACKS_INFO");
        jsonobject.put("mediaSessionId", ea());
        jsonarray = new JSONArray();
        int i = 0;
_L2:
        if (i >= al.length)
        {
            break; /* Loop/switch isn't completed */
        }
        jsonarray.put(i, al[i]);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            jsonobject.put("activeTrackIds", jsonarray);
        }
        catch (JSONException jsonexception) { }
        a(jsonobject.toString(), l, null);
        return l;
    }

    public void a(long l, int i)
    {
        for (Iterator iterator = CF.iterator(); iterator.hasNext(); ((gr)iterator.next()).c(l, i)) { }
    }

    public final void ai(String s)
    {
        BD.b("message received: %s", new Object[] {
            s
        });
        JSONObject jsonobject;
        String s1;
        long l;
        jsonobject = new JSONObject(s);
        s1 = jsonobject.getString("type");
        l = jsonobject.optLong("requestId", -1L);
        if (!s1.equals("MEDIA_STATUS"))
        {
            break MISSING_BLOCK_LABEL_149;
        }
        JSONArray jsonarray = jsonobject.getJSONArray("status");
        if (jsonarray.length() > 0)
        {
            a(l, jsonarray.getJSONObject(0));
            return;
        }
        try
        {
            Cu = null;
            onStatusUpdated();
            onMetadataUpdated();
            CC.c(l, 0);
            return;
        }
        catch (JSONException jsonexception)
        {
            gn gn1 = BD;
            Object aobj[] = new Object[2];
            aobj[0] = jsonexception.getMessage();
            aobj[1] = s;
            gn1.d("Message is malformed (%s); ignoring: %s", aobj);
            return;
        }
        if (s1.equals("INVALID_PLAYER_STATE"))
        {
            BD.d("received unexpected error: Invalid Player State.", new Object[0]);
            JSONObject jsonobject4 = jsonobject.optJSONObject("customData");
            for (Iterator iterator1 = CF.iterator(); iterator1.hasNext(); ((gr)iterator1.next()).b(l, 1, jsonobject4)) { }
            break MISSING_BLOCK_LABEL_371;
        }
        if (s1.equals("LOAD_FAILED"))
        {
            JSONObject jsonobject3 = jsonobject.optJSONObject("customData");
            Cv.b(l, 1, jsonobject3);
            return;
        }
        if (s1.equals("LOAD_CANCELLED"))
        {
            JSONObject jsonobject2 = jsonobject.optJSONObject("customData");
            Cv.b(l, 2, jsonobject2);
            return;
        }
        if (s1.equals("INVALID_REQUEST"))
        {
            BD.d("received unexpected error: Invalid Request.", new Object[0]);
            JSONObject jsonobject1 = jsonobject.optJSONObject("customData");
            for (Iterator iterator = CF.iterator(); iterator.hasNext(); ((gr)iterator.next()).b(l, 1, jsonobject1)) { }
        }
    }

    public long b(gq gq, JSONObject jsonobject)
        throws IOException
    {
        JSONObject jsonobject1;
        long l;
        jsonobject1 = new JSONObject();
        l = ed();
        Cy.a(l, gq);
        z(true);
        jsonobject1.put("requestId", l);
        jsonobject1.put("type", "STOP");
        jsonobject1.put("mediaSessionId", ea());
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l, null);
        return l;
    }

    public long c(gq gq, JSONObject jsonobject)
        throws IOException, IllegalStateException
    {
        JSONObject jsonobject1;
        long l;
        jsonobject1 = new JSONObject();
        l = ed();
        Cx.a(l, gq);
        z(true);
        jsonobject1.put("requestId", l);
        jsonobject1.put("type", "PLAY");
        jsonobject1.put("mediaSessionId", ea());
        if (jsonobject != null)
        {
            try
            {
                jsonobject1.put("customData", jsonobject);
            }
            catch (JSONException jsonexception) { }
        }
        a(jsonobject1.toString(), l, null);
        return l;
    }

    public long ea()
        throws IllegalStateException
    {
        if (Cu == null)
        {
            throw new IllegalStateException("No current media session");
        } else
        {
            return Cu.ea();
        }
    }

    public void ee()
    {
        et();
    }

    public long getApproximateStreamPosition()
    {
        MediaInfo mediainfo;
        for (mediainfo = getMediaInfo(); mediainfo == null || Ct == 0L;)
        {
            return 0L;
        }

        double d = Cu.getPlaybackRate();
        long l = Cu.getStreamPosition();
        int i = Cu.getPlayerState();
        if (d == 0.0D || i != 2)
        {
            return l;
        }
        long l1 = SystemClock.elapsedRealtime() - Ct;
        long l2;
        long l3;
        long l4;
        if (l1 < 0L)
        {
            l2 = 0L;
        } else
        {
            l2 = l1;
        }
        if (l2 == 0L)
        {
            return l;
        }
        l3 = mediainfo.getStreamDuration();
        l4 = l + (long)(d * (double)l2);
        if (l4 <= l3)
        {
            if (l4 < 0L)
            {
                l3 = 0L;
            } else
            {
                l3 = l4;
            }
        }
        return l3;
    }

    public MediaInfo getMediaInfo()
    {
        if (Cu == null)
        {
            return null;
        } else
        {
            return Cu.getMediaInfo();
        }
    }

    public MediaStatus getMediaStatus()
    {
        return Cu;
    }

    public long getStreamDuration()
    {
        MediaInfo mediainfo = getMediaInfo();
        if (mediainfo != null)
        {
            return mediainfo.getStreamDuration();
        } else
        {
            return 0L;
        }
    }

    protected void onMetadataUpdated()
    {
    }

    protected void onStatusUpdated()
    {
    }

    static 
    {
        Cp = TimeUnit.HOURS.toMillis(24L);
        Cq = TimeUnit.HOURS.toMillis(24L);
        Cr = TimeUnit.HOURS.toMillis(24L);
        Cs = TimeUnit.SECONDS.toMillis(1L);
    }
}
