// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import java.io.File;
import java.io.FilenameFilter;

// Referenced classes of package jp.co.yahoo.applicot:
//            CrashReportFinder

class this._cls0
    implements FilenameFilter
{

    final CrashReportFinder this$0;

    public boolean accept(File file, String s)
    {
        return s.endsWith(".stacktrace");
    }

    ()
    {
        this$0 = CrashReportFinder.this;
        super();
    }
}
