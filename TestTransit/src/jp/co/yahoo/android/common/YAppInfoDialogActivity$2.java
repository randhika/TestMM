// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;
import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoDialogActivity, YAppInfoData

class val.xmlData
    implements android.content.istener
{

    final YAppInfoDialogActivity this$0;
    final YAppInfoData val$xmlData;

    public void onCancel(DialogInterface dialoginterface)
    {
        android.content.Activity._cls2 _lcls2 = getSharedPreferences("PREFS_CHECK_VERSION", 0).edit();
        _lcls2.putString("canceled_version", val$xmlData.latestAppVersion);
        _lcls2.commit();
    }

    _cls9()
    {
        this$0 = final_yappinfodialogactivity;
        val$xmlData = YAppInfoData.this;
        super();
    }
}
