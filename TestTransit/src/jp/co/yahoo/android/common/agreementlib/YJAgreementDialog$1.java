// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.common.agreementlib:
//            YJAgreementDialog

class val.listener
    implements android.view.
{

    final YJAgreementDialog this$0;
    final android.view. val$listener;

    public void onClick(View view)
    {
        if (val$listener != null)
        {
            val$listener.onClick(YJAgreementDialog.access$000(YJAgreementDialog.this));
        }
        cancel();
    }

    A()
    {
        this$0 = final_yjagreementdialog;
        val$listener = android.view..this;
        super();
    }
}
