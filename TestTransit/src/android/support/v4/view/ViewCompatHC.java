// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

class ViewCompatHC
{

    ViewCompatHC()
    {
    }

    public static float getAlpha(View view)
    {
        return view.getAlpha();
    }

    static long getFrameTime()
    {
        return ValueAnimator.getFrameDelay();
    }

    public static int getLayerType(View view)
    {
        return view.getLayerType();
    }

    public static int getMeasuredHeightAndState(View view)
    {
        return view.getMeasuredHeightAndState();
    }

    public static int getMeasuredState(View view)
    {
        return view.getMeasuredState();
    }

    public static int getMeasuredWidthAndState(View view)
    {
        return view.getMeasuredWidthAndState();
    }

    public static float getPivotX(View view)
    {
        return view.getPivotX();
    }

    public static float getPivotY(View view)
    {
        return view.getPivotY();
    }

    public static float getRotation(View view)
    {
        return view.getRotation();
    }

    public static float getRotationX(View view)
    {
        return view.getRotationX();
    }

    public static float getRotationY(View view)
    {
        return view.getRotationY();
    }

    public static float getScaleX(View view)
    {
        return view.getScaleX();
    }

    public static float getScaleY(View view)
    {
        return view.getScaleY();
    }

    public static float getTranslationX(View view)
    {
        return view.getTranslationX();
    }

    public static float getTranslationY(View view)
    {
        return view.getTranslationY();
    }

    public static float getX(View view)
    {
        return view.getX();
    }

    public static float getY(View view)
    {
        return view.getY();
    }

    public static int resolveSizeAndState(int i, int j, int k)
    {
        return View.resolveSizeAndState(i, j, k);
    }

    public static void setAlpha(View view, float f)
    {
        view.setAlpha(f);
    }

    public static void setLayerType(View view, int i, Paint paint)
    {
        view.setLayerType(i, paint);
    }

    public static void setPivotX(View view, float f)
    {
        view.setPivotX(f);
    }

    public static void setPivotY(View view, float f)
    {
        view.setPivotY(f);
    }

    public static void setRotation(View view, float f)
    {
        view.setRotation(f);
    }

    public static void setRotationX(View view, float f)
    {
        view.setRotationX(f);
    }

    public static void setRotationY(View view, float f)
    {
        view.setRotationY(f);
    }

    public static void setScaleX(View view, float f)
    {
        view.setScaleX(f);
    }

    public static void setScaleY(View view, float f)
    {
        view.setScaleY(f);
    }

    public static void setTranslationX(View view, float f)
    {
        view.setTranslationX(f);
    }

    public static void setTranslationY(View view, float f)
    {
        view.setTranslationY(f);
    }

    public static void setX(View view, float f)
    {
        view.setX(f);
    }

    public static void setY(View view, float f)
    {
        view.setY(f);
    }
}
