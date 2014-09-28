// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.AddressSearch;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersAdressSearchActivity, OhtersAddressListActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final OthersAdressSearchActivity this$0;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        Bundle bundle = OthersAdressSearchActivity.access$100(OthersAdressSearchActivity.this).getResults();
        if (bundle == null || bundle.size() == 0)
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0122));
        } else
        {
            Intent intent = new Intent(OthersAdressSearchActivity.this, jp/co/yahoo/android/apps/transit/OhtersAddressListActivity);
            intent.putExtra("address", bundle);
            intent.putExtra(getString(0x7f0d01df), OthersAdressSearchActivity.access$200(OthersAdressSearchActivity.this));
            startActivityForResult(intent, getResources().getInteger(0x7f0c0046));
        }
        return false;
    }

    ()
    {
        this$0 = OthersAdressSearchActivity.this;
        super();
    }
}
