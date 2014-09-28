// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import jp.co.yahoo.android.common.HttpConfig;
import jp.co.yahoo.android.common.YHttpRequest;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager

private class setRequestData
{

    private boolean _downloading;
    private final Object _lock;
    private _requestData _requestData;
    private YHttpRequest _yHttpRequest;
    final YLoginServiceManager this$0;

    public boolean cancel()
    {
        Object obj = _lock;
        obj;
        JVM INSTR monitorenter ;
        if (!_downloading) goto _L2; else goto _L1
_L1:
        _downloading = false;
        boolean flag = true;
_L4:
        obj;
        JVM INSTR monitorexit ;
        if (flag && _yHttpRequest != null)
        {
            _yHttpRequest.cancel();
        }
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean fetch()
    {
        int i;
        Map map;
        String s2;
        String s3;
        String s4;
        String s5;
        if (_requestData == null)
        {
            return false;
        }
        String as[] = getCredential();
        String s = as[0];
        String s1 = as[1];
        HttpConfig httpconfig = _requestData.HttpConfig();
        i = httpconfig.getMethod();
        map = httpconfig.getRequestBodyMap();
        s2 = httpconfig.getRequestBodyString();
        s3 = httpconfig.getCharset();
        s4 = httpconfig.getUploadFilePath();
        s5 = httpconfig.getContentType();
        int j = httpconfig.getTimeout();
        boolean flag = httpconfig.isAuthApi();
        String s6 = _requestData.Url();
        StringBuilder stringbuilder = new StringBuilder(s6);
        Object obj = _requestData.Header();
        if (flag)
        {
            if (i == 2 && map != null)
            {
                map.put("appid", YLoginServiceManager.access$600(YLoginServiceManager.this));
                map.put("wssid", s1);
                if (YLoginServiceManager.access$800(YLoginServiceManager.this) == 1)
                {
                    YHttpRequest yhttprequest;
                    if (s6.contains("?"))
                    {
                        stringbuilder.append("&");
                    } else
                    {
                        stringbuilder.append("?");
                    }
                    stringbuilder.append("appid=");
                    stringbuilder.append(YLoginServiceManager.access$600(YLoginServiceManager.this));
                    stringbuilder.append("&wssid=");
                    stringbuilder.append(s1);
                }
            } else
            {
                if (s6.contains("?"))
                {
                    stringbuilder.append("&");
                } else
                {
                    stringbuilder.append("?");
                }
                stringbuilder.append("appid=");
                stringbuilder.append(YLoginServiceManager.access$600(YLoginServiceManager.this));
                stringbuilder.append("&wssid=");
                stringbuilder.append(s1);
            }
            if (obj == null)
            {
                obj = new HashMap();
            }
            ((Map) (obj)).put("Cookie", s);
        }
        yhttprequest = new YHttpRequest(YLoginServiceManager.access$900(YLoginServiceManager.this), stringbuilder.toString(), ((Map) (obj))) {

            final YLoginServiceManager.YJDNDownloader this$1;

            public boolean onCompleteInThread()
            {
label0:
                {
                    synchronized (_lock)
                    {
                        if (_downloading)
                        {
                            break label0;
                        }
                    }
                    return false;
                }
                String s7;
                Object obj3;
                boolean flag1;
                _downloading = false;
                s7 = _requestData.getUrl();
                obj3 = _requestData.getOptionalObj();
                flag1 = _requestData.getUseErrorCheck();
                obj2;
                JVM INSTR monitorexit ;
                YLoginServiceManager.access$1300(this$0, getStatusCode(), getResponseByteArray(), getAllHeader(), s7, obj3, flag1);
                clearResponse();
                return false;
                exception1;
                obj2;
                JVM INSTR monitorexit ;
                throw exception1;
            }

            public void onTimeoutInThread()
            {
label0:
                {
                    synchronized (_lock)
                    {
                        if (_downloading)
                        {
                            break label0;
                        }
                    }
                    return;
                }
                String s7;
                Object obj3;
                boolean flag1;
                _downloading = false;
                s7 = _requestData.getUrl();
                obj3 = _requestData.getOptionalObj();
                flag1 = _requestData.getUseErrorCheck();
                obj2;
                JVM INSTR monitorexit ;
                YLoginServiceManager.access$1400(this$0, getStatusCode(), getResponseByteArray(), getAllHeader(), s7, obj3, flag1);
                clearResponse();
                return;
                exception1;
                obj2;
                JVM INSTR monitorexit ;
                throw exception1;
            }

            
            {
                this$1 = YLoginServiceManager.YJDNDownloader.this;
                super(context, s, map);
            }
        };
        _yHttpRequest = yhttprequest;
        _yHttpRequest.setTimeout(j);
        synchronized (_lock)
        {
            _downloading = true;
        }
        if (i != 2) goto _L2; else goto _L1
_L1:
        if (map == null) goto _L4; else goto _L3
_L3:
        _yHttpRequest.asyncPost(map);
_L6:
        return true;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        if (s2 != null)
        {
            _yHttpRequest.asyncPost(s2, s3);
        } else
        if (s4 != null)
        {
            YHttpRequest yhttprequest3 = _yHttpRequest;
            File file2 = new File(s4);
            yhttprequest3.asyncPost(file2, s5);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (i == 3)
        {
            if (map != null)
            {
                _yHttpRequest.asyncDelete(map);
            } else
            if (s2 != null)
            {
                _yHttpRequest.asyncDelete(s2, s3);
            } else
            if (s4 != null)
            {
                YHttpRequest yhttprequest2 = _yHttpRequest;
                File file1 = new File(s4);
                yhttprequest2.asyncDelete(file1, s5);
            } else
            {
                _yHttpRequest.asyncDelete();
            }
        } else
        if (i == 4)
        {
            if (map != null)
            {
                _yHttpRequest.asyncPut(map);
            } else
            if (s2 != null)
            {
                _yHttpRequest.asyncPut(s2, s3);
            } else
            if (s4 != null)
            {
                YHttpRequest yhttprequest1 = _yHttpRequest;
                File file = new File(s4);
                yhttprequest1.asyncPut(file, s5);
            }
        } else
        {
            _yHttpRequest.asyncGet();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public String getDownloadingUrl()
    {
        String s;
        _yHttpRequest _lyhttprequest = _requestData;
        s = null;
        if (_lyhttprequest != null)
        {
            s = _requestData.Url();
        }
        Object obj = _lock;
        obj;
        JVM INSTR monitorenter ;
        if (_downloading)
        {
            return s;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public _downloading getRequestData()
    {
        return _requestData;
    }

    public boolean isDownloading()
    {
        boolean flag;
        synchronized (_lock)
        {
            flag = _downloading;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setRequestData(_downloading _pdownloading)
    {
        _requestData = _pdownloading;
    }




/*
    static boolean access$1102(_cls1 _pcls1, boolean flag)
    {
        _pcls1._downloading = flag;
        return flag;
    }

*/


    public _cls1.this._cls1()
    {
        this$0 = YLoginServiceManager.this;
        super();
        _yHttpRequest = null;
        _downloading = false;
        _requestData = null;
        _lock = new Object();
    }

    public _lock(_lock _plock)
    {
        this$0 = YLoginServiceManager.this;
        super();
        _yHttpRequest = null;
        _downloading = false;
        _requestData = null;
        _lock = new Object();
        setRequestData(_plock);
    }
}
