// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.viewparts.AutoCompleteSuggestTextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements android.view.stener
{

    final InputSearch this$0;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d040c));
        InputSearch.access$600(InputSearch.this).hideSoftInputFromWindow(InputSearch.access$500(InputSearch.this).getWindowToken(), 0);
        InputSearch.access$800(InputSearch.this, InputSearch.access$700(InputSearch.this));
        if (InputSearch.access$900(InputSearch.this) == null)
        {
            setRegist();
            if (InputSearch.access$1000(InputSearch.this) == null)
            {
                InputSearch.access$1100(InputSearch.this);
                return;
            } else
            {
                InputSearch.access$1200(InputSearch.this).requestAsync(true);
                return;
            }
        } else
        {
            InputSearch.access$1100(InputSearch.this);
            return;
        }
    }

    chSSO()
    {
        this$0 = InputSearch.this;
        super();
    }
}
