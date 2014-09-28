// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHeader;

public class GzipCompressingEntity extends HttpEntityWrapper
{

    private static final String GZIP_CODEC = "gzip";

    public GzipCompressingEntity(HttpEntity httpentity)
    {
        super(httpentity);
    }

    public Header getContentEncoding()
    {
        return new BasicHeader("Content-Encoding", "gzip");
    }

    public long getContentLength()
    {
        return -1L;
    }

    public boolean isChunked()
    {
        return true;
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        if (outputstream == null)
        {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        GZIPOutputStream gzipoutputstream = new GZIPOutputStream(outputstream);
        InputStream inputstream = wrappedEntity.getContent();
        byte abyte0[] = new byte[2048];
        do
        {
            int i = inputstream.read(abyte0);
            if (i != -1)
            {
                gzipoutputstream.write(abyte0, 0, i);
            } else
            {
                gzipoutputstream.close();
                return;
            }
        } while (true);
    }
}
