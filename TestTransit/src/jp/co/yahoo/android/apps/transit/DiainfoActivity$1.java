// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity

class this._cls0
    implements android.view.er
{

    final DiainfoActivity this$0;

    public void onClick(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0423)).append("/").append(getString(0x7f0d03f4)).toString());
        TransitUtil.openURL(DiainfoActivity.this, getString(0x7f0d056c));
    }

    tUtil()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}
