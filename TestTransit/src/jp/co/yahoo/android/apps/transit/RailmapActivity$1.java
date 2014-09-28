// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailmapActivity, InputSearch

class this._cls0
    implements android.view.er
{

    final RailmapActivity this$0;

    public void onClick(View view)
    {
        Intent intent = new Intent(RailmapActivity.this, jp/co/yahoo/android/apps/transit/InputSearch);
        intent.putExtra(getString(0x7f0d01c3), true);
        intent.putExtra(getString(0x7f0d01bc), true);
        intent.putExtra(getString(0x7f0d022d), getString(0x7f0d0481));
        startActivityForResult(intent, getResources().getInteger(0x7f0c0049));
    }

    ()
    {
        this$0 = RailmapActivity.this;
        super();
    }
}
