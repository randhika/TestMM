// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;


public class ReportField extends Enum
{

    private static final ReportField $VALUES[];
    public static final ReportField ANDROID_VERSION;
    public static final ReportField APPLICATION_LOG;
    public static final ReportField APPLICOT_ID;
    public static final ReportField APPLICOT_VERSION;
    public static final ReportField APP_MEM_SIZE;
    public static final ReportField APP_VERSION_CODE;
    public static final ReportField APP_VERSION_NAME;
    public static final ReportField AVAILABLE_MEM_SIZE;
    public static final ReportField BCOOKIE;
    public static final ReportField BRAND;
    public static final ReportField BUILD;
    public static final ReportField CRASH_CONFIGURATION;
    public static final ReportField CUSTOM_DATA;
    public static final ReportField DEVICE_FEATURES;
    public static final ReportField DEVICE_ID;
    public static final ReportField DISPLAY;
    public static final ReportField DROPBOX;
    public static final ReportField DUMPSYS_MEMINFO;
    public static final ReportField ENVIRONMENT;
    public static final ReportField EVENTSLOG;
    public static final ReportField FILE_PATH;
    public static final ReportField INITIAL_CONFIGURATION;
    public static final ReportField INSTALLATION_ID;
    public static final ReportField IS_SILENT;
    public static final ReportField LOGCAT;
    public static final ReportField MEDIA_CODEC_LIST;
    public static final ReportField PACKAGE_NAME;
    public static final ReportField PHONE_MODEL;
    public static final ReportField PRODUCT;
    public static final ReportField RADIOLOG;
    public static final ReportField REPORT_ID;
    public static final ReportField SETTINGS_GLOBAL;
    public static final ReportField SETTINGS_SECURE;
    public static final ReportField SETTINGS_SYSTEM;
    public static final ReportField SHARED_PREFERENCES;
    public static final ReportField STACK_TRACE;
    public static final ReportField THREAD_DETAILS;
    public static final ReportField TOTAL_MEM_SIZE;
    public static final ReportField USER_APP_START_DATE;
    public static final ReportField USER_COMMENT;
    public static final ReportField USER_CRASH_DATE;
    public static final ReportField USER_EMAIL;
    public static final ReportField USER_IP;
    public static final ReportField YJDN_APPID;

    private ReportField(String s, int i)
    {
        super(s, i);
    }


    public static ReportField valueOf(String s)
    {
        return (ReportField)Enum.valueOf(jp/co/yahoo/applicot/ReportField, s);
    }

    public static ReportField[] values()
    {
        return (ReportField[])$VALUES.clone();
    }

    public boolean containsKeyValuePairs()
    {
        return false;
    }

