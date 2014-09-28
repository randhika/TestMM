// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.Activity;
import android.content.DialogInterface;
import android.location.LocationManager;
import java.util.Timer;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            LocationSearch

class this._cls0
    implements android.content.nCancelListener
{

    final LocationSearch this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        if (LocationSearch.access$000(LocationSearch.this) != null)
        {
            LocationSearch.access$000(LocationSearch.this).cancel();
        }
        thread = null;
        LocationSearch.access$100(LocationSearch.this).removeUpdates(LocationSearch.this);
        bCanceled = true;
        if (LocationSearch.access$200(LocationSearch.this))
        {
            ((Activity)LocationSearch.access$300(LocationSearch.this)).finish();
        }
    }

    ()
    {
        this$0 = LocationSearch.this;
        super();
    }
}
