// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

// Referenced classes of package android.support.v7.internal.widget:
//            AbsSpinnerICS, ListPopupWindow, AdapterViewICS

class SpinnerICS extends AbsSpinnerICS
    implements android.content.DialogInterface.OnClickListener
{
    private class DialogPopup
        implements SpinnerPopup, android.content.DialogInterface.OnClickListener
    {

        private ListAdapter mListAdapter;
        private AlertDialog mPopup;
        private CharSequence mPrompt;
        final SpinnerICS this$0;

        public void dismiss()
        {
            mPopup.dismiss();
            mPopup = null;
        }

        public CharSequence getHintText()
        {
            return mPrompt;
        }

        public boolean isShowing()
        {
            if (mPopup != null)
            {
                return mPopup.isShowing();
            } else
            {
                return false;
            }
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            setSelection(i);
            if (mOnItemClickListener != null)
            {
                performItemClick(null, i, mListAdapter.getItemId(i));
            }
            dismiss();
        }

        public void setAdapter(ListAdapter listadapter)
        {
            mListAdapter = listadapter;
        }

        public void setPromptText(CharSequence charsequence)
        {
            mPrompt = charsequence;
        }

        public void show()
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
            if (mPrompt != null)
            {
                builder.setTitle(mPrompt);
            }
            mPopup = builder.setSingleChoiceItems(mListAdapter, getSelectedItemPosition(), this).show();
        }

        private DialogPopup()
        {
            this$0 = SpinnerICS.this;
            super();
        }

    }

    private static class DropDownAdapter
        implements ListAdapter, SpinnerAdapter
    {

        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public boolean areAllItemsEnabled()
        {
            ListAdapter listadapter = mListAdapter;
            if (listadapter != null)
            {
                return listadapter.areAllItemsEnabled();
            } else
            {
                return true;
            }
        }

        public int getCount()
        {
            if (mAdapter == null)
            {
                return 0;
            } else
            {
                return mAdapter.getCount();
            }
        }

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            if (mAdapter == null)
            {
                return null;
            } else
            {
                return mAdapter.getDropDownView(i, view, viewgroup);
            }
        }

        public Object getItem(int i)
        {
            if (mAdapter == null)
            {
                return null;
            } else
            {
                return mAdapter.getItem(i);
            }
        }

        public long getItemId(int i)
        {
            if (mAdapter == null)
            {
                return -1L;
            } else
            {
                return mAdapter.getItemId(i);
            }
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            return getDropDownView(i, view, viewgroup);
        }

        public int getViewTypeCount()
        {
            return 1;
        }

        public boolean hasStableIds()
        {
            return mAdapter != null && mAdapter.hasStableIds();
        }

        public boolean isEmpty()
        {
            return getCount() == 0;
        }

        public boolean isEnabled(int i)
        {
            ListAdapter listadapter = mListAdapter;
            if (listadapter != null)
            {
                return listadapter.isEnabled(i);
            } else
            {
                return true;
            }
        }

        public void registerDataSetObserver(DataSetObserver datasetobserver)
        {
            if (mAdapter != null)
            {
                mAdapter.registerDataSetObserver(datasetobserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver datasetobserver)
        {
            if (mAdapter != null)
            {
                mAdapter.unregisterDataSetObserver(datasetobserver);
            }
        }

        public DropDownAdapter(SpinnerAdapter spinneradapter)
        {
            mAdapter = spinneradapter;
            if (spinneradapter instanceof ListAdapter)
            {
                mListAdapter = (ListAdapter)spinneradapter;
            }
        }
    }

    private class DropdownPopup extends ListPopupWindow
        implements SpinnerPopup
    {

        private ListAdapter mAdapter;
        private CharSequence mHintText;
        final SpinnerICS this$0;

        public CharSequence getHintText()
        {
            return mHintText;
        }

        public void setAdapter(ListAdapter listadapter)
        {
            super.setAdapter(listadapter);
            mAdapter = listadapter;
        }

        public void setPromptText(CharSequence charsequence)
        {
            mHintText = charsequence;
        }

        public void show()
        {
            int i = getPaddingLeft();
            Drawable drawable;
            int j;
            if (mDropDownWidth == -2)
            {
                int i1 = getWidth();
                int j1 = getPaddingRight();
                setContentWidth(Math.max(measureContentWidth((SpinnerAdapter)mAdapter, getBackground()), i1 - i - j1));
            } else
            if (mDropDownWidth == -1)
            {
                int k = getWidth();
                int l = getPaddingRight();
                setContentWidth(k - i - l);
            } else
            {
                setContentWidth(mDropDownWidth);
            }
            drawable = getBackground();
            j = 0;
            if (drawable != null)
            {
                drawable.getPadding(mTempRect);
                j = -mTempRect.left;
            }
            setHorizontalOffset(j + i);
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(getSelectedItemPosition());
        }


        public DropdownPopup(Context context, AttributeSet attributeset, int i)
        {
            this$0 = SpinnerICS.this;
            super(context, attributeset, i);
            setAnchorView(SpinnerICS.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterViewICS.OnItemClickListenerWrapper(SpinnerICS.this, new _cls1()));
        }
    }

    private static interface SpinnerPopup
    {

        public abstract void dismiss();

        public abstract CharSequence getHintText();

        public abstract boolean isShowing();

        public abstract void setAdapter(ListAdapter listadapter);

        public abstract void setPromptText(CharSequence charsequence);

        public abstract void show();
    }


    private static final int MAX_ITEMS_MEASURED = 15;
    static final int MODE_DIALOG = 0;
    static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    int mDropDownWidth;
    private int mGravity;
    private SpinnerPopup mPopup;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect;

    SpinnerICS(Context context)
    {
        this(context, ((AttributeSet) (null)));
    }

    SpinnerICS(Context context, int i)
    {
        this(context, null, android.support.v7.appcompat.R.attr.spinnerStyle, i);
    }

    SpinnerICS(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.spinnerStyle);
    }

    SpinnerICS(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, -1);
    }

    SpinnerICS(Context context, AttributeSet attributeset, int i, int j)
    {
        TypedArray typedarray;
        super(context, attributeset, i);
        mTempRect = new Rect();
        typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.Spinner, i, 0);
        if (j == -1)
        {
            j = typedarray.getInt(7, 0);
        }
        j;
        JVM INSTR tableswitch 0 1: default 72
    //                   0 131
    //                   1 147;
           goto _L1 _L2 _L3
