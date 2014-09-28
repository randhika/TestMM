// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.location.Location;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

// Referenced classes of package jp.co.yahoo.android.ads:
//            h

public class c
    implements Serializable
{

    public static final DecimalFormat a = new DecimalFormat("#########0.000000");
    public static final SimpleDateFormat b = new SimpleDateFormat("mmss.SSS");
    private int c;
    private String d;
    private long e;
    private long f;
    private double g;
    private double h;
    private double i;
    private double j;
    private double k;
    private double l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private long q;
    private String r;

    public c(String s, long l1)
    {
        e = -1L;
        f = -1L;
        m = false;
        n = false;
        o = false;
        p = false;
        q = -1L;
        d = s;
        e = l1;
        c = 1;
    }

    public static String a(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        String s;
        try
        {
            s = a.format(obj);
        }
        catch (Exception exception)
        {
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("formatLocationValue failed: ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString());
            return "";
        }
        return s;
    }

    public static String b(long l1)
    {
        String s;
        try
        {
            s = b.format(Long.valueOf(l1));
        }
        catch (Exception exception)
        {
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("formatLocationValue failed: ").append(exception.getClass().getName()).append(":").append(exception.getMessage()).toString());
            return "";
        }
        return s;
    }

    public int a()
    {
        return c;
    }

    public void a(int i1)
    {
        c = i1;
    }

    public void a(long l1)
    {
        e = l1;
    }

    public void a(Location location)
    {
        if (location == null)
        {
            l();
            return;
        } else
        {
            f = System.currentTimeMillis();
            g = location.getLatitude();
            h = location.getLongitude();
            i = location.getAccuracy();
            j = location.getAltitude();
            k = location.getSpeed();
            l = location.getBearing();
            q = location.getTime();
            r = location.getProvider();
            m = location.hasBearing();
            n = location.hasSpeed();
            o = location.hasAccuracy();
            p = location.hasAltitude();
            return;
        }
    }

    public long b()
    {
        return e;
    }

    public double c()
    {
        return g;
    }

    public double d()
    {
        return h;
    }

    public double e()
    {
        return i;
    }

    public double f()
    {
        return j;
    }

    public double g()
    {
        return k;
    }

    public double h()
    {
        return l;
    }

    public long i()
    {
        return q;
    }

    public String j()
    {
        return r;
    }

    public String k()
    {
        if (j() == null || i() <= 0L)
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("da=wgs84");
        stringbuilder.append((new StringBuilder()).append("&la=").append(a(Double.valueOf(c()))).toString());
        stringbuilder.append((new StringBuilder()).append("&lo=").append(a(Double.valueOf(d()))).toString());
        stringbuilder.append((new StringBuilder()).append("&ac=").append(a(Double.valueOf(e()))).toString());
        stringbuilder.append((new StringBuilder()).append("&al=").append(a(Double.valueOf(f()))).toString());
        stringbuilder.append((new StringBuilder()).append("&sp=").append(a(Double.valueOf(g()))).toString());
        stringbuilder.append((new StringBuilder()).append("&he=").append(a(Double.valueOf(h()))).toString());
        stringbuilder.append((new StringBuilder()).append("&gpsti=").append(jp.co.yahoo.android.ads.h.a(i())).toString());
        stringbuilder.append("&seti={0}");
        long l1 = f - e;
        stringbuilder.append((new StringBuilder()).append("&fsti=").append(b(l1)).toString());
        StringBuilder stringbuilder1 = (new StringBuilder()).append("&spid=");
        String s;
        if (d == null)
        {
            s = "";
        } else
        {
            s = d;
        }
        stringbuilder.append(stringbuilder1.append(s).toString());
        return stringbuilder.toString();
    }

    public void l()
    {
        d = null;
        f = -1L;
        g = 0.0D;
        h = 0.0D;
        i = 0.0D;
        j = 0.0D;
        k = 0.0D;
        l = 0.0D;
        q = -1L;
        r = null;
        m = false;
        n = false;
        o = false;
        p = false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("provider=").append(j()).toString());
        stringbuilder.append((new StringBuilder()).append(", accuracy=").append(e()).toString());
        stringbuilder.append((new StringBuilder()).append(", latitude=").append(c()).toString());
        stringbuilder.append((new StringBuilder()).append(", longitude=").append(d()).toString());
        stringbuilder.append((new StringBuilder()).append(", altitude=").append(f()).toString());
        stringbuilder.append((new StringBuilder()).append(", time=").append(i()).toString());
        stringbuilder.append((new StringBuilder()).append(", speed=").append(g()).toString());
        stringbuilder.append((new StringBuilder()).append(", bearing=").append(h()).toString());
        stringbuilder.append((new StringBuilder()).append(", hasAccuracy=").append(o).toString());
        stringbuilder.append((new StringBuilder()).append(", hasAltitude=").append(p).toString());
        stringbuilder.append((new StringBuilder()).append(", hasSpeed=").append(n).toString());
        stringbuilder.append((new StringBuilder()).append(", hasBearing=").append(m).toString());
        return (new StringBuilder()).append("LocationInfo[location=[").append(stringbuilder.toString()).append("], spaceId=").append(d).append(", startTime=").append(e).append(", updateTime=").append(f).append("]").toString();
    }

}
