// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.DialogInterface;
import com.android.volley.RequestQueue;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            ApiBase

class this._cls0
    implements android.content.rface.OnCancelListener
{

    final ApiBase this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        ApiBase.access$002(ApiBase.this, true);
        if (queue != null)
        {
            queue.stop();
        }
        if (listener != null)
        {
            listener.onCanceled();
        }
    }

    iListener()
    {
        this$0 = ApiBase.this;
        super();
    }
}
