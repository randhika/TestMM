// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity, SearchResultListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.
{

    final RailmapActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Intent intent = new Intent(RailmapActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
        String s = getString(0x7f0d0232);
        NaviSearch _tmp = (NaviSearch)apibase;
        intent.putExtra(s, NaviSearch.m_routeList);
        intent.putExtra(getString(0x7f0d022c), RailmapActivity.access$2500(RailmapActivity.this));
        startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
        return false;
    }

    ivity()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
