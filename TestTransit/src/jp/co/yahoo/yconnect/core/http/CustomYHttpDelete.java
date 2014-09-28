// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.http;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class CustomYHttpDelete extends HttpEntityEnclosingRequestBase
{

    public static final String METHOD_NAME = "DELETE";

    public CustomYHttpDelete()
    {
    }

    public CustomYHttpDelete(String s)
    {
        setURI(URI.create(s));
    }

    public CustomYHttpDelete(URI uri)
    {
        setURI(uri);
    }

    public String getMethod()
    {
        return "DELETE";
    }
}
