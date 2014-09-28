// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            YBrowserFRUtils

static final class val.fr
    implements android.content.ClickListener
{

    final Activity val$activity;
    final String val$fr;
    final String val$url;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        YBrowserFRUtils.openURL(val$activity.getApplicationContext(), val$url, val$fr, false);
    }

    (Activity activity1, String s, String s1)
    {
        val$activity = activity1;
        val$url = s;
        val$fr = s1;
        super();
    }
}
