// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.weather.data;

import java.util.Vector;

// Referenced classes of package jp.co.yahoo.android.maps.weather.data:
//            TileManager, Tile

public static interface 
{

    public abstract void removeTile(Tile tile);

    public abstract void requestNewTiles(Vector vector);
}