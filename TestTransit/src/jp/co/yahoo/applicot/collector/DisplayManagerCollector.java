// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.log.ApplicotLog;

// Referenced classes of package jp.co.yahoo.applicot.collector:
//            Compatibility

final class DisplayManagerCollector
{

    static final SparseArray mDensities = new SparseArray();
    static final SparseArray mFlagsNames = new SparseArray();

    DisplayManagerCollector()
    {
    }

    private static String activeFlags(SparseArray sparsearray, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (int j = 0; j < sparsearray.size(); j++)
        {
            int k = i & sparsearray.keyAt(j);
            if (k <= 0)
            {
                continue;
            }
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append('+');
            }
            stringbuilder.append((String)sparsearray.get(k));
        }

        return stringbuilder.toString();
    }

    private static String collectCurrentSizeRange(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            Method method = display.getClass().getMethod("getCurrentSizeRange", new Class[] {
                android/graphics/Point, android/graphics/Point
            });
            Point point = new Point();
            Point point1 = new Point();
            method.invoke(display, new Object[] {
                point, point1
            });
            stringbuilder.append(display.getDisplayId()).append(".currentSizeRange.smallest=[").append(point.x).append(',').append(point.y).append(']').append('\n');
            stringbuilder.append(display.getDisplayId()).append(".currentSizeRange.largest=[").append(point1.x).append(',').append(point1.y).append(']').append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
    }

    private static Object collectDisplayData(Display display)
    {
        display.getMetrics(new DisplayMetrics());
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(collectCurrentSizeRange(display));
        stringbuilder.append(collectFlags(display));
        stringbuilder.append(display.getDisplayId()).append(".height=").append(display.getHeight()).append('\n');
        stringbuilder.append(collectMetrics(display, "getMetrics"));
        stringbuilder.append(collectName(display));
        stringbuilder.append(display.getDisplayId()).append(".orientation=").append(display.getOrientation()).append('\n');
        stringbuilder.append(display.getDisplayId()).append(".pixelFormat=").append(display.getPixelFormat()).append('\n');
        stringbuilder.append(collectMetrics(display, "getRealMetrics"));
        stringbuilder.append(collectSize(display, "getRealSize"));
        stringbuilder.append(collectRectSize(display));
        stringbuilder.append(display.getDisplayId()).append(".refreshRate=").append(display.getRefreshRate()).append('\n');
        stringbuilder.append(collectRotation(display));
        stringbuilder.append(collectSize(display, "getSize"));
        stringbuilder.append(display.getDisplayId()).append(".width=").append(display.getWidth()).append('\n');
        stringbuilder.append(collectIsValid(display));
        return stringbuilder.toString();
    }

    public static String collectDisplays(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Display adisplay[];
        Display adisplay1[];
        int i;
        if (Compatibility.getAPILevel() < 17)
        {
            WindowManager windowmanager = (WindowManager)context.getSystemService("window");
            adisplay = new Display[1];
            adisplay[0] = windowmanager.getDefaultDisplay();
        } else
        {
            try
            {
                Object obj = context.getSystemService((String)(String)context.getClass().getField("DISPLAY_SERVICE").get(null));
                adisplay = (Display[])(Display[])obj.getClass().getMethod("getDisplays", new Class[0]).invoke(obj, new Object[0]);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", illegalargumentexception);
                adisplay = null;
            }
            catch (SecurityException securityexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", securityexception);
                adisplay = null;
            }
            catch (IllegalAccessException illegalaccessexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", illegalaccessexception);
                adisplay = null;
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", nosuchfieldexception);
                adisplay = null;
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", nosuchmethodexception);
                adisplay = null;
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                Applicot.log.w(Applicot.LOG_TAG, "Error while collecting DisplayManager data: ", invocationtargetexception);
                adisplay = null;
            }
        }
        adisplay1 = adisplay;
        i = adisplay1.length;
        for (int j = 0; j < i; j++)
        {
            stringbuilder.append(collectDisplayData(adisplay1[j]));
        }

        return stringbuilder.toString();
    }

    private static String collectFlags(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i;
        Field afield[];
        int j;
        i = ((Integer)display.getClass().getMethod("getFlags", new Class[0]).invoke(display, new Object[0])).intValue();
        afield = display.getClass().getFields();
        j = afield.length;
        int k = 0;
_L2:
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        Field field = afield[k];
        if (field.getName().startsWith("FLAG_"))
        {
            mFlagsNames.put(field.getInt(null), field.getName());
        }
        break MISSING_BLOCK_LABEL_160;
        try
        {
            stringbuilder.append(display.getDisplayId()).append(".flags=").append(activeFlags(mFlagsNames, i)).append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
        k++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Object collectIsValid(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            Boolean boolean1 = (Boolean)display.getClass().getMethod("isValid", new Class[0]).invoke(display, new Object[0]);
            stringbuilder.append(display.getDisplayId()).append(".isValid=").append(boolean1).append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
    }

    private static Object collectMetrics(Display display, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        DisplayMetrics displaymetrics;
        Field afield[];
        int i;
        displaymetrics = (DisplayMetrics)display.getClass().getMethod(s, new Class[0]).invoke(display, new Object[0]);
        afield = android/util/DisplayMetrics.getFields();
        i = afield.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        Field field = afield[j];
        if (field.getType().equals(java/lang/Integer) && field.getName().startsWith("DENSITY_") && !field.getName().equals("DENSITY_DEFAULT"))
        {
            mDensities.put(field.getInt(null), field.getName());
        }
        break MISSING_BLOCK_LABEL_422;
        try
        {
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".density=").append(displaymetrics.density).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".densityDpi=").append(displaymetrics.getClass().getField("densityDpi")).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append("scaledDensity=x").append(displaymetrics.scaledDensity).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".widthPixels=").append(displaymetrics.widthPixels).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".heightPixels=").append(displaymetrics.heightPixels).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".xdpi=").append(displaymetrics.xdpi).append('\n');
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append(".ydpi=").append(displaymetrics.ydpi).append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        catch (NoSuchFieldException nosuchfieldexception) { }
        return stringbuilder.toString();
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static String collectName(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            String s = (String)display.getClass().getMethod("getName", new Class[0]).invoke(display, new Object[0]);
            stringbuilder.append(display.getDisplayId()).append(".name=").append(s).append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
    }

    private static Object collectRectSize(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            Method method = display.getClass().getMethod("getRectSize", new Class[] {
                android/graphics/Rect
            });
            Rect rect = new Rect();
            method.invoke(display, new Object[] {
                rect
            });
            stringbuilder.append(display.getDisplayId()).append(".rectSize=[").append(rect.top).append(',').append(rect.left).append(',').append(rect.width()).append(',').append(rect.height()).append(']').append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
    }

    private static Object collectRotation(Display display)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i;
        i = ((Integer)display.getClass().getMethod("getRotation", new Class[0]).invoke(display, new Object[0])).intValue();
        stringbuilder.append(display.getDisplayId()).append(".rotation=");
        i;
        JVM INSTR tableswitch 0 3: default 84
    //                   0 103
    //                   1 114
    //                   2 125
    //                   3 136;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        stringbuilder.append(i);
_L6:
        stringbuilder.append('\n');
_L7:
        return stringbuilder.toString();
_L2:
        stringbuilder.append("ROTATION_0");
          goto _L6
_L3:
        stringbuilder.append("ROTATION_90");
          goto _L6
_L4:
        stringbuilder.append("ROTATION_180");
          goto _L6
_L5:
        stringbuilder.append("ROTATION_270");
          goto _L6
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L7
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
          goto _L7
        SecurityException securityexception;
        securityexception;
          goto _L7
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
          goto _L7
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L7
    }

    private static Object collectSize(Display display, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            Method method = display.getClass().getMethod(s, new Class[] {
                android/graphics/Point
            });
            Point point = new Point();
            method.invoke(display, new Object[] {
                point
            });
            stringbuilder.append(display.getDisplayId()).append('.').append(s).append("=[").append(point.x).append(',').append(point.y).append(']').append('\n');
        }
        catch (SecurityException securityexception) { }
        catch (NoSuchMethodException nosuchmethodexception) { }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
        return stringbuilder.toString();
    }

}
