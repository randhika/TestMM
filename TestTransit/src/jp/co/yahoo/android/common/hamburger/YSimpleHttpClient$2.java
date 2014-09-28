// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YSimpleHttpClient

class this._cls0
    implements HttpResponseInterceptor
{

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
            httpresponse.setEntity(new flatingEntity(httpresponse.getEntity()));
            return;
        }
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    flatingEntity()
    {
        this$0 = YSimpleHttpClient.this;
        super();
    }
}
