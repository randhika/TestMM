// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class CheckListView extends LinearLayout
{
    public static interface OnItemCheckedChangeListener
    {

        public abstract void onCheckedChanged(CompoundButton compoundbutton, boolean flag);
    }


    private LayoutInflater inflater;
    private ArrayList listChecks;
    private ArrayList listItems;
    private OnItemCheckedChangeListener listener;

    public CheckListView(Context context)
    {
        super(context);
        setOrientation(1);
        inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
    }

    public ArrayList getAllItems()
    {
        return listItems;
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
            if (((CheckBox)linearlayout.findViewById(0x7f0a0216)).isChecked())
            {
                arraylist.add(linearlayout.getTag());
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
            if (!((CheckBox)linearlayout.findViewById(0x7f0a0216)).isChecked())
            {
                arraylist.add(linearlayout.getTag());
            }
        }

        return arraylist;
    }

    public void setListChecks(ArrayList arraylist)
    {
        listChecks = arraylist;
    }

    public void setListItems(ArrayList arraylist)
    {
        listItems = arraylist;
    }

    public void setOnCheckedChangeListener(OnItemCheckedChangeListener onitemcheckedchangelistener)
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
                Object obj = iterator.next();
                LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f030067, null);
                TextView textview = (TextView)linearlayout.findViewById(0x7f0a020f);
                CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f0a0216);
                if (listChecks != null && listChecks.size() > i && listChecks.get(i) != null)
                {
                    checkbox.setChecked(((Boolean)listChecks.get(i)).booleanValue());
                }
                checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

                    final CheckListView this$0;

                    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
                    {
                        if (listener != null)
                        {
                            listener.onCheckedChanged(compoundbutton, flag);
                        }
                    }

            
            {
                this$0 = CheckListView.this;
                super();
            }
                });
                linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                    final CheckListView this$0;

                    public void onClick(View view)
                    {
                        ((CheckBox)view.findViewById(0x7f0a0216)).toggle();
                    }

            
            {
                this$0 = CheckListView.this;
                super();
            }
                });
                textview.setText(obj.toString());
                linearlayout.setTag(obj);
                linearlayout.setId(i);
                addView(linearlayout);
                addView((ImageView)inflater.inflate(0x7f030059, null));
                i++;
            }
        }
    }

}
