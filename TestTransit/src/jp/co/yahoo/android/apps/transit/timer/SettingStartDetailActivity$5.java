// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class this._cls0
    implements android.content.ClickListener
{

    final SettingStartDetailActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i, boolean flag)
    {
        SettingStartDetailActivity.access$500(SettingStartDetailActivity.this)[i] = flag;
    }

    I()
    {
        this$0 = SettingStartDetailActivity.this;
        super();
    }
}
