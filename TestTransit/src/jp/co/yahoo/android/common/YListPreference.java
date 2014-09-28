// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.AttributeSet;

public class YListPreference extends ListPreference
    implements android.preference.Preference.OnPreferenceChangeListener
{

    private static final String TAG = "YCalPrefListPreference";
    private boolean _autoSummary;
    private android.preference.Preference.OnPreferenceChangeListener _onPrefChangetListener;

    public YListPreference(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        _autoSummary = true;
        _onPrefChangetListener = null;
        super.setOnPreferenceChangeListener(this);
    }

    private boolean setSummary(Preference preference, Object obj)
    {
        if (!_autoSummary)
        {
            return true;
        }
        CharSequence acharsequence[] = getEntries();
        CharSequence acharsequence1[] = getEntryValues();
        int i = acharsequence1.length;
        for (int j = 0; j < i; j++)
        {
            if (acharsequence1[j].equals(obj))
            {
                preference.setSummary(acharsequence[j]);
                return true;
            }
        }

        throw new IllegalArgumentException(obj.toString());
    }

    protected void onAttachedToActivity()
    {
        super.onAttachedToActivity();
        setSummary(this, getValue());
    }

    public boolean onPreferenceChange(Preference preference, Object obj)
    {
        boolean flag = true;
        if (_onPrefChangetListener != null)
        {
            flag = _onPrefChangetListener.onPreferenceChange(preference, obj);
        }
        if (flag)
        {
            setSummary(preference, obj);
        }
        return flag;
    }

    public void setOnPreferenceChangeListener(android.preference.Preference.OnPreferenceChangeListener onpreferencechangelistener)
    {
        _onPrefChangetListener = onpreferencechangelistener;
    }

    public void setShowAuthSummary(boolean flag)
    {
        _autoSummary = flag;
    }
}
