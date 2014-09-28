// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Point;

// Referenced classes of package jp.co.yahoo.android.maps:
//            GeoPoint

public interface Projection
{

    public abstract GeoPoint fromPixels(int i, int j);

    public abstract int getLeftWorldPixel();

    public abstract int[] getTileIdPos(int i, int j);

    public abstract Point getTopLeftWorldPixels();

    public abstract int getTopWorldPixel();

    public abstract float metersToEquatorPixels(float f);

    public abstract int toPixelXFromLL(double d, double d1);

    public abstract int toPixelXFromWP(int i, int j);

    public abstract int toPixelYFromLL(double d, double d1);

    public abstract int toPixelYFromWP(int i, int j);

    public abstract Point toPixels(Point point, Point point1);

    public abstract Point toPixels(GeoPoint geopoint, Point point);

    public abstract int toWorldPixelX(double d, double d1);

    public abstract int toWorldPixelY(double d, double d1);

    public abstract Point toWorldPixels(GeoPoint geopoint, Point point);
}
