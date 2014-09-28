// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.iListener
{

    final DiainfoActivity this$0;

    public boolean onCanceled()
    {
        DiainfoActivity.access$402(DiainfoActivity.this, true);
        if (DiainfoActivity.access$500(DiainfoActivity.this) != null)
        {
            DiainfoActivity.access$500(DiainfoActivity.this).setVisibility(8);
        }
        DiainfoActivity.access$600(DiainfoActivity.this).setVisibility(0);
        DiainfoActivity.access$202(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onError(APIError apierror)
    {
        DiainfoActivity.access$402(DiainfoActivity.this, true);
        if (DiainfoActivity.access$500(DiainfoActivity.this) != null)
        {
            DiainfoActivity.access$500(DiainfoActivity.this).setVisibility(8);
        }
        DiainfoActivity.access$600(DiainfoActivity.this).setVisibility(0);
        DiainfoActivity.access$202(DiainfoActivity.this, true);
        setCompleted();
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        registinfo = bundle;
        DiainfoActivity.access$402(DiainfoActivity.this, true);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0067);
        if (registinfo == null)
        {
            linearlayout.setVisibility(0);
        } else
        {
            linearlayout.setVisibility(8);
        }
        setCompleted();
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    thApiListener()
    {
        this$0 = DiainfoActivity.this;
        super();
    }
}
