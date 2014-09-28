// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfo

class val.url
    implements android.view.stener
{

    final StationInfo this$0;
    final String val$url;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d03f5));
        String s = getString(0x7f0d0059);
        if (TransitUtil.isAppInstalled(StationInfo.this, s))
        {
            Intent intent = new Intent();
            intent.setPackage(s);
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(val$url));
            try
            {
                startActivity(intent);
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        } else
        {
            StationInfo.access$400(StationInfo.this).show();
            return;
        }
    }

    ansitUtil()
    {
        this$0 = final_stationinfo;
        val$url = String.this;
        super();
    }
}
