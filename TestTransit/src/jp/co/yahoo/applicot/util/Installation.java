// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
import jp.co.yahoo.applicot.Applicot;

public class Installation
{

    private static final String INSTALLATION = "Applicot-INSTALLATION";
    private static String sID;

    public Installation()
    {
    }

    public static String id(Context context)
    {
        jp/co/yahoo/applicot/util/Installation;
        JVM INSTR monitorenter ;
        File file;
        if (sID != null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        file = new File(context.getFilesDir(), "Applicot-INSTALLATION");
        if (!file.exists())
        {
            writeInstallationFile(file);
        }
        sID = readInstallationFile(file);
        String s = sID;
_L2:
        jp/co/yahoo/applicot/util/Installation;
        JVM INSTR monitorexit ;
        return s;
        IOException ioexception;
        ioexception;
        Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Couldn't retrieve InstallationId for ").append(context.getPackageName()).toString(), ioexception);
        s = "Couldn't retrieve InstallationId";
        continue; /* Loop/switch isn't completed */
        RuntimeException runtimeexception;
        runtimeexception;
        Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Couldn't retrieve InstallationId for ").append(context.getPackageName()).toString(), runtimeexception);
        s = "Couldn't retrieve InstallationId";
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    private static String readInstallationFile(File file)
        throws IOException
    {
        RandomAccessFile randomaccessfile;
        byte abyte0[];
        randomaccessfile = new RandomAccessFile(file, "r");
        abyte0 = new byte[(int)randomaccessfile.length()];
        randomaccessfile.readFully(abyte0);
        randomaccessfile.close();
        return new String(abyte0);
        Exception exception;
        exception;
        randomaccessfile.close();
        throw exception;
    }

    private static void writeInstallationFile(File file)
        throws IOException
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        fileoutputstream.write(UUID.randomUUID().toString().getBytes());
        fileoutputstream.close();
        return;
        Exception exception;
        exception;
        fileoutputstream.close();
        throw exception;
    }
}
