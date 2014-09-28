// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, a, h

class a
    implements android.view.Display._cls13
{

    final CreateAdLayoutDisplay a;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 0: default 24
    //                   0 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        if (!h.d(CreateAdLayoutDisplay.b(a).g()) || !h.d(CreateAdLayoutDisplay.b(a).p()))
        {
            CreateAdLayoutDisplay.a(a, view);
            CreateAdLayoutDisplay.b(a, view);
        } else
        {
            (new Thread(new init>(a, CreateAdLayoutDisplay.b(a).h()[0]))).start();
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
