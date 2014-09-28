// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.app.Activity;
import android.content.DialogInterface;
import android.location.LocationManager;
import java.util.Timer;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            LocationSearch

class this._cls0
    implements android.content.nCancelListener
{

    final LocationSearch this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        if (LocationSearch.access$700(LocationSearch.this) != null)
        {
            LocationSearch.access$700(LocationSearch.this).cancel();
        }
        thread = null;
        LocationSearch.access$800(LocationSearch.this).removeUpdates(LocationSearch.this);
        bCanceled = true;
        if (LocationSearch.access$900(LocationSearch.this))
        {
            ((Activity)LocationSearch.access$400(LocationSearch.this)).finish();
        }
    }

    A()
    {
        this$0 = LocationSearch.this;
        super();
    }
}
