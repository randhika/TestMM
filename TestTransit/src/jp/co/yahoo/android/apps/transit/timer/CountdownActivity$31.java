// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import jp.co.yahoo.android.apps.transit.timer.common.CountdownPanelManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.timer.viewparts..CountdownTimelineListener
{

    final CountdownActivity this$0;

    public void onClickDia(int i)
    {
        CountdownActivity.access$2302(CountdownActivity.this, true);
        CountdownActivity.access$300(CountdownActivity.this).changeTargetIndex(i);
    }

    public void onFinishShow(int i)
    {
        CountdownActivity.access$300(CountdownActivity.this).changeTargetIndex(i);
    }

    Manager()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
