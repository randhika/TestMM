// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultView

class this._cls0
    implements Runnable
{

    final SearchResultView this$0;

    public void run()
    {
        View view2;
        int k;
        View view = SearchResultView.access$200(SearchResultView.this).findViewWithTag(Integer.toString(SearchResultView.access$100(SearchResultView.this)));
        int i = view.getTop() + ((View)view.getParent()).getTop() + SearchResultView.access$300(SearchResultView.this).getTop();
        View view1 = (View)SearchResultView.access$300(SearchResultView.this).getParent();
        int j = i + view1.getTop();
        view2 = (View)view1.getParent();
        k = j + view2.getTop();
        SearchResultView.access$300(SearchResultView.this).getId();
        JVM INSTR tableswitch 2131362603 2131362604: default 120
    //                   2131362603 150
    //                   2131362604 205;
           goto _L1 _L2 _L3
_L1:
        int l = k + SearchResultView.access$300(SearchResultView.this).getHeight();
        SearchResultView.access$400(SearchResultView.this).scrollToImakoko(l);
        return;
_L2:
        View view4 = (View)view2.getParent();
        int i1 = k + view4.getTop();
        View view5 = (View)view4.getParent();
        k = i1 + view5.getTop() + ((View)view5.getParent()).getTop();
        continue; /* Loop/switch isn't completed */
_L3:
        View view3 = (View)view2.getParent();
        k = k + view3.getTop() + ((View)view3.getParent()).getTop();
        if (true) goto _L1; else goto _L4
_L4:
    }

    archResultCallback()
    {
        this$0 = SearchResultView.this;
        super();
    }
}
