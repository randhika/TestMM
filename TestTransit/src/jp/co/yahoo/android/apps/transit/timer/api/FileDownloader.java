// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader extends AsyncTask
{

    private final String TAG = "AsyncFileLoader";
    private final int TIMEOUT_CONNECT = 30000;
    private final int TIMEOUT_READ = 5000;
    private BufferedInputStream _bufferedInputStream;
    private int _currentByte;
    private FileOutputStream _fileOutputStream;
    private InputStream _inputStream;
    private File _outputFile;
    private int _totalByte;
    private URL _url;
    private URLConnection _urlConnection;
    private String _urlStr;
    private byte buff[];
    private boolean isError;

    public FileDownloader(String s, File file)
    {
        buff = new byte[5120];
        _totalByte = 0;
        _currentByte = 0;
        isError = false;
        _urlStr = s;
        _outputFile = file;
    }

    private void close()
        throws IOException
    {
        _fileOutputStream.flush();
        _fileOutputStream.close();
        _bufferedInputStream.close();
    }

    private void connect()
        throws IOException
    {
        _url = new URL(_urlStr);
        _urlConnection = _url.openConnection();
        _urlConnection.setReadTimeout(5000);
        _urlConnection.setConnectTimeout(30000);
        _inputStream = _urlConnection.getInputStream();
        _bufferedInputStream = new BufferedInputStream(_inputStream, 5120);
        _fileOutputStream = new FileOutputStream(_outputFile);
        _totalByte = _urlConnection.getContentLength();
        _currentByte = 0;
    }

    protected transient Boolean doInBackground(Void avoid[])
    {
        if (isCancelled())
        {
            return Boolean.valueOf(false);
        }
_L2:
        int i;
        boolean flag;
        try
        {
            i = _bufferedInputStream.read(buff);
        }
        catch (IOException ioexception)
        {
            isError = true;
            ioexception.printStackTrace();
            return Boolean.valueOf(false);
        }
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        _fileOutputStream.write(buff, 0, i);
        _currentByte = i + _currentByte;
        flag = isCancelled();
        if (!flag) goto _L2; else goto _L1
_L1:
        return Boolean.valueOf(true);
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    public int getLoadedBytePercent()
    {
        if (_totalByte <= 0)
        {
            return 0;
        } else
        {
            return (int)Math.floor((100L * (long)_currentByte) / (long)_totalByte);
        }
    }

    public boolean isErorr()
    {
        return isError;
    }

    protected void onPostExecute(Boolean boolean1)
    {
        if (!boolean1.booleanValue())
        {
            break MISSING_BLOCK_LABEL_12;
        }
        close();
        return;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        isError = true;
        return;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Boolean)obj);
    }

    protected void onPreExecute()
    {
        try
        {
            connect();
            return;
        }
        catch (IOException ioexception)
        {
            isError = true;
            ioexception.printStackTrace();
            cancel(true);
            return;
        }
    }
}
