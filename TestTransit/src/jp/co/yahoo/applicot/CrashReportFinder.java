// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;

// Referenced classes of package jp.co.yahoo.applicot:
//            Applicot

final class CrashReportFinder
{

    private final Context context;

    public CrashReportFinder(Context context1)
    {
        context = context1;
    }

    public String[] getCrashReportFiles()
    {
        String as[];
        if (context == null)
        {
            Log.e(Applicot.LOG_TAG, "Trying to get Applicot reports but Applicot is not initialized.");
            as = new String[0];
        } else
        {
            File file = context.getFilesDir();
            if (file == null)
            {
                Log.w(Applicot.LOG_TAG, "Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
                return new String[0];
            }
            Log.d(Applicot.LOG_TAG, (new StringBuilder()).append("Looking for error files in ").append(file.getAbsolutePath()).toString());
            as = file.list(new FilenameFilter() {

                final CrashReportFinder this$0;

                public boolean accept(File file1, String s)
                {
                    return s.endsWith(".stacktrace");
                }

            
            {
                this$0 = CrashReportFinder.this;
                super();
            }
            });
            if (as == null)
            {
                return new String[0];
            }
        }
        return as;
    }
}
