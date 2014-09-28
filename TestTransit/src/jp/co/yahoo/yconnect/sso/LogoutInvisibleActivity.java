// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.sso:
//            SSODialogFragment, AppLogoutAsyncTask, AppLogoutWebviewClient

public class LogoutInvisibleActivity extends FragmentActivity
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/LogoutInvisibleActivity.getSimpleName();
    public SSODialogFragment fragment;

    public LogoutInvisibleActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        YConnectLogger.verbose(TAG, "Webview invisible activity.");
        setContentView(jp.co.yahoo.yconnect.sdk.R.layout.appsso_invisible);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    public Loader onCreateLoader(int i, Bundle bundle)
    {
        YConnectLogger.verbose(TAG, "onCreateLoader");
        Bundle bundle1 = new Bundle();
        bundle1.putString("Message", "\u8AAD\u307F\u8FBC\u307F\u4E2D...");
        fragment = SSODialogFragment.newInstance();
        fragment.setArguments(bundle1);
        fragment.show(getSupportFragmentManager(), "progress");
        return new AppLogoutAsyncTask(this);
    }

    public void onLoadFinished(Loader loader, Boolean boolean1)
    {
        YConnectLogger.verbose(TAG, "onLoadFinished");
        if (boolean1.booleanValue())
        {
            (new AppLogoutWebviewClient(this)).logoutWebviewClient();
            return;
        } else
        {
            finish();
            return;
        }
    }

    public volatile void onLoadFinished(Loader loader, Object obj)
    {
        onLoadFinished(loader, (Boolean)obj);
    }

    public void onLoaderReset(Loader loader)
    {
    }

}
