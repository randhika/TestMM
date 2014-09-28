// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            AppLoginActivity

public class LoginInvisibleActivity extends Activity
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/LoginInvisibleActivity.getSimpleName();

    public LoginInvisibleActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.verbose(TAG, "Webview invisible activity.");
        setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_invisible);
        startActivity(new Intent(this, jp/co/yahoo/yconnect/sso/AppLoginActivity));
        finish();
    }

}
