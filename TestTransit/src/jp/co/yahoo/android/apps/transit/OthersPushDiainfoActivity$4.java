// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.widget.CompoundButton;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersPushDiainfoActivity

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.._cls0
{

    final OthersPushDiainfoActivity this$0;

    public boolean onCanceled()
    {
        OthersPushDiainfoActivity.access$602(OthersPushDiainfoActivity.this, false);
        closeProgressDialog();
        return false;
    }

    public boolean onError(APIError apierror)
    {
        OthersPushDiainfoActivity.access$602(OthersPushDiainfoActivity.this, false);
        closeProgressDialog();
        if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            TransitUtil.logout(OthersPushDiainfoActivity.this);
            OthersPushDiainfoActivity.access$1100(OthersPushDiainfoActivity.this);
            return false;
        } else
        {
            showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
            return false;
        }
    }

    public boolean onSuccess(Bundle bundle)
    {
        OthersPushDiainfoActivity.access$602(OthersPushDiainfoActivity.this, false);
        OthersPushDiainfoActivity.access$702(OthersPushDiainfoActivity.this, bundle);
        if (OthersPushDiainfoActivity.access$800(OthersPushDiainfoActivity.this) != null)
        {
            OthersPushDiainfoActivity.access$902(OthersPushDiainfoActivity.this, OthersPushDiainfoActivity.access$800(OthersPushDiainfoActivity.this).booleanValue());
            OthersPushDiainfoActivity.access$802(OthersPushDiainfoActivity.this, null);
        } else
        {
            boolean flag = OthersPushDiainfoActivity.access$400(OthersPushDiainfoActivity.this).getDiainfoStopAllFlag(bundle);
            OthersPushDiainfoActivity otherspushdiainfoactivity = OthersPushDiainfoActivity.this;
            boolean flag1;
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            OthersPushDiainfoActivity.access$902(otherspushdiainfoactivity, flag1);
        }
        if (OthersPushDiainfoActivity.access$1000(OthersPushDiainfoActivity.this).isChecked() == OthersPushDiainfoActivity.access$900(OthersPushDiainfoActivity.this))
        {
            onCheckedChanged(null, OthersPushDiainfoActivity.access$900(OthersPushDiainfoActivity.this));
            return false;
        } else
        {
            OthersPushDiainfoActivity.access$1000(OthersPushDiainfoActivity.this).setChecked(OthersPushDiainfoActivity.access$900(OthersPushDiainfoActivity.this));
            return false;
        }
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    ner()
    {
        this$0 = OthersPushDiainfoActivity.this;
        super();
    }
}
