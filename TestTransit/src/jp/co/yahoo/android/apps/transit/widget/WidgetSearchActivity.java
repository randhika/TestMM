// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.OthersAdressSearchActivity;
import jp.co.yahoo.android.apps.transit.SearchResultListActivity;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.yjvoice.YJVONbestResult;
import jp.co.yahoo.android.yjvoice.YJVORecognizeListener;
import jp.co.yahoo.android.yjvoice.YJVORecognizeResult;
import jp.co.yahoo.android.yjvoice.YJVO_FILTER;
import jp.co.yahoo.android.yjvoice.YJVO_STATE;
import jp.co.yahoo.android.yjvoice.YJVO_TYPE;
import jp.co.yahoo.android.yjvoice.screen.YJVOVRecognizer;

public class WidgetSearchActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{

    static final int VOICE_REQUEST_CODE = 100;
    private ConditionData conditions;
    private YJVOVRecognizer mRecognizer;
    private String nowAddress;
    private String nowLat;
    private String nowLon;
    private String searchType;
    private SetSharedPreferences sp;
    private String voiceGoal;
    private WidgetSearchActivity widgetSearch;

    public WidgetSearchActivity()
    {
    }

    private void addressDialog()
    {
        if (isFinishing())
        {
            finish();
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setCancelable(true).setPositiveButton(getString(0x7f0d015d), new android.content.DialogInterface.OnClickListener() {

                final WidgetSearchActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Intent intent = new Intent(WidgetSearchActivity.this, jp/co/yahoo/android/apps/transit/OthersAdressSearchActivity);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c004a));
                    finish();
                }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
            }).setNegativeButton(getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

                final WidgetSearchActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                    widgetSearch.finish();
                }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
            }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final WidgetSearchActivity this$0;

                public void onCancel(DialogInterface dialoginterface)
                {
                    dialoginterface.dismiss();
                    widgetSearch.finish();
                }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
            }).setMessage(getString(0x7f0d0126)).show();
            return;
        }
    }

    private void searchStart()
    {
        setDefaultCondition();
        NaviSearch navisearch = new NaviSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final WidgetSearchActivity this$0;

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
                Intent intent = new Intent(WidgetSearchActivity.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                intent.putExtra(getString(0x7f0d0232), navisearchdata);
                intent.putExtra(getString(0x7f0d022c), conditions);
                startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
        });
        navisearch.setCondition(conditions);
        navisearch.request();
    }

    private void setCurrentPlace()
    {
        LocationSearch locationsearch = new LocationSearch(this, this);
        locationsearch.setIsFinishActivity(true);
        locationsearch.getCurrentAddress();
    }

    public void getStationForVoice()
    {
        if (isFinishing())
        {
            finish();
        } else
        {
            setVoiceSetting();
            if (!mRecognizer.isRecognizing())
            {
                mRecognizer.recognizeStart();
                return;
            }
        }
    }

    protected void init()
    {
        super.init();
        if ("home".equals(searchType))
        {
            sp = new SetSharedPreferences(this, getString(0x7f0d00c3));
            String s = sp.getStringSharedPreferenceData(getString(0x7f0d01a2));
            if (s == null || s == "")
            {
                addressDialog();
                return;
            } else
            {
                setCurrentPlace();
                return;
            }
        }
        if ("voice".equals(searchType))
        {
            getStationForVoice();
            return;
        } else
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0113));
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300b9);
        widgetSearch = this;
        searchType = getIntent().getStringExtra(getString(0x7f0d024d));
        boolean flag = getSharedPreferences(getString(0x7f0d04e2), 0).getBoolean(getString(0x7f0d039c), true);
        if (!getResources().getBoolean(0x7f080007) || !flag)
        {
            init();
        }
    }

    public void onError(String s, String s1)
    {
        showSimpleErrorMessageDialog(s1);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            finish();
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onSuccess(String s, Bundle bundle)
    {
        nowAddress = bundle.getString(getString(0x7f0d01a2));
        nowLon = bundle.getString(getString(0x7f0d01a4));
        nowLat = bundle.getString(getString(0x7f0d01a3));
        if ("voice".equals(searchType))
        {
            conditions = (new SaveCondition(this)).getCond();
            if (conditions == null)
            {
                conditions = new ConditionData();
            }
            conditions.startName = nowAddress;
            conditions.startLat = nowLat;
            conditions.startLon = nowLon;
            conditions.goalName = voiceGoal;
            searchStart();
            return;
        }
        if ("home".equals(searchType))
        {
            sp = new SetSharedPreferences(this, getString(0x7f0d00c3));
            String s1 = sp.getStringSharedPreferenceData(getString(0x7f0d01a2));
            String s2 = sp.getStringSharedPreferenceData(getString(0x7f0d01a4));
            String s3 = sp.getStringSharedPreferenceData(getString(0x7f0d01a3));
            conditions = (new SaveCondition(this)).getCond();
            if (conditions == null)
            {
                conditions = new ConditionData();
            }
            conditions.startName = nowAddress;
            conditions.startLat = nowLat;
            conditions.startLon = nowLon;
            conditions.goalName = s1;
            conditions.goalLat = s3;
            conditions.goalLon = s2;
            searchStart();
            return;
        } else
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0113));
            return;
        }
    }

    public void onTimeout(String s, String s1)
    {
        showSimpleErrorMessageDialog(s1);
    }

    public void setDefaultCondition()
    {
        Calendar calendar = Calendar.getInstance();
        conditions.year = calendar.get(1);
        conditions.month = 1 + calendar.get(2);
        conditions.day = calendar.get(5);
        conditions.hour = calendar.get(11);
        conditions.minite = calendar.get(12);
    }

    protected void setVoiceSetting()
    {
        mRecognizer = new YJVOVRecognizer();
        mRecognizer.init(new YJVORecognizeListener() {

            final WidgetSearchActivity this$0;

            public void onRecognizeResult(int i, YJVORecognizeResult yjvorecognizeresult)
            {
                (new Handler(getMainLooper())).post(new Runnable() {

                    final _cls2 this$1;

                    public void run()
                    {
                        YJVORecognizeResult yjvorecognizeresult = mRecognizer.getResult(0);
                        if (yjvorecognizeresult.type == YJVO_TYPE.NBEST)
                        {
                            String s = ((YJVONbestResult)yjvorecognizeresult.result).getTranscribe(0);
                            if (TransitUtil.isEmpty(s))
                            {
                                showSimpleErrorMessageDialog(getString(0x7f0d0157));
                                return;
                            } else
                            {
                                String as[] = s.split(" ", 0);
                                voiceGoal = as[0];
                                setCurrentPlace();
                                return;
                            }
                        } else
                        {
                            finish();
                            return;
                        }
                    }

            
            {
                this$1 = _cls2.this;
                super();
            }
                });
            }

            public void onRecognizeState(YJVO_STATE yjvo_state)
            {
                static class _cls8
                {

                    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[];

                    static 
                    {
                        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE = new int[YJVO_STATE.values().length];
                        try
                        {
                            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.RECOGNIZE_CANCEL.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.RECOGNIZE_ERROR.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[YJVO_STATE.VOICE_TOO_LONG.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror2)
                        {
                            return;
                        }
                    }
                }

                switch (_cls8..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_STATE[yjvo_state.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    finish();
                    return;

                case 2: // '\002'
                case 3: // '\003'
                    finish();
                    break;
                }
            }

            public void onRecordingStart()
            {
            }

            public void onVolumeChanged(short word0)
            {
            }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
        }, this);
        mRecognizer.setPrompt(getString(0x7f0d032e));
        mRecognizer.setApplicationData(getString(0x7f0d059c), TransitUtil.getVersionName(this));
        mRecognizer.setFilter(YJVO_FILTER.NUMBER);
    }

    public void showSimpleErrorMessageDialog(String s)
    {
        if (isFinishing())
        {
            finish();
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleWarnning(getString(0x7f0d015e)).setMessage(s).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final WidgetSearchActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                    widgetSearch.finish();
                }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
            }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final WidgetSearchActivity this$0;

                public void onCancel(DialogInterface dialoginterface)
                {
                    dialoginterface.cancel();
                    widgetSearch.finish();
                }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
            }).show();
            return;
        }
    }




/*
    static String access$202(WidgetSearchActivity widgetsearchactivity, String s)
    {
        widgetsearchactivity.voiceGoal = s;
        return s;
    }

*/


}
