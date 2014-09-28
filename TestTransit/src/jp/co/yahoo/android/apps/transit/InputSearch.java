// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.StationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.History;
import jp.co.yahoo.android.apps.transit.timer.RailDirectionActivity;
import jp.co.yahoo.android.apps.transit.timer.StationListActivity;
import jp.co.yahoo.android.apps.transit.viewparts.AutoCompleteSuggestTextView;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersEditStationActivity, SearchResultListActivity

public class InputSearch extends TransitBaseActivity
{

    private int BTN_MODE_ARROWND;
    private int BTN_MODE_HISTORY;
    private int BTN_MODE_REGIST;
    private int BTN_MODE_SUGGEST;
    private ScrollView aroundList;
    private boolean bCurrent;
    private boolean bForceSuggest;
    private boolean bShowBtn;
    private Button btnAround;
    private Button btnDelete;
    private Button btnHistory;
    private Button btnRegist;
    private android.view.View.OnClickListener cllistener;
    private ConditionData condition;
    private ScrollView historyList;
    private InputMethodManager imm;
    private LayoutInflater inflater;
    private Button inputFinish;
    private AutoCompleteSuggestTextView inputSuggest;
    private Intent intent;
    private LinearLayout lnBtnArea;
    private LocationSearch locAddress;
    private LocationSearch locStation;
    private LinearLayout lsArownd;
    private LinearLayout lsHistory;
    private LinearLayout lsRegist;
    private LinearLayout lsSuggest;
    private int nBtnMode;
    private int nListMode;
    private int nReqCode;
    private int nSearchType;
    private StationData objAddress;
    private StationData objResult;
    private StationSearch objSearch;
    private Bundle objStations;
    private RegistSearchSSO regist;
    private ScrollView registList;
    private Bundle registinfo;
    private String sSpaceId;
    private String sSpaceIdBus;
    private String sSpaceIdSuggest;
    private String sTarget;
    private LinearLayout suggestList;
    private ScrollView suggestListItems;
    private BearerToken taken;

    public InputSearch()
    {
        BTN_MODE_SUGGEST = 1;
        BTN_MODE_HISTORY = 2;
        BTN_MODE_REGIST = 3;
        BTN_MODE_ARROWND = 4;
        nBtnMode = -1;
        sSpaceIdSuggest = null;
        nReqCode = 0;
        imm = null;
        bForceSuggest = false;
        objStations = null;
        objAddress = null;
        objSearch = null;
        locAddress = null;
        locStation = null;
    }

