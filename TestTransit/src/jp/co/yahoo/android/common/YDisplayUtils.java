// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLog

public class YDisplayUtils
{

    public static final int HDPI = 2;
    private static YDisplayUtils INSTANCE = null;
    public static final int MDPI = 1;
    private static final String TAG = jp/co/yahoo/android/common/YDisplayUtils.getSimpleName();
    public static final int XHDPI = 3;
    private float DISPLAY_DENSITY;
    private float DISPLAY_FONTSCALE;
    private int DPI_TYPE;
    private int VIEW_SCALED_TOUCH_SLOP;
    private int VIEW_STATUSBAR_HEIGHT_LANDSCAPE;
    private int VIEW_STATUSBAR_HEIGHT_PORTRAIT;
    private int WINDOW_HEIGHT;
    private int WINDOW_WIDTH;
    private boolean _initPorrait;

    private YDisplayUtils(Activity activity)
    {
        WINDOW_WIDTH = -1;
        WINDOW_HEIGHT = -1;
        DISPLAY_DENSITY = 0.0F;
        DISPLAY_FONTSCALE = 0.0F;
        DPI_TYPE = -1;
        VIEW_SCALED_TOUCH_SLOP = 0;
        VIEW_STATUSBAR_HEIGHT_PORTRAIT = -1;
        VIEW_STATUSBAR_HEIGHT_LANDSCAPE = -1;
        init(activity);
    }

    private void calcViewStatusbarHeight()
    {
        VIEW_STATUSBAR_HEIGHT_PORTRAIT = 38;
        VIEW_STATUSBAR_HEIGHT_LANDSCAPE = 38;
        if (DPI_TYPE == 3)
        {
            VIEW_STATUSBAR_HEIGHT_PORTRAIT = 50;
            VIEW_STATUSBAR_HEIGHT_LANDSCAPE = 50;
        } else
        if (Build.MODEL.equals("SO-01B") && android.os.Build.VERSION.SDK_INT == 4)
        {
            VIEW_STATUSBAR_HEIGHT_PORTRAIT = 29;
            VIEW_STATUSBAR_HEIGHT_LANDSCAPE = 29;
            return;
        }
    }

    public static YDisplayUtils getInstance(Activity activity)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new YDisplayUtils(activity);
        }
        INSTANCE.init(activity);
        return INSTANCE;
    }

    private void init(Activity activity)
    {
        DisplayMetrics displaymetrics;
        if (_initPorrait != isPortrait(activity))
        {
            DISPLAY_DENSITY = 0.0F;
        }
        if (DISPLAY_DENSITY != 0.0F)
        {
            return;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        WINDOW_WIDTH = display.getWidth();
        WINDOW_HEIGHT = display.getHeight();
        displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        DISPLAY_DENSITY = displaymetrics.density;
        DISPLAY_FONTSCALE = displaymetrics.scaledDensity;
        displaymetrics.densityDpi;
        JVM INSTR lookupswitch 3: default 120
    //                   160: 345
    //                   240: 353
    //                   320: 361;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_361;
_L5:
        VIEW_SCALED_TOUCH_SLOP = ViewConfiguration.get(activity).getScaledTouchSlop();
        calcViewStatusbarHeight();
        _initPorrait = isPortrait(activity);
        YLog.d(TAG, "==== YDisplayUtils ====");
        String s = TAG;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(WINDOW_WIDTH);
        YLog.d(s, "WINDOW_WIDTH: %d", aobj);
        String s1 = TAG;
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(WINDOW_HEIGHT);
        YLog.d(s1, "WINDOW_HEIGHT: %d", aobj1);
        String s2 = TAG;
        Object aobj2[] = new Object[1];
        aobj2[0] = Float.valueOf(DISPLAY_DENSITY);
        YLog.d(s2, "DISPLAY_DENSITY: %.2f", aobj2);
        String s3 = TAG;
        Object aobj3[] = new Object[1];
        aobj3[0] = Float.valueOf(DISPLAY_FONTSCALE);
        YLog.d(s3, "DISPLAY_FONTSCALE: %.2f", aobj3);
        String s4 = TAG;
        Object aobj4[] = new Object[1];
        String s5;
        String s6;
        Object aobj5[];
        if (DPI_TYPE == 2)
        {
            s5 = "hdpi";
        } else
        {
            s5 = "mdpi";
        }
        aobj4[0] = s5;
        YLog.d(s4, "DPI_TYPE: %s", aobj4);
        s6 = TAG;
        aobj5 = new Object[1];
        aobj5[0] = Integer.valueOf(VIEW_SCALED_TOUCH_SLOP);
        YLog.d(s6, "VIEW_SCALED_TOUCH_SLOP: %d", aobj5);
        return;
_L2:
        DPI_TYPE = 1;
          goto _L5
_L3:
        DPI_TYPE = 2;
          goto _L5
        DPI_TYPE = 3;
          goto _L5
    }

    public static boolean isPortrait(Context context)
    {
        for (Resources resources = context.getResources(); resources == null || resources.getConfiguration().orientation == 1;)
        {
            return true;
        }

        return false;
    }

    public float dp2px(float f)
    {
        return f * DISPLAY_DENSITY;
    }

    public int getDpiType()
    {
        return DPI_TYPE;
    }

    public int getViewScaledTouchSlop()
    {
        return VIEW_SCALED_TOUCH_SLOP;
    }

    public int getViewStatusbarHeight(boolean flag)
    {
        if (flag)
        {
            return VIEW_STATUSBAR_HEIGHT_PORTRAIT;
        } else
        {
            return VIEW_STATUSBAR_HEIGHT_LANDSCAPE;
        }
    }

    public int getWindowHeight()
    {
        return WINDOW_HEIGHT;
    }

    public int getWindowWidth()
    {
        return WINDOW_WIDTH;
    }

    public float px2sp(float f)
    {
        return f / DISPLAY_FONTSCALE;
    }

    public float sp2px(float f)
    {
        return f * DISPLAY_FONTSCALE;
    }

}
