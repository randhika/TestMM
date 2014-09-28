// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;


// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            FixedHeaderScrollView

class this._cls0
    implements Runnable
{

    final this._cls0 this$0;

    public void run()
    {
        int i = tScrollY();
        if (cess._mth200(this._cls0.this) - i == 0)
        {
            cess._mth300(this._cls0.this, i);
            return;
        } else
        {
            cess._mth400(this._cls0.this);
            return;
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
