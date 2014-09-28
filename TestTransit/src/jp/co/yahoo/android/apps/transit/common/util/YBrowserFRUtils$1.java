// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.widget.CompoundButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            YBrowserFRUtils

static final class val.activity
    implements android.widget.heckedChangeListener
{

    final Activity val$activity;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        YBrowserFRUtils.access$000(val$activity.getApplicationContext(), "recommended", flag);
    }

    (Activity activity1)
    {
        val$activity = activity1;
        super();
    }
}
