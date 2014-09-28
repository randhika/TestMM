// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.widget.CompoundButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            DeepRadioGroup

private class <init>
    implements android.widget.ner
{

    final DeepRadioGroup this$0;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (DeepRadioGroup.access$300(DeepRadioGroup.this))
        {
            return;
        }
        DeepRadioGroup.access$302(DeepRadioGroup.this, true);
        if (DeepRadioGroup.access$400(DeepRadioGroup.this) != -1)
        {
            DeepRadioGroup.access$500(DeepRadioGroup.this, DeepRadioGroup.access$400(DeepRadioGroup.this), false);
        }
        DeepRadioGroup.access$302(DeepRadioGroup.this, false);
        int i = compoundbutton.getId();
        DeepRadioGroup.access$600(DeepRadioGroup.this, i);
    }

    private ()
    {
        this$0 = DeepRadioGroup.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
