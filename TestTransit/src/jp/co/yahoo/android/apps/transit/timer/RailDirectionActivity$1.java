// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            RailDirectionActivity

class this._cls0
    implements jp.co.yahoo.android.yolp.common.vity._cls1
{

    final RailDirectionActivity this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        Bundle bundle = (Bundle)apibase.getResult();
        if (bundle == null || bundle.size() < 1)
        {
            RailDirectionActivity.access$000(RailDirectionActivity.this);
        } else
        {
            RailDirectionActivity.access$102(RailDirectionActivity.this, (StationData)bundle.getSerializable("0"));
            RailDirectionActivity.access$100(RailDirectionActivity.this).setSettingType(RailDirectionActivity.access$200(RailDirectionActivity.this));
            RailDirectionActivity.access$302(RailDirectionActivity.this, RailDirectionActivity.access$100(RailDirectionActivity.this).getRailDirection());
            RailDirectionActivity.access$400(RailDirectionActivity.this, RailDirectionActivity.access$300(RailDirectionActivity.this));
        }
        return false;
    }

    ()
    {
        this$0 = RailDirectionActivity.this;
        super();
    }
}
