// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api..LocationSearchlListener
{

    final InputSearch this$0;

    public void onError(String s, String s1)
    {
        if (!TransitUtil.isEmpty(s1))
        {
            showErrorMessageDialog(s1, getString(0x7f0d014f));
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        if (!TransitUtil.isEmpty(bundle.getString(getString(0x7f0d01a2))))
        {
            InputSearch.access$1902(InputSearch.this, new StationData());
            InputSearch.access$1900(InputSearch.this).setnNaviType(128);
            InputSearch.access$1900(InputSearch.this).setLat(bundle.getString(getString(0x7f0d01a3)));
            InputSearch.access$1900(InputSearch.this).setLon(bundle.getString(getString(0x7f0d01a4)));
            InputSearch.access$1900(InputSearch.this).setAddress(bundle.getString(getString(0x7f0d01a2)));
            InputSearch.access$1900(InputSearch.this).setName(InputSearch.access$1900(InputSearch.this).getAddress());
        }
        Bundle bundle1 = bundle.getBundle(getString(0x7f0d0240));
        if (bundle1 != null && bundle1.size() > 0)
        {
            InputSearch.access$2002(InputSearch.this, bundle1);
        }
        if (InputSearch.access$1900(InputSearch.this) != null && InputSearch.access$2000(InputSearch.this) != null && InputSearch.access$2000(InputSearch.this).size() > 0)
        {
            InputSearch.access$2100(InputSearch.this, InputSearch.access$2000(InputSearch.this), InputSearch.access$1900(InputSearch.this));
        }
    }

    public void onTimeout(String s, String s1)
    {
        if (!TransitUtil.isEmpty(s1))
        {
            showErrorMessageDialog(s1, getString(0x7f0d014f));
        }
    }

    onData()
    {
        this$0 = InputSearch.this;
        super();
    }
}
