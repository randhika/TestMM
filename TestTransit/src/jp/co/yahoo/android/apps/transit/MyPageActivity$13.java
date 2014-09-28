// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.view.animation.Animation;
import jp.co.yahoo.android.apps.transit.common.FixedHeaderScrollView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity

class this._cls0
    implements android.view.animation.nListener
{

    final MyPageActivity this$0;

    public void onAnimationEnd(Animation animation)
    {
        MyPageActivity.access$700(MyPageActivity.this).setHeaderView(MyPageActivity.access$000(MyPageActivity.this), MyPageActivity.access$100(MyPageActivity.this));
        MyPageActivity.access$2800(MyPageActivity.this).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
        MyPageActivity.access$2700(MyPageActivity.this).setVisibility(0);
    }

    ScrollView()
    {
        this$0 = MyPageActivity.this;
        super();
    }
}
