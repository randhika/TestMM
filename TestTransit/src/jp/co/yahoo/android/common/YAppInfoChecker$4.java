// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoChecker, YVersionCheckListener, YAppInfoEventListener

class this._cls0
    implements android.content.DismissListener
{

    final YAppInfoChecker this$0;

    public void onDismiss(DialogInterface dialoginterface)
    {
        YAppInfoChecker.access$402(YAppInfoChecker.this, false);
        if (YAppInfoChecker.access$500(YAppInfoChecker.this) != null)
        {
            YAppInfoChecker.access$500(YAppInfoChecker.this).onDismissUpdateDialog();
        }
        if (YAppInfoChecker.access$200(YAppInfoChecker.this) != null)
        {
            YAppInfoChecker.access$200(YAppInfoChecker.this).onAppInfoEvent(4, null);
        }
    }

    ner()
    {
        this$0 = YAppInfoChecker.this;
        super();
    }
}
