// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorCompat

class val.runnable
    implements ViewPropertyAnimatorListener
{

    final tListener this$0;
    final Runnable val$runnable;

    public void onAnimationCancel(View view)
    {
    }

    public void onAnimationEnd(View view)
    {
    }

    public void onAnimationStart(View view)
    {
        val$runnable.run();
        tListener(view, null);
    }

    ()
    {
        this$0 = final_;
        val$runnable = Runnable.this;
        super();
    }
}
