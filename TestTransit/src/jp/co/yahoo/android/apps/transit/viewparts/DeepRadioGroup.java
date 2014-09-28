// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class DeepRadioGroup extends LinearLayout
{
    private class CheckedStateTracker
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        final DeepRadioGroup this$0;

        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            if (mProtectFromCheckedChange)
            {
                return;
            }
            mProtectFromCheckedChange = true;
            if (mCheckedId != -1)
            {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;
            int i = compoundbutton.getId();
            setCheckedId(i);
        }

        private CheckedStateTracker()
        {
            this$0 = DeepRadioGroup.this;
            super();
        }

    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams
    {

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            if (typedarray.hasValue(i))
            {
                width = typedarray.getLayoutDimension(i, "layout_width");
            } else
            {
                width = -2;
            }
            if (typedarray.hasValue(j))
            {
                height = typedarray.getLayoutDimension(j, "layout_height");
                return;
            } else
            {
                height = -2;
                return;
            }
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j, f);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
        }
    }

    public static interface OnCheckedChangeListener
    {

        public abstract void onCheckedChanged(DeepRadioGroup deepradiogroup, int i);
    }

    private static interface OnRadioButtonFoundListener
    {

        public abstract void onRadioButtonFound(RadioButton radiobutton);
    }

    private class PassThroughHierarchyChangeListener
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        private android.view.ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
        final DeepRadioGroup this$0;

        public void onChildViewAdded(View view, View view1)
        {
            if (view == DeepRadioGroup.this)
            {
                (new TreeScanner(new OnRadioButtonFoundListener() {

                    final PassThroughHierarchyChangeListener this$1;

                    public void onRadioButtonFound(RadioButton radiobutton)
                    {
                        if (radiobutton.getId() == -1)
                        {
                            radiobutton.setId(radiobutton.hashCode());
                        }
                        radiobutton.setOnCheckedChangeListener(mChildOnCheckedChangeListener);
                    }

            
            {
                this$1 = PassThroughHierarchyChangeListener.this;
                super();
            }
                })).scan(view1);
            }
            if (mOnHierarchyChangeListener != null)
            {
                mOnHierarchyChangeListener.onChildViewAdded(view, view1);
            }
        }

        public void onChildViewRemoved(View view, View view1)
        {
            if (view == DeepRadioGroup.this)
            {
                (new TreeScanner(new OnRadioButtonFoundListener() {

                    final PassThroughHierarchyChangeListener this$1;

                    public void onRadioButtonFound(RadioButton radiobutton)
                    {
                        radiobutton.setOnCheckedChangeListener(null);
                    }

            
            {
                this$1 = PassThroughHierarchyChangeListener.this;
                super();
            }
                })).scan(view1);
            }
        }


/*
        static android.view.ViewGroup.OnHierarchyChangeListener access$202(PassThroughHierarchyChangeListener passthroughhierarchychangelistener, android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            passthroughhierarchychangelistener.mOnHierarchyChangeListener = onhierarchychangelistener;
            return onhierarchychangelistener;
        }

*/

        private PassThroughHierarchyChangeListener()
        {
            this$0 = DeepRadioGroup.this;
            super();
        }

    }

    private class TreeScanner
    {

        OnRadioButtonFoundListener listener;
        final DeepRadioGroup this$0;

        public void scan(View view)
        {
            if (!(view instanceof RadioButton)) goto _L2; else goto _L1
_L1:
            listener.onRadioButtonFound((RadioButton)view);
_L4:
            return;
_L2:
            if (view instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view;
                int i = 0;
                int j = viewgroup.getChildCount();
                while (i < j) 
                {
                    scan(viewgroup.getChildAt(i));
                    i++;
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public TreeScanner(OnRadioButtonFoundListener onradiobuttonfoundlistener)
        {
            this$0 = DeepRadioGroup.this;
            super();
            listener = onradiobuttonfoundlistener;
        }
    }


    private int mCheckedId;
    private android.widget.CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private boolean mProtectFromCheckedChange;

    public DeepRadioGroup(Context context)
    {
        super(context);
        mCheckedId = -1;
        mProtectFromCheckedChange = false;
        setOrientation(1);
        init();
    }

    public DeepRadioGroup(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCheckedId = -1;
        mProtectFromCheckedChange = false;
        init();
    }

    private void init()
    {
        mChildOnCheckedChangeListener = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    private void setCheckedId(int i)
    {
        mCheckedId = i;
        if (mOnCheckedChangeListener != null)
        {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
    }

    private void setCheckedStateForView(int i, boolean flag)
    {
        View view = findViewById(i);
        if (view != null && (view instanceof RadioButton))
        {
            ((RadioButton)view).setChecked(flag);
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        (new TreeScanner(new OnRadioButtonFoundListener() {

            final DeepRadioGroup this$0;

            public void onRadioButtonFound(RadioButton radiobutton)
            {
                if (radiobutton.isChecked())
                {
                    mProtectFromCheckedChange = true;
                    if (mCheckedId != -1)
                    {
                        setCheckedStateForView(mCheckedId, false);
                    }
                    mProtectFromCheckedChange = false;
                    setCheckedId(radiobutton.getId());
                }
            }

            
            {
                this$0 = DeepRadioGroup.this;
                super();
            }
        })).scan(view);
        super.addView(view, i, layoutparams);
    }

    public void check(int i)
    {
        if (i != -1 && i == mCheckedId)
        {
            return;
        }
        if (mCheckedId != -1)
        {
            setCheckedStateForView(mCheckedId, false);
        }
        if (i != -1)
        {
            setCheckedStateForView(i, true);
        }
        setCheckedId(i);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void clearCheck()
    {
        check(-1);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public int getCheckedRadioButtonId()
    {
        return mCheckedId;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        if (mCheckedId != -1)
        {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener oncheckedchangelistener)
    {
        mOnCheckedChangeListener = oncheckedchangelistener;
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        mPassThroughListener.mOnHierarchyChangeListener = onhierarchychangelistener;
    }



/*
    static boolean access$302(DeepRadioGroup deepradiogroup, boolean flag)
    {
        deepradiogroup.mProtectFromCheckedChange = flag;
        return flag;
    }

*/




}
