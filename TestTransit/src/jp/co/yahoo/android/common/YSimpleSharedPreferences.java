// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Map;

public class YSimpleSharedPreferences
{

    private final Context _context;
    private final SharedPreferences _pref;

    public YSimpleSharedPreferences(Context context)
    {
        _pref = PreferenceManager.getDefaultSharedPreferences(context);
        _context = context;
    }

    public YSimpleSharedPreferences(Context context, String s)
    {
        _pref = context.getSharedPreferences(s, 0);
        _context = context;
    }

    public void clearAll()
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.clear();
        editor.commit();
    }

    public Map getAll()
    {
        return _pref.getAll();
    }

    protected Context getContext()
    {
        return _context;
    }

    public SharedPreferences getSharedPreferences()
    {
        return _pref;
    }

    public boolean readBoolean(String s, boolean flag)
    {
        return _pref.getBoolean(s, flag);
    }

    public float readFloat(String s, float f)
    {
        return _pref.getFloat(s, f);
    }

    public int readInt(String s, int i)
    {
        return _pref.getInt(s, i);
    }

    public long readLong(String s, long l)
    {
        return _pref.getLong(s, l);
    }

    public String readString(String s, String s1)
    {
        return _pref.getString(s, s1);
    }

    public void remove(String s)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.remove(s);
        editor.commit();
    }

    public void writeBoolean(String s, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.putBoolean(s, flag);
        editor.commit();
    }

    public void writeFloat(String s, float f)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.putFloat(s, f);
        editor.commit();
    }

    public void writeInt(String s, int i)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.putInt(s, i);
        editor.commit();
    }

    public void writeLong(String s, long l)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.putLong(s, l);
        editor.commit();
    }

    public void writeString(String s, String s1)
    {
        android.content.SharedPreferences.Editor editor = _pref.edit();
        editor.putString(s, s1);
        editor.commit();
    }
}
