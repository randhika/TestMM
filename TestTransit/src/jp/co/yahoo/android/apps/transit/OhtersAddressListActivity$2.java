// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OhtersAddressListActivity, OthersActivity

class this._cls0
    implements android.content.ner
{

    final OhtersAddressListActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent(OhtersAddressListActivity.this, jp/co/yahoo/android/apps/transit/OthersActivity);
        startActivityForResult(intent, OhtersAddressListActivity.access$400(OhtersAddressListActivity.this).getInteger(0x7f0c004a));
        finish();
    }

    ()
    {
        this$0 = OhtersAddressListActivity.this;
        super();
    }
}
