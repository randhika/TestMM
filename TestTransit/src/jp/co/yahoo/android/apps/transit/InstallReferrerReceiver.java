// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class InstallReferrerReceiver extends BroadcastReceiver
{

    private static final String PREF_REFERRER = "install_referrer";
    private static final String PREF_REFERRER_KEY = "referrer";
    private Context _AlarmContext;

    public InstallReferrerReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        _AlarmContext = context;
        String s = intent.getStringExtra("referrer");
        if (!TransitUtil.isEmpty(s))
        {
            TransitUtil.touchRD((new StringBuilder()).append(_AlarmContext.getString(0x7f0d0554)).append(s).append("/").append(_AlarmContext.getString(0x7f0d0561)).toString());
            android.content.SharedPreferences.Editor editor = context.getSharedPreferences("install_referrer", 0).edit();
            editor.putString("referrer", s);
            editor.commit();
        }
    }
}
