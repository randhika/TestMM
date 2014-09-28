// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;

public class PlusOneDummyView extends FrameLayout
{
    private static class a
        implements d
    {

        private Context mContext;

        public Drawable getDrawable(int i)
        {
            return mContext.getResources().getDrawable(0x1080004);
        }

        public boolean isValid()
        {
            return true;
        }

        private a(Context context)
        {
            mContext = context;
        }

    }

    private static class b
        implements d
    {

        private Context mContext;

        public Drawable getDrawable(int i)
        {
            Resources resources;
            String s;
            try
            {
                resources = mContext.createPackageContext("com.google.android.gms", 4).getResources();
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                return null;
            }
            switch (i)
            {
            case 2: // '\002'
                s = "ic_plusone_tall";
                break;

            default:
                s = "ic_plusone_standard";
                break;

            case 0: // '\0'
                s = "ic_plusone_small";
                break;

            case 1: // '\001'
                s = "ic_plusone_medium";
                break;
            }
              goto _L1
_L3:
            return resources.getDrawable(resources.getIdentifier(s, "drawable", "com.google.android.gms"));
_L1:
            if (true) goto _L3; else goto _L2
_L2:
        }

        public boolean isValid()
        {
            try
            {
                mContext.createPackageContext("com.google.android.gms", 4).getResources();
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                return false;
            }
            return true;
        }

        private b(Context context)
        {
            mContext = context;
        }

    }

    private static class c
        implements d
    {

        private Context mContext;

        public Drawable getDrawable(int i)
        {
            i;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 64
        //                       1 70
        //                       2 76;
               goto _L1 _L2 _L3 _L4
_L1:
            String s = "ic_plusone_standard_off_client";
_L6:
            int j = mContext.getResources().getIdentifier(s, "drawable", mContext.getPackageName());
            return mContext.getResources().getDrawable(j);
_L2:
            s = "ic_plusone_small_off_client";
            continue; /* Loop/switch isn't completed */
_L3:
            s = "ic_plusone_medium_off_client";
            continue; /* Loop/switch isn't completed */
_L4:
            s = "ic_plusone_tall_off_client";
            if (true) goto _L6; else goto _L5
_L5:
        }

        public boolean isValid()
        {
            int i = mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", mContext.getPackageName());
            int j = mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", mContext.getPackageName());
            int k = mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", mContext.getPackageName());
            int l = mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", mContext.getPackageName());
            return i != 0 && j != 0 && k != 0 && l != 0;
        }

        private c(Context context)
        {
            mContext = context;
        }

    }

    private static interface d
    {

        public abstract Drawable getDrawable(int i);

        public abstract boolean isValid();
    }


    public static final String TAG = "PlusOneDummyView";

    public PlusOneDummyView(Context context, int i)
    {
        super(context);
        Button button = new Button(context);
        button.setEnabled(false);
        button.setBackgroundDrawable(jT().getDrawable(i));
        Point point = cY(i);
        addView(button, new android.widget.FrameLayout.LayoutParams(point.x, point.y, 17));
    }

    private Point cY(int i)
    {
        int j;
        int k;
        Point point;
        j = 24;
        k = 20;
        point = new Point();
        i;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 117
    //                   1 111
    //                   2 123;
           goto _L1 _L2 _L3 _L4
_L1:
        int l = j;
        j = 38;
        k = l;
_L6:
        android.util.DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        float f = TypedValue.applyDimension(1, j, displaymetrics);
        float f1 = TypedValue.applyDimension(1, k, displaymetrics);
        point.x = (int)(0.5D + (double)f);
        point.y = (int)(0.5D + (double)f1);
        return point;
_L3:
        j = 32;
        continue; /* Loop/switch isn't completed */
_L2:
        k = 14;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 50;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private d jT()
    {
        Object obj = new b(getContext());
        if (!((d) (obj)).isValid())
        {
            obj = new c(getContext());
        }
        if (!((d) (obj)).isValid())
        {
            obj = new a(getContext());
        }
        return ((d) (obj));
    }
}
