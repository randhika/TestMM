// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog, YFileTools

public class YDistributionUtils
{

    private static final String ASSETS_FILE_NAME = "ydistribution.txt";
    private static final int DISTRI_DELETE = 3;
    private static final int DISTRI_NO_OVERRIDE = 2;
    private static final int DISTRI_OVERRIDE = 1;
    private static final int DISTRI_UNKNOW = 0;
    private static final String LOCAL_FILE_NAME = "/distribution/ydistribution_local.txt";
    private static final String SEPARETER = "=";
    private static final String TAG = jp/co/yahoo/android/common/YDistributionUtils.getSimpleName();
    private AssetManager mAssets;
    private final HashMap mCache = new HashMap();
    private String mDataFilePath;

    public YDistributionUtils(Context context)
    {
        mDataFilePath = null;
        mAssets = null;
        mAssets = context.getResources().getAssets();
        try
        {
            mAssets.open("ydistribution.txt").close();
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException("assets file not found.");
        }
        mDataFilePath = (new StringBuilder()).append(context.getFilesDir().getAbsolutePath()).append("/distribution/ydistribution_local.txt").toString();
        createCache(mDataFilePath);
    }

    private boolean checkUpdate(String s, String s1)
    {
        return !mCache.containsKey(s) || !TextUtils.equals((CharSequence)mCache.get(s), s1);
    }

