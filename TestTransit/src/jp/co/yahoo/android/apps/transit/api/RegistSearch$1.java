// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.DialogInterface;
import jp.co.yahoo.android.common.login.YLoginServiceManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            RegistSearch

class this._cls0
    implements android.content..OnCancelListener
{

    final RegistSearch this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        RegistSearch.access$000(RegistSearch.this).cancelYJDN();
        RegistSearch.access$200(RegistSearch.this).onLoginApiCanceled(method, RegistSearch.access$101(RegistSearch.this));
    }

    oginApiListener()
    {
        this$0 = RegistSearch.this;
        super();
    }
}
