// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TopTutorialDialogFragment

private static class imageList extends PagerAdapter
{

    private Context context;
    private int imageList[];

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        ((ViewPager)viewgroup).removeView((View)obj);
    }

    public int getCount()
    {
        return imageList.length;
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        ImageView imageview = new ImageView(context);
        if (i < imageList.length)
        {
            imageview.setImageResource(imageList[i]);
        }
        viewgroup.addView(imageview);
        return imageview;
    }

    public boolean isViewFromObject(View view, Object obj)
    {
        return view.equals(obj);
    }

    public (Context context1, int ai[])
    {
        context = context1;
        imageList = ai;
    }
}
