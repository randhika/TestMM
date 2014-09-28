// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements android.view.stener
{

    final InputSearch this$0;

    public void onClick(View view)
    {
        InputSearch.access$000(InputSearch.this);
        InputSearch.access$100(InputSearch.this, (StationData)view.getTag());
        if ((InputSearch.access$200(InputSearch.this).getType() == 1 || InputSearch.access$200(InputSearch.this).getType() == 2) && TransitUtil.isEmpty(InputSearch.access$200(InputSearch.this).getId()))
        {
            InputSearch.access$300(InputSearch.this, InputSearch.access$200(InputSearch.this).getName());
            return;
        } else
        {
            InputSearch.access$400(InputSearch.this);
            return;
        }
    }

    ansitUtil()
    {
        this$0 = InputSearch.this;
        super();
    }
}
