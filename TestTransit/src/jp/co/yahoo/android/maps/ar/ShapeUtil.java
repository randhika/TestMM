// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;


public class ShapeUtil
{

    public ShapeUtil()
    {
    }

    public static final double calcAngle(double d, double d1, double d2, double d3)
    {
        return calcAngle(d, d1, d2, d3, calcDistance(d, d1, d2, d3));
    }

    public static final double calcAngle(double d, double d1, double d2, double d3, 
            double d4)
    {
        if (d4 == 0.0D)
        {
            return 0.0D;
        }
        double d5 = (3.1415926535897931D * d) / 180D;
        double d6 = (3.1415926535897931D * d1) / 180D;
        double d7 = (3.1415926535897931D * d2) / 180D;
        double d8 = (Math.sin((3.1415926535897931D * d3) / 180D - d6) * Math.sin(1.5707963267948966D - d7)) / Math.sin(d4 / 6370300D);
        if (d8 < -1D)
        {
            d8 = -1D;
        }
        if (d8 > 1.0D)
        {
            d8 = 1.0D;
        }
        double d9 = Math.asin(d8);
        if (d7 < d5)
        {
            d9 = 3.1415926535897931D - d9;
        }
        if (d9 < 0.0D)
        {
            d9 += 6.2831853071795862D;
        }
        return (180D * d9) / 3.1415926535897931D;
    }

    public static final double calcDistance(double d, double d1, double d2, double d3)
    {
        double d4 = Math.cos((3.1415926535897931D * d3) / 180D) * Math.cos((3.1415926535897931D * d2) / 180D);
        double d5 = Math.sin((3.1415926535897931D * d3) / 180D) * Math.cos((3.1415926535897931D * d2) / 180D);
        double d6 = Math.sin((3.1415926535897931D * d2) / 180D);
        double d7 = Math.cos((3.1415926535897931D * d1) / 180D) * Math.cos((3.1415926535897931D * d) / 180D);
        double d8 = Math.sin((3.1415926535897931D * d1) / 180D) * Math.cos((3.1415926535897931D * d) / 180D);
        double d9 = Math.sin((3.1415926535897931D * d) / 180D);
        double d10 = d4 * d7 + d5 * d8 + d6 * d9;
        if (d10 < -1D)
        {
            d10 = -1D;
        }
        if (d10 > 1.0D)
        {
            d10 = 1.0D;
        }
        return 6370300D * Math.acos(d10);
    }
}
