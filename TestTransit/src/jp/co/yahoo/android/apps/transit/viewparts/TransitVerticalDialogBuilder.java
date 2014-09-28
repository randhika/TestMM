// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TransitDialogBuilder

public class TransitVerticalDialogBuilder extends TransitDialogBuilder
{

    private LinearLayout contents;
    private Context context;
    private AlertDialog dialog;

    public TransitVerticalDialogBuilder(Context context1)
    {
        super(context1);
        context = context1;
        contents = (LinearLayout)((LayoutInflater)context1.getSystemService("layout_inflater")).inflate(0x7f030057, null);
        setView(contents);
    }

    public AlertDialog create()
    {
        dialog = super.create();
        return dialog;
    }

    public volatile android.app.AlertDialog.Builder setMessage(CharSequence charsequence)
    {
        return setMessage(charsequence);
    }

    public TransitDialogBuilder setMessage(CharSequence charsequence)
    {
        ((TextView)contents.findViewById(0x7f0a01f4)).setText(charsequence);
        return this;
    }

    public android.app.AlertDialog.Builder setNegativeButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        return setNegativeButton(((CharSequence) (context.getString(i))), onclicklistener);
    }

    public android.app.AlertDialog.Builder setNegativeButton(CharSequence charsequence, final android.content.DialogInterface.OnClickListener listener)
    {
        Button button = (Button)contents.findViewById(0x7f0a01f1);
        button.setText(charsequence);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            final TransitVerticalDialogBuilder this$0;
            final android.content.DialogInterface.OnClickListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onClick(null, -2);
                }
                try
                {
                    dialog.dismiss();
                    return;
                }
                catch (Exception exception)
                {
                    return;
                }
            }

            
            {
                this$0 = TransitVerticalDialogBuilder.this;
                listener = onclicklistener;
                super();
            }
        });
        button.setVisibility(0);
        return this;
    }

    public android.app.AlertDialog.Builder setNeutralButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        return setNeutralButton(((CharSequence) (context.getString(i))), onclicklistener);
    }

    public android.app.AlertDialog.Builder setNeutralButton(CharSequence charsequence, final android.content.DialogInterface.OnClickListener listener)
    {
        Button button = (Button)contents.findViewById(0x7f0a01f5);
        button.setText(charsequence);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            final TransitVerticalDialogBuilder this$0;
            final android.content.DialogInterface.OnClickListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onClick(null, -3);
                }
                try
                {
                    dialog.dismiss();
                    return;
                }
                catch (Exception exception)
                {
                    return;
                }
            }

            
            {
                this$0 = TransitVerticalDialogBuilder.this;
                listener = onclicklistener;
                super();
            }
        });
        button.setVisibility(0);
        return this;
    }

    public android.app.AlertDialog.Builder setPositiveButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        return setPositiveButton(((CharSequence) (context.getString(i))), onclicklistener);
    }

    public android.app.AlertDialog.Builder setPositiveButton(CharSequence charsequence, final android.content.DialogInterface.OnClickListener listener)
    {
        Button button = (Button)contents.findViewById(0x7f0a01f0);
        button.setText(charsequence);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            final TransitVerticalDialogBuilder this$0;
            final android.content.DialogInterface.OnClickListener val$listener;

            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onClick(null, -1);
                }
                try
                {
                    dialog.dismiss();
                    return;
                }
                catch (Exception exception)
                {
                    return;
                }
            }

            
            {
                this$0 = TransitVerticalDialogBuilder.this;
                listener = onclicklistener;
                super();
            }
        });
        button.setVisibility(0);
        return this;
    }

    public AlertDialog show()
    {
        dialog = super.show();
        return dialog;
    }

}
