// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizer

class this._cls0
    implements Runnable
{

    final YJVORecognizer this$0;

    public void run()
    {
        recognizeCancelInternal();
    }

    ()
    {
        this$0 = YJVORecognizer.this;
        super();
    }
}
