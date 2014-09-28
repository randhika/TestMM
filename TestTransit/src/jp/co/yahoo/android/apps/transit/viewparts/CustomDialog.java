// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TransitDialogBuilder

public class CustomDialog extends LinearLayout
{

    private AlertDialog dialog;
    private LayoutInflater inflater;

    public CustomDialog(Context context)
    {
        super(context);
        setOrientation(1);
        inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
        addView((LinearLayout)inflater.inflate(0x7f030051, null));
    }

    public void close()
    {
        dialog.cancel();
    }

    public void setContents(String s)
    {
        TextView textview = (TextView)findViewById(0x7f0a01ee);
        textview.setText(s);
        textview.setVisibility(0);
    }

    public void setNegativeBtn(String s, android.view.View.OnClickListener onclicklistener)
    {
        Button button = (Button)findViewById(0x7f0a01f1);
        button.setText(s);
        button.setOnClickListener(onclicklistener);
        button.setVisibility(0);
    }

    public void setPositiveBtn(String s, android.view.View.OnClickListener onclicklistener)
    {
        Button button = (Button)findViewById(0x7f0a01f0);
        button.setText(s);
        button.setOnClickListener(onclicklistener);
        button.setVisibility(0);
    }

    public void setTitle(String s)
    {
        ((TextView)findViewById(0x7f0a01ed)).setText(s);
    }

    public void showDialog()
    {
        dialog = (new TransitDialogBuilder(getContext())).setView(this).show();
    }
}
