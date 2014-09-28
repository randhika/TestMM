// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity, DiainfoDetailActivity

class this._cls0
    implements android.view.er
{

    final DiainfoActivity this$0;

    public void onClick(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0416)).append("/").append(getString(0x7f0d040d)).toString());
        Intent intent = new Intent(DiainfoActivity.this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
        DiainfoData diainfodata = (DiainfoData)view.getTag();
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01d9), diainfodata.getRailCode());
        bundle.putString(getString(0x7f0d01a8), diainfodata.getCpId());
        bundle.putString(getString(0x7f0d01dc), diainfodata.getRailRangeCode());
        intent.putExtra(getString(0x7f0d022c), bundle);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0040));
    }

    ta()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}
