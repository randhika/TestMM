// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;

// Referenced classes of package com.google.android.gms.internal:
//            hj

public final class hn extends g
{

    private static final hn GL = new hn();

    private hn()
    {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View b(Context context, int i, int j)
        throws com.google.android.gms.dynamic.g.a
    {
        return GL.c(context, i, j);
    }

    private View c(Context context, int i, int j)
        throws com.google.android.gms.dynamic.g.a
    {
        View view;
        try
        {
            com.google.android.gms.dynamic.d d1 = e.h(context);
            view = (View)e.e(((hj)G(context)).a(d1, i, j));
        }
        catch (Exception exception)
        {
            throw new com.google.android.gms.dynamic.g.a((new StringBuilder()).append("Could not get button with size ").append(i).append(" and color ").append(j).toString(), exception);
        }
        return view;
    }

    public hj N(IBinder ibinder)
    {
        return hj.a.M(ibinder);
    }

    public Object d(IBinder ibinder)
    {
        return N(ibinder);
    }

}
