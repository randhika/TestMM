// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager, YLoginServiceListener

class val.optionalObj extends Handler
{

    final YLoginServiceManager this$0;
    final byte val$bytes[];
    final int val$code;
    final Header val$headers[];
    final Object val$optionalObj;
    final String val$url;
    final boolean val$useErrorCheck;

    public void handleMessage(Message message)
    {
        if (!val$useErrorCheck) goto _L2; else goto _L1
_L1:
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNHttpError(val$bytes, val$headers, val$code, true, val$url, val$optionalObj);
        }
_L4:
        YLoginServiceManager.access$100(YLoginServiceManager.this);
        return;
_L2:
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNResponsed(val$bytes, val$headers, val$code, val$url, val$optionalObj);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    (Object obj)
    {
        this$0 = final_yloginservicemanager;
        val$useErrorCheck = flag;
        val$bytes = abyte0;
        val$headers = aheader;
        val$code = i;
        val$url = String.this;
        val$optionalObj = obj;
        super(final_looper);
    }
}
