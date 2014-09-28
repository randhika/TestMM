// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultActivity, SearchResultListActivity

class val.conditionData
    implements jp.co.yahoo.android.apps.transit.api.conditionData
{

    final SearchResultActivity this$0;
    final ConditionData val$conditionData;

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
        Intent intent = new Intent(SearchResultActivity.access$000(SearchResultActivity.this), jp/co/yahoo/android/apps/transit/SearchResultListActivity);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d022c), val$conditionData);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
        return false;
    }

    y()
    {
        this$0 = final_searchresultactivity;
        val$conditionData = ConditionData.this;
        super();
    }
}
