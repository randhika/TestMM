// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch, StationSearch, RevGeocoder

class this._cls0 extends Handler
{

    final LocationSearch this$0;

    public void handleMessage(Message message)
    {
        if (LocationSearch.access$400(LocationSearch.this) != null && LocationSearch.access$400(LocationSearch.this).isShowing())
        {
            LocationSearch.access$400(LocationSearch.this).dismiss();
        }
        if (LocationSearch.access$500(LocationSearch.this) != null)
        {
            LocationSearch.access$500(LocationSearch.this).stopRequest();
        }
        if (LocationSearch.access$600(LocationSearch.this) != null)
        {
            LocationSearch.access$600(LocationSearch.this).stopRequest();
        }
        onTimeout(LocationSearch.access$700(LocationSearch.this, 0x7f0d01d0), LocationSearch.access$700(LocationSearch.this, 0x7f0d010c), LocationSearch.access$700(LocationSearch.this, 0x7f0d01ba));
    }

    ()
    {
        this$0 = LocationSearch.this;
        super();
    }
}
