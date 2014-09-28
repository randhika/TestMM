// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListView, TransitBaseActivity, SearchResultActivity

class val.INDEX
    implements android.view.rchResultListView._cls1
{

    final SearchResultListView this$0;
    final int val$INDEX;

    public void onClick(View view)
    {
        ((TransitBaseActivity)SearchResultListView.access$000(SearchResultListView.this)).touchTapRD((new StringBuilder()).append(SearchResultListView.access$000(SearchResultListView.this).getString(0x7f0d0412)).append("/").append(Integer.toString(val$INDEX)).toString());
        ConditionData conditiondata = (ConditionData)SearchResultListView.access$100(SearchResultListView.this).clone();
        conditiondata.afterFinal = SearchResultListView.access$200(SearchResultListView.this).booleanValue();
        conditiondata.mtf = 1 + val$INDEX;
        Intent intent = new Intent(SearchResultListView.access$000(SearchResultListView.this), jp/co/yahoo/android/apps/transit/SearchResultActivity);
        intent.putExtra(SearchResultListView.access$000(SearchResultListView.this).getString(0x7f0d0231), val$INDEX);
        intent.putExtra(SearchResultListView.access$000(SearchResultListView.this).getString(0x7f0d0232), SearchResultListView.access$300(SearchResultListView.this));
        intent.putExtra(SearchResultListView.access$000(SearchResultListView.this).getString(0x7f0d022c), conditiondata);
        ((TransitBaseActivity)SearchResultListView.access$000(SearchResultListView.this)).startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
    }

    ()
    {
        this$0 = final_searchresultlistview;
        val$INDEX = I.this;
        super();
    }
}
