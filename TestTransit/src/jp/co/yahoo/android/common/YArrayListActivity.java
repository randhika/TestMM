// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YArrayListActivity extends ListActivity
{

    public YArrayListActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.common_array_list_activity);
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
    }

    protected void setArray(String as[])
    {
        setListAdapter(new ArrayAdapter(this, 0x1090003, as));
    }
}
