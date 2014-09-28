// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


public class DoublePoint
{

    public double x;
    public double y;

    public DoublePoint()
    {
    }

    public DoublePoint(double d, double d1)
    {
        x = d;
        y = d1;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void translate(double d, double d1)
    {
        x = d + x;
        y = d1 + y;
    }
}
