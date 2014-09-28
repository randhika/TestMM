// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.android.ads:
//            c, h, AdLocationService, j

public class d
{

    public static void a(Context context)
    {
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorenter ;
        c c1 = b(context);
        if (c1 != null) goto _L2; else goto _L1
_L1:
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorexit ;
        return;
_L2:
        c1.a(null);
        a(context, c1);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static void a(Context context, String s)
    {
        if (!d(context))
        {
            h.a(3, "[ALManager] Done.");
            return;
        } else
        {
            Activity activity = (Activity)context;
            Intent intent = new Intent(activity, jp/co/yahoo/android/ads/AdLocationService);
            c c1 = new c(s, System.currentTimeMillis());
            intent.putExtra("info", c1);
            a(context, c1);
            activity.startService(intent);
            return;
        }
    }

    public static void a(Context context, c c1)
    {
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorenter ;
        if (c1 != null) goto _L2; else goto _L1
_L1:
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorexit ;
        return;
_L2:
        (new Thread(new Runnable(context, j.a(c1)) {

            final Context a;
            final String b;

            public void run()
            {
                android.content.SharedPreferences.Editor editor = a.getSharedPreferences("yjadviewpref", 0).edit();
                editor.putString("lc_info", b);
                editor.commit();
            }

            
            {
                a = context;
                b = s;
                super();
            }
        })).start();
        h.a(3, (new StringBuilder()).append("[ALManager] saveAdLocationInfo: ").append(c1).toString());
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        h.a(3, (new StringBuilder()).append("[ALManager] saveAdLocationInfo Failed. (").append(exception1.getClass().getName()).append("):").append(exception1.getMessage()).toString());
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static c b(Context context)
    {
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorenter ;
        String s = context.getSharedPreferences("yjadviewpref", 0).getString("lc_info", null);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        c c1 = (c)(c)j.b(s);
_L1:
        h.a(2, (new StringBuilder()).append("[ALManager] loadLocationInfo: ").append(c1).toString());
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorexit ;
        return c1;
        Exception exception1;
        exception1;
        h.a(3, (new StringBuilder()).append("[ALManager] loadLocationInfo (").append(exception1.getClass().getName()).append("):").append(exception1.getMessage()).toString());
        c1 = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static String c(Context context)
    {
        c c1 = b(context);
        if (c1 == null)
        {
            return null;
        } else
        {
            return h.a(c1.k(), System.currentTimeMillis());
        }
    }

    private static boolean d(Context context)
    {
        boolean flag = true;
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorenter ;
        c c1 = b(context);
        if (c1 != null) goto _L2; else goto _L1
_L1:
        jp/co/yahoo/android/ads/d;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        String s = "";
        long l = System.currentTimeMillis() - c1.b();
        if (c1.a() == 0 && (c1.b() <= 0L || l > 0x1b7740L))
        {
            break MISSING_BLOCK_LABEL_171;
        }
        break; /* Loop/switch isn't completed */
_L4:
        h.a(3, (new StringBuilder()).append("[ALManager] checkLocationStatus ").append(s).append(": ").append(flag).append(" (status: ").append(c1.a()).append(", startTime: ").append(c1.b()).append(", diff: ").append(l).append(")").toString());
        if (true) goto _L1; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        if (c1.a() == flag && l > 0x1d4c0L)
        {
            s = "(RUNNING expired)";
        } else
        {
            flag = false;
        }
          goto _L4
        s = "(INTERVAL expired)";
          goto _L4
    }
}
