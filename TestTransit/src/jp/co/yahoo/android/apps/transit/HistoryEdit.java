// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.db.History;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class HistoryEdit extends TransitBaseActivity
{

    private Button btnDelete;
    private CheckListView chkList;
    private LinearLayout listRegist;
    private History objHistory;

    public HistoryEdit()
    {
    }

    private void deleteHistory()
    {
        ArrayList arraylist = chkList.getCheckItems();
        if (arraylist == null || arraylist.size() < 1)
        {
            return;
        }
        Object obj;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); objHistory.deleteHistory(((StationData)obj).getName()))
        {
            obj = iterator.next();
        }

        showHistory();
    }

    private void launchBackl()
    {
        setResult(-1, new Intent());
        finish();
    }

    protected void delClickListener()
    {
        deleteHistory();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030029);
        setTitle(getString(0x7f0d0317));
        chkList = new CheckListView(this);
        listRegist = (LinearLayout)findViewById(0x7f0a012f);
        objHistory = new History(this);
        btnDelete = (Button)findViewById(0x7f0a00ee);
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {

            final HistoryEdit this$0;

            public void onClick(View view)
            {
                if (chkList.getCheckItems().size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d012d), getString(0x7f0d0150));
                    return;
                } else
                {
                    showdelMessageDialog(getString(0x7f0d00cc));
                    return;
                }
            }

            
            {
                this$0 = HistoryEdit.this;
                super();
            }
        });
        showHistory();
        dispAd(this, "2080288754", "pv");
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            launchBackl();
        }
        return super.onKeyDown(i, keyevent);
    }

    protected void showHistory()
    {
        ArrayList arraylist;
        List list;
        Iterator iterator;
        try
        {
            listRegist.removeAllViews();
            list = objHistory.getHistory(0);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        if (list.size() >= 1)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        ((TextView)findViewById(0x7f0a012e)).setVisibility(0);
        return;
        arraylist = new ArrayList();
        for (iterator = list.iterator(); iterator.hasNext(); arraylist.add((StationData)iterator.next())) { }
        chkList = new CheckListView(this);
        chkList.setListItems(arraylist);
        listRegist.addView(chkList);
        chkList.showView();
        return;
    }

}
