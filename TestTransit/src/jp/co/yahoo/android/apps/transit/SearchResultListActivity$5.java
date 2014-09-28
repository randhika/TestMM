// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.res.Resources;
import android.widget.TabHost;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListActivity, SearchResultListView

class this._cls0
    implements android.widget.
{

    final SearchResultListActivity this$0;

    public void onTabChanged(String s)
    {
        SearchResultListView searchresultlistview = (SearchResultListView)SearchResultListActivity.access$300(SearchResultListActivity.this).getCurrentView();
        int i = getResources().getInteger(0x7f0c0026);
        if (searchresultlistview.getId() == 0x7f0a002f)
        {
            i = getResources().getInteger(0x7f0c0030);
            SearchResultListActivity.access$000(SearchResultListActivity.this).setVisibility(0);
            SearchResultListActivity.access$100(SearchResultListActivity.this).setVisibility(8);
            SearchResultListActivity.access$200(SearchResultListActivity.this).setVisibility(8);
        } else
        if (searchresultlistview.getId() == 0x7f0a002e)
        {
            i = getResources().getInteger(0x7f0c002f);
            SearchResultListActivity.access$000(SearchResultListActivity.this).setVisibility(8);
            SearchResultListActivity.access$100(SearchResultListActivity.this).setVisibility(0);
            SearchResultListActivity.access$200(SearchResultListActivity.this).setVisibility(8);
        } else
        if (searchresultlistview.getId() == 0x7f0a002d)
        {
            i = getResources().getInteger(0x7f0c002e);
            SearchResultListActivity.access$000(SearchResultListActivity.this).setVisibility(8);
            SearchResultListActivity.access$100(SearchResultListActivity.this).setVisibility(8);
            SearchResultListActivity.access$200(SearchResultListActivity.this).setVisibility(0);
        }
        if (!searchresultlistview.isDataFilled())
        {
            if (SearchResultListActivity.access$400(SearchResultListActivity.this).sort != i)
            {
                SearchResultListActivity.access$502(SearchResultListActivity.this, (ConditionData)SearchResultListActivity.access$400(SearchResultListActivity.this).clone());
                SearchResultListActivity.access$500(SearchResultListActivity.this).sort = i;
                search();
            }
            return;
        } else
        {
            SearchResultListActivity.access$400(SearchResultListActivity.this).sort = i;
            return;
        }
    }

    A()
    {
        this$0 = SearchResultListActivity.this;
        super();
    }
}
