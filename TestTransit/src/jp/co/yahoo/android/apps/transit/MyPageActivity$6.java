// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.db.ResultDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class this._cls0
    implements android.view.ner
{

    final MyPageActivity this$0;

    public void onClick(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0440)).append("/").append(getString(0x7f0d03f9)).toString());
        LinearLayout linearlayout = (LinearLayout)view.getParent();
        int i = MyPageActivity.access$1500(MyPageActivity.this).countMyroute();
        ConditionData conditiondata = (ConditionData)((Bundle)linearlayout.getTag()).getSerializable(getString(0x7f0d022c));
        registMyroute(MyPageActivity.access$1500(MyPageActivity.this), conditiondata);
        if (i == 0 && MyPageActivity.access$1500(MyPageActivity.this).countMyroute() > 0)
        {
            MyPageActivity.access$1700(MyPageActivity.this).setImageResource(0x7f0200f4);
            MyPageActivity.access$1700(MyPageActivity.this).setContentDescription(getString(0x7f0d0356));
        }
    }

    nData()
    {
        this$0 = MyPageActivity.this;
        super();
    }
}
