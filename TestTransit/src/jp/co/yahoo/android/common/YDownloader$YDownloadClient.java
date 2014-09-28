// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLogger, YDownloader, YDownloadItem, YDownloadManager, 
//            YDownloadListener

class _downloading
{

    private int _code;
    private boolean _downloading;
    private HttpURLConnection _httpConn;
    final YDownloader this$0;

    public void cancel()
    {
        _downloading = false;
        if (_httpConn != null)
        {
            _httpConn.disconnect();
        }
    }

    public InputStream download(String s)
    {
        return download(s, null, false);
    }

    public InputStream download(String s, Map map, boolean flag)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        InputStream inputstream;
        YLogger.log("argument aUrl is null...");
        inputstream = null;
_L8:
        return inputstream;
_L2:
        boolean flag1;
        _code = 0;
        inputstream = null;
        flag1 = false;
        _downloading = true;
        _httpConn = (HttpURLConnection)(new URL(s)).openConnection();
        _httpConn.setConnectTimeout(YDownloader.access$100(YDownloader.this));
        _httpConn.setReadTimeout(YDownloader.access$200(YDownloader.this));
        inputstream = null;
        flag1 = false;
        if (map == null) goto _L4; else goto _L3
_L3:
        Iterator iterator = map.entrySet().iterator();
_L6:
        boolean flag2 = iterator.hasNext();
        inputstream = null;
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        IOException ioexception;
        java.util.  = (java.util.)iterator.next();
        _httpConn.setRequestProperty((String).roperty(), (String).roperty());
          goto _L6
_L10:
        _downloading = false;
        if (!flag1 || inputstream != null) goto _L8; else goto _L7
_L7:
        YLogger.log((new StringBuilder()).append("Retry: ").append(s).toString());
        if (YDownloader.access$500().contains(s))
        {
            break MISSING_BLOCK_LABEL_650;
        }
        if (YDownloader.access$500().size() > 10)
        {
            YDownloader.access$500().clear();
        }
        YDownloader.access$500().add(s);
        if (YDownloader.access$300(YDownloader.this) == null) goto _L8; else goto _L9
_L9:
        YDownloader.access$400(YDownloader.this).requestDownload(s, YDownloader.access$300(YDownloader.this).getListener(), YDownloader.access$300(YDownloader.this).getRequestProperty(), YDownloader.access$300(YDownloader.this).getOptionalObj());
        return inputstream;
_L4:
        int i;
        _httpConn.connect();
        _code = _httpConn.getResponseCode();
        i = _code;
        inputstream = null;
        flag1 = false;
        if (i != 200)
        {
            break MISSING_BLOCK_LABEL_598;
        }
        int j;
        YDownloadItem ydownloaditem;
        j = _httpConn.getContentLength();
        ydownloaditem = YDownloader.access$300(YDownloader.this);
        inputstream = null;
        flag1 = false;
        if (ydownloaditem == null)
        {
            break MISSING_BLOCK_LABEL_453;
        }
        YDownloadListener ydownloadlistener;
        YDownloader.access$300(YDownloader.this)._contentLength = j;
        ydownloadlistener = YDownloader.access$300(YDownloader.this).getListener();
        inputstream = null;
        flag1 = false;
        if (ydownloadlistener == null)
        {
            break MISSING_BLOCK_LABEL_453;
        }
        YDownloader.access$300(YDownloader.this).getListener().onConnected(YDownloader.access$300(YDownloader.this));
        int k = YDownloader.access$400(YDownloader.this).getMaxDownloadSize();
        inputstream = null;
        flag1 = false;
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_567;
        }
        inputstream = _httpConn.getInputStream();
        flag1 = false;
        if (inputstream == null)
        {
            flag1 = true;
            try
            {
                YLogger.log((new StringBuilder()).append("Cannot get InputStream: ").append(s).toString());
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception)
            {
                YLogger.log((new StringBuilder()).append("IOException: ").append(ioexception.getMessage()).append(" : ").append(ioexception.toString()).toString());
            }
            catch (Exception exception)
            {
                YLogger.log((new StringBuilder()).append("Exception: ").append(exception.getMessage()).append(" : ").append(exception.toString()).toString());
            }
        }
          goto _L10
        YLogger.log((new StringBuilder()).append(" size is exceeded (no retry): ").append(s).toString());
        inputstream = null;
        flag1 = false;
          goto _L10
        int l;
        YLogger.log((new StringBuilder()).append("Status Error: ").append(_code).toString());
        l = _code;
        inputstream = null;
        flag1 = false;
        if (l == -1)
        {
            flag1 = true;
            inputstream = null;
        }
          goto _L10
        YLogger.log((new StringBuilder()).append("Already retried: ").append(s).toString());
        return inputstream;
    }

    public int getResponseCode()
    {
        return _code;
    }


    ()
    {
        this$0 = YDownloader.this;
        super();
        _code = 0;
        _downloading = false;
    }
}
