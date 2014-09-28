// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersPushDiainfoActivity

class this._cls1
    implements android.content.r
{

    final sh this$1;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
        setResult(-1);
        finish();
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/OthersPushDiainfoActivity$2

/* anonymous class */
    class OthersPushDiainfoActivity._cls2
        implements jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener
    {

        final OthersPushDiainfoActivity this$0;

        public void onCanceled()
        {
            OthersPushDiainfoActivity.access$302(OthersPushDiainfoActivity.this, false);
            closeProgressDialog();
        }

        public void onError(int i, String s, String s1, String s2)
        {
            OthersPushDiainfoActivity.access$302(OthersPushDiainfoActivity.this, false);
            closeProgressDialog();
            if (s != null && s.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
            {
                TransitUtil.logout(OthersPushDiainfoActivity.this);
                saveAndBack(null);
                return;
            } else
            {
                showErrorMessageDialog(s2, s1);
                return;
            }
        }

        public void onSuccess(String s, String s1)
        {
            OthersPushDiainfoActivity.access$302(OthersPushDiainfoActivity.this, false);
            OthersPushDiainfoActivity.access$400(OthersPushDiainfoActivity.this).savePushTime(OthersPushDiainfoActivity.access$000(OthersPushDiainfoActivity.this));
            closeProgressDialog();
            if (isFinishing())
            {
                return;
            } else
            {
                (new TransitDialogBuilder(OthersPushDiainfoActivity.this)).setMessage(s1).setTitleInfo(s).setPositiveButton(getString(0x7f0d015c), new OthersPushDiainfoActivity._cls2._cls1()).show();
                return;
            }
        }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
    }

}
