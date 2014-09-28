// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginActivity, YLoginServiceManager

class this._cls0
    implements android.content.nClickListener
{

    final YLoginActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        YLoginActivity.access$000(YLoginActivity.this).cancelLogin();
    }

    ger()
    {
        this$0 = YLoginActivity.this;
        super();
    }
}
