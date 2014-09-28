// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.common:
//            YInputFilter

public static class mStringList
    implements InputFilter
{

    private boolean mIsImeMode;
    private String mString;
    private List mStringList;

    public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
    {
        boolean flag;
        if (l - k > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIsImeMode = flag;
        if (mString != null)
        {
            if (charsequence.equals(mString))
            {
                return "";
            } else
            {
                return null;
            }
        }
        if (mStringList.contains(charsequence))
        {
            return "";
        } else
        {
            return null;
        }
    }

    public boolean isImeMode()
    {
        return mIsImeMode;
    }

    public (String s)
    {
        mIsImeMode = false;
        mString = s;
    }

    public mString(List list)
    {
        mIsImeMode = false;
        mStringList = list;
    }
}
