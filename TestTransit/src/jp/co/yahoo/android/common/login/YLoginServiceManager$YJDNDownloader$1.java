// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.Context;
import java.util.Map;
import jp.co.yahoo.android.common.YHttpRequest;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginServiceManager

class this._cls1 extends YHttpRequest
{

    final clearResponse this$1;

    public boolean onCompleteInThread()
    {
label0:
        {
            synchronized (cess._mth1000(this._cls1.this))
            {
                if (cess._mth1100(this._cls1.this))
                {
                    break label0;
                }
            }
            return false;
        }
        String s;
        Object obj1;
        boolean flag;
        cess._mth1102(this._cls1.this, false);
        s = cess._mth1200(this._cls1.this).l();
        obj1 = cess._mth1200(this._cls1.this).tionalObj();
        flag = cess._mth1200(this._cls1.this).eErrorCheck();
        obj;
        JVM INSTR monitorexit ;
        YLoginServiceManager.access$1300(_fld0, getStatusCode(), getResponseByteArray(), getAllHeader(), s, obj1, flag);
        clearResponse();
        return false;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onTimeoutInThread()
    {
label0:
        {
            synchronized (cess._mth1000(this._cls1.this))
            {
                if (cess._mth1100(this._cls1.this))
                {
                    break label0;
                }
            }
            return;
        }
        String s;
        Object obj1;
        boolean flag;
        cess._mth1102(this._cls1.this, false);
        s = cess._mth1200(this._cls1.this).l();
        obj1 = cess._mth1200(this._cls1.this).tionalObj();
        flag = cess._mth1200(this._cls1.this).eErrorCheck();
        obj;
        JVM INSTR monitorexit ;
        YLoginServiceManager.access$1400(_fld0, getStatusCode(), getResponseByteArray(), getAllHeader(), s, obj1, flag);
        clearResponse();
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    (Context context, String s, Map map)
    {
        this$1 = this._cls1.this;
        super(context, s, map);
    }
}
