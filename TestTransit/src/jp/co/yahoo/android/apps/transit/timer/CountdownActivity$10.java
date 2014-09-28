// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.view.animation.Animation;
import jp.co.yahoo.android.apps.transit.common.DragAndDropLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.view.animation.stener
{

    final CountdownActivity this$0;

    public void onAnimationEnd(Animation animation)
    {
        CountdownActivity.access$900(CountdownActivity.this).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
    }

    A()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
