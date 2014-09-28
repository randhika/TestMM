// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.common.RingAlerm;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            VolumeDialog

class this._cls0
    implements android.widget.arChangeListener
{

    final VolumeDialog this$0;

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        VolumeDialog.access$202(VolumeDialog.this, i * 5);
        VolumeDialog.access$300(VolumeDialog.this).setText((new StringBuilder()).append(Integer.toString(VolumeDialog.access$200(VolumeDialog.this))).append(context.getString(0x7f0d02af)).toString());
        VolumeDialog.access$400(VolumeDialog.this).setAlermVolume(VolumeDialog.access$200(VolumeDialog.this));
        if (VolumeDialog.access$000(VolumeDialog.this))
        {
            VolumeDialog.access$500(VolumeDialog.this).setVolume(VolumeDialog.access$200(VolumeDialog.this));
        }
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
    }

    ()
    {
        this$0 = VolumeDialog.this;
        super();
    }
}
