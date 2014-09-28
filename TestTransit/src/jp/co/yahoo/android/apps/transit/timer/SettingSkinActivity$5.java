// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingSkinActivity

class val.objItem
    implements android.content.kListener
{

    final SettingSkinActivity this$0;
    final SkinMetaData val$objItem;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.dismiss();
        SettingSkinActivity.access$700(SettingSkinActivity.this, val$objItem);
    }

    ()
    {
        this$0 = final_settingskinactivity;
        val$objItem = SkinMetaData.this;
        super();
    }
}