    private void addSuggestView(List list, String s, int i)
    {
        RelativeLayout relativelayout = (RelativeLayout)inflater.inflate(0x7f03005c, null);
        ((TextView)relativelayout.findViewById(0x7f0a01fc)).setText(s);
        relativelayout.setId(i);
        lsSuggest.addView(relativelayout);
        if (list != null && list.size() > 0)
        {
            int j = 0;
            Iterator iterator = list.iterator();
            do
            {
                StationData stationdata;
label0:
                {
                    if (iterator.hasNext())
                    {
                        stationdata = (StationData)iterator.next();
                        if (stationdata.getType() != i)
                        {
                            continue;
                        }
                        if (j <= 9)
                        {
                            break label0;
                        }
                    }
                    if (j == 0)
                    {
                        lsSuggest.addView(getNoSuggest());
                    }
                    return;
                }
                j++;
                LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f030083, null);
                ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                ((TextView)linearlayout.findViewById(0x7f0a0063)).setText(stationdata.getName());
                ((TextView)linearlayout.findViewById(0x7f0a0281)).setText(stationdata.getAddress());
                linearlayout.setOnClickListener(cllistener);
                linearlayout.setTag(stationdata);
                lsSuggest.addView(linearlayout);
                lsSuggest.addView(imageview);
            } while (true);
        } else
        {
            lsSuggest.addView(getNoSuggest());
            return;
        }
    }

    private void dispatchPage(int i)
    {
        Resources resources = getResources();
        if (i == resources.getInteger(0x7f0c0067))
        {
            inputFinish.setText(getString(0x7f0d050e));
            sSpaceId = "2080124750";
        } else
        if (i == resources.getInteger(0x7f0c0064))
        {
            sSpaceId = "2080124759";
        } else
        if (i == resources.getInteger(0x7f0c0066))
        {
            sSpaceId = "2080124763";
        } else
        if (i == resources.getInteger(0x7f0c0056))
        {
            sSpaceId = "2080124755";
        } else
        if (i == resources.getInteger(0x7f0c005f))
        {
            sSpaceId = "2080325030";
            sSpaceIdSuggest = "2080325629";
        } else
        {
            inputFinish.setText(getString(0x7f0d050e));
            sSpaceId = "2080124750";
        }
        sSpaceIdBus = "2080124749";
    }

    private StationData getAddressData(String s)
    {
        SetSharedPreferences setsharedpreferences = new SetSharedPreferences(this, s);
        StationData stationdata = new StationData();
        stationdata.setName(setsharedpreferences.getStringSharedPreferenceData("address"));
        stationdata.setAddress(setsharedpreferences.getStringSharedPreferenceData("address"));
        stationdata.setLat(setsharedpreferences.getStringSharedPreferenceData("lat"));
        stationdata.setLon(setsharedpreferences.getStringSharedPreferenceData("lon"));
        stationdata.setnNaviType(128);
        return stationdata;
    }

    private TextView getNoSuggest()
    {
        TextView textview = (TextView)inflater.inflate(0x7f030074, null);
        textview.setText(getString(0x7f0d012f));
        return textview;
    }

    private void getStationId(String s)
    {
        objSearch = new StationSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final InputSearch this$0;

            public boolean onCanceled()
            {
                launchReturn();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                launchReturn();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objSearch.getResults();
                if (bundle != null && bundle.containsKey("0"))
                {
                    StationData stationdata = (StationData)bundle.getSerializable("0");
                    objResult.setId(stationdata.getId());
                    objResult.setLat(stationdata.getLat());
                    objResult.setLon(stationdata.getLon());
                }
                launchReturn();
                return false;
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        });
        objSearch.setLocoMode("false");
        if (s.equals("\u4F59\u90E8"))
        {
            objSearch.setSort("-int_custom02");
        } else
        {
            objSearch.setSort("hybrid+-int_custom02");
        }
        objSearch.setExactName(s);
        objSearch.setCount(1);
        objSearch.setDialogDisplay(false);
        objSearch.request();
    }

    private void launchCancel()
    {
        Intent intent1 = new Intent();
        imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
        if (condition != null)
        {
            intent1.putExtra(getString(0x7f0d022c), condition);
        }
        setResult(0, intent1);
        finish();
    }

    private void launchListForTimer(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d0241), s);
        bundle.putString(getString(0x7f0d018e), "hybrid+-int_custom02");
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/StationListActivity);
        intent1.putExtra(getString(0x7f0d0244), nReqCode);
        intent1.putExtra(getString(0x7f0d0247), Integer.parseInt(sTarget));
        intent1.putExtra(getString(0x7f0d022c), bundle);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c0062));
    }

    private void launchRailForTimer()
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/RailDirectionActivity);
        intent1.putExtra(getString(0x7f0d0244), nReqCode);
        intent1.putExtra(getString(0x7f0d0247), Integer.parseInt(sTarget));
        intent1.putExtra(getString(0x7f0d023e), parseStationData());
        startActivityForResult(intent1, getResources().getInteger(0x7f0c0053));
    }

    private void launchResult()
    {
        if (objResult == null)
        {
            return;
        } else
        {
            (new LocationSearch(this, new jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener() {

                final InputSearch this$0;

                public void onError(String s, String s1)
                {
                    showErrorMessageDialog(s1, getString(0x7f0d014f));
                }

                public void onSuccess(String s, Bundle bundle)
                {
                    ConditionData conditiondata = (new SaveCondition(InputSearch.this)).getCond();
                    if (conditiondata == null)
                    {
                        conditiondata = new ConditionData();
                    }
                    conditiondata.startName = bundle.getString(getString(0x7f0d01a2));
                    conditiondata.startLat = bundle.getString(getString(0x7f0d01a3));
                    conditiondata.startLon = bundle.getString(getString(0x7f0d01a4));
                    if (objResult.getId() != null)
                    {
                        conditiondata.goalCode = objResult.getId();
                    }
                    conditiondata.goalName = objResult.getName();
                    conditiondata.afterFinal = false;
                    Calendar calendar = Calendar.getInstance();
                    conditiondata.year = calendar.get(1);
                    conditiondata.month = 1 + calendar.get(2);
                    conditiondata.day = calendar.get(5);
                    conditiondata.hour = calendar.get(11);
                    conditiondata.minite = calendar.get(12);
                    launchSearchResult(conditiondata);
                }

                public void onTimeout(String s, String s1)
                {
                    showErrorMessageDialog(s1, getString(0x7f0d014f));
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            })).getCurrentAddress();
            return;
        }
    }

    private void launchReturn()
    {
        Intent intent1 = new Intent();
        String s = inputSuggest.getText().toString();
        imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
        if (nReqCode == getResources().getInteger(0x7f0c005f))
        {
            if (objResult != null && objResult.getName().equals(s))
            {
                launchRailForTimer();
                return;
            }
            String s2 = TransitUtil.trim(s);
            if (!TransitUtil.isEmpty(s2))
            {
                launchListForTimer(s2);
                return;
            } else
            {
                launchCancel();
                return;
            }
        }
        intent1.putExtra(getString(0x7f0d0243), sTarget);
        if (objResult != null && objResult.getName().equals(s))
        {
            intent1.putExtra(getString(0x7f0d023e), objResult);
            intent1.putExtra(getString(0x7f0d024a), false);
        } else
        {
            String s1 = TransitUtil.trim(s);
            if (!TransitUtil.isEmpty(s1))
            {
                objResult = new StationData();
                objResult.setName(s1);
                intent1.putExtra(getString(0x7f0d023e), objResult);
                intent1.putExtra(getString(0x7f0d024a), true);
            }
        }
        intent1.putExtra(getString(0x7f0d022c), condition);
        if (bCurrent)
        {
            launchResult();
            return;
        } else
        {
            setResult(-1, intent1);
            finish();
            return;
        }
    }

    private jp.co.yahoo.android.apps.transit.timer.api.data.StationData parseStationData()
    {
        jp.co.yahoo.android.apps.transit.timer.api.data.StationData stationdata = new jp.co.yahoo.android.apps.transit.timer.api.data.StationData();
        stationdata.setStationId(objResult.getId());
        stationdata.setName(objResult.getName());
        return stationdata;
    }

    private void setBtnStatus(int i)
    {
        if (nBtnMode != i)
        {
            nBtnMode = i;
            if (i == BTN_MODE_SUGGEST)
            {
                if (bShowBtn)
                {
                    lnBtnArea.setVisibility(8);
                }
                if (!TransitUtil.isEmpty(sSpaceIdSuggest))
                {
                    dispAd(this, sSpaceIdSuggest, "pv");
                    return;
                } else
                {
                    dispAd(this, sSpaceId, "pv");
                    return;
                }
            }
            if (bShowBtn)
            {
                lnBtnArea.setVisibility(0);
            }
            dispAd(this, sSpaceId, "pv");
            if (i == BTN_MODE_ARROWND)
            {
                btnHistory.setSelected(false);
                btnRegist.setSelected(false);
                btnAround.setSelected(true);
            } else
            if (i == BTN_MODE_HISTORY)
            {
                btnHistory.setSelected(true);
                btnRegist.setSelected(false);
                btnAround.setSelected(false);
            } else
            {
                btnHistory.setSelected(false);
                btnRegist.setSelected(true);
                btnAround.setSelected(false);
            }
            if (TransitUtil.isEmpty(inputSuggest.getEditableText().toString()))
            {
                return;
            }
        }
    }

    private void setResult(StationData stationdata)
    {
        objResult = stationdata;
        setText(objResult.getName());
    }

    private void setResultHear()
    {
        objResult = null;
        setText(getString(0x7f0d0289));
    }

    private void setText(String s)
    {
        inputSuggest.setStop();
        inputSuggest.setText(s);
        inputSuggest.setStart();
        if (!TransitUtil.isEmpty(s))
        {
            btnDelete.setVisibility(0);
        } else
        {
            btnDelete.setVisibility(8);
        }
        inputSuggest.setSelection(inputSuggest.getText().length());
    }

    private void showAroundList(Bundle bundle, StationData stationdata)
    {
        inputSuggest.setMode(nSearchType);
        if (lsArownd == null)
        {
            android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                final InputSearch this$0;

                public void onClick(View view)
                {
                    setResult((StationData)view.getTag());
                    launchReturn();
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            };
            lsArownd = new LinearLayout(this);
            lsArownd.setOrientation(1);
            TextView textview = (TextView)inflater.inflate(0x7f030077, null);
            ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
            if (nListMode != 5)
            {
                RelativeLayout relativelayout1 = (RelativeLayout)inflater.inflate(0x7f03005c, null);
                ((TextView)relativelayout1.findViewById(0x7f0a01fc)).setText(getString(0x7f0d0289));
                lsArownd.addView(relativelayout1);
                textview.setText(stationdata.getName());
                textview.setTag(stationdata);
                textview.setOnClickListener(onclicklistener);
                lsArownd.addView(textview);
                lsArownd.addView(imageview);
            }
            RelativeLayout relativelayout = (RelativeLayout)inflater.inflate(0x7f03005c, null);
            ((TextView)relativelayout.findViewById(0x7f0a01fc)).setText(getString(0x7f0d028a));
            lsArownd.addView(relativelayout);
            for (int i = 0; i < bundle.size(); i++)
            {
                StationData stationdata1 = (StationData)bundle.getSerializable(Integer.toString(i));
                ImageView imageview1 = (ImageView)inflater.inflate(0x7f030059, null);
                TextView textview1 = (TextView)inflater.inflate(0x7f030077, null);
                textview1.setText(stationdata1.getName());
                textview1.setTag(stationdata1);
                textview1.setOnClickListener(onclicklistener);
                lsArownd.addView(textview1);
                lsArownd.addView(imageview1);
            }

        }
        imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
        aroundList.removeAllViews();
        aroundList.addView(lsArownd);
        aroundList.setVisibility(0);
        suggestList.setVisibility(8);
        registList.setVisibility(8);
        historyList.setVisibility(8);
    }

    private void showHistoryList()
    {
        inputSuggest.setMode(nSearchType);
        if (lsHistory != null) goto _L2; else goto _L1
_L1:
        lsHistory = new LinearLayout(this);
        lsHistory.setOrientation(1);
        History history = new History(this);
        if (nSearchType != 3) goto _L4; else goto _L3
_L3:
        if (nListMode != 5) goto _L6; else goto _L5
_L5:
        List list1 = history.getHistory(nListMode);
_L7:
        ImageView imageview;
        for (Iterator iterator = list1.iterator(); iterator.hasNext(); lsHistory.addView(imageview))
        {
            StationData stationdata = (StationData)iterator.next();
            imageview = (ImageView)inflater.inflate(0x7f030059, null);
            TextView textview = (TextView)inflater.inflate(0x7f030077, null);
            textview.setText(stationdata.getName());
            textview.setTag(stationdata);
            textview.setOnClickListener(cllistener);
            lsHistory.addView(textview);
        }

          goto _L2
        Exception exception;
        exception;
        exception.printStackTrace();
_L2:
        historyList.removeAllViews();
        historyList.addView(lsHistory);
        historyList.setVisibility(0);
        suggestList.setVisibility(8);
        registList.setVisibility(8);
        aroundList.setVisibility(8);
        return;
_L6:
        list1 = history.getHistory(0);
          goto _L7
_L4:
        List list = history.getHistory(nSearchType);
        list1 = list;
          goto _L7
    }

    private void showResistList()
    {
        inputSuggest.setMode(nSearchType);
        if (lsRegist == null)
        {
            LinearLayout linearlayout = new LinearLayout(this);
            lsRegist = linearlayout;
            lsRegist.setOrientation(1);
            if (nListMode != 5)
            {
                StationData stationdata1 = getAddressData(getString(0x7f0d00c3));
                StationData stationdata2 = getAddressData(getString(0x7f0d00c5));
                StationData stationdata3 = getAddressData(getString(0x7f0d00c4));
                if (!TransitUtil.isEmpty(stationdata1.getName()) || !TransitUtil.isEmpty(stationdata2.getName()) || !TransitUtil.isEmpty(stationdata3.getName()))
                {
                    RelativeLayout relativelayout2 = (RelativeLayout)inflater.inflate(0x7f03005c, null);
                    ((TextView)relativelayout2.findViewById(0x7f0a01fc)).setText(getString(0x7f0d02a6));
                    lsRegist.addView(relativelayout2);
                    if (!TransitUtil.isEmpty(stationdata1.getName()))
                    {
                        ImageView imageview3 = (ImageView)inflater.inflate(0x7f030059, null);
                        LinearLayout linearlayout4 = (LinearLayout)inflater.inflate(0x7f030084, null);
                        ((TextView)linearlayout4.findViewById(0x7f0a0063)).setText(getString(0x7f0d0250));
                        ((ImageView)linearlayout4.findViewById(0x7f0a0221)).setImageResource(0x7f0201c7);
                        ((TextView)linearlayout4.findViewById(0x7f0a0064)).setText(stationdata1.getName());
                        linearlayout4.setTag(stationdata1);
                        linearlayout4.setOnClickListener(cllistener);
                        lsRegist.addView(linearlayout4);
                        lsRegist.addView(imageview3);
                    }
                    if (!TransitUtil.isEmpty(stationdata2.getName()))
                    {
                        ImageView imageview2 = (ImageView)inflater.inflate(0x7f030059, null);
                        LinearLayout linearlayout3 = (LinearLayout)inflater.inflate(0x7f030084, null);
                        ((TextView)linearlayout3.findViewById(0x7f0a0063)).setText(getString(0x7f0d0252));
                        ((ImageView)linearlayout3.findViewById(0x7f0a0221)).setImageResource(0x7f0201c8);
                        ((TextView)linearlayout3.findViewById(0x7f0a0064)).setText(stationdata2.getName());
                        linearlayout3.setTag(stationdata2);
                        linearlayout3.setOnClickListener(cllistener);
                        lsRegist.addView(linearlayout3);
                        lsRegist.addView(imageview2);
                    }
                    if (!TransitUtil.isEmpty(stationdata3.getName()))
                    {
                        ImageView imageview1 = (ImageView)inflater.inflate(0x7f030059, null);
                        LinearLayout linearlayout2 = (LinearLayout)inflater.inflate(0x7f030084, null);
                        ((TextView)linearlayout2.findViewById(0x7f0a0063)).setText(getString(0x7f0d0251));
                        ((ImageView)linearlayout2.findViewById(0x7f0a0221)).setImageResource(0x7f0201c9);
                        ((TextView)linearlayout2.findViewById(0x7f0a0064)).setText(stationdata3.getName());
                        linearlayout2.setTag(stationdata3);
                        linearlayout2.setOnClickListener(cllistener);
                        lsRegist.addView(linearlayout2);
                        lsRegist.addView(imageview1);
                    }
                }
            }
            if (registinfo != null && registinfo.size() > 0)
            {
                RelativeLayout relativelayout1 = (RelativeLayout)inflater.inflate(0x7f03005c, null);
                ((TextView)relativelayout1.findViewById(0x7f0a01fc)).setText(getString(0x7f0d0470));
                lsRegist.addView(relativelayout1);
                for (int i = 0; i < registinfo.size(); i++)
                {
                    ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                    StationData stationdata = (StationData)registinfo.getSerializable(Integer.toString(i));
                    TextView textview1 = (TextView)inflater.inflate(0x7f030077, null);
                    textview1.setText(stationdata.getName());
                    textview1.setTag(stationdata);
                    textview1.setOnClickListener(cllistener);
                    lsRegist.addView(textview1);
                    lsRegist.addView(imageview);
                }

            } else
            {
                LinearLayout linearlayout1;
                if (taken == null)
                {
                    linearlayout1 = (LinearLayout)inflater.inflate(0x7f030091, null);
                    Button button = (Button)linearlayout1.findViewById(0x7f0a0069);
                    android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

                        final InputSearch this$0;

                        public void onClick(View view)
                        {
                            TransitUtil.login(InputSearch.this);
                        }

            
            {
                this$0 = InputSearch.this;
                super();
            }
                    };
                    button.setOnClickListener(onclicklistener1);
                } else
                {
                    RelativeLayout relativelayout = (RelativeLayout)inflater.inflate(0x7f03005c, null);
                    ((TextView)relativelayout.findViewById(0x7f0a01fc)).setText(getString(0x7f0d0470));
                    lsRegist.addView(relativelayout);
                    linearlayout1 = (LinearLayout)inflater.inflate(0x7f030082, null);
                    TextView textview = (TextView)linearlayout1.findViewById(0x7f0a027b);
                    textview.setText(getString(0x7f0d0278));
                    ((TextView)linearlayout1.findViewById(0x7f0a027f)).setText(getString(0x7f0d0128));
                    android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                        final InputSearch this$0;

                        public void onClick(View view)
                        {
                            Intent intent1 = new Intent(InputSearch.this, jp/co/yahoo/android/apps/transit/OthersEditStationActivity);
                            startActivityForResult(intent1, getResources().getInteger(0x7f0c0056));
                        }

            
            {
                this$0 = InputSearch.this;
                super();
            }
                    };
                    textview.setOnClickListener(onclicklistener);
                }
                lsRegist.addView(linearlayout1);
            }
        }
        registList.removeAllViews();
        registList.addView(lsRegist);
        registList.setVisibility(0);
        suggestList.setVisibility(8);
        historyList.setVisibility(8);
        aroundList.setVisibility(8);
    }

    private void touchRDSearch()
    {
        if (nReqCode == getResources().getInteger(0x7f0c0066))
        {
            touchRD((new StringBuilder()).append(getString(0x7f0d0560)).append("/").append(getString(0x7f0d0450)).append("/").append(getString(0x7f0d0452)).append("/").append(getString(0x7f0d0415)).append("/").append(getString(0x7f0d0561)).toString());
        }
    }

    protected void getAroundInfo()
    {
        if (lsArownd != null)
        {
            aroundList.setVisibility(0);
            suggestList.setVisibility(8);
            registList.setVisibility(8);
            historyList.setVisibility(8);
            return;
        } else
        {
            jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener locationsearchllistener = new jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener() {

                final InputSearch this$0;

                public void onError(String s, String s1)
                {
                    if (!TransitUtil.isEmpty(s1))
                    {
                        showErrorMessageDialog(s1, getString(0x7f0d014f));
                    }
                }

                public void onSuccess(String s, Bundle bundle1)
                {
                    if (!TransitUtil.isEmpty(bundle1.getString(getString(0x7f0d01a2))))
                    {
                        objAddress = new StationData();
                        objAddress.setnNaviType(128);
                        objAddress.setLat(bundle1.getString(getString(0x7f0d01a3)));
                        objAddress.setLon(bundle1.getString(getString(0x7f0d01a4)));
                        objAddress.setAddress(bundle1.getString(getString(0x7f0d01a2)));
                        objAddress.setName(objAddress.getAddress());
                    }
                    Bundle bundle2 = bundle1.getBundle(getString(0x7f0d0240));
                    if (bundle2 != null && bundle2.size() > 0)
                    {
                        objStations = bundle2;
                    }
                    if (objAddress != null && objStations != null && objStations.size() > 0)
                    {
                        showAroundList(objStations, objAddress);
                    }
                }

                public void onTimeout(String s, String s1)
                {
                    if (!TransitUtil.isEmpty(s1))
                    {
                        showErrorMessageDialog(s1, getString(0x7f0d014f));
                    }
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            };
            locAddress = new LocationSearch(this, locationsearchllistener);
            locAddress.setDialogMessage(false, null);
            locAddress.setErroMsg(false);
            locAddress.getCurrentAddress();
            locStation = new LocationSearch(this, locationsearchllistener);
            Bundle bundle = new Bundle();
            bundle.putString(getString(0x7f0d01b6), "10");
            bundle.putString(getString(0x7f0d018e), "dist");
            locStation.getCurrentStation(bundle);
            return;
        }
    }

    protected void launchSearchResult(final ConditionData condition)
    {
        NaviSearch navisearch = new NaviSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final InputSearch this$0;
            final ConditionData val$condition;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s = apierror.getMessage();
                if (StringUtil.isEmpty(s))
                {
                    s = getString(0x7f0d0108);
                }
                showSimpleErrorMessageDialog(s);
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData navisearchdata = NaviSearch.m_routeList;
                Intent intent1 = new Intent(InputSearch.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                intent1.putExtra(getString(0x7f0d0232), navisearchdata);
                intent1.putExtra(getString(0x7f0d022c), condition);
                startActivityForResult(intent1, getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$0 = InputSearch.this;
                condition = conditiondata;
                super();
            }
        });
        navisearch.setCondition(condition);
        navisearch.request();
    }

    protected void onActivityResult(int i, int j, Intent intent1)
    {
        super.onActivityResult(i, j, intent1);
        if (nReqCode == getResources().getInteger(0x7f0c005f))
        {
            if (j == 0)
            {
                launchCancel();
            } else
            if (-1 == j)
            {
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030020);
        getWindow().setSoftInputMode(5);
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        imm = (InputMethodManager)getSystemService("input_method");
        intent = getIntent();
        condition = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        nSearchType = intent.getIntExtra(getString(0x7f0d0233), 3);
        nListMode = intent.getIntExtra(getString(0x7f0d01c9), 1);
        boolean flag = intent.getBooleanExtra(getString(0x7f0d01de), true);
        boolean flag1 = intent.getBooleanExtra(getString(0x7f0d01c3), false);
        nReqCode = intent.getIntExtra(getString(0x7f0d01df), 0);
        bCurrent = intent.getBooleanExtra(getString(0x7f0d01a1), false);
        sTarget = intent.getStringExtra(getString(0x7f0d0243));
        bForceSuggest = intent.getBooleanExtra(getString(0x7f0d01bc), false);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayOptions(0x10 | actionbar.getDisplayOptions());
        actionbar.setCustomView(0x7f030089);
        LinearLayout linearlayout = (LinearLayout)actionbar.getCustomView();
        inputSuggest = (AutoCompleteSuggestTextView)linearlayout.findViewById(0x7f0a0298);
        inputFinish = (Button)linearlayout.findViewById(0x7f0a0299);
        btnDelete = (Button)linearlayout.findViewById(0x7f0a029a);
        lnBtnArea = (LinearLayout)findViewById(0x7f0a00b8);
        btnRegist = (Button)findViewById(0x7f0a00ba);
        btnHistory = (Button)findViewById(0x7f0a00b9);
        btnAround = (Button)findViewById(0x7f0a00bb);
        suggestListItems = (ScrollView)findViewById(0x7f0a00c4);
        historyList = (ScrollView)findViewById(0x7f0a00bd);
        registList = (ScrollView)findViewById(0x7f0a00c5);
        aroundList = (ScrollView)findViewById(0x7f0a00c6);
        suggestList = (LinearLayout)findViewById(0x7f0a00be);
        btnDelete.setVisibility(8);
        dispatchPage(nReqCode);
        String s = intent.getStringExtra(getString(0x7f0d022d));
        if (!TransitUtil.isEmpty(s))
        {
            inputSuggest.setHint(s);
        } else
        if (nSearchType == 1)
        {
            inputSuggest.setHint(getString(0x7f0d016c));
        } else
        {
            inputSuggest.setHint(getString(0x7f0d016b));
        }
        if (bForceSuggest)
        {
            inputFinish.setVisibility(8);
        }
        cllistener = new android.view.View.OnClickListener() {

            final InputSearch this$0;

            public void onClick(View view)
            {
                touchRDSearch();
                setResult((StationData)view.getTag());
                if ((objResult.getType() == 1 || objResult.getType() == 2) && TransitUtil.isEmpty(objResult.getId()))
                {
                    getStationId(objResult.getName());
                    return;
                } else
                {
                    launchReturn();
                    return;
                }
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        };
        if (nSearchType == 1)
        {
            inputSuggest.setMode(1);
            findViewById(0x7f0a00bf).setVisibility(8);
            if (!flag)
            {
                btnRegist.setVisibility(8);
            }
        } else
        if (nSearchType == 2)
        {
            inputSuggest.setMode(2);
            findViewById(0x7f0a00bf).setVisibility(8);
            if (!flag)
            {
                btnRegist.setVisibility(8);
            }
        } else
        {
            inputSuggest.setMode(3);
            inputFinish.setText(getString(0x7f0d050e));
        }
        if (nListMode == 5)
        {
            findViewById(0x7f0a00c2).setVisibility(8);
        }
        if (!flag && nSearchType != 0)
        {
            bShowBtn = false;
            lnBtnArea.setVisibility(8);
        } else
        {
            bShowBtn = true;
        }
        if (flag)
        {
            btnRegist.setOnClickListener(new android.view.View.OnClickListener() {

                final InputSearch this$0;

                public void onClick(View view)
                {
                    touchTapRD(getString(0x7f0d040c));
                    imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
                    setBtnStatus(BTN_MODE_REGIST);
                    if (registinfo == null)
                    {
                        setRegist();
                        if (taken == null)
                        {
                            showResistList();
                            return;
                        } else
                        {
                            regist.requestAsync(true);
                            return;
                        }
                    } else
                    {
                        showResistList();
                        return;
                    }
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            });
        }
        if (flag1)
        {
            btnAround.setOnClickListener(new android.view.View.OnClickListener() {

                final InputSearch this$0;

                public void onClick(View view)
                {
                    touchTapRD(getString(0x7f0d03e8));
                    imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
                    setBtnStatus(BTN_MODE_ARROWND);
                    getAroundInfo();
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            });
        } else
        {
            btnAround.setVisibility(8);
        }
        inputFinish.setOnClickListener(new android.view.View.OnClickListener() {

            final InputSearch this$0;

            public void onClick(View view)
            {
                touchRDSearch();
                launchReturn();
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        });
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {

            final InputSearch this$0;

            public void onClick(View view)
            {
                setText("");
                objResult = null;
                setBtnStatus(BTN_MODE_HISTORY);
                showHistoryList();
                btnDelete.setVisibility(8);
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        });
        btnHistory.setOnClickListener(new android.view.View.OnClickListener() {

            final InputSearch this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0429));
                imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
                setBtnStatus(BTN_MODE_HISTORY);
                showHistoryList();
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        });
        inputSuggest.addSuggestListener(new jp.co.yahoo.android.apps.transit.viewparts.AutoCompleteSuggestTextView.SuggestListener() {

            final InputSearch this$0;

            public void onInputed(String s1)
            {
                if (s1 != null && s1 != "")
                {
                    btnDelete.setVisibility(0);
                }
            }

            public void onNoMatch(int i)
            {
                showSuggest(null);
            }

            public void onNoinput()
            {
                setBtnStatus(BTN_MODE_HISTORY);
                showHistoryList();
                btnDelete.setVisibility(8);
            }

            public void onSuggestSuccess(int i, List list)
            {
                setBtnStatus(BTN_MODE_SUGGEST);
                showSuggest(list);
            }

            
            {
                this$0 = InputSearch.this;
                super();
            }
        });
        setBtnStatus(BTN_MODE_HISTORY);
        showHistoryList();
        if (nReqCode == 0)
        {
            showSelectTicketTypeDialog(null);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            launchCancel();
        }
        return super.onKeyDown(i, keyevent);
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            lsRegist = null;
            setRegist();
            regist.requestAsync(true);
        }
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
        if (locStation != null)
        {
            locStation.stopRequest();
        }
        if (locAddress != null)
        {
            locAddress.stopRequest();
        }
    }

    public void scrollBusstop(View view)
    {
        RelativeLayout relativelayout = (RelativeLayout)suggestList.findViewById(2);
        if (relativelayout == null)
        {
            return;
        } else
        {
            suggestListItems.smoothScrollTo(0, relativelayout.getTop());
            return;
        }
    }

    public void scrollLandmark(View view)
    {
        RelativeLayout relativelayout = (RelativeLayout)suggestList.findViewById(3);
        if (relativelayout == null)
        {
            return;
        } else
        {
            suggestListItems.smoothScrollTo(0, relativelayout.getTop());
            return;
        }
    }

    public void scrollStation(View view)
    {
        RelativeLayout relativelayout = (RelativeLayout)suggestList.findViewById(1);
        if (relativelayout == null)
        {
            return;
        } else
        {
            suggestListItems.smoothScrollTo(0, relativelayout.getTop());
            return;
        }
    }

    protected void setRegist()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken != null)
        {
            regist = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final InputSearch this$0;

                public boolean onCanceled()
                {
                    showResistList();
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                    {
                        taken = null;
                    }
                    showResistList();
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    registinfo = bundle;
                    showResistList();
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            });
        }
    }

    protected void showSuggest(List list)
    {
        if (lsSuggest == null)
        {
            lsSuggest = new LinearLayout(this);
            lsSuggest.setOrientation(1);
            suggestListItems.setOnTouchListener(new android.view.View.OnTouchListener() {

                final InputSearch this$0;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    switch (motionevent.getAction())
                    {
                    default:
                        return false;

                    case 2: // '\002'
                    case 8: // '\b'
                        imm.hideSoftInputFromWindow(inputSuggest.getWindowToken(), 0);
                        break;
                    }
                    return false;
                }

            
            {
                this$0 = InputSearch.this;
                super();
            }
            });
        } else
        {
            lsSuggest.removeAllViews();
        }
        if (nSearchType != 2)
        {
            addSuggestView(list, getString(0x7f0d0304), 1);
        }
        if (nSearchType != 1)
        {
            addSuggestView(list, getString(0x7f0d025e), 2);
        }
        if (nSearchType == 3 && nListMode != 5)
        {
            addSuggestView(list, getString(0x7f0d0290), 3);
        }
        suggestListItems.removeAllViews();
        suggestListItems.addView(lsSuggest);
        suggestList.setVisibility(0);
        aroundList.setVisibility(8);
        registList.setVisibility(8);
        historyList.setVisibility(8);
    }





/*
    static BearerToken access$1002(InputSearch inputsearch, BearerToken bearertoken)
    {
        inputsearch.taken = bearertoken;
        return bearertoken;
    }

*/











/*
    static StationData access$1902(InputSearch inputsearch, StationData stationdata)
    {
        inputsearch.objAddress = stationdata;
        return stationdata;
    }

*/




/*
    static Bundle access$2002(InputSearch inputsearch, Bundle bundle)
    {
        inputsearch.objStations = bundle;
        return bundle;
    }

*/


/*
    static StationData access$202(InputSearch inputsearch, StationData stationdata)
    {
        inputsearch.objResult = stationdata;
        return stationdata;
    }

*/











/*
    static Bundle access$902(InputSearch inputsearch, Bundle bundle)
    {
        inputsearch.registinfo = bundle;
        return bundle;
    }

*/
}
