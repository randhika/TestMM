// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.AsyncTaskLoader;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.DataManager;

public class AppLogoutAsyncTask extends AsyncTaskLoader
{

    private static final String TAG = jp/co/yahoo/yconnect/sso/AppLogoutAsyncTask.getSimpleName();
    private FragmentActivity activity;

    public AppLogoutAsyncTask(FragmentActivity fragmentactivity)
    {
        super(fragmentactivity);
        activity = fragmentactivity;
    }

    public Boolean loadInBackground()
    {
        Boolean boolean1;
        try
        {
            YConnectLogger.info(TAG, "Request delete AccessToken, RefreshToken and IdToken.");
            byte abyte0[] = DataManager.loadSecretKey(activity, "default_yid");
            DataManager datamanager = new DataManager(activity, abyte0, "default_yid");
            datamanager.deleteAccessToken();
            datamanager.deleteIdToken();
            boolean1 = Boolean.valueOf(true);
        }
        catch (Exception exception)
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("error=").append(exception.getMessage()).toString());
            return Boolean.valueOf(false);
        }
        return boolean1;
    }

    public volatile Object loadInBackground()
    {
        return loadInBackground();
    }

    protected void onStartLoading()
    {
        forceLoad();
    }

}
