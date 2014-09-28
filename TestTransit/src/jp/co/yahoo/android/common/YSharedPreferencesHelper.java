// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.common:
//            YSimpleSharedPreferences

public abstract class YSharedPreferencesHelper extends YSimpleSharedPreferences
{

    private static final String KEY_VERSION = "YSharedPreferencesHelper_version";
    private static final String TAG = jp/co/yahoo/android/common/YSharedPreferencesHelper.getSimpleName();

    public YSharedPreferencesHelper(Context context, int i)
    {
        super(context);
        init(i);
    }

    public YSharedPreferencesHelper(Context context, String s, int i)
    {
        super(context, s);
        init(i);
    }

    private void init(int i)
    {
        if (i < 1)
        {
            throw new IllegalArgumentException("version\u306F1\u4EE5\u4E0A\u306B\u8A2D\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044\u3002");
        }
        SharedPreferences sharedpreferences = getSharedPreferences();
        int j = sharedpreferences.getInt("YSharedPreferencesHelper_version", 0);
        if (j == i)
        {
            return;
        }
        if (sharedpreferences.getAll().size() == 0)
        {
            onCreate();
            return;
        } else
        {
            onUpdate(j, i);
            writeInt("YSharedPreferencesHelper_version", i);
            return;
        }
    }

    protected abstract void onCreate();

    protected abstract void onUpdate(int i, int j);

}
