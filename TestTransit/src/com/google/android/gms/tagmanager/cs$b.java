// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;


// Referenced classes of package com.google.android.gms.tagmanager:
//            by, cs

private static class agY
{

    private com.google.android.gms.internal.b.agY agY;
    private by ahD;

    public int getSize()
    {
        int i = ((com.google.android.gms.internal.b)ahD.getObject()).Z();
        int j;
        if (agY == null)
        {
            j = 0;
        } else
        {
            j = agY.Z();
        }
        return j + i;
    }

    public by mJ()
    {
        return ahD;
    }

    public com.google.android.gms.internal.b mp()
    {
        return agY;
    }

    public (by by1, com.google.android.gms.internal.b b1)
    {
        ahD = by1;
        agY = b1;
    }
}
