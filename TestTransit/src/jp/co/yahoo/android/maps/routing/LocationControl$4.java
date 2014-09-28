// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            LocationControl

class this._cls0 extends Handler
{

    final LocationControl this$0;

    public void handleMessage(Message message)
    {
        if (LocationControl.access$11(LocationControl.this) == null)
        {
            return;
        }
        if (LocationControl.access$10(LocationControl.this) != null)
        {
            if (LocationControl.access$12(LocationControl.this))
            {
                LocationControl.access$10(LocationControl.this).onYLocationError(LocationControl.this);
            } else
            if (LocationControl.access$13(LocationControl.this) == 0)
            {
                LocationControl.access$14(LocationControl.this, 1);
            } else
            {
                LocationControl.access$10(LocationControl.this).onYLocationError(LocationControl.this);
            }
        }
        sendEmptyMessageDelayed(0, LocationControl.access$7(LocationControl.this));
    }

    cationControlListener()
    {
        this$0 = LocationControl.this;
        super();
    }
}
