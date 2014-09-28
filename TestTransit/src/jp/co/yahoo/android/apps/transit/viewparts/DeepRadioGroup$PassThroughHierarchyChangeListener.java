// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.view.View;
import android.widget.RadioButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            DeepRadioGroup

private class <init>
    implements android.view.hroughHierarchyChangeListener
{

    private android.view.canner.scan mOnHierarchyChangeListener;
    final DeepRadioGroup this$0;

    public void onChildViewAdded(View view, View view1)
    {
        if (view == DeepRadioGroup.this)
        {
            (new this._cls0(DeepRadioGroup.this, new DeepRadioGroup.OnRadioButtonFoundListener() {

                final DeepRadioGroup.PassThroughHierarchyChangeListener this$1;

                public void onRadioButtonFound(RadioButton radiobutton)
                {
                    if (radiobutton.getId() == -1)
                    {
                        radiobutton.setId(radiobutton.hashCode());
                    }
                    radiobutton.setOnCheckedChangeListener(DeepRadioGroup.access$700(this$0));
                }

            
            {
                this$1 = DeepRadioGroup.PassThroughHierarchyChangeListener.this;
                super();
            }
            }))._mth0(view1);
        }
        if (mOnHierarchyChangeListener != null)
        {
            mOnHierarchyChangeListener.ed(view, view1);
        }
    }

    public void onChildViewRemoved(View view, View view1)
    {
        if (view == DeepRadioGroup.this)
        {
            (new this._cls0(DeepRadioGroup.this, new DeepRadioGroup.OnRadioButtonFoundListener() {

                final DeepRadioGroup.PassThroughHierarchyChangeListener this$1;

                public void onRadioButtonFound(RadioButton radiobutton)
                {
                    radiobutton.setOnCheckedChangeListener(null);
                }

            
            {
                this$1 = DeepRadioGroup.PassThroughHierarchyChangeListener.this;
                super();
            }
            }))._mth2(view1);
        }
    }


/*
    static android.view.hroughHierarchyChangeListener access$202(_cls2 _pcls2, android.view.hroughHierarchyChangeListener hroughhierarchychangelistener)
    {
        _pcls2.mOnHierarchyChangeListener = hroughhierarchychangelistener;
        return hroughhierarchychangelistener;
    }

*/

    private _cls2.this._cls1()
    {
        this$0 = DeepRadioGroup.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
