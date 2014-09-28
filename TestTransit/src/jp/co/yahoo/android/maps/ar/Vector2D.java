// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;


public class Vector2D
{

    public float mX;
    public float mY;

    public Vector2D()
    {
        mX = 0.0F;
        mY = 0.0F;
    }

    public Vector2D(float f, float f1)
    {
        mX = f;
        mY = f1;
    }

    public Vector2D addition(Vector2D vector2d)
    {
        return new Vector2D(mX + vector2d.mX, mY + vector2d.mY);
    }

    public Vector2D division(float f)
    {
        if (f == 0.0F)
        {
            return new Vector2D();
        } else
        {
            return new Vector2D(mX * (1.0F / f), mY * (1.0F / f));
        }
    }

    public float dotProduct(Vector2D vector2d)
    {
        return mX * vector2d.mX + mY * vector2d.mY;
    }

    public float getSquareLength()
    {
        return mX * mX + mY * mY;
    }

    public Vector2D multiplication(float f)
    {
        return new Vector2D(f * mX, f * mY);
    }

    public Vector2D subtraction(Vector2D vector2d)
    {
        return new Vector2D(mX - vector2d.mX, mY - vector2d.mY);
    }

    public Vector2D unitVector()
    {
        return division((float)Math.sqrt(getSquareLength()));
    }
}
