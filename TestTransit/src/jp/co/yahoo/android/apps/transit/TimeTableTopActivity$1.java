// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableTopActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.ener
{

    final TimeTableTopActivity this$0;

    public boolean onCanceled()
    {
        TimeTableTopActivity.access$102(TimeTableTopActivity.this, true);
        if (TimeTableTopActivity.access$300(TimeTableTopActivity.this) != null)
        {
            TimeTableTopActivity.access$300(TimeTableTopActivity.this).setVisibility(8);
        }
        TimeTableTopActivity.access$400(TimeTableTopActivity.this).setVisibility(0);
        TimeTableTopActivity.access$502(TimeTableTopActivity.this, true);
        TimeTableTopActivity.access$200(TimeTableTopActivity.this);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        TimeTableTopActivity.access$102(TimeTableTopActivity.this, true);
        if (TimeTableTopActivity.access$300(TimeTableTopActivity.this) != null)
        {
            TimeTableTopActivity.access$300(TimeTableTopActivity.this).setVisibility(8);
        }
        TimeTableTopActivity.access$400(TimeTableTopActivity.this).setVisibility(0);
        TimeTableTopActivity.access$502(TimeTableTopActivity.this, true);
        TimeTableTopActivity.access$200(TimeTableTopActivity.this);
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        TimeTableTopActivity.access$002(TimeTableTopActivity.this, bundle);
        TimeTableTopActivity.access$102(TimeTableTopActivity.this, true);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0185);
        if (TimeTableTopActivity.access$000(TimeTableTopActivity.this) == null)
        {
            linearlayout.setVisibility(0);
        } else
        {
            linearlayout.setVisibility(8);
        }
        TimeTableTopActivity.access$200(TimeTableTopActivity.this);
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    Listener()
    {
        this$0 = TimeTableTopActivity.this;
        super();
    }
}
