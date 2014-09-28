// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class AlarmCheckListView extends LinearLayout
{
    public static interface OnItemCheckedChangeListener
    {

        public abstract void onCheckedChanged(CompoundButton compoundbutton, boolean flag);
    }


    private ArrayList enables;
    private LayoutInflater inflater;
    private ArrayList listItems;
    private OnItemCheckedChangeListener listener;
    private ArrayList textLabels;
    private ArrayList typeLabels;

    public AlarmCheckListView(Context context)
    {
        super(context);
        setOrientation(1);
        inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
    }

    public ArrayList getCheckItems()
    {
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < getChildCount(); i++)
        {
            if (!(getChildAt(i) instanceof LinearLayout))
            {
                continue;
            }
            LinearLayout linearlayout = (LinearLayout)getChildAt(i);
            CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f0a0216);
            if (checkbox != null && checkbox.isChecked())
            {
                arraylist.add((Integer)linearlayout.getTag());
            }
        }

        return arraylist;
    }

    public ArrayList getNoCheckItems()
    {
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < getChildCount(); i++)
        {
            if (!(getChildAt(i) instanceof LinearLayout))
            {
                continue;
            }
            LinearLayout linearlayout = (LinearLayout)getChildAt(i);
            CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f0a0216);
            if (checkbox != null && !checkbox.isChecked())
            {
                arraylist.add((Integer)linearlayout.getTag());
            }
        }

        return arraylist;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (flag && typeLabels != null)
        {
            Paint paint = null;
            int i1 = 0;
            int j1 = 0;
            while (j1 < getChildCount()) 
            {
                if (getChildAt(j1) instanceof LinearLayout)
                {
                    TextView textview = (TextView)((LinearLayout)getChildAt(j1)).findViewById(0x7f0a020f);
                    if (textview != null && typeLabels.get(i1) != null)
                    {
                        String s = (String)typeLabels.get(i1);
                        String s1 = textview.getText().toString();
                        if (!s1.contains(s))
                        {
                            int k1 = textview.getWidth();
                            if (paint == null)
                            {
                                float f1 = textview.getTextSize();
                                paint = new Paint();
                                paint.setTextSize(f1);
                                paint.setSubpixelText(true);
                            }
                            float f = paint.measureText(s);
                            String s2 = TransitUtil.getEllipsisString(s1, (int)((float)k1 - f), paint.getTextSize());
                            textview.setText((new StringBuilder()).append(s2).append(s).toString());
                        }
                        i1++;
                    }
                }
                j1++;
            }
        }
    }

    public void setListItems(ArrayList arraylist)
    {
        listItems = arraylist;
    }

    public void setListLabels(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2)
    {
        textLabels = arraylist;
        typeLabels = arraylist1;
        enables = arraylist2;
    }

    public void setOnItemClickChangeListener(OnItemCheckedChangeListener onitemcheckedchangelistener)
    {
        listener = onitemcheckedchangelistener;
    }

    public void showView()
    {
        if (listItems != null)
        {
            int i = 0;
            Iterator iterator = listItems.iterator();
            while (iterator.hasNext()) 
            {
                Integer integer = (Integer)iterator.next();
                LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f030069, null);
                TextView textview = (TextView)linearlayout.findViewById(0x7f0a020f);
                CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f0a0216);
                checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

                    final AlarmCheckListView this$0;

                    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
                    {
                        if (listener != null)
                        {
                            listener.onCheckedChanged(compoundbutton, flag);
                        }
                    }

            
            {
                this$0 = AlarmCheckListView.this;
                super();
            }
                });
                linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                    final AlarmCheckListView this$0;

                    public void onClick(View view)
                    {
                        ((CheckBox)view.findViewById(0x7f0a0216)).toggle();
                    }

            
            {
                this$0 = AlarmCheckListView.this;
                super();
            }
                });
                android.widget.LinearLayout.LayoutParams layoutparams;
                LinearLayout linearlayout1;
                if (textLabels != null && textLabels.get(i) != null)
                {
                    textview.setText((CharSequence)textLabels.get(i));
                } else
                {
                    textview.setText(String.valueOf(integer));
                }
                if (enables != null && enables.get(i) != null)
                {
                    textview.setEnabled(((Boolean)enables.get(i)).booleanValue());
                    checkbox.setEnabled(((Boolean)enables.get(i)).booleanValue());
                    linearlayout.setEnabled(((Boolean)enables.get(i)).booleanValue());
                }
                linearlayout.setTag(integer);
                linearlayout.setId(i);
                addView(linearlayout);
                layoutparams = new android.widget.LinearLayout.LayoutParams(-1, 1);
                linearlayout1 = new LinearLayout(getContext());
                linearlayout1.setBackgroundColor(getContext().getResources().getColor(0x7f090036));
                linearlayout1.setLayoutParams(layoutparams);
                addView(linearlayout1);
                i++;
            }
        }
    }

}
