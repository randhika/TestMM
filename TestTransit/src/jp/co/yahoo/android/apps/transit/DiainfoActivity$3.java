// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.PopupNew;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity

class this._cls0
    implements Runnable
{

    final DiainfoActivity this$0;

    public void run()
    {
        View view = findViewById(0);
        if (view != null)
        {
            float f = view.getWidth();
            float f1 = TransitUtil.dpToPx(DiainfoActivity.this, 133F);
            float f2 = TransitUtil.dpToPx(DiainfoActivity.this, 7F);
            float f3 = f - f1 - f2;
            DiainfoActivity.access$302(DiainfoActivity.this, new PopupNew(DiainfoActivity.this, view));
            DiainfoActivity.access$300(DiainfoActivity.this).display((int)f3, 0, -1, getString(0x7f0d03a5), 0x7f02029e, false);
        }
    }

    ()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}
