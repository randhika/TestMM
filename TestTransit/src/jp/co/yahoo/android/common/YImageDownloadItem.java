// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.graphics.drawable.Drawable;

// Referenced classes of package jp.co.yahoo.android.common:
//            YImageDownloadListener

public class YImageDownloadItem
{

    Drawable _drawable;
    private YImageDownloadListener _listener;
    private Object _optionalObj;
    private String _url;

    public YImageDownloadItem(String s, YImageDownloadListener yimagedownloadlistener, Object obj)
    {
        _url = s;
        _listener = yimagedownloadlistener;
        _optionalObj = obj;
    }

    public Drawable getDrawable()
    {
        return _drawable;
    }

    public YImageDownloadListener getListener()
    {
        return _listener;
    }

    public Object getOptionalObj()
    {
        return _optionalObj;
    }

    public String getUrl()
    {
        return _url;
    }
}
