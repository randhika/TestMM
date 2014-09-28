// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDownloadItem, YDownloadListener, YLogger, YDownloadManager

class YDownloader extends AsyncTask
{
    class YDownloadClient
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
            _httpConn.setConnectTimeout(_connectTimeout);
            _httpConn.setReadTimeout(_readTimeout);
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
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            _httpConn.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
              goto _L6
_L10:
            _downloading = false;
            if (!flag1 || inputstream != null) goto _L8; else goto _L7
_L7:
            YLogger.log((new StringBuilder()).append("Retry: ").append(s).toString());
            if (YDownloader.sRetryList.contains(s))
            {
                break MISSING_BLOCK_LABEL_650;
            }
            if (YDownloader.sRetryList.size() > 10)
            {
                YDownloader.sRetryList.clear();
            }
            YDownloader.sRetryList.add(s);
            if (_item == null) goto _L8; else goto _L9
_L9:
            _man.requestDownload(s, _item.getListener(), _item.getRequestProperty(), _item.getOptionalObj());
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
            ydownloaditem = _item;
            inputstream = null;
            flag1 = false;
            if (ydownloaditem == null)
            {
                break MISSING_BLOCK_LABEL_453;
            }
            YDownloadListener ydownloadlistener;
            _item._contentLength = j;
            ydownloadlistener = _item.getListener();
            inputstream = null;
            flag1 = false;
            if (ydownloadlistener == null)
            {
                break MISSING_BLOCK_LABEL_453;
            }
            _item.getListener().onConnected(_item);
            int k = _man.getMaxDownloadSize();
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


        YDownloadClient()
        {
            this$0 = YDownloader.this;
            super();
            _code = 0;
            _downloading = false;
        }
    }


    private static final ArrayList sRetryList = new ArrayList();
    private boolean _callOnFinish;
    private int _connectTimeout;
    private YDownloadClient _downloadClient;
    private YDownloadItem _item;
    private YDownloadManager _man;
    private int _readTimeout;

    YDownloader(YDownloadManager ydownloadmanager)
    {
        _connectTimeout = 30000;
        _readTimeout = 30000;
        _callOnFinish = false;
        _man = ydownloadmanager;
        _downloadClient = new YDownloadClient();
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((YDownloadItem[])aobj);
    }

    protected transient Void doInBackground(YDownloadItem aydownloaditem[])
    {
        _item = aydownloaditem[0];
        String s = _item.getUrl();
        _item._input = _downloadClient.download(s, _item.getRequestProperty(), false);
        _item._code = _downloadClient.getResponseCode();
        if (!isCancelled() && _item != null && _item.getListener() != null)
        {
            _callOnFinish = _item.getListener().onFinishedInBackground(_item);
        }
        return null;
    }

    YDownloadItem getDownloadItem()
    {
        return _item;
    }

    YDownloadItem getItem()
    {
        return _item;
    }

    boolean isDownloading()
    {
        return _downloadClient._downloading;
    }

    protected void onCancelled()
    {
        YLogger.log("cancelled");
        _downloadClient.cancel();
        if (_item != null && _item.getListener() != null)
        {
            _item.getListener().onCancelled(_item);
        }
        _man.removeDownloader(this);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Void)obj);
    }

    protected void onPostExecute(Void void1)
    {
        if (!isCancelled() && _callOnFinish && _item != null && _item.getListener() != null)
        {
            _item.getListener().onFinished(_item);
        }
        _man.removeDownloader(this);
        _man.requestAnother();
    }

    public void setConnectTimeout(int i)
    {
        _connectTimeout = i;
    }

    public void setReadTimeout(int i)
    {
        _readTimeout = i;
    }






}
