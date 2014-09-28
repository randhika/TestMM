// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.common.gAndDropListener
{

    final CountdownActivity this$0;

    public void onDrop(int i, int j)
    {
        CountdownActivity.access$1500(CountdownActivity.this, i, j);
        android.content.tor tor = getSharedPreferences(getString(0x7f0d01a0), 0).edit();
        tor.putInt(getString(0x7f0d01b7), i);
        tor.putInt(getString(0x7f0d01b8), j);
        tor.commit();
    }

    agAndDropListener()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
