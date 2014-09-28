// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.timer.common.RingAlerm;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            VolumeDialog

class this._cls0
    implements android.content..OnClickListener
{

    final VolumeDialog this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (VolumeDialog.access$700(VolumeDialog.this) != null)
        {
            VolumeDialog.access$500(VolumeDialog.this).stopAlerm();
            VolumeDialog.access$700(VolumeDialog.this).onOk(VolumeDialog.access$200(VolumeDialog.this));
        }
    }

    alogListener()
    {
        this$0 = VolumeDialog.this;
        super();
    }
}
