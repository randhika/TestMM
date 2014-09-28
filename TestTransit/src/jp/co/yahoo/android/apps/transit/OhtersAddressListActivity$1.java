// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OhtersAddressListActivity

class this._cls0
    implements android.widget.ner
{

    final OhtersAddressListActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        setAddress(OhtersAddressListActivity.access$000(OhtersAddressListActivity.this)[i], OhtersAddressListActivity.access$100(OhtersAddressListActivity.this)[i], OhtersAddressListActivity.access$200(OhtersAddressListActivity.this)[i]);
        OhtersAddressListActivity.access$300(OhtersAddressListActivity.this);
    }

    ()
    {
        this$0 = OhtersAddressListActivity.this;
        super();
    }
}
