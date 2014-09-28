// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;


// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.common.ManagerListener
{

    final TransitBaseActivity this$0;

    public void onCanceled()
    {
        closeProgressDialog();
        onLoginResult();
    }

    public void onError(int i, String s, String s1, String s2)
    {
        closeProgressDialog();
        onLoginResult();
    }

    public void onSuccess(String s, String s1)
    {
        closeProgressDialog();
        onLoginResult();
    }

    er.PushManagerListener()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
