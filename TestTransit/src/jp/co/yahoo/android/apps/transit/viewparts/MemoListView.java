// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class MemoListView extends LinearLayout
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{
    public static interface OnItemClickChangeListener
    {

        public abstract void onClicked(Bundle bundle);

        public abstract void onLongClicked(Bundle bundle);
    }


    private final int TYPE_NORMAL = 0;
    private LayoutInflater inflater;
    private ArrayList listItems;
    private ArrayList listViews;
    private OnItemClickChangeListener listener;
    private int type;

    public MemoListView(Context context)
    {
        super(context);
        type = 0;
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
            if (((CheckBox)linearlayout.findViewById(0x7f0a0216)).isChecked())
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
            if (!((CheckBox)linearlayout.findViewById(0x7f0a0216)).isChecked())
            {
                arraylist.add((Bundle)linearlayout.getTag());
            }
        }

        return arraylist;
    }

    public void onClick(View view)
    {
        if (type == 0)
        {
            if (listener != null)
            {
                listener.onClicked((Bundle)view.getTag());
            }
            return;
        } else
        {
            ((CheckBox)view.findViewById(0x7f0a0216)).toggle();
            return;
        }
    }

    public boolean onLongClick(View view)
    {
        if (listener != null)
        {
            listener.onLongClicked((Bundle)view.getTag());
            return true;
        } else
        {
            return false;
        }
    }

    public void setOnItemClickChangeListener(OnItemClickChangeListener onitemclickchangelistener)
    {
        listener = onitemclickchangelistener;
    }

    public void setType(int i)
    {
        type = i;
        Iterator iterator = listViews.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            View view = (View)iterator.next();
            CheckBox checkbox = (CheckBox)view.findViewById(0x7f0a0216);
            ImageView imageview;
            if (checkbox != null)
            {
                if (i == 0)
                {
                    if (checkbox.getVisibility() != 4)
                    {
                        checkbox.setVisibility(4);
                    }
                } else
                if (checkbox.getVisibility() != 0)
                {
                    checkbox.setVisibility(0);
                }
            }
            imageview = (ImageView)view.findViewById(0x7f0a021c);
            if (imageview != null)
            {
                if (i == 0)
                {
                    if (imageview.getVisibility() != 0)
                    {
                        imageview.setVisibility(0);
                    }
                } else
                if (imageview.getVisibility() != 8)
                {
                    imageview.setVisibility(8);
                }
            }
        } while (true);
    }

    public void showView(ArrayList arraylist, ArrayList arraylist1, boolean flag, int i)
    {
        if (arraylist != null && arraylist1 != null)
        {
            listItems = arraylist;
            listViews = arraylist1;
            type = i;
            int j = 0;
            Iterator iterator = listItems.iterator();
            while (iterator.hasNext()) 
            {
                Bundle bundle = (Bundle)iterator.next();
                View view = (View)listViews.get(j);
                view.setOnClickListener(this);
                if (flag)
                {
                    view.setOnLongClickListener(this);
                }
                view.setTag(bundle);
                view.setId(j);
                CheckBox checkbox = (CheckBox)view.findViewById(0x7f0a0216);
                ImageView imageview = (ImageView)view.findViewById(0x7f0a021c);
                if (i == 0)
                {
                    checkbox.setVisibility(4);
                    if (imageview != null)
                    {
                        imageview.setVisibility(0);
                    }
                } else
                {
                    checkbox.setVisibility(0);
                    if (imageview != null)
                    {
                        imageview.setVisibility(8);
                    }
                }
                addView(view);
                addView((ImageView)inflater.inflate(0x7f030059, null));
                j++;
            }
        }
    }
}
