// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfo

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.thApiListener
{

    final StationInfo this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        Toast.makeText(StationInfo.this, getString(0x7f0d00ab), 0).show();
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    ror()
    {
        this$0 = StationInfo.this;
        super();
    }
}
