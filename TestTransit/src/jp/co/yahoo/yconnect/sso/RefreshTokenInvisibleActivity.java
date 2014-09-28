// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            RefreshTokenAsyncTask

public class RefreshTokenInvisibleActivity extends FragmentActivity
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/RefreshTokenInvisibleActivity.getSimpleName();

    public RefreshTokenInvisibleActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.verbose(TAG, "Refresh token webview invisible activity.");
        setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_invisible);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    public Loader onCreateLoader(int i, Bundle bundle)
    {
        YConnectLogger.verbose(TAG, "onCreateLoader");
        return new RefreshTokenAsyncTask(this);
    }

    public volatile void onLoadFinished(Loader loader, Object obj)
    {
        onLoadFinished(loader, (String)obj);
    }

    public void onLoadFinished(Loader loader, String s)
    {
        YConnectLogger.verbose(TAG, "onLoadFinished");
        YConnectLogger.debug(TAG, (new StringBuilder()).append("AsyncTAesk result : ").append(s).toString());
        if (s != null && !s.equals(""))
        {
            if (s == "expired")
            {
                AppLoginExplicit.getInstance().issueRefreshToken(this, 200);
                s = "0";
            }
            setResult(Integer.valueOf(s).intValue());
        } else
        {
            setResult(Integer.valueOf("999").intValue());
        }
        finish();
    }

    public void onLoaderReset(Loader loader)
    {
    }

}
