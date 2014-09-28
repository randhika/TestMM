// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.widget.Button;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            VolumeDialog

class this._cls0
    implements jp.co.yahoo.android.apps.transit.timer.common.Listener
{

    final VolumeDialog this$0;

    public void onError()
    {
        VolumeDialog.access$002(VolumeDialog.this, false);
        VolumeDialog.access$100(VolumeDialog.this).setText(context.getString(0x7f0d04bc));
    }

    public void onStop()
    {
        VolumeDialog.access$002(VolumeDialog.this, false);
        VolumeDialog.access$100(VolumeDialog.this).setText(context.getString(0x7f0d04bc));
    }

    tener()
    {
        this$0 = VolumeDialog.this;
        super();
    }
}
