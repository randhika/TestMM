// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class YAnimationHelper
{

    static final int DEFAULT_DURATION = 300;
    static final LinearInterpolator DEFAULT_INTERPOLATOR = new LinearInterpolator();

    public YAnimationHelper()
    {
    }

    public static Animation fadeInAnimation()
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(300L);
        alphaanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        alphaanimation.setFillAfter(true);
        return alphaanimation;
    }

    public static AnimationSet fadeInBottomAnimation(long l)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(l);
        animationset.addAnimation(alphaanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1F, 1, 0.0F);
        translateanimation.setDuration(l);
        animationset.addAnimation(translateanimation);
        return animationset;
    }

    public static LayoutAnimationController fadeInBottomLayoutAnimation(long l, float f)
    {
        return new LayoutAnimationController(fadeInBottomAnimation(l), 0.5F);
    }

    public static GridLayoutAnimationController fadeInGridAnimation(long l)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(l);
        animationset.addAnimation(alphaanimation);
        GridLayoutAnimationController gridlayoutanimationcontroller = new GridLayoutAnimationController(alphaanimation, 0.5F, 0.5F);
        gridlayoutanimationcontroller.setDirectionPriority(2);
        return gridlayoutanimationcontroller;
    }

    public static Animation fadeOutAnimation()
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
        alphaanimation.setDuration(300L);
        alphaanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        alphaanimation.setFillAfter(true);
        return alphaanimation;
    }

    public static Animation inFromBottomAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation inFromLeftAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, -1F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation inFromRightAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation inFromTopAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation leftRotateAnimation()
    {
        RotateAnimation rotateanimation = new RotateAnimation(360F, 0.0F, 1, 0.5F, 1, 0.5F);
        rotateanimation.setDuration(300L);
        rotateanimation.setFillAfter(false);
        rotateanimation.setFillBefore(true);
        rotateanimation.setStartOffset(0L);
        rotateanimation.setRepeatCount(-1);
        rotateanimation.setRepeatMode(1);
        rotateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return rotateanimation;
    }

    public static Animation outToBottomAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation outToLeftAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, -1F, 2, 0.0F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation outToRightAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation outToTopAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1F);
        translateanimation.setDuration(300L);
        translateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return translateanimation;
    }

    public static Animation rightRotateAnimation()
    {
        RotateAnimation rotateanimation = new RotateAnimation(0.0F, 360F, 1, 0.5F, 1, 0.5F);
        rotateanimation.setDuration(300L);
        rotateanimation.setFillAfter(false);
        rotateanimation.setFillBefore(true);
        rotateanimation.setStartOffset(0L);
        rotateanimation.setRepeatCount(-1);
        rotateanimation.setRepeatMode(1);
        rotateanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        return rotateanimation;
    }

    public static Animation scaleAnimation(float f)
    {
        ScaleAnimation scaleanimation = new ScaleAnimation(1.0F, f, 1.0F, f, 1, 0.5F, 1, 0.5F);
        scaleanimation.setDuration(300L);
        scaleanimation.setInterpolator(DEFAULT_INTERPOLATOR);
        scaleanimation.setFillAfter(true);
        return scaleanimation;
    }

}
