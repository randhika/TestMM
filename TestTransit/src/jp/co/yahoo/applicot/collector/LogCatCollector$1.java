// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package jp.co.yahoo.applicot.collector:
//            LogCatCollector

static final class val.process
    implements Runnable
{

    final Process val$process;

    public void run()
    {
        InputStream inputstream;
        byte abyte0[];
        inputstream = val$process.getErrorStream();
        abyte0 = new byte[8192];
        int i;
        do
        {
            i = inputstream.read(abyte0);
        } while (i >= 0);
        return;
        IOException ioexception;
        ioexception;
    }

    (Process process1)
    {
        val$process = process1;
        super();
    }
}
