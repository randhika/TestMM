// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersEditStationActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final OthersEditStationActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            TransitUtil.login(OthersEditStationActivity.this);
        } else
        {
            showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        }
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        if (registSearch.getMethod() == "GET")
        {
            TextView textview = (TextView)findViewById(0x7f0a0134);
            if (bundle == null || bundle.size() < 1)
            {
                textview.setVisibility(0);
            } else
            {
                textview.setVisibility(8);
            }
            OthersEditStationActivity.access$100(OthersEditStationActivity.this, bundle);
            return false;
        }
        String s = registSearch.getType();
        if (s != null && s.equals(getString(0x7f0d057e)))
        {
            Toast.makeText(OthersEditStationActivity.this, getString(0x7f0d00ab), 0).show();
        }
        if (taken == null)
        {
            setResult(-1);
        }
        setRegist();
        registSearch.setMethod("GET");
        registSearch.requestAsync(true);
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    ner()
    {
        this$0 = OthersEditStationActivity.this;
        super();
    }
}
