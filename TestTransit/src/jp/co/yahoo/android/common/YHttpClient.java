// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRequestDirector;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHosts, YLog, YPackageManager, YApplicationBase, 
//            RequestAcceptEncoding, ResponseGzipUncompress

public class YHttpClient extends DefaultHttpClient
{
    private class RequestDirector extends DefaultRequestDirector
    {

        final YHttpClient this$0;

        public HttpResponse execute(HttpHost httphost, HttpRequest httprequest, HttpContext httpcontext)
            throws HttpException, IOException
        {
            convertUrl(httprequest);
            URI uri = ((HttpRequestBase)httprequest).getURI();
            String s = uri.getHost();
            String s1 = uri.getScheme();
            return super.execute(new HttpHost(s, uri.getPort(), s1), httprequest, httpcontext);
        }

        public RequestDirector(HttpRequestExecutor httprequestexecutor, ClientConnectionManager clientconnectionmanager, ConnectionReuseStrategy connectionreusestrategy, ConnectionKeepAliveStrategy connectionkeepalivestrategy, HttpRoutePlanner httprouteplanner, HttpProcessor httpprocessor, 
                HttpRequestRetryHandler httprequestretryhandler, RedirectHandler redirecthandler, AuthenticationHandler authenticationhandler, AuthenticationHandler authenticationhandler1, UserTokenHandler usertokenhandler, HttpParams httpparams)
        {
            this$0 = YHttpClient.this;
            super(httprequestexecutor, clientconnectionmanager, connectionreusestrategy, connectionkeepalivestrategy, httprouteplanner, httpprocessor, httprequestretryhandler, redirecthandler, authenticationhandler, authenticationhandler1, usertokenhandler, httpparams);
        }
    }

    private static final class UntrustManager
        implements X509TrustManager
    {

        private final ArrayList _issuers = new ArrayList();

        private boolean hasCertificate(X509Certificate x509certificate)
        {
            int i = _issuers.size();
            for (int j = 0; j < i; j++)
            {
                if (x509certificate.equals(_issuers.get(j)))
                {
                    return true;
                }
            }

            return false;
        }

        public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
            if (ax509certificate != null)
            {
                for (int i = 0; i < ax509certificate.length; i++)
                {
                    if (!hasCertificate(ax509certificate[i]))
                    {
                        _issuers.add(ax509certificate[i]);
                    }
                }

            }
        }

