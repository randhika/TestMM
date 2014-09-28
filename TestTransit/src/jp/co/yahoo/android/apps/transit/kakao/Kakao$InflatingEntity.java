// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

private static class  extends HttpEntityWrapper
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

    public (HttpEntity httpentity)
    {
        super(httpentity);
    }
}
