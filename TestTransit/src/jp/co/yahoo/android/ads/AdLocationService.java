// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h, c, d

public class AdLocationService extends Service
{
    private class a
        implements LocationListener
    {

        final AdLocationService a;
        private LocationManager b;

        private boolean a(double d1)
        {
            int i = Double.compare(d1, 10D);
            boolean flag = false;
            if (i < 0)
            {
                flag = true;
            }
            h.a(3, (new StringBuilder()).append("[ALListener] isValidAdLocation: ").append(flag).append(" (accuracy=").append(d1).append(")").toString());
            return flag;
        }

        public boolean a()
        {
label0:
            {
                if (b == null)
                {
                    try
                    {
                        AdLocationService.a(a).a(System.currentTimeMillis());
                        b = (LocationManager)a.getApplicationContext().getSystemService("location");
                        List list = b.getProviders(true);
                        h.a(3, (new StringBuilder()).append("[ALRequester] providers=").append(list).toString());
                        String s;
                        for (Iterator iterator = list.iterator(); iterator.hasNext(); b.requestLocationUpdates(s, 10000L, 10F, AdLocationService.a()))
                        {
                            s = (String)iterator.next();
                        }

                        break label0;
                    }
                    catch (Exception exception)
                    {
                        h.a(6, (new StringBuilder()).append("[ALRequester] error.").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString(), exception);
                        b = null;
                    }
                }
                return false;
            }
            AdLocationService.b(a);
            return true;
        }

        public boolean a(Location location)
        {
            return AdLocationService.c(a) == null || Double.compare(AdLocationService.c(a).getAccuracy(), location.getAccuracy()) > 0;
        }

        public void b()
        {
            if (b != null)
            {
                b.removeUpdates(this);
                b = null;
            }
        }

        public void onLocationChanged(Location location)
        {
            h.a(2, (new StringBuilder()).append("[ALListener] onLocationChanged: location=").append(location).toString());
            if (a(location))
            {
                AdLocationService.a(a, location);
                h.a(3, "[ALListener] location updated.");
            }
            if (a(location.getAccuracy()))
            {
                a.stopSelf();
            }
        }

        public void onProviderDisabled(String s)
        {
        }

        public void onProviderEnabled(String s)
        {
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
        }

        private a()
        {
            a = AdLocationService.this;
            super();
        }

    }


    private static a e;
    private TimerTask a;
    private Timer b;
    private c c;
    private Location d;

    public AdLocationService()
    {
    }

    static Location a(AdLocationService adlocationservice, Location location)
    {
        adlocationservice.d = location;
        return location;
    }

    static a a()
    {
        return e;
    }

    static c a(AdLocationService adlocationservice)
    {
        return adlocationservice.c;
    }

    private void b()
    {
        this;
        JVM INSTR monitorenter ;
        b = new Timer();
        a = new TimerTask(new Handler()) {

            final Handler a;
            final AdLocationService b;

            public void run()
            {
                a.post(new Runnable(this) {

                    final _cls1 a;

                    public void run()
                    {
                        a.b.stopSelf();
                        h.a(3, "[ALService] Time out");
                    }

            
            {
                a = _pcls1;
                super();
            }
                });
            }

            
            {
                b = AdLocationService.this;
                a = handler;
                super();
            }
        };
        b.schedule(a, 60000L);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static void b(AdLocationService adlocationservice)
    {
        adlocationservice.b();
    }

    static Location c(AdLocationService adlocationservice)
    {
        return adlocationservice.d;
    }

    private void c()
    {
        this;
        JVM INSTR monitorenter ;
        if (e != null)
        {
            e.b();
            e = null;
        }
        if (a != null)
        {
            a.cancel();
            a = null;
        }
        if (b != null)
        {
            b.cancel();
            b = null;
        }
        h.a(3, "[ALService] clearService End");
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception2;
        exception2;
        h.a(3, (new StringBuilder()).append("[ALService] clearService: ").append(exception2.getClass().getName()).append(": ").append(exception2.getMessage()).toString());
        h.a(3, "[ALService] clearService End");
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        h.a(3, "[ALService] clearService End");
        throw exception;
    }

    public IBinder onBind(Intent intent)
    {
        h.a(3, (new StringBuilder()).append("[ALService] onBind: intent=").append(intent).toString());
        return null;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        h.a(3, (new StringBuilder()).append("[ALService] onConfigurationChanged: newConfig=").append(configuration).toString());
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy()
    {
        h.a(3, "[ALService] onDestroy: ");
        c();
        c.a(0);
        if (d != null)
        {
            c.a(d);
        }
        jp.co.yahoo.android.ads.d.a(this, c);
        super.onDestroy();
    }

    public void onStart(Intent intent, int i)
    {
        this;
        JVM INSTR monitorenter ;
        super.onStart(intent, i);
        h.a(3, (new StringBuilder()).append("[ALService] onStart: intent=").append(intent).toString());
        if (e != null) goto _L2; else goto _L1
_L1:
        e = new a();
        c = (c)intent.getSerializableExtra("info");
        if (c == null) goto _L2; else goto _L3
_L3:
        boolean flag = e.a();
        if (!flag) goto _L2; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        stopSelf();
        if (true) goto _L4; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean onUnbind(Intent intent)
    {
        h.a(3, (new StringBuilder()).append("[ALService] onUnbind: intent=").append(intent).toString());
        return super.onUnbind(intent);
    }

    public ComponentName startService(Intent intent)
    {
        h.a(3, (new StringBuilder()).append("[ALService] startService: service=").append(intent).toString());
        return super.startService(intent);
    }

    public boolean stopService(Intent intent)
    {
        h.a(3, (new StringBuilder()).append("[ALService] stopService: name=").append(intent).toString());
        return super.stopService(intent);
    }
}