        public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
            if (ax509certificate != null)
            {
                for (int i = 0; i < ax509certificate.length; i++)
                {
                    if (!hasCertificate(ax509certificate[i]))
                    {
                        _issuers.add(ax509certificate[i]);
                    }
                }

            }
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return (X509Certificate[])_issuers.toArray(new X509Certificate[0]);
        }

        public UntrustManager()
        {
        }
    }

    private static class UntrustSSLSocketFactory extends SSLSocketFactory
    {

        private final SSLContext _context;

        public Socket connectSocket(Socket socket, String s, int i, InetAddress inetaddress, int j, HttpParams httpparams)
            throws IOException
        {
            return super.connectSocket(socket, s, i, inetaddress, j, httpparams);
        }

        public Socket createSocket()
            throws IOException
        {
            return _context.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket socket, String s, int i, boolean flag)
            throws IOException, UnknownHostException
        {
            return _context.getSocketFactory().createSocket(socket, s, i, flag);
        }

        public UntrustSSLSocketFactory()
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
        {
            super(null);
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] {
                new UntrustManager()
            }, null);
            _context = sslcontext;
            setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
    }


    private static final String TAG = jp/co/yahoo/android/common/YHttpClient.getSimpleName();
    private boolean mEnableRedirect;
    private YHosts mHosts;
    private int mTimeout;

    public YHttpClient()
    {
        mHosts = null;
        mEnableRedirect = true;
        mTimeout = 0;
    }

    public YHttpClient(ClientConnectionManager clientconnectionmanager, HttpParams httpparams)
    {
        super(clientconnectionmanager, httpparams);
        mHosts = null;
        mEnableRedirect = true;
        mTimeout = 0;
    }

    public YHttpClient(HttpParams httpparams)
    {
        super(httpparams);
        mHosts = null;
        mEnableRedirect = true;
        mTimeout = 0;
    }

    private void convertUrl(HttpRequest httprequest)
    {
        if (mHosts != null && (httprequest instanceof HttpRequestBase))
        {
            HttpRequestBase httprequestbase = (HttpRequestBase)httprequest;
            mHosts.convertURL(httprequestbase);
            YLog.e(TAG, httprequestbase.getURI().toString());
        }
    }

    public static boolean isNetworkAvailable(Context context)
    {
        NetworkInfo networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkinfo == null)
        {
            return false;
        } else
        {
            return networkinfo.isConnected();
        }
    }

    public void acceptUntrustedSslCertificate(Context context)
    {
        if (!YPackageManager.isDebugSignature(context))
        {
            return;
        }
        YLog.e(TAG, "******* accept untrusted certificates *******");
        try
        {
            Scheme scheme = new Scheme("https", new UntrustSSLSocketFactory(), 443);
            getConnectionManager().getSchemeRegistry().register(scheme);
            return;
        }
        catch (KeyManagementException keymanagementexception)
        {
            YLog.e(TAG, keymanagementexception.getMessage());
            keymanagementexception.printStackTrace();
            return;
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            YLog.e(TAG, nosuchalgorithmexception.getMessage());
            nosuchalgorithmexception.printStackTrace();
            return;
        }
        catch (KeyStoreException keystoreexception)
        {
            YLog.e(TAG, keystoreexception.getMessage());
            keystoreexception.printStackTrace();
            return;
        }
        catch (UnrecoverableKeyException unrecoverablekeyexception)
        {
            YLog.e(TAG, unrecoverablekeyexception.getMessage());
            unrecoverablekeyexception.printStackTrace();
            return;
        }
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
                    throw new IllegalStateException((new StringBuilder()).append("Invalid class name: ").append(s).toString());
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

    protected RequestDirector createClientRequestDirector(HttpRequestExecutor httprequestexecutor, ClientConnectionManager clientconnectionmanager, ConnectionReuseStrategy connectionreusestrategy, ConnectionKeepAliveStrategy connectionkeepalivestrategy, HttpRoutePlanner httprouteplanner, HttpProcessor httpprocessor, HttpRequestRetryHandler httprequestretryhandler, 
            RedirectHandler redirecthandler, AuthenticationHandler authenticationhandler, AuthenticationHandler authenticationhandler1, UserTokenHandler usertokenhandler, HttpParams httpparams)
    {
        return new RequestDirector(httprequestexecutor, clientconnectionmanager, connectionreusestrategy, connectionkeepalivestrategy, httprouteplanner, httpprocessor, httprequestretryhandler, redirecthandler, authenticationhandler, authenticationhandler1, usertokenhandler, httpparams);
    }

    protected volatile org.apache.http.client.RequestDirector createClientRequestDirector(HttpRequestExecutor httprequestexecutor, ClientConnectionManager clientconnectionmanager, ConnectionReuseStrategy connectionreusestrategy, ConnectionKeepAliveStrategy connectionkeepalivestrategy, HttpRoutePlanner httprouteplanner, HttpProcessor httpprocessor, HttpRequestRetryHandler httprequestretryhandler, 
            RedirectHandler redirecthandler, AuthenticationHandler authenticationhandler, AuthenticationHandler authenticationhandler1, UserTokenHandler usertokenhandler, HttpParams httpparams)
    {
        return createClientRequestDirector(httprequestexecutor, clientconnectionmanager, connectionreusestrategy, connectionkeepalivestrategy, httprouteplanner, httpprocessor, httprequestretryhandler, redirecthandler, authenticationhandler, authenticationhandler1, usertokenhandler, httpparams);
    }

    protected HttpParams createHttpParams()
    {
        HttpParams httpparams = super.createHttpParams();
        HttpProtocolParams.setUserAgent(httpparams, YApplicationBase.getBrowserUserAgent());
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
        addRequestInterceptor(new RequestAcceptEncoding());
        addResponseInterceptor(new ResponseGzipUncompress());
    }

    public void enableRedirect(boolean flag)
    {
        mEnableRedirect = flag;
    }

    public void enableYHosts(Context context)
    {
        mHosts = YHosts.getInstance(context);
    }

    public void enableYHosts(Context context, int i)
    {
        mHosts = YHosts.getInstance(context);
        YHosts.setEnable(i);
    }

    public void setTimeout(int i)
    {
        mTimeout = i;
    }


}
