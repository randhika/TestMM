// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YSimpleHttpClient, YHBGRd

public class YHBGRefReceiver extends BroadcastReceiver
{

    public YHBGRefReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getStringExtra("referrer");
        Log.i("[REFERRER]", s);
        if (s.startsWith("hbg_"))
        {
            YSimpleHttpClient.setUserAgent("Mozilla/5.0 (Linux; U; Android 4.0.4; ja-jp; L-01E Build/IMM76L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
            YHBGRd.sendAsync(context, new String[] {
                "referrer", s
            });
        }
    }
}
