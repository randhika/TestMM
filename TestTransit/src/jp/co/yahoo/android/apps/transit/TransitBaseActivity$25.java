// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import jp.co.yahoo.android.common.YAppInfoChecker;
import jp.co.yahoo.android.common.YVersionCheckListener;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.activity
    implements YVersionCheckListener
{

    final TransitBaseActivity this$0;
    final Activity val$activity;

    public void onAppInfoDownloadCompleted()
    {
        YAppInfoChecker yappinfochecker = YAppInfoChecker.getInstanse();
        if (!yappinfochecker.isLatestVersion(getApplicationContext()))
        {
            yappinfochecker.showUpdateDialog(val$activity);
        }
    }

    public void onAppInfoDownloadTimeout()
    {
    }

    public void onDismissUpdateDialog()
    {
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$activity = Activity.this;
        super();
    }
}
