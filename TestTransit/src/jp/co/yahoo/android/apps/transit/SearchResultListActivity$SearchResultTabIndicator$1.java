// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.TabHost;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListActivity

class val.this._cls0
    implements android.view.esultTabIndicator._cls1
{

    final is._cls0 this$1;
    final SearchResultListActivity val$this$0;

    public void onClick(View view)
    {
        if (view == SearchResultListActivity.access$1000(_fld0).tIndicator())
        {
            touchTapRD(getString(0x7f0d03d8));
            SearchResultListActivity.access$300(_fld0).setCurrentTab(0);
        } else
        {
            if (view == SearchResultListActivity.access$1100(_fld0).tIndicator())
            {
                touchTapRD(getString(0x7f0d03d9));
                SearchResultListActivity.access$300(_fld0).setCurrentTab(1);
                return;
            }
            if (view == SearchResultListActivity.access$1200(_fld0).tIndicator())
            {
                touchTapRD(getString(0x7f0d03d0));
                SearchResultListActivity.access$300(_fld0).setCurrentTab(2);
                return;
            }
        }
    }

    ()
    {
        this$1 = final_;
        val$this$0 = SearchResultListActivity.this;
        super();
    }
}
