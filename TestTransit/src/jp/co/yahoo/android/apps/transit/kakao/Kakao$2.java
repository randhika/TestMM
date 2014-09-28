// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.util.Log;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

class this._cls0 extends Thread
{

    final Kakao this$0;

    public void run()
    {
        try
        {
            Kakao.access$100(Kakao.this);
            return;
        }
        catch (Exception exception)
        {
            if (Kakao.isLogging)
            {
                Log.w("kakao-android-sdk", exception);
            }
            Kakao.access$200(Kakao.this).onResult(new atus(false, exception.toString(), exception), null);
            return;
        }
    }

    kaoListener()
    {
        this$0 = Kakao.this;
        super();
    }
}
