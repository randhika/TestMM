// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.view.
{

    final CountdownActivity this$0;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        nDoubleTap = 2;
        if (CountdownActivity.access$700(CountdownActivity.this).onTouchEvent(motionevent))
        {
            return true;
        } else
        {
            return onTouchEvent(motionevent);
        }
    }

    ()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
