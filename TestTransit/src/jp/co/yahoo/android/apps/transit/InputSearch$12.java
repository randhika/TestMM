// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch, OthersEditStationActivity

class this._cls0
    implements android.view.tener
{

    final InputSearch this$0;

    public void onClick(View view)
    {
        Intent intent = new Intent(InputSearch.this, jp/co/yahoo/android/apps/transit/OthersEditStationActivity);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0056));
    }

    onActivity()
    {
        this$0 = InputSearch.this;
        super();
    }
}
