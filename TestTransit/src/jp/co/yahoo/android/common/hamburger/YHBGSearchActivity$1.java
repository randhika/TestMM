// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGSearchActivity

class this._cls0
    implements android.view.er.YHBGSearchActivity._cls1
{

    final YHBGSearchActivity this$0;

    public void onClick(View view)
    {
        int i;
        YHBGSearchActivity.access$0(YHBGSearchActivity.this).setChecked(false);
        YHBGSearchActivity.access$1(YHBGSearchActivity.this).setChecked(false);
        YHBGSearchActivity.access$2(YHBGSearchActivity.this).setChecked(false);
        YHBGSearchActivity.access$3(YHBGSearchActivity.this).setChecked(false);
        YHBGSearchActivity.access$4(YHBGSearchActivity.this).setChecked(false);
        ((ToggleButton)view).setChecked(true);
        i = view.getId();
        if (i != 0x7f090007) goto _L2; else goto _L1
_L1:
        YHBGSearchActivity.access$5(YHBGSearchActivity.this, "http://search.yahoo.co.jp/search?");
_L4:
        String s = YHBGSearchActivity.access$6(YHBGSearchActivity.this).getText().toString();
        YHBGSearchActivity.access$6(YHBGSearchActivity.this).setText(s);
        YHBGSearchActivity.access$6(YHBGSearchActivity.this).setSelection(s.length());
        return;
_L2:
        if (i == 0x7f090008)
        {
            YHBGSearchActivity.access$5(YHBGSearchActivity.this, "http://image.search.yahoo.co.jp/search?");
        } else
        if (i == 0x7f090009)
        {
            YHBGSearchActivity.access$5(YHBGSearchActivity.this, "http://video.search.yahoo.co.jp/search?");
        } else
        if (i == 0x7f09000a)
        {
            YHBGSearchActivity.access$5(YHBGSearchActivity.this, "http://chiebukuro.search.yahoo.co.jp/search?");
        } else
        if (i == 0x7f09000b)
        {
            YHBGSearchActivity.access$5(YHBGSearchActivity.this, "http://realtime.search.yahoo.co.jp/search?");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = YHBGSearchActivity.this;
        super();
    }
}
