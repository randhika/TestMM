// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.timer.common.RingAlerm;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            VolumeDialog

class this._cls0
    implements android.content..OnCancelListener
{

    final VolumeDialog this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        VolumeDialog.access$500(VolumeDialog.this).stopAlerm();
    }

    ()
    {
        this$0 = VolumeDialog.this;
        super();
    }
}
