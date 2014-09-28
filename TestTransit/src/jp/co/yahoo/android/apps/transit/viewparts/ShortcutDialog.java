// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TransitDialogBuilder

public class ShortcutDialog extends LinearLayout
{

    private AlertDialog dialog;
    private LayoutInflater inflater;
    private String sTitle;

    public ShortcutDialog(Context context)
    {
        super(context);
        sTitle = null;
        setOrientation(1);
        inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
        addView((LinearLayout)inflater.inflate(0x7f0300a7, null));
    }

    public void close()
    {
        dialog.cancel();
    }

    public void setCondition(Bundle bundle)
    {
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
        sTitle = s;
    }

    public void showDialog()
    {
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(getContext());
        if (sTitle != null)
        {
            transitdialogbuilder.setTitle(sTitle);
        }
        dialog = transitdialogbuilder.setView(this).show();
    }
}
