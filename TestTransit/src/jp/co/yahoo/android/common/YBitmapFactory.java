// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import java.io.InputStream;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YBitmapFactory
{

    private static final String TAG = jp/co/yahoo/android/common/YBitmapFactory.getSimpleName();

    public YBitmapFactory()
    {
    }

    public static Bitmap copy(Bitmap bitmap, android.graphics.Bitmap.Config config, boolean flag)
    {
        Bitmap bitmap1;
        try
        {
            bitmap1 = bitmap.copy(config, flag);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap createBitmap(int i, int j, android.graphics.Bitmap.Config config)
    {
        Bitmap bitmap;
        try
        {
            bitmap = Bitmap.createBitmap(i, j, config);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap;
    }

    public static Bitmap createBitmap(Bitmap bitmap, int i, int j, int k, int l, Matrix matrix, boolean flag)
    {
        Bitmap bitmap1;
        try
        {
            bitmap1 = Bitmap.createBitmap(bitmap, i, j, k, l, matrix, flag);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int j, boolean flag)
    {
        Bitmap bitmap1;
        try
        {
            bitmap1 = Bitmap.createScaledBitmap(bitmap, i, j, flag);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap decodeFile(String s)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeFile(s);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap;
    }

    public static Bitmap decodeFile(String s, android.graphics.BitmapFactory.Options options)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeFile(s, options);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap;
    }

    public static Bitmap decodeResource(Resources resources, int i)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeResource(resources, i);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap;
    }

    public static Bitmap decodeStream(InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeStream(inputstream, rect, options);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLog.e(TAG, outofmemoryerror.getMessage());
            return null;
        }
        return bitmap;
    }

}
