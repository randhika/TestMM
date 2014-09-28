// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoDetailActivity

class val.diainfo
    implements jp.co.yahoo.android.apps.transit.common.anagerListener
{

    final DiainfoDetailActivity this$0;
    final DiainfoData val$diainfo;

    public void onCanceled()
    {
        closeProgressDialog();
    }

    public void onError(int i, String s, String s1, String s2)
    {
        closeProgressDialog();
        if (s != null)
        {
            try
            {
                if (s.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                {
                    TransitUtil.logout(DiainfoDetailActivity.this);
                    DiainfoDetailActivity.access$400(DiainfoDetailActivity.this, val$diainfo);
                    return;
                }
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                return;
            }
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        if (s.equals("0"))
        {
            findViewById(0x7f0a007b).setVisibility(8);
        }
        showErrorMessageDialog(s2, s1);
        return;
    }

    public void onSuccess(String s, String s1)
    {
        closeProgressDialog();
        findViewById(0x7f0a007b).setVisibility(8);
    }

    r.PushManagerListener()
    {
        this$0 = final_diainfodetailactivity;
        val$diainfo = DiainfoData.this;
        super();
    }
}
