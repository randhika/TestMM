// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

// Referenced classes of package com.google.android.gms.internal:
//            ip

public final class gt extends Drawable
    implements android.graphics.drawable.Drawable.Callback
{
    private static final class a extends Drawable
    {

        private static final a FK = new a();
        private static final a FL = new a();

        static a fh()
        {
            return FK;
        }

        public void draw(Canvas canvas)
        {
        }

        public android.graphics.drawable.Drawable.ConstantState getConstantState()
        {
            return FL;
        }

        public int getOpacity()
        {
            return -2;
        }

        public void setAlpha(int i)
        {
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
        }


        private a()
        {
        }
    }

    private static final class a.a extends android.graphics.drawable.Drawable.ConstantState
    {

        public int getChangingConfigurations()
        {
            return 0;
        }

        public Drawable newDrawable()
        {
            return a.fh();
        }

        private a.a()
        {
        }

    }

    static final class b extends android.graphics.drawable.Drawable.ConstantState
    {

        int FM;
        int FN;

        public int getChangingConfigurations()
        {
            return FM;
        }

        public Drawable newDrawable()
        {
            return new gt(this);
        }

        b(b b1)
        {
            if (b1 != null)
            {
                FM = b1.FM;
                FN = b1.FN;
            }
        }
    }


    private int FA;
    private int FB;
    private boolean FC;
    private b FD;
    private Drawable FE;
    private Drawable FF;
    private boolean FG;
    private boolean FH;
    private boolean FI;
    private int FJ;
    private boolean Fp;
    private int Fv;
    private long Fw;
    private int Fx;
    private int Fy;
    private int Fz;

    public gt(Drawable drawable, Drawable drawable1)
    {
        this(null);
        if (drawable == null)
        {
            drawable = a.fh();
        }
        FE = drawable;
        drawable.setCallback(this);
        b b1 = FD;
        b1.FN = b1.FN | drawable.getChangingConfigurations();
        if (drawable1 == null)
        {
            drawable1 = a.fh();
        }
        FF = drawable1;
        drawable1.setCallback(this);
        b b2 = FD;
        b2.FN = b2.FN | drawable1.getChangingConfigurations();
    }

    gt(b b1)
    {
        Fv = 0;
        Fz = 255;
        FB = 0;
        Fp = true;
        FD = new b(b1);
    }

    public boolean canConstantState()
    {
        if (!FG)
        {
            boolean flag;
            if (FE.getConstantState() != null && FF.getConstantState() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            FH = flag;
            FG = true;
        }
        return FH;
    }

    public void draw(Canvas canvas)
    {
        boolean flag = true;
        Fv;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 101
    //                   2 119;
           goto _L1 _L2 _L3
_L1:
        boolean flag1 = flag;
_L5:
        int i;
        boolean flag2;
        Drawable drawable;
        Drawable drawable1;
        i = FB;
        flag2 = Fp;
        drawable = FE;
        drawable1 = FF;
        if (flag1)
        {
            if (!flag2 || i == 0)
            {
                drawable.draw(canvas);
            }
            if (i == Fz)
            {
                drawable1.setAlpha(Fz);
                drawable1.draw(canvas);
            }
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        Fw = SystemClock.uptimeMillis();
        Fv = 2;
        flag1 = false;
        if (true) goto _L5; else goto _L4
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        if (Fw >= 0L)
        {
            float f = (float)(SystemClock.uptimeMillis() - Fw) / (float)FA;
            float f1;
            if (f < 1.0F)
            {
                flag = false;
            }
            if (flag)
            {
                Fv = 0;
            }
            f1 = Math.min(f, 1.0F);
            FB = (int)((float)Fx + f1 * (float)(Fy - Fx));
        }
        if (true) goto _L1; else goto _L6
_L6:
        if (flag2)
        {
            drawable.setAlpha(Fz - i);
        }
        drawable.draw(canvas);
        if (flag2)
        {
            drawable.setAlpha(Fz);
        }
        if (i > 0)
        {
            drawable1.setAlpha(i);
            drawable1.draw(canvas);
            drawable1.setAlpha(Fz);
        }
        invalidateSelf();
        return;
    }

    public Drawable fg()
    {
        return FF;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | FD.FM | FD.FN;
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if (canConstantState())
        {
            FD.FM = getChangingConfigurations();
            return FD;
        } else
        {
            return null;
        }
    }

    public int getIntrinsicHeight()
    {
        return Math.max(FE.getIntrinsicHeight(), FF.getIntrinsicHeight());
    }

    public int getIntrinsicWidth()
    {
        return Math.max(FE.getIntrinsicWidth(), FF.getIntrinsicWidth());
    }

    public int getOpacity()
    {
        if (!FI)
        {
            FJ = Drawable.resolveOpacity(FE.getOpacity(), FF.getOpacity());
            FI = true;
        }
        return FJ;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if (ip.gc())
        {
            android.graphics.drawable.Drawable.Callback callback = getCallback();
            if (callback != null)
            {
                callback.invalidateDrawable(this);
            }
        }
    }

    public Drawable mutate()
    {
        if (!FC && super.mutate() == this)
        {
            if (!canConstantState())
            {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            FE.mutate();
            FF.mutate();
            FC = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        FE.setBounds(rect);
        FF.setBounds(rect);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        if (ip.gc())
        {
            android.graphics.drawable.Drawable.Callback callback = getCallback();
            if (callback != null)
            {
                callback.scheduleDrawable(this, runnable, l);
            }
        }
    }

    public void setAlpha(int i)
    {
        if (FB == Fz)
        {
            FB = i;
        }
        Fz = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        FE.setColorFilter(colorfilter);
        FF.setColorFilter(colorfilter);
    }

    public void startTransition(int i)
    {
        Fx = 0;
        Fy = Fz;
        FB = 0;
        FA = i;
        Fv = 1;
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        if (ip.gc())
        {
            android.graphics.drawable.Drawable.Callback callback = getCallback();
            if (callback != null)
            {
                callback.unscheduleDrawable(this, runnable);
            }
        }
    }
}
