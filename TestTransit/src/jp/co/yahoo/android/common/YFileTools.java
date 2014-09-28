// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public final class YFileTools
{

    private static final String TAG = "YFileTools";

    private YFileTools()
    {
    }

    public static void copyFile(String s, String s1)
    {
        FileChannel filechannel;
        FileChannel filechannel1;
        filechannel = null;
        filechannel1 = null;
        filechannel = (new FileInputStream(s)).getChannel();
        filechannel1 = (new FileOutputStream(s1)).getChannel();
        filechannel1.transferFrom(filechannel, 0L, filechannel.size());
        Exception exception;
        IOException ioexception2;
        IOException ioexception3;
        if (filechannel1 != null)
        {
            try
            {
                filechannel1.close();
            }
            catch (IOException ioexception6) { }
        }
        if (filechannel == null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        filechannel.close();
_L2:
        return;
        ioexception2;
        YLog.e("YFileTools", ioexception2.getMessage());
        if (filechannel1 != null)
        {
            try
            {
                filechannel1.close();
            }
            catch (IOException ioexception4) { }
        }
        if (filechannel == null) goto _L2; else goto _L1
_L1:
        try
        {
            filechannel.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception3)
        {
            return;
        }
        exception;
        IOException ioexception5;
        if (filechannel1 != null)
        {
            try
            {
                filechannel1.close();
            }
            catch (IOException ioexception1) { }
        }
        if (filechannel != null)
        {
            try
            {
                filechannel.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        ioexception5;
    }

    public static boolean deleteDir(String s)
    {
        File file = new File(s);
        if (file.exists()) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag;
        flag = file.isDirectory();
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        String as[];
        int i;
        int j;
        as = file.list();
        i = as.length;
        j = 0;
_L8:
        if (j >= i) goto _L6; else goto _L5
_L5:
        boolean flag2;
        File file1 = new File(file.getAbsolutePath(), as[j]);
        if (file1.isFile())
        {
            if (!file1.delete())
            {
                YLog.e("YFileTools", (new StringBuilder()).append("ERROR: deleteDir: ").append(file1.getPath()).toString());
                return false;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (!file1.isDirectory())
        {
            continue; /* Loop/switch isn't completed */
        }
        flag2 = deleteDir(file1.getAbsolutePath());
        flag1 = false;
        if (!flag2) goto _L4; else goto _L7
_L7:
        j++;
          goto _L8
_L6:
        return file.delete();
    }

    public static boolean exists(String s)
    {
        return (new File(s)).exists();
    }

    public static String getAppDirInSDCard(Context context)
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory().toString()).append("/data/").append(context.getPackageName()).toString();
    }

    public static boolean isValidCache(Context context, String s, int i)
    {
        return isValidCache(context.getFileStreamPath(s), i);
    }

    public static boolean isValidCache(File file, int i)
    {
        if (file.exists())
        {
            long l = file.lastModified();
            long l1 = (new Date()).getTime();
            YLog.i("YFileTools", (new StringBuilder()).append(l1).append(" ").append(l + (long)(i * 1000)).toString());
            if (l1 <= l + (long)(i * 1000))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean makeDirs(String s)
    {
        File file = new File(s);
        if (file.exists())
        {
            return true;
        } else
        {
            return file.mkdirs();
        }
    }

    public static boolean moveFile(String s, String s1, boolean flag)
    {
        File file;
        File file1;
        file = new File(s);
        file1 = new File(s1, file.getName());
        if (!file1.exists()) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        if (file1.delete()) goto _L2; else goto _L5
_L5:
        YLog.e("YFileTools", "ERROR: moveFile(%s, %s)", new Object[] {
            s, s1
        });
_L7:
        return false;
_L4:
        YLog.w("YFileTools", (new StringBuilder()).append("Already exists: ").append(file1.getAbsolutePath()).toString());
        return false;
_L2:
        if (makeDirs(s1))
        {
            if (!file.renameTo(file1))
            {
                Object aobj[] = new Object[2];
                aobj[0] = file.toString();
                aobj[1] = file1.toString();
                YLog.e("YFileTools", "ERROR: renameTo(%s, %s", aobj);
            }
            return true;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
