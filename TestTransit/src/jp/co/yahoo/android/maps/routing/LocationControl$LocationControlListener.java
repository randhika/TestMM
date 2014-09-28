// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.routing;

import jp.co.yahoo.android.maps.asyncgetindoorlocation.IndoorLocation;

// Referenced classes of package jp.co.yahoo.android.maps.routing:
//            LocationControl

public static interface 
{

    public abstract void onYIndoorLocationChanged(LocationControl locationcontrol, IndoorLocation indoorlocation);

    public abstract void onYLocationChanged(LocationControl locationcontrol);

    public abstract void onYLocationError(LocationControl locationcontrol);
}
