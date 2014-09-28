// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDisplayUtils

public abstract class YDialogBaseActivity extends Activity
{
    private class YDialogBtnCancelListener
        implements android.view.View.OnClickListener
    {

        final YDialogBaseActivity this$0;

        public void onClick(View view)
        {
            if (onCancelButtonClick())
            {
                setResult(0);
                finish();
            }
        }

        private YDialogBtnCancelListener()
        {
            this$0 = YDialogBaseActivity.this;
            super();
        }

    }

    private class YDialogBtnOkListener
        implements android.view.View.OnClickListener
    {

        final YDialogBaseActivity this$0;

        public void onClick(View view)
        {
            if (onOkButtonClick())
            {
                finish();
            }
        }

        private YDialogBtnOkListener()
        {
            this$0 = YDialogBaseActivity.this;
            super();
        }

    }


    private static final String TAG = jp/co/yahoo/android/common/YDialogBaseActivity.getSimpleName();

    public YDialogBaseActivity()
    {
    }

    protected boolean onCancelButtonClick()
    {
        return true;
    }

    protected abstract boolean onOkButtonClick();

    public void setContentView(int i)
    {
        setContentView(getLayoutInflater().inflate(i, null));
    }

    public void setContentView(View view)
    {
        View view1 = getLayoutInflater().inflate(R.layout.common_dialog_base_activity, null);
        ((ViewGroup)view1.findViewById(R.id.layout_frame)).addView(view);
        super.setContentView(view1);
        findViewById(R.id.btn_ok).setOnClickListener(new YDialogBtnOkListener());
        findViewById(R.id.btn_cancel).setOnClickListener(new YDialogBtnCancelListener());
    }

    protected void setDialogWidth(int i)
    {
        View view = findViewById(R.id.btn_ok);
        View view1 = findViewById(R.id.btn_cancel);
        YDisplayUtils ydisplayutils = YDisplayUtils.getInstance(this);
        float f = (float)(view.getLayoutParams().width + view1.getLayoutParams().width) + 2.0F * ydisplayutils.dp2px(4F);
        if ((float)i < 1.1F * f)
        {
            i = (int)f;
        }
        ViewGroup viewgroup = (ViewGroup)findViewById(R.id.frame_root);
        if (viewgroup == null)
        {
            return;
        } else
        {
            viewgroup.getLayoutParams().width = i;
            return;
        }
    }

    public void setSubTitle(CharSequence charsequence)
    {
        TextView textview = (TextView)findViewById(R.id.text_subtitle);
        textview.setText(charsequence);
        textview.setVisibility(0);
    }

    public void setTitle(CharSequence charsequence)
    {
        TextView textview = (TextView)findViewById(R.id.text_title);
        textview.setText(charsequence);
        textview.setVisibility(0);
    }

    public void setTitle2(CharSequence charsequence)
    {
        TextView textview = (TextView)findViewById(R.id.text_title2);
        textview.setText(charsequence);
        textview.setVisibility(0);
    }

    public void setTitleIcon(int i)
    {
        ImageView imageview = (ImageView)findViewById(R.id.img_icon);
        imageview.setImageResource(i);
        imageview.setVisibility(0);
    }

    protected void showCancelButton(boolean flag)
    {
        Button button = (Button)findViewById(R.id.btn_cancel);
        if (flag)
        {
            button.setVisibility(0);
            return;
        } else
        {
            button.setVisibility(8);
            return;
        }
    }

    protected void showFooterBorderLine(boolean flag)
    {
        View view = findViewById(R.id.dialog_footer_boder);
        if (flag)
        {
            view.setVisibility(0);
            return;
        } else
        {
            view.setVisibility(8);
            return;
        }
    }

    protected void showHeaderBorderLine(boolean flag)
    {
        View view = findViewById(R.id.diaolg_header_boder);
        if (flag)
        {
            view.setVisibility(0);
            return;
        } else
        {
            view.setVisibility(8);
            return;
        }
    }

    protected void showOkButton(boolean flag)
    {
        Button button = (Button)findViewById(R.id.btn_ok);
        if (button == null)
        {
            return;
        }
        if (flag)
        {
            button.setVisibility(0);
            return;
        } else
        {
            button.setVisibility(8);
            return;
        }
    }

}
