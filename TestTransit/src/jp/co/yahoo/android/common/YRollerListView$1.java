// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.widget.AbsListView;

// Referenced classes of package jp.co.yahoo.android.common:
//            YRollerListView

class val.l
    implements android.widget.llListener
{

    final YRollerListView this$0;
    final android.widget.llListener val$l;

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        val$l.onScroll(abslistview, i, j, k);
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        val$l.onScrollStateChanged(abslistview, i);
        YRollerListView.this.onScrollStateChanged(abslistview, i);
    }

    ()
    {
        this$0 = final_yrollerlistview;
        val$l = android.widget.llListener.this;
        super();
    }
}
