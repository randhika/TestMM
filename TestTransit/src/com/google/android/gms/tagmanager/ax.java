// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, dh, ay

class ax extends aj
{

    private static final String ID;
    private static final String aek;
    private final Context kO;

    public ax(Context context)
    {
        super(ID, new String[0]);
        kO = context;
    }

    public boolean lh()
    {
        return true;
    }

    public com.google.android.gms.internal.d.a w(Map map)
    {
        String s;
        String s1;
        if ((com.google.android.gms.internal.d.a)map.get(aek) != null)
        {
            s = dh.j((com.google.android.gms.internal.d.a)map.get(aek));
        } else
        {
            s = null;
        }
        s1 = ay.e(kO, s);
        if (s1 != null)
        {
            return dh.r(s1);
        } else
        {
            return dh.nd();
        }
    }

    static 
    {
        ID = a.ab.toString();
        aek = b.bH.toString();
    }
}
