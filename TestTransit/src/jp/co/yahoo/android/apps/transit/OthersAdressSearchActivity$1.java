// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersAdressSearchActivity

class this._cls0
    implements TextWatcher
{

    final OthersAdressSearchActivity this$0;

    public void afterTextChanged(Editable editable)
    {
        if (editable.length() < 1)
        {
            OthersAdressSearchActivity.access$000(OthersAdressSearchActivity.this).setVisibility(8);
            return;
        } else
        {
            OthersAdressSearchActivity.access$000(OthersAdressSearchActivity.this).setVisibility(0);
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
        this$0 = OthersAdressSearchActivity.this;
        super();
    }
}
