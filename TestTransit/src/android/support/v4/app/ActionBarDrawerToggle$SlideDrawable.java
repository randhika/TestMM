// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v4.view.ViewCompat;
import android.view.Window;

// Referenced classes of package android.support.v4.app:
//            ActionBarDrawerToggle

private class <init> extends InsetDrawable
    implements android.graphics.drawable.it>
{

    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    final ActionBarDrawerToggle this$0;

    public void draw(Canvas canvas)
    {
        int i = 1;
        copyBounds(mTmpRect);
        canvas.save();
        int j;
        int k;
        if (ViewCompat.getLayoutDirection(ActionBarDrawerToggle.access$400(ActionBarDrawerToggle.this).getWindow().getDecorView()) == i)
        {
            j = i;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            i = -1;
        }
        k = mTmpRect.width();
        canvas.translate(-mOffset * (float)k * mPosition * (float)i, 0.0F);
        if (j != 0 && !mHasMirroring)
        {
            canvas.translate(k, 0.0F);
            canvas.scale(-1F, 1.0F);
        }
        super.draw(canvas);
        canvas.restore();
    }

    public float getPosition()
    {
        return mPosition;
    }

    public void setOffset(float f)
    {
        mOffset = f;
        invalidateSelf();
    }

    public void setPosition(float f)
    {
        mPosition = f;
        invalidateSelf();
    }

    private (Drawable drawable)
    {
        this$0 = ActionBarDrawerToggle.this;
        super(drawable, 0);
        int i = android.os.ideDrawable.this._fld0;
        boolean flag = false;
        if (i > 18)
        {
            flag = true;
        }
        mHasMirroring = flag;
        mTmpRect = new Rect();
    }

    mTmpRect(Drawable drawable, mTmpRect mtmprect)
    {
        this(drawable);
    }
}
