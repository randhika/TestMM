// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import java.util.Vector;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileHttpLoader, TileRequest

public static interface 
{

    public abstract boolean endAllTileHttpLoader(TileHttpLoader tilehttploader, Vector vector);

    public abstract boolean endTileHttpLoader(byte abyte0[], TileRequest tilerequest);

    public abstract boolean errorTileHttpLoader(TileHttpLoader tilehttploader, Vector vector);
}
