// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            Kakao

class kaoListener
    implements Runnable
{

    final Kakao this$0;
    final String val$function;
    final kaoListener val$listener;
    final thod val$method;
    final ArrayList val$params;

    public void run()
    {
        Kakao.access$300(Kakao.this, val$method, val$function, val$params, false, val$listener);
_L1:
        return;
        Exception exception;
        exception;
        if (Kakao.isLogging)
        {
            Log.w("kakao-android-sdk", exception);
        }
        if (val$listener != null)
        {
            val$listener.onResult(new atus(false, exception.toString(), exception), null);
            return;
        }
          goto _L1
    }

    kaoListener()
    {
        this$0 = final_kakao;
        val$method = thod;
        val$function = s;
        val$params = arraylist;
        val$listener = kaoListener.this;
        super();
    }
}
