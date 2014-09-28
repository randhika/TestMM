// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoBaseActivity, OthersEditRailActivity

class this._cls0
    implements android.content.kListener
{

    final DiainfoBaseActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent(DiainfoBaseActivity.this, jp/co/yahoo/android/apps/transit/OthersEditRailActivity);
        intent.putExtra(getString(0x7f0d01d2), true);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0055));
    }

    ()
    {
        this$0 = DiainfoBaseActivity.this;
        super();
    }
}
