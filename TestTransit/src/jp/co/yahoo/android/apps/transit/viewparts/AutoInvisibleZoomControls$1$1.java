// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.os.Handler;
import android.widget.ZoomControls;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AutoInvisibleZoomControls

class this._cls1
    implements Runnable
{

    final is._cls0 this$1;

    public void run()
    {
        AutoInvisibleZoomControls.access$000(_fld0).setVisibility(4);
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/viewparts/AutoInvisibleZoomControls$1

/* anonymous class */
    class AutoInvisibleZoomControls._cls1 extends TimerTask
    {

        final AutoInvisibleZoomControls this$0;

        public void run()
        {
            AutoInvisibleZoomControls.access$100(AutoInvisibleZoomControls.this).post(new AutoInvisibleZoomControls._cls1._cls1());
        }

            
            {
                this$0 = AutoInvisibleZoomControls.this;
                super();
            }
    }

}
