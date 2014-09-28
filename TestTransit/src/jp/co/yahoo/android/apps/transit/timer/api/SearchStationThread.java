// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.api:
//            StationSearch

public class SearchStationThread extends Thread
{

    private Bundle condition;
    private Context context;
    private Handler mainHandler;

    public SearchStationThread(Context context1, Handler handler)
    {
        context = context1;
        mainHandler = handler;
    }

    public String getString(int i)
    {
        return context.getString(i);
    }

    public void run()
    {
        StationSearch stationsearch = new StationSearch(context);
        Bundle bundle;
        Bundle bundle1;
        Message message;
        if (condition.containsKey(getString(0x7f0d01a3)) && condition.containsKey(getString(0x7f0d01a3)))
        {
            stationsearch.setLat(condition.getString(getString(0x7f0d01a3)));
            stationsearch.setLon(condition.getString(getString(0x7f0d01a4)));
        } else
        if (condition.containsKey(getString(0x7f0d0241)))
        {
            stationsearch.setQuery(condition.getString(getString(0x7f0d0241)));
        }
        if (condition.containsKey(getString(0x7f0d023b)))
        {
            stationsearch.setStart(Integer.parseInt(condition.getString(getString(0x7f0d023b))));
        } else
        {
            stationsearch.setStart(1);
        }
        if (condition.containsKey(getString(0x7f0d01e4)))
        {
            stationsearch.setResultCount(Integer.parseInt(condition.getString(getString(0x7f0d01e4))));
        } else
        {
            stationsearch.setResultCount(10);
        }
        if (condition.containsKey(getString(0x7f0d018e)))
        {
            stationsearch.setSort(condition.getString(getString(0x7f0d018e)));
        } else
        {
            stationsearch.setSort("dist");
        }
        stationsearch.execute();
        bundle = (Bundle)stationsearch.getResult();
        bundle1 = new Bundle();
        message = mainHandler.obtainMessage();
        message.obj = bundle1;
        bundle1.putString(getString(0x7f0d01cf), getString(0x7f0d01d1));
        if (bundle == null || bundle.size() < 1)
        {
            bundle1.putInt(getString(0x7f0d01ce), 0x7f0c0077);
            bundle1.putString(getString(0x7f0d01b9), getString(0x7f0d010a));
        } else
        {
            bundle1.putBundle(getString(0x7f0d0240), bundle);
            bundle1.putInt(getString(0x7f0d01ce), 0x7f0c0078);
        }
        mainHandler.sendMessage(message);
    }

    public void setCondtion(Bundle bundle)
    {
        condition = bundle;
    }
}
