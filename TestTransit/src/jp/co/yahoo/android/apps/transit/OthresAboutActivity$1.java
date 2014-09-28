// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.GetKousyou;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthresAboutActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final OthresAboutActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        OthresAboutActivity.access$100(OthresAboutActivity.this).setText(getString(0x7f0d0115));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        String s = OthresAboutActivity.access$000(OthresAboutActivity.this).kousyou();
        if (s == "" || s == null)
        {
            OthresAboutActivity.access$100(OthresAboutActivity.this).setText(getString(0x7f0d0115));
        } else
        {
            OthresAboutActivity.access$100(OthresAboutActivity.this).setText(s);
        }
        return false;
    }

    r()
    {
        this$0 = OthresAboutActivity.this;
        super();
    }
}
