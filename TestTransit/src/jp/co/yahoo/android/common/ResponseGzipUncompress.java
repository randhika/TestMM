// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package jp.co.yahoo.android.common:
//            GzipDecompressingEntity

public class ResponseGzipUncompress
    implements HttpResponseInterceptor
{

    private static final String GZIP_CODEC = "gzip";

    public ResponseGzipUncompress()
    {
    }

    public void process(HttpResponse httpresponse, HttpContext httpcontext)
        throws HttpException, IOException
    {
        HttpEntity httpentity;
        if (httpcontext == null)
        {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        httpentity = httpresponse.getEntity();
        if (httpentity != null) goto _L2; else goto _L1
_L1:
        Header header;
        return;
_L2:
        if ((header = httpentity.getContentEncoding()) != null)
        {
            HeaderElement aheaderelement[] = header.getElements();
            int i = 0;
            while (i < aheaderelement.length) 
            {
                if (aheaderelement[i].getName().equalsIgnoreCase("gzip"))
                {
                    httpresponse.setEntity(new GzipDecompressingEntity(httpresponse.getEntity()));
                    return;
                }
                i++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }
}
