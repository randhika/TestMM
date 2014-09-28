// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.content.ckListener
{

    final CountdownActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        CountdownActivity.access$1800(CountdownActivity.this).delAlarm(CountdownActivity.access$2000(CountdownActivity.this));
        CountdownActivity.access$2002(CountdownActivity.this, null);
        setAlermOff();
        Toast.makeText(CountdownActivity.this, getString(0x7f0d0030), 0).show();
        CountdownActivity.access$1900(CountdownActivity.this);
    }

    A()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
