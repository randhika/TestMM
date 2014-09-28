// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoChecker, YAppInfoEventListener

class val.action
    implements android.content.ClickListener
{

    final YAppInfoChecker this$0;
    final String val$action;
    final Activity val$activity;
    final String val$btnText;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        android.content.Editor editor = val$activity.getSharedPreferences("PREFS_CHECK_VERSION", 0).edit();
        editor.putString("canceled", YAppInfoChecker.access$300(YAppInfoChecker.this).latestAppVersion);
        editor.commit();
        if (YAppInfoChecker.access$200(YAppInfoChecker.this) != null)
        {
            Bundle bundle = new Bundle();
            bundle.putString("text", val$btnText);
            bundle.putString("action", val$action);
            if (YAppInfoChecker.access$200(YAppInfoChecker.this).onAppInfoEvent(6, bundle))
            {
                onButtonClicked(1, val$action, val$activity);
            }
            return;
        } else
        {
            onButtonClicked(1, val$action, val$activity);
            return;
        }
    }

    ner()
    {
        this$0 = final_yappinfochecker;
        val$activity = activity1;
        val$btnText = s;
        val$action = String.this;
        super();
    }
}
