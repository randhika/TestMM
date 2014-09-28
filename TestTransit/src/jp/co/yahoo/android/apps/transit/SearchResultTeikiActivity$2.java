// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultTeikiActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final SearchResultTeikiActivity this$0;

    public boolean onCanceled()
    {
        SearchResultTeikiActivity.access$400(SearchResultTeikiActivity.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        String s = apierror.getMessage();
        if (StringUtil.isEmpty(s))
        {
            s = getString(0x7f0d0108);
        }
        showSimpleErrorMessageDialog(s, new android.content.DialogInterface.OnClickListener() {

            final SearchResultTeikiActivity._cls2 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                SearchResultTeikiActivity.access$400(this$0);
            }

            
            {
                this$1 = SearchResultTeikiActivity._cls2.this;
                super();
            }
        }, new android.content.DialogInterface.OnCancelListener() {

            final SearchResultTeikiActivity._cls2 this$1;

            public void onCancel(DialogInterface dialoginterface)
            {
                SearchResultTeikiActivity.access$400(this$0);
            }

            
            {
                this$1 = SearchResultTeikiActivity._cls2.this;
                super();
            }
        });
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        NaviSearchData navisearchdata = NaviSearch.m_routeList;
        if (!TransitUtil.isEmpty(SearchResultTeikiActivity.access$000(SearchResultTeikiActivity.this).passtype)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (SearchResultTeikiActivity.access$000(SearchResultTeikiActivity.this).passtype.equals(getString(0x7f0d057a)))
        {
            SearchResultTeikiActivity.access$102(SearchResultTeikiActivity.this, (jp.co.yahoo.android.apps.transit.api.data.)navisearchdata.routes.get(0));
        } else
        {
            if (!SearchResultTeikiActivity.access$000(SearchResultTeikiActivity.this).passtype.equals(getString(0x7f0d057c)))
            {
                continue; /* Loop/switch isn't completed */
            }
            SearchResultTeikiActivity.access$202(SearchResultTeikiActivity.this, (jp.co.yahoo.android.apps.transit.api.data.)navisearchdata.routes.get(0));
        }
_L4:
        showTeiki((jp.co.yahoo.android.apps.transit.api.data.)navisearchdata.routes.get(0));
        return false;
        if (!SearchResultTeikiActivity.access$000(SearchResultTeikiActivity.this).passtype.equals(getString(0x7f0d057b))) goto _L1; else goto _L3
_L3:
        SearchResultTeikiActivity.access$302(SearchResultTeikiActivity.this, (jp.co.yahoo.android.apps.transit.api.data.)navisearchdata.routes.get(0));
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    _cls2.this._cls1()
    {
        this$0 = SearchResultTeikiActivity.this;
        super();
    }
}
