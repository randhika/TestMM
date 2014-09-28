// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import java.io.File;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingSkinActivity

class val.objItem
    implements android.content.kListener
{

    final SettingSkinActivity this$0;
    final SkinMetaData val$objItem;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        try
        {
            CountdownUtil.delete(new File((new StringBuilder()).append(val$objItem.sPath).append("/").append(getString(0x7f0d04ea)).toString()));
            val$objItem.isDownloaded = false;
            if (val$objItem.isSetting)
            {
                val$objItem.isSetting = false;
                SettingSkinActivity.access$300(SettingSkinActivity.this).updateSetting(getString(0x7f0d04f6));
            }
            SettingSkinActivity.access$300(SettingSkinActivity.this).updateSkin(val$objItem);
        }
        catch (Exception exception)
        {
            showSimpleErrorMessageDialog(getString(0x7f0d04eb));
            exception.printStackTrace();
        }
        SettingSkinActivity.access$400(SettingSkinActivity.this);
    }

    ()
    {
        this$0 = final_settingskinactivity;
        val$objItem = SkinMetaData.this;
        super();
    }
}
