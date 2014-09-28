// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.AssistSearch;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AutoCompleteSuggestTextView

public class context extends BaseAdapter
    implements Filterable
{

    private Context context;
    private ArrayList items;
    final AutoCompleteSuggestTextView this$0;

    public void clear()
    {
        items.clear();
    }

    protected View createView(int i)
    {
        return LayoutInflater.from(context).inflate(0x7f03007e, null);
    }

    public int getCount()
    {
        int i;
        try
        {
            i = items.size();
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i;
    }

    public Filter getFilter()
    {
        return new Filter() {

            final AutoCompleteSuggestTextView.SuggestAdapter this$1;

            protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
            {
                if (!AutoCompleteSuggestTextView.access$100(this$0).booleanValue())
                {
                    return null;
                }
                if (charsequence != null)
                {
                    break MISSING_BLOCK_LABEL_57;
                }
                android.widget.Filter.FilterResults filterresults;
                try
                {
                    charsequence = getText().toString();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    return null;
                }
                if (charsequence == null)
                {
                    break MISSING_BLOCK_LABEL_251;
                }
                if (charsequence.toString().equals(""))
                {
                    break MISSING_BLOCK_LABEL_251;
                }
                if (!TransitUtil.isEmpty(sOldQuery) && sOldQuery.equals(charsequence.toString()))
                {
                    return null;
                }
                sOldQuery = charsequence.toString();
                filterresults = new android.widget.Filter.FilterResults();
                items = new ArrayList();
                if (2 != getResources().getConfiguration().orientation)
                {
                    break MISSING_BLOCK_LABEL_190;
                }
_L1:
                filterresults.values = items;
                filterresults.count = items.size();
                return filterresults;
                AssistSearch assistsearch = new AssistSearch(getContext());
                assistsearch.setResults(30);
                assistsearch.setQ(charsequence.toString());
                assistsearch.requestSingleThread();
                items = (ArrayList)assistsearch.getSuggest();
                  goto _L1
                return null;
            }

            protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
            {
                if (filterresults != null);
                if (filterresults.count > 0 && hasFocus())
                {
                    notifyDataSetChanged();
                    return;
                }
                try
                {
                    suggestListener.onNoMatch(AutoCompleteSuggestTextView.access$300(this$0));
                    notifyDataSetInvalidated();
                    return;
                }
                catch (Exception exception)
                {
                    notifyDataSetInvalidated();
                }
                return;
            }

            
            {
                this$1 = AutoCompleteSuggestTextView.SuggestAdapter.this;
                super();
            }
        };
    }

    public Object getItem(int i)
    {
        if (items.size() == 0)
        {
            return null;
        }
        Object obj;
        try
        {
            obj = items.get(i);
        }
        catch (Exception exception)
        {
            return null;
        }
        return obj;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public List getItems()
    {
        return items;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view != null)
        {
            break MISSING_BLOCK_LABEL_10;
        }
        view = createView(i);
        refreshView(i, view);
        return view;
        Exception exception;
        exception;
        return view;
    }

    protected void refreshView(int i, View view)
    {
        ((TextView)view.findViewById(0x1020014)).setText((CharSequence)getItem(i));
    }



/*
    static ArrayList access$202(_cls1 _pcls1, ArrayList arraylist)
    {
        _pcls1.items = arraylist;
        return arraylist;
    }

*/

    public _cls1.this._cls1(Context context1)
    {
        this$0 = AutoCompleteSuggestTextView.this;
        super();
        items = new ArrayList();
        context = context1;
    }
}
