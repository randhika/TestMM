// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdContainer, m, a, h, 
//            f

class ntainer extends AdContainer
{

    final m b;

    protected void onAttachedToWindow()
    {
        (new Thread(new Runnable() {

            final m._cls1 a;

            public void run()
            {
                f.a(a.getContext(), m.a(a.b).l());
            }

            
            {
                a = m._cls1.this;
                super();
            }
        })).start();
        h.a(m.a(b, m.b(b)), (long)(1000F * Float.parseFloat(m.a(b).m())));
    }

    _cls1.a(m m1, Context context)
    {
        b = m1;
        super(context);
    }
}
