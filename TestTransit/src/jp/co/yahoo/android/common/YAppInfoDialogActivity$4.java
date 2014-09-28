// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoDialogActivity, YAppInfoData

class val.xmlData
    implements android.content.Listener
{

    final YAppInfoDialogActivity this$0;
    final YAppInfoData val$xmlData;

    public void onDismiss(DialogInterface dialoginterface)
    {
        if (val$xmlData.isUnderminVersion || val$xmlData.isForceUpdateDate)
        {
            YAppInfoDialogActivity.access$102(YAppInfoDialogActivity.this, true);
        }
        (new Handler()).postDelayed(new Runnable() {

            final YAppInfoDialogActivity._cls4 this$1;

            public void run()
            {
                YAppInfoDialogActivity.access$000(this$0).setClassName(getApplicationContext(), getIntent().getStringExtra("calling_activity_name"));
                YAppInfoDialogActivity.access$000(this$0).addFlags(0x20000);
                YAppInfoDialogActivity.access$000(this$0).putExtra("req_code", jp/co/yahoo/android/common/YAppInfoDialogActivity.hashCode());
                startActivity(YAppInfoDialogActivity.access$000(this$0));
                finish();
            }

            
            {
                this$1 = YAppInfoDialogActivity._cls4.this;
                super();
            }
        }, 200L);
    }

    _cls1.this._cls1()
    {
        this$0 = final_yappinfodialogactivity;
        val$xmlData = YAppInfoData.this;
        super();
    }
}
