// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.util.ByteArrayBuffer;

// Referenced classes of package jp.co.yahoo.android.common:
//            YImageDownloadManager, YSoftHashMap, YImageDownloader, YImageDownloadItem, 
//            YBitmap

class _downloading
{

    boolean _downloading;
    HttpURLConnection _httpConn;
    final YImageDownloader this$0;

    void cancel()
    {
        _downloading = false;
        if (_httpConn != null)
        {
            _httpConn.disconnect();
        }
    }

    Drawable downloadImage(String s)
    {
        return downloadImage(s, false);
    }

    Drawable downloadImage(String s, boolean flag)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        Drawable drawable = null;
_L14:
        return drawable;
_L2:
        boolean flag1;
        flag1 = false;
        drawable = null;
        drawable = (Drawable)YImageDownloadManager.CACHE.get(s);
        flag1 = false;
        if (drawable != null) goto _L4; else goto _L3
_L3:
        _downloading = true;
        URL url = new URL(s);
        _httpConn = (HttpURLConnection)url.openConnection();
        _httpConn.connect();
        if (_httpConn.getResponseCode() != 200) goto _L6; else goto _L5
_L5:
        int j = _httpConn.getContentLength();
        if (j >= 0x300000) goto _L8; else goto _L7
_L7:
        InputStream inputstream = _httpConn.getInputStream();
        if (inputstream == null) goto _L10; else goto _L9
_L9:
        int k;
        k = j;
        if (j < 0)
        {
            k = 4096;
        }
        ByteArrayBuffer bytearraybuffer = new ByteArrayBuffer(k);
        byte abyte0[] = new byte[4096];
_L13:
        int l = inputstream.read(abyte0);
        if (l == -1) goto _L12; else goto _L11
_L11:
        bytearraybuffer.append(abyte0, 0, l);
          goto _L13
        Exception exception1;
        exception1;
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_183;
        }
        inputstream.close();
        IOException ioexception;
        throw exception1;
_L4:
        Exception exception2;
        ByteArrayInputStream bytearrayinputstream1;
        _downloading = false;
        if (flag1 && drawable == null)
        {
            Log.e("YImageDownloader", (new StringBuilder()).append("Retry: ").append(s).toString());
            Exception exception;
            int i;
            OutOfMemoryError outofmemoryerror;
            byte abyte1[];
            ByteArrayInputStream bytearrayinputstream;
            byte abyte2[];
            if (!YImageDownloader.access$000().contains(s))
            {
                if (YImageDownloader.access$000().size() > 10)
                {
                    YImageDownloader.access$000().clear();
                }
                YImageDownloader.access$000().add(s);
                YImageDownloader.access$200(YImageDownloader.this).requestDownload(s, YImageDownloader.access$100(YImageDownloader.this).getListener());
                return drawable;
            } else
            {
                Log.e("YImageDownloader", (new StringBuilder()).append("Already retried: ").append(s).toString());
                return drawable;
            }
        }
          goto _L14
_L12:
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_325;
        }
        inputstream.close();
        abyte2 = bytearraybuffer.toByteArray();
        abyte1 = abyte2;
_L16:
        if (abyte1 == null) goto _L4; else goto _L15
_L15:
        bytearrayinputstream = new ByteArrayInputStream(abyte1);
        drawable = YBitmap.stream2drawable(bytearrayinputstream);
        if (drawable != null)
        {
            break MISSING_BLOCK_LABEL_454;
        }
        YImageDownloadManager.clearCache();
        flag1 = true;
_L17:
        if (bytearrayinputstream != null)
        {
            try
            {
                bytearrayinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception)
            {
                Log.e("YImageDownloader", (new StringBuilder()).append("IOException: ").append(ioexception.getMessage()).toString());
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                Log.e("YImageDownloader", (new StringBuilder()).append("Exception: ").append(exception.getMessage()).toString());
            }
        }
          goto _L4
        outofmemoryerror;
        Log.e("YImageDownloader", (new StringBuilder()).append("OOME: while calling toByteArray.").append(s).toString());
        flag1 = true;
        YImageDownloadManager.clearCache();
        abyte1 = null;
          goto _L16
        YImageDownloadManager.CACHE.put(s, drawable);
          goto _L17
        exception2;
        bytearrayinputstream1 = bytearrayinputstream;
_L18:
        if (bytearrayinputstream1 == null)
        {
            break MISSING_BLOCK_LABEL_483;
        }
        bytearrayinputstream1.close();
        throw exception2;
_L10:
        flag1 = true;
        Log.e("YImageDownloader", (new StringBuilder()).append("Cannot get InputStream: ").append(s).toString());
          goto _L4
_L8:
        Log.e("YImageDownloader", (new StringBuilder()).append("Image size is exceeded (no retry): ").append(s).toString());
        flag1 = false;
          goto _L4
_L6:
        Log.e("YImageDownloader", (new StringBuilder()).append("Status Error: ").append(_httpConn.getResponseCode()).toString());
        i = _httpConn.getResponseCode();
        flag1 = false;
        if (i == -1)
        {
            flag1 = true;
        }
          goto _L4
        exception2;
        bytearrayinputstream1 = null;
          goto _L18
    }

    ()
    {
        this$0 = YImageDownloader.this;
        super();
        _downloading = false;
    }
}
