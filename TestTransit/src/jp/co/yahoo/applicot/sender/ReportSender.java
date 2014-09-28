// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.sender;

import jp.co.yahoo.applicot.collector.CrashReportData;

// Referenced classes of package jp.co.yahoo.applicot.sender:
//            ReportSenderException

public interface ReportSender
{

    public abstract void send(CrashReportData crashreportdata)
        throws ReportSenderException;
}
