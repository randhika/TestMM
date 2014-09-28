// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.Transit;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

public class SearchDetailCondition extends LinearLayout
{

    private View condisionView;
    private ConditionData conditionData;
    private Context context;
    private Resources res;

    public SearchDetailCondition(Context context1)
    {
        super(context1);
    }

    private android.view.View.OnClickListener getOnClickListenerForBackCond(ConditionData conditiondata)
    {
        if (conditiondata == null)
        {
            new ConditionData();
        }
        final ConditionData cond = (ConditionData)conditiondata.clone();
        cond.afterFinal = false;
        cond.mtf = -1;
        android.view.View.OnClickListener onclicklistener;
        try
        {
            onclicklistener = new android.view.View.OnClickListener() {

                final SearchDetailCondition this$0;
                final ConditionData val$cond;

                public void onClick(View view)
                {
                    ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d03ce));
                    Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/Transit);
                    cond.mtf = -1;
                    if (cond.searchType >= 4)
                    {
                        cond.searchType = 1;
                    }
                    intent.putExtra(res.getString(0x7f0d0181), getResources().getInteger(0x7f0c0020));
                    intent.putExtra(res.getString(0x7f0d022c), cond);
                    ((TransitBaseActivity)context).startActivityInCurrentMenu(intent);
                }

            
            {
                this$0 = SearchDetailCondition.this;
                cond = conditiondata;
                super();
            }
            };
        }
        catch (Exception exception)
        {
            return null;
        }
        return onclicklistener;
    }

    public void searchNowButtonOff()
    {
        ((Button)condisionView.findViewById(0x7f0a02e3)).setVisibility(8);
    }

    public void setCondition(ConditionData conditiondata, Resources resources, Context context1)
    {
        conditionData = conditiondata;
        res = resources;
        context = context1;
        condisionView = LayoutInflater.from(context1).inflate(0x7f030099, null);
        Button button = (Button)condisionView.findViewById(0x7f0a02e4);
        android.view.View.OnClickListener onclicklistener = getOnClickListenerForBackCond(conditiondata);
        if (onclicklistener != null)
        {
            button.setOnClickListener(onclicklistener);
            button.setVisibility(0);
        }
        addView(condisionView, new android.widget.LinearLayout.LayoutParams(-1, -2));
    }


}
