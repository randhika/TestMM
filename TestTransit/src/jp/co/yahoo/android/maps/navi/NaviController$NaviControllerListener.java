// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.navi;


// Referenced classes of package jp.co.yahoo.android.maps.navi:
//            NaviController

public static interface I
{

    public abstract boolean onGoal(NaviController navicontroller);

    public abstract boolean onLocationAccuracyBad(NaviController navicontroller);

    public abstract boolean onLocationChanged(NaviController navicontroller);

    public abstract boolean onLocationTimeOver(NaviController navicontroller);

    public abstract boolean onRouteOut(NaviController navicontroller);
}
