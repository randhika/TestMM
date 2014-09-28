// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoBaseActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.tener
{

    final DiainfoBaseActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (isRegistEdit && apierror != null && apierror.getCode() != null && apierror.getCode().equals("40001"))
        {
            showRegistOverDialog();
            return false;
        } else
        {
            showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
            return false;
        }
    }

    public boolean onSuccess(Bundle bundle)
    {
        if (isRegist)
        {
            showRegistComplete();
            return false;
        } else
        {
            Toast.makeText(DiainfoBaseActivity.this, getString(0x7f0d00aa), 0).show();
            return false;
        }
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    iListener()
    {
        this$0 = DiainfoBaseActivity.this;
        super();
    }
}
