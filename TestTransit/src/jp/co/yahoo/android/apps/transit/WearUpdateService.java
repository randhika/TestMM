// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownManager;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultActivity

public class WearUpdateService extends WearableListenerService
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    private final String COUNTDOWN_GET = "/countdown/get";
    private final String COUNTDOWN_NORMAL_RESULT = "/countdown/normal/result";
    private final String COUNTDOWN_OPEN = "/countdown/open";
    private final String COUNTDOWN_REVERSE_RESULT = "/countdown/reverse/result";
    private final String ROUTE_MEMO_DETAIL_GET = "/route_memo/detail/get";
    private final String ROUTE_MEMO_DETAIL_OPEN = "/route_memo/detail/open";
    private final String ROUTE_MEMO_DETAIL_RESULT = "/route_memo/detail/result";
    private final String ROUTE_MEMO_LIST_GET = "/route_memo/list/get";
    private final String ROUTE_MEMO_LIST_RESULT = "/route_memo/list/result";
    private final String TAG = "transit";
    private GoogleApiClient mGoogleApiClient;

    public WearUpdateService()
    {
    }

    private static Asset createAssetFromBytes(byte abyte0[])
    {
        return Asset.createFromBytes(abyte0);
    }

    private void openCountdown(MessageEvent messageevent)
    {
        Bundle bundle = TransitUtil.byte2bundle(messageevent.getData());
        Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        if (bundle.containsKey("type"))
        {
            intent.putExtra(getString(0x7f0d0247), bundle.getInt("type"));
        }
        if (bundle.containsKey("week"))
        {
            intent.putExtra(getString(0x7f0d024c), bundle.getInt("week"));
        }
        if (bundle.containsKey("start_time"))
        {
            intent.putExtra(getString(0x7f0d023c), bundle.getString("start_time"));
        }
        intent.putExtra(getString(0x7f0d01bf), true);
        intent.addFlags(0x14000000);
        startActivity(intent);
    }

    private void openRoutememo(MessageEvent messageevent)
    {
        String s = TransitUtil.byte2bundle(messageevent.getData()).getString("/route_memo/detail/open");
        if (TransitUtil.isEmpty(s))
        {
            return;
        }
        ResultDB resultdb = new ResultDB(this);
        Bundle bundle = resultdb.getSearchResultMemo(s);
        ConditionData conditiondata = null;
        NaviSearchData navisearchdata = null;
        if (bundle != null)
        {
            int i = bundle.size();
            conditiondata = null;
            navisearchdata = null;
            if (i > 0)
            {
                navisearchdata = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
                conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
            }
        }
        if (s != null)
        {
            resultdb.updateSearchMemoRefer(s);
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/SearchResultActivity);
        intent.addFlags(0x14000000);
        intent.putExtra(getString(0x7f0d0231), 0);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        intent.putExtra(getString(0x7f0d01bd), true);
        intent.putExtra(getString(0x7f0d01bf), true);
        startActivity(intent);
    }

    private void sendCountdown(MessageEvent messageevent)
    {
        int i = -1;
        StationData stationdata = null;
        Bundle bundle = null;
        int j = Integer.parseInt(new String(messageevent.getData()));
        final String path;
        final StationData station;
        boolean flag;
        if (j == getResources().getInteger(0x7f0c0074) || j == getResources().getInteger(0x7f0c0073))
        {
            path = "/countdown/reverse/result";
        } else
        {
            path = "/countdown/normal/result";
            j = CountdownUtil.getCountdownType(this);
        }
        if (j == -1)
        {
            j = getResources().getInteger(0x7f0c0074);
        } else
        {
            stationdata = (new SettingDb(this)).getTimetable(j);
        }
        if (stationdata == null)
        {
            stationdata = new StationData();
            stationdata.setSettingType(j);
        } else
        {
            bundle = stationdata.getTimetable();
        }
        station = stationdata;
        flag = false;
        if (bundle != null)
        {
            final CountdownManager countdown = new CountdownManager(this);
            countdown.setListener(new jp.co.yahoo.android.apps.transit.timer.common.CountdownManager.CountdownListener() {

                final WearUpdateService this$0;
                final CountdownManager val$countdown;
                final String val$path;
                final StationData val$station;

                public boolean changeWeek(int k)
                {
                    return false;
                }

                public boolean end(TimeTableItemData timetableitemdata)
                {
                    return false;
                }

                public boolean onAllFiltered()
                {
                    Bundle bundle1 = new Bundle();
                    sendCountdown(path, countdown.getWeek(), countdown.getIsTomorrow(), bundle1, station);
                    return false;
                }

                public boolean onNoTimetable()
                {
                    sendCountdown(path, countdown.getWeek(), countdown.getIsTomorrow(), null, station);
                    return false;
                }

                public void updateTarget(TimeTableItemData timetableitemdata)
                {
                }

                public void updateTime(int k)
                {
                }

            
            {
                this$0 = WearUpdateService.this;
                path = s;
                countdown = countdownmanager;
                station = stationdata;
                super();
            }
            });
            countdown.setWeek(-1);
            countdown.setCountDown(bundle);
            i = countdown.getWeek();
            flag = countdown.getIsTomorrow();
            bundle = countdown.getNowTimetables();
        }
        sendCountdown(path, i, flag, bundle, stationdata);
    }

    private void sendCountdown(String s, int i, boolean flag, Bundle bundle, StationData stationdata)
    {
        PutDataMapRequest putdatamaprequest = PutDataMapRequest.create(s);
        DataMap datamap = putdatamaprequest.getDataMap();
        if (bundle != null)
        {
            datamap.putAsset("timetable", createAssetFromBytes(TransitUtil.bundle2byte(bundle)));
        }
        datamap.putInt("week", i);
        datamap.putBoolean("tomorrow", flag);
        stationdata.setTimetable(null);
        datamap.putByteArray("station", TransitUtil.obj2byte(stationdata));
        com.google.android.gms.wearable.PutDataRequest putdatarequest = putdatamaprequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(mGoogleApiClient, putdatarequest).setResultCallback(new ResultCallback() {

            final WearUpdateService this$0;

            public volatile void onResult(Result result)
            {
                onResult((com.google.android.gms.wearable.DataApi.DataItemResult)result);
            }

            public void onResult(com.google.android.gms.wearable.DataApi.DataItemResult dataitemresult)
            {
            }

            
            {
                this$0 = WearUpdateService.this;
                super();
            }
        });
    }

    public void onConnected(Bundle bundle)
    {
    }

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
    }

    public void onConnectionSuspended(int i)
    {
    }

    public void onCreate()
    {
        super.onCreate();
        mGoogleApiClient = (new com.google.android.gms.common.api.GoogleApiClient.Builder(this)).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Wearable.API).build();
        mGoogleApiClient.connect();
    }

    public void onDataChanged(DataEventBuffer dataeventbuffer)
    {
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }

    public void onMessageReceived(MessageEvent messageevent)
    {
        String s;
        String s1;
        String s2;
        s = (new StringBuilder()).append(getString(0x7f0d0560)).append(getString(0x7f0d0456)).toString();
        s1 = getString(0x7f0d0561);
        s2 = messageevent.getPath();
        if (!s2.equals("/route_memo/list/get")) goto _L2; else goto _L1
_L1:
        sendMemoSummary();
        s = (new StringBuilder()).append(s).append(getString(0x7f0d0457)).append(getString(0x7f0d045a)).toString();
_L4:
        TransitUtil.touchRD((new StringBuilder()).append(s).append("/").append(s1).toString());
        return;
_L2:
        if (s2.equals("/route_memo/detail/get"))
        {
            sendMemo(new String(messageevent.getData()));
            s = (new StringBuilder()).append(s).append(getString(0x7f0d0457)).append(getString(0x7f0d0459)).toString();
        } else
        if (s2.equals("/countdown/get"))
        {
            sendCountdown(messageevent);
            s = (new StringBuilder()).append(s).append(getString(0x7f0d045b)).append(getString(0x7f0d045d)).toString();
        } else
        if (s2.equals("/countdown/open"))
        {
            openCountdown(messageevent);
            s = (new StringBuilder()).append(s).append(getString(0x7f0d045b)).append(getString(0x7f0d045c)).toString();
        } else
        if (s2.equals("/route_memo/detail/open"))
        {
            openRoutememo(messageevent);
            s = (new StringBuilder()).append(s).append(getString(0x7f0d0457)).append(getString(0x7f0d045c)).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void sendMemo(String s)
    {
        PutDataMapRequest putdatamaprequest = PutDataMapRequest.create("/route_memo/detail/result");
        DataMap datamap = putdatamaprequest.getDataMap();
        Bundle bundle = (new ResultDB(this)).getSearchResultMemo(s);
        NaviSearchData navisearchdata = null;
        if (bundle != null)
        {
            int i = bundle.size();
            navisearchdata = null;
            if (i > 0)
            {
                navisearchdata = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
            }
        }
        datamap.putAsset("/route_memo/detail/result", createAssetFromBytes(TransitUtil.obj2byte(navisearchdata)));
        com.google.android.gms.wearable.PutDataRequest putdatarequest = putdatamaprequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(mGoogleApiClient, putdatarequest).setResultCallback(new ResultCallback() {

            final WearUpdateService this$0;

            public volatile void onResult(Result result)
            {
                onResult((com.google.android.gms.wearable.DataApi.DataItemResult)result);
            }

            public void onResult(com.google.android.gms.wearable.DataApi.DataItemResult dataitemresult)
            {
            }

            
            {
                this$0 = WearUpdateService.this;
                super();
            }
        });
    }

    protected void sendMemoSummary()
    {
        PutDataMapRequest putdatamaprequest = PutDataMapRequest.create("/route_memo/list/result");
        DataMap datamap = putdatamaprequest.getDataMap();
        ArrayList arraylist = (ArrayList)(new ResultDB(this)).getSearchMenoCondition();
        Bundle bundle = new Bundle();
        for (int i = 0; i < arraylist.size(); i++)
        {
            bundle.putBundle(Integer.toString(i), (Bundle)arraylist.get(i));
        }

        datamap.putAsset("/route_memo/list/result", createAssetFromBytes(TransitUtil.bundle2byte(bundle)));
        com.google.android.gms.wearable.PutDataRequest putdatarequest = putdatamaprequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(mGoogleApiClient, putdatarequest).setResultCallback(new ResultCallback() {

            final WearUpdateService this$0;

            public volatile void onResult(Result result)
            {
                onResult((com.google.android.gms.wearable.DataApi.DataItemResult)result);
            }

            public void onResult(com.google.android.gms.wearable.DataApi.DataItemResult dataitemresult)
            {
            }

            
            {
                this$0 = WearUpdateService.this;
                super();
            }
        });
    }

}
