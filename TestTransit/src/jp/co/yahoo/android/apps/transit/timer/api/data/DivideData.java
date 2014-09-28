// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;

public class DivideData
    implements Serializable
{

    private static final long serialVersionUID = 0xbcde6c61d5e983cL;
    private boolean bReverse;
    private int nDivide;
    private int nDivideHour;
    private int nDivideMin;

    public DivideData()
    {
        nDivide = 1400;
        nDivideHour = 14;
        nDivideMin = 0;
        bReverse = false;
    }

    public int getDivide()
    {
        return nDivide;
    }

    public int getDivideHour()
    {
        return nDivideHour;
    }

    public int getDivideMin()
    {
        return nDivideMin;
    }

    public boolean isReverse()
    {
        return bReverse;
    }

    public void setDivideHour(int i)
    {
        nDivideHour = i;
        nDivide = i * 100 + nDivideMin;
    }

    public void setDivideMin(int i)
    {
        nDivideMin = i;
        nDivide = i + 100 * nDivideHour;
    }

    public void setReverse(boolean flag)
    {
        bReverse = flag;
    }
}
