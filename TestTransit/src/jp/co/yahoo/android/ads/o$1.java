// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.webkit.WebView;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jp.co.yahoo.android.ads:
//            o, AdContainer

class a
    implements Runnable
{

    final AdContainer a;
    final o b;

    public void run()
    {
        o.a(b, new WebView(o.a(b)));
        o.a(b, o.b(b), a);
        a.addView(o.b(b));
        android.widget.tiveLayout.LayoutParams layoutparams = new android.widget.tiveLayout.LayoutParams(-1, -2);
        o.b(b).setLayoutParams(layoutparams);
        o.c(b).set(true);
    }

    ntainer(o o1, AdContainer adcontainer)
    {
        b = o1;
        a = adcontainer;
        super();
    }
}
