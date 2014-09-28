// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import jp.co.yahoo.applicot.annotation.ReportsCrashes;
import jp.co.yahoo.applicot.log.AndroidLogDelegate;
import jp.co.yahoo.applicot.log.ApplicotLog;

// Referenced classes of package jp.co.yahoo.applicot:
//            ApplicotConfigurationException, ReportingInteractionMode, ApplicotConfiguration, ErrorReporter

public class Applicot
{

    public static final boolean DEV_LOGGING = false;
    public static final String LOG_TAG = jp/co/yahoo/applicot/Applicot.getSimpleName();
    public static final String PREF_ALWAYS_ACCEPT = "applicot.alwaysaccept";
    public static final String PREF_DISABLE_APPLICOT = "applicot.disable";
    public static final String PREF_ENABLE_APPLICOT = "applicot.enable";
    public static final String PREF_ENABLE_DEVICE_ID = "applicot.deviceid.enable";
    public static final String PREF_ENABLE_SYSTEM_LOGS = "applicot.syslog.enable";
    public static final String PREF_LAST_VERSION_NR = "applicot.lastVersionNr";
    public static final String PREF_USER_EMAIL_ADDRESS = "applicot.user.email";
    public static String bCookie = "";
    private static ApplicotConfiguration configProxy;
    private static ErrorReporter errorReporterSingleton;
    public static ApplicotLog log = new AndroidLogDelegate();
    private static String mApplicotKey;
    private static Context mContext;
    private static android.content.SharedPreferences.OnSharedPreferenceChangeListener mPrefListener;
    private static ReportsCrashes mReportsCrashes;
    public static String yjdnAppId = "";

    public Applicot()
    {
    }

