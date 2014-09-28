// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersEditRailActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.er
{

    final OthersEditRailActivity this$0;

    public boolean onCanceled()
    {
        if (OthersEditRailActivity.access$400(OthersEditRailActivity.this))
        {
            closeProgressDialog();
        }
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (OthersEditRailActivity.access$400(OthersEditRailActivity.this))
        {
            closeProgressDialog();
        }
        if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            TransitUtil.login(OthersEditRailActivity.this);
        } else
        {
            showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        }
        return false;
    }

    public boolean onSuccess(Bundle bundle)
    {
        if (OthersEditRailActivity.access$100(OthersEditRailActivity.this).getMethod() == "GET")
        {
            TextView textview = (TextView)findViewById(0x7f0a0134);
            if (bundle == null || bundle.size() < 1)
            {
                textview.setVisibility(0);
            } else
            {
                textview.setVisibility(8);
            }
            OthersEditRailActivity.access$200(OthersEditRailActivity.this, bundle);
        } else
        if (OthersEditRailActivity.access$300(OthersEditRailActivity.this) == null)
        {
            if (OthersEditRailActivity.access$400(OthersEditRailActivity.this))
            {
                closeProgressDialog();
                return false;
            }
        } else
        if (OthersEditRailActivity.access$400(OthersEditRailActivity.this))
        {
            OthersEditRailActivity.access$500(OthersEditRailActivity.this);
            return false;
        } else
        {
            OthersEditRailActivity.access$600(OthersEditRailActivity.this);
            OthersEditRailActivity.access$100(OthersEditRailActivity.this).setSearchType(getString(0x7f0d01d5));
            OthersEditRailActivity.access$100(OthersEditRailActivity.this).setMethod("GET");
            OthersEditRailActivity.access$100(OthersEditRailActivity.this).requestAsync(true);
            return false;
        }
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    stener()
    {
        this$0 = OthersEditRailActivity.this;
        super();
    }
}
