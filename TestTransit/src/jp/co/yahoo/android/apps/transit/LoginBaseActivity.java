// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.ProgressDialog;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class LoginBaseActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener
{

    public static final int CODE_LOGIN = 1000;
    public static final int CODE_LOGOUT = 1100;
    protected String m_YID;
    protected ProgressDialog progressDialog;
    private PushDiainfoManager pushMgr;

    public LoginBaseActivity()
    {
        m_YID = "";
    }

    public void login()
    {
        TransitUtil.login(this);
    }

    public void logout()
    {
        jp.co.yahoo.yconnect.core.oauth2.BearerToken bearertoken = TransitUtil.getAccessToken(this);
        if (bearertoken == null)
        {
            TransitUtil.logout(this);
            onLogout();
            return;
        }
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        if (pushMgr.setRailPushAllOff(bearertoken, TransitUtil.getYID(this), this))
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
            progressDialog.setMessage(getString(0x7f0d04a1));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
            return;
        } else
        {
            TransitUtil.logout(this);
            onLogout();
            return;
        }
    }

    public void onCanceled()
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        m_YID = TransitUtil.getYID(this);
    }

    public void onError(int i, String s, String s1, String s2)
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        if (s.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            TransitUtil.logout(this);
            onLogout();
            return;
        }
        showErrorMessageDialog(s2, s1);
        return;
    }

    protected void onLogout()
    {
    }

    public void onSuccess(String s, String s1)
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        TransitUtil.logout(this);
        onLogout();
    }
}
