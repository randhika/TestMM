// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.view.View;

// Referenced classes of package android.support.v7.internal.widget:
//            ScrollingTabContainerView

class val.tabView
    implements Runnable
{

    final ScrollingTabContainerView this$0;
    final View val$tabView;

    public void run()
    {
        int i = val$tabView.getLeft() - (getWidth() - val$tabView.getWidth()) / 2;
        smoothScrollTo(i, 0);
        mTabSelector = null;
    }

    ()
    {
        this$0 = final_scrollingtabcontainerview;
        val$tabView = View.this;
        super();
    }
}
