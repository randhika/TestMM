// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoDetailActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final DiainfoDetailActivity this$0;

    public boolean onCanceled()
    {
        showDiainfo();
        if (DiainfoDetailActivity.access$000(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$002(DiainfoDetailActivity.this, false);
        } else
        if (DiainfoDetailActivity.access$100(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$102(DiainfoDetailActivity.this, false);
            return false;
        }
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        showDiainfo();
        if (DiainfoDetailActivity.access$000(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$002(DiainfoDetailActivity.this, false);
        } else
        {
            if (DiainfoDetailActivity.access$100(DiainfoDetailActivity.this))
            {
                DiainfoDetailActivity.access$102(DiainfoDetailActivity.this, false);
                return false;
            }
            if (DiainfoDetailActivity.access$200(DiainfoDetailActivity.this))
            {
                DiainfoDetailActivity.access$202(DiainfoDetailActivity.this, false);
                return false;
            }
        }
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = ((DiainfoSearch)apibase).getResult();
        if (bundle != null) goto _L2; else goto _L1
_L1:
        showErrorMessageDialog(getString(0x7f0d0133), getString(0x7f0d014f));
        if (!DiainfoDetailActivity.access$000(DiainfoDetailActivity.this)) goto _L4; else goto _L3
_L3:
        DiainfoDetailActivity.access$002(DiainfoDetailActivity.this, false);
_L6:
        return false;
_L4:
        if (DiainfoDetailActivity.access$100(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$102(DiainfoDetailActivity.this, false);
            return false;
        }
        if (DiainfoDetailActivity.access$200(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$202(DiainfoDetailActivity.this, false);
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        diainfo = (DiainfoData)bundle.getSerializable("0");
        showDiainfo();
        if (DiainfoDetailActivity.access$000(DiainfoDetailActivity.this))
        {
            postRegist(diainfo);
            DiainfoDetailActivity.access$002(DiainfoDetailActivity.this, false);
            return false;
        }
        if (DiainfoDetailActivity.access$100(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$300(DiainfoDetailActivity.this, diainfo);
            DiainfoDetailActivity.access$102(DiainfoDetailActivity.this, false);
            return false;
        }
        if (DiainfoDetailActivity.access$200(DiainfoDetailActivity.this))
        {
            DiainfoDetailActivity.access$400(DiainfoDetailActivity.this, diainfo);
            DiainfoDetailActivity.access$202(DiainfoDetailActivity.this, false);
            return false;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    ()
    {
        this$0 = DiainfoDetailActivity.this;
        super();
    }
}
