// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package jp.co.yahoo.android.common:
//            YCancelable, YApplicationBase, YLogger, YHttpDeleteWithBody, 
//            YHosts, YHttpClient, ResponseGzipUncompress

public class YHttpRequest
    implements Runnable, YCancelable
{

    private static final int DELETE_TYPE = 3;
    private static final int GET_TYPE = 1;
    private static final int POST_TYPE = 2;
    private static final int PUT_TYPE = 4;
    private static final int STATE_CANCELED = 3;
    private static final int STATE_LOADED = 2;
    private static final int STATE_LOADING = 1;
    private static final int STATE_UNINITIALIZED = 0;
    private static final String TAG = "YHttpRequest";
    private boolean mAcceptGZip;
    private boolean mAutoRedirect;
    private HttpRequestBase mConnection;
    private Context mContext;
    private final Handler mHandler;
    private int mHttpMethod;
    private HttpResponse mHttpResponse;
    private int mReadyState;
    private HttpEntity mRequestBodyEntity;
    private Map mRequestBodyMap;
    private Map mRequestHeader;
    private int mTimeout;
    private boolean mTimeoutFlag;
    private String mURL;

    public YHttpRequest(Context context, String s)
    {
        mReadyState = 0;
        mTimeout = 30000;
        mTimeoutFlag = false;
        mAcceptGZip = false;
        mAutoRedirect = true;
        mURL = "";
        mRequestHeader = null;
        mRequestBodyMap = null;
        mRequestBodyEntity = null;
        mHttpResponse = null;
        mConnection = null;
        mContext = null;
        mHandler = new Handler(Looper.getMainLooper()) {

            final YHttpRequest this$0;

            public void handleMessage(Message message)
            {
                String s1 = message.getData().getString("Topic");
                if (s1.equals("timeout"))
                {
                    onTimeout();
                } else
                if (s1.equals("complete"))
                {
                    onComplete();
                    return;
                }
            }

            
            {
                this$0 = YHttpRequest.this;
                super(looper);
            }
        };
        mContext = context;
        mURL = s;
    }

    public YHttpRequest(Context context, String s, Map map)
    {
        mReadyState = 0;
        mTimeout = 30000;
        mTimeoutFlag = false;
        mAcceptGZip = false;
        mAutoRedirect = true;
        mURL = "";
        mRequestHeader = null;
        mRequestBodyMap = null;
        mRequestBodyEntity = null;
        mHttpResponse = null;
        mConnection = null;
        mContext = null;
        mHandler = new _cls1(Looper.getMainLooper());
        mContext = context;
        mURL = s;
        mRequestHeader = map;
    }

    public YHttpRequest(String s)
    {
        mReadyState = 0;
        mTimeout = 30000;
        mTimeoutFlag = false;
        mAcceptGZip = false;
        mAutoRedirect = true;
        mURL = "";
        mRequestHeader = null;
        mRequestBodyMap = null;
        mRequestBodyEntity = null;
        mHttpResponse = null;
        mConnection = null;
        mContext = null;
        mHandler = new _cls1(Looper.getMainLooper());
        mURL = s;
        if (YApplicationBase.getInstance() != null)
        {
            mContext = YApplicationBase.getInstance().getApplicationContext();
        }
    }

    private void abortHttpConnection()
    {
        if (mConnection == null)
        {
            break MISSING_BLOCK_LABEL_14;
        }
        mConnection.abort();
        return;
        Exception exception;
        exception;
        YLogger.log("YHttpRequest", exception.toString());
        return;
    }

    private void asyncRequest(int i, HttpEntity httpentity, Map map)
    {
        if (equqalState(1))
        {
            return;
        } else
        {
            mRequestBodyEntity = httpentity;
            mRequestBodyMap = map;
            mHttpMethod = i;
            createConnection();
            (new Thread(this)).start();
            return;
        }
    }

    private void createConnection()
    {
        if (1 == mHttpMethod)
        {
            mConnection = new HttpGet(mURL);
            return;
        }
        if (2 == mHttpMethod)
        {
            mConnection = new HttpPost(mURL);
            return;
        }
        if (3 == mHttpMethod)
        {
            mConnection = new YHttpDeleteWithBody(mURL);
            return;
        }
        if (4 == mHttpMethod)
        {
            mConnection = new HttpPut(mURL);
            return;
        } else
        {
            mConnection = new HttpGet(mURL);
            return;
        }
    }

    private boolean equalEitherAndSetState(int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (mReadyState != i && mReadyState != j)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        mReadyState = k;
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        throw exception;
    }

    private boolean equqalState(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = mReadyState;
        boolean flag;
        if (j == i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private HttpResponse execute(HttpClient httpclient, HttpRequestBase httprequestbase)
    {
        HttpResponse httpresponse = null;
        if (httprequestbase != null)
        {
            HttpResponse httpresponse1;
            try
            {
                httpresponse1 = httpclient.execute(httprequestbase);
            }
            catch (InterruptedIOException interruptedioexception)
            {
                YLogger.log("YHttpRequest", interruptedioexception.toString());
                mTimeoutFlag = true;
                return null;
            }
            catch (IOException ioexception)
            {
                YLogger.log("YHttpRequest", ioexception.toString());
                return null;
            }
            catch (Exception exception)
            {
                YLogger.log("YHttpRequest", exception.toString());
                return null;
            }
            httpresponse = httpresponse1;
        }
        return httpresponse;
    }

    private void fetch()
    {
        Object obj;
        org.apache.http.params.HttpParams httpparams;
        if (mContext != null && YHosts.isDebuggable(mContext))
        {
            YHttpClient yhttpclient = new YHttpClient();
            yhttpclient.acceptUntrustedSslCertificate(mContext);
            obj = yhttpclient;
        } else
        {
            obj = new DefaultHttpClient();
        }
        httpparams = ((DefaultHttpClient) (obj)).getParams();
        HttpConnectionParams.setSoTimeout(httpparams, mTimeout);
        HttpConnectionParams.setConnectionTimeout(httpparams, mTimeout);
        HttpClientParams.setRedirecting(httpparams, mAutoRedirect);
        initHttpConnection(((DefaultHttpClient) (obj)));
        if (notEqualAndSetState(3, 1))
        {
            mHttpResponse = execute(((HttpClient) (obj)), mConnection);
            notEqualAndSetState(3, 2);
        }
    }

    public static int getNetWorkAvail(Context context)
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        NetworkInfo networkinfo;
        return -1;
_L2:
        if ((networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) goto _L1; else goto _L3
_L3:
        if (!networkinfo.isConnected()) goto _L1; else goto _L4
_L4:
        int i = networkinfo.getType();
        return i;
        Exception exception;
        exception;
        YLogger.log("YHttpRequest", exception.toString());
        return -1;
    }

    private StringEntity getStringEntity(String s, String s1)
    {
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_13;
        }
        return new StringEntity(s);
        StringEntity stringentity = new StringEntity(s, s1);
        return stringentity;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        YLogger.log("YHttpRequest", unsupportedencodingexception.toString());
        return null;
    }

    private void initHttpConnection(DefaultHttpClient defaulthttpclient)
    {
        if (!equqalState(3)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            if (YApplicationBase.getInstance() != null)
            {
                mConnection.setHeader("User-Agent", YApplicationBase.getUserAgent());
            }
            if (mAcceptGZip)
            {
                mConnection.setHeader("Accept-Encoding", "gzip");
                defaulthttpclient.addResponseInterceptor(new ResponseGzipUncompress());
            }
            if (mRequestHeader != null && mRequestHeader.size() > 0)
            {
                String s1;
                for (Iterator iterator1 = mRequestHeader.keySet().iterator(); iterator1.hasNext(); mConnection.setHeader(s1, (String)mRequestHeader.get(s1)))
                {
                    s1 = (String)iterator1.next();
                }

            }
        }
        catch (Exception exception)
        {
            YLogger.log("YHttpRequest", exception.toString());
            return;
        }
        if (mRequestBodyEntity == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mHttpMethod == 2)
        {
            ((HttpPost)mConnection).setEntity(mRequestBodyEntity);
            return;
        }
        if (mHttpMethod == 4)
        {
            ((HttpPut)mConnection).setEntity(mRequestBodyEntity);
            return;
        }
        if (mHttpMethod != 3) goto _L1; else goto _L3
_L3:
        ((YHttpDeleteWithBody)mConnection).setEntity(mRequestBodyEntity);
        return;
        if (mRequestBodyMap == null) goto _L1; else goto _L4
_L4:
        Map map = mRequestBodyMap;
        ArrayList arraylist;
        arraylist = null;
        if (map == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i = mRequestBodyMap.size();
        arraylist = null;
        if (i <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        arraylist = new ArrayList();
        String s;
        for (Iterator iterator = mRequestBodyMap.keySet().iterator(); iterator.hasNext(); arraylist.add(new BasicNameValuePair(s, (String)mRequestBodyMap.get(s))))
        {
            s = (String)iterator.next();
        }

        if (arraylist == null) goto _L1; else goto _L5
_L5:
        if (mHttpMethod == 2)
        {
            ((HttpPost)mConnection).setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
            return;
        }
        break MISSING_BLOCK_LABEL_396;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        YLogger.log("YHttpRequest", unsupportedencodingexception.toString());
        return;
        if (mHttpMethod == 4)
        {
            ((HttpPut)mConnection).setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
            return;
        }
        if (mHttpMethod != 3) goto _L1; else goto _L6
_L6:
        ((YHttpDeleteWithBody)mConnection).setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        return;
    }

    public static byte[] inputStreamToByteArray(InputStream inputstream)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[1024];
_L2:
        int i = inputstream.read(abyte0);
        if (i <= 0)
        {
            IOException ioexception;
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception1)
            {
                YLogger.log("YHttpRequest", ioexception1.toString());
            }
            return bytearrayoutputstream.toByteArray();
        }
        try
        {
            bytearrayoutputstream.write(abyte0, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            YLogger.log("YHttpRequest", ioexception.toString());
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String inputStreamToString(InputStream inputstream)
    {
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
_L1:
        String s = bufferedreader.readLine();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        stringbuilder.append((new StringBuilder()).append(s).append("\n").toString());
          goto _L1
        IOException ioexception2;
        ioexception2;
        YLogger.log("YHttpRequest", ioexception2.toString());
        IOException ioexception5;
        IOException ioexception6;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception3)
        {
            YLogger.log("YHttpRequest", ioexception3.toString());
        }
        try
        {
            bufferedreader.close();
        }
        catch (IOException ioexception4)
        {
            YLogger.log("YHttpRequest", ioexception4.toString());
        }
        return stringbuilder.toString();
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception5)
        {
            YLogger.log("YHttpRequest", ioexception5.toString());
        }
        try
        {
            bufferedreader.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception6)
        {
            YLogger.log("YHttpRequest", ioexception6.toString());
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        Exception exception;
        exception;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception)
        {
            YLogger.log("YHttpRequest", ioexception.toString());
        }
        try
        {
            bufferedreader.close();
        }
        catch (IOException ioexception1)
        {
            YLogger.log("YHttpRequest", ioexception1.toString());
        }
        throw exception;
    }

    public static boolean isNetWorkAvail(Context context)
    {
        return getNetWorkAvail(context) != -1;
    }

    private boolean notEqualAndSetState(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if (mReadyState != i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        mReadyState = j;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private void resetHttpConnection()
    {
        if (mConnection != null)
        {
            mConnection = null;
        }
    }

    private void setState(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mReadyState = i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void syncRequest(int i, HttpEntity httpentity, Map map)
    {
        mRequestBodyEntity = httpentity;
        mRequestBodyMap = map;
        mHttpMethod = i;
        createConnection();
        fetch();
    }

    public void asyncDelete()
    {
        asyncRequest(3, null, null);
    }

    public void asyncDelete(File file, String s)
    {
        asyncRequest(3, new FileEntity(file, s), null);
    }

    public void asyncDelete(String s, String s1)
    {
        asyncRequest(3, getStringEntity(s, s1), null);
    }

    public void asyncDelete(Map map)
    {
        asyncRequest(3, null, map);
    }

    public void asyncDelete(HttpEntity httpentity)
    {
        asyncRequest(3, httpentity, null);
    }

    public void asyncGet()
    {
        asyncRequest(1, null, null);
    }

    public void asyncPost(File file, String s)
    {
        asyncRequest(2, new FileEntity(file, s), null);
    }

    public void asyncPost(String s, String s1)
    {
        asyncRequest(2, getStringEntity(s, s1), null);
    }

    public void asyncPost(Map map)
    {
        asyncRequest(2, null, map);
    }

    public void asyncPost(HttpEntity httpentity)
    {
        asyncRequest(2, httpentity, null);
    }

    public void asyncPut(File file, String s)
    {
        asyncRequest(4, new FileEntity(file, s), null);
    }

    public void asyncPut(String s, String s1)
    {
        asyncRequest(4, getStringEntity(s, s1), null);
    }

    public void asyncPut(Map map)
    {
        asyncRequest(4, null, map);
    }

    public void asyncPut(HttpEntity httpentity)
    {
        asyncRequest(4, httpentity, null);
    }

    public void cancel()
    {
        if (equalEitherAndSetState(0, 1, 3))
        {
            abortHttpConnection();
            onCanceled();
        }
    }

    public void clearResponse()
    {
        mHttpResponse = null;
    }

    public Header[] getAllHeader()
    {
        if (mHttpResponse != null)
        {
            return mHttpResponse.getAllHeaders();
        } else
        {
            return null;
        }
    }

    public Header[] getHeader(String s)
    {
        if (mHttpResponse != null)
        {
            return mHttpResponse.getHeaders(s);
        } else
        {
            return null;
        }
    }

    public String getHeaderString(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Header aheader[] = getHeader(s);
        for (int i = 0; i < aheader.length; i++)
        {
            stringbuilder.append(aheader[i].getValue());
            stringbuilder.append(";");
        }

        return stringbuilder.toString();
    }

    public byte[] getResponseByteArray()
    {
        if (mHttpResponse == null)
        {
            return null;
        }
        byte abyte0[];
        try
        {
            abyte0 = inputStreamToByteArray(mHttpResponse.getEntity().getContent());
        }
        catch (IllegalStateException illegalstateexception)
        {
            YLogger.log("YHttpRequest", illegalstateexception.toString());
            return null;
        }
        catch (IOException ioexception)
        {
            YLogger.log("YHttpRequest", ioexception.toString());
            return null;
        }
        return abyte0;
    }

    public InputStream getResponseStream()
    {
        if (mHttpResponse == null)
        {
            return null;
        }
        InputStream inputstream;
        try
        {
            inputstream = mHttpResponse.getEntity().getContent();
        }
        catch (IllegalStateException illegalstateexception)
        {
            YLogger.log("YHttpRequest", illegalstateexception.toString());
            return null;
        }
        catch (IOException ioexception)
        {
            YLogger.log("YHttpRequest", ioexception.toString());
            return null;
        }
        return inputstream;
    }

    public String getResponseString()
    {
        if (mHttpResponse == null)
        {
            return "";
        }
        String s = inputStreamToString(mHttpResponse.getEntity().getContent());
        return s;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        YLogger.log("YHttpRequest", illegalstateexception.toString());
_L2:
        return "";
        IOException ioexception;
        ioexception;
        YLogger.log("YHttpRequest", ioexception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public int getStatusCode()
    {
        if (mHttpResponse == null)
        {
            return 0;
        } else
        {
            return mHttpResponse.getStatusLine().getStatusCode();
        }
    }

    public void onCanceled()
    {
    }

    public void onComplete()
    {
    }

    public boolean onCompleteInThread()
    {
        return true;
    }

    public void onTimeout()
    {
    }

    public void onTimeoutInThread()
    {
    }

    public void reset()
    {
        setState(0);
    }

    public void run()
    {
        fetch();
        if (!equqalState(3))
        {
            if (mTimeoutFlag)
            {
                onTimeoutInThread();
                Bundle bundle1 = new Bundle();
                bundle1.putString("Topic", "timeout");
                Message message1 = mHandler.obtainMessage();
                message1.setData(bundle1);
                mHandler.sendMessage(message1);
                return;
            }
            if (onCompleteInThread())
            {
                Bundle bundle = new Bundle();
                bundle.putString("Topic", "complete");
                Message message = mHandler.obtainMessage();
                message.setData(bundle);
                mHandler.sendMessage(message);
                return;
            }
        }
    }

    public boolean setAcceptGzip(boolean flag)
    {
        if (equqalState(1))
        {
            return false;
        } else
        {
            mAcceptGZip = flag;
            return true;
        }
    }

    public boolean setHeader(Map map)
    {
        if (equqalState(1))
        {
            return false;
        } else
        {
            mRequestHeader = map;
            return true;
        }
    }

    public boolean setRedirecting(boolean flag)
    {
        if (equqalState(1))
        {
            return false;
        } else
        {
            mAutoRedirect = flag;
            return true;
        }
    }

    public boolean setTimeout(int i)
    {
        if (equqalState(1))
        {
            return false;
        } else
        {
            mTimeout = i;
            return true;
        }
    }

    public boolean setURL(String s)
    {
        if (equqalState(1))
        {
            return false;
        } else
        {
            mURL = s;
            return true;
        }
    }

    public void syncDelete()
    {
        syncRequest(3, null, null);
    }

    public void syncGet()
    {
        syncRequest(1, null, null);
    }

    public void syncPost(File file, String s)
    {
        syncRequest(2, new FileEntity(file, s), null);
    }

    public void syncPost(String s, String s1)
    {
        syncRequest(2, getStringEntity(s, s1), null);
    }

    public void syncPost(Map map)
    {
        syncRequest(2, null, map);
    }

    public void syncPost(HttpEntity httpentity)
    {
        syncRequest(2, httpentity, null);
    }

    public void syncPut(File file, String s)
    {
        syncRequest(4, new FileEntity(file, s), null);
    }

    public void syncPut(String s, String s1)
    {
        syncRequest(4, getStringEntity(s, s1), null);
    }

    public void syncPut(Map map)
    {
        syncRequest(4, null, map);
    }

    public void syncPut(HttpEntity httpentity)
    {
        syncRequest(4, httpentity, null);
    }
}
