// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.annotation;

import java.lang.annotation.Annotation;
import jp.co.yahoo.applicot.ReportField;
import jp.co.yahoo.applicot.ReportingInteractionMode;

public interface ReportsCrashes
    extends Annotation
{

    public abstract String[] additionalDropBoxTags();

    public abstract String[] additionalSharedPreferences();

    public abstract String applicationLogFile();

    public abstract int applicationLogFileLines();

    public abstract int connectionTimeout();

    public abstract ReportField[] customReportContent();

    public abstract boolean deleteOldUnsentReportsOnApplicationStart();

    public abstract boolean deleteUnapprovedReportsOnApplicationStart();

    public abstract boolean disableSSLCertValidation();

    public abstract int dropboxCollectionMinutes();

    public abstract String[] excludeMatchingSettingsKeys();

    public abstract String[] excludeMatchingSharedPreferencesKeys();

    public abstract boolean forceCloseDialogAfterToast();

    public abstract String formUri();

    public abstract String formUriBasicAuthLogin();

    public abstract String formUriBasicAuthPassword();

    public abstract jp.co.yahoo.applicot.sender.HttpSender.Method httpMethod();

    public abstract boolean includeDropBoxSystemTags();

    public abstract String[] logcatArguments();

    public abstract boolean logcatFilterByPid();

    public abstract String mailTo();

    public abstract int maxNumberOfRequestRetries();

    public abstract ReportingInteractionMode mode();

    public abstract jp.co.yahoo.applicot.sender.HttpSender.Type reportType();

    public abstract int resDialogCommentPrompt();

    public abstract int resDialogEmailPrompt();

    public abstract int resDialogIcon();

    public abstract int resDialogOkToast();

    public abstract int resDialogText();

    public abstract int resDialogTitle();

    public abstract int resNotifIcon();

    public abstract int resNotifText();

    public abstract int resNotifTickerText();

    public abstract int resNotifTitle();

    public abstract int resToastText();

    public abstract boolean sendReportsInDevMode();

    public abstract int sharedPreferencesMode();

    public abstract String sharedPreferencesName();

    public abstract int socketTimeout();
}
