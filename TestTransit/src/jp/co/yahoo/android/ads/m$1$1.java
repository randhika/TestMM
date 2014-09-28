// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.ads:
//            m, a, f, AdContainer, 
//            h

class a
    implements Runnable
{

    final a a;

    public void run()
    {
        f.a(a.tContext(), m.a(a.a).l());
    }

    ainer(ainer ainer)
    {
        a = ainer;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/ads/m$1

/* anonymous class */
    class m._cls1 extends AdContainer
    {

        final m b;

        protected void onAttachedToWindow()
        {
            (new Thread(new m._cls1._cls1(this))).start();
            h.a(m.a(b, m.b(b)), (long)(1000F * Float.parseFloat(m.a(b).m())));
        }

            
            {
                b = m1;
                super(context);
            }
    }

}
