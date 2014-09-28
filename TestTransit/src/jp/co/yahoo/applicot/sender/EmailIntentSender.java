// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;

import android.content.Context;
import android.content.Intent;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.ApplicotConfiguration;
import jp.co.yahoo.applicot.ApplicotConstants;
import jp.co.yahoo.applicot.ReportField;
import jp.co.yahoo.applicot.collector.CrashReportData;

// Referenced classes of package jp.co.yahoo.applicot.sender:
//            ReportSender, ReportSenderException

public class EmailIntentSender
    implements ReportSender
{

    private final Context mContext;

    public EmailIntentSender(Context context)
    {
        mContext = context;
    }

    private String buildBody(CrashReportData crashreportdata)
    {
        ReportField areportfield[] = Applicot.getConfig().customReportContent();
        if (areportfield.length == 0)
        {
            areportfield = ApplicotConstants.DEFAULT_MAIL_REPORT_FIELDS;
        }
        StringBuilder stringbuilder = new StringBuilder();
        ReportField areportfield1[] = areportfield;
        int i = areportfield1.length;
        for (int j = 0; j < i; j++)
        {
            ReportField reportfield = areportfield1[j];
            stringbuilder.append(reportfield.toString()).append("=");
            stringbuilder.append((String)crashreportdata.get(reportfield));
            stringbuilder.append('\n');
        }

        return stringbuilder.toString();
    }

    public void send(CrashReportData crashreportdata)
        throws ReportSenderException
    {
        String s = (new StringBuilder()).append(mContext.getPackageName()).append(" Crash Report").toString();
        String s1 = buildBody(crashreportdata);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(0x10000000);
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", s);
        intent.putExtra("android.intent.extra.TEXT", s1);
        String as[] = new String[1];
        as[0] = Applicot.getConfig().mailTo();
        intent.putExtra("android.intent.extra.EMAIL", as);
        mContext.startActivity(intent);
    }
}
