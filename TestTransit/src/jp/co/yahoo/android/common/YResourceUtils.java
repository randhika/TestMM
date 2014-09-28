// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.res.Resources;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package jp.co.yahoo.android.common:
//            YFileTools, YLog

public class YResourceUtils
{

    private static final String TAG = "YResourceUtils";

    private YResourceUtils()
    {
    }

    public static boolean saveToFile(Context context, int i, String s)
    {
        File file;
        InputStream inputstream;
        BufferedOutputStream bufferedoutputstream;
        file = new File(s);
        if (file.exists())
        {
            file.delete();
        }
        YFileTools.makeDirs(file.getParent());
        inputstream = context.getResources().openRawResource(i);
        bufferedoutputstream = null;
        BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(new FileOutputStream(file));
        byte abyte0[] = new byte[4096];
_L3:
        int j = inputstream.read(abyte0);
        if (j == -1) goto _L2; else goto _L1
_L1:
        bufferedoutputstream1.write(abyte0, 0, j);
          goto _L3
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        bufferedoutputstream = bufferedoutputstream1;
_L10:
        filenotfoundexception.printStackTrace();
        YLog.e("YResourceUtils", filenotfoundexception.getMessage());
        if (bufferedoutputstream != null)
        {
            try
            {
                bufferedoutputstream.close();
            }
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
                return false;
            }
        }
_L5:
        return false;
_L2:
        IOException ioexception;
        Exception exception;
        if (bufferedoutputstream1 != null)
        {
            try
            {
                bufferedoutputstream1.close();
            }
            catch (IOException ioexception4)
            {
                ioexception4.printStackTrace();
                return true;
            }
            return true;
        } else
        {
            return true;
        }
        ioexception;
_L8:
        ioexception.printStackTrace();
        YLog.e("YResourceUtils", ioexception.getMessage());
        if (bufferedoutputstream == null) goto _L5; else goto _L4
_L4:
        try
        {
            bufferedoutputstream.close();
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
            return false;
        }
        return false;
        exception;
_L7:
        if (bufferedoutputstream != null)
        {
            try
            {
                bufferedoutputstream.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L7; else goto _L6
_L6:
        ioexception;
        bufferedoutputstream = bufferedoutputstream1;
          goto _L8
        filenotfoundexception;
        bufferedoutputstream = null;
        if (true) goto _L10; else goto _L9
_L9:
    }
}