_L1:
        mGravity = typedarray.getInt(0, 17);
        mPopup.setPromptText(typedarray.getString(6));
        typedarray.recycle();
        if (mTempAdapter != null)
        {
            mPopup.setAdapter(mTempAdapter);
            mTempAdapter = null;
        }
        return;
_L2:
        mPopup = new DialogPopup();
        continue; /* Loop/switch isn't completed */
_L3:
        DropdownPopup dropdownpopup = new DropdownPopup(context, attributeset, i);
        mDropDownWidth = typedarray.getLayoutDimension(3, -2);
        dropdownpopup.setBackgroundDrawable(typedarray.getDrawable(2));
        int k = typedarray.getDimensionPixelOffset(5, 0);
        if (k != 0)
        {
            dropdownpopup.setVerticalOffset(k);
        }
        int l = typedarray.getDimensionPixelOffset(4, 0);
        if (l != 0)
        {
            dropdownpopup.setHorizontalOffset(l);
        }
        mPopup = dropdownpopup;
        if (true) goto _L1; else goto _L4
_L4:
    }

    private View makeAndAddView(int i)
    {
        if (!mDataChanged)
        {
            View view1 = mRecycler.get(i);
            if (view1 != null)
            {
                setUpChild(view1);
                return view1;
            }
        }
        View view = mAdapter.getView(i, null, this);
        setUpChild(view);
        return view;
    }

    private void setUpChild(View view)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if (layoutparams == null)
        {
            layoutparams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, 0, layoutparams);
        view.setSelected(hasFocus());
        int i = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec, mSpinnerPadding.top + mSpinnerPadding.bottom, layoutparams.height);
        view.measure(ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mSpinnerPadding.left + mSpinnerPadding.right, layoutparams.width), i);
        int j = mSpinnerPadding.top + (getMeasuredHeight() - mSpinnerPadding.bottom - mSpinnerPadding.top - view.getMeasuredHeight()) / 2;
        int k = j + view.getMeasuredHeight();
        view.layout(0, j, 0 + view.getMeasuredWidth(), k);
    }

    public int getBaseline()
    {
        int i = -1;
        if (getChildCount() <= 0) goto _L2; else goto _L1
_L1:
        View view = getChildAt(0);
_L4:
        if (view != null)
        {
            int k = view.getBaseline();
            if (k >= 0)
            {
                i = k + view.getTop();
            }
        }
        return i;
_L2:
        SpinnerAdapter spinneradapter = mAdapter;
        view = null;
        if (spinneradapter != null)
        {
            int j = mAdapter.getCount();
            view = null;
            if (j > 0)
            {
                view = makeAndAddView(0);
                mRecycler.put(0, view);
                removeAllViewsInLayout();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence getPrompt()
    {
        return mPopup.getHintText();
    }

    void layout(int i, boolean flag)
    {
        int j;
        int k;
        View view;
        int l;
        int i1;
        j = mSpinnerPadding.left;
        k = getRight() - getLeft() - mSpinnerPadding.left - mSpinnerPadding.right;
        if (mDataChanged)
        {
            handleDataChanged();
        }
        if (mItemCount == 0)
        {
            resetList();
            return;
        }
        if (mNextSelectedPosition >= 0)
        {
            setSelectedPositionInt(mNextSelectedPosition);
        }
        recycleAllViews();
        removeAllViewsInLayout();
        mFirstPosition = mSelectedPosition;
        view = makeAndAddView(mSelectedPosition);
        l = view.getMeasuredWidth();
        i1 = j;
        7 & mGravity;
        JVM INSTR lookupswitch 2: default 144
    //                   1: 185
    //                   5: 201;
           goto _L1 _L2 _L3
_L1:
        view.offsetLeftAndRight(i1);
        mRecycler.clear();
        invalidate();
        checkSelectionChanged();
        mDataChanged = false;
        mNeedSync = false;
        setNextSelectedPositionInt(mSelectedPosition);
        return;
_L2:
        i1 = (j + k / 2) - l / 2;
        continue; /* Loop/switch isn't completed */
_L3:
        i1 = (j + k) - l;
        if (true) goto _L1; else goto _L4
_L4:
    }

    int measureContentWidth(SpinnerAdapter spinneradapter, Drawable drawable)
    {
        int i;
        if (spinneradapter == null)
        {
            i = 0;
        } else
        {
            i = 0;
            View view = null;
            int j = 0;
            int k = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            int l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            int i1 = Math.max(0, getSelectedItemPosition());
            int j1 = Math.min(spinneradapter.getCount(), i1 + 15);
            for (int k1 = Math.max(0, i1 - (15 - (j1 - i1))); k1 < j1; k1++)
            {
                int l1 = spinneradapter.getItemViewType(k1);
                if (l1 != j)
                {
                    j = l1;
                    view = null;
                }
                view = spinneradapter.getView(k1, view, this);
                if (view.getLayoutParams() == null)
                {
                    view.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
                }
                view.measure(k, l);
                i = Math.max(i, view.getMeasuredWidth());
            }

            if (drawable != null)
            {
                drawable.getPadding(mTempRect);
                return i + (mTempRect.left + mTempRect.right);
            }
        }
        return i;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        setSelection(i);
        dialoginterface.dismiss();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mPopup != null && mPopup.isShowing())
        {
            mPopup.dismiss();
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mInLayout = true;
        layout(0, false);
        mInLayout = false;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (mPopup != null && android.view.View.MeasureSpec.getMode(i) == 0x80000000)
        {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), measureContentWidth(getAdapter(), getBackground())), android.view.View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick()
    {
        boolean flag = super.performClick();
        if (!flag)
        {
            flag = true;
            if (!mPopup.isShowing())
            {
                mPopup.show();
            }
        }
        return flag;
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((SpinnerAdapter)adapter);
    }

    public void setAdapter(SpinnerAdapter spinneradapter)
    {
        super.setAdapter(spinneradapter);
        if (mPopup != null)
        {
            mPopup.setAdapter(new DropDownAdapter(spinneradapter));
            return;
        } else
        {
            mTempAdapter = new DropDownAdapter(spinneradapter);
            return;
        }
    }

    public void setGravity(int i)
    {
        if (mGravity != i)
        {
            if ((i & 7) == 0)
            {
                i |= 3;
            }
            mGravity = i;
            requestLayout();
        }
    }

    public void setOnItemClickListener(AdapterViewICS.OnItemClickListener onitemclicklistener)
    {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    void setOnItemClickListenerInt(AdapterViewICS.OnItemClickListener onitemclicklistener)
    {
        super.setOnItemClickListener(onitemclicklistener);
    }

    public void setPrompt(CharSequence charsequence)
    {
        mPopup.setPromptText(charsequence);
    }

    public void setPromptId(int i)
    {
        setPrompt(getContext().getText(i));
    }


    // Unreferenced inner class android/support/v7/internal/widget/SpinnerICS$DropdownPopup$1

/* anonymous class */
    class DropdownPopup._cls1
        implements AdapterViewICS.OnItemClickListener
    {

        final DropdownPopup this$1;
        final SpinnerICS val$this$0;

        public void onItemClick(AdapterViewICS adapterviewics, View view, int i, long l)
        {
            setSelection(i);
            if (mOnItemClickListener != null)
            {
                performItemClick(view, i, mAdapter.getItemId(i));
            }
            dismiss();
        }

            
            {
                this$1 = final_dropdownpopup;
                this$0 = SpinnerICS.this;
                super();
            }
    }

}
