// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import jp.co.yahoo.android.common.YLogger;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager, IYLoginService, YLoginServiceListener

class this._cls0
    implements ServiceConnection
{

    final YLoginServiceManager this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        YLoginServiceManager.access$502(YLoginServiceManager.this, nterface(ibinder));
        try
        {
            YLoginServiceManager.access$500(YLoginServiceManager.this).setAppid(YLoginServiceManager.access$600(YLoginServiceManager.this));
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onServiceConnected();
        }
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        YLoginServiceManager.access$502(YLoginServiceManager.this, null);
        if (YLoginServiceManager.access$000(YLoginServiceManager.this) != null)
        {
            YLoginServiceManager.access$000(YLoginServiceManager.this).onServiceDisconnected();
        }
    }

    ()
    {
        this$0 = YLoginServiceManager.this;
        super();
    }
}
