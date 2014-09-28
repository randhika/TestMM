// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceListener, YLoginServiceManager, YJDNErrorData

public class YLoginActivity extends Activity
    implements YLoginServiceListener, android.view.View.OnClickListener
{

    private CheckBox mCheckBoxAutoLogin;
    private boolean mConnectedFlag;
    private EditText mEditTextPasswd;
    private EditText mEditTextYid;
    private YLoginServiceManager mLoginManager;
    private ProgressDialog mProgressDialog;

    public YLoginActivity()
    {
        mEditTextYid = null;
        mEditTextPasswd = null;
        mCheckBoxAutoLogin = null;
        mConnectedFlag = false;
        mProgressDialog = null;
        mLoginManager = null;
    }

    private void doLogin()
    {
        if (!mConnectedFlag)
        {
            return;
        }
        String s = mEditTextYid.getText().toString();
        String s1 = mEditTextPasswd.getText().toString();
        boolean flag = mCheckBoxAutoLogin.isChecked();
        if (s.indexOf(" ") != -1)
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "Yahoo! JAPAN ID\u306B\u4E0D\u6B63\u306A\u6587\u5B57\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002");
            return;
        }
        if (s.equals(""))
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "Yahoo! JAPAN ID\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002");
            return;
        }
        if (s1.equals(""))
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002");
            return;
        } else
        {
            mLoginManager.login(s, s1, flag);
            mProgressDialog = getProgressDialog();
            mProgressDialog.show();
            return;
        }
    }

    private ProgressDialog getProgressDialog()
    {
        ProgressDialog progressdialog = new ProgressDialog(this);
        progressdialog.setProgressStyle(0);
        progressdialog.setCancelable(true);
        progressdialog.setMessage("\u901A\u4FE1\u4E2D...\u3000\u3000");
        progressdialog.setButton(-2, "\u30AD\u30E3\u30F3\u30BB\u30EB", new android.content.DialogInterface.OnClickListener() {

            final YLoginActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mLoginManager.cancelLogin();
            }

            
            {
                this$0 = YLoginActivity.this;
                super();
            }
        });
        progressdialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final YLoginActivity this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
                mLoginManager.cancelLogin();
            }

            
            {
                this$0 = YLoginActivity.this;
                super();
            }
        });
        return progressdialog;
    }

    private Intent getResultIntent()
    {
        Intent intent = new Intent();
        intent.putExtra("extra_key_request_code", 1059);
        intent.putExtra("extra_key_is_change_yid", false);
        return intent;
    }

    private void setupViews()
    {
        requestWindowFeature(7);
        setContentView(jp.co.yahoo.android.common.R.layout.common_login);
        getWindow().setFeatureInt(7, jp.co.yahoo.android.common.R.layout.common_login_titlebar);
        ((TextView)findViewById(jp.co.yahoo.android.common.R.id.TitleOnTitlebar)).setText("\u30ED\u30B0\u30A4\u30F3");
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.ButtonCancel)).setOnClickListener(this);
        mEditTextYid = (EditText)findViewById(jp.co.yahoo.android.common.R.id.CommonEditTextYID);
        mEditTextPasswd = (EditText)findViewById(jp.co.yahoo.android.common.R.id.CommonEditTextPasswd);
        mEditTextPasswd.setInputType(129);
        mEditTextPasswd.setTransformationMethod(new PasswordTransformationMethod());
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.CommonButtonLogin)).setOnClickListener(this);
        ((Button)findViewById(jp.co.yahoo.android.common.R.id.CommonButtonGetYID)).setOnClickListener(this);
        mCheckBoxAutoLogin = (CheckBox)findViewById(jp.co.yahoo.android.common.R.id.CommonCheckBoxAutoLogin);
        Intent intent = getIntent();
        if (intent != null)
        {
            String s = intent.getStringExtra("extra_key_message");
            if (s != null && !"".equals(s))
            {
                TextView textview = (TextView)findViewById(jp.co.yahoo.android.common.R.id.CommonLoginExtraMessage);
                textview.setText(s);
                textview.setVisibility(0);
            }
        }
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i == jp.co.yahoo.android.common.R.id.CommonButtonLogin)
        {
            doLogin();
        } else
        {
            if (i == jp.co.yahoo.android.common.R.id.CommonButtonGetYID)
            {
                Intent intent = getIntent();
                String s = "https://account.edit.yahoo.co.jp/registration";
                if (intent != null)
                {
                    s = intent.getStringExtra("extra_key_registration_url");
                }
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                return;
            }
            if (i == jp.co.yahoo.android.common.R.id.ButtonCancel)
            {
                finish();
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setResult(0, getResultIntent());
        Intent intent = getIntent();
        String s = "";
        if (intent != null)
        {
            s = intent.getStringExtra("extra_key_appid");
        }
        mLoginManager = new YLoginServiceManager(this, s);
        setupViews();
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
        mProgressDialog.dismiss();
        setResult(-1, getResultIntent());
        finish();
    }

    public void onLoginCanceled()
    {
    }

    public void onLoginFailed(String s)
    {
        mProgressDialog.dismiss();
        if (s.equals("InvalidLoginOrPassword"))
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "Yahoo! JAPAN ID\u307E\u305F\u306F\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u6B63\u3057\u304F\u3042\u308A\u307E\u305B\u3093\u3002");
            return;
        }
        if ("UserMustLogin".equals(s))
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "\u4E00\u6642\u7684\u306B\u30ED\u30B0\u30A4\u30F3\u3067\u304D\u307E\u305B\u3093\u3002\u30D6\u30E9\u30A6\u30B6\u3088\u308A\u30ED\u30B0\u30A4\u30F3\u5F8C\u3001\u518D\u5EA6\u304A\u8A66\u3057\u304F\u3060\u3055\u3044\u3002");
            return;
        } else
        {
            showDialog("\u30ED\u30B0\u30A4\u30F3\u30A8\u30E9\u30FC", "\u4E00\u6642\u7684\u306B\u30A2\u30D7\u30EA\u30B1\u30FC\u30B7\u30E7\u30F3\u304C\u3054\u5229\u7528\u3044\u305F\u3060\u3051\u307E\u305B\u3093\u3002\u3057\u3070\u3089\u304F\u6642\u9593\u3092\u304A\u3044\u3066\u304B\u3089\u518D\u5EA6\u304A\u8A66\u3057\u304F\u3060\u3055\u3044\u3002");
            return;
        }
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
        if (mLoginManager.isAutoLogin())
        {
            mCheckBoxAutoLogin.setChecked(true);
            if (!getIntent().getBooleanExtra("extra_key_other_id_login", false))
            {
                mEditTextYid.setText(mLoginManager.getStoredYID());
            }
            return;
        } else
        {
            mCheckBoxAutoLogin.setChecked(false);
            return;
        }
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

    protected void showDialog(String s, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setMessage(s1);
        builder.setNegativeButton("OK", new android.content.DialogInterface.OnClickListener() {

            final YLoginActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = YLoginActivity.this;
                super();
            }
        });
        builder.show();
    }

}
