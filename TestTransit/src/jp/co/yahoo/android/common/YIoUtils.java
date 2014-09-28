// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.apache.http.util.CharArrayBuffer;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YIoUtils
{

    private static final String TAG = jp/co/yahoo/android/common/YIoUtils.getSimpleName();

    public YIoUtils()
    {
    }

    public static final String InputStreamToString(InputStream inputstream)
    {
        BufferedReader bufferedreader;
        CharArrayBuffer chararraybuffer;
        char ac[];
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        chararraybuffer = new CharArrayBuffer(1024);
        ac = new char[1024];
_L1:
        int i = bufferedreader.read(ac);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        chararraybuffer.append(ac, 0, i);
          goto _L1
        IOException ioexception1;
        ioexception1;
        ioexception1.printStackTrace();
        String s;
        IOException ioexception3;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
            return null;
        }
        return null;
        s = chararraybuffer.toString();
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
            return s;
        }
        return s;
        Exception exception;
        exception;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        throw exception;
    }

    public static final void close(Closeable closeable)
    {
        if (closeable == null)
        {
            return;
        }
        try
        {
            closeable.close();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static final boolean write(String s, String s1)
    {
        try
        {
            PrintStream printstream = new PrintStream(s);
            printstream.print(s1);
            printstream.close();
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            YLog.e(TAG, (new StringBuilder()).append(filenotfoundexception.getClass().getSimpleName()).append(":").append(filenotfoundexception.getMessage()).toString());
            filenotfoundexception.printStackTrace();
            return false;
        }
        return true;
    }

}
