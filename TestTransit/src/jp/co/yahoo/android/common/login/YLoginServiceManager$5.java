// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.RemoteException;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager

class Stub extends Stub
{

    final YLoginServiceManager this$0;

    public void receiveMessage(String s, String s1, String s2)
        throws RemoteException
    {
        YLoginServiceManager.access$700(YLoginServiceManager.this, s, s1, s2);
    }

    Stub()
    {
        this$0 = YLoginServiceManager.this;
        super();
    }
}
