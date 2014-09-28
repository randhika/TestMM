// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.text.format.Time;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jp.co.yahoo.android.apps.transit.common.BackgroundWorker;
import jp.co.yahoo.android.apps.transit.common.DragAndDropLayout;
import jp.co.yahoo.android.apps.transit.common.RequestRecommend;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.WeatherSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.api.data.WeatherData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownPanelManager;
import jp.co.yahoo.android.apps.transit.timer.common.Holiday;
import jp.co.yahoo.android.apps.transit.timer.common.RingAlerm;
import jp.co.yahoo.android.apps.transit.timer.common.SettingShortcut;
import jp.co.yahoo.android.apps.transit.timer.common.SkinDrawableManager;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CheckListDialog;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownPanelView;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownTimelineView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.apps.transit.viewparts.TransitVerticalDialogBuilder;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingTopActivity, SettingAlbumListActivity, CountdownListActivity

public class CountdownActivity extends CountdownBaseActivity
    implements android.view.GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener, jp.co.yahoo.android.apps.transit.timer.common.CountdownManager.CountdownListener
{

    private Alerm alerm;
    private FrameLayout allbody;
    private Animation animIn;
    private Animation animOut;
    private Animation animOutFull;
    private TextView btnAlert;
    private View changeArea;
    private ImageView changeBtn;
    private CountdownActivity context;
    private SettingDb db;
    private View dragView;
    private StationData fromStation;
    private DragAndDropLayout fullScreenBody;
    private GestureDetector gesture;
    private Intent intent;
    private boolean isClickedDia;
    private boolean isPausing;
    private boolean isScrollingTimeline;
    private boolean isSkin;
    private ViewPager mViewPager;
    private SkinMetaData meta;
    int nDoubleTap;
    private ImageView next;
    private CountdownPanelManager objCountdown;
    private LinearLayout panelbody;
    private ImageView prev;
    private ImageView reload;
    protected Resources res;
    private boolean showDialog;
    private SkinDrawableManager skin;
    private AlermData start;
    private AlermData startAlerm;
    private String startTime;
    private HorizontalScrollView timelineBody;
    private CountdownTimelineView timelineView;
    private int timetableId;
    private Bundle timetables;
    private StationData toStation;
    protected int type;
    private int week;
    private int window_option;

    public CountdownActivity()
    {
        window_option = 128;
        mViewPager = null;
        db = null;
        objCountdown = null;
        timetables = null;
        alerm = null;
        reload = null;
        gesture = null;
        res = null;
        type = 1;
        week = -1;
        timetableId = -1;
        startTime = null;
        isPausing = false;
        showDialog = false;
        prev = null;
        next = null;
        changeArea = null;
        changeBtn = null;
        btnAlert = null;
        timelineBody = null;
        timelineView = null;
        isClickedDia = false;
        isScrollingTimeline = false;
        allbody = null;
        panelbody = null;
        fullScreenBody = null;
        dragView = null;
        skin = null;
        meta = null;
        isSkin = false;
        animIn = null;
        animOut = null;
        animOutFull = null;
        nDoubleTap = 0;
    }

    private void activityAnimate(int i)
    {
    }

    private void analyzeUri(Uri uri)
    {
        String s = uri.getQueryParameter(getString(0x7f0d0389));
        String s1 = uri.getQueryParameter(getString(0x7f0d037f));
        String s2 = uri.getQueryParameter(getString(0x7f0d0378));
        String s3 = uri.getQueryParameter(getString(0x7f0d0388));
        String s4 = uri.getQueryParameter(getString(0x7f0d0374));
        String s5 = "0";
        String s6 = "0";
        if (s3 != null && s3.length() > 3)
        {
            s5 = s3.substring(0, 2);
            s6 = s3.substring(2, 4);
        }
        fromStation = new StationData();
        fromStation.setName(s);
        fromStation.setRailname(s1);
        fromStation.setDirname(s2);
        fromStation.setGovernmentCode(s4);
        fromStation.setSettingType(res.getInteger(0x7f0c0075));
        TimeTableItemData timetableitemdata = new TimeTableItemData();
        timetableitemdata.setMinute(Integer.parseInt(s6));
        timetableitemdata.setHour(Integer.parseInt(s5));
        timetableitemdata.setIndex(0);
        timetableitemdata.setDestinfo(s1);
        timetableitemdata.setTraintype("-1");
        timetableitemdata.setTraininfo("-1");
        timetableitemdata.setLastStation(true);
        timetableitemdata.setFirstStation(true);
        Bundle bundle = new Bundle();
        bundle.putSerializable("0", timetableitemdata);
        Bundle bundle1 = new Bundle();
        bundle1.putBundle("timetable", bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(Integer.toString(1), bundle1);
        bundle2.putBundle(Integer.toString(2), bundle1);
        bundle2.putBundle(Integer.toString(4), bundle1);
        fromStation.setTimetable(bundle2);
        type = res.getInteger(0x7f0c0075);
    }

    private void checkSkin(SkinMetaData skinmetadata)
    {
        if (isSkin(skinmetadata))
        {
            if (skinmetadata.isUpdate)
            {
                isSkin = false;
                skindb.updateSetting(getString(0x7f0d04f6));
                Toast.makeText(this, getString(0x7f0d04f0), 1).show();
                return;
            }
            if (!CountdownUtil.isNowTerm(skinmetadata.sStartDate, skinmetadata.sEndDate))
            {
                isSkin = false;
                skindb.updateSetting(getString(0x7f0d04f6));
                Toast.makeText(this, getString(0x7f0d04ef), 1).show();
                return;
            }
            if (skinmetadata.isDelete)
            {
                isSkin = false;
                skindb.updateSetting(getString(0x7f0d04f6));
                Toast.makeText(this, getString(0x7f0d04ed), 1).show();
                return;
            }
            if (!(new File(skinmetadata.sPath)).exists())
            {
                isSkin = false;
                skindb.updateSetting(getString(0x7f0d04f6));
                Toast.makeText(this, getString(0x7f0d04ed), 1).show();
                return;
            } else
            {
                isSkin = true;
                return;
            }
        } else
        {
            isSkin = false;
            return;
        }
    }

    private void launchSettingTop()
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingTopActivity);
        intent1.setAction("android.intent.action.VIEW");
        intent1.putExtra(getString(0x7f0d01ca), 9);
        intent1.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c005f));
        startActivity(intent1);
        finish();
    }

    private void resetTimeline()
    {
        if (timelineView == null)
        {
            return;
        } else
        {
            timelineView.resetView(objCountdown.getNowTimetables(), objCountdown.getTargetTime());
            return;
        }
    }

    private void restartCountdown()
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        if (type == res.getInteger(0x7f0c0075))
        {
            intent1.setAction("android.intent.action.VIEW");
            intent1.setData(intent.getData());
        } else
        {
            intent1.putExtra(getString(0x7f0d0247), type);
            intent1.putExtra(getString(0x7f0d024c), week);
        }
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003b));
        finish();
        activityAnimate(type);
    }

    private void setAlerm()
    {
        startAlerm = alerm.getCountdownAlerm();
        if (btnAlert == null)
        {
            btnAlert = (TextView)findViewById(0x7f0a01c8);
        }
        if (startAlerm != null)
        {
            btnAlert.setSelected(true);
            return;
        } else
        {
            btnAlert.setSelected(false);
            return;
        }
    }

    private void setDragViewPosition(int i, int j)
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)dragView.getLayoutParams();
        layoutparams.leftMargin = i;
        layoutparams.topMargin = j;
        layoutparams.rightMargin = 0;
        layoutparams.bottomMargin = 0;
        layoutparams.gravity = 51;
        dragView.setLayoutParams(layoutparams);
    }

    private void settingBtnAction()
    {
        changeArea = findViewById(0x7f0a01bf);
        ImageView imageview;
        if (type == res.getInteger(0x7f0c0074) || type == res.getInteger(0x7f0c0073))
        {
            changeArea.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                final CountdownActivity this$0;

                public void onGlobalLayout()
                {
                    int i = (int)TypedValue.applyDimension(1, 11F, res.getDisplayMetrics());
                    android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)changeBtn.getLayoutParams();
                    layoutparams.topMargin = (changeArea.getTop() + ((View)changeArea.getParent()).getTop()) - i;
                    changeBtn.setLayoutParams(layoutparams);
                    changeBtn.setVisibility(0);
                    if (android.os.Build.VERSION.SDK_INT < 16)
                    {
                        TransitUtil.removeGlobalOnLayoutListener(changeArea.getViewTreeObserver(), this);
                        return;
                    } else
                    {
                        TransitUtil.removeOnGlobalLayoutListener(changeArea.getViewTreeObserver(), this);
                        return;
                    }
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            });
            changeBtn = (ImageView)findViewById(0x7f0a00d3);
            changeBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final CountdownActivity this$0;

                public void onClick(View view)
                {
                    changeActivityToggle();
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            });
            Bundle bundle;
            if (type == res.getInteger(0x7f0c0074))
            {
                changeBtn.setImageResource(0x7f0200dd);
                changeBtn.setContentDescription(getString(0x7f0d0287));
            } else
            if (type == res.getInteger(0x7f0c0073))
            {
                changeBtn.setImageResource(0x7f0200dc);
                changeBtn.setContentDescription(getString(0x7f0d0265));
            }
        }
        ((TextView)findViewById(0x7f0a01c6)).setOnClickListener(new android.view.View.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0415));
                if (fromStation != null && fromStation.getSettingType() == getResources().getInteger(0x7f0c0075))
                {
                    finish();
                } else
                {
                    TimeTableItemData timetableitemdata = objCountdown.getTargetTime();
                    if (timetableitemdata != null)
                    {
                        int i = timetableitemdata.getHour();
                        int j = timetableitemdata.getMinute();
                        Calendar calendar = Calendar.getInstance();
                        if (i >= 24)
                        {
                            i -= 24;
                            int k = Calendar.getInstance().get(11);
                            if (k < 24 && k > 3)
                            {
                                calendar.add(5, 1);
                            }
                        }
                        String s = Integer.toString(calendar.get(1));
                        if (1 + calendar.get(2) < 10)
                        {
                            s = (new StringBuilder()).append(s).append("0").toString();
                        }
                        String s1 = (new StringBuilder()).append(s).append(Integer.toString(1 + calendar.get(2))).toString();
                        if (calendar.get(5) < 10)
                        {
                            s1 = (new StringBuilder()).append(s1).append("0").toString();
                        }
                        String s2 = (new StringBuilder()).append(s1).append(Integer.toString(calendar.get(5))).toString();
                        String s3;
                        String s4;
                        String s5;
                        Bundle bundle4;
                        if (i < 10)
                        {
                            s3 = (new StringBuilder()).append("0").append(Integer.toString(i)).toString();
                        } else
                        {
                            s3 = Integer.toString(i);
                        }
                        if (j < 10)
                        {
                            s4 = (new StringBuilder()).append(s3).append("0").append(Integer.toString(j)).toString();
                        } else
                        {
                            s4 = (new StringBuilder()).append(s3).append(Integer.toString(j)).toString();
                        }
                        s5 = "android.intent.action.VIEW";
                        bundle4 = new Bundle();
                        if (toStation == null)
                        {
                            s5 = "android.intent.action.EDIT";
                            bundle4.putString(getString(0x7f0d038c), "");
                        } else
                        {
                            bundle4.putString(getString(0x7f0d038c), toStation.getName());
                            bundle4.putString(getString(0x7f0d038d), toStation.getStationId());
                        }
                        bundle4.putString(getString(0x7f0d0385), getString(0x7f0d005a));
                        bundle4.putString(getString(0x7f0d037c), fromStation.getName());
                        bundle4.putString(getString(0x7f0d037d), fromStation.getStationId());
                        bundle4.putString(getString(0x7f0d038f), "1");
                        bundle4.putString(getString(0x7f0d038b), s4);
                        bundle4.putString(getString(0x7f0d0376), s2);
                        intentToTransit(bundle4, s5);
                        return;
                    }
                }
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        imageview = (ImageView)findViewById(0x7f0a01be);
        if (db.count(type) < 2)
        {
            imageview.setVisibility(8);
        } else
        {
            imageview.setOnClickListener(new android.view.View.OnClickListener() {

                final CountdownActivity this$0;

                public void onClick(View view)
                {
                    ArrayList arraylist;
                    String s;
                    String as[];
                    int i;
                    if (type == res.getInteger(0x7f0c0074))
                    {
                        touchTapRD(getString(0x7f0d041d));
                    } else
                    {
                        touchTapRD(getString(0x7f0d041c));
                    }
                    arraylist = db.getAllStation(type);
                    if (type == res.getInteger(0x7f0c0074))
                    {
                        s = getString(0x7f0d0179);
                    } else
                    {
                        s = getString(0x7f0d0178);
                    }
                    as = new String[arraylist.size()];
                    i = 0;
                    for (int j = 0; j < arraylist.size(); j++)
                    {
                        StationData stationdata = (StationData)arraylist.get(j);
                        as[j] = (new StringBuilder()).append(stationdata.getName()).append("\n").append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString();
                        if (stationdata.isSetting())
                        {
                            i = j;
                        }
                    }

                    showSingleChoiceListDialog(s, 0, null, as, i, 0x7f0d007f, arraylist. new android.content.DialogInterface.OnClickListener() {

                        final _cls5 this$1;
                        final ArrayList val$stations;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            StationData stationdata = (StationData)stations.get(i);
                            stationdata.setSetting(true);
                            (new SettingDb(_fld0)).updateSetting(stationdata, type);
                            changeActivity(type);
                        }

            
            {
                this$1 = final__pcls5;
                stations = ArrayList.this;
                super();
            }
                    });
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            });
        }
        bundle = fromStation.getTimetable().getBundle(Integer.toString(CountdownUtil.getNowWeek(this)));
        if (bundle != null)
        {
            Bundle bundle1 = bundle.getBundle("dest");
            TextView textview = (TextView)findViewById(0x7f0a01c7);
            if (bundle1 == null || bundle1.size() < 1)
            {
                textview.setVisibility(8);
            } else
            {
                Bundle bundle2 = bundle1.getBundle("dest");
                Bundle bundle3 = bundle1.getBundle("kind");
                if ((bundle2 == null || bundle2.size() < 2) && (bundle3 == null || bundle3.size() < 2))
                {
                    textview.setVisibility(8);
                    return;
                }
            }
        }
    }

    private void settingData(int i)
    {
        SettingDb settingdb = new SettingDb(this);
        if (type == res.getInteger(0x7f0c0074))
        {
            if (fromStation == null)
            {
                fromStation = settingdb.getTimetable(getResources().getInteger(0x7f0c0074));
            }
            toStation = settingdb.getTimetable(getResources().getInteger(0x7f0c0073));
        } else
        if (type == res.getInteger(0x7f0c0073))
        {
            if (fromStation == null)
            {
                fromStation = settingdb.getTimetable(getResources().getInteger(0x7f0c0073));
            }
            toStation = settingdb.getTimetable(getResources().getInteger(0x7f0c0074));
            return;
        }
    }

    private void showAlertEditDialogNew()
    {
        int i = startAlerm.getStartTime();
        int j = startAlerm.getCountdownTime();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("\u300C").append(startAlerm.getLine()).append(" ");
        stringbuffer.append(CountdownUtil.getZeroNum(i / 3600)).append(":");
        stringbuffer.append(CountdownUtil.getZeroNum((i % 3600) / 60)).append("\u767A  \u306E");
        stringbuffer.append(Integer.toString(j)).append("\u5206\u524D");
        stringbuffer.append("\u300D").append(getString(0x7f0d0033));
        stringbuffer.append("\n\n").append(getString(0x7f0d0032));
        (new TransitVerticalDialogBuilder(this)).setTitleWarnning(getString(0x7f0d0257)).setMessage(stringbuffer).setPositiveButton(getString(0x7f0d0031), new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                alerm.delAlarm(startAlerm);
                startAlerm = null;
                setAlermOff();
                Toast.makeText(CountdownActivity.this, getString(0x7f0d0030), 0).show();
                setAlerm();
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        }).setNegativeButton(getString(0x7f0d0071), null).show();
    }

    private void showExtractToast()
    {
        String s = (new SimpleDateFormat("yyMMdd")).format(new Date());
        String s1 = s.substring(2, 6);
        if (s1.equals("1231") || s1.equals("0101") || s1.equals("0102") || s1.equals("0103"))
        {
            SharedPreferences sharedpreferences = getSharedPreferences("EXTRACT", 0);
            String s2 = sharedpreferences.getString("shown", null);
            if (s2 == null || !s2.equals(s))
            {
                Toast.makeText(this, getString(0x7f0d0350), 1).show();
                sharedpreferences.edit().putString("shown", s).commit();
            }
        }
    }

    private void showTimeline()
    {
        jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownTimelineView.CountdownTimelineListener countdowntimelinelistener;
        if (timelineBody == null)
        {
            timelineBody = (HorizontalScrollView)findViewById(0x7f0a01b9);
            timelineBody.setOnTouchListener(new android.view.View.OnTouchListener() {

                final CountdownActivity this$0;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    switch (motionevent.getAction())
                    {
                    case 2: // '\002'
                    default:
                        return false;

                    case 0: // '\0'
                        isScrollingTimeline = true;
                        return false;

                    case 1: // '\001'
                    case 3: // '\003'
                        isScrollingTimeline = false;
                        break;
                    }
                    return false;
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            });
        } else
        {
            timelineBody.removeAllViews();
        }
        countdowntimelinelistener = new jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownTimelineView.CountdownTimelineListener() {

            final CountdownActivity this$0;

            public void onClickDia(int i)
            {
                isClickedDia = true;
                objCountdown.changeTargetIndex(i);
            }

            public void onFinishShow(int i)
            {
                objCountdown.changeTargetIndex(i);
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        };
        timelineView = new CountdownTimelineView(this);
        timelineView.setListener(countdowntimelinelistener);
        timelineView.initView(objCountdown.getNowTimetables(), objCountdown.getTargetTime());
        timelineBody.addView(timelineView, -2, -1);
        timelineBody.setVisibility(0);
    }

    private void updateTimelineTarget(TimeTableItemData timetableitemdata)
    {
        if (timelineView != null)
        {
            int i = timelineView.updateTarget(timetableitemdata);
            if (isClickedDia)
            {
                isClickedDia = false;
                return;
            }
            if (!isScrollingTimeline && i != -1)
            {
                timelineBody.smoothScrollTo(i, 0);
                return;
            }
        }
    }

    public void changeActivity(int i)
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        intent1.putExtra(getString(0x7f0d0247), i);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003b));
        finish();
        activityAnimate(i);
    }

    public void changeActivityToggle()
    {
        res.getInteger(0x7f0c0074);
        int i;
        String s;
        if (type == res.getInteger(0x7f0c0074))
        {
            i = res.getInteger(0x7f0c0073);
            s = getString(0x7f0d04c2);
        } else
        {
            i = res.getInteger(0x7f0c0074);
            s = getString(0x7f0d04c3);
        }
        if (db.count(i) < 1)
        {
            showSettingDialog(false, s);
            return;
        } else
        {
            changeActivity(i);
            return;
        }
    }

    public void changeSkin(jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata)
    {
        if (skinmenudata.sId.equals(meta.sId)) goto _L2; else goto _L1
_L1:
        if (!skinmenudata.isDownloaded) goto _L4; else goto _L3
_L3:
        skindb.updateSetting(skinmenudata.sId);
        restartCountdown();
_L6:
        return;
_L4:
        launchSettingSkin();
        return;
_L2:
        if (skinmenudata.sId.equals(getString(0x7f0d04e5)))
        {
            Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingAlbumListActivity);
            intent1.putExtra(getString(0x7f0d0236), meta);
            startActivityForResult(intent1, getResources().getInteger(0x7f0c005a));
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean changeWeek(int i)
    {
        showWeekDialog(i);
        return false;
    }

    public void changeWeekActivity(int i)
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        if (fromStation.getTimetable().getBundle(Integer.toString(i)).size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d0132), getString(0x7f0d015e), getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                final CountdownActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    showWeekDialog(week);
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            });
            return;
        }
        if (type == res.getInteger(0x7f0c0072))
        {
            Bundle bundle = fromStation.getTimetable();
            fromStation.setTimetable(null);
            intent1.putExtra(getString(0x7f0d023e), fromStation);
            intent1.putExtra(getString(0x7f0d0245), bundle);
        }
        intent1.putExtra(getString(0x7f0d0247), type);
        intent1.putExtra(getString(0x7f0d024c), i);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003b));
        finish();
        activityAnimate(type);
    }

    public boolean end(TimeTableItemData timetableitemdata)
    {
        return false;
    }

    public void finish()
    {
        if (objCountdown != null)
        {
            objCountdown.setListener(null);
            objCountdown.stop();
        }
        super.finish();
        if (skin != null)
        {
            skin.clearImage();
        }
    }

    public String getSkinId()
    {
        return meta.sId;
    }

    public boolean launchCountdownList()
    {
        Bundle bundle = timetables;
        if (bundle == null || bundle.size() < 1)
        {
            onNoTimetable();
            return false;
        }
        if (objCountdown.getTargetTime() == null)
        {
            onAllFiltered();
            return false;
        } else
        {
            Intent intent1 = new Intent(context, jp/co/yahoo/android/apps/transit/timer/CountdownListActivity);
            intent1.putExtra(getString(0x7f0d024c), week);
            intent1.putExtra(getString(0x7f0d0247), type);
            startActivityForResult(intent1, getResources().getInteger(0x7f0c003b));
            return true;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent1)
    {
        super.onActivityResult(i, j, intent1);
        if (i == getResources().getInteger(0x7f0c003b))
        {
            supportInvalidateOptionsMenu();
        } else
        {
            if (i != res.getInteger(0x7f0c005c))
            {
                continue;
            }
            if (j == -1)
            {
                String s = intent1.getStringExtra(getString(0x7f0d01c5));
                if (!CountdownUtil.isEmpty(s) && !s.equals(meta.sId))
                {
                    jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata = new jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData();
                    skinmenudata.sId = s;
                    skinmenudata.isDownloaded = true;
                    changeSkin(skinmenudata);
                    return;
                }
            } else
            {
                supportInvalidateOptionsMenu();
                return;
            }
        }
        do
        {
            return;
        } while (i != res.getInteger(0x7f0c005a) || j != -1);
        SkinMetaData skinmetadata = (SkinMetaData)intent1.getSerializableExtra(getString(0x7f0d0236));
        skinmetadata.isDownloaded = true;
        skindb.updateDownload(skinmetadata);
        restartCountdown();
    }

    public boolean onAllFiltered()
    {
        showDialog = true;
        showErrorMessageDialog(getString(0x7f0d0131), getString(0x7f0d015e), new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                showFilterDialog(new View(CountdownActivity.this));
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        prev.setVisibility(8);
        next.setVisibility(8);
        return false;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (skindb == null)
        {
            skindb = new SkinDb(this);
        }
        meta = skindb.getSkin();
        checkSkin(meta);
        setLayout();
        setTitle(getString(0x7f0d0349));
        (new Holiday(this)).update();
        res = getResources();
        db = new SettingDb(this);
        alerm = new Alerm(this);
        context = this;
        intent = getIntent();
        Uri uri = intent.getData();
        start = (AlermData)intent.getSerializableExtra(getString(0x7f0d023d));
        type = intent.getIntExtra(getString(0x7f0d0247), -1);
        week = intent.getIntExtra(getString(0x7f0d024c), -1);
        timetableId = intent.getIntExtra(getString(0x7f0d0246), -1);
        startTime = intent.getStringExtra(getString(0x7f0d023c));
        if (start != null)
        {
            StationData stationdata = db.getTimetableById(start.getTimetableId());
            window_option = 0x680080;
            if (stationdata != null)
            {
                fromStation = stationdata;
                type = stationdata.getSettingType();
            }
            (new RingAlerm(this, null)).startAlerm(start, true);
        }
        if ("android.intent.action.VIEW".equals(getIntent().getAction()) && uri != null)
        {
            type = res.getInteger(0x7f0c0075);
            analyzeUri(uri);
        } else
        {
            if (type != res.getInteger(0x7f0c0072))
            {
                continue;
            }
            fromStation = db.getTimetable(res.getInteger(0x7f0c0072), 0, -1);
        }
label0:
        do
        {
            do
            {
                SettingShortcut settingshortcut = new SettingShortcut(this);
                if (!settingshortcut.isShowed())
                {
                    showDialog = true;
                    settingshortcut.showCreate(0x7f0d00b7, 0x7f0d00b5);
                }
                showExtractToast();
                getWindow().addFlags(window_option);
                settingData(type);
                if (fromStation == null)
                {
                    if (toStation == null)
                    {
                        break label0;
                    }
                    showDialog = true;
                    type = toStation.getSettingType();
                    settingData(type);
                    (new Handler()).post(new Runnable() {

                        final CountdownActivity this$0;

                        public void run()
                        {
                            changeActivityToggle();
                        }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
                    });
                }
                setAlerm();
                settingBtnAction();
                showCountdown();
                if (isSkin)
                {
                    showSkin();
                } else
                {
                    showTimeline();
                }
                if (!showDialog && intent.getBooleanExtra(getString(0x7f0d01bf), false))
                {
                    (new RequestRecommend(context)).request();
                }
                showDialog = false;
                return;
            } while (type == res.getInteger(0x7f0c0074) || type == res.getInteger(0x7f0c0073));
            type = getCountdownType();
            if (type < 0)
            {
                launchSettingTop();
                return;
            }
        } while (true);
        launchSettingTop();
    }

    public void onDestroy()
    {
        if (objCountdown != null)
        {
            objCountdown.setListener(null);
            objCountdown.stop();
        }
        super.onDestroy();
        getWindow().clearFlags(window_option);
    }

    public boolean onDoubleTap(MotionEvent motionevent)
    {
        if (nDoubleTap == 0)
        {
            toCurrent(mViewPager);
        } else
        {
            if (nDoubleTap == 1)
            {
                toStart(mViewPager);
                return true;
            }
            if (nDoubleTap == 2)
            {
                toEnd(mViewPager);
                return true;
            }
        }
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            setResult(0);
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    public boolean onNoTimetable()
    {
        showDialog = true;
        showErrorMessageDialog(getString(0x7f0d0132), getString(0x7f0d015e), getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                showWeekDialog(week);
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        prev.setVisibility(8);
        next.setVisibility(8);
        return false;
    }

    protected void onPause()
    {
        super.onPause();
        isPausing = true;
    }

    public void onRestart()
    {
        super.onRestart();
        if (type != res.getInteger(0x7f0c0075) && type != res.getInteger(0x7f0c0072))
        {
            changeActivity(getCountdownType());
            return;
        }
        String s = skindb.getSkinId();
        if (s != null && meta != null && !s.equals(meta.sId))
        {
            restartCountdown();
            return;
        } else
        {
            supportInvalidateOptionsMenu();
            return;
        }
    }

    protected void onResume()
    {
        super.onResume();
        isPausing = false;
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return false;
    }

    public void onStart()
    {
        super.onStart();
        if (type == res.getInteger(0x7f0c0074))
        {
            dispAd(this, "2080325041", "pv");
            return;
        }
        if (type == res.getInteger(0x7f0c0073))
        {
            dispAd(this, "2080325042", "pv");
            return;
        }
        if (type == res.getInteger(0x7f0c0075))
        {
            dispAd(this, "2080325043", "pv");
            return;
        } else
        {
            dispAd(this, "2080325044", "pv");
            return;
        }
    }

    public void onStop()
    {
        super.onStop();
        if (skin != null)
        {
            skin.clearImage();
        }
    }

    protected void setAlermOff()
    {
        for (int i = 0; i < mViewPager.getChildCount(); i++)
        {
            CountdownPanelView countdownpanelview = (CountdownPanelView)mViewPager.getChildAt(i);
            if (countdownpanelview != null)
            {
                countdownpanelview.setAlertLabel(false);
            }
        }

    }

    protected void setLayout()
    {
        if (isSkin)
        {
            setContentView(0x7f03004f);
            return;
        } else
        {
            setContentView(0x7f03004c);
            return;
        }
    }

    protected void settingFilter(final String sFilter, int i, final boolean bDest)
    {
        if (sFilter != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (i <= sFilter.split(",").length)
        {
            showErrorMessageDialog(getString(0x7f0d011b), getString(0x7f0d015e), new android.content.DialogInterface.OnClickListener() {

                final CountdownActivity this$0;
                final boolean val$bDest;

                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    if (bDest)
                    {
                        showFilterDestDialog();
                        return;
                    } else
                    {
                        showFilterKindDialog();
                        return;
                    }
                }

            
            {
                this$0 = CountdownActivity.this;
                bDest = flag;
                super();
            }
            });
            return;
        }
        Bundle bundle;
        if (bDest)
        {
            bundle = CountdownUtil.filter(timetables.getBundle("timetable"), sFilter, fromStation.getTimetable().getString("filter_kind"));
        } else
        {
            bundle = CountdownUtil.filter(timetables.getBundle("timetable"), fromStation.getTimetable().getString("filter_dest"), sFilter);
        }
        if (bundle == null || bundle.size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d0131), getString(0x7f0d015e), new android.content.DialogInterface.OnClickListener() {

                final CountdownActivity this$0;
                final boolean val$bDest;

                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    if (bDest)
                    {
                        showFilterDestDialog();
                        return;
                    } else
                    {
                        showFilterKindDialog();
                        return;
                    }
                }

            
            {
                this$0 = CountdownActivity.this;
                bDest = flag;
                super();
            }
            });
            return;
        }
        ArrayList arraylist = alerm.getAlarmByTimetableId(Integer.parseInt(fromStation.getId()), AlermData.TYPE_ALERT);
        if (arraylist != null && arraylist.size() > 0)
        {
            final AlermData alermdata = (AlermData)arraylist.get(0);
            int j = ((AlermData)arraylist.get(0)).getStartTime();
            int k = 0;
label0:
            do
            {
label1:
                {
                    int l = bundle.size();
                    boolean flag = false;
                    if (k < l)
                    {
                        TimeTableItemData timetableitemdata = (TimeTableItemData)bundle.getSerializable(Integer.toString(k));
                        if (60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute() != j)
                        {
                            break label1;
                        }
                        flag = true;
                    }
                    if (!flag)
                    {
                        String s = getString(0x7f0d0034);
                        String s1 = getString(0x7f0d0257);
                        android.content.DialogInterface.OnClickListener onclicklistener = new android.content.DialogInterface.OnClickListener() {

                            final CountdownActivity this$0;
                            final AlermData val$alermdata;
                            final boolean val$bDest;
                            final String val$sFilter;

                            public void onClick(DialogInterface dialoginterface, int i1)
                            {
                                alerm.delAlarm(alermdata);
                                setAlerm();
                                Bundle bundle2 = fromStation.getTimetable();
                                if (bDest)
                                {
                                    bundle2.putString("filter_dest", sFilter);
                                } else
                                {
                                    bundle2.putString("filter_kind", sFilter);
                                }
                                fromStation.setTimetable(bundle2);
                                db.updateTimetable(fromStation, bundle2);
                                showCountdown();
                            }

            
            {
                this$0 = CountdownActivity.this;
                alermdata = alermdata1;
                bDest = flag;
                sFilter = s;
                super();
            }
                        };
                        android.content.DialogInterface.OnClickListener onclicklistener1 = new android.content.DialogInterface.OnClickListener() {

                            final CountdownActivity this$0;
                            final boolean val$bDest;

                            public void onClick(DialogInterface dialoginterface, int i1)
                            {
                                if (bDest)
                                {
                                    showFilterDestDialog();
                                    return;
                                } else
                                {
                                    showFilterKindDialog();
                                    return;
                                }
                            }

            
            {
                this$0 = CountdownActivity.this;
                bDest = flag;
                super();
            }
                        };
                        showErrorMessageDialog(s, s1, onclicklistener, onclicklistener1);
                        return;
                    }
                    break label0;
                }
                k++;
            } while (true);
        }
        Bundle bundle1 = fromStation.getTimetable();
        if (bDest)
        {
            bundle1.putString("filter_dest", sFilter);
        } else
        {
            bundle1.putString("filter_kind", sFilter);
        }
        fromStation.setTimetable(bundle1);
        db.updateTimetable(fromStation, bundle1);
        setAlerm();
        showCountdown();
        if (!isSkin)
        {
            resetTimeline();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void showAlertDialog(View view)
    {
        touchTapRD(getString(0x7f0d03c3));
        TimeTableItemData timetableitemdata = objCountdown.getTargetTime();
        if (timetableitemdata == null)
        {
            return;
        }
        int i = 60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute();
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        time.setToNow();
        int j = time.hour;
        if (j <= 3)
        {
            j += 24;
        }
        if (60 * (j * 60) + 60 * time.minute > i)
        {
            showErrorMessageDialog(getString(0x7f0d0101), getString(0x7f0d015e));
            return;
        }
        if (startAlerm == null)
        {
            showAlertDialogNew();
            return;
        } else
        {
            showAlertEditDialogNew();
            return;
        }
    }

    public void showAlertDialogNew()
    {
        String s = getString(0x7f0d025a);
        final String items[] = getResources().getStringArray(0x7f070001);
        String as[] = new String[items.length];
        for (int i = 0; i < as.length; i++)
        {
            as[i] = (new StringBuilder()).append(items[i]).append(getString(0x7f0d025d)).toString();
        }

        showSingleChoiceListDialog(s, 0, null, as, 0, 0x7f0d007e, new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity this$0;
            final String val$items[];

            public void onClick(DialogInterface dialoginterface, int j)
            {
                String s1 = items[j];
                TimeTableItemData timetableitemdata = objCountdown.getTargetTime();
                int k = (60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute()) - 60 * Integer.parseInt(s1);
                Time time = new Time("Asia/Tokyo");
                time.setToNow();
                int l = time.hour;
                if (l <= 3)
                {
                    l += 24;
                }
                if (k <= 60 * (l * 60) + 60 * time.minute)
                {
                    showErrorMessageDialog(getString(0x7f0d0101), getString(0x7f0d015e));
                    return;
                }
                alerm.delAlarmByTime(AlermData.TYPE_ALERT);
                if (fromStation.getSettingType() == res.getInteger(0x7f0c0075) || fromStation.getSettingType() == res.getInteger(0x7f0c0072))
                {
                    fromStation.setId(Integer.toString(db.addTempSetting(fromStation)));
                }
                AlermData alermdata = new AlermData();
                String s2 = fromStation.getRailname();
                String s3 = fromStation.getDirname();
                String s4 = fromStation.getName();
                if (s3 != null && !s3.equals(""))
                {
                    s2 = (new StringBuilder()).append(s2).append(" ").append(s3).append(getString(0x7f0d0275)).append(" ").toString();
                }
                if (s4 != null && !s4.equals(""))
                {
                    s2 = (new StringBuilder()).append(s2).append(" ").append(s4).append(getString(0x7f0d0304)).toString();
                }
                alermdata.setStartTime(60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute());
                alermdata.setTimetableId(Integer.parseInt(fromStation.getId()));
                alermdata.setRepeat("0");
                alermdata.setSetting(true);
                alermdata.setType(AlermData.TYPE_ALERT);
                alermdata.setLine(s2);
                alermdata.setCountdownTime(Integer.parseInt(s1));
                alerm.setAlerm(alermdata, false);
                startAlerm = alerm.getCountdownAlerm();
                if (objCountdown.getNowPanel() != null)
                {
                    objCountdown.getNowPanel().setAlertLabel(true);
                }
                objCountdown.setAlermTime(alermdata.getStartTime());
                setAlerm();
            }

            
            {
                this$0 = CountdownActivity.this;
                items = as;
                super();
            }
        });
    }

    protected void showCountdown()
    {
        reload = (ImageView)findViewById(0x7f0a01d1);
        mViewPager = (ViewPager)findViewById(0x7f0a01c1);
        prev = (ImageView)findViewById(0x7f0a01c3);
        next = (ImageView)findViewById(0x7f0a01c4);
        TextView textview = (TextView)findViewById(0x7f0a01bb);
        TextView textview1 = (TextView)findViewById(0x7f0a01bc);
        TextView textview2 = (TextView)findViewById(0x7f0a01bd);
        if (fromStation.getRailname() != null)
        {
            textview.setText(fromStation.getRailname());
        }
        if (fromStation.getDirname() != null)
        {
            textview1.setText((new StringBuilder()).append(fromStation.getDirname()).append(getString(0x7f0d0275)).toString());
        }
        if (fromStation.getName() != null)
        {
            textview2.setText(fromStation.getName());
        }
        if (objCountdown != null)
        {
            objCountdown.setListener(null);
            objCountdown.stop();
        }
        objCountdown = new CountdownPanelManager(this, mViewPager, this, isSkin);
        objCountdown.setWeek(week);
        if (start == null) goto _L2; else goto _L1
_L1:
        int k = start.getStartTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(11, k / 3600);
        calendar1.set(12, -1 + (k % 3600) / 60);
        calendar1.set(13, 0);
        calendar1.set(14, 0);
        Time time1 = new Time("Asia/Tokyo");
        time1.set(calendar1.getTimeInMillis());
        objCountdown.setTime(time1);
_L4:
        if (fromStation.getSettingType() == res.getInteger(0x7f0c0075))
        {
            objCountdown.setWeekVisible(false);
        }
        objCountdown.setCountDown(fromStation.getTimetable());
        objCountdown.startCountDown();
        if (startAlerm != null)
        {
            objCountdown.setAlermTime(startAlerm.getStartTime());
        }
        timetables = objCountdown.getTodayTimetables();
        if (timetableId != -1)
        {
            objCountdown.changeTargetIndex(timetableId);
        }
        gesture = new GestureDetector(this, this);
        mViewPager.setOnTouchListener(new android.view.View.OnTouchListener() {

            final CountdownActivity this$0;

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                nDoubleTap = 0;
                if (gesture.onTouchEvent(motionevent))
                {
                    return true;
                } else
                {
                    return onTouchEvent(motionevent);
                }
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        prev.setOnTouchListener(new android.view.View.OnTouchListener() {

            final CountdownActivity this$0;

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                nDoubleTap = 1;
                if (gesture.onTouchEvent(motionevent))
                {
                    return true;
                } else
                {
                    return onTouchEvent(motionevent);
                }
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        next.setOnTouchListener(new android.view.View.OnTouchListener() {

            final CountdownActivity this$0;

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                nDoubleTap = 2;
                if (gesture.onTouchEvent(motionevent))
                {
                    return true;
                } else
                {
                    return onTouchEvent(motionevent);
                }
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        return;
_L2:
        if (!CountdownUtil.isEmpty(startTime))
        {
            int i = Integer.parseInt(startTime.substring(0, 2));
            if (i > 23)
            {
                i -= 24;
            }
            int j = Integer.parseInt(startTime.substring(2, 4));
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, i);
            calendar.set(12, j - 1);
            calendar.set(13, 0);
            calendar.set(14, 0);
            Time time = new Time("Asia/Tokyo");
            time.set(calendar.getTimeInMillis());
            objCountdown.setTime(time);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void showFilterDestDialog()
    {
        CheckListDialog checklistdialog = new CheckListDialog(this);
        Bundle bundle = timetables.getBundle("dest");
        if (bundle != null)
        {
            if (bundle.size() >= 1);
        }
        Bundle bundle1 = bundle.getBundle("dest");
        if (bundle1 != null)
        {
            if (bundle1.size() >= 1);
        }
        String s = fromStation.getTimetable().getString("filter_dest");
        if (s == null)
        {
            s = "";
        }
        String as[] = s.split(",");
        String as1[] = CountdownUtil.getMargeDest(fromStation.getTimetable(), "dest", "info");
        boolean aflag[] = new boolean[as1.length];
        final int nSize = as1.length;
        int i = 0;
        while (i < as1.length) 
        {
            if (as.length < 0)
            {
                aflag[i] = true;
            } else
            if (Arrays.asList(as).contains(as1[i]))
            {
                aflag[i] = false;
            } else
            {
                aflag[i] = true;
            }
            i++;
        }
        checklistdialog.setTitle(getString(0x7f0d0281));
        checklistdialog.setCheck(aflag);
        checklistdialog.setResponseChecked(false);
        checklistdialog.setItems(as1, as1);
        checklistdialog.showDilaog(new jp.co.yahoo.android.apps.transit.timer.viewparts.CheckListDialog.DialogListener() {

            final CountdownActivity this$0;
            final int val$nSize;

            public void onCancel()
            {
            }

            public void onOk(String s1)
            {
                settingFilter(s1, nSize, true);
            }

            
            {
                this$0 = CountdownActivity.this;
                nSize = i;
                super();
            }
        });
    }

    public void showFilterDialog(View view)
    {
        touchTapRD(getString(0x7f0d03e1));
        Bundle bundle = timetables.getBundle("dest");
        if (bundle == null || bundle.size() < 1)
        {
            return;
        }
        Bundle bundle1 = bundle.getBundle("dest");
        Bundle bundle2 = bundle.getBundle("kind");
        if (bundle1 != null && bundle1.size() > 0 && (bundle2 == null || bundle2.size() < 2))
        {
            showFilterDestDialog();
            return;
        }
        if (bundle2 != null && bundle2.size() > 0 && (bundle1 == null || bundle1.size() < 2))
        {
            showFilterKindDialog();
            return;
        } else
        {
            TransitDialogBuilder transitdialogbuilder = (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d0280));
            CharSequence acharsequence[] = new CharSequence[2];
            acharsequence[0] = (new StringBuilder()).append(getString(0x7f0d0282)).append(getString(0x7f0d0280)).toString();
            acharsequence[1] = (new StringBuilder()).append(getString(0x7f0d0281)).append(getString(0x7f0d0280)).toString();
            transitdialogbuilder.setItems(acharsequence, new android.content.DialogInterface.OnClickListener() {

                final CountdownActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if (i == 0)
                    {
                        showFilterKindDialog();
                        return;
                    } else
                    {
                        showFilterDestDialog();
                        return;
                    }
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0d0071, new android.content.DialogInterface.OnClickListener() {

                final CountdownActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    protected void showFilterKindDialog()
    {
        CheckListDialog checklistdialog = new CheckListDialog(this);
        Bundle bundle = timetables.getBundle("dest");
        if (bundle != null)
        {
            if (bundle.size() >= 1);
        }
        Bundle bundle1 = bundle.getBundle("kind");
        if (bundle1 != null)
        {
            if (bundle1.size() >= 1);
        }
        String s = fromStation.getTimetable().getString("filter_kind");
        if (s == null)
        {
            s = "";
        }
        String as[] = s.split(",");
        String as1[] = CountdownUtil.getMargeDest(fromStation.getTimetable(), "kind", "info");
        boolean aflag[] = new boolean[as1.length];
        final int nSize = as1.length;
        int i = 0;
        while (i < as1.length) 
        {
            if (as.length < 1)
            {
                aflag[i] = true;
            } else
            if (Arrays.asList(as).contains(as1[i]))
            {
                aflag[i] = false;
            } else
            {
                aflag[i] = true;
            }
            i++;
        }
        checklistdialog.setTitle(getString(0x7f0d0282));
        checklistdialog.setCheck(aflag);
        checklistdialog.setResponseChecked(false);
        checklistdialog.setItems(as1, as1);
        checklistdialog.showDilaog(new jp.co.yahoo.android.apps.transit.timer.viewparts.CheckListDialog.DialogListener() {

            final CountdownActivity this$0;
            final int val$nSize;

            public void onCancel()
            {
            }

            public void onOk(String s1)
            {
                settingFilter(s1, nSize, false);
            }

            
            {
                this$0 = CountdownActivity.this;
                nSize = i;
                super();
            }
        });
    }

    protected void showSkin()
    {
        if (meta.sId.equals(getString(0x7f0d04e9)))
        {
            ((ImageView)findViewById(0x7f0a01d2)).setVisibility(0);
        }
        allbody = (FrameLayout)findViewById(0x7f0a01b7);
        animIn = AnimationUtils.loadAnimation(context, 0x7f04000a);
        animOut = AnimationUtils.loadAnimation(context, 0x7f04000b);
        animOut.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            final CountdownActivity this$0;

            public void onAnimationEnd(Animation animation)
            {
                if (panelbody != null)
                {
                    panelbody.setVisibility(8);
                }
                if (changeBtn != null)
                {
                    changeBtn.setVisibility(8);
                }
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        animOutFull = AnimationUtils.loadAnimation(context, 0x7f04000b);
        animOutFull.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            final CountdownActivity this$0;

            public void onAnimationEnd(Animation animation)
            {
                fullScreenBody.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        skin = new SkinDrawableManager(this, meta, allbody);
        panelbody = (LinearLayout)allbody.findViewById(0x7f0a01b8);
        skin.setOnClickListener(new android.view.View.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(View view)
            {
                if (panelbody.getVisibility() == 8)
                {
                    getSupportActionBar().show();
                    skin.setLock(true);
                    skin.setStart(true);
                    panelbody.startAnimation(animIn);
                    panelbody.setVisibility(0);
                    if (changeBtn != null)
                    {
                        changeBtn.startAnimation(animIn);
                        changeBtn.setVisibility(0);
                    }
                    fullScreenBody.startAnimation(animOutFull);
                } else
                if (panelbody.getVisibility() == 0)
                {
                    getSupportActionBar().hide();
                    skin.setLock(false);
                    skin.setStart(false);
                    panelbody.startAnimation(animOut);
                    if (changeBtn != null)
                    {
                        changeBtn.startAnimation(animOut);
                    }
                    fullScreenBody.startAnimation(animIn);
                    fullScreenBody.setVisibility(0);
                    fullScreenBody.post(new Runnable() {

                        final _cls11 this$1;

                        public void run()
                        {
                            if (dragView.getVisibility() == 4)
                            {
                                SharedPreferences sharedpreferences = getSharedPreferences(getString(0x7f0d01a0), 0);
                                int i = sharedpreferences.getInt(getString(0x7f0d01b7), 0xff676981);
                                int j = sharedpreferences.getInt(getString(0x7f0d01b8), 0xff676981);
                                if (i != 0xff676981 && j != 0xff676981)
                                {
                                    setDragViewPosition(i, j);
                                }
                                dragView.setVisibility(0);
                            }
                        }

            
            {
                this$1 = _cls11.this;
                super();
            }
                    });
                    objCountdown.updateSkinDragViewItem();
                    return;
                }
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        fullScreenBody = (DragAndDropLayout)allbody.findViewById(0x7f0a01e0);
        dragView = fullScreenBody.findViewById(0x7f0a01e1);
        fullScreenBody.setDragView(dragView, new jp.co.yahoo.android.apps.transit.common.DragAndDropLayout.DragAndDropListener() {

            final CountdownActivity this$0;

            public void onDrop(int i, int j)
            {
                setDragViewPosition(i, j);
                android.content.SharedPreferences.Editor editor = getSharedPreferences(getString(0x7f0d01a0), 0).edit();
                editor.putInt(getString(0x7f0d01b7), i);
                editor.putInt(getString(0x7f0d01b8), j);
                editor.commit();
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
        objCountdown.setSkinDragView(dragView, fromStation.getRailname());
        if (meta.sId.equals(getString(0x7f0d04e5)))
        {
            BackgroundWorker.post(new jp.co.yahoo.android.apps.transit.common.BackgroundWorker.WorkerListener() {

                final CountdownActivity this$0;
                final Activity val$activity;

                public boolean doInBackground()
                {
                    jp.co.yahoo.android.apps.transit.common.util.FileUtil.MediaData mediadata = FileUtil.getDirectoryImageData(activity, true, meta.sDownloadUrl);
                    if (mediadata.listPath != null && !mediadata.listPath.isEmpty())
                    {
                        skin.setSkinAlbumData(mediadata.listPath);
                    }
                    return true;
                }

                public void onPostExecute()
                {
                    if (meta.nCount > 0)
                    {
                        skin.startTimer();
                    }
                }

            
            {
                this$0 = CountdownActivity.this;
                activity = activity1;
                super();
            }
            });
            return;
        } else
        {
            skin.startTimer();
            return;
        }
    }

    public void showWeather()
    {
        if (toStation == null) goto _L2; else goto _L1
_L1:
        StationData stationdata = toStation;
_L6:
        Animation animation = AnimationUtils.loadAnimation(this, 0x7f04000e);
        animation.setRepeatMode(1);
        reload.startAnimation(animation);
        final String sName = stationdata.getName();
        WeatherSearch weathersearch = new WeatherSearch(this);
        weathersearch.setAreaCode(stationdata.getGovernmentCode());
        weathersearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final CountdownActivity this$0;
            final String val$sName;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                reload.clearAnimation();
                TextView textview = (TextView)findViewById(0x7f0a01cd);
                textview.setText("\u30FC");
                TextView textview1 = (TextView)findViewById(0x7f0a01ce);
                textview1.setText("\u30FC");
                TextView textview2 = (TextView)findViewById(0x7f0a01cf);
                textview2.setText("\u30FC");
                if (!(apibase.getResult() instanceof WeatherData))
                {
                    return false;
                }
                WeatherData weatherdata = (WeatherData)apibase.getResult();
                if (weatherdata == null)
                {
                    return false;
                }
                ((TextView)findViewById(0x7f0a01cb)).setText(sName);
                ImageView imageview = (ImageView)findViewById(0x7f0a01cc);
                try
                {
                    InputStream inputstream = (new URL(weatherdata.getIcon())).openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputstream);
                    imageview.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 2 * bitmap.getWidth(), 2 * bitmap.getHeight(), true));
                    inputstream.close();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                textview.setText(weatherdata.getPrecip());
                textview1.setText(weatherdata.getMaxTemp());
                textview2.setText(weatherdata.getMinTemp());
                ((LinearLayout)findViewById(0x7f0a01ca)).setOnClickListener(weatherdata. new android.view.View.OnClickListener() {

                    final _cls14 this$1;
                    final WeatherData val$data;

                    public void onClick(View view)
                    {
                        String s = data.getUrl();
                        if (CountdownUtil.isEmpty(s))
                        {
                            return;
                        } else
                        {
                            String.format(s, new Object[0]);
                            return;
                        }
                    }

            
            {
                this$1 = final__pcls14;
                data = WeatherData.this;
                super();
            }
                });
                return false;
            }

            
            {
                this$0 = CountdownActivity.this;
                sName = s;
                super();
            }
        }, false);
        ((LinearLayout)findViewById(0x7f0a01d0)).setOnClickListener(new android.view.View.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(View view)
            {
                Animation animation1 = AnimationUtils.loadAnimation(CountdownActivity.this, 0x7f04000e);
                animation1.setRepeatMode(1);
                reload.startAnimation(animation1);
                showWeather();
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
_L4:
        return;
_L2:
        if (fromStation == null) goto _L4; else goto _L3
_L3:
        stationdata = fromStation;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void showWeekDialog(int i)
    {
        String s = getString(0x7f0d0593);
        byte byte0;
        String as[];
        if (i == 4)
        {
            byte0 = 2;
        } else
        if (i == 2)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        as = new String[3];
        as[0] = getString(0x7f0d0595);
        as[1] = getString(0x7f0d0596);
        as[2] = getString(0x7f0d0594);
        showSingleChoiceListDialog(s, 0, null, as, byte0, 0x7f0d007f, new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                int k;
                if (j == 0)
                {
                    k = 1;
                } else
                if (j == 1)
                {
                    k = 2;
                } else
                {
                    k = 4;
                }
                changeWeekActivity(k);
            }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
        });
    }

    public void toCurrent(View view)
    {
        objCountdown.changeNow();
    }

    public void toEnd(View view)
    {
        objCountdown.changeEnd();
    }

    public void toNext(View view)
    {
        objCountdown.changeNext();
    }

    public void toPrev(View view)
    {
        objCountdown.changePrev();
    }

    public void toStart(View view)
    {
        objCountdown.changeTargetIndex(0);
    }

    public void updateTarget(TimeTableItemData timetableitemdata)
    {
        if (timetableitemdata != null)
        {
            if (timetableitemdata.isFirstStation() || timetableitemdata.getIndex() == 0)
            {
                prev.setVisibility(8);
            } else
            {
                prev.setVisibility(0);
            }
            if (timetableitemdata.isLastStation() || timetableitemdata.getIndex() == -1 + objCountdown.getMaxCount())
            {
                next.setVisibility(8);
            } else
            {
                next.setVisibility(0);
            }
            if (isSkin)
            {
                if (fullScreenBody != null && fullScreenBody.getVisibility() == 0)
                {
                    objCountdown.updateSkinDragViewItem();
                    return;
                }
            } else
            {
                updateTimelineTarget(timetableitemdata);
                return;
            }
        }
    }

    public void updateTime(int i)
    {
        if (!isSkin) goto _L2; else goto _L1
_L1:
        if (!isPausing && skin != null) goto _L4; else goto _L3
_L3:
        return;
_L4:
        skin.setNextDrawable(i);
        return;
_L2:
        if (timelineView != null)
        {
            timelineView.updateTime(i);
            return;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }
















/*
    static AlermData access$2002(CountdownActivity countdownactivity, AlermData alermdata)
    {
        countdownactivity.startAlerm = alermdata;
        return alermdata;
    }

*/



/*
    static boolean access$2202(CountdownActivity countdownactivity, boolean flag)
    {
        countdownactivity.isScrollingTimeline = flag;
        return flag;
    }

*/


/*
    static boolean access$2302(CountdownActivity countdownactivity, boolean flag)
    {
        countdownactivity.isClickedDia = flag;
        return flag;
    }

*/







}
