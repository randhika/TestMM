// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Looper;
import android.os.Process;
import android.text.format.Time;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.applicot.annotation.ReportsCrashes;
import jp.co.yahoo.applicot.collector.ConfigurationCollector;
import jp.co.yahoo.applicot.collector.CrashReportData;
import jp.co.yahoo.applicot.collector.CrashReportDataFactory;
import jp.co.yahoo.applicot.log.ApplicotLog;
import jp.co.yahoo.applicot.sender.EmailIntentSender;
import jp.co.yahoo.applicot.sender.HttpSender;
import jp.co.yahoo.applicot.sender.ReportSender;
import jp.co.yahoo.applicot.util.PackageManagerWrapper;
import jp.co.yahoo.applicot.util.ToastSender;

// Referenced classes of package jp.co.yahoo.applicot:
//            CrashReportFileNameParser, CrashReportFinder, Applicot, ApplicotConfiguration, 
//            ReportingInteractionMode, ReportField, ApplicotConstants, CrashReportDialog, 
//            CrashReportPersister, SendWorker

public class ErrorReporter
    implements Thread.UncaughtExceptionHandler
{

    private static int mNotificationCounter = 0;
    private static boolean toastWaitEnded = true;
    private Thread brokenThread;
    private final CrashReportDataFactory crashReportDataFactory;
    private boolean enabled;
    private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
    private transient Activity lastActivityCreated;
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler mDfltExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    private final List mReportSenders = new ArrayList();
    private final SharedPreferences prefs;
    private Throwable unhandledThrowable;

    ErrorReporter(Context context, SharedPreferences sharedpreferences, boolean flag)
    {
        enabled = false;
        mContext = context;
        prefs = sharedpreferences;
        enabled = flag;
        String s = ConfigurationCollector.collectConfiguration(mContext);
        Time time = new Time();
        time.setToNow();
        crashReportDataFactory = new CrashReportDataFactory(mContext, sharedpreferences, time, s);
        Thread.setDefaultUncaughtExceptionHandler(this);
        checkReportsOnApplicationStart();
    }

    private boolean containsOnlySilentOrApprovedReports(String as[])
    {
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            if (!fileNameParser.isApproved(s))
            {
                return false;
            }
        }

        return true;
    }

    private void deletePendingReports(boolean flag, boolean flag1, int i)
    {
        String as[] = (new CrashReportFinder(mContext)).getCrashReportFiles();
        Arrays.sort(as);
        if (as != null)
        {
            for (int j = 0; j < as.length - i; j++)
            {
                String s = as[j];
                boolean flag2 = fileNameParser.isApproved(s);
                if ((!flag2 || !flag) && (flag2 || !flag1))
                {
                    continue;
                }
                File file = new File(mContext.getFilesDir(), s);
                Applicot.log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Deleting file ").append(s).toString());
                if (!file.delete())
                {
                    Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Could not delete report : ").append(file).toString());
                }
            }

        }
    }

    private void endApplication()
    {
        if (Applicot.getConfig().mode() == ReportingInteractionMode.SILENT || Applicot.getConfig().mode() == ReportingInteractionMode.TOAST && Applicot.getConfig().forceCloseDialogAfterToast())
        {
            mDfltExceptionHandler.uncaughtException(brokenThread, unhandledThrowable);
            return;
        }
        Log.e(Applicot.LOG_TAG, (new StringBuilder()).append(mContext.getPackageName()).append(" fatal error : ").append(unhandledThrowable.getMessage()).toString(), unhandledThrowable);
        if (lastActivityCreated != null)
        {
            Log.i(Applicot.LOG_TAG, "Finishing the last Activity prior to killing the Process");
            lastActivityCreated.finish();
            Log.i(Applicot.LOG_TAG, (new StringBuilder()).append("Finished ").append(lastActivityCreated.getClass()).toString());
            lastActivityCreated = null;
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    private String getLatestNonSilentReport(String as[])
    {
        if (as != null && as.length > 0)
        {
            for (int i = -1 + as.length; i >= 0; i--)
            {
                if (!fileNameParser.isSilent(as[i]))
                {
                    return as[i];
                }
            }

            return as[-1 + as.length];
        } else
        {
            return null;
        }
    }

    private String getReportFileName(CrashReportData crashreportdata)
    {
        Time time = new Time();
        time.setToNow();
        long l = time.toMillis(false);
        String s = crashreportdata.getProperty(ReportField.IS_SILENT);
        StringBuilder stringbuilder = (new StringBuilder()).append("").append(l);
        String s1;
        if (s != null)
        {
            s1 = ApplicotConstants.SILENT_SUFFIX;
        } else
        {
            s1 = "";
        }
        return stringbuilder.append(s1).append(".stacktrace").toString();
    }

    private void handleException(Throwable throwable, ReportingInteractionMode reportinginteractionmode, boolean flag, final boolean endApplication)
    {
        final boolean showDirectDialog = true;
        if (!enabled)
        {
            return;
        }
        boolean flag1 = false;
        boolean flag2;
        CrashReportData crashreportdata;
        final String reportFileName;
        SendWorker sendworker;
        final SendWorker worker;
        if (reportinginteractionmode == null)
        {
            reportinginteractionmode = Applicot.getConfig().mode();
        } else
        {
            ReportingInteractionMode reportinginteractionmode1 = ReportingInteractionMode.SILENT;
            flag1 = false;
            if (reportinginteractionmode == reportinginteractionmode1)
            {
                ReportingInteractionMode reportinginteractionmode2 = Applicot.getConfig().mode();
                ReportingInteractionMode reportinginteractionmode3 = ReportingInteractionMode.SILENT;
                flag1 = false;
                if (reportinginteractionmode2 != reportinginteractionmode3)
                {
                    flag1 = true;
                }
            }
        }
        if (throwable == null)
        {
            throwable = new Exception("Report requested by developer");
        }
        if (reportinginteractionmode == ReportingInteractionMode.TOAST || Applicot.getConfig().resToastText() != 0 && (reportinginteractionmode == ReportingInteractionMode.NOTIFICATION || reportinginteractionmode == ReportingInteractionMode.DIALOG))
        {
            flag2 = showDirectDialog;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            (new Thread() {

                final ErrorReporter this$0;

                public void run()
                {
                    Looper.prepare();
                    ToastSender.sendToast(mContext, Applicot.getConfig().resToastText(), 1);
                    Looper.loop();
                }

            
            {
                this$0 = ErrorReporter.this;
                super();
            }
            }).start();
        }
        crashreportdata = crashReportDataFactory.createCrashData(throwable, flag, brokenThread);
        reportFileName = getReportFileName(crashreportdata);
        saveCrashReportFile(reportFileName, crashreportdata);
        if (reportinginteractionmode == ReportingInteractionMode.SILENT || reportinginteractionmode == ReportingInteractionMode.TOAST || prefs.getBoolean("applicot.alwaysaccept", false))
        {
            Log.d(Applicot.LOG_TAG, "About to start ReportSenderWorker from #handleException");
            sendworker = startSendingReports(flag1, showDirectDialog);
        } else
        {
            ReportingInteractionMode reportinginteractionmode4 = ReportingInteractionMode.NOTIFICATION;
            sendworker = null;
            if (reportinginteractionmode == reportinginteractionmode4)
            {
                Log.d(Applicot.LOG_TAG, "Notification will be created on application start.");
                sendworker = null;
            }
        }
        if (flag2)
        {
            toastWaitEnded = false;
            (new Thread() {

                final ErrorReporter this$0;

                public void run()
                {
                    Time time = new Time();
                    Time time1 = new Time();
                    time.setToNow();
                    long l = time.toMillis(false);
                    long l1 = 0L;
                    while (l1 < 3000L) 
                    {
                        try
                        {
                            Thread.sleep(3000L);
                        }
                        catch (InterruptedException interruptedexception)
                        {
                            Log.d(Applicot.LOG_TAG, "Interrupted while waiting for Toast to end.", interruptedexception);
                        }
                        time1.setToNow();
                        l1 = time1.toMillis(false) - l;
                    }
                    ErrorReporter.toastWaitEnded = true;
                }

            
            {
                this$0 = ErrorReporter.this;
                super();
            }
            }).start();
        }
        worker = sendworker;
        if (reportinginteractionmode != ReportingInteractionMode.DIALOG || prefs.getBoolean("applicot.alwaysaccept", false))
        {
            showDirectDialog = false;
        }
        (new Thread() {

            final ErrorReporter this$0;
            final boolean val$endApplication;
            final String val$reportFileName;
            final boolean val$showDirectDialog;
            final SendWorker val$worker;

            public void run()
            {
                Log.d(Applicot.LOG_TAG, "Waiting for Toast + worker...");
                while (!ErrorReporter.toastWaitEnded || worker != null && worker.isAlive()) 
                {
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException interruptedexception)
                    {
                        Log.e(Applicot.LOG_TAG, "Error : ", interruptedexception);
                    }
                }
                if (showDirectDialog)
                {
                    Log.d(Applicot.LOG_TAG, "About to create DIALOG from #handleException");
                    notifyDialog(reportFileName);
                }
                Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Wait for Toast + worker ended. Kill Application ? ").append(endApplication).toString());
                if (endApplication)
                {
                    ErrorReporter.this.endApplication();
                }
            }

            
            {
                this$0 = ErrorReporter.this;
                worker = sendworker;
                showDirectDialog = flag;
                reportFileName = s;
                endApplication = flag1;
                super();
            }
        }).start();
    }

    private void notifySendReport(String s)
    {
        NotificationManager notificationmanager = (NotificationManager)mContext.getSystemService("notification");
        ApplicotConfiguration applicotconfiguration = Applicot.getConfig();
        Notification notification = new Notification(applicotconfiguration.resNotifIcon(), mContext.getText(applicotconfiguration.resNotifTickerText()), System.currentTimeMillis());
        CharSequence charsequence = mContext.getText(applicotconfiguration.resNotifTitle());
        CharSequence charsequence1 = mContext.getText(applicotconfiguration.resNotifText());
        Intent intent = new Intent(mContext, jp/co/yahoo/applicot/CrashReportDialog);
        Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Creating Notification for ").append(s).toString());
        intent.putExtra("REPORT_FILE_NAME", s);
        Context context = mContext;
        int i = mNotificationCounter;
        mNotificationCounter = i + 1;
        PendingIntent pendingintent = PendingIntent.getActivity(context, i, intent, 0x8000000);
        notification.setLatestEventInfo(mContext, charsequence, charsequence1, pendingintent);
        Intent intent1 = new Intent(mContext, jp/co/yahoo/applicot/CrashReportDialog);
        intent1.putExtra("FORCE_CANCEL", true);
        notification.deleteIntent = PendingIntent.getActivity(mContext, -1, intent1, 0);
        notificationmanager.notify(666, notification);
    }

    private void saveCrashReportFile(String s, CrashReportData crashreportdata)
    {
        try
        {
            Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Writing crash report file ").append(s).append(".").toString());
            (new CrashReportPersister(mContext)).store(crashreportdata, s);
            return;
        }
        catch (Exception exception)
        {
            Log.e(Applicot.LOG_TAG, "An error occurred while writing the report file...", exception);
        }
    }

    public void addReportSender(ReportSender reportsender)
    {
        mReportSenders.add(reportsender);
    }

    public void checkReportsOnApplicationStart()
    {
        long l = prefs.getInt("applicot.lastVersionNr", 0);
        PackageInfo packageinfo = (new PackageManagerWrapper(mContext)).getPackageInfo();
        boolean flag;
        CrashReportFinder crashreportfinder;
        String as[];
        if (packageinfo != null && (long)packageinfo.versionCode > l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (Applicot.getConfig().deleteOldUnsentReportsOnApplicationStart())
            {
                deletePendingReports();
            }
            android.content.SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("applicot.lastVersionNr", packageinfo.versionCode);
            editor.commit();
        }
        if ((Applicot.getConfig().mode() == ReportingInteractionMode.NOTIFICATION || Applicot.getConfig().mode() == ReportingInteractionMode.DIALOG) && Applicot.getConfig().deleteUnapprovedReportsOnApplicationStart())
        {
            deletePendingNonApprovedReports(true);
        }
        crashreportfinder = new CrashReportFinder(mContext);
        as = crashreportfinder.getCrashReportFiles();
        if (as != null && as.length > 0)
        {
            ReportingInteractionMode reportinginteractionmode = Applicot.getConfig().mode();
            String as1[] = crashreportfinder.getCrashReportFiles();
            boolean flag1 = containsOnlySilentOrApprovedReports(as1);
            if (reportinginteractionmode == ReportingInteractionMode.SILENT || reportinginteractionmode == ReportingInteractionMode.TOAST || flag1 && (reportinginteractionmode == ReportingInteractionMode.NOTIFICATION || reportinginteractionmode == ReportingInteractionMode.DIALOG))
            {
                if (reportinginteractionmode == ReportingInteractionMode.TOAST && !flag1)
                {
                    ToastSender.sendToast(mContext, Applicot.getConfig().resToastText(), 1);
                }
                Log.v(Applicot.LOG_TAG, "About to start ReportSenderWorker from #checkReportOnApplicationStart");
                startSendingReports(false, false);
            } else
            {
                if (Applicot.getConfig().mode() == ReportingInteractionMode.NOTIFICATION)
                {
                    notifySendReport(getLatestNonSilentReport(as1));
                    return;
                }
                if (Applicot.getConfig().mode() == ReportingInteractionMode.DIALOG)
                {
                    return;
                }
            }
        }
    }

    void deletePendingNonApprovedReports(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        deletePendingReports(false, true, i);
    }

    void deletePendingReports()
    {
        deletePendingReports(true, true, 0);
    }

    public String getCustomData(String s)
    {
        return crashReportDataFactory.getCustomData(s);
    }

    public void handleException(Throwable throwable)
    {
        handleException(throwable, Applicot.getConfig().mode(), false, false);
    }

    public void handleException(Throwable throwable, boolean flag)
    {
        handleException(throwable, Applicot.getConfig().mode(), false, flag);
    }

    public void handleSilentException(Throwable throwable)
    {
        if (enabled)
        {
            handleException(throwable, ReportingInteractionMode.SILENT, true, false);
            Log.d(Applicot.LOG_TAG, "Applicot sent Silent report.");
            return;
        } else
        {
            Log.d(Applicot.LOG_TAG, "Applicot is disabled. Silent report not sent.");
            return;
        }
    }

    void notifyDialog(String s)
    {
        Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Creating Dialog for ").append(s).toString());
        Intent intent = new Intent(mContext, jp/co/yahoo/applicot/CrashReportDialog);
        intent.putExtra("REPORT_FILE_NAME", s);
        intent.setFlags(0x10000000);
        mContext.startActivity(intent);
    }

    public String putCustomData(String s, String s1)
    {
        return crashReportDataFactory.putCustomData(s, s1);
    }

    public void removeAllReportSenders()
    {
        mReportSenders.clear();
    }

    public String removeCustomData(String s)
    {
        return crashReportDataFactory.removeCustomData(s);
    }

    public void removeReportSender(ReportSender reportsender)
    {
        mReportSenders.remove(reportsender);
    }

    public void removeReportSenders(Class class1)
    {
        if (jp/co/yahoo/applicot/sender/ReportSender.isAssignableFrom(class1))
        {
            Iterator iterator = mReportSenders.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                ReportSender reportsender = (ReportSender)iterator.next();
                if (class1.isInstance(reportsender))
                {
                    mReportSenders.remove(reportsender);
                }
            } while (true);
        }
    }

    public void setDefaultReportSenders()
    {
        ApplicotConfiguration applicotconfiguration = Applicot.getConfig();
        Context context = Applicot.getContext();
        removeAllReportSenders();
        if (!"".equals(applicotconfiguration.mailTo()))
        {
            Log.w(Applicot.LOG_TAG, (new StringBuilder()).append(context.getPackageName()).append(" reports will be sent by email (if accepted by user).").toString());
            setReportSender(new EmailIntentSender(context));
        } else
        {
            if (!(new PackageManagerWrapper(context)).hasPermission("android.permission.INTERNET"))
            {
                Log.e(Applicot.LOG_TAG, (new StringBuilder()).append(context.getPackageName()).append(" should be granted permission ").append("android.permission.INTERNET").append(" if you want your crash reports to be sent. If you don't want to add this permission to your application you can also enable sending reports by email. If this is your will then provide your email address in @ReportsCrashes(mailTo=\"your.account@domain.com\"").toString());
                return;
            }
            if (applicotconfiguration.formUri() != null && !"".equals(applicotconfiguration.formUri()))
            {
                setReportSender(new HttpSender(Applicot.getConfig().httpMethod(), Applicot.getConfig().reportType(), null));
                return;
            }
        }
    }

    public void setEnabled(boolean flag)
    {
        String s = Applicot.LOG_TAG;
        StringBuilder stringbuilder = (new StringBuilder()).append("Applicot is ");
        String s1;
        if (flag)
        {
            s1 = "enabled";
        } else
        {
            s1 = "disabled";
        }
        Log.i(s, stringbuilder.append(s1).append(" for ").append(mContext.getPackageName()).toString());
        enabled = flag;
    }

    public void setReportSender(ReportSender reportsender)
    {
        removeAllReportSenders();
        addReportSender(reportsender);
    }

    SendWorker startSendingReports(boolean flag, boolean flag1)
    {
        SendWorker sendworker = new SendWorker(mContext, mReportSenders, flag, flag1);
        sendworker.start();
        return sendworker;
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        if (enabled)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        if (mDfltExceptionHandler != null)
        {
            Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Applicot is disabled for ").append(mContext.getPackageName()).append(" - forwarding uncaught Exception on to default ExceptionHandler").toString());
            mDfltExceptionHandler.uncaughtException(thread, throwable);
            return;
        }
        try
        {
            Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Applicot is disabled for ").append(mContext.getPackageName()).append(" - no default ExceptionHandler").toString());
            return;
        }
        catch (Throwable throwable1) { }
        if (mDfltExceptionHandler != null)
        {
            mDfltExceptionHandler.uncaughtException(thread, throwable);
            return;
        }
        break MISSING_BLOCK_LABEL_203;
        brokenThread = thread;
        unhandledThrowable = throwable;
        Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Applicot caught a ").append(throwable.getClass().getSimpleName()).append(" exception for ").append(mContext.getPackageName()).append(". Building report.").toString());
        handleException(throwable, Applicot.getConfig().mode(), false, true);
    }





/*
    static boolean access$102(boolean flag)
    {
        toastWaitEnded = flag;
        return flag;
    }

*/

}
