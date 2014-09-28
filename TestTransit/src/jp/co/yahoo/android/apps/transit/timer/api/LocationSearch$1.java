// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            LocationSearch, SearchStationThread

class this._cls0 extends Handler
{

    final LocationSearch this$0;

    public void handleMessage(Message message)
    {
        if (bCanceled)
        {
            if (LocationSearch.access$000(LocationSearch.this) != null && LocationSearch.access$000(LocationSearch.this).isShowing())
            {
                LocationSearch.access$000(LocationSearch.this).dismiss();
            }
        } else
        {
            bCanceled = true;
            Bundle bundle = (Bundle)message.obj;
            int i = bundle.getInt(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ce));
            String s = bundle.getString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01cf));
            String s1 = bundle.getString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01b9));
            String s2 = bundle.getString(LocationSearch.access$100(LocationSearch.this, 0x7f0d01ba));
            if (i > 0)
            {
                if (i == 0x7f0c0078)
                {
                    removeUpdateFromLocationManager();
                    Bundle bundle1 = bundle.getBundle(LocationSearch.access$100(LocationSearch.this, 0x7f0d0240));
                    if ((bundle1 == null || bundle1.size() < 1) && LocationSearch.access$200(LocationSearch.this))
                    {
                        bCanceled = false;
                        LocationSearch.access$300(LocationSearch.this).remove(LocationSearch.access$100(LocationSearch.this, 0x7f0d01a3));
                        LocationSearch.access$300(LocationSearch.this).remove(LocationSearch.access$100(LocationSearch.this, 0x7f0d01a4));
                        SearchStationThread searchstationthread = new SearchStationThread(LocationSearch.access$400(LocationSearch.this), LocationSearch.access$500(LocationSearch.this));
                        searchstationthread.setCondtion(LocationSearch.access$300(LocationSearch.this));
                        searchstationthread.start();
                        return;
                    }
                    if (LocationSearch.access$000(LocationSearch.this) != null && LocationSearch.access$000(LocationSearch.this).isShowing())
                    {
                        LocationSearch.access$000(LocationSearch.this).dismiss();
                    }
                    LocationSearch.access$600(LocationSearch.this).onSuccess(s, bundle);
                    return;
                }
                if (i == 0x7f0c0077)
                {
                    removeUpdateFromLocationManager();
                    if (LocationSearch.access$000(LocationSearch.this) != null && LocationSearch.access$000(LocationSearch.this).isShowing())
                    {
                        LocationSearch.access$000(LocationSearch.this).dismiss();
                    }
                    onError(s, s1);
                    return;
                }
                if (i == 0x7f0c0079)
                {
                    removeUpdateFromLocationManager();
                    if (LocationSearch.access$000(LocationSearch.this) != null && LocationSearch.access$000(LocationSearch.this).isShowing())
                    {
                        LocationSearch.access$000(LocationSearch.this).dismiss();
                    }
                    onTimeout(s, s1, s2);
                    return;
                }
            }
        }
    }

    cationSearchListener()
    {
        this$0 = LocationSearch.this;
        super();
    }
}
