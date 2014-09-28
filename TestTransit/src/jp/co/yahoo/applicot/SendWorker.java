// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.applicot.collector.CrashReportData;
import jp.co.yahoo.applicot.sender.ReportSender;
import jp.co.yahoo.applicot.sender.ReportSenderException;

// Referenced classes of package jp.co.yahoo.applicot:
//            CrashReportFileNameParser, Applicot, CrashReportFinder, CrashReportPersister, 
//            ApplicotConfiguration

final class SendWorker extends Thread
{

    private final boolean approvePendingReports;
    private final Context context;
    private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
    private final List reportSenders;
    private final boolean sendOnlySilentReports;

    public SendWorker(Context context1, List list, boolean flag, boolean flag1)
    {
        context = context1;
        reportSenders = list;
        sendOnlySilentReports = flag;
        approvePendingReports = flag1;
    }

    private void approvePendingReports()
    {
        Log.d(Applicot.LOG_TAG, "Mark all pending reports as approved.");
        String as[] = (new CrashReportFinder(context)).getCrashReportFiles();
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            if (fileNameParser.isApproved(s))
            {
                continue;
            }
            File file = new File(context.getFilesDir(), s);
            String s1 = s.replace(".stacktrace", "-approved.stacktrace");
            File file1 = new File(context.getFilesDir(), s1);
            if (!file.renameTo(file1))
            {
                Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Could not rename approved report from ").append(file).append(" to ").append(file1).toString());
            }
        }

    }

    private void checkAndSendReports(Context context1, boolean flag)
    {
        String as[];
        int i;
        int j;
        int k;
        Log.d(Applicot.LOG_TAG, "#checkAndSendReports - start");
        as = (new CrashReportFinder(context1)).getCrashReportFiles();
        Arrays.sort(as);
        i = 0;
        j = as.length;
        k = 0;
_L5:
        if (k >= j) goto _L2; else goto _L1
_L1:
        String s = as[k];
        if (!flag || fileNameParser.isSilent(s)) goto _L4; else goto _L3
_L3:
        k++;
          goto _L5
_L4:
        if (i < 5) goto _L6; else goto _L2
_L2:
        Log.d(Applicot.LOG_TAG, "#checkAndSendReports - finish");
        return;
_L6:
        Log.i(Applicot.LOG_TAG, (new StringBuilder()).append("Sending file ").append(s).toString());
        sendCrashReport((new CrashReportPersister(context1)).load(s));
        deleteFile(context1, s);
_L7:
        i++;
          goto _L3
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Failed to send crash reports for ").append(s).toString(), runtimeexception);
        deleteFile(context1, s);
          goto _L2
        IOException ioexception;
        ioexception;
        Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Failed to load crash report for ").append(s).toString(), ioexception);
        deleteFile(context1, s);
          goto _L2
        ReportSenderException reportsenderexception;
        reportsenderexception;
        Log.e(Applicot.LOG_TAG, (new StringBuilder()).append("Failed to send crash report for ").append(s).toString(), reportsenderexception);
          goto _L7
    }

    private void deleteFile(Context context1, String s)
    {
        if (!context1.deleteFile(s))
        {
            Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Could not delete error report : ").append(s).toString());
        }
    }

    private void sendCrashReport(CrashReportData crashreportdata)
        throws ReportSenderException
    {
        if (Applicot.isDebuggable() && !Applicot.getConfig().sendReportsInDevMode()) goto _L2; else goto _L1
_L1:
        boolean flag;
        Iterator iterator;
        flag = false;
        iterator = reportSenders.iterator();
_L3:
        ReportSender reportsender;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        reportsender = (ReportSender)iterator.next();
        reportsender.send(crashreportdata);
        flag = true;
        continue; /* Loop/switch isn't completed */
        ReportSenderException reportsenderexception;
        reportsenderexception;
        if (!flag)
        {
            throw reportsenderexception;
        }
        Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("ReportSender of class ").append(reportsender.getClass().getName()).append(" failed but other senders completed their task. Applicot will not send this report again.").toString());
        if (true) goto _L3; else goto _L2
_L2:
    }

    public void run()
    {
        if (approvePendingReports)
        {
            approvePendingReports();
        }
        checkAndSendReports(context, sendOnlySilentReports);
    }
}
