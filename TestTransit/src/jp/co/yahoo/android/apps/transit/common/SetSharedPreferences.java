// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SetSharedPreferences extends PreferenceActivity
{

    private SharedPreferences sp;

    public SetSharedPreferences(Context context, String s)
    {
        sp = context.getSharedPreferences(s, 0);
    }

    public void delSharedPreferenceData()
    {
        android.content.SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public Map getSharedPreferenceData()
    {
        return sp.getAll();
    }

    public String getStringSharedPreferenceData(String s)
    {
        return sp.getString(s, "");
    }

    public void setSharedPreferenceData(Map map)
    {
        android.content.SharedPreferences.Editor editor = sp.edit();
        java.util.Map.Entry entry;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); editor.putString((String)entry.getKey(), entry.getValue().toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        editor.commit();
    }
}
