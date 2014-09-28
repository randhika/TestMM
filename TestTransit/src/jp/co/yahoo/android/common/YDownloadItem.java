// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.InputStream;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDownloadListener

public class YDownloadItem
{

    public int _code;
    public int _contentLength;
    public InputStream _input;
    private YDownloadListener _listener;
    private Object _optionalObj;
    private Map _requestProperty;
    private String _url;

    public YDownloadItem(String s, YDownloadListener ydownloadlistener, Map map, Object obj)
    {
        _contentLength = -1;
        _url = s;
        _listener = ydownloadlistener;
        _requestProperty = map;
        _optionalObj = obj;
    }

    public int getContentLength()
    {
        return _contentLength;
    }

    public InputStream getInputStream()
    {
        return _input;
    }

    public YDownloadListener getListener()
    {
        return _listener;
    }

    public Object getOptionalObj()
    {
        return _optionalObj;
    }

    public Map getRequestProperty()
    {
        return _requestProperty;
    }

    public int getStatusCode()
    {
        return _code;
    }

    public String getUrl()
    {
        return _url;
    }
}
