// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager, YLoginServiceListener

class this._cls0 extends Handler
{

    final YLoginServiceManager this$0;

    public void handleMessage(Message message)
    {
        String s = message.getData().getString("Topic");
        if (!"on-login-changed".equals(s)) goto _L2; else goto _L1
_L1:
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) == null) goto _L4; else goto _L3
_L3:
        if (!isLogin()) goto _L6; else goto _L5
_L5:
        YLoginServiceManager.access$000(YLoginServiceManager.this).onLogin();
_L4:
        return;
_L6:
        YLoginServiceManager.access$000(YLoginServiceManager.this).onLogout();
        return;
_L2:
        if (!"on-login-failed".equals(s))
        {
            break; /* Loop/switch isn't completed */
        }
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onLoginFailed(message.getData().getString("Data"));
            return;
        }
        if (true) goto _L4; else goto _L7
_L7:
        if (!"on-login-canceled".equals(s))
        {
            break; /* Loop/switch isn't completed */
        }
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onLoginCanceled();
            return;
        }
        if (true) goto _L4; else goto _L8
_L8:
        if ("on-update-credential".equals(s))
        {
            if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
            {
                YLoginServiceManager.access$000(YLoginServiceManager.this).onUpdateCredential();
            }
            YLoginServiceManager.access$100(YLoginServiceManager.this);
            return;
        }
        if (!"on-update-credential-failed".equals(s))
        {
            continue; /* Loop/switch isn't completed */
        }
        java.util.LinkedList linkedlist1 = YLoginServiceManager.access$200(YLoginServiceManager.this);
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNDownloadFailedAtConverter(message.getData().getString("Data"), false, YLoginServiceManager.access$300(YLoginServiceManager.this, linkedlist1), YLoginServiceManager.access$400(YLoginServiceManager.this, linkedlist1));
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (!"on-update-credential-invalidtoken".equals(s)) goto _L4; else goto _L9
_L9:
        java.util.LinkedList linkedlist = YLoginServiceManager.access$200(YLoginServiceManager.this);
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onYJDNDownloadFailedAtConverter(message.getData().getString("Data"), true, YLoginServiceManager.access$300(YLoginServiceManager.this, linkedlist), YLoginServiceManager.access$400(YLoginServiceManager.this, linkedlist));
            return;
        }
        if (true) goto _L4; else goto _L10
_L10:
    }

    ()
    {
        this$0 = YLoginServiceManager.this;
        super();
    }
}
