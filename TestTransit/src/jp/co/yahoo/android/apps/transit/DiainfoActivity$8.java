// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity, DiainfoAllCategory

class this._cls0
    implements android.view.er
{

    final DiainfoActivity this$0;

    public void onClick(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0416)).append("/").append(getString(0x7f0d03c5)).toString());
        Intent intent = new Intent(DiainfoActivity.this, jp/co/yahoo/android/apps/transit/DiainfoAllCategory);
        startActivityForResult(intent, getResources().getInteger(0x7f0c003d));
    }

    ()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}