    private void createCache(String s)
    {
        File file = new File(s);
        if (file.exists() && file.isFile() && file.canRead()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        BufferedReader bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new FileReader(s));
_L6:
        String s1 = bufferedreader1.readLine();
        if (s1 == null) goto _L4; else goto _L3
_L3:
        String s2;
        int i;
        s2 = s1.trim();
        i = s2.indexOf("=");
        if (i == -1) goto _L6; else goto _L5
_L5:
        String s3 = s2.substring(0, i).trim();
        if (s3.length() != 0)
        {
            String s4 = s2.substring(i + 1, s2.length()).trim();
            mCache.put(s3, s4);
        }
          goto _L6
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        bufferedreader = bufferedreader1;
_L11:
        filenotfoundexception.printStackTrace();
        YLog.e(TAG, filenotfoundexception.getMessage());
        if (bufferedreader != null)
        {
            try
            {
                bufferedreader.close();
                return;
            }
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
            return;
        }
          goto _L1
_L4:
        if (bufferedreader1 == null)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        bufferedreader1.close();
        return;
        IOException ioexception4;
        ioexception4;
        ioexception4.printStackTrace();
        return;
        IOException ioexception;
        ioexception;
_L10:
        ioexception.printStackTrace();
        YLog.e(TAG, ioexception.getMessage());
        if (bufferedreader == null) goto _L1; else goto _L7
_L7:
        try
        {
            bufferedreader.close();
            return;
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        return;
        Exception exception;
        exception;
_L9:
        if (bufferedreader != null)
        {
            try
            {
                bufferedreader.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if (true) goto _L9; else goto _L8
_L8:
        ioexception;
        bufferedreader = bufferedreader1;
          goto _L10
        filenotfoundexception;
        bufferedreader = null;
          goto _L11
    }

    private boolean update(AssetManager assetmanager, String s)
    {
        boolean flag;
        BufferedReader bufferedreader;
        flag = false;
        bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(assetmanager.open(s)));
        byte byte0 = 0;
_L4:
        String s1 = bufferedreader1.readLine();
        if (s1 == null) goto _L2; else goto _L1
_L1:
        String s2 = s1.trim();
        if (s2.indexOf("#") == 0) goto _L4; else goto _L3
_L3:
        if (!TextUtils.equals(s2, "[no-override]"))
        {
            break MISSING_BLOCK_LABEL_74;
        }
        byte0 = 2;
          goto _L4
        if (!TextUtils.equals(s2, "[override]"))
        {
            break MISSING_BLOCK_LABEL_90;
        }
        byte0 = 1;
          goto _L4
        if (!TextUtils.equals(s2, "[delete]")) goto _L6; else goto _L5
_L5:
        byte0 = 3;
          goto _L4
_L6:
        if (TextUtils.equals(s2, "")) goto _L4; else goto _L7
_L7:
        if (3 != byte0)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        if (!mCache.containsKey(s2)) goto _L4; else goto _L8
_L8:
        mCache.remove(s2);
        flag = true;
          goto _L4
        int i = s2.indexOf("=");
        if (i == -1) goto _L4; else goto _L9
_L9:
        String s4;
        String s5;
        String s3 = s2.substring(0, i);
        s4 = s2.substring(i + 1, s2.length());
        s5 = s3.trim();
        if (s5.length() == 0) goto _L4; else goto _L10
_L10:
        String s6 = s4.trim();
        byte0;
        JVM INSTR tableswitch 1 2: default 434
    //                   1 236
    //                   2 264;
           goto _L4 _L11 _L12
_L11:
        if (!checkUpdate(s5, s6))
        {
            break; /* Loop/switch isn't completed */
        }
        mCache.put(s5, s6);
        flag = true;
        break; /* Loop/switch isn't completed */
_L12:
        boolean flag1 = mCache.containsKey(s5);
        if (!flag1) goto _L11; else goto _L4
_L2:
        if (bufferedreader1 != null)
        {
            try
            {
                bufferedreader1.close();
            }
            catch (IOException ioexception4)
            {
                ioexception4.printStackTrace();
                return flag;
            }
        }
_L14:
        return flag;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L19:
        filenotfoundexception.printStackTrace();
        YLog.e(TAG, filenotfoundexception.getMessage());
        if (bufferedreader == null) goto _L14; else goto _L13
_L13:
        try
        {
            bufferedreader.close();
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
            return flag;
        }
        return flag;
        IOException ioexception2;
        ioexception2;
_L18:
        ioexception2.printStackTrace();
        YLog.e(TAG, ioexception2.getMessage());
        if (bufferedreader == null) goto _L14; else goto _L15
_L15:
        try
        {
            bufferedreader.close();
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
            return flag;
        }
        return flag;
        Exception exception;
        exception;
_L17:
        if (bufferedreader != null)
        {
            try
            {
                bufferedreader.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if (true) goto _L17; else goto _L16
_L16:
        ioexception2;
        bufferedreader = bufferedreader1;
          goto _L18
        filenotfoundexception;
        bufferedreader = bufferedreader1;
          goto _L19
    }

    private void write(String s)
    {
        BufferedWriter bufferedwriter;
        BufferedWriter bufferedwriter1;
        FileNotFoundException filenotfoundexception;
        File file = new File(s);
        File file1 = new File(file.getParent());
        Iterator iterator;
        String s2;
        String s3;
        if (!file1.exists())
        {
            YFileTools.makeDirs(file1.getPath());
        } else
        if (!file1.isDirectory())
        {
            String s1 = TAG;
            Object aobj[] = new Object[1];
            aobj[0] = file1.getPath();
            YLog.e(s1, "error path is no directory %s", aobj);
            return;
        }
        if (file.exists())
        {
            file.delete();
        }
        bufferedwriter = null;
        bufferedwriter1 = new BufferedWriter(new FileWriter(s));
        for (iterator = mCache.keySet().iterator(); iterator.hasNext(); bufferedwriter1.flush())
        {
            s2 = (String)iterator.next();
            s3 = (String)mCache.get(s2);
            bufferedwriter1.append((new StringBuilder()).append(s2).append("=").append(s3).toString());
            bufferedwriter1.newLine();
        }

          goto _L1
        filenotfoundexception;
        bufferedwriter = bufferedwriter1;
_L8:
        filenotfoundexception.printStackTrace();
        YLog.e(TAG, filenotfoundexception.getMessage());
        if (bufferedwriter == null)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        bufferedwriter.close();
_L3:
        return;
_L1:
        if (bufferedwriter1 == null)
        {
            break MISSING_BLOCK_LABEL_242;
        }
        bufferedwriter1.close();
        return;
        IOException ioexception4;
        ioexception4;
        ioexception4.printStackTrace();
        return;
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
        return;
        IOException ioexception;
        ioexception;
_L6:
        ioexception.printStackTrace();
        YLog.e(TAG, ioexception.getMessage());
        if (bufferedwriter == null) goto _L3; else goto _L2
_L2:
        try
        {
            bufferedwriter.close();
            return;
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        return;
        Exception exception;
        exception;
_L5:
        if (bufferedwriter != null)
        {
            try
            {
                bufferedwriter.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        bufferedwriter = bufferedwriter1;
        if (true) goto _L5; else goto _L4
_L4:
        ioexception;
        bufferedwriter = bufferedwriter1;
          goto _L6
        filenotfoundexception;
        bufferedwriter = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public String getString(String s)
    {
        this;
        JVM INSTR monitorenter ;
        String s1 = (String)mCache.get(s);
        this;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }

    public void putString(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        mCache.put(s, s1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String readAssetFile()
    {
        this;
        JVM INSTR monitorenter ;
        BufferedInputStream bufferedinputstream = null;
        BufferedInputStream bufferedinputstream1 = new BufferedInputStream(mAssets.open("ydistribution.txt"));
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        for (byte abyte0[] = new byte[4096]; -1 != bufferedinputstream1.read(abyte0); bytearrayoutputstream.write(abyte0)) { }
          goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        bufferedinputstream = bufferedinputstream1;
_L12:
        filenotfoundexception.printStackTrace();
        YLog.e(TAG, filenotfoundexception.getMessage());
        if (bufferedinputstream == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        bufferedinputstream.close();
_L4:
        String s = null;
_L3:
        this;
        JVM INSTR monitorexit ;
        return s;
_L1:
        String s1 = bytearrayoutputstream.toString();
        s = s1;
        if (bufferedinputstream1 == null) goto _L3; else goto _L2
_L2:
        bufferedinputstream1.close();
          goto _L3
        IOException ioexception4;
        ioexception4;
        ioexception4.printStackTrace();
          goto _L3
        Exception exception1;
        exception1;
_L5:
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
          goto _L4
        exception1;
          goto _L5
        IOException ioexception;
        ioexception;
_L10:
        ioexception.printStackTrace();
        YLog.e(TAG, ioexception.getMessage());
        if (bufferedinputstream == null) goto _L4; else goto _L6
_L6:
        bufferedinputstream.close();
          goto _L4
        IOException ioexception2;
        ioexception2;
        ioexception2.printStackTrace();
          goto _L4
        Exception exception;
        exception;
_L9:
        if (bufferedinputstream == null)
        {
            break MISSING_BLOCK_LABEL_193;
        }
        bufferedinputstream.close();
_L7:
        throw exception;
        IOException ioexception1;
        ioexception1;
        ioexception1.printStackTrace();
          goto _L7
        exception;
        bufferedinputstream = bufferedinputstream1;
        continue; /* Loop/switch isn't completed */
        exception;
        bufferedinputstream = bufferedinputstream1;
        if (true) goto _L9; else goto _L8
_L8:
        ioexception;
        bufferedinputstream = bufferedinputstream1;
          goto _L10
        ioexception;
        bufferedinputstream = bufferedinputstream1;
          goto _L10
        filenotfoundexception;
        bufferedinputstream = null;
        continue; /* Loop/switch isn't completed */
        filenotfoundexception;
        bufferedinputstream = bufferedinputstream1;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public void updateFromAssetFile()
    {
        this;
        JVM INSTR monitorenter ;
        if (update(mAssets, "ydistribution.txt"))
        {
            write(mDataFilePath);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void write()
    {
        this;
        JVM INSTR monitorenter ;
        write(mDataFilePath);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

}
