// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view.menu;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.internal.view.menu:
//            MenuItemWrapperICS

static class addView extends FrameLayout
    implements CollapsibleActionView
{

    final android.support.v7.view.CollapsibleActionView mWrappedView;

    View getWrappedView()
    {
        return (View)mWrappedView;
    }

    public void onActionViewCollapsed()
    {
        mWrappedView.onActionViewCollapsed();
    }

    public void onActionViewExpanded()
    {
        mWrappedView.onActionViewExpanded();
    }

    (View view)
    {
        super(view.getContext());
        mWrappedView = (android.support.v7.view.CollapsibleActionView)view;
        addView(view);
    }
}
