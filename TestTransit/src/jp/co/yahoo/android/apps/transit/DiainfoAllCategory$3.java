// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoAllCategory

class this._cls0
    implements android.view.iainfoAllCategory._cls3
{

    final DiainfoAllCategory this$0;

    public void onClick(View view)
    {
        launchDiainfoRailList((DiainfoData)view.getTag());
    }

    ()
    {
        this$0 = DiainfoAllCategory.this;
        super();
    }
}
