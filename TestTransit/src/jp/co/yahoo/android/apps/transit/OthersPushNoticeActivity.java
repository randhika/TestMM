// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class OthersPushNoticeActivity extends TransitBaseActivity
{

    public OthersPushNoticeActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002d);
        setTitle(getString(0x7f0d03b7));
        dispAd(this, "2080342782", "pv");
    }

    public void openSoftware(View view)
    {
        TransitUtil.openURL(this, getString(0x7f0d0018));
    }
}
