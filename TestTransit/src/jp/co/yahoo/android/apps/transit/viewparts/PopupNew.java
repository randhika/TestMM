// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class PopupNew
{
    public static interface PopupListener
    {

        public abstract void onClick();
    }


    private Activity mActivity;
    private View mParent;
    private PopupWindow mPopupWindow;

    public PopupNew(Activity activity, View view)
    {
        mActivity = activity;
        mParent = view;
    }

    public static boolean isShow(Context context, String s, int i)
    {
        if (i != -1 && i != TransitUtil.getVersionCode(context))
        {
            return false;
        } else
        {
            return context.getSharedPreferences(context.getString(0x7f0d04df), 0).getBoolean(s, true);
        }
    }

    public static void setShowed(Context context, String s, int i)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(0x7f0d04df), 0).edit();
        editor.putBoolean(s, false);
        editor.commit();
    }

    public void dismiss()
    {
        if (mPopupWindow != null && mPopupWindow.isShowing())
        {
            mPopupWindow.dismiss();
        }
    }

    public void display(int i, int j, int k, String s)
    {
        display(i, j, k, s, 0, true);
    }

    public void display(int i, int j, int k, String s, int l, boolean flag)
    {
        display(i, j, k, s, l, flag, null);
    }

    public void display(int i, int j, int k, String s, int l, boolean flag, final PopupListener listener)
    {
        if (isShow(mActivity, s, k))
        {
            Object obj;
            android.content.SharedPreferences.Editor editor;
            if (l != 0)
            {
                ImageView imageview = new ImageView(mActivity);
                imageview.setImageResource(l);
                obj = imageview;
            } else
            {
                obj = (LinearLayout)mActivity.getLayoutInflater().inflate(0x7f03008c, null);
            }
            ((View) (obj)).setOnClickListener(new android.view.View.OnClickListener() {

                final PopupNew this$0;
                final PopupListener val$listener;

                public void onClick(View view)
                {
                    dismiss();
                    if (listener != null)
                    {
                        listener.onClick();
                    }
                }

            
            {
                this$0 = PopupNew.this;
                listener = popuplistener;
                super();
            }
            });
            mPopupWindow = new PopupWindow(mActivity);
            mPopupWindow.setWindowLayoutMode(-2, -2);
            mPopupWindow.setContentView(((View) (obj)));
            mPopupWindow.setBackgroundDrawable(mActivity.getResources().getDrawable(0x7f02026b));
            mPopupWindow.setOutsideTouchable(flag);
            mPopupWindow.showAsDropDown(mParent, i, j);
            editor = mActivity.getSharedPreferences(mActivity.getString(0x7f0d04df), 0).edit();
            editor.putBoolean(s, false);
            editor.commit();
        }
    }
}
