// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.os.Handler;
import android.widget.ZoomControls;
import java.util.TimerTask;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AutoInvisibleZoomControls

class this._cls0 extends TimerTask
{

    final AutoInvisibleZoomControls this$0;

    public void run()
    {
        AutoInvisibleZoomControls.access$100(AutoInvisibleZoomControls.this).post(new Runnable() {

            final AutoInvisibleZoomControls._cls1 this$1;

            public void run()
            {
                AutoInvisibleZoomControls.access$000(this$0).setVisibility(4);
            }

            
            {
                this$1 = AutoInvisibleZoomControls._cls1.this;
                super();
            }
        });
    }

    _cls1.this._cls1()
    {
        this$0 = AutoInvisibleZoomControls.this;
        super();
    }
}
