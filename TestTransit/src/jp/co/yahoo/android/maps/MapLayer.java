// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;


public interface MapLayer
{

    public abstract boolean exists(int i, int j);

    public abstract int getMaxZoomLevel(int i);

    public abstract int getMinZoomLevel(int i);
}
