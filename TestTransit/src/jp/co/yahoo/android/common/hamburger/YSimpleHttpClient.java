// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGUtils

public class YSimpleHttpClient extends DefaultHttpClient
{
    private static class InflatingEntity extends HttpEntityWrapper
    {

        public InputStream getContent()
            throws IOException
        {
            return new GZIPInputStream(wrappedEntity.getContent());
        }

        public long getContentLength()
        {
            return -1L;
        }

        public InflatingEntity(HttpEntity httpentity)
        {
            super(httpentity);
        }
    }


    private static final String DEFAULT_USERAGENT = "Mozilla/5.0 (Linux; U; Android 4.0.1; ja-jp; Galaxy Nexus Build/ITL41D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
    private static final String ENCODING_GZIP = "gzip";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String TAG = "YHamburger";
    private static String sBrowserUserAgent;
    private static String sUserAgent;
    private boolean mEnableRedirect;
    private int mTimeout;

    public YSimpleHttpClient()
    {
        mEnableRedirect = true;
        mTimeout = 0;
    }

    public YSimpleHttpClient(ClientConnectionManager clientconnectionmanager, HttpParams httpparams)
    {
        super(clientconnectionmanager, httpparams);
        mEnableRedirect = true;
        mTimeout = 0;
    }

    public YSimpleHttpClient(HttpParams httpparams)
    {
        super(httpparams);
        mEnableRedirect = true;
        mTimeout = 0;
    }

    public static String getBrowserUserAgent(Context context)
    {
        if (sBrowserUserAgent == null)
        {
            sBrowserUserAgent = (new WebView(context)).getSettings().getUserAgentString();
        }
        return sBrowserUserAgent;
    }

    public static String getUserAgent()
    {
        jp/co/yahoo/android/common/hamburger/YSimpleHttpClient;
        JVM INSTR monitorenter ;
        if (sUserAgent == null)
        {
            if (sBrowserUserAgent != null)
            {
                break MISSING_BLOCK_LABEL_29;
            }
            sUserAgent = "Mozilla/5.0 (Linux; U; Android 4.0.1; ja-jp; Galaxy Nexus Build/ITL41D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
        }
_L1:
        String s = sUserAgent;
        jp/co/yahoo/android/common/hamburger/YSimpleHttpClient;
        JVM INSTR monitorexit ;
        return s;
        sUserAgent = sBrowserUserAgent;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean isNetworkAvailable(Context context)
    {
        NetworkInfo networkinfo;
        boolean flag;
        boolean flag1;
        try
        {
            networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        }
        catch (SecurityException securityexception)
        {
            YHBGUtils.debug("ACCESS_NETWORK_STATE\u306E\u6A29\u9650\u304C\u3042\u308A\u307E\u305B\u3093");
            return true;
        }
        flag = false;
        if (networkinfo == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        flag1 = networkinfo.isConnected();
        flag = flag1;
        return flag;
    }

    public static void setUserAgent(String s)
    {
        jp/co/yahoo/android/common/hamburger/YSimpleHttpClient;
        JVM INSTR monitorenter ;
        sUserAgent = s;
        jp/co/yahoo/android/common/hamburger/YSimpleHttpClient;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected ClientConnectionManager createClientConnectionManager()
    {
        SchemeRegistry schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        HttpParams httpparams = getParams();
        ClientConnectionManagerFactory clientconnectionmanagerfactory = (ClientConnectionManagerFactory)httpparams.getParameter("http.connection-manager.factory-object");
        if (clientconnectionmanagerfactory == null)
        {
            String s = (String)httpparams.getParameter("http.connection-manager.factory-class-name");
            if (s != null)
            {
                try
                {
                    clientconnectionmanagerfactory = (ClientConnectionManagerFactory)Class.forName(s).newInstance();
                }
                catch (ClassNotFoundException classnotfoundexception)
                {
                    throw new IllegalStateException((new StringBuilder("Invalid class name: ")).append(s).toString());
                }
                catch (IllegalAccessException illegalaccessexception)
                {
                    throw new IllegalAccessError(illegalaccessexception.getMessage());
                }
                catch (InstantiationException instantiationexception)
                {
                    throw new InstantiationError(instantiationexception.getMessage());
                }
            }
        }
        if (clientconnectionmanagerfactory != null)
        {
            return clientconnectionmanagerfactory.newInstance(httpparams, schemeregistry);
        } else
        {
            return new ThreadSafeClientConnManager(getParams(), schemeregistry);
        }
    }

    protected HttpParams createHttpParams()
    {
        HttpParams httpparams = super.createHttpParams();
        HttpProtocolParams.setUserAgent(httpparams, getUserAgent());
        if (mTimeout != 0)
        {
            HttpConnectionParams.setConnectionTimeout(httpparams, mTimeout);
            HttpConnectionParams.setSoTimeout(httpparams, mTimeout);
        }
        httpparams.setParameter("http.protocol.handle-redirects", Boolean.valueOf(mEnableRedirect));
        return httpparams;
    }

    public void enableAcceptGzip()
    {
        addRequestInterceptor(new HttpRequestInterceptor() {

            final YSimpleHttpClient this$0;

            public void process(HttpRequest httprequest, HttpContext httpcontext)
            {
                if (!httprequest.containsHeader("Accept-Encoding"))
                {
                    httprequest.addHeader("Accept-Encoding", "gzip");
                }
            }

            
            {
                this$0 = YSimpleHttpClient.this;
                super();
            }
        });
        addResponseInterceptor(new HttpResponseInterceptor() {

            final YSimpleHttpClient this$0;

            public void process(HttpResponse httpresponse, HttpContext httpcontext)
            {
                Header header = httpresponse.getEntity().getContentEncoding();
                if (header == null) goto _L2; else goto _L1
_L1:
                HeaderElement aheaderelement[];
                int i;
                int j;
                aheaderelement = header.getElements();
                i = aheaderelement.length;
                j = 0;
_L5:
                if (j < i) goto _L3; else goto _L2
_L2:
                return;
_L3:
                if (aheaderelement[j].getName().equalsIgnoreCase("gzip"))
                {
                    httpresponse.setEntity(new InflatingEntity(httpresponse.getEntity()));
                    return;
                }
                j++;
                if (true) goto _L5; else goto _L4
_L4:
            }

            
            {
                this$0 = YSimpleHttpClient.this;
                super();
            }
        });
    }

    public void enableRedirect(boolean flag)
    {
        mEnableRedirect = flag;
    }

    public void setTimeout(int i)
    {
        mTimeout = i;
    }
}
