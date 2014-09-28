// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager, YLoginServiceListener, YJDNErrorData

class val.errorData extends Handler
{

    final YLoginServiceManager this$0;
    final byte val$bytes[];
    final int val$code;
    final YJDNErrorData val$errorData;
    final Header val$headers[];
    final Object val$optionalObj;
    final int val$status;
    final String val$url;
    final boolean val$useErrorCheck;

    public void handleMessage(Message message)
    {
        if (!val$useErrorCheck) goto _L2; else goto _L1
_L1:
        if (val$status != 0 || YLoginServiceManager.access$000(YLoginServiceManager.this) == null) goto _L4; else goto _L3
_L3:
        YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNDownloaded(val$bytes, val$headers, val$code, val$url, val$optionalObj);
_L6:
        YLoginServiceManager.access$100(YLoginServiceManager.this);
        return;
_L4:
        if (val$status == 1 && YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNDownloadFailed(val$errorData, val$bytes, val$headers, val$code, val$url, val$optionalObj);
        } else
        if (val$status == 2 && YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNHttpError(val$bytes, val$headers, val$code, false, val$url, val$optionalObj);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNResponsed(val$bytes, val$headers, val$code, val$url, val$optionalObj);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    (YJDNErrorData yjdnerrordata)
    {
        this$0 = final_yloginservicemanager;
        val$useErrorCheck = flag;
        val$status = i;
        val$bytes = abyte0;
        val$headers = aheader;
        val$code = j;
        val$url = s;
        val$optionalObj = Object.this;
        val$errorData = yjdnerrordata;
        super(final_looper);
    }
}
