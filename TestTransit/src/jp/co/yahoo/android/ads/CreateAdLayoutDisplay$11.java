// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, a, h

class a
    implements android.view.Display._cls11
{

    final CreateAdLayoutDisplay a;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        this;
        JVM INSTR monitorenter ;
        int i = motionevent.getAction();
        i;
        JVM INSTR tableswitch 0 0: default 28
    //                   0 32;
           goto _L1 _L2
_L1:
        this;
        JVM INSTR monitorexit ;
        return true;
_L2:
        if (h.d(CreateAdLayoutDisplay.b(a).g()) && h.d(CreateAdLayoutDisplay.b(a).p())) goto _L4; else goto _L3
_L3:
        CreateAdLayoutDisplay.a(a, view);
        CreateAdLayoutDisplay.b(a, view);
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L4:
        (new Thread(new init>(a, CreateAdLayoutDisplay.b(a).h()[0]))).start();
          goto _L1
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
