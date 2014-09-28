// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;


// Referenced classes of package jp.co.yahoo.applicot:
//            ReportField

public final class ApplicotConstants
{

    static final String APPROVED_SUFFIX = "-approved";
    public static final String DEFAULT_API_URI = "https://applicot.yahooapis.jp/v1/receive?appid=yjdn_proxy_test06";
    public static final String DEFAULT_APPLICATION_LOGFILE = "";
    public static final int DEFAULT_APPLICATION_LOGFILE_LINES = 3;
    public static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 3000;
    public static final boolean DEFAULT_DELETE_OLD_UNSENT_REPORTS_ON_APPLICATION_START = true;
    public static final boolean DEFAULT_DELETE_UNAPPROVED_REPORTS_ON_APPLICATION_START = true;
    public static final int DEFAULT_DIALOG_ICON = 0x1080027;
    public static final boolean DEFAULT_DISABLE_SSL_CERT_VALIDATION = false;
    public static final int DEFAULT_DROPBOX_COLLECTION_MINUTES = 5;
    public static final boolean DEFAULT_FORCE_CLOSE_DIALOG_AFTER_TOAST = false;
    public static final boolean DEFAULT_INCLUDE_DROPBOX_SYSTEM_TAGS = false;
    public static final boolean DEFAULT_LOGCAT_FILTER_BY_PID = false;
    public static final int DEFAULT_LOGCAT_LINES = 3;
    public static final ReportField DEFAULT_MAIL_REPORT_FIELDS[];
    public static final int DEFAULT_MAX_NUMBER_OF_REQUEST_RETRIES = 3;
    public static final int DEFAULT_NOTIFICATION_ICON = 0x1080078;
    public static final ReportField DEFAULT_REPORT_FIELDS[];
    public static final int DEFAULT_RES_VALUE = 0;
    public static final boolean DEFAULT_SEND_REPORTS_IN_DEV_MODE = true;
    public static final int DEFAULT_SHARED_PREFERENCES_MODE = 0;
    public static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    public static final String DEFAULT_STRING_VALUE = "";
    protected static final String EXTRA_FORCE_CANCEL = "FORCE_CANCEL";
    protected static final String EXTRA_REPORT_FILE_NAME = "REPORT_FILE_NAME";
    static final int MAX_SEND_REPORTS = 5;
    static final int NOTIF_CRASH_ID = 666;
    public static final String NULL_VALUE = "Applicot-NULL-STRING";
    public static final String REPORTFILE_EXTENSION = ".stacktrace";
    static final String SILENT_SUFFIX;
    static final int TOAST_WAIT_DURATION = 3000;
    public static final String VERSION = "1.0.0";

    public ApplicotConstants()
    {
    }

    static 
    {
        SILENT_SUFFIX = (new StringBuilder()).append("-").append(ReportField.IS_SILENT).toString();
        ReportField areportfield[] = new ReportField[7];
        areportfield[0] = ReportField.USER_COMMENT;
        areportfield[1] = ReportField.ANDROID_VERSION;
        areportfield[2] = ReportField.APP_VERSION_NAME;
        areportfield[3] = ReportField.BRAND;
        areportfield[4] = ReportField.PHONE_MODEL;
        areportfield[5] = ReportField.CUSTOM_DATA;
        areportfield[6] = ReportField.STACK_TRACE;
        DEFAULT_MAIL_REPORT_FIELDS = areportfield;
        ReportField areportfield1[] = new ReportField[33];
        areportfield1[0] = ReportField.REPORT_ID;
        areportfield1[1] = ReportField.BCOOKIE;
        areportfield1[2] = ReportField.APPLICOT_ID;
        areportfield1[3] = ReportField.APPLICOT_VERSION;
        areportfield1[4] = ReportField.APP_VERSION_CODE;
        areportfield1[5] = ReportField.APP_VERSION_NAME;
        areportfield1[6] = ReportField.PACKAGE_NAME;
        areportfield1[7] = ReportField.FILE_PATH;
        areportfield1[8] = ReportField.PHONE_MODEL;
        areportfield1[9] = ReportField.BRAND;
        areportfield1[10] = ReportField.PRODUCT;
        areportfield1[11] = ReportField.ANDROID_VERSION;
        areportfield1[12] = ReportField.BUILD;
        areportfield1[13] = ReportField.APP_MEM_SIZE;
        areportfield1[14] = ReportField.CUSTOM_DATA;
        areportfield1[15] = ReportField.IS_SILENT;
        areportfield1[16] = ReportField.STACK_TRACE;
        areportfield1[17] = ReportField.INITIAL_CONFIGURATION;
        areportfield1[18] = ReportField.CRASH_CONFIGURATION;
        areportfield1[19] = ReportField.DISPLAY;
        areportfield1[20] = ReportField.USER_COMMENT;
        areportfield1[21] = ReportField.USER_EMAIL;
        areportfield1[22] = ReportField.USER_APP_START_DATE;
        areportfield1[23] = ReportField.USER_CRASH_DATE;
        areportfield1[24] = ReportField.DUMPSYS_MEMINFO;
        areportfield1[25] = ReportField.LOGCAT;
        areportfield1[26] = ReportField.INSTALLATION_ID;
        areportfield1[27] = ReportField.DEVICE_FEATURES;
        areportfield1[28] = ReportField.ENVIRONMENT;
        areportfield1[29] = ReportField.SHARED_PREFERENCES;
        areportfield1[30] = ReportField.SETTINGS_SYSTEM;
        areportfield1[31] = ReportField.SETTINGS_SECURE;
        areportfield1[32] = ReportField.SETTINGS_GLOBAL;
        DEFAULT_REPORT_FIELDS = areportfield1;
    }
}
