// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.text.Editable;
import android.text.TextWatcher;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AutoCompleteSuggestTextView

class this._cls0
    implements TextWatcher
{

    final AutoCompleteSuggestTextView this$0;

    public void afterTextChanged(Editable editable)
    {
        if (suggestListener == null)
        {
            return;
        }
        if (editable == null || editable.toString().equals(""))
        {
            suggestListener.onNoinput();
            return;
        } else
        {
            suggestListener.onInputed(editable.toString());
            return;
        }
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    ()
    {
        this$0 = AutoCompleteSuggestTextView.this;
        super();
    }
}
