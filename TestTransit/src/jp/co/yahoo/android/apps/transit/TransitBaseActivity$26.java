// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.timer.api.SearchListener
{

    final TransitBaseActivity this$0;

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    public void onSuccess(String s, Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle(getString(0x7f0d0240));
        if (bundle1 == null || bundle1.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d0119), getString(0x7f0d014f));
            return;
        } else
        {
            TransitBaseActivity.access$400(TransitBaseActivity.this, bundle1);
            return;
        }
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    h.LocationSearchListener()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
