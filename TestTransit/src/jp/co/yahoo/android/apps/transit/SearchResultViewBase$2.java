// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearchFlat;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultViewBase

class val.clickTime
    implements jp.co.yahoo.android.apps.transit.api.clickTime
{

    final SearchResultViewBase this$0;
    final String val$clickTime;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        Toast.makeText(context, context.getString(0x7f0d010b), 0).show();
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        showTimeTableDialog(SearchResultViewBase.access$000(SearchResultViewBase.this).getResults(), val$clickTime);
        return false;
    }

    ()
    {
        this$0 = final_searchresultviewbase;
        val$clickTime = String.this;
        super();
    }
}
