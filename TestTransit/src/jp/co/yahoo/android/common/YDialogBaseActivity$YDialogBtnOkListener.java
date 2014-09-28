// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDialogBaseActivity

private class <init>
    implements android.view.BtnOkListener
{

    final YDialogBaseActivity this$0;

    public void onClick(View view)
    {
        if (onOkButtonClick())
        {
            finish();
        }
    }

    private I()
    {
        this$0 = YDialogBaseActivity.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
