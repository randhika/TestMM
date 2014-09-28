// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.util.Log;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            YJVOVRecognizer, RecognizerScreen

class this._cls0
    implements Runnable
{

    final YJVOVRecognizer this$0;

    public void run()
    {
        int i = mScreenID;
        try
        {
            Thread.sleep(2000L);
        }
        catch (InterruptedException interruptedexception)
        {
            Log.e("YJVOICESCREEN", interruptedexception.toString());
        }
        if (i == mErrScreenID && i == mScreenID)
        {
            mScreen.changeScreen(TATE.HIDE);
        }
    }

    TATE()
    {
        this$0 = YJVOVRecognizer.this;
        super();
    }
}
