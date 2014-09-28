// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.util.Arrays;

// Referenced classes of package jp.co.yahoo.android.common:
//            YShakeDetector

private static class clear
{

    private int _position;
    private final int _size;
    private final float _values[];

    private void clear()
    {
        int i = _values.length;
        int j = 0;
        while (j < i) 
        {
            if (j % 2 == 0)
            {
                _values[j] = 9999F;
            } else
            {
                _values[j] = -9999F;
            }
            j++;
        }
    }

    public boolean add(float f)
    {
        _values[_position] = f;
        if (-1 + _size == _position)
        {
            _position = 0;
            return true;
        } else
        {
            _position = 1 + _position;
            return false;
        }
    }

    public float getMedian()
    {
        float af[] = (float[])_values.clone();
        Arrays.sort(af);
        return af[af.length / 2];
    }

    public (int i)
    {
        _position = 0;
        _size = i;
        _values = new float[i];
        clear();
    }
}
