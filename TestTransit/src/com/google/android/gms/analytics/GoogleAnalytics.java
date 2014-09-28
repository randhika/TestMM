// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.analytics:
//            TrackerHandler, t, r, g, 
//            ae, h, l, Tracker, 
//            aa, v, w, Logger, 
//            af, u, ai, aj, 
//            ak, f

public class GoogleAnalytics extends TrackerHandler
{
    static interface a
    {

        public abstract void h(Activity activity);

        public abstract void i(Activity activity);
    }

    class b
        implements android.app.Application.ActivityLifecycleCallbacks
    {

        final GoogleAnalytics wu;

        public void onActivityCreated(Activity activity, Bundle bundle)
        {
        }

        public void onActivityDestroyed(Activity activity)
        {
        }

        public void onActivityPaused(Activity activity)
        {
        }

        public void onActivityResumed(Activity activity)
        {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
        {
        }

        public void onActivityStarted(Activity activity)
        {
            GoogleAnalytics.a(wu, activity);
        }

        public void onActivityStopped(Activity activity)
        {
            GoogleAnalytics.b(wu, activity);
        }

        b()
        {
            wu = GoogleAnalytics.this;
            super();
        }
    }


    private static boolean wm;
    private static GoogleAnalytics wt;
    private Context mContext;
    private String tC;
    private String tD;
    private f tV;
    private boolean wn;
    private af wo;
    private volatile Boolean wp;
    private Logger wq;
    private Set wr;
    private boolean ws;

    protected GoogleAnalytics(Context context)
    {
        this(context, ((f) (t.x(context))), ((af) (r.cE())));
    }

    private GoogleAnalytics(Context context, f f1, af af1)
    {
        wp = Boolean.valueOf(false);
        ws = false;
        if (context == null)
        {
            throw new IllegalArgumentException("context cannot be null");
        } else
        {
            mContext = context.getApplicationContext();
            tV = f1;
            wo = af1;
            com.google.android.gms.analytics.g.u(mContext);
            ae.u(mContext);
            h.u(mContext);
            wq = new l();
            wr = new HashSet();
            dj();
            return;
        }
    }

    private int P(String s)
    {
        String s1 = s.toLowerCase();
        if ("verbose".equals(s1))
        {
            return 0;
        }
        if ("info".equals(s1))
        {
            return 1;
        }
        if ("warning".equals(s1))
        {
            return 2;
        }
        return !"error".equals(s1) ? -1 : 3;
    }

    private Tracker a(Tracker tracker)
    {
        if (tC != null)
        {
            tracker.set("&an", tC);
        }
        if (tD != null)
        {
            tracker.set("&av", tD);
        }
        return tracker;
    }

    static void a(GoogleAnalytics googleanalytics, Activity activity)
    {
        googleanalytics.f(activity);
    }

    static void b(GoogleAnalytics googleanalytics, Activity activity)
    {
        googleanalytics.g(activity);
    }

    static GoogleAnalytics di()
    {
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorenter ;
        GoogleAnalytics googleanalytics = wt;
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorexit ;
        return googleanalytics;
        Exception exception;
        exception;
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void dj()
    {
        if (!wm) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ApplicationInfo applicationinfo1 = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 129);
        ApplicationInfo applicationinfo = applicationinfo1;
_L4:
        if (applicationinfo == null)
        {
            aa.D("Couldn't get ApplicationInfo to load gloabl config.");
            return;
        }
        break; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        aa.C((new StringBuilder()).append("PackageManager doesn't know about package: ").append(namenotfoundexception).toString());
        applicationinfo = null;
        if (true) goto _L4; else goto _L3
_L3:
        Bundle bundle = applicationinfo.metaData;
        if (bundle != null)
        {
            int i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (i > 0)
            {
                w w1 = (w)(new v(mContext)).r(i);
                if (w1 != null)
                {
                    a(w1);
                    return;
                }
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    private void f(Activity activity)
    {
        for (Iterator iterator = wr.iterator(); iterator.hasNext(); ((a)iterator.next()).h(activity)) { }
    }

    private void g(Activity activity)
    {
        for (Iterator iterator = wr.iterator(); iterator.hasNext(); ((a)iterator.next()).i(activity)) { }
    }

    public static GoogleAnalytics getInstance(Context context)
    {
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorenter ;
        GoogleAnalytics googleanalytics;
        if (wt == null)
        {
            wt = new GoogleAnalytics(context);
        }
        googleanalytics = wt;
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorexit ;
        return googleanalytics;
        Exception exception;
        exception;
        com/google/android/gms/analytics/GoogleAnalytics;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void a(a a1)
    {
        wr.add(a1);
    }

    void a(w w1)
    {
        aa.C("Loading global config values.");
        if (w1.cY())
        {
            tC = w1.cZ();
            aa.C((new StringBuilder()).append("app name loaded: ").append(tC).toString());
        }
        if (w1.da())
        {
            tD = w1.db();
            aa.C((new StringBuilder()).append("app version loaded: ").append(tD).toString());
        }
        if (w1.dc())
        {
            int i = P(w1.dd());
            if (i >= 0)
            {
                aa.C((new StringBuilder()).append("log level loaded: ").append(i).toString());
                getLogger().setLogLevel(i);
            }
        }
        if (w1.de())
        {
            wo.setLocalDispatchPeriod(w1.df());
        }
        if (w1.dg())
        {
            setDryRun(w1.dh());
        }
    }

    void b(a a1)
    {
        wr.remove(a1);
    }

    public void dispatchLocalHits()
    {
        wo.dispatchLocalHits();
    }

    public void enableAutoActivityReports(Application application)
    {
        if (android.os.Build.VERSION.SDK_INT >= 14 && !ws)
        {
            application.registerActivityLifecycleCallbacks(new b());
            ws = true;
        }
    }

    public boolean getAppOptOut()
    {
        u.cU().a(u.a.vN);
        return wp.booleanValue();
    }

    public Logger getLogger()
    {
        return wq;
    }

    public boolean isDryRunEnabled()
    {
        u.cU().a(u.a.vZ);
        return wn;
    }

    public Tracker newTracker(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Tracker tracker;
        u.cU().a(u.a.vJ);
        tracker = new Tracker(null, this, mContext);
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        aj aj1 = (aj)(new ai(mContext)).r(i);
        if (aj1 == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        tracker.a(aj1);
        Tracker tracker1 = a(tracker);
        this;
        JVM INSTR monitorexit ;
        return tracker1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Tracker newTracker(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Tracker tracker;
        u.cU().a(u.a.vJ);
        tracker = a(new Tracker(s, this, mContext));
        this;
        JVM INSTR monitorexit ;
        return tracker;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void p(Map map)
    {
        this;
        JVM INSTR monitorenter ;
        if (map != null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        throw new IllegalArgumentException("hit cannot be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        ak.a(map, "&ul", ak.a(Locale.getDefault()));
        ak.a(map, "&sr", ae.dv().getValue("&sr"));
        map.put("&_u", u.cU().cW());
        u.cU().cV();
        tV.p(map);
        this;
        JVM INSTR monitorexit ;
    }

    public void reportActivityStart(Activity activity)
    {
        if (!ws)
        {
            f(activity);
        }
    }

    public void reportActivityStop(Activity activity)
    {
        if (!ws)
        {
            g(activity);
        }
    }

    public void setAppOptOut(boolean flag)
    {
        u.cU().a(u.a.vM);
        wp = Boolean.valueOf(flag);
        if (wp.booleanValue())
        {
            tV.cl();
        }
    }

    public void setDryRun(boolean flag)
    {
        u.cU().a(u.a.vY);
        wn = flag;
    }

    public void setLocalDispatchPeriod(int i)
    {
        wo.setLocalDispatchPeriod(i);
    }

    public void setLogger(Logger logger)
    {
        u.cU().a(u.a.wa);
        wq = logger;
    }
}
