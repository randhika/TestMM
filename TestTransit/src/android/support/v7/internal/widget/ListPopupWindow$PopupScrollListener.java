// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.os.Handler;
import android.widget.AbsListView;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.internal.widget:
//            ListPopupWindow

private class <init>
    implements android.widget.PopupScrollListener
{

    final ListPopupWindow this$0;

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if (i == 1 && !isInputMethodNotNeeded() && ListPopupWindow.access$800(ListPopupWindow.this).getContentView() != null)
        {
            ListPopupWindow.access$1000(ListPopupWindow.this).removeCallbacks(ListPopupWindow.access$900(ListPopupWindow.this));
            ListPopupWindow.access$900(ListPopupWindow.this).run();
        }
    }

    private ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
