// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package android.support.v7.internal.widget:
//            ScrollingTabContainerView

private class <init>
    implements android.view.View.TabClickListener
{

    final ScrollingTabContainerView this$0;

    public void onClick(View view)
    {
        ((<init>)view).<init>().getTab();
        int i = ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildCount();
        int j = 0;
        while (j < i) 
        {
            View view1 = ScrollingTabContainerView.access$200(ScrollingTabContainerView.this).getChildAt(j);
            boolean flag;
            if (view1 == view)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view1.setSelected(flag);
            j++;
        }
    }

    private ()
    {
        this$0 = ScrollingTabContainerView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
