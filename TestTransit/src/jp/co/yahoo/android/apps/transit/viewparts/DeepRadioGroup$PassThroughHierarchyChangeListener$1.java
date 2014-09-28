// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.widget.RadioButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            DeepRadioGroup

class this._cls1
    implements this._cls1
{

    final is._cls0 this$1;

    public void onRadioButtonFound(RadioButton radiobutton)
    {
        if (radiobutton.getId() == -1)
        {
            radiobutton.setId(radiobutton.hashCode());
        }
        radiobutton.setOnCheckedChangeListener(DeepRadioGroup.access$700(_fld0));
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
