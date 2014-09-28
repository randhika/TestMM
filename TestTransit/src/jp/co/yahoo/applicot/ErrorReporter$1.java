// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.os.Looper;
import jp.co.yahoo.applicot.util.ToastSender;

// Referenced classes of package jp.co.yahoo.applicot:
//            ErrorReporter, Applicot, ApplicotConfiguration

class this._cls0 extends Thread
{

    final ErrorReporter this$0;

    public void run()
    {
        Looper.prepare();
        ToastSender.sendToast(ErrorReporter.access$000(ErrorReporter.this), Applicot.getConfig().resToastText(), 1);
        Looper.loop();
    }

    ()
    {
        this$0 = ErrorReporter.this;
        super();
    }
}
