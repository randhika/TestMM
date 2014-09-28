// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import java.util.Map;
import jp.co.yahoo.applicot.annotation.ReportsCrashes;

// Referenced classes of package jp.co.yahoo.applicot:
//            ReportField, ReportingInteractionMode, ApplicotConfigurationException, Applicot

public class ApplicotConfiguration
    implements ReportsCrashes
{

    private String mAdditionalDropboxTags[];
    private String mAdditionalSharedPreferences[];
    private String mApplicationLogFile;
    private Integer mApplicationLogFileLines;
    private Integer mConnectionTimeout;
    private ReportField mCustomReportContent[];
    private Boolean mDeleteOldUnsentReportsOnApplicationStart;
    private Boolean mDeleteUnapprovedReportsOnApplicationStart;
    private Boolean mDisableSSLCertValidation;
    private Integer mDropboxCollectionMinutes;
    private String mExcludeMatchingSettingsKeys[];
    private String mExcludeMatchingSharedPreferencesKeys[];
    private Boolean mForceCloseDialogAfterToast;
    private String mFormKey;
    private String mFormUri;
    private String mFormUriBasicAuthLogin;
    private String mFormUriBasicAuthPassword;
    private Map mHttpHeaders;
    private jp.co.yahoo.applicot.sender.HttpSender.Method mHttpMethod;
    private Boolean mIncludeDropboxSystemTags;
    private String mLogcatArguments[];
    private Boolean mLogcatFilterByPid;
    private String mMailTo;
    private Integer mMaxNumberOfRequestRetries;
    private ReportingInteractionMode mMode;
    private jp.co.yahoo.applicot.sender.HttpSender.Type mReportType;
    private ReportsCrashes mReportsCrashes;
    private Integer mResDialogCommentPrompt;
    private Integer mResDialogEmailPrompt;
    private Integer mResDialogIcon;
    private Integer mResDialogOkToast;
    private Integer mResDialogText;
    private Integer mResDialogTitle;
    private Integer mResNotifIcon;
    private Integer mResNotifText;
    private Integer mResNotifTickerText;
    private Integer mResNotifTitle;
    private Integer mResToastText;
    private Boolean mSendReportsInDevMode;
    private Integer mSharedPreferenceMode;
    private String mSharedPreferenceName;
    private Integer mSocketTimeout;

    public ApplicotConfiguration(ReportsCrashes reportscrashes)
    {
        mAdditionalDropboxTags = null;
        mAdditionalSharedPreferences = null;
        mConnectionTimeout = null;
        mCustomReportContent = null;
        mDeleteUnapprovedReportsOnApplicationStart = null;
        mDeleteOldUnsentReportsOnApplicationStart = null;
        mDropboxCollectionMinutes = null;
        mForceCloseDialogAfterToast = null;
        mFormKey = null;
        mFormUri = null;
        mFormUriBasicAuthLogin = null;
        mFormUriBasicAuthPassword = null;
        mIncludeDropboxSystemTags = null;
        mLogcatArguments = null;
        mMailTo = null;
        mMaxNumberOfRequestRetries = null;
        mMode = null;
        mReportsCrashes = null;
        mResDialogCommentPrompt = null;
        mResDialogEmailPrompt = null;
        mResDialogIcon = null;
        mResDialogOkToast = null;
        mResDialogText = null;
        mResDialogTitle = null;
        mResNotifIcon = null;
        mResNotifText = null;
        mResNotifTickerText = null;
        mResNotifTitle = null;
        mResToastText = null;
        mSharedPreferenceMode = null;
        mSharedPreferenceName = null;
        mSocketTimeout = null;
        mLogcatFilterByPid = null;
        mSendReportsInDevMode = null;
        mExcludeMatchingSharedPreferencesKeys = null;
        mExcludeMatchingSettingsKeys = null;
        mApplicationLogFile = null;
        mApplicationLogFileLines = null;
        mDisableSSLCertValidation = null;
        mHttpMethod = null;
        mReportType = null;
        mReportsCrashes = reportscrashes;
    }

    public static boolean isNull(String s)
    {
        return s == null || "Applicot-NULL-STRING".equals(s);
    }

    public String[] additionalDropBoxTags()
    {
        if (mAdditionalDropboxTags != null)
        {
            return mAdditionalDropboxTags;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.additionalDropBoxTags();
        } else
        {
            return new String[0];
        }
    }

    public String[] additionalSharedPreferences()
    {
        if (mAdditionalSharedPreferences != null)
        {
            return mAdditionalSharedPreferences;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.additionalSharedPreferences();
        } else
        {
            return new String[0];
        }
    }

    public Class annotationType()
    {
        return mReportsCrashes.annotationType();
    }

    public String applicationLogFile()
    {
        if (mApplicationLogFile != null)
        {
            return mApplicationLogFile;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.applicationLogFile();
        } else
        {
            return "";
        }
    }

    public int applicationLogFileLines()
    {
        if (mApplicationLogFileLines != null)
        {
            return mApplicationLogFileLines.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.applicationLogFileLines();
        } else
        {
            return 3;
        }
    }

    public int connectionTimeout()
    {
        if (mConnectionTimeout != null)
        {
            return mConnectionTimeout.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.connectionTimeout();
        } else
        {
            return 3000;
        }
    }

    public ReportField[] customReportContent()
    {
        if (mCustomReportContent != null)
        {
            return mCustomReportContent;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.customReportContent();
        } else
        {
            return new ReportField[0];
        }
    }

    public boolean deleteOldUnsentReportsOnApplicationStart()
    {
        if (mDeleteOldUnsentReportsOnApplicationStart != null)
        {
            return mDeleteOldUnsentReportsOnApplicationStart.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.deleteOldUnsentReportsOnApplicationStart();
        } else
        {
            return true;
        }
    }

    public boolean deleteUnapprovedReportsOnApplicationStart()
    {
        if (mDeleteUnapprovedReportsOnApplicationStart != null)
        {
            return mDeleteUnapprovedReportsOnApplicationStart.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.deleteUnapprovedReportsOnApplicationStart();
        } else
        {
            return true;
        }
    }

    public boolean disableSSLCertValidation()
    {
        if (mDisableSSLCertValidation != null)
        {
            return mDisableSSLCertValidation.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.disableSSLCertValidation();
        } else
        {
            return false;
        }
    }

    public int dropboxCollectionMinutes()
    {
        if (mDropboxCollectionMinutes != null)
        {
            return mDropboxCollectionMinutes.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.dropboxCollectionMinutes();
        } else
        {
            return 5;
        }
    }

    public String[] excludeMatchingSettingsKeys()
    {
        if (mExcludeMatchingSettingsKeys != null)
        {
            return mExcludeMatchingSettingsKeys;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.excludeMatchingSettingsKeys();
        } else
        {
            return new String[0];
        }
    }

    public String[] excludeMatchingSharedPreferencesKeys()
    {
        if (mExcludeMatchingSharedPreferencesKeys != null)
        {
            return mExcludeMatchingSharedPreferencesKeys;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.excludeMatchingSharedPreferencesKeys();
        } else
        {
            return new String[0];
        }
    }

    public boolean forceCloseDialogAfterToast()
    {
        if (mForceCloseDialogAfterToast != null)
        {
            return mForceCloseDialogAfterToast.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.forceCloseDialogAfterToast();
        } else
        {
            return false;
        }
    }

    public String formUri()
    {
        if (mFormUri != null)
        {
            return mFormUri;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.formUri();
        } else
        {
            return "";
        }
    }

    public String formUriBasicAuthLogin()
    {
        if (mFormUriBasicAuthLogin != null)
        {
            return mFormUriBasicAuthLogin;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.formUriBasicAuthLogin();
        } else
        {
            return "Applicot-NULL-STRING";
        }
    }

    public String formUriBasicAuthPassword()
    {
        if (mFormUriBasicAuthPassword != null)
        {
            return mFormUriBasicAuthPassword;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.formUriBasicAuthPassword();
        } else
        {
            return "Applicot-NULL-STRING";
        }
    }

    public Map getHttpHeaders()
    {
        return mHttpHeaders;
    }

    public jp.co.yahoo.applicot.sender.HttpSender.Method httpMethod()
    {
        if (mHttpMethod != null)
        {
            return mHttpMethod;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.httpMethod();
        } else
        {
            return jp.co.yahoo.applicot.sender.HttpSender.Method.POST;
        }
    }

    public boolean includeDropBoxSystemTags()
    {
        if (mIncludeDropboxSystemTags != null)
        {
            return mIncludeDropboxSystemTags.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.includeDropBoxSystemTags();
        } else
        {
            return false;
        }
    }

    public String[] logcatArguments()
    {
        if (mLogcatArguments != null)
        {
            return mLogcatArguments;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.logcatArguments();
        } else
        {
            String as[] = new String[4];
            as[0] = "-t";
            as[1] = Integer.toString(3);
            as[2] = "-v";
            as[3] = "time";
            return as;
        }
    }

    public boolean logcatFilterByPid()
    {
        if (mLogcatFilterByPid != null)
        {
            return mLogcatFilterByPid.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.logcatFilterByPid();
        } else
        {
            return false;
        }
    }

    public String mailTo()
    {
        if (mMailTo != null)
        {
            return mMailTo;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.mailTo();
        } else
        {
            return "";
        }
    }

    public int maxNumberOfRequestRetries()
    {
        if (mMaxNumberOfRequestRetries != null)
        {
            return mMaxNumberOfRequestRetries.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.maxNumberOfRequestRetries();
        } else
        {
            return 3;
        }
    }

    public ReportingInteractionMode mode()
    {
        if (mMode != null)
        {
            return mMode;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.mode();
        } else
        {
            return ReportingInteractionMode.SILENT;
        }
    }

    public jp.co.yahoo.applicot.sender.HttpSender.Type reportType()
    {
        if (mReportType != null)
        {
            return mReportType;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.reportType();
        } else
        {
            return jp.co.yahoo.applicot.sender.HttpSender.Type.FORM;
        }
    }

    public int resDialogCommentPrompt()
    {
        if (mResDialogCommentPrompt != null)
        {
            return mResDialogCommentPrompt.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogCommentPrompt();
        } else
        {
            return 0;
        }
    }

    public int resDialogEmailPrompt()
    {
        if (mResDialogEmailPrompt != null)
        {
            return mResDialogEmailPrompt.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogEmailPrompt();
        } else
        {
            return 0;
        }
    }

    public int resDialogIcon()
    {
        if (mResDialogIcon != null)
        {
            return mResDialogIcon.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogIcon();
        } else
        {
            return 0x1080027;
        }
    }

    public int resDialogOkToast()
    {
        if (mResDialogOkToast != null)
        {
            return mResDialogOkToast.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogOkToast();
        } else
        {
            return 0;
        }
    }

    public int resDialogText()
    {
        if (mResDialogText != null)
        {
            return mResDialogText.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogText();
        } else
        {
            return 0;
        }
    }

    public int resDialogTitle()
    {
        if (mResDialogTitle != null)
        {
            return mResDialogTitle.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resDialogTitle();
        } else
        {
            return 0;
        }
    }

    public int resNotifIcon()
    {
        if (mResNotifIcon != null)
        {
            return mResNotifIcon.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resNotifIcon();
        } else
        {
            return 0x1080078;
        }
    }

    public int resNotifText()
    {
        if (mResNotifText != null)
        {
            return mResNotifText.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resNotifText();
        } else
        {
            return 0;
        }
    }

    public int resNotifTickerText()
    {
        if (mResNotifTickerText != null)
        {
            return mResNotifTickerText.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resNotifTickerText();
        } else
        {
            return 0;
        }
    }

    public int resNotifTitle()
    {
        if (mResNotifTitle != null)
        {
            return mResNotifTitle.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resNotifTitle();
        } else
        {
            return 0;
        }
    }

    public int resToastText()
    {
        if (mResToastText != null)
        {
            return mResToastText.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.resToastText();
        } else
        {
            return 0;
        }
    }

    public boolean sendReportsInDevMode()
    {
        if (mSendReportsInDevMode != null)
        {
            return mSendReportsInDevMode.booleanValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.sendReportsInDevMode();
        } else
        {
            return true;
        }
    }

    public void setAdditionalDropboxTags(String as[])
    {
        mAdditionalDropboxTags = as;
    }

    public void setAdditionalSharedPreferences(String as[])
    {
        mAdditionalSharedPreferences = as;
    }

    public void setApplicationLogFile(String s)
    {
        mApplicationLogFile = s;
    }

    public void setApplicationLogFileLines(int i)
    {
        mApplicationLogFileLines = Integer.valueOf(i);
    }

    public void setConnectionTimeout(Integer integer)
    {
        mConnectionTimeout = integer;
    }

    public void setCustomReportContent(ReportField areportfield[])
    {
        mCustomReportContent = areportfield;
    }

    public void setDeleteOldUnsentReportsOnApplicationStart(Boolean boolean1)
    {
        mDeleteOldUnsentReportsOnApplicationStart = boolean1;
    }

    public void setDeleteUnapprovedReportsOnApplicationStart(Boolean boolean1)
    {
        mDeleteUnapprovedReportsOnApplicationStart = boolean1;
    }

    public void setDisableSSLCertValidation(boolean flag)
    {
        mDisableSSLCertValidation = Boolean.valueOf(flag);
    }

    public void setDropboxCollectionMinutes(Integer integer)
    {
        mDropboxCollectionMinutes = integer;
    }

    public void setExcludeMatchingSettingsKeys(String as[])
    {
        mExcludeMatchingSettingsKeys = as;
    }

    public void setExcludeMatchingSharedPreferencesKeys(String as[])
    {
        mExcludeMatchingSharedPreferencesKeys = as;
    }

    public void setForceCloseDialogAfterToast(Boolean boolean1)
    {
        mForceCloseDialogAfterToast = boolean1;
    }

    public void setFormUri(String s)
    {
        mFormUri = s;
    }

    public void setFormUriBasicAuthLogin(String s)
    {
        mFormUriBasicAuthLogin = s;
    }

    public void setFormUriBasicAuthPassword(String s)
    {
        mFormUriBasicAuthPassword = s;
    }

    public void setHttpHeaders(Map map)
    {
        mHttpHeaders = map;
    }

    public void setHttpMethod(jp.co.yahoo.applicot.sender.HttpSender.Method method)
    {
        mHttpMethod = method;
    }

    public void setIncludeDropboxSystemTags(Boolean boolean1)
    {
        mIncludeDropboxSystemTags = boolean1;
    }

    public void setLogcatArguments(String as[])
    {
        mLogcatArguments = as;
    }

    public void setLogcatFilterByPid(Boolean boolean1)
    {
        mLogcatFilterByPid = boolean1;
    }

    public void setMailTo(String s)
    {
        mMailTo = s;
    }

    public void setMaxNumberOfRequestRetries(Integer integer)
    {
        mMaxNumberOfRequestRetries = integer;
    }

    public void setMode(ReportingInteractionMode reportinginteractionmode)
        throws ApplicotConfigurationException
    {
        mMode = reportinginteractionmode;
        Applicot.checkCrashResources();
    }

    public void setReportType(jp.co.yahoo.applicot.sender.HttpSender.Type type)
    {
        mReportType = type;
    }

    public void setResDialogCommentPrompt(int i)
    {
        mResDialogCommentPrompt = Integer.valueOf(i);
    }

    public void setResDialogEmailPrompt(int i)
    {
        mResDialogEmailPrompt = Integer.valueOf(i);
    }

    public void setResDialogIcon(int i)
    {
        mResDialogIcon = Integer.valueOf(i);
    }

    public void setResDialogOkToast(int i)
    {
        mResDialogOkToast = Integer.valueOf(i);
    }

    public void setResDialogText(int i)
    {
        mResDialogText = Integer.valueOf(i);
    }

    public void setResDialogTitle(int i)
    {
        mResDialogTitle = Integer.valueOf(i);
    }

    public void setResNotifIcon(int i)
    {
        mResNotifIcon = Integer.valueOf(i);
    }

    public void setResNotifText(int i)
    {
        mResNotifText = Integer.valueOf(i);
    }

    public void setResNotifTickerText(int i)
    {
        mResNotifTickerText = Integer.valueOf(i);
    }

    public void setResNotifTitle(int i)
    {
        mResNotifTitle = Integer.valueOf(i);
    }

    public void setResToastText(int i)
    {
        mResToastText = Integer.valueOf(i);
    }

    public void setSendReportsInDevMode(Boolean boolean1)
    {
        mSendReportsInDevMode = boolean1;
    }

    public void setSharedPreferenceMode(Integer integer)
    {
        mSharedPreferenceMode = integer;
    }

    public void setSharedPreferenceName(String s)
    {
        mSharedPreferenceName = s;
    }

    public void setSocketTimeout(Integer integer)
    {
        mSocketTimeout = integer;
    }

    public int sharedPreferencesMode()
    {
        if (mSharedPreferenceMode != null)
        {
            return mSharedPreferenceMode.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.sharedPreferencesMode();
        } else
        {
            return 0;
        }
    }

    public String sharedPreferencesName()
    {
        if (mSharedPreferenceName != null)
        {
            return mSharedPreferenceName;
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.sharedPreferencesName();
        } else
        {
            return "";
        }
    }

    public int socketTimeout()
    {
        if (mSocketTimeout != null)
        {
            return mSocketTimeout.intValue();
        }
        if (mReportsCrashes != null)
        {
            return mReportsCrashes.socketTimeout();
        } else
        {
            return 5000;
        }
    }
}
