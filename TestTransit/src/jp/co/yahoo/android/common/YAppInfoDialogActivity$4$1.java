// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoDialogActivity, YAppInfoData

class this._cls1
    implements Runnable
{

    final sh this$1;

    public void run()
    {
        YAppInfoDialogActivity.access$000(_fld0).setClassName(getApplicationContext(), getIntent().getStringExtra("calling_activity_name"));
        YAppInfoDialogActivity.access$000(_fld0).addFlags(0x20000);
        YAppInfoDialogActivity.access$000(_fld0).putExtra("req_code", jp/co/yahoo/android/common/YAppInfoDialogActivity.hashCode());
        startActivity(YAppInfoDialogActivity.access$000(_fld0));
        finish();
    }

    l.xmlData()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/common/YAppInfoDialogActivity$4

/* anonymous class */
    class YAppInfoDialogActivity._cls4
        implements android.content.DialogInterface.OnDismissListener
    {

        final YAppInfoDialogActivity this$0;
        final YAppInfoData val$xmlData;

        public void onDismiss(DialogInterface dialoginterface)
        {
            if (xmlData.isUnderminVersion || xmlData.isForceUpdateDate)
            {
                YAppInfoDialogActivity.access$102(YAppInfoDialogActivity.this, true);
            }
            (new Handler()).postDelayed(new YAppInfoDialogActivity._cls4._cls1(), 200L);
        }

            
            {
                this$0 = final_yappinfodialogactivity;
                xmlData = YAppInfoData.this;
                super();
            }
    }

}
