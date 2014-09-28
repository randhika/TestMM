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
//            OtherCandidatesActivity, SearchResultActivity, SearchResultListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final OtherCandidatesActivity this$0;

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
        if (OtherCandidatesActivity.access$000(OtherCandidatesActivity.this).mtf != -1)
        {
            Intent intent = new Intent(OtherCandidatesActivity.this, jp/co/yahoo/android/apps/transit/SearchResultActivity);
            intent.putExtra(getString(0x7f0d0231), -1 + OtherCandidatesActivity.access$000(OtherCandidatesActivity.this).mtf);
            intent.putExtra(getString(0x7f0d0232), navisearchdata);
            intent.putExtra(getString(0x7f0d022c), OtherCandidatesActivity.access$000(OtherCandidatesActivity.this));
            startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
        } else
        {
            Intent intent1 = new Intent(OtherCandidatesActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
            intent1.putExtra(getString(0x7f0d0232), navisearchdata);
            intent1.putExtra(getString(0x7f0d022c), OtherCandidatesActivity.access$000(OtherCandidatesActivity.this));
            startActivityForResult(intent1, getResources().getInteger(0x7f0c0059));
        }
        return false;
    }

    ()
    {
        this$0 = OtherCandidatesActivity.this;
        super();
    }
}
