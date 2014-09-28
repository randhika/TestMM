// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpRequest, YAppInfoChecker

class > extends YHttpRequest
{

    final YAppInfoChecker this$0;
    final Context val$context;

    public void onCanceled()
    {
        super.onCanceled();
        onDownloadCanceled();
    }

    public void onComplete()
    {
        onDownloadComplete();
    }

    public boolean onCompleteInThread()
    {
        if (getStatusCode() == 200)
        {
            YAppInfoChecker.access$002(YAppInfoChecker.this, getResponseString());
            parse();
            if (val$context != null)
            {
                YAppInfoChecker.access$100(YAppInfoChecker.this, val$context);
            }
        }
        return true;
    }

    public void onTimeout()
    {
        super.onTimeout();
        onDownloadTimeout();
    }

    (Context context1)
    {
        this$0 = final_yappinfochecker;
        val$context = context1;
        super(String.this);
    }
}
