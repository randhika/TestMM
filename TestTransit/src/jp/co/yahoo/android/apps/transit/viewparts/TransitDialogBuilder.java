// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            CustomDialogTitle

public class TransitDialogBuilder extends android.app.AlertDialog.Builder
{

    private Context context;

    public TransitDialogBuilder(Context context1)
    {
        super(context1);
        context = context1;
    }

    public volatile android.app.AlertDialog.Builder setMessage(CharSequence charsequence)
    {
        return setMessage(charsequence);
    }

    public TransitDialogBuilder setMessage(CharSequence charsequence)
    {
        View view = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030056, null);
        ((TextView)view.findViewById(0x7f0a01f4)).setText(charsequence);
        setView(view);
        return this;
    }

    public volatile android.app.AlertDialog.Builder setTitle(CharSequence charsequence)
    {
        return setTitle(charsequence);
    }

    public TransitDialogBuilder setTitle(CharSequence charsequence)
    {
        super.setCustomTitle(new CustomDialogTitle(context, charsequence.toString(), 0));
        return this;
    }

    public TransitDialogBuilder setTitleIcon(String s, int i)
    {
        super.setCustomTitle(new CustomDialogTitle(context, s, i));
        return this;
    }

    public TransitDialogBuilder setTitleInfo(String s)
    {
        CustomDialogTitle customdialogtitle = new CustomDialogTitle(context, s, 0);
        customdialogtitle.setIconInfo();
        super.setCustomTitle(customdialogtitle);
        return this;
    }

    public TransitDialogBuilder setTitleWarnning(String s)
    {
        CustomDialogTitle customdialogtitle = new CustomDialogTitle(context, s, 0);
        customdialogtitle.setIconWarning();
        super.setCustomTitle(customdialogtitle);
        return this;
    }
}
