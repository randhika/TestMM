// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

class this._cls0
    implements com.android.volley.istener
{

    final ApiBase this$0;

    public volatile void onResponse(Object obj)
    {
        onResponse((JSONObject)obj);
    }

    public void onResponse(JSONObject jsonobject)
    {
        if (!ApiBase.access$000(ApiBase.this))
        {
            onSuccess(jsonobject);
        }
    }

    ()
    {
        this$0 = ApiBase.this;
        super();
    }
}
