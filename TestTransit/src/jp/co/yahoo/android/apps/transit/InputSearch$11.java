// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements android.view.tener
{

    final InputSearch this$0;

    public void onClick(View view)
    {
        TransitUtil.login(InputSearch.this);
    }

    nsitUtil()
    {
        this$0 = InputSearch.this;
        super();
    }
}
