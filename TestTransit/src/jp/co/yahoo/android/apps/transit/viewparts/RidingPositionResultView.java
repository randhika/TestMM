// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class RidingPositionResultView extends LinearLayout
{

    private Context RidingPositionContext;
    private String activeCarNumList[];
    private int dispWidth;
    private int lastOutflow;
    private Resources res;
    private ArrayList ridingPosition;
    private int ridingPositiondir;

    public RidingPositionResultView(Context context)
    {
        super(context);
        removeAllViews();
        RidingPositionContext = context;
        setOrientation(1);
    }

    private void delArrow(LinearLayout linearlayout)
    {
        ImageView imageview = (ImageView)linearlayout.findViewById(0x7f0a02be);
        ImageView imageview1 = (ImageView)linearlayout.findViewById(0x7f0a02bf);
        imageview.setVisibility(4);
        imageview1.setVisibility(4);
    }

    private android.view.View.OnClickListener getOnClickListenerForNext(final int page)
    {
        android.view.View.OnClickListener onclicklistener;
        try
        {
            onclicklistener = new android.view.View.OnClickListener() {

                final RidingPositionResultView this$0;
                final int val$page;

                public void onClick(View view)
                {
                    ((HorizontalScrollView)findViewById(0x7f0a002c)).smoothScrollTo(page * dispWidth, 0);
                }

            
            {
                this$0 = RidingPositionResultView.this;
                page = i;
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

    private android.view.View.OnClickListener getOnClickListenerForPrev(final int page)
    {
        android.view.View.OnClickListener onclicklistener;
        try
        {
            onclicklistener = new android.view.View.OnClickListener() {

                final RidingPositionResultView this$0;
                final int val$page;

                public void onClick(View view)
                {
                    ((HorizontalScrollView)findViewById(0x7f0a002c)).smoothScrollTo((-2 + page) * dispWidth, 0);
                }

            
            {
                this$0 = RidingPositionResultView.this;
                page = i;
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

    private void setArrowOnclick(LinearLayout linearlayout, int i)
    {
        android.view.View.OnClickListener onclicklistener;
        ImageView imageview;
        onclicklistener = getOnClickListenerForPrev(i);
        imageview = (ImageView)linearlayout.findViewById(0x7f0a02be);
        if (i != 1) goto _L2; else goto _L1
_L1:
        imageview.setVisibility(8);
_L8:
        ImageView imageview1 = (ImageView)linearlayout.findViewById(0x7f0a02bf);
        if (lastOutflow != i) goto _L4; else goto _L3
_L3:
        imageview1.setVisibility(8);
_L6:
        return;
_L2:
        if (onclicklistener != null)
        {
            imageview.setOnClickListener(onclicklistener);
            imageview.setVisibility(0);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        android.view.View.OnClickListener onclicklistener1 = getOnClickListenerForNext(i);
        if (onclicklistener1 == null) goto _L6; else goto _L5
_L5:
        imageview1.setOnClickListener(onclicklistener1);
        imageview1.setVisibility(0);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void setCarDetail(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar ridingpositioncar)
    {
        LayoutInflater layoutinflater = LayoutInflater.from(RidingPositionContext);
        HorizontalScrollView horizontalscrollview = new HorizontalScrollView(RidingPositionContext);
        horizontalscrollview.setId(0x7f0a002c);
        LinearLayout linearlayout = new LinearLayout(RidingPositionContext);
        ArrayList arraylist = ridingpositioncar.outflow;
        lastOutflow = arraylist.size();
        activeCarNumList = new String[lastOutflow];
        if (arraylist.size() == 1)
        {
            LinearLayout linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f030095, null);
            linearlayout.addView(linearlayout2);
            ((LinearLayout)linearlayout2.findViewById(0x7f0a02b4)).getLayoutParams().width = dispWidth;
            delArrow(linearlayout2);
            ((TextView)linearlayout2.findViewById(0x7f0a02b6)).setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(0)).carNo);
            setPictgram(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(0)).Means, linearlayout2);
        } else
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030095, null);
                linearlayout.addView(linearlayout1);
                ((LinearLayout)linearlayout1.findViewById(0x7f0a02b4)).getLayoutParams().width = dispWidth;
                setArrowOnclick(linearlayout1, i + 1);
                ((TextView)linearlayout1.findViewById(0x7f0a02b6)).setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(i)).carNo);
                activeCarNumList[i] = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(i)).carNo;
                setPictgram(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(i)).Means, linearlayout1);
                i++;
            }
        }
        horizontalscrollview.addView(linearlayout);
        horizontalscrollview.setOnTouchListener(new android.view.View.OnTouchListener() {

            final RidingPositionResultView this$0;

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return true;
            }

            
            {
                this$0 = RidingPositionResultView.this;
                super();
            }
        });
        addView(horizontalscrollview);
    }

    private void setCarImage(int i, String as[])
    {
        LinearLayout linearlayout = new LinearLayout(RidingPositionContext);
        linearlayout.setOrientation(0);
        linearlayout.setGravity(17);
        for (int j = 0; j < i; j++)
        {
            LinearLayout linearlayout1 = (LinearLayout)LayoutInflater.from(RidingPositionContext).inflate(0x7f030094, null);
            linearlayout.addView(linearlayout1);
            ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a02b2);
            imageview.setVisibility(0);
            TextView textview = (TextView)linearlayout1.findViewById(0x7f0a02b3);
            textview.setText(Integer.toString(j + 1));
            if (as[j + 1] == null)
            {
                textview.setTextColor(res.getColor(0x7f090013));
                imageview.setImageResource(0x7f020212);
            }
        }

        if (i > 8)
        {
            HorizontalScrollView horizontalscrollview = new HorizontalScrollView(RidingPositionContext);
            horizontalscrollview.addView(linearlayout);
            addView(horizontalscrollview);
            return;
        } else
        {
            addView(linearlayout);
            return;
        }
    }

    private void setDirection()
    {
        int i = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition)ridingPosition.get(ridingPositiondir)).isFrontFirstCar;
        LinearLayout linearlayout = new LinearLayout(RidingPositionContext);
        linearlayout.setOrientation(0);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        linearlayout.setPadding(5, 3, 5, 3);
        if (i == 1)
        {
            ImageView imageview = new ImageView(RidingPositionContext);
            imageview.setBackgroundDrawable(res.getDrawable(0x7f020214));
            linearlayout.addView(imageview);
            linearlayout.setGravity(19);
        }
        TextView textview = new TextView(RidingPositionContext);
        textview.setText(res.getString(0x7f0d029d));
        textview.setTextSize(18F);
        linearlayout.addView(textview);
        if (i == 0)
        {
            ImageView imageview1 = new ImageView(RidingPositionContext);
            imageview1.setBackgroundDrawable(res.getDrawable(0x7f020254));
            linearlayout.addView(imageview1);
            linearlayout.setGravity(21);
        }
        addView(linearlayout);
    }

    private void setPictgram(String s, LinearLayout linearlayout)
    {
        String as[];
        int i;
        as = s.split("/");
        i = 0;
_L10:
        if (i >= as.length)
        {
            break MISSING_BLOCK_LABEL_198;
        }
        Integer.parseInt(as[i]);
        JVM INSTR tableswitch 1 7: default 68
    //                   1 74
    //                   2 91
    //                   3 108
    //                   4 125
    //                   5 142
    //                   6 160
    //                   7 179;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_179;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L11:
        i++;
        if (true) goto _L10; else goto _L9
_L9:
        ((ImageView)linearlayout.findViewById(0x7f0a02b7)).setImageResource(0x7f020242);
          goto _L11
_L3:
        ((ImageView)linearlayout.findViewById(0x7f0a02b8)).setImageResource(0x7f020238);
          goto _L11
_L4:
        ((ImageView)linearlayout.findViewById(0x7f0a02b9)).setImageResource(0x7f020236);
          goto _L11
_L5:
        ((ImageView)linearlayout.findViewById(0x7f0a02ba)).setImageResource(0x7f02023c);
          goto _L11
_L6:
        ((ImageView)linearlayout.findViewById(0x7f0a02bb)).setImageResource(0x7f020240);
          goto _L11
_L7:
        ((ImageView)linearlayout.findViewById(0x7f0a02bc)).setImageResource(0x7f02023a);
          goto _L11
        ((ImageView)linearlayout.findViewById(0x7f0a02bd)).setImageResource(0x7f02023e);
          goto _L11
    }

    private void setSeparator()
    {
        LinearLayout linearlayout = new LinearLayout(RidingPositionContext);
        ImageView imageview = new ImageView(RidingPositionContext);
        imageview.setBackgroundColor(res.getColor(0x7f090036));
        linearlayout.addView(imageview, new android.widget.LinearLayout.LayoutParams(-1, 5));
        addView(linearlayout);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    public void setData(ArrayList arraylist, String s, int i, int j)
    {
        ridingPosition = arraylist;
        ridingPositiondir = i;
        dispWidth = j;
    }

    public void setTabView(int i)
    {
        res = RidingPositionContext.getResources();
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar ridingpositioncar = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar)((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition)ridingPosition.get(ridingPositiondir)).Cars.get(i);
        String s = ridingpositioncar.numOfCar;
        String as[] = new String[1 + Integer.parseInt(s)];
        ArrayList arraylist = ridingpositioncar.outflow;
        for (int j = 0; j < arraylist.size(); j++)
        {
            String s1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow)arraylist.get(j)).carNo;
            as[Integer.parseInt(s1)] = s1;
        }

        setCarImage(Integer.parseInt(s), as);
        setDirection();
        setSeparator();
        setCarDetail(ridingpositioncar);
    }

}
