// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.YolpApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            StationListActivity

class this._cls0
    implements jp.co.yahoo.android.yolp.common.vity._cls1
{

    final StationListActivity this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        StationListActivity.access$002(StationListActivity.this, (Bundle)apibase.getResult());
        if (StationListActivity.access$000(StationListActivity.this) != null)
        {
            StationListActivity.access$100(StationListActivity.this);
        } else
        if (((YolpApiBase)apibase).getError() != null)
        {
            showErrorMessageDialog(getString(0x7f0d010a), getString(0x7f0d014f));
        } else
        {
            showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
        }
        return false;
    }

    ()
    {
        this$0 = StationListActivity.this;
        super();
    }
}
