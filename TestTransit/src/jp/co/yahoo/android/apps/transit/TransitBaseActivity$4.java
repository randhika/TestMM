// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.inputmethod.InputMethodManager;
import jp.co.yahoo.android.common.hamburger.YHBGRd;
import jp.co.yahoo.android.common.hamburger.YSimpleSideDrawer;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements jp.co.yahoo.android.common.hamburger.eListener
{

    final TransitBaseActivity this$0;

    public void onStateChanged(int i)
    {
        if (i == 2)
        {
            TransitBaseActivity.access$000(TransitBaseActivity.this).hideSoftInputFromWindow(mDrawer.getWindowToken(), 0);
            YHBGRd.sendAsync(getApplicationContext(), new String[] {
                "open"
            });
            setDiainfoMenuState(isDiainfo());
            setOtherMenuState(isAlarm());
            setHumbergarIcon();
            onOpenSlideMenu();
        }
    }

    StateListener()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
