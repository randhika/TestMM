// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.agreementlib;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YJAgreementDialog extends Dialog
{

    private Button mButtonNegative;
    private Button mButtonPositive;
    private LayoutInflater mInflater;
    private LinearLayout mLayoutBottom;
    private FrameLayout mLayoutCustom;
    private LinearLayout mLayoutPermission;
    private LinearLayout mLayoutTitle;
    private TextView mTextPrivacyPolicy;

    public YJAgreementDialog(Activity activity)
    {
        super(activity, R.style.YJCommonAgreementDialogTheme);
        mLayoutTitle = null;
        mLayoutCustom = null;
        mLayoutBottom = null;
        mLayoutPermission = null;
        mTextPrivacyPolicy = null;
        mButtonPositive = null;
        mButtonNegative = null;
        mInflater = null;
        setContentView(R.layout.yjcommon_agreement_dialog);
        mLayoutTitle = (LinearLayout)findViewById(R.id.LayoutTitle);
        mLayoutCustom = (FrameLayout)findViewById(R.id.LayoutCustom);
        mLayoutBottom = (LinearLayout)findViewById(R.id.LayoutBottom);
        mLayoutPermission = (LinearLayout)findViewById(R.id.LayoutPermission);
        mTextPrivacyPolicy = (TextView)findViewById(R.id.TextPrivacyPolicy);
        mButtonPositive = (Button)findViewById(R.id.ButtonPositive);
        mButtonNegative = (Button)findViewById(R.id.ButtonNegative);
        mInflater = activity.getLayoutInflater();
        setCanceledOnTouchOutside(false);
    }

    private void setLayoutButtonSpace()
    {
        if (mButtonPositive.getVisibility() == 0 && mButtonNegative.getVisibility() == 0)
        {
            findViewById(R.id.LayoutLeftSpacer).setVisibility(8);
            findViewById(R.id.LayoutRightSpacer).setVisibility(8);
            return;
        } else
        {
            findViewById(R.id.LayoutLeftSpacer).setVisibility(0);
            findViewById(R.id.LayoutRightSpacer).setVisibility(0);
            return;
        }
    }

    public void addPermission(String s, String s1)
    {
        View view = mInflater.inflate(R.layout.yjcommon_agreement_permission_row, null);
        TextView textview = (TextView)view.findViewById(R.id.TextPermissionLabel);
        TextView textview1 = (TextView)view.findViewById(R.id.TextPermissionDescription);
        textview.setText(s);
        textview1.setText(s1);
        mLayoutPermission.addView(view);
    }

    public Button getNegativeButton()
    {
        return mButtonNegative;
    }

    public Button getPositiveButton()
    {
        return mButtonPositive;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    public void setIcon(int i)
    {
        ImageView imageview = (ImageView)findViewById(R.id.ImageIcon);
        imageview.setImageResource(i);
        imageview.setVisibility(0);
        mLayoutTitle.setVisibility(0);
    }

    public void setIcon(Bitmap bitmap)
    {
        ImageView imageview = (ImageView)findViewById(R.id.ImageIcon);
        imageview.setImageBitmap(bitmap);
        imageview.setVisibility(0);
        mLayoutTitle.setVisibility(0);
    }

    public void setNegativeButton(int i, android.view.View.OnClickListener onclicklistener)
    {
        setNegativeButton(getContext().getResources().getString(i), onclicklistener);
    }

    public void setNegativeButton(String s, final android.view.View.OnClickListener listener)
    {
        mButtonNegative = (Button)findViewById(R.id.ButtonNegative);
        mButtonNegative.setText(s);
        mButtonNegative.setVisibility(0);
        mLayoutBottom.setVisibility(0);
        setLayoutButtonSpace();
        mButtonNegative.setOnClickListener(new android.view.View.OnClickListener() {

            final YJAgreementDialog this$0;
            final android.view.View.OnClickListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onClick(mButtonNegative);
                }
                cancel();
            }

            
            {
                this$0 = YJAgreementDialog.this;
                listener = onclicklistener;
                super();
            }
        });
    }

    public void setPositiveButton(int i, android.view.View.OnClickListener onclicklistener)
    {
        setPositiveButton(getContext().getResources().getString(i), onclicklistener);
    }

    public void setPositiveButton(String s, final android.view.View.OnClickListener listener)
    {
        mButtonPositive = (Button)findViewById(R.id.ButtonPositive);
        mButtonPositive.setText(s);
        mButtonPositive.setVisibility(0);
        mLayoutBottom.setVisibility(0);
        setLayoutButtonSpace();
        mButtonPositive.setOnClickListener(new android.view.View.OnClickListener() {

            final YJAgreementDialog this$0;
            final android.view.View.OnClickListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onClick(mButtonPositive);
                }
                cancel();
            }

            
            {
                this$0 = YJAgreementDialog.this;
                listener = onclicklistener;
                super();
            }
        });
    }

    public void setPrivacyPolicyLongClickListener(android.view.View.OnLongClickListener onlongclicklistener)
    {
        mTextPrivacyPolicy.setOnLongClickListener(onlongclicklistener);
    }

    public void setPrivacyPolicyText(String s)
    {
        mTextPrivacyPolicy.setText(s);
    }

    public void setTitle(int i)
    {
        setTitle(getContext().getResources().getString(i));
    }

    public void setTitle(String s)
    {
        ((TextView)findViewById(R.id.TextTitle)).setText(s);
        mLayoutTitle.setVisibility(0);
    }

    public void setView(View view)
    {
        FrameLayout framelayout = (FrameLayout)findViewById(R.id.LayoutView);
        findViewById(R.id.ViewExtraSpace).setVisibility(8);
        framelayout.addView(view);
        mLayoutCustom.setVisibility(0);
    }


}
