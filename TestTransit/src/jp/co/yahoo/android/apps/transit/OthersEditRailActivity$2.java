// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersEditRailActivity, DiainfoAllCategory

class this._cls0
    implements android.view.sEditRailActivity._cls2
{

    final OthersEditRailActivity this$0;

    public void onClick(View view)
    {
        Intent intent = new Intent(OthersEditRailActivity.this, jp/co/yahoo/android/apps/transit/DiainfoAllCategory);
        intent.putExtra(getString(0x7f0d01af), true);
        startActivityForResult(intent, getResources().getInteger(0x7f0c003d));
    }

    ()
    {
        this$0 = OthersEditRailActivity.this;
        super();
    }
}
