// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.a;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, dh

class aa extends aj
{

    private static final String ID;

    public aa()
    {
        super(ID, new String[0]);
    }

    public boolean lh()
    {
        return true;
    }

    public com.google.android.gms.internal.d.a w(Map map)
    {
        String s = Build.MANUFACTURER;
        String s1 = Build.MODEL;
        if (!s1.startsWith(s) && !s.equals("unknown"))
        {
            s1 = (new StringBuilder()).append(s).append(" ").append(s1).toString();
        }
        return dh.r(s1);
    }

    static 
    {
        ID = a.F.toString();
    }
}
