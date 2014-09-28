// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.internal.view.ActionBarPolicy;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package android.support.v7.internal.widget:
//            SpinnerICS, AdapterViewICS, CompatTextView

public class ScrollingTabContainerView extends HorizontalScrollView
    implements AdapterViewICS.OnItemClickListener
{
    private class TabAdapter extends BaseAdapter
    {

        final ScrollingTabContainerView this$0;

        public int getCount()
        {
            return mTabLayout.getChildCount();
        }

        public Object getItem(int i)
        {
            return ((TabView)mTabLayout.getChildAt(i)).getTab();
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if (view == null)
            {
                return createTabView((android.support.v7.app.ActionBar.Tab)getItem(i), true);
            } else
            {
                ((TabView)view).bindTab((android.support.v7.app.ActionBar.Tab)getItem(i));
                return view;
            }
        }

        private TabAdapter()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
        }

    }

    private class TabClickListener
        implements android.view.View.OnClickListener
    {

        final ScrollingTabContainerView this$0;

        public void onClick(View view)
        {
            ((TabView)view).getTab().select();
            int i = mTabLayout.getChildCount();
            int j = 0;
            while (j < i) 
            {
                View view1 = mTabLayout.getChildAt(j);
                boolean flag;
                if (view1 == view)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                view1.setSelected(flag);
                j++;
            }
        }

        private TabClickListener()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
        }

    }

    public static class TabView extends LinearLayout
    {

        private View mCustomView;
        private ImageView mIconView;
        private ScrollingTabContainerView mParent;
        private android.support.v7.app.ActionBar.Tab mTab;
        private TextView mTextView;

        void attach(ScrollingTabContainerView scrollingtabcontainerview, android.support.v7.app.ActionBar.Tab tab, boolean flag)
        {
            mParent = scrollingtabcontainerview;
            mTab = tab;
            if (flag)
            {
                setGravity(19);
            }
            update();
        }

        public void bindTab(android.support.v7.app.ActionBar.Tab tab)
        {
            mTab = tab;
            update();
        }

        public android.support.v7.app.ActionBar.Tab getTab()
        {
            return mTab;
        }

        public void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            int k;
            if (mParent != null)
            {
                k = mParent.mMaxTabWidth;
            } else
            {
                k = 0;
            }
            if (k > 0 && getMeasuredWidth() > k)
            {
                super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), j);
            }
        }

        public void update()
        {
            android.support.v7.app.ActionBar.Tab tab = mTab;
            View view = tab.getCustomView();
            if (view != null)
            {
                android.view.ViewParent viewparent = view.getParent();
                if (viewparent != this)
                {
                    if (viewparent != null)
                    {
                        ((ViewGroup)viewparent).removeView(view);
                    }
                    addView(view);
                }
                mCustomView = view;
                if (mTextView != null)
                {
                    mTextView.setVisibility(8);
                }
                if (mIconView != null)
                {
                    mIconView.setVisibility(8);
                    mIconView.setImageDrawable(null);
                }
            } else
            {
                if (mCustomView != null)
                {
                    removeView(mCustomView);
                    mCustomView = null;
                }
                android.graphics.drawable.Drawable drawable = tab.getIcon();
                CharSequence charsequence = tab.getText();
                if (drawable != null)
                {
                    if (mIconView == null)
                    {
                        ImageView imageview = new ImageView(getContext());
                        android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-2, -2);
                        layoutparams1.gravity = 16;
                        imageview.setLayoutParams(layoutparams1);
                        addView(imageview, 0);
                        mIconView = imageview;
                    }
                    mIconView.setImageDrawable(drawable);
                    mIconView.setVisibility(0);
                } else
                if (mIconView != null)
                {
                    mIconView.setVisibility(8);
                    mIconView.setImageDrawable(null);
                }
                if (charsequence != null)
                {
                    if (mTextView == null)
                    {
                        CompatTextView compattextview = new CompatTextView(getContext(), null, android.support.v7.appcompat.R.attr.actionBarTabTextStyle);
                        compattextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
                        layoutparams.gravity = 16;
                        compattextview.setLayoutParams(layoutparams);
                        addView(compattextview);
                        mTextView = compattextview;
                    }
                    mTextView.setText(charsequence);
                    mTextView.setVisibility(0);
                } else
                if (mTextView != null)
                {
                    mTextView.setVisibility(8);
                    mTextView.setText(null);
                }
                if (mIconView != null)
                {
                    mIconView.setContentDescription(tab.getContentDescription());
                    return;
                }
            }
        }

        public TabView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    private static final String TAG = "ScrollingTabContainerView";
    private boolean mAllowCollapse;
    private int mContentHeight;
    private final LayoutInflater mInflater;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    private LinearLayout mTabLayout;
    Runnable mTabSelector;
    private SpinnerICS mTabSpinner;

    public ScrollingTabContainerView(Context context)
    {
        super(context);
        mInflater = LayoutInflater.from(context);
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy actionbarpolicy = ActionBarPolicy.get(context);
        setContentHeight(actionbarpolicy.getTabContainerHeight());
        mStackedTabMaxWidth = actionbarpolicy.getStackedTabMaxWidth();
        mTabLayout = (LinearLayout)mInflater.inflate(android.support.v7.appcompat.R.layout.abc_action_bar_tabbar, this, false);
        addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    private SpinnerICS createSpinner()
    {
        SpinnerICS spinnerics = new SpinnerICS(getContext(), null, android.support.v7.appcompat.R.attr.actionDropDownStyle);
        spinnerics.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -1));
        spinnerics.setOnItemClickListenerInt(this);
        return spinnerics;
    }

    private TabView createTabView(android.support.v7.app.ActionBar.Tab tab, boolean flag)
    {
        TabView tabview = (TabView)mInflater.inflate(android.support.v7.appcompat.R.layout.abc_action_bar_tab, mTabLayout, false);
        tabview.attach(this, tab, flag);
        if (flag)
        {
            tabview.setBackgroundDrawable(null);
            tabview.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, mContentHeight));
            return tabview;
        }
        tabview.setFocusable(true);
        if (mTabClickListener == null)
        {
            mTabClickListener = new TabClickListener();
        }
        tabview.setOnClickListener(mTabClickListener);
        return tabview;
    }

    private boolean isCollapsed()
    {
        return mTabSpinner != null && mTabSpinner.getParent() == this;
    }

    private void performCollapse()
    {
        if (isCollapsed())
        {
            return;
        }
        if (mTabSpinner == null)
        {
            mTabSpinner = createSpinner();
        }
        removeView(mTabLayout);
        addView(mTabSpinner, new android.view.ViewGroup.LayoutParams(-2, -1));
        if (mTabSpinner.getAdapter() == null)
        {
            mTabSpinner.setAdapter(new TabAdapter());
        }
        if (mTabSelector != null)
        {
            removeCallbacks(mTabSelector);
            mTabSelector = null;
        }
        mTabSpinner.setSelection(mSelectedTabIndex);
    }

    private boolean performExpand()
    {
        if (!isCollapsed())
        {
            return false;
        } else
        {
            removeView(mTabSpinner);
            addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
            setTabSelected(mTabSpinner.getSelectedItemPosition());
            return false;
        }
    }

    public void addTab(android.support.v7.app.ActionBar.Tab tab, int i, boolean flag)
    {
        TabView tabview = createTabView(tab, false);
        mTabLayout.addView(tabview, i, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        if (mTabSpinner != null)
        {
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (flag)
        {
            tabview.setSelected(true);
        }
        if (mAllowCollapse)
        {
            requestLayout();
        }
    }

    public void addTab(android.support.v7.app.ActionBar.Tab tab, boolean flag)
    {
        TabView tabview = createTabView(tab, false);
        mTabLayout.addView(tabview, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        if (mTabSpinner != null)
        {
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (flag)
        {
            tabview.setSelected(true);
        }
        if (mAllowCollapse)
        {
            requestLayout();
        }
    }

    public void animateToTab(int i)
    {
        final View tabView = mTabLayout.getChildAt(i);
        if (mTabSelector != null)
        {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {

            final ScrollingTabContainerView this$0;
            final View val$tabView;

            public void run()
            {
                int j = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(j, 0);
                mTabSelector = null;
            }

            
            {
                this$0 = ScrollingTabContainerView.this;
                tabView = view;
                super();
            }
        };
        post(mTabSelector);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (mTabSelector != null)
        {
            post(mTabSelector);
        }
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        ActionBarPolicy actionbarpolicy = ActionBarPolicy.get(getContext());
        setContentHeight(actionbarpolicy.getTabContainerHeight());
        mStackedTabMaxWidth = actionbarpolicy.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mTabSelector != null)
        {
            removeCallbacks(mTabSelector);
        }
    }

    public void onItemClick(AdapterViewICS adapterviewics, View view, int i, long l)
    {
        ((TabView)view).getTab().select();
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        boolean flag;
        int l;
        boolean flag1;
        if (k == 0x40000000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setFillViewport(flag);
        l = mTabLayout.getChildCount();
        if (l > 1 && (k == 0x40000000 || k == 0x80000000))
        {
            int i1;
            int j1;
            int k1;
            if (l > 2)
            {
                mMaxTabWidth = (int)(0.4F * (float)android.view.View.MeasureSpec.getSize(i));
            } else
            {
                mMaxTabWidth = android.view.View.MeasureSpec.getSize(i) / 2;
            }
            mMaxTabWidth = Math.min(mMaxTabWidth, mStackedTabMaxWidth);
        } else
        {
            mMaxTabWidth = -1;
        }
        i1 = android.view.View.MeasureSpec.makeMeasureSpec(mContentHeight, 0x40000000);
        if (!flag && mAllowCollapse)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            mTabLayout.measure(0, i1);
            if (mTabLayout.getMeasuredWidth() > android.view.View.MeasureSpec.getSize(i))
            {
                performCollapse();
            } else
            {
                performExpand();
            }
        } else
        {
            performExpand();
        }
        j1 = getMeasuredWidth();
        super.onMeasure(i, i1);
        k1 = getMeasuredWidth();
        if (flag && j1 != k1)
        {
            setTabSelected(mSelectedTabIndex);
        }
    }

    public void removeAllTabs()
    {
        mTabLayout.removeAllViews();
        if (mTabSpinner != null)
        {
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (mAllowCollapse)
        {
            requestLayout();
        }
    }

    public void removeTabAt(int i)
    {
        mTabLayout.removeViewAt(i);
        if (mTabSpinner != null)
        {
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (mAllowCollapse)
        {
            requestLayout();
        }
    }

    public void setAllowCollapse(boolean flag)
    {
        mAllowCollapse = flag;
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
        requestLayout();
    }

    public void setTabSelected(int i)
    {
        mSelectedTabIndex = i;
        int j = mTabLayout.getChildCount();
        int k = 0;
        while (k < j) 
        {
            View view = mTabLayout.getChildAt(k);
            boolean flag;
            if (k == i)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view.setSelected(flag);
            if (flag)
            {
                animateToTab(i);
            }
            k++;
        }
        if (mTabSpinner != null && i >= 0)
        {
            mTabSpinner.setSelection(i);
        }
    }

    public void updateTab(int i)
    {
        ((TabView)mTabLayout.getChildAt(i)).update();
        if (mTabSpinner != null)
        {
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (mAllowCollapse)
        {
            requestLayout();
        }
    }


}
