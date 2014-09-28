// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.YjpnShortUrl;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final SearchResultBaseActivity this$0;

    public boolean onCanceled()
    {
        String s = getString(0x7f0d0109);
        Toast.makeText(SearchResultBaseActivity.this, s, 1).show();
        SearchResultBaseActivity.access$300(SearchResultBaseActivity.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        String s = apierror.getMessage();
        if (TextUtils.isEmpty(s))
        {
            s = getString(0x7f0d0109);
        }
        Toast.makeText(SearchResultBaseActivity.this, s, 1).show();
        SearchResultBaseActivity.access$300(SearchResultBaseActivity.this);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        String s = SearchResultBaseActivity.access$100(SearchResultBaseActivity.this).getShortUrl();
        if (!TextUtils.isEmpty(s))
        {
            if (SearchResultBaseActivity.access$200(SearchResultBaseActivity.this) == null)
            {
                SearchResultBaseActivity.access$202(SearchResultBaseActivity.this, new SparseArray());
            }
            SearchResultBaseActivity.access$200(SearchResultBaseActivity.this).put(result_id, s);
        }
        SearchResultBaseActivity.access$300(SearchResultBaseActivity.this);
        return false;
    }

    A()
    {
        this$0 = SearchResultBaseActivity.this;
        super();
    }
}
