// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceListener, YLoginServiceManager, YJDNErrorData

public class YLogoutDialogActivity extends Activity
    implements YLoginServiceListener, android.view.View.OnClickListener
{

    private String mAppid;
    private boolean mConnectedFlag;
    private YLoginServiceManager mLoginManager;

    public YLogoutDialogActivity()
    {
        mConnectedFlag = false;
        mAppid = "";
        mLoginManager = null;
    }

    private Intent getResultIntent()
    {
        Intent intent = new Intent();
        intent.putExtra("extra_key_request_code", 1060);
        return intent;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (intent != null && intent.getIntExtra("extra_key_request_code", -1) == 1059)
        {
            Intent intent1 = new Intent();
            intent1.putExtra("extra_key_request_code", 1059);
            intent1.putExtra("extra_key_is_change_yid", true);
            setResult(j, intent1);
            finish();
        }
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i != jp.co.yahoo.android.common.R.id.CommonButtonLogout) goto _L2; else goto _L1
_L1:
        if (mConnectedFlag) goto _L4; else goto _L3
_L3:
        return;
_L4:
        mLoginManager.logout();
        setResult(-1, getResultIntent());
        finish();
        return;
_L2:
        if (i != jp.co.yahoo.android.common.R.id.CommonButtonOtherLogin)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!mConnectedFlag) goto _L3; else goto _L5
_L5:
        mLoginManager.logout();
        mLoginManager.startLoginActivityForResult(this, true);
        return;
        if (i != jp.co.yahoo.android.common.R.id.CommonButtonLogoutCancel) goto _L3; else goto _L6
_L6:
        finish();
        return;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setResult(0, getResultIntent());
        Intent intent = getIntent();
        if (intent != null)
        {
            mAppid = intent.getStringExtra("extra_key_appid");
        }
        mLoginManager = new YLoginServiceManager(this, mAppid);
        setContentView(jp.co.yahoo.android.common.R.layout.common_logout_dialog);
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.CommonButtonLogout)).setOnClickListener(this);
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.CommonButtonOtherLogin)).setOnClickListener(this);
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.CommonButtonLogoutCancel)).setOnClickListener(this);
        mLoginManager.bindService(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mLoginManager.unbindService(this);
        mLoginManager = null;
    }

    public void onLogin()
    {
    }

    public void onLoginCanceled()
    {
    }

    public void onLoginFailed(String s)
    {
    }

    public void onLogout()
    {
    }

    protected void onPause()
    {
        super.onPause();
        mLoginManager.unregisterCallback();
    }

    protected void onResume()
    {
        super.onResume();
        mLoginManager.registerCallback();
    }

    public void onServiceConnected()
    {
        mLoginManager.registerCallback();
        mConnectedFlag = true;
        ((TextView)findViewById(jp.co.yahoo.android.common.R.id.CommonTextViewYID)).setText(mLoginManager.getYID());
    }

    public void onServiceDisconnected()
    {
    }

    public void onUpdateCredential()
    {
    }

    public void onYJDNCanceled(boolean flag, String as[], Object aobj[])
    {
    }

    public void onYJDNDownloadFailed(YJDNErrorData yjdnerrordata, byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNDownloadFailedAtConverter(String s, boolean flag, String as[], Object aobj[])
    {
    }

    public void onYJDNDownloaded(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNDownloadedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNHttpError(byte abyte0[], Header aheader[], int i, boolean flag, String s, Object obj)
    {
    }

    public void onYJDNResponsed(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNResponsedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }
}
