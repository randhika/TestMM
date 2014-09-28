// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;


public class ScaleUtils
{

    public ScaleUtils()
    {
    }

    public static int getIndoormapMaximumScaleZoomLevel()
    {
        return -3;
    }

    public static int getIndoormapMinimumScaleZoomLevel()
    {
        return 1;
    }

    public static boolean isExistIndoormapZoomLevel(int i)
    {
        return -3 <= i && i <= 1;
    }

    public static boolean isOnlyIndoormapZoomlevel(int i)
    {
        return i <= -2;
    }

    public static int scidturn(int i)
    {
        return 21 - i;
    }
}
