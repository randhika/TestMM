// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

// Referenced classes of package jp.co.yahoo.android.common:
//            YPackageManager

public class YApplicationBase extends Application
{

    private static YApplicationBase INSTANCE;
    public static final String PREF_KEY_SRD_ACCESS_TIME = "SRD_ACCESS_TIME";
    public static final String PREF_KEY_SRD_ACCESS_TIME_ON_STARTUP = "SRD_ACCESS_TIME_ON_STARTUP";
    private static final String PREF_KEY_UUID = "UUID";
    private static final String PREF_NAME = "PREFS_YAPPLICATIONBASE";
    private static String sBrowserUserAgent = null;
    private static String sName = null;
    private static String sUUID = null;
    private static String sUserAgent = null;
    private static String sVersion = null;
    private static int sVersionCode = -1;

    public YApplicationBase()
    {
        if (INSTANCE != null)
        {
            throw new IllegalStateException("Only one ApplicationBase allowed!");
        } else
        {
            INSTANCE = this;
            return;
        }
    }

    public static String getBrowserUserAgent()
    {
        if (sBrowserUserAgent == null)
        {
            sBrowserUserAgent = (new WebView(INSTANCE)).getSettings().getUserAgentString();
        }
        return sBrowserUserAgent;
    }

    public static String getClientUUID()
    {
        if (INSTANCE == null)
        {
            return "";
        }
        if (sUUID != null)
        {
            return sUUID;
        }
        SharedPreferences sharedpreferences = INSTANCE.getApplicationContext().getSharedPreferences("PREFS_YAPPLICATIONBASE", 0);
        sUUID = sharedpreferences.getString("UUID", null);
        if (sUUID == null)
        {
            sUUID = UUID.randomUUID().toString();
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("UUID", sUUID);
            editor.commit();
        }
        return sUUID;
    }

    public static YApplicationBase getInstance()
    {
        return INSTANCE;
    }

    public static String getName()
    {
        if (INSTANCE == null)
        {
            return "";
        }
        if (sName != null)
        {
            return sName;
        } else
        {
            String s = INSTANCE.getApplicationInfo().packageName;
            sName = s.substring(1 + s.lastIndexOf("."));
            return sName;
        }
    }

    public static String getUserAgent()
    {
        if (INSTANCE == null)
        {
            return "";
        }
        if (sUserAgent != null)
        {
            return sUserAgent;
        } else
        {
            Object aobj[] = new Object[9];
            aobj[0] = "1.1";
            aobj[1] = getName();
            aobj[2] = getVersion();
            aobj[3] = Build.BRAND;
            aobj[4] = Build.DEVICE;
            aobj[5] = Build.MANUFACTURER;
            aobj[6] = Build.MODEL;
            aobj[7] = android.os.Build.VERSION.RELEASE;
            aobj[8] = Build.ID;
            sUserAgent = String.format("YahooJMobileApp/%s (Android %s; %s) (%s; %s; %s; %s; %s/%s)", aobj);
            return sUserAgent;
        }
    }

    public static String getVersion()
    {
        if (INSTANCE == null)
        {
            return "";
        }
        if (sVersion != null)
        {
            return sVersion;
        }
        PackageInfo packageinfo;
        try
        {
            packageinfo = INSTANCE.getPackageManager().getPackageInfo(INSTANCE.getApplicationInfo().packageName, 0);
        }
        catch (Exception exception)
        {
            return "";
        }
        sVersion = packageinfo.versionName;
        return sVersion;
    }

    public static int getVersionCode()
    {
        if (INSTANCE == null)
        {
            return -1;
        }
        if (sVersionCode != -1)
        {
            return sVersionCode;
        }
        PackageInfo packageinfo;
        try
        {
            packageinfo = INSTANCE.getPackageManager().getPackageInfo(INSTANCE.getApplicationInfo().packageName, 0);
        }
        catch (Exception exception)
        {
            return -1;
        }
        sVersionCode = packageinfo.versionCode;
        return sVersionCode;
    }

    public static boolean isDebugSignature()
    {
        return YPackageManager.isDebugSignature(INSTANCE);
    }

    public boolean isSRDOverTime(String s, int i)
    {
        int j = getApplicationContext().getSharedPreferences("PREFS_YAPPLICATIONBASE", 0).getInt(s, 0);
        int k = (int)((new Date()).getTime() / 1000L);
        int l = j + i;
        boolean flag = false;
        if (k > l)
        {
            flag = true;
        }
        return flag;
    }

    public void onCreate()
    {
        super.onCreate();
        getBrowserUserAgent();
    }

    protected void onTouchSRDCompleted(boolean flag)
    {
    }

    public void saveTouchSRDTime(String s)
    {
        android.content.SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("PREFS_YAPPLICATIONBASE", 0).edit();
        editor.putInt(s, (int)((new Date()).getTime() / 1000L));
        editor.commit();
    }

    public void touchSRD(final String aURL)
    {
        (new AsyncTask() {

            final YApplicationBase this$0;
            final String val$aURL;
            final String val$ua;

            protected transient Boolean doInBackground(Void avoid[])
            {
                InputStream inputstream;
                HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(aURL)).openConnection();
                httpurlconnection.setInstanceFollowRedirects(false);
                httpurlconnection.setConnectTimeout(10000);
                httpurlconnection.setReadTimeout(10000);
                httpurlconnection.setRequestProperty("User-Agent", ua);
                httpurlconnection.connect();
                inputstream = httpurlconnection.getInputStream();
                boolean flag = true;
                Exception exception;
                Exception exception1;
                IOException ioexception1;
                if (inputstream != null)
                {
                    try
                    {
                        inputstream.close();
                    }
                    catch (IOException ioexception2) { }
                }
                return Boolean.valueOf(flag);
                exception1;
                flag = false;
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
                null.close();
                flag = false;
                continue; /* Loop/switch isn't completed */
                ioexception1;
                flag = false;
                if (true) goto _L2; else goto _L1
_L1:
                break MISSING_BLOCK_LABEL_110;
_L2:
                break MISSING_BLOCK_LABEL_77;
                exception;
                if (false)
                {
                    try
                    {
                        null.close();
                    }
                    catch (IOException ioexception) { }
                }
                throw exception;
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected void onPostExecute(Boolean boolean1)
            {
                onTouchSRDCompleted(boolean1.booleanValue());
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Boolean)obj);
            }

            
            {
                this$0 = YApplicationBase.this;
                aURL = s;
                ua = s1;
                super();
            }
        }).execute(new Void[0]);
    }

}
