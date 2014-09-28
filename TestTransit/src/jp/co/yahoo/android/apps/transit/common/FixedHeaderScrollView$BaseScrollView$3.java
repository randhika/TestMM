// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            FixedHeaderScrollView

class val.headerTop
    implements Runnable
{

    final val.headerTop this$0;
    final int val$headerTop;
    final int val$scrollY;

    public void run()
    {
        if (val$scrollY < val$headerTop / 2)
        {
            oothScrollTo(tScrollX(), 0);
            return;
        } else
        {
            oothScrollTo(tScrollX(), val$headerTop);
            return;
        }
    }

    ()
    {
        this$0 = final_;
        val$scrollY = i;
        val$headerTop = I.this;
        super();
    }
}
