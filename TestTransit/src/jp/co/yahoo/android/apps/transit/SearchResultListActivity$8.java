// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.PopupNew;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListActivity

class this._cls0
    implements Runnable
{

    final SearchResultListActivity this$0;

    public void run()
    {
        View view = findViewById(0);
        if (view != null)
        {
            float f = view.getWidth();
            float f1 = TransitUtil.dpToPx(SearchResultListActivity.this, 141F);
            float f2 = TransitUtil.dpToPx(SearchResultListActivity.this, 7F);
            float f3 = (f - f1) / 2.0F - f2;
            SearchResultListActivity.access$1602(SearchResultListActivity.this, new PopupNew(SearchResultListActivity.this, view));
            SearchResultListActivity.access$1600(SearchResultListActivity.this).display((int)f3, 0, -1, getString(0x7f0d03a4), 0x7f02029d, false);
        }
    }

    A()
    {
        this$0 = SearchResultListActivity.this;
        super();
    }
}
