// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;


// Referenced classes of package jp.co.yahoo.applicot:
//            ApplicotConstants

final class CrashReportFileNameParser
{

    CrashReportFileNameParser()
    {
    }

    public boolean isApproved(String s)
    {
        return isSilent(s) || s.contains("-approved");
    }

    public boolean isSilent(String s)
    {
        return s.contains(ApplicotConstants.SILENT_SUFFIX);
    }
}
