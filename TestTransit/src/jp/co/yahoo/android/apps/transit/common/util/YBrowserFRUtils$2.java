// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            YBrowserFRUtils

static final class val.activity
    implements android.content.ClickListener
{

    final Activity val$activity;
    final String val$pkg;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=jp.co.yahoo.android.ybrowser&referrer=%s", new Object[] {
            val$pkg.substring(1 + val$pkg.lastIndexOf("."))
        })));
        intent.setFlags(0x10000000);
        YBrowserFRUtils.access$100(val$activity.getApplicationContext(), intent);
    }

    (String s, Activity activity1)
    {
        val$pkg = s;
        val$activity = activity1;
        super();
    }
}
