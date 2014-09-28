// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final SearchResultListActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        String s = apierror.getMessage();
        if (StringUtil.isEmpty(s))
        {
            s = getString(0x7f0d0108);
        }
        showSimpleErrorMessageDialog(s);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData navisearchdata = NaviSearch.m_routeList;
        SearchResultListActivity.access$402(SearchResultListActivity.this, SearchResultListActivity.access$500(SearchResultListActivity.this));
        if (SearchResultListActivity.access$400(SearchResultListActivity.this).afterFinal)
        {
            SearchResultListActivity.access$600(SearchResultListActivity.this, SearchResultListActivity.access$400(SearchResultListActivity.this), navisearchdata);
        } else
        {
            SearchResultListActivity.access$700(SearchResultListActivity.this, navisearchdata);
        }
        SearchResultListActivity.access$800(SearchResultListActivity.this);
        SearchResultListActivity.access$900(SearchResultListActivity.this, navisearchdata);
        return false;
    }

    A()
    {
        this$0 = SearchResultListActivity.this;
        super();
    }
}
