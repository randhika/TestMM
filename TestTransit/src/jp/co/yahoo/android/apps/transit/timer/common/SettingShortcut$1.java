// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            SettingShortcut

class this._cls0
    implements android.content.ClickListener
{

    final SettingShortcut this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
    }

    ()
    {
        this$0 = SettingShortcut.this;
        super();
    }
}
