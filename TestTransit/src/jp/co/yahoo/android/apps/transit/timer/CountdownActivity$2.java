// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.view.lobalLayoutListener
{

    final CountdownActivity this$0;

    public void onGlobalLayout()
    {
        int i = (int)TypedValue.applyDimension(1, 11F, res.getDisplayMetrics());
        android.widget.rams rams = (android.widget.rams)CountdownActivity.access$000(CountdownActivity.this).getLayoutParams();
        rams.topMargin = (CountdownActivity.access$100(CountdownActivity.this).getTop() + ((View)CountdownActivity.access$100(CountdownActivity.this).getParent()).getTop()) - i;
        CountdownActivity.access$000(CountdownActivity.this).setLayoutParams(rams);
        CountdownActivity.access$000(CountdownActivity.this).setVisibility(0);
        if (android.os.T < 16)
        {
            TransitUtil.removeGlobalOnLayoutListener(CountdownActivity.access$100(CountdownActivity.this).getViewTreeObserver(), this);
            return;
        } else
        {
            TransitUtil.removeOnGlobalLayoutListener(CountdownActivity.access$100(CountdownActivity.this).getViewTreeObserver(), this);
            return;
        }
    }

    ()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
