// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.view.menu;

import android.view.View;

// Referenced classes of package android.support.v7.internal.view.menu:
//            ActionMenuPresenter, MenuBuilder

private class mPopup
    implements Runnable
{

    private this._cls0 mPopup;
    final ActionMenuPresenter this$0;

    public void run()
    {
        mMenu.changeMenuMode();
        View view = (View)mMenuView;
        if (view != null && view.getWindowToken() != null && mPopup.())
        {
            ActionMenuPresenter.access$102(ActionMenuPresenter.this, mPopup);
        }
        ActionMenuPresenter.access$302(ActionMenuPresenter.this, null);
    }

    public ( )
    {
        this$0 = ActionMenuPresenter.this;
        super();
        mPopup = ;
    }
}