    static 
    {
        REPORT_ID = new ReportField("REPORT_ID", 0);
        BCOOKIE = new ReportField("BCOOKIE", 1);
        YJDN_APPID = new ReportField("YJDN_APPID", 2);
        APPLICOT_ID = new ReportField("APPLICOT_ID", 3);
        APPLICOT_VERSION = new ReportField("APPLICOT_VERSION", 4);
        APP_VERSION_CODE = new ReportField("APP_VERSION_CODE", 5);
        APP_VERSION_NAME = new ReportField("APP_VERSION_NAME", 6);
        PACKAGE_NAME = new ReportField("PACKAGE_NAME", 7);
        FILE_PATH = new ReportField("FILE_PATH", 8);
        PHONE_MODEL = new ReportField("PHONE_MODEL", 9);
        ANDROID_VERSION = new ReportField("ANDROID_VERSION", 10);
        BUILD = new ReportField("BUILD", 11) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        BRAND = new ReportField("BRAND", 12);
        PRODUCT = new ReportField("PRODUCT", 13);
        TOTAL_MEM_SIZE = new ReportField("TOTAL_MEM_SIZE", 14);
        AVAILABLE_MEM_SIZE = new ReportField("AVAILABLE_MEM_SIZE", 15);
        APP_MEM_SIZE = new ReportField("APP_MEM_SIZE", 16);
        CUSTOM_DATA = new ReportField("CUSTOM_DATA", 17) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        STACK_TRACE = new ReportField("STACK_TRACE", 18);
        INITIAL_CONFIGURATION = new ReportField("INITIAL_CONFIGURATION", 19) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        CRASH_CONFIGURATION = new ReportField("CRASH_CONFIGURATION", 20) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        DISPLAY = new ReportField("DISPLAY", 21) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        USER_COMMENT = new ReportField("USER_COMMENT", 22);
        USER_APP_START_DATE = new ReportField("USER_APP_START_DATE", 23);
        USER_CRASH_DATE = new ReportField("USER_CRASH_DATE", 24);
        DUMPSYS_MEMINFO = new ReportField("DUMPSYS_MEMINFO", 25);
        DROPBOX = new ReportField("DROPBOX", 26);
        LOGCAT = new ReportField("LOGCAT", 27);
        EVENTSLOG = new ReportField("EVENTSLOG", 28);
        RADIOLOG = new ReportField("RADIOLOG", 29);
        IS_SILENT = new ReportField("IS_SILENT", 30);
        DEVICE_ID = new ReportField("DEVICE_ID", 31);
        INSTALLATION_ID = new ReportField("INSTALLATION_ID", 32);
        USER_EMAIL = new ReportField("USER_EMAIL", 33);
        DEVICE_FEATURES = new ReportField("DEVICE_FEATURES", 34) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        ENVIRONMENT = new ReportField("ENVIRONMENT", 35) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        SETTINGS_SYSTEM = new ReportField("SETTINGS_SYSTEM", 36) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        SETTINGS_SECURE = new ReportField("SETTINGS_SECURE", 37) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        SETTINGS_GLOBAL = new ReportField("SETTINGS_GLOBAL", 38) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        SHARED_PREFERENCES = new ReportField("SHARED_PREFERENCES", 39) {

            public boolean containsKeyValuePairs()
            {
                return true;
            }

        };
        APPLICATION_LOG = new ReportField("APPLICATION_LOG", 40);
        MEDIA_CODEC_LIST = new ReportField("MEDIA_CODEC_LIST", 41);
        THREAD_DETAILS = new ReportField("THREAD_DETAILS", 42);
        USER_IP = new ReportField("USER_IP", 43);
        ReportField areportfield[] = new ReportField[44];
        areportfield[0] = REPORT_ID;
        areportfield[1] = BCOOKIE;
        areportfield[2] = YJDN_APPID;
        areportfield[3] = APPLICOT_ID;
        areportfield[4] = APPLICOT_VERSION;
        areportfield[5] = APP_VERSION_CODE;
        areportfield[6] = APP_VERSION_NAME;
        areportfield[7] = PACKAGE_NAME;
        areportfield[8] = FILE_PATH;
        areportfield[9] = PHONE_MODEL;
        areportfield[10] = ANDROID_VERSION;
        areportfield[11] = BUILD;
        areportfield[12] = BRAND;
        areportfield[13] = PRODUCT;
        areportfield[14] = TOTAL_MEM_SIZE;
        areportfield[15] = AVAILABLE_MEM_SIZE;
        areportfield[16] = APP_MEM_SIZE;
        areportfield[17] = CUSTOM_DATA;
        areportfield[18] = STACK_TRACE;
        areportfield[19] = INITIAL_CONFIGURATION;
        areportfield[20] = CRASH_CONFIGURATION;
        areportfield[21] = DISPLAY;
        areportfield[22] = USER_COMMENT;
        areportfield[23] = USER_APP_START_DATE;
        areportfield[24] = USER_CRASH_DATE;
        areportfield[25] = DUMPSYS_MEMINFO;
        areportfield[26] = DROPBOX;
        areportfield[27] = LOGCAT;
        areportfield[28] = EVENTSLOG;
        areportfield[29] = RADIOLOG;
        areportfield[30] = IS_SILENT;
        areportfield[31] = DEVICE_ID;
        areportfield[32] = INSTALLATION_ID;
        areportfield[33] = USER_EMAIL;
        areportfield[34] = DEVICE_FEATURES;
        areportfield[35] = ENVIRONMENT;
        areportfield[36] = SETTINGS_SYSTEM;
        areportfield[37] = SETTINGS_SECURE;
        areportfield[38] = SETTINGS_GLOBAL;
        areportfield[39] = SHARED_PREFERENCES;
        areportfield[40] = APPLICATION_LOG;
        areportfield[41] = MEDIA_CODEC_LIST;
        areportfield[42] = THREAD_DETAILS;
        areportfield[43] = USER_IP;
        $VALUES = areportfield;
    }
}
