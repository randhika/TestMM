// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import com.android.volley.VolleyError;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

class this._cls0
    implements com.android.volley.rrorListener
{

    final ApiBase this$0;

    public void onErrorResponse(VolleyError volleyerror)
    {
        if (!ApiBase.access$000(ApiBase.this))
        {
            onError(volleyerror);
        }
    }

    ()
    {
        this$0 = ApiBase.this;
        super();
    }
}