    static void checkCrashResources()
        throws ApplicotConfigurationException
    {
        ApplicotConfiguration applicotconfiguration = getConfig();
        static class _cls2
        {

            static final int $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[];

            static 
            {
                $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode = new int[ReportingInteractionMode.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.TOAST.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.NOTIFICATION.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$applicot$ReportingInteractionMode[ReportingInteractionMode.DIALOG.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        _cls2..SwitchMap.jp.co.yahoo.applicot.ReportingInteractionMode[applicotconfiguration.mode().ordinal()];
        JVM INSTR tableswitch 1 3: default 44
    //                   1 45
    //                   2 64
    //                   3 110;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if (applicotconfiguration.resToastText() == 0)
        {
            throw new ApplicotConfigurationException("TOAST mode: you have to define the resToastText parameter in your application @ReportsCrashes() annotation.");
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (applicotconfiguration.resNotifTickerText() == 0 || applicotconfiguration.resNotifTitle() == 0 || applicotconfiguration.resNotifText() == 0 || applicotconfiguration.resDialogText() == 0)
        {
            throw new ApplicotConfigurationException("NOTIFICATION mode: you have to define at least the resNotifTickerText, resNotifTitle, resNotifText, resDialogText parameters in your application @ReportsCrashes() annotation.");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (applicotconfiguration.resDialogText() == 0)
        {
            throw new ApplicotConfigurationException("DIALOG mode: you have to define at least the resDialogText parameters in your application @ReportsCrashes() annotation.");
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static String getApplicotKey()
    {
        return mApplicotKey;
    }

    public static SharedPreferences getApplicotSharedPreferences()
    {
        ApplicotConfiguration applicotconfiguration = getConfig();
        if (!"".equals(applicotconfiguration.sharedPreferencesName()))
        {
            return mContext.getSharedPreferences(applicotconfiguration.sharedPreferencesName(), applicotconfiguration.sharedPreferencesMode());
        } else
        {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        }
    }

    public static String getBCookie()
    {
        return bCookie;
    }

    public static ApplicotConfiguration getConfig()
    {
        if (configProxy == null)
        {
            if (mContext == null)
            {
                log.i(LOG_TAG, "Calling Applicot.getConfig() before Applicot.init() gives you an empty configuration instance. You might prefer calling Applicot.getNewDefaultConfig(Application) to get an instance with default values taken from a @ReportsCrashes annotation.");
            }
            configProxy = getNewDefaultConfig(mContext);
        }
        return configProxy;
    }

    public static Context getContext()
    {
        return mContext;
    }

    public static ErrorReporter getErrorReporter()
    {
        if (errorReporterSingleton == null)
        {
            throw new IllegalStateException("Cannot access ErrorReporter before Applicot#init");
        } else
        {
            return errorReporterSingleton;
        }
    }

    public static ApplicotConfiguration getNewDefaultConfig(Context context)
    {
        if (mReportsCrashes != null)
        {
            return new ApplicotConfiguration((ReportsCrashes)context.getClass().getAnnotation(jp/co/yahoo/applicot/annotation/ReportsCrashes));
        } else
        {
            return new ApplicotConfiguration((ReportsCrashes)jp/co/yahoo/applicot/Applicot.getAnnotation(jp/co/yahoo/applicot/annotation/ReportsCrashes));
        }
    }

    public static void init(Context context, String s, String s1)
    {
        SharedPreferences sharedpreferences;
        if (mContext != null)
        {
            log.w(LOG_TAG, "Applicot#init called more than once. Won't do anything more.");
            return;
        }
        if (mApplicotKey != null)
        {
            log.w(LOG_TAG, "Applicot#init called more than once. Won't do anything more.");
            return;
        }
        mContext = context;
        mApplicotKey = s;
        mReportsCrashes = (ReportsCrashes)mContext.getClass().getAnnotation(jp/co/yahoo/applicot/annotation/ReportsCrashes);
        if (mReportsCrashes == null)
        {
            log.i(LOG_TAG, (new StringBuilder()).append("Applicot#init called but no ReportsCrashes annotation on Activity ").append(mContext.getPackageName()).toString());
        }
        sharedpreferences = getApplicotSharedPreferences();
        checkCrashResources();
        log.d(LOG_TAG, (new StringBuilder()).append("Applicot is enabled for ").append(mContext.getPackageName()).append(", intializing...").toString());
        boolean flag;
        if (!shouldDisableApplicot(sharedpreferences))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            ErrorReporter errorreporter = new ErrorReporter(mContext, sharedpreferences, flag);
            errorreporter.setDefaultReportSenders();
            errorReporterSingleton = errorreporter;
        }
        catch (ApplicotConfigurationException applicotconfigurationexception)
        {
            log.w(LOG_TAG, "Error : ", applicotconfigurationexception);
        }
        mPrefListener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences sharedpreferences1, String s2)
            {
                if ("applicot.disable".equals(s2) || "applicot.enable".equals(s2))
                {
                    boolean flag1;
                    if (!Applicot.shouldDisableApplicot(sharedpreferences1))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    Applicot.getErrorReporter().setEnabled(flag1);
                }
            }

        };
        sharedpreferences.registerOnSharedPreferenceChangeListener(mPrefListener);
        return;
    }

    static boolean isDebuggable()
    {
        PackageManager packagemanager = mContext.getPackageManager();
        int i;
        int j;
        boolean flag;
        try
        {
            i = packagemanager.getApplicationInfo(mContext.getPackageName(), 0).flags;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        j = i & 2;
        flag = false;
        if (j > 0)
        {
            flag = true;
        }
        return flag;
    }

    public static void setApplicotKey(String s)
    {
        mApplicotKey = s;
    }

    public static void setBCookie(String s)
    {
        bCookie = s;
    }

    public static void setConfig(ApplicotConfiguration applicotconfiguration)
    {
        configProxy = applicotconfiguration;
    }

    public static void setLog(ApplicotLog applicotlog)
    {
        log = applicotlog;
    }

    private static boolean shouldDisableApplicot(SharedPreferences sharedpreferences)
    {
        boolean flag = true;
        boolean flag1;
        if (sharedpreferences.getBoolean("applicot.enable", true))
        {
            flag = false;
        }
        flag1 = sharedpreferences.getBoolean("applicot.disable", flag);
        return flag1;
        Exception exception;
        exception;
        return false;
    }


}
