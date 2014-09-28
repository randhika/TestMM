// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGUtils, YHttpGet, YHBGRd

public class YIOUtils
{
    static class TouchUrlClient extends AsyncTask
    {

        protected volatile transient Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            int i = 0;
            int j = as.length;
            do
            {
                if (i >= j)
                {
                    return null;
                }
                String s = as[i];
                if (s != null)
                {
                    try
                    {
                        Uri uri = Uri.parse(s);
                        YHttpGet yhttpget = new YHttpGet();
                        if (YHBGRd.sBCookie != null)
                        {
                            BasicClientCookie basicclientcookie = new BasicClientCookie("B", YHBGRd.sBCookie);
                            basicclientcookie.setDomain(".yahoo.co.jp");
                            basicclientcookie.setPath("/");
                            yhttpget.getCookieStore().addCookie(basicclientcookie);
                        }
                        yhttpget.enableRedirect(false);
                        yhttpget.get(uri);
                        YHBGUtils.debug((new StringBuilder("rd => ")).append(uri).toString());
                    }
                    catch (Exception exception) { }
                }
                i++;
            } while (true);
        }

        TouchUrlClient()
        {
        }
    }


    private static final int BUF_SIZE = 8192;
    private static final int MAX_FEED_SIZE = 0x300000;

    public YIOUtils()
    {
    }

    public static void closeQuietely(InputStream inputstream)
    {
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_8;
        }
        inputstream.close();
        return;
        IOException ioexception;
        ioexception;
    }

    public static void closeQuietely(OutputStream outputstream)
    {
        if (outputstream == null)
        {
            break MISSING_BLOCK_LABEL_8;
        }
        outputstream.close();
        return;
        IOException ioexception;
        ioexception;
    }

    public static byte[] getByteFromInput(InputStream inputstream)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        if (inputstream == null)
        {
            return null;
        }
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[8192];
_L1:
        int i = inputstream.read(abyte0);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        byte abyte1[] = bytearrayoutputstream.toByteArray();
        closeQuietely(bytearrayoutputstream);
        return abyte1;
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
        IOException ioexception;
        ioexception;
        closeQuietely(bytearrayoutputstream);
        return null;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        if (bytearrayoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        bytearrayoutputstream.reset();
        closeQuietely(bytearrayoutputstream);
        closeQuietely(bytearrayoutputstream);
        return null;
        Exception exception;
        exception;
        closeQuietely(bytearrayoutputstream);
        throw exception;
    }

    public static boolean isExternalStorageWritable()
    {
        boolean flag = "mounted".equals(Environment.getExternalStorageState());
        boolean flag1 = false;
        if (flag)
        {
            flag1 = true;
        }
        return flag1;
    }

    public static String readFromAsset(Context context, String s)
    {
        AssetManager assetmanager;
        StringBuilder stringbuilder;
        assetmanager = context.getResources().getAssets();
        stringbuilder = new StringBuilder();
        InputStream inputstream;
        byte abyte0[];
        inputstream = assetmanager.open(s);
        abyte0 = new byte[8192];
_L3:
        int i = inputstream.read(abyte0);
        if (i != -1) goto _L2; else goto _L1
_L1:
        return stringbuilder.toString();
_L2:
        stringbuilder.append(new String(abyte0, 0, i));
          goto _L3
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L1
    }

    public static String readFromCache(File file)
    {
        FileInputStream fileinputstream = null;
        boolean flag = file.exists();
        String s;
        s = null;
        fileinputstream = null;
        if (!flag) goto _L2; else goto _L1
_L1:
        boolean flag1 = file.isFile();
        s = null;
        fileinputstream = null;
        if (!flag1) goto _L2; else goto _L3
_L3:
        int i = file.length() != 0x300000L;
        fileinputstream = null;
        if (i >= 0) goto _L5; else goto _L4
_L4:
        FileInputStream fileinputstream1 = new FileInputStream(file);
        String s1 = readFromStream(fileinputstream1);
        s = s1;
        fileinputstream = fileinputstream1;
_L2:
        closeQuietely(fileinputstream);
        return s;
_L5:
        file.delete();
        s = null;
        fileinputstream = null;
          goto _L2
        IOException ioexception;
        ioexception;
_L9:
        ioexception.printStackTrace();
        closeQuietely(fileinputstream);
        return null;
        Exception exception;
        exception;
_L7:
        closeQuietely(fileinputstream);
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        if (true) goto _L7; else goto _L6
_L6:
        ioexception;
        fileinputstream = fileinputstream1;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public static String readFromHttpEntity(HttpEntity httpentity)
    {
        InputStream inputstream = null;
        String s;
        inputstream = httpentity.getContent();
        s = readFromStream(inputstream);
        closeQuietely(inputstream);
        return s;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        illegalstateexception.printStackTrace();
        closeQuietely(inputstream);
        return null;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        closeQuietely(inputstream);
        return null;
        Exception exception;
        exception;
        closeQuietely(inputstream);
        throw exception;
    }

    public static String readFromStorage(Context context, String s)
    {
        return readFromStorage(context, context.getPackageName(), s);
    }

    public static String readFromStorage(Context context, String s, String s1)
    {
        String s2 = null;
        FileInputStream fileinputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        YHBGUtils.debug("readFromStorage");
        fileinputstream = context.createPackageContext(s, 4).openFileInput(s1);
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[8192];
_L1:
        int i = fileinputstream.read(abyte0);
        s2 = null;
        if (i == -1)
        {
            try
            {
                s2 = bytearrayoutputstream.toString();
                YHBGUtils.debug((new StringBuilder("readFromStorage: ")).append(s2).toString());
            }
            catch (FileNotFoundException filenotfoundexception)
            {
                filenotfoundexception.printStackTrace();
                return s2;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return s2;
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return s2;
            }
            return s2;
        }
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
    }

    public static String readFromStream(InputStream inputstream)
    {
        byte abyte0[];
        ByteArrayOutputStream bytearrayoutputstream;
        abyte0 = new byte[8192];
        bytearrayoutputstream = new ByteArrayOutputStream();
_L1:
        int i = inputstream.read(abyte0);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        String s = bytearrayoutputstream.toString();
        closeQuietely(inputstream);
        return s;
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        closeQuietely(inputstream);
        return null;
        Exception exception;
        exception;
        closeQuietely(inputstream);
        throw exception;
    }

    public static Object readObjectFromCache(Context context, String s)
    {
        Object obj;
        FileInputStream fileinputstream;
        obj = null;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        YHBGUtils.debug((new StringBuilder("IOUtils#readObjectFromCache: filename=")).append(s).toString());
        fileinputstream = null;
        ObjectInputStream objectinputstream;
        fileinputstream = context.openFileInput(s);
        objectinputstream = new ObjectInputStream(fileinputstream);
        Object obj1 = objectinputstream.readObject();
        obj = obj1;
        closeQuietely(fileinputstream);
_L1:
        Exception exception;
        ClassNotFoundException classnotfoundexception;
        IOException ioexception;
        FileNotFoundException filenotfoundexception;
        StringBuilder stringbuilder = new StringBuilder("IOUtils#readObjectFromCache: result=");
        String s1;
        if (obj == null)
        {
            s1 = "null";
        } else
        {
            s1 = "OK";
        }
        YHBGUtils.debug(stringbuilder.append(s1).toString());
        return obj;
        filenotfoundexception;
_L6:
        YHBGUtils.debug((new StringBuilder("IOUtils#readObjectFromCache0: ")).append(filenotfoundexception).toString());
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        ioexception;
_L5:
        YHBGUtils.error((new StringBuilder("IOUtils#readObjectFromCache1: ")).append(ioexception).toString());
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        classnotfoundexception;
_L4:
        YHBGUtils.error((new StringBuilder("IOUtils#readObjectFromCache2: ")).append(classnotfoundexception).toString());
        try
        {
            context.deleteFile(s);
        }
        catch (Exception exception1) { }
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        exception;
_L3:
        closeQuietely(fileinputstream);
        throw exception;
        exception;
        if (true) goto _L3; else goto _L2
_L2:
        classnotfoundexception;
          goto _L4
        ioexception;
          goto _L5
        filenotfoundexception;
          goto _L6
    }

    public static Object readObjectFromCache(Context context, String s, String s1)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        Object obj;
        Context context1;
        FileInputStream fileinputstream;
        obj = null;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        context1 = context.createPackageContext(s, 4);
        fileinputstream = null;
        ObjectInputStream objectinputstream;
        fileinputstream = context1.openFileInput(s1);
        objectinputstream = new ObjectInputStream(fileinputstream);
        Object obj1 = objectinputstream.readObject();
        obj = obj1;
        closeQuietely(fileinputstream);
_L1:
        Exception exception;
        ClassNotFoundException classnotfoundexception;
        IOException ioexception;
        FileNotFoundException filenotfoundexception;
        StringBuilder stringbuilder = new StringBuilder("IOUtils#readObjectFromCache: result=");
        String s2;
        if (obj == null)
        {
            s2 = "null";
        } else
        {
            s2 = "OK";
        }
        YHBGUtils.debug(stringbuilder.append(s2).toString());
        return obj;
        filenotfoundexception;
_L6:
        YHBGUtils.debug((new StringBuilder("IOUtils#readObjectFromCache0: ")).append(filenotfoundexception).toString());
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        ioexception;
_L5:
        YHBGUtils.error((new StringBuilder("IOUtils#readObjectFromCache1: ")).append(ioexception).toString());
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        classnotfoundexception;
_L4:
        YHBGUtils.error((new StringBuilder("IOUtils#readObjectFromCache2: ")).append(classnotfoundexception).toString());
        try
        {
            context1.deleteFile(s1);
        }
        catch (Exception exception1) { }
        closeQuietely(fileinputstream);
        obj = null;
          goto _L1
        exception;
_L3:
        closeQuietely(fileinputstream);
        throw exception;
        exception;
        if (true) goto _L3; else goto _L2
_L2:
        classnotfoundexception;
          goto _L4
        ioexception;
          goto _L5
        filenotfoundexception;
          goto _L6
    }

    public static void touchUrl(String s)
    {
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        Uri uri = Uri.parse(s);
        YHttpGet yhttpget = new YHttpGet();
        if (YHBGRd.sBCookie != null)
        {
            BasicClientCookie basicclientcookie = new BasicClientCookie("B", YHBGRd.sBCookie);
            basicclientcookie.setDomain("yahoo.co.jp");
            basicclientcookie.setPath("/");
            yhttpget.getCookieStore().addCookie(basicclientcookie);
        }
        yhttpget.enableRedirect(false);
        yhttpget.get(uri);
        YHBGUtils.debug((new StringBuilder("rd => ")).append(uri).toString());
        return;
        Exception exception;
        exception;
    }

    public static transient void touchUrlAsync(String as[])
    {
        (new TouchUrlClient()).execute(as);
    }

    public static boolean writeToCache(File file, Object obj)
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);
        objectoutputstream.writeObject(obj);
        objectoutputstream.flush();
        objectoutputstream.close();
        return true;
        IOException ioexception;
        ioexception;
