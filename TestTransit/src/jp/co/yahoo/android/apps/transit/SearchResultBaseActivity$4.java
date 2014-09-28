// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.db.ResultDB;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultBaseActivity

class val.sql
    implements android.content.ener
{

    final SearchResultBaseActivity this$0;
    final ResultDB val$sql;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == 0)
        {
            SearchResultBaseActivity.access$400(SearchResultBaseActivity.this, val$sql, 1, 0);
            return;
        } else
        {
            SearchResultBaseActivity.access$500(SearchResultBaseActivity.this);
            return;
        }
    }

    A()
    {
        this$0 = final_searchresultbaseactivity;
        val$sql = ResultDB.this;
        super();
    }
}
