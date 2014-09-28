// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            RailDirection

class this._cls0
    implements android.view.ener
{

    final RailDirection this$0;

    public void onClick(View view)
    {
        RailDirection.access$102(RailDirection.this, (StationData)view.getTag(0x7f0d023e));
        RailDirection.access$202(RailDirection.this, (RailDirectionData)view.getTag(0x7f0d01b3));
        RailDirection.access$300(RailDirection.this);
    }

    ectionData()
    {
        this$0 = RailDirection.this;
        super();
    }
}
