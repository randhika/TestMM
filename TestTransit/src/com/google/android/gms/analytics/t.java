// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.internal.fd;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.android.gms.analytics:
//            f, ak, aa, a, 
//            m, g, ag, s, 
//            h, GoogleAnalytics, u, y

class t extends Thread
    implements f
{

    private static t uO;
    private volatile boolean mClosed;
    private final Context mContext;
    private final LinkedBlockingQueue uK = new LinkedBlockingQueue();
    private volatile boolean uL;
    private volatile List uM;
    private volatile String uN;
    private volatile ag uP;

    private t(Context context)
    {
        super("GAThread");
        uL = false;
        mClosed = false;
        if (context != null)
        {
            mContext = context.getApplicationContext();
        } else
        {
            mContext = context;
        }
        start();
    }

    static int O(String s1)
    {
        int i = 1;
        if (!TextUtils.isEmpty(s1))
        {
            int j = -1 + s1.length();
            i = 0;
            for (int k = j; k >= 0; k--)
            {
                char c1 = s1.charAt(k);
                i = c1 + (0xfffffff & i << 6) + (c1 << 14);
                int l = 0xfe00000 & i;
                if (l != 0)
                {
                    i ^= l >> 21;
                }
            }

        }
        return i;
    }

    static Context a(t t1)
    {
        return t1.mContext;
    }

    static String a(t t1, String s1)
    {
        t1.uN = s1;
        return s1;
    }

    private String a(Throwable throwable)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        PrintStream printstream = new PrintStream(bytearrayoutputstream);
        throwable.printStackTrace(printstream);
        printstream.flush();
        return new String(bytearrayoutputstream.toByteArray());
    }

    static void a(t t1, Map map)
    {
        t1.s(map);
    }

    static String b(t t1)
    {
        return t1.uN;
    }

    static boolean b(t t1, Map map)
    {
        return t1.r(map);
    }

    static List c(t t1)
    {
        return t1.uM;
    }

    static void c(t t1, Map map)
    {
        t1.t(map);
    }

    static ag d(t t1)
    {
        return t1.uP;
    }

    static String d(t t1, Map map)
    {
        return t1.q(map);
    }

    private String q(Map map)
    {
        if (map.containsKey("useSecure"))
        {
            if (ak.d((String)map.get("useSecure"), true))
            {
                return "https:";
            } else
            {
                return "http:";
            }
        } else
        {
            return "https:";
        }
    }

    private boolean r(Map map)
    {
        if (map.get("&sf") == null)
        {
            return false;
        }
        double d1 = ak.a((String)map.get("&sf"), 100D);
        if (d1 >= 100D)
        {
            return false;
        }
        if ((double)(O((String)map.get("&cid")) % 10000) >= d1 * 100D)
        {
            String s1;
            if (map.get("&t") == null)
            {
                s1 = "unknown";
            } else
            {
                s1 = (String)map.get("&t");
            }
            aa.C(String.format("%s hit sampled out", new Object[] {
                s1
            }));
            return true;
        } else
        {
            return false;
        }
    }

    private void s(Map map)
    {
        m m1 = com.google.android.gms.analytics.a.s(mContext);
        ak.a(map, "&adid", m1.getValue("&adid"));
        ak.a(map, "&ate", m1.getValue("&ate"));
    }

    private void t(Map map)
    {
        g g1 = g.cu();
        ak.a(map, "&an", g1.getValue("&an"));
        ak.a(map, "&av", g1.getValue("&av"));
        ak.a(map, "&aid", g1.getValue("&aid"));
        ak.a(map, "&aiid", g1.getValue("&aiid"));
        map.put("&v", "1");
    }

    static t x(Context context)
    {
        if (uO == null)
        {
            uO = new t(context);
        }
        return uO;
    }

    static String y(Context context)
    {
        FileInputStream fileinputstream;
        byte abyte0[];
        int i;
        fileinputstream = context.openFileInput("gaInstallData");
        abyte0 = new byte[8192];
        i = fileinputstream.read(abyte0, 0, 8192);
        if (fileinputstream.available() <= 0)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        aa.A("Too much campaign data, ignoring it.");
        fileinputstream.close();
        context.deleteFile("gaInstallData");
        return null;
        String s1;
        try
        {
            fileinputstream.close();
            context.deleteFile("gaInstallData");
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            aa.B("No campaign data found.");
            return null;
        }
        catch (IOException ioexception)
        {
            aa.A("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        aa.D("Campaign file is empty.");
        return null;
        s1 = new String(abyte0, 0, i);
        aa.B((new StringBuilder()).append("Campaign found: ").append(s1).toString());
        return s1;
    }

    void a(Runnable runnable)
    {
        uK.add(runnable);
    }

    public void cl()
    {
        a(new Runnable() {

            final t uR;

            public void run()
            {
                t.d(uR).cl();
            }

            
            {
                uR = t.this;
                super();
            }
        });
    }

    public void cq()
    {
        a(new Runnable() {

            final t uR;

            public void run()
            {
                t.d(uR).cq();
            }

            
            {
                uR = t.this;
                super();
            }
        });
    }

    public void cs()
    {
        a(new Runnable() {

            final t uR;

            public void run()
            {
                t.d(uR).cs();
            }

            
            {
                uR = t.this;
                super();
            }
        });
    }

    public LinkedBlockingQueue ct()
    {
        return uK;
    }

    public Thread getThread()
    {
        return this;
    }

    protected void init()
    {
        uP.cL();
        uM = new ArrayList();
        uM.add(new fd("appendVersion", "&_v".substring(1), "ma4.0.2"));
        uM.add(new fd("appendQueueTime", "&qt".substring(1), null));
        uM.add(new fd("appendCacheBuster", "&z".substring(1), null));
    }

    public void p(Map map)
    {
        HashMap hashmap = new HashMap(map);
        String s1 = (String)map.get("&ht");
        if (s1 != null)
        {
            try
            {
                Long.valueOf(s1);
            }
            catch (NumberFormatException numberformatexception)
            {
                s1 = null;
            }
        }
        if (s1 == null)
        {
            hashmap.put("&ht", Long.toString(System.currentTimeMillis()));
        }
        a(new Runnable(hashmap) {

            final Map uQ;
            final t uR;

            public void run()
            {
                t.a(uR, uQ);
                if (TextUtils.isEmpty((CharSequence)uQ.get("&cid")))
                {
                    uQ.put("&cid", h.cv().getValue("&cid"));
                }
                if (GoogleAnalytics.getInstance(t.a(uR)).getAppOptOut() || t.b(uR, uQ))
                {
                    return;
                }
                if (!TextUtils.isEmpty(t.b(uR)))
                {
                    u.cU().u(true);
                    uQ.putAll((new HitBuilders.HitBuilder()).setCampaignParamsFromUrl(t.b(uR)).build());
                    u.cU().u(false);
                    t.a(uR, null);
                }
                t.c(uR, uQ);
                Map map1 = com.google.android.gms.analytics.y.u(uQ);
                t.d(uR).b(map1, Long.valueOf((String)uQ.get("&ht")).longValue(), t.d(uR, uQ), t.c(uR));
            }

            
            {
                uR = t.this;
                uQ = map;
                super();
            }
        });
    }

    public void run()
    {
        Process.setThreadPriority(10);
        Throwable throwable1;
        InterruptedException interruptedexception1;
        Runnable runnable;
        try
        {
            Thread.sleep(5000L);
        }
        catch (InterruptedException interruptedexception)
        {
            aa.D("sleep interrupted in GAThread initialize");
        }
        try
        {
            if (uP == null)
            {
                uP = new s(mContext, this);
            }
            init();
            uN = y(mContext);
            aa.C("Initialized GA Thread");
        }
        catch (Throwable throwable)
        {
            aa.A((new StringBuilder()).append("Error initializing the GAThread: ").append(a(throwable)).toString());
            aa.A("Google Analytics will not start up.");
            uL = true;
        }
        if (mClosed)
        {
            break; /* Loop/switch isn't completed */
        }
        runnable = (Runnable)uK.take();
        if (!uL)
        {
            runnable.run();
        }
        continue; /* Loop/switch isn't completed */
        interruptedexception1;
        try
        {
            aa.B(interruptedexception1.toString());
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable1)
        {
            aa.A((new StringBuilder()).append("Error on GAThread: ").append(a(throwable1)).toString());
            aa.A("Google Analytics is shutting down.");
            uL = true;
        }
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_55;
_L1:
    }
}
