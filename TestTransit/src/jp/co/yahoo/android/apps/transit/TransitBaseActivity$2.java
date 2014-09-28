// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.settings
    implements jp.co.yahoo.android.common.agreementlib.greementListener
{

    final TransitBaseActivity this$0;
    final SharedPreferences val$settings;

    public void onAgree()
    {
        init();
        android.content.or or = val$settings.edit();
        or.putBoolean(getString(0x7f0d039c), false);
        or.commit();
    }

    public void onDisagree()
    {
        android.content.or or = val$settings.edit();
        or.putBoolean(getString(0x7f0d039c), true);
        or.commit();
        finish();
    }

    er.OnAggreementListener()
    {
        this$0 = final_transitbaseactivity;
        val$settings = SharedPreferences.this;
        super();
    }
}
