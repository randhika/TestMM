// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import jp.co.yahoo.android.common.YHttpRequest;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginService, IYLoginServiceCallback

class tub extends tub
{

    final YLoginService this$0;

    public void cancelCredential()
        throws RemoteException
    {
        if (YLoginService.access$2000(YLoginService.this))
        {
            YLoginService.access$2202(YLoginService.this, false);
            if (YLoginService.access$2300(YLoginService.this) != null)
            {
                YLoginService.access$2300(YLoginService.this).cancel();
            }
            YLoginService.access$1900(YLoginService.this, "", "fetch-credential-canceled", "");
        }
    }

    public void cancelLogin()
        throws RemoteException
    {
        if (YLoginService.access$1600(YLoginService.this))
        {
            YLoginService.access$1602(YLoginService.this, false);
            if (YLoginService.access$1800(YLoginService.this) != null)
            {
                YLoginService.access$1800(YLoginService.this).cancel();
            }
            YLoginService.access$1900(YLoginService.this, "", "fetch-token-canceled", "");
        }
    }

    public String getCredential()
        throws RemoteException
    {
        return YLoginService.access$400(YLoginService.this);
    }

    public String getGuid()
        throws RemoteException
    {
        return YLoginService.access$300(YLoginService.this);
    }

    public String getStoredYID()
        throws RemoteException
    {
        return YLoginService.access$100(YLoginService.this, YLoginService.access$000(YLoginService.this), "");
    }

    public String getYID()
        throws RemoteException
    {
        return YLoginService.access$200(YLoginService.this);
    }

    public boolean hasCredential()
        throws RemoteException
    {
        return YLoginService.access$500(YLoginService.this);
    }

    public boolean hasToken()
        throws RemoteException
    {
        while (YLoginService.access$600(YLoginService.this).equals("") || YLoginService.access$200(YLoginService.this).equals("")) 
        {
            return false;
        }
        return true;
    }

    public boolean isAutoLogin()
        throws RemoteException
    {
        return YLoginService.access$800(YLoginService.this, YLoginService.access$700(YLoginService.this), true);
    }

    public void login(String s, String s1, boolean flag)
        throws RemoteException
    {
        YLoginService.access$900(YLoginService.this, YLoginService.access$700(YLoginService.this), flag);
        YLoginService.access$1402(YLoginService.this, s);
        YLoginService.access$1502(YLoginService.this, s1);
        YLoginService.access$1602(YLoginService.this, true);
        YLoginService.access$1700(YLoginService.this);
    }

    public void logout()
        throws RemoteException
    {
        YLoginService.access$202(YLoginService.this, "");
        YLoginService.access$1502(YLoginService.this, "");
        YLoginService.access$602(YLoginService.this, "");
        YLoginService.access$302(YLoginService.this, "");
        YLoginService.access$402(YLoginService.this, "");
        if (!YLoginService.access$800(YLoginService.this, YLoginService.access$700(YLoginService.this), true))
        {
            YLoginService.access$1300(YLoginService.this, YLoginService.access$000(YLoginService.this), "");
        }
        YLoginService.access$1300(YLoginService.this, YLoginService.access$1200(YLoginService.this), "");
        YLoginService.access$1300(YLoginService.this, YLoginService.access$1100(YLoginService.this), "");
        YLoginService.access$1900(YLoginService.this, "", "logout-done", "");
    }

    public void registerCallback(IYLoginServiceCallback iyloginservicecallback)
        throws RemoteException
    {
        YLoginService.access$2400(YLoginService.this).register(iyloginservicecallback);
    }

    public void requestCredential()
        throws RemoteException
    {
        if (YLoginService.access$2000(YLoginService.this))
        {
            return;
        } else
        {
            YLoginService.access$2100(YLoginService.this, YLoginService.access$600(YLoginService.this));
            return;
        }
    }

    public boolean requestingCredential()
        throws RemoteException
    {
        return YLoginService.access$2000(YLoginService.this);
    }

    public void setAppid(String s)
        throws RemoteException
    {
        YLoginService.access$2502(YLoginService.this, s);
    }

    public void setAutoLogin(boolean flag)
        throws RemoteException
    {
        if (flag)
        {
            YLoginService.access$900(YLoginService.this, YLoginService.access$700(YLoginService.this), true);
            YLoginService.access$1000(YLoginService.this, YLoginService.access$000(YLoginService.this), YLoginService.access$200(YLoginService.this));
            YLoginService.access$1000(YLoginService.this, YLoginService.access$1100(YLoginService.this), YLoginService.access$300(YLoginService.this));
            YLoginService.access$1300(YLoginService.this, YLoginService.access$1200(YLoginService.this), YLoginService.access$600(YLoginService.this));
            return;
        } else
        {
            YLoginService.access$900(YLoginService.this, YLoginService.access$700(YLoginService.this), false);
            YLoginService.access$1300(YLoginService.this, YLoginService.access$000(YLoginService.this), "");
            YLoginService.access$1300(YLoginService.this, YLoginService.access$1200(YLoginService.this), "");
            YLoginService.access$1300(YLoginService.this, YLoginService.access$1100(YLoginService.this), "");
            return;
        }
    }

    public void unregisterCallback(IYLoginServiceCallback iyloginservicecallback)
        throws RemoteException
    {
        YLoginService.access$2400(YLoginService.this).unregister(iyloginservicecallback);
    }

    llback()
    {
        this$0 = YLoginService.this;
        super();
    }
}
