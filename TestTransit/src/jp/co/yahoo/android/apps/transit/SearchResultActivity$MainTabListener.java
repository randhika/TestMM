// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultActivity

public static class cls
    implements android.support.v7.app.bListener
{

    private final SearchResultActivity activity;
    private final Class cls;
    private Fragment fragment;
    private final String tag;

    public void onTabReselected(android.support.v7.app.bListener blistener, FragmentTransaction fragmenttransaction)
    {
    }

    public void onTabSelected(android.support.v7.app.bListener blistener, FragmentTransaction fragmenttransaction)
    {
        int i = blistener.bListener();
        if (SearchResultActivity.access$700(activity) && i == 0 && activity.result_id != 0)
        {
            SearchResultActivity.access$702(activity, false);
            return;
        }
        SearchResultActivity.access$702(activity, false);
        if (i > activity.result_id)
        {
            if (activity.result_id == 0 && i > 1)
            {
                SearchResultActivity.access$602(activity, false);
            } else
            {
                SearchResultActivity.access$602(activity, true);
            }
        } else
        if (i == 0 && activity.result_id > 1)
        {
            SearchResultActivity.access$602(activity, true);
        } else
        {
            SearchResultActivity.access$602(activity, false);
        }
        fragmenttransaction.setTransition(4097);
        if (fragment == null)
        {
            fragment = Fragment.instantiate(activity, cls.getName());
            Bundle bundle = new Bundle();
            bundle.putInt("section_number", blistener.cls());
            fragment.setArguments(bundle);
            fragmenttransaction.add(0x1020002, fragment, tag);
            return;
        } else
        {
            fragmenttransaction.attach(fragment);
            return;
        }
    }

    public void onTabUnselected(android.support.v7.app.bListener blistener, FragmentTransaction fragmenttransaction)
    {
        if (fragment != null)
        {
            fragmenttransaction.detach(fragment);
        }
    }

    public (SearchResultActivity searchresultactivity, String s, Class class1)
    {
        activity = searchresultactivity;
        tag = s;
        cls = class1;
    }
}
