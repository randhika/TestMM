// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class this._cls0
    implements android.view.OnGlobalLayoutListener
{

    final MyPageActivity this$0;

    public void onGlobalLayout()
    {
        MyPageActivity.access$2002(MyPageActivity.this, MyPageActivity.access$700(MyPageActivity.this).getHeight() - MyPageActivity.access$2100(MyPageActivity.this).getHeight());
        MyPageActivity.access$2200(MyPageActivity.this).setMinimumHeight(MyPageActivity.access$2000(MyPageActivity.this));
        try
        {
            if (android.os._INT < 16)
            {
                TransitUtil.removeGlobalOnLayoutListener(MyPageActivity.access$2100(MyPageActivity.this).getViewTreeObserver(), this);
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        TransitUtil.removeOnGlobalLayoutListener(MyPageActivity.access$2100(MyPageActivity.this).getViewTreeObserver(), this);
        return;
    }

    itUtil()
    {
        this$0 = MyPageActivity.this;
        super();
    }
}