_L2:
        YHBGUtils.error((new StringBuilder("\u66F8\u304D\u8FBC\u307F\u6642\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F ")).append(ioexception).toString());
        ioexception.printStackTrace();
        return false;
        ioexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static boolean writeToCache(File file, String s)
    {
        boolean flag;
        try
        {
            flag = writeToCache(new FileOutputStream(file), s);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            return false;
        }
        return flag;
    }

    public static boolean writeToCache(FileOutputStream fileoutputstream, String s)
    {
        boolean flag;
        flag = false;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        fileoutputstream.write(s.getBytes());
        fileoutputstream.flush();
        flag = true;
        closeQuietely(fileoutputstream);
        return flag;
        IOException ioexception;
        ioexception;
        YHBGUtils.error((new StringBuilder("\u66F8\u304D\u8FBC\u307F\u6642\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F ")).append(ioexception).toString());
        ioexception.printStackTrace();
        closeQuietely(fileoutputstream);
        return false;
        Exception exception;
        exception;
        closeQuietely(fileoutputstream);
        throw exception;
    }

    public static boolean writeToCache(OutputStream outputstream, InputStream inputstream)
    {
        byte abyte0[] = new byte[8192];
_L1:
        int i = inputstream.read(abyte0);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        outputstream.flush();
        closeQuietely(outputstream);
        return true;
        outputstream.write(abyte0, 0, i);
          goto _L1
        IOException ioexception;
        ioexception;
        YHBGUtils.error((new StringBuilder("\u66F8\u304D\u8FBC\u307F\u6642\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F ")).append(ioexception).toString());
        closeQuietely(outputstream);
        return false;
        Exception exception;
        exception;
        closeQuietely(outputstream);
        throw exception;
    }

    public static boolean writeToCache(OutputStream outputstream, Object obj)
    {
        try
        {
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(outputstream);
            objectoutputstream.writeObject(obj);
            objectoutputstream.flush();
            objectoutputstream.close();
        }
        catch (IOException ioexception)
        {
            YHBGUtils.error((new StringBuilder("\u66F8\u304D\u8FBC\u307F\u6642\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F ")).append(ioexception).toString());
            ioexception.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean writeToCache(OutputStream outputstream, byte abyte0[])
    {
        try
        {
            outputstream.write(abyte0);
        }
        catch (IOException ioexception)
        {
            YHBGUtils.error((new StringBuilder("\u66F8\u304D\u8FBC\u307F\u6642\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F ")).append(ioexception).toString());
            ioexception.printStackTrace();
            return false;
        }
        return true;
    }
}
