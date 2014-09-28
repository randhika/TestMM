// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;

public class TopTutorialDialogFragment extends DialogFragment
{
    public static interface TopTutorialDialogListener
    {

        public abstract void onCanceled(boolean flag);

        public abstract void onCloseClick(DialogFragment dialogfragment, boolean flag);

        public abstract void onVoteClick(DialogFragment dialogfragment, boolean flag);
    }

    private static class TopTutorialPagerAdapter extends PagerAdapter
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

        public TopTutorialPagerAdapter(Context context1, int ai[])
        {
            context = context1;
            imageList = ai;
        }
    }


    private Activity activity;
    private TopTutorialDialogListener listener;
    private boolean showReview;

    public TopTutorialDialogFragment()
    {
    }

    public static TopTutorialDialogFragment newInstance(boolean flag, int ai[])
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("review", flag);
        bundle.putIntArray("image", ai);
        TopTutorialDialogFragment toptutorialdialogfragment = new TopTutorialDialogFragment();
        toptutorialdialogfragment.setArguments(bundle);
        return toptutorialdialogfragment;
    }

    public void onAttach(Activity activity1)
    {
        super.onAttach(activity1);
        activity = activity1;
        if (activity1 instanceof TopTutorialDialogListener)
        {
            listener = (TopTutorialDialogListener)activity1;
        }
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        if (listener != null)
        {
            listener.onCanceled(showReview);
        }
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Bundle bundle1 = getArguments();
        final boolean showReview = bundle1.getBoolean("review");
        final int imageList[] = bundle1.getIntArray("image");
        this.showReview = showReview;
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(1);
        dialog.getWindow().setFlags(1024, 256);
        dialog.setContentView(0x7f0300b7);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final ViewPager viewPager = (ViewPager)dialog.findViewById(0x7f0a033f);
        final ImageView leftArrow = (ImageView)dialog.findViewById(0x7f0a0340);
        final ImageView rightArrow = (ImageView)dialog.findViewById(0x7f0a0341);
        final ArrayList indicatorList = new ArrayList(imageList.length);
        LinearLayout linearlayout = (LinearLayout)dialog.findViewById(0x7f0a0342);
        RelativeLayout relativelayout = (RelativeLayout)dialog.findViewById(0x7f0a0343);
        ((ImageView)dialog.findViewById(0x7f0a0346)).setOnClickListener(new android.view.View.OnClickListener() {

            final TopTutorialDialogFragment this$0;
            final boolean val$showReview;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onCloseClick(TopTutorialDialogFragment.this, showReview);
                }
            }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                showReview = flag;
                super();
            }
        });
        viewPager.setAdapter(new TopTutorialPagerAdapter(activity, imageList));
        viewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.SimpleOnPageChangeListener() {

            final TopTutorialDialogFragment this$0;
            final int val$imageList[];
            final ArrayList val$indicatorList;
            final ImageView val$leftArrow;
            final ImageView val$rightArrow;

            public void onPageSelected(int k)
            {
                super.onPageSelected(k);
                if (imageList.length > 1)
                {
                    int l;
                    if (k == 0)
                    {
                        leftArrow.setEnabled(false);
                    } else
                    {
                        leftArrow.setEnabled(true);
                    }
                    if (k == -1 + imageList.length)
                    {
                        rightArrow.setEnabled(false);
                    } else
                    {
                        rightArrow.setEnabled(true);
                    }
                    l = 0;
                    while (l < indicatorList.size()) 
                    {
                        ImageView imageview3 = (ImageView)indicatorList.get(l);
                        if (l == k)
                        {
                            imageview3.setSelected(true);
                        } else
                        {
                            imageview3.setSelected(false);
                        }
                        l++;
                    }
                }
            }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                imageList = ai;
                leftArrow = imageview;
                rightArrow = imageview1;
                indicatorList = arraylist;
                super();
            }
        });
        if (imageList.length <= 1)
        {
            leftArrow.setVisibility(8);
            rightArrow.setVisibility(8);
            linearlayout.setVisibility(8);
        } else
        {
            leftArrow.setEnabled(false);
            int i;
            int j;
            if (imageList.length <= 1)
            {
                rightArrow.setEnabled(false);
            } else
            {
                rightArrow.setEnabled(true);
            }
            leftArrow.setOnClickListener(new android.view.View.OnClickListener() {

                final TopTutorialDialogFragment this$0;
                final ViewPager val$viewPager;

                public void onClick(View view)
                {
                    viewPager.setCurrentItem(-1 + viewPager.getCurrentItem());
                }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                viewPager = viewpager;
                super();
            }
            });
            rightArrow.setOnClickListener(new android.view.View.OnClickListener() {

                final TopTutorialDialogFragment this$0;
                final ViewPager val$viewPager;

                public void onClick(View view)
                {
                    viewPager.setCurrentItem(1 + viewPager.getCurrentItem());
                }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                viewPager = viewpager;
                super();
            }
            });
            i = activity.getResources().getDimensionPixelSize(0x7f0b0035);
            j = 0;
            while (j < imageList.length) 
            {
                ImageView imageview = new ImageView(activity);
                imageview.setImageResource(0x7f0201de);
                imageview.setPadding(i, 0, i, 0);
                if (j == 0)
                {
                    imageview.setSelected(true);
                }
                linearlayout.addView(imageview);
                indicatorList.add(imageview);
                j++;
            }
        }
        if (!showReview)
        {
            relativelayout.setVisibility(8);
            dialog.findViewById(0x7f0a004d).setVisibility(0);
            return dialog;
        } else
        {
            ImageView imageview1 = (ImageView)relativelayout.findViewById(0x7f0a0344);
            ImageView imageview2 = (ImageView)relativelayout.findViewById(0x7f0a0345);
            imageview1.setOnClickListener(new android.view.View.OnClickListener() {

                final TopTutorialDialogFragment this$0;
                final boolean val$showReview;

                public void onClick(View view)
                {
                    if (listener != null)
                    {
                        listener.onCloseClick(TopTutorialDialogFragment.this, showReview);
                    }
                }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                showReview = flag;
                super();
            }
            });
            imageview2.setOnClickListener(new android.view.View.OnClickListener() {

                final TopTutorialDialogFragment this$0;
                final boolean val$showReview;

                public void onClick(View view)
                {
                    if (listener != null)
                    {
                        listener.onVoteClick(TopTutorialDialogFragment.this, showReview);
                    }
                }

            
            {
                this$0 = TopTutorialDialogFragment.this;
                showReview = flag;
                super();
            }
            });
            return dialog;
        }
    }

}
