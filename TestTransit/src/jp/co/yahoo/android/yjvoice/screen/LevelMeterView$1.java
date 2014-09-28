// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.os.Handler;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            LevelMeterView

class this._cls0
    implements Runnable
{

    final LevelMeterView this$0;

    public void run()
    {
        int _tmp = LevelMeterView.access$008(LevelMeterView.this);
        if (LevelMeterView.access$000(LevelMeterView.this) < 5)
        {
            LevelMeterView.access$100(LevelMeterView.this).invalidate();
            LevelMeterView.access$200(LevelMeterView.this).postDelayed(this, 100L);
            return;
        } else
        {
            LevelMeterView.access$002(LevelMeterView.this, -1);
            return;
        }
    }

    _cls9()
    {
        this$0 = LevelMeterView.this;
        super();
    }
}
