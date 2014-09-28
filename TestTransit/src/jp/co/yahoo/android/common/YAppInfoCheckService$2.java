// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;


// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpRequest, YAppInfoCheckService

class this._cls0 extends YHttpRequest
{

    final YAppInfoCheckService this$0;

    public void onCanceled()
    {
        super.onCanceled();
    }

    public void onComplete()
    {
        YAppInfoCheckService.access$200(YAppInfoCheckService.this);
    }

    public boolean onCompleteInThread()
    {
        if (getStatusCode() == 200)
        {
            String s = getResponseString();
            if (s != null)
            {
                YAppInfoCheckService.access$100(YAppInfoCheckService.this, s);
                return true;
            }
        }
        return false;
    }

    public void onTimeout()
    {
        super.onTimeout();
    }

    (String s)
    {
        this$0 = YAppInfoCheckService.this;
        super(s);
    }
}
