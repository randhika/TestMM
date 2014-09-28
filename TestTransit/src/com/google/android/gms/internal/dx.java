// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            eu, bi, ec, du, 
//            dz, ds, ai, dy, 
//            ay, et, eb, ed, 
//            ev, ea, eo, al, 
//            ex, ey, bb

public final class dx extends dw.a
{

    private static final Object qp = new Object();
    private static dx qq;
    private final Context mContext;
    private final ed qr;
    private final bi qs;
    private final ay qt;

    dx(Context context, ay ay1, bi bi1, ed ed1)
    {
        mContext = context;
        qr = ed1;
        qs = bi1;
        qt = ay1;
    }

    private static du a(Context context, ay ay1, bi bi1, ed ed1, ds ds1)
    {
        eu.z("Starting ad request from service.");
        bi1.init();
        ec ec1 = new ec(context);
        if (ec1.rm == -1)
        {
            eu.z("Device is offline.");
            return new du(2);
        }
        dz dz1 = new dz(ds1.applicationInfo.packageName);
        if (ds1.pX.extras != null)
        {
            String s4 = ds1.pX.extras.getString("_ad");
            if (s4 != null)
            {
                return dy.a(context, ds1, s4);
            }
        }
        android.location.Location location = bi1.a(250L);
        String s1 = ay1.aN();
        String s2 = dy.a(ds1, ec1, location, ay1.aO());
        if (s2 == null)
        {
            return new du(0);
        }
        ey.a a1 = s(s2);
        et.sv.post(new Runnable(context, ds1, dz1, a1, s1) {

            final Context qu;
            final ds qv;
            final dz qw;
            final ey.a qx;
            final String qy;

            public void run()
            {
                ex ex1 = ex.a(qu, new al(), false, false, null, qv.kQ);
                ex1.setWillNotDraw(true);
                qw.b(ex1);
                ey ey1 = ex1.cb();
                ey1.a("/invalidRequest", qw.qD);
                ey1.a("/loadAdURL", qw.qE);
                ey1.a("/log", bb.mZ);
                ey1.a(qx);
                eu.z("Loading the JS library.");
                ex1.loadUrl(qy);
            }

            
            {
                qu = context;
                qv = ds1;
                qw = dz1;
                qx = a1;
                qy = s1;
                super();
            }
        });
        eb eb1 = dz1.bx();
        if (eb1 == null || TextUtils.isEmpty(eb1.getUrl()))
        {
            return new du(dz1.getErrorCode());
        }
        boolean flag = eb1.bA();
        String s3 = null;
        if (flag)
        {
            s3 = ed1.u(ds1.pY.packageName);
        }
        return a(context, ds1.kQ.sw, eb1.getUrl(), s3, eb1);
    }

    public static du a(Context context, String s1, String s2, String s3, eb eb1)
    {
        HttpURLConnection httpurlconnection;
        ea ea1;
        URL url;
        long l;
        URL url1;
        int i;
        int j;
        Map map;
        du du1;
        String s4;
        du du2;
        du du3;
        String s5;
        String s6;
        du du4;
        byte abyte0[];
        BufferedOutputStream bufferedoutputstream;
        try
        {
            ea1 = new ea();
            url = new URL(s2);
            l = SystemClock.elapsedRealtime();
        }
        catch (IOException ioexception)
        {
            eu.D((new StringBuilder()).append("Error while connecting to ad server: ").append(ioexception.getMessage()).toString());
            return new du(2);
        }
        url1 = url;
        i = 0;
        httpurlconnection = (HttpURLConnection)url1.openConnection();
        eo.a(context, s1, false, httpurlconnection);
        if (!TextUtils.isEmpty(s3))
        {
            httpurlconnection.addRequestProperty("x-afma-drt-cookie", s3);
        }
        if (eb1 == null)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (!TextUtils.isEmpty(eb1.bz()))
        {
            httpurlconnection.setDoOutput(true);
            abyte0 = eb1.bz().getBytes();
            httpurlconnection.setFixedLengthStreamingMode(abyte0.length);
            bufferedoutputstream = new BufferedOutputStream(httpurlconnection.getOutputStream());
            bufferedoutputstream.write(abyte0);
            bufferedoutputstream.close();
        }
        j = httpurlconnection.getResponseCode();
        map = httpurlconnection.getHeaderFields();
        if (j < 200 || j >= 300)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        s5 = url1.toString();
        s6 = eo.a(new InputStreamReader(httpurlconnection.getInputStream()));
        a(s5, map, s6, j);
        ea1.a(s5, map, s6);
        du4 = ea1.i(l);
        httpurlconnection.disconnect();
        return du4;
        a(url1.toString(), map, ((String) (null)), j);
        if (j < 300 || j >= 400)
        {
            break MISSING_BLOCK_LABEL_338;
        }
        s4 = httpurlconnection.getHeaderField("Location");
        if (!TextUtils.isEmpty(s4))
        {
            break MISSING_BLOCK_LABEL_294;
        }
        eu.D("No location header to follow redirect.");
        du3 = new du(0);
        httpurlconnection.disconnect();
        return du3;
        url1 = new URL(s4);
        if (++i <= 5)
        {
            break MISSING_BLOCK_LABEL_380;
        }
        eu.D("Too many redirects.");
        du2 = new du(0);
        httpurlconnection.disconnect();
        return du2;
        eu.D((new StringBuilder()).append("Received error HTTP response code: ").append(j).toString());
        du1 = new du(0);
        httpurlconnection.disconnect();
        return du1;
        ea1.d(map);
        httpurlconnection.disconnect();
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
    }

    public static dx a(Context context, ay ay1, bi bi1, ed ed1)
    {
        dx dx1;
        synchronized (qp)
        {
            if (qq == null)
            {
                qq = new dx(context.getApplicationContext(), ay1, bi1, ed1);
            }
            dx1 = qq;
        }
        return dx1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static void a(String s1, Map map, String s2, int i)
    {
        if (eu.p(2))
        {
            eu.C((new StringBuilder()).append("Http Response: {\n  URL:\n    ").append(s1).append("\n  Headers:").toString());
            if (map != null)
            {
                for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
                {
                    String s3 = (String)iterator.next();
                    eu.C((new StringBuilder()).append("    ").append(s3).append(":").toString());
                    Iterator iterator1 = ((List)map.get(s3)).iterator();
                    while (iterator1.hasNext()) 
                    {
                        String s4 = (String)iterator1.next();
                        eu.C((new StringBuilder()).append("      ").append(s4).toString());
                    }
                }

            }
            eu.C("  Body:");
            if (s2 != null)
            {
                for (int j = 0; j < Math.min(s2.length(), 0x186a0); j += 1000)
                {
                    eu.C(s2.substring(j, Math.min(s2.length(), j + 1000)));
                }

            } else
            {
                eu.C("    null");
            }
            eu.C((new StringBuilder()).append("  Response Code:\n    ").append(i).append("\n}").toString());
        }
    }

    private static ey.a s(String s1)
    {
        return new ey.a(s1) {

            final String qz;

            public void a(ex ex1)
            {
                Object aobj[] = new Object[2];
                aobj[0] = "AFMA_buildAdURL";
                aobj[1] = qz;
                String s2 = String.format("javascript:%s(%s);", aobj);
                eu.C((new StringBuilder()).append("About to execute: ").append(s2).toString());
                ex1.loadUrl(s2);
            }

            
            {
                qz = s1;
                super();
            }
        };
    }

    public du b(ds ds1)
    {
        return a(mContext, qt, qs, qr, ds1);
    }

}
