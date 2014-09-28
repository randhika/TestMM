// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.support.v4.view.ActionProvider;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider extends ActionProvider
{
    public static interface OnShareTargetSelectedListener
    {

        public abstract boolean onShareTargetSelected(ShareActionProvider shareactionprovider, Intent intent);
    }

    private class ShareActivityChooserModelPolicy
        implements android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener
    {

        final ShareActionProvider this$0;

        public boolean onChooseActivity(ActivityChooserModel activitychoosermodel, Intent intent)
        {
            if (mOnShareTargetSelectedListener != null)
            {
                mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, intent);
            }
            return false;
        }

        private ShareActivityChooserModelPolicy()
        {
            this$0 = ShareActionProvider.this;
            super();
        }

    }

    private class ShareMenuItemOnMenuItemClickListener
        implements android.view.MenuItem.OnMenuItemClickListener
    {

        final ShareActionProvider this$0;

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            Intent intent = ActivityChooserModel.get(mContext, mShareHistoryFileName).chooseActivity(menuitem.getItemId());
            if (intent != null)
            {
                intent.addFlags(0x80000);
                mContext.startActivity(intent);
            }
            return true;
        }

        private ShareMenuItemOnMenuItemClickListener()
        {
            this$0 = ShareActionProvider.this;
            super();
        }

    }


    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    private final Context mContext;
    private int mMaxShownActivityCount;
    private android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener();
    private OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    private String mShareHistoryFileName;

    public ShareActionProvider(Context context)
    {
        super(context);
        mMaxShownActivityCount = 4;
        mShareHistoryFileName = "share_history.xml";
        mContext = context;
    }

    private void setActivityChooserPolicyIfNeeded()
    {
        if (mOnShareTargetSelectedListener == null)
        {
            return;
        }
        if (mOnChooseActivityListener == null)
        {
            mOnChooseActivityListener = new ShareActivityChooserModelPolicy();
        }
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setOnChooseActivityListener(mOnChooseActivityListener);
    }

    public boolean hasSubMenu()
    {
        return true;
    }

    public View onCreateActionView()
    {
        ActivityChooserModel activitychoosermodel = ActivityChooserModel.get(mContext, mShareHistoryFileName);
        ActivityChooserView activitychooserview = new ActivityChooserView(mContext);
        activitychooserview.setActivityChooserModel(activitychoosermodel);
        TypedValue typedvalue = new TypedValue();
        mContext.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionModeShareDrawable, typedvalue, true);
        activitychooserview.setExpandActivityOverflowButtonDrawable(mContext.getResources().getDrawable(typedvalue.resourceId));
        activitychooserview.setProvider(this);
        activitychooserview.setDefaultActionButtonContentDescription(android.support.v7.appcompat.R.string.abc_shareactionprovider_share_with_application);
        activitychooserview.setExpandActivityOverflowButtonContentDescription(android.support.v7.appcompat.R.string.abc_shareactionprovider_share_with);
        return activitychooserview;
    }

    public void onPrepareSubMenu(SubMenu submenu)
    {
        submenu.clear();
        ActivityChooserModel activitychoosermodel = ActivityChooserModel.get(mContext, mShareHistoryFileName);
        android.content.pm.PackageManager packagemanager = mContext.getPackageManager();
        int i = activitychoosermodel.getActivityCount();
        int j = Math.min(i, mMaxShownActivityCount);
        for (int k = 0; k < j; k++)
        {
            ResolveInfo resolveinfo1 = activitychoosermodel.getActivity(k);
            submenu.add(0, k, k, resolveinfo1.loadLabel(packagemanager)).setIcon(resolveinfo1.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
        }

        if (j < i)
        {
            SubMenu submenu1 = submenu.addSubMenu(0, j, j, mContext.getString(android.support.v7.appcompat.R.string.abc_activity_chooser_view_see_all));
            for (int l = 0; l < i; l++)
            {
                ResolveInfo resolveinfo = activitychoosermodel.getActivity(l);
                submenu1.add(0, l, l, resolveinfo.loadLabel(packagemanager)).setIcon(resolveinfo.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
            }

        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onsharetargetselectedlistener)
    {
        mOnShareTargetSelectedListener = onsharetargetselectedlistener;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String s)
    {
        mShareHistoryFileName = s;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent)
    {
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setIntent(intent);
    }



}
