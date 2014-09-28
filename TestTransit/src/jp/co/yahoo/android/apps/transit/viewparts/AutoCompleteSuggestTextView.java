// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.AssistSearch;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class AutoCompleteSuggestTextView extends AutoCompleteTextView
{
    class MyOnKeyListener
        implements android.view.View.OnKeyListener
    {

        final AutoCompleteSuggestTextView this$0;

        public boolean onKey(View view, int i, KeyEvent keyevent)
        {
            AutoCompleteSuggestTextView autocompletesuggesttextview = (AutoCompleteSuggestTextView)view;
            if (keyevent.getAction() == 0 && i == 66)
            {
                try
                {
                    if ((0x10 & keyevent.getFlags()) != 0 && imm != null)
                    {
                        autocompletesuggesttextview.dismissDropDown();
                        imm.hideSoftInputFromWindow(autocompletesuggesttextview.getWindowToken(), 0);
                    }
                }
                catch (Exception exception)
                {
                    return false;
                }
            }
            return false;
        }

        MyOnKeyListener()
        {
            this$0 = AutoCompleteSuggestTextView.this;
            super();
        }
    }

    class MyOnTextWatcher
        implements TextWatcher
    {

        final AutoCompleteSuggestTextView this$0;

        public void afterTextChanged(Editable editable)
        {
            if (suggestListener == null)
            {
                return;
            }
            if (editable == null || editable.toString().equals(""))
            {
                suggestListener.onNoinput();
                return;
            } else
            {
                suggestListener.onInputed(editable.toString());
                return;
            }
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        MyOnTextWatcher()
        {
            this$0 = AutoCompleteSuggestTextView.this;
            super();
        }
    }

    public class SuggestAdapter extends BaseAdapter
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

                final SuggestAdapter this$1;

                protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
                {
                    if (!bEnableSuggest.booleanValue())
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
                        suggestListener.onNoMatch(nMode);
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
                this$1 = SuggestAdapter.this;
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
        static ArrayList access$202(SuggestAdapter suggestadapter, ArrayList arraylist)
        {
            suggestadapter.items = arraylist;
            return arraylist;
        }

*/

        public SuggestAdapter(Context context1)
        {
            this$0 = AutoCompleteSuggestTextView.this;
            super();
            items = new ArrayList();
            context = context1;
        }
    }

    public static interface SuggestListener
    {

        public abstract void onInputed(String s);

        public abstract void onNoMatch(int i);

        public abstract void onNoinput();

        public abstract void onSuggestSuccess(int i, List list);
    }


    public static final int SUGGEST_MODE_BUSSTOP = 2;
    public static final int SUGGEST_MODE_MIX = 3;
    public static final int SUGGEST_MODE_NO = 4;
    public static final int SUGGEST_MODE_STATION = 1;
    private SuggestAdapter adapter;
    private Boolean bEnableSuggest;
    private InputMethodManager imm;
    private int nMode;
    protected String sOldQuery;
    public SuggestListener suggestListener;

    public AutoCompleteSuggestTextView(Context context)
    {
        super(context);
        suggestListener = null;
        nMode = 1;
        bEnableSuggest = Boolean.valueOf(true);
        sOldQuery = null;
        init(context);
    }

    public AutoCompleteSuggestTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        suggestListener = null;
        nMode = 1;
        bEnableSuggest = Boolean.valueOf(true);
        sOldQuery = null;
        init(context);
    }

    public AutoCompleteSuggestTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        suggestListener = null;
        nMode = 1;
        bEnableSuggest = Boolean.valueOf(true);
        sOldQuery = null;
        init(context);
    }

    public void addSuggestListener(SuggestListener suggestlistener)
    {
        suggestListener = suggestlistener;
    }

    public void enableSuggest(boolean flag)
    {
        sOldQuery = null;
        bEnableSuggest = Boolean.valueOf(flag);
    }

    public SuggestAdapter getSuggestAdapter()
    {
        return adapter;
    }

    protected void init(Context context)
    {
        adapter = new SuggestAdapter(context);
        imm = (InputMethodManager)getContext().getSystemService("input_method");
        setSingleLine();
        setThreshold(1);
        setOnKeyListener(new MyOnKeyListener());
        addTextChangedListener(new MyOnTextWatcher());
        setAdapter(adapter);
    }

    public void postSuggest()
    {
        setText(getText().toString());
    }

    protected void replaceText(CharSequence charsequence)
    {
        Selection.removeSelection(getText());
        setText(charsequence);
        Editable editable = getText();
        Selection.setSelection(editable, editable.length());
    }

    public void setMode(int i)
    {
        nMode = i;
    }

    public void setStart()
    {
        sOldQuery = null;
        setAdapter(adapter);
    }

    public void setStop()
    {
        sOldQuery = null;
        setAdapter((SuggestAdapter)null);
    }

    public void showDropDown()
    {
        if (suggestListener != null)
        {
            suggestListener.onSuggestSuccess(nMode, adapter.getItems());
            return;
        } else
        {
            super.showDropDown();
            return;
        }
    }



}
