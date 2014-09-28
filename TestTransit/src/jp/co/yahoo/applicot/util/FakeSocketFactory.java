// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

// Referenced classes of package jp.co.yahoo.applicot.util:
//            NaiveTrustManager

public class FakeSocketFactory
    implements SocketFactory, LayeredSocketFactory
{

    private SSLContext sslcontext;

    public FakeSocketFactory()
    {
        sslcontext = null;
    }

    private static SSLContext createEasySSLContext()
        throws IOException
    {
        SSLContext sslcontext1;
        try
        {
            sslcontext1 = SSLContext.getInstance("TLS");
            TrustManager atrustmanager[] = new TrustManager[1];
            atrustmanager[0] = new NaiveTrustManager();
            sslcontext1.init(null, atrustmanager, null);
        }
        catch (Exception exception)
        {
            throw new IOException(exception.getMessage());
        }
        return sslcontext1;
    }

    private SSLContext getSSLContext()
        throws IOException
    {
        if (sslcontext == null)
        {
            sslcontext = createEasySSLContext();
        }
        return sslcontext;
    }

    public Socket connectSocket(Socket socket, String s, int i, InetAddress inetaddress, int j, HttpParams httpparams)
        throws IOException
    {
        int k = HttpConnectionParams.getConnectionTimeout(httpparams);
        int l = HttpConnectionParams.getSoTimeout(httpparams);
        InetSocketAddress inetsocketaddress = new InetSocketAddress(s, i);
        Socket socket1;
        SSLSocket sslsocket;
        if (socket != null)
        {
            socket1 = socket;
        } else
        {
            socket1 = createSocket();
        }
        sslsocket = (SSLSocket)(SSLSocket)socket1;
        if (inetaddress != null || j > 0)
        {
            if (j < 0)
            {
                j = 0;
            }
            sslsocket.bind(new InetSocketAddress(inetaddress, j));
        }
        sslsocket.connect(inetsocketaddress, k);
        sslsocket.setSoTimeout(l);
        return sslsocket;
    }

    public Socket createSocket()
        throws IOException
    {
        return getSSLContext().getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String s, int i, boolean flag)
        throws IOException
    {
        return getSSLContext().getSocketFactory().createSocket(socket, s, i, flag);
    }

    public boolean isSecure(Socket socket)
        throws IllegalArgumentException
    {
        return true;
    }
}
