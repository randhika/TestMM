// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.internal.widget:
//            ActivityChooserView, ActivityChooserModel

private class <init>
    implements android.widget.r, android.view.ner, android.view.ner, android.widget.r
{

    final ActivityChooserView this$0;

    private void notifyOnDismissListener()
    {
        if (ActivityChooserView.access$1000(ActivityChooserView.this) != null)
        {
            ActivityChooserView.access$1000(ActivityChooserView.this).onDismiss();
        }
    }

    public void onClick(View view)
    {
        if (view == ActivityChooserView.access$700(ActivityChooserView.this))
        {
            dismissPopup();
            android.content.pm.ResolveInfo resolveinfo = ActivityChooserView.access$000(ActivityChooserView.this).getDefaultActivity();
            int i = ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().getActivityIndex(resolveinfo);
            Intent intent = ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().chooseActivity(i);
            if (intent != null)
            {
                intent.addFlags(0x80000);
                getContext().startActivity(intent);
            }
            return;
        }
        if (view == ActivityChooserView.access$800(ActivityChooserView.this))
        {
            ActivityChooserView.access$602(ActivityChooserView.this, false);
            ActivityChooserView.access$500(ActivityChooserView.this, ActivityChooserView.access$900(ActivityChooserView.this));
            return;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public void onDismiss()
    {
        notifyOnDismissListener();
        if (mProvider != null)
        {
            mProvider.subUiVisibilityChanged(false);
        }
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        ((ooserViewAdapter)adapterview.getAdapter()).getItemViewType(i);
        JVM INSTR tableswitch 0 1: default 32
    //                   0 50
    //                   1 40;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException();
_L3:
        ActivityChooserView.access$500(ActivityChooserView.this, 0x7fffffff);
_L5:
        return;
_L2:
        dismissPopup();
        if (!ActivityChooserView.access$600(ActivityChooserView.this))
        {
            break; /* Loop/switch isn't completed */
        }
        if (i > 0)
        {
            ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().setDefaultActivity(i);
            return;
        }
        if (true) goto _L5; else goto _L4
_L4:
        Intent intent;
        if (!ActivityChooserView.access$000(ActivityChooserView.this).getShowDefaultActivity())
        {
            i++;
        }
        intent = ActivityChooserView.access$000(ActivityChooserView.this).getDataModel().chooseActivity(i);
        if (intent != null)
        {
            intent.addFlags(0x80000);
            getContext().startActivity(intent);
            return;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public boolean onLongClick(View view)
    {
        if (view == ActivityChooserView.access$700(ActivityChooserView.this))
        {
            if (ActivityChooserView.access$000(ActivityChooserView.this).getCount() > 0)
            {
                ActivityChooserView.access$602(ActivityChooserView.this, true);
                ActivityChooserView.access$500(ActivityChooserView.this, ActivityChooserView.access$900(ActivityChooserView.this));
            }
            return true;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    private ooserViewAdapter()
    {
        this$0 = ActivityChooserView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
