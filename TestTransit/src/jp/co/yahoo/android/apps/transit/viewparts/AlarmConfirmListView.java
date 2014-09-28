// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class AlarmConfirmListView extends LinearLayout
{
    public static interface OnItemCheckedChangeListener
    {

        public abstract void onCheckedChanged(CompoundButton compoundbutton, boolean flag);
    }


    private LayoutInflater inflater;
    private ArrayList lengthAlarmLabels;
    private ArrayList listItems;
    private OnItemCheckedChangeListener listener;
    private ArrayList numRouteLabels;
    private ArrayList textAlarmLabels;
    private ArrayList textRouteLabels;
    private ArrayList timeAlarmLabels;
    private ArrayList timeRouteLabels;

    public AlarmConfirmListView(Context context)
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
                arraylist.add((Bundle)linearlayout.getTag());
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
                arraylist.add((Bundle)linearlayout.getTag());
            }
        }

        return arraylist;
    }

    public void setListItems(ArrayList arraylist)
    {
        listItems = arraylist;
    }

    public void setListLabels(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, ArrayList arraylist3, ArrayList arraylist4, ArrayList arraylist5)
    {
        numRouteLabels = arraylist;
        textRouteLabels = arraylist1;
        timeRouteLabels = arraylist2;
        textAlarmLabels = arraylist3;
        timeAlarmLabels = arraylist4;
        lengthAlarmLabels = arraylist5;
    }

    public void setOnItemClickChangeListener(OnItemCheckedChangeListener onitemcheckedchangelistener)
    {
        listener = onitemcheckedchangelistener;
    }

    public void showView()
    {
        if (listItems == null)
        {
            return;
        }
        int i = 0;
        for (Iterator iterator = listItems.iterator(); iterator.hasNext();)
        {
            Bundle bundle = (Bundle)iterator.next();
            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, 1);
            LinearLayout linearlayout1 = new LinearLayout(getContext());
            linearlayout1.setBackgroundColor(getContext().getResources().getColor(0x7f090036));
            linearlayout1.setLayoutParams(layoutparams1);
            addView(linearlayout1);
            LinearLayout linearlayout2 = (LinearLayout)inflater.inflate(0x7f030064, null);
            TextView textview = (TextView)linearlayout2.findViewById(0x7f0a020e);
            TextView textview1 = (TextView)linearlayout2.findViewById(0x7f0a020f);
            TextView textview2 = (TextView)linearlayout2.findViewById(0x7f0a0210);
            ArrayList arraylist;
            if (numRouteLabels != null && numRouteLabels.get(i) != null)
            {
                textview.setText((CharSequence)numRouteLabels.get(i));
            } else
            {
                textview.setVisibility(8);
            }
            if (textRouteLabels != null && textRouteLabels.get(i) != null)
            {
                textview1.setText((CharSequence)textRouteLabels.get(i));
            } else
            {
                textview1.setVisibility(8);
            }
            if (timeRouteLabels != null && timeRouteLabels.get(i) != null)
            {
                textview2.setText((CharSequence)timeRouteLabels.get(i));
            } else
            {
                textview2.setVisibility(8);
            }
            linearlayout2.setId(i);
            addView(linearlayout2);
            arraylist = bundle.getParcelableArrayList(getContext().getString(0x7f0d0234));
            if (arraylist == null)
            {
                i++;
            } else
            {
                int j = 0;
                Iterator iterator1 = arraylist.iterator();
                while (iterator1.hasNext()) 
                {
                    Bundle bundle1 = (Bundle)iterator1.next();
                    android.widget.LinearLayout.LayoutParams layoutparams2 = new android.widget.LinearLayout.LayoutParams(-1, 1);
                    LinearLayout linearlayout3 = new LinearLayout(getContext());
                    linearlayout3.setBackgroundColor(getContext().getResources().getColor(0x7f090036));
                    linearlayout3.setLayoutParams(layoutparams2);
                    addView(linearlayout3);
                    LinearLayout linearlayout4 = (LinearLayout)inflater.inflate(0x7f030068, null);
                    TextView textview3 = (TextView)linearlayout4.findViewById(0x7f0a020f);
                    TextView textview4 = (TextView)linearlayout4.findViewById(0x7f0a0210);
                    TextView textview5 = (TextView)linearlayout4.findViewById(0x7f0a0217);
                    CheckBox checkbox = (CheckBox)linearlayout4.findViewById(0x7f0a0216);
                    android.widget.CompoundButton.OnCheckedChangeListener oncheckedchangelistener = new android.widget.CompoundButton.OnCheckedChangeListener() {

                        final AlarmConfirmListView this$0;

                        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
                        {
                            if (listener != null)
                            {
                                listener.onCheckedChanged(compoundbutton, flag);
                            }
                        }

            
            {
                this$0 = AlarmConfirmListView.this;
                super();
            }
                    };
                    checkbox.setOnCheckedChangeListener(oncheckedchangelistener);
                    android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                        final AlarmConfirmListView this$0;

                        public void onClick(View view)
                        {
                            ((CheckBox)view.findViewById(0x7f0a0216)).toggle();
                        }

            
            {
                this$0 = AlarmConfirmListView.this;
                super();
            }
                    };
                    linearlayout4.setOnClickListener(onclicklistener);
                    if (textAlarmLabels != null && textAlarmLabels.get(i) != null && ((ArrayList)textAlarmLabels.get(i)).get(j) != null)
                    {
                        textview3.setText((CharSequence)((ArrayList)textAlarmLabels.get(i)).get(j));
                    } else
                    {
                        textview3.setVisibility(8);
                    }
                    if (timeAlarmLabels != null && timeAlarmLabels.get(i) != null && ((ArrayList)timeAlarmLabels.get(i)).get(j) != null)
                    {
                        textview4.setText((CharSequence)((ArrayList)timeAlarmLabels.get(i)).get(j));
                    } else
                    {
                        textview4.setVisibility(8);
                    }
                    if (lengthAlarmLabels != null && lengthAlarmLabels.get(i) != null && ((ArrayList)lengthAlarmLabels.get(i)).get(j) != null)
                    {
                        textview5.setText((CharSequence)((ArrayList)lengthAlarmLabels.get(i)).get(j));
                    } else
                    {
                        textview5.setVisibility(8);
                    }
                    linearlayout4.setTag(bundle1);
                    linearlayout4.setId(i);
                    addView(linearlayout4);
                    j++;
                }
                i++;
            }
        }

        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, 1);
        LinearLayout linearlayout = new LinearLayout(getContext());
        linearlayout.setBackgroundColor(getContext().getResources().getColor(0x7f090036));
        linearlayout.setLayoutParams(layoutparams);
        addView(linearlayout);
    }

}
