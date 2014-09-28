// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.X509TrustManager;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpClient

private static final class 
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

    public ()
    {
    }
}
