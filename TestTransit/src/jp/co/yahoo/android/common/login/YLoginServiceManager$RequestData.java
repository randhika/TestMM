// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import java.util.Map;
import jp.co.yahoo.android.common.HttpConfig;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager

private static class _useErrorCheck
{

    private HttpConfig _config;
    private Map _header;
    private Object _optionalObj;
    private String _url;
    private boolean _useErrorCheck;

    public Map getHeader()
    {
        return _header;
    }

    public HttpConfig getHttpConfig()
    {
        return _config;
    }

    public Object getOptionalObj()
    {
        return _optionalObj;
    }

    public String getUrl()
    {
        return _url;
    }

    public boolean getUseErrorCheck()
    {
        return _useErrorCheck;
    }

    public (String s, Map map, HttpConfig httpconfig, Object obj, boolean flag)
    {
        _url = s;
        _header = map;
        _config = httpconfig;
        _optionalObj = obj;
        _useErrorCheck = flag;
    }
}
