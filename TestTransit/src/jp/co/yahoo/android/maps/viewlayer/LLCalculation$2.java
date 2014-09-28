// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.Hashtable;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer:
//            LLCalculation

class put extends Hashtable
{

    ()
    {
        put("a", new Double(6377397.1550000003D));
        put("b", new Double(6356078.96325D));
        put("e2", new Double((6377397.1550000003D * 6377397.1550000003D - 6356078.96325D * 6356078.96325D) / (6377397.1550000003D * 6377397.1550000003D)));
    }
}
