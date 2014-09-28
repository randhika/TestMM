// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.graphics.Bitmap;
import java.util.Arrays;

// Referenced classes of package jp.co.yahoo.android.ads:
//            f, h

public class a
{

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private boolean h;
    private Bitmap i;
    private String j;
    private String k;
    private String l;
    private Bitmap m;
    private String n[];
    private String o[];
    private String p[];
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;

    public a()
    {
        a = "";
        b = "";
        c = "";
        d = "";
        e = "";
        f = 0;
        g = 0;
        h = false;
        i = null;
        j = "";
        k = "";
        l = "";
        m = null;
        n = new String[5];
        o = new String[5];
        p = new String[5];
        q = "";
        r = "";
        s = "";
        t = "";
        u = "";
        v = "";
        w = "";
        x = "";
        y = "";
    }

    public String a()
    {
        return a;
    }

    public void a(int i1)
    {
        f = i1;
    }

    public void a(String s1)
    {
        a = s1;
    }

    public void a(String s1, int i1)
    {
        n[i1] = s1;
    }

    public void a(boolean flag)
    {
        h = flag;
    }

    public String b()
    {
        return b;
    }

    public void b(int i1)
    {
        g = i1;
    }

    public void b(String s1)
    {
        b = s1;
    }

    public void b(String s1, int i1)
    {
        o[i1] = s1;
    }

    public String c()
    {
        return c;
    }

    public void c(String s1)
    {
        c = s1;
    }

    public void c(String s1, int i1)
    {
        p[i1] = s1;
    }

    public String d()
    {
        return e;
    }

    public void d(String s1)
    {
        e = s1;
    }

    public int e()
    {
        return f;
    }

    public void e(String s1)
    {
        j = s1;
    }

    public int f()
    {
        return g;
    }

    public void f(String s1)
    {
        k = s1;
    }

    public String g()
    {
        return j;
    }

    public void g(String s1)
    {
        l = s1;
    }

    public void h(String s1)
    {
        s = s1;
    }

    public String[] h()
    {
        return n;
    }

    public void i(String s1)
    {
        t = s1;
    }

    public String[] i()
    {
        return o;
    }

    public String j()
    {
        return s;
    }

    public void j(String s1)
    {
        u = s1;
    }

    public String k()
    {
        return t;
    }

    public void k(String s1)
    {
        q = s1;
    }

    public String l()
    {
        return u;
    }

    public void l(String s1)
    {
        v = s1;
    }

    public String m()
    {
        return q;
    }

    public void m(String s1)
    {
        w = s1;
    }

    public String n()
    {
        return v;
    }

    public void n(String s1)
    {
        x = s1;
    }

    public String o()
    {
        return w;
    }

    public void o(String s1)
    {
        y = s1;
    }

    public String p()
    {
        return x;
    }

    public String q()
    {
        return y;
    }

    public Bitmap r()
    {
        if (i != null)
        {
            return i;
        }
        i = jp.co.yahoo.android.ads.f.b(e, false);
        if (i == null)
        {
            jp.co.yahoo.android.ads.h.a(5, (new StringBuilder()).append("Could not get portrait image for ad from ").append(e).toString());
        }
        return i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("[Ad Class START]").append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("apos : ").append(a).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("position : ").append(b).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("status : ").append(c).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("type : ").append(d).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("portraitURL : ").append(e).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("portraitHeight : ").append(f).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("portraitWidth : ").append(g).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("pr : ").append(h).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("portrait : ").append(i).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("expandPortraitURL : ").append(j).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("expandLandscapeURL : ").append(k).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("backgroundcolor : ").append(l).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("expand : ").append(m).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("href Array : ").append(Arrays.toString(n)).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("portraitCoords : ").append(Arrays.toString(o)).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("landscapeCoords : ").append(Arrays.toString(p)).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("waitsec : ").append(q).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("intervalmin : ").append(r).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("adcsc : ").append(u).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("adhtml : ").append(v).append("\n").toString());
        stringbuilder.append((new StringBuilder()).append("vastXml : ").append(y).append("\n").toString());
        stringbuilder.append("[Ad Class END]");
        return stringbuilder.toString();
    }
}
