// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.content.Intent;
import android.content.res.Resources;
import jp.co.yahoo.android.apps.transit.SearchResultListActivity;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            WidgetSearchActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls1
{

    final WidgetSearchActivity this$0;

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
        Intent intent = new Intent(WidgetSearchActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d022c), WidgetSearchActivity.access$000(WidgetSearchActivity.this));
        startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
        return false;
    }

    ()
    {
        this$0 = WidgetSearchActivity.this;
        super();
    }
}
