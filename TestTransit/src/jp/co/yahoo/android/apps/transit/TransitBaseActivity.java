// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import jp.co.yahoo.android.ads.AdResponse;
import jp.co.yahoo.android.ads.AdViewForInstance;
import jp.co.yahoo.android.ads.AdViewListener;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.Alarm;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.SingletonMap;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.kakao.KakaoLink;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.timer.SettingStationActivity;
import jp.co.yahoo.android.apps.transit.timer.SettingTopActivity;
import jp.co.yahoo.android.apps.transit.timer.StationListActivity;
import jp.co.yahoo.android.apps.transit.timer.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.timer.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.SkinMeta;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.android.apps.transit.viewparts.ListItemImageText;
import jp.co.yahoo.android.apps.transit.viewparts.PopupNew;
import jp.co.yahoo.android.apps.transit.viewparts.RuleforPreinstallDialoig;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.common.YAppInfoChecker;
import jp.co.yahoo.android.common.YDistributionUtils;
import jp.co.yahoo.android.common.YStrictMode;
import jp.co.yahoo.android.common.YVersionCheckListener;
import jp.co.yahoo.android.common.agreementlib.YJAgreementHelper;
import jp.co.yahoo.android.common.hamburger.YHBGRd;
import jp.co.yahoo.android.common.hamburger.YHBGRecommendApps;
import jp.co.yahoo.android.common.hamburger.YHBGUtils;
import jp.co.yahoo.android.common.hamburger.YSimpleSideDrawer;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.YolpApiBase;
import jp.co.yahoo.android.yolp.common.YolpError;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MyPageActivity, Transit, HomeDatetimeConditionActivity, SearchResultListActivity, 
//            OtherCandidatesActivity, SearchResultActivity, StationInfo, SearchResultTeikiActivity, 
//            InputSearch, TimeTableTopActivity, TimeTableActivity, DiainfoActivity, 
//            DiainfoDetailActivity, OthersPushDiainfoActivity, OthersActivity, PushDiainfoService, 
//            RailmapActivity

public abstract class TransitBaseActivity extends ActionBarActivity
    implements jp.co.yahoo.android.yolp.common.ApiBase.ApiListener
{
    class AdviewListener
        implements AdViewListener
    {

        final TransitBaseActivity this$0;

        private void sedAdLayout(AdResponse adresponse, RelativeLayout relativelayout)
        {
            if (adresponse != null && relativelayout != null && adresponse.getCode() == "200")
            {
                relativelayout.removeAllViews();
                relativelayout.setPadding(0, 2, 0, 2);
                relativelayout.addView(adresponse.getAdlayout());
            }
        }

        public void onAdViewActivityEnd(Map map)
        {
            if (map.containsKey("pv"))
            {
                sedAdLayout((AdResponse)map.get("pv"), (RelativeLayout)findViewById(0x7f0a006f));
            }
            if (map.containsKey("bottom"))
            {
                sedAdLayout((AdResponse)map.get("bottom"), (RelativeLayout)findViewById(0x7f0a0070));
            }
            if (map.containsKey("top"))
            {
                sedAdLayout((AdResponse)map.get("top"), (RelativeLayout)findViewById(0x7f0a0060));
            }
        }

        public void onAdViewActivityStart()
        {
        }

        AdviewListener()
        {
            this$0 = TransitBaseActivity.this;
            super();
        }
    }


    protected static final int MENU_DIA_INFO = 5;
    protected static final int MENU_HOME = 1;
    protected static final int MENU_MEMO = 3;
    protected static final int MENU_ROUTEMAP = 4;
    protected static final int MENU_SETTING = 6;
    protected static final int MENU_TIMER_AROUND = 8;
    public static final int MENU_TIMER_COUNTDOWN = 7;
    public static final int MENU_TIMER_SETTING = 9;
    protected static final int MENU_TIMETABLE = 2;
    private boolean bError;
    protected Bundle chkDiainfo;
    protected Bundle chkRegistRail;
    private InputMethodManager imm;
    YSimpleSideDrawer mDrawer;
    private Handler mHandler;
    private ProgressDialog m_progDialog;
    private int menuId;
    private int nNowWeek;
    private Bundle objHolidayData;
    private Bundle objOrdinaryData;
    protected RegistSearchSSO objRegistSearch;
    private Bundle objSaturdayData;
    protected ProgressDialog progressDialog;
    private String sRdPath;
    protected BearerToken taken;

    public TransitBaseActivity()
    {
        menuId = 1;
        sRdPath = null;
        imm = null;
        mDrawer = null;
        chkDiainfo = null;
        chkRegistRail = null;
        objRegistSearch = null;
        m_progDialog = null;
        objOrdinaryData = null;
        objSaturdayData = null;
        objHolidayData = null;
        bError = false;
        nNowWeek = -1;
        mHandler = new Handler(new android.os.Handler.Callback() {

            final TransitBaseActivity this$0;

            public boolean handleMessage(Message message)
            {
                if (message.what != 1) goto _L2; else goto _L1
_L1:
                Iterator iterator;
                boolean flag;
                iterator = YHBGRecommendApps.getHambergerEntries(getApplicationContext());
                flag = false;
                LinearLayout linearlayout;
                LayoutInflater layoutinflater;
                linearlayout = (LinearLayout)findViewById(0x7f0a0295);
                linearlayout.setVisibility(0);
                layoutinflater = LayoutInflater.from(TransitBaseActivity.this);
                int i = 0;
_L9:
                if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
                jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry yhambergerentry;
                LinearLayout linearlayout1;
                yhambergerentry = (jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry)iterator.next();
                if (yhambergerentry.mName.equals("\u3082\u3063\u3068\u898B\u308B"))
                {
                    continue; /* Loop/switch isn't completed */
                }
                linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030072, linearlayout, false);
                ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a0221);
                int j = (int)TransitUtil.dpToPx(TransitBaseActivity.this, 32F);
                android.view.ViewGroup.LayoutParams layoutparams = imageview.getLayoutParams();
                layoutparams.width = j;
                layoutparams.height = j;
                imageview.setLayoutParams(layoutparams);
                imageview.setImageBitmap(yhambergerentry.mBitmap);
                ((TextView)linearlayout1.findViewById(0x7f0a0222)).setText(yhambergerentry.mName);
                if (YHBGUtils.getApplicationInfo(getApplicationContext(), yhambergerentry.mPackage) == null) goto _L6; else goto _L5
_L5:
                android.view.View.OnClickListener onclicklistener = yhambergerentry. new android.view.View.OnClickListener() {

                    final _cls3 this$1;
                    final jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry val$entry;

                    public void onClick(View view)
                    {
                        Intent intent = getPackageManager().getLaunchIntentForPackage(entry.mPackage);
                        if (YHBGUtils.startActivity(_fld0, intent))
                        {
                            Context context = getApplicationContext();
                            String as[] = new String[2];
                            as[0] = "kick";
                            as[1] = entry.mPackage;
                            YHBGRd.sendAsync(context, as);
                        }
                    }

            
            {
                this$1 = final__pcls3;
                entry = jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry.this;
                super();
            }
                };
                linearlayout1.setOnClickListener(onclicklistener);
_L7:
                int k = i + 1;
                linearlayout.addView(linearlayout1, i);
                flag = true;
                ImageView imageview1 = (ImageView)layoutinflater.inflate(0x7f030059, null);
                i = k + 1;
                try
                {
                    linearlayout.addView(imageview1, k);
                    continue; /* Loop/switch isn't completed */
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
_L4:
                if (!flag)
                {
                    findViewById(0x7f0a0295).setVisibility(8);
                    return false;
                }
                break; /* Loop/switch isn't completed */
_L6:
                android.view.View.OnClickListener onclicklistener1 = yhambergerentry. new android.view.View.OnClickListener() {

                    final _cls3 this$1;
                    final jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry val$entry;

                    public void onClick(View view)
                    {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(entry.mUrl));
                        if (YHBGUtils.startActivity(_fld0, intent))
                        {
                            Context context = getApplicationContext();
                            String as[] = new String[2];
                            as[0] = "store";
                            as[1] = entry.mPackage;
                            YHBGRd.sendAsync(context, as);
                        }
                    }

            
            {
                this$1 = final__pcls3;
                entry = jp.co.yahoo.android.common.hamburger.YHBGRecommendApps.YHambergerEntry.this;
                super();
            }
                };
                linearlayout1.setOnClickListener(onclicklistener1);
                if (true) goto _L7; else goto _L2
_L2:
                return false;
                if (true) goto _L9; else goto _L8
_L8:
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        });
    }

    private boolean isExistDiainfo(DiainfoData diainfodata, DiainfoData diainfodata1)
    {
        if (diainfodata != null && diainfodata1 != null && diainfodata.getRailCode().equals(diainfodata1.getRailCode()) && diainfodata.getRailRangeCode().equals(diainfodata1.getRailRangeCode()))
        {
            ArrayList arraylist = diainfodata1.getDetailinfo();
            if (arraylist != null && arraylist.size() > 0)
            {
                Iterator iterator = arraylist.iterator();
                while (iterator.hasNext()) 
                {
                    if (!((jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail)iterator.next()).getStatusCode().equals("000200010005"))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isSearchMenu(int i)
    {
        switch (i)
        {
        default:
            return true;

        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            return false;
        }
    }

    private void launchMyrouteEdit(ConditionData conditiondata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/MyPageActivity);
        intent.putExtra(getString(0x7f0d01a5), 257);
        intent.putExtra(getString(0x7f0d0247), 1);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0058));
        intent.putExtra(getString(0x7f0d022c), conditiondata);
        startActivityForResult(intent, getResources().getInteger(0x7f0c004c));
    }

    private void launchStation(Bundle bundle)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/StationListActivity);
        intent.putExtra(getString(0x7f0d0240), bundle);
        intent.putExtra(getString(0x7f0d0247), getResources().getInteger(0x7f0c0072));
        intent.putExtra(getString(0x7f0d0244), getResources().getInteger(0x7f0c003b));
        intent.putExtra(getString(0x7f0d01ca), 8);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0063));
    }

    private void registMyroute(ResultDB resultdb, ConditionData conditiondata, int i, int j)
    {
        String s;
        resultdb.addMyroute(conditiondata);
        s = getString(0x7f0d0360);
        i;
        JVM INSTR tableswitch 1 2: default 36
    //                   1 72
    //                   2 93;
           goto _L1 _L2 _L3
_L1:
        String s1 = getString(0x7f0d00ad, new Object[] {
            s
        });
_L5:
        showMessageDialog(s1, getString(0x7f0d00ac), getString(0x7f0d0074));
        return;
_L2:
        s1 = getString(0x7f0d00af, new Object[] {
            s
        });
        continue; /* Loop/switch isn't completed */
_L3:
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(j);
        s1 = getString(0x7f0d00a9, aobj);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private Intent showCountdown()
    {
        if (this instanceof CountdownActivity)
        {
            ((CountdownActivity)this).changeActivityToggle();
            return null;
        }
        int i = getCountdownType();
        if (i > 0)
        {
            Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
            intent.putExtra(getString(0x7f0d0247), i);
            return intent;
        }
        if (getSharedPreferences(getString(0x7f0d01a0), 0).getBoolean(getString(0x7f0d01bb), true))
        {
            Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingTopActivity);
            intent1.setAction("android.intent.action.VIEW");
            intent1.putExtra(getString(0x7f0d01ca), 9);
            startActivity(intent1);
        } else
        {
            showSettingDialog(false, null);
        }
        return null;
    }

    private void showDeleteMyrouteDialog(final ResultDB sql, final ConditionData condition)
    {
        String s = getString(0x7f0d0153);
        Object aobj[] = new Object[1];
        aobj[0] = sql.getMaxMyroute();
        showSingleChoiceListDialog(s, getString(0x7f0d013d, aobj), 0x7f070008, 0, new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;
            final ConditionData val$condition;
            final ResultDB val$sql;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                StringBuilder stringbuilder = new StringBuilder(getString(0x7f0d0560));
                stringbuilder.append(getString(0x7f0d043f)).append("/");
                stringbuilder.append(getString(0x7f0d0442)).append("/");
                if (i == 0)
                {
                    stringbuilder.append(getString(0x7f0d03e6));
                    touchRD(stringbuilder.toString());
                    registMyroute(sql, condition, 1, 0);
                    return;
                } else
                {
                    stringbuilder.append(getString(0x7f0d03e5));
                    touchRD(stringbuilder.toString());
                    launchMyrouteEdit(condition);
                    return;
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                sql = resultdb;
                condition = conditiondata;
                super();
            }
        }, new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (i == -1)
                {
                    StringBuilder stringbuilder = new StringBuilder(getString(0x7f0d0560));
                    stringbuilder.append(getString(0x7f0d043f)).append("/");
                    stringbuilder.append(getString(0x7f0d0442)).append("/");
                    stringbuilder.append(getString(0x7f0d03e4));
                    touchRD(stringbuilder.toString());
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        });
    }

    private void touchMenuRD(int i)
    {
        StringBuilder stringbuilder;
        if (isSearchMenu(menuId) == isSearchMenu(i))
        {
            return;
        }
        stringbuilder = new StringBuilder(getString(0x7f0d0560));
        if (isSearchMenu(i))
        {
            stringbuilder.append(getString(0x7f0d043d)).append("/");
        } else
        {
            stringbuilder.append(getString(0x7f0d043e)).append("/");
        }
        i;
        JVM INSTR tableswitch 1 9: default 108
    //                   1 138
    //                   2 168
    //                   3 198
    //                   4 183
    //                   5 153
    //                   6 213
    //                   7 228
    //                   8 243
    //                   9 258;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        touchRD(stringbuilder.toString());
        return;
_L2:
        stringbuilder.append(getString(0x7f0d0423));
        continue; /* Loop/switch isn't completed */
_L6:
        stringbuilder.append(getString(0x7f0d0436));
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append(getString(0x7f0d0422));
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuilder.append(getString(0x7f0d0414));
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(getString(0x7f0d03f8));
        continue; /* Loop/switch isn't completed */
_L7:
        stringbuilder.append(getString(0x7f0d0401));
        continue; /* Loop/switch isn't completed */
_L8:
        stringbuilder.append(getString(0x7f0d03e7));
        continue; /* Loop/switch isn't completed */
_L9:
        stringbuilder.append(getString(0x7f0d03e9));
        continue; /* Loop/switch isn't completed */
_L10:
        stringbuilder.append(getString(0x7f0d0417));
        if (true) goto _L1; else goto _L11
_L11:
    }

    protected void chkDiainfo()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            setDiainfo(false);
            return;
        } else
        {
            DiainfoSearch diainfosearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

                final TransitBaseActivity this$0;

                public boolean onCanceled()
                {
                    setDiainfo(false);
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    setDiainfo(false);
                    return false;
                }

                public boolean onSuccess(jp.co.yahoo.android.apps.transit.api.ApiBase apibase, Object obj)
                {
                    chkDiainfo = ((DiainfoSearch)apibase).getResult();
                    if (chkDiainfo != null && chkRegistRail != null)
                    {
                        chkDiainfoDetail();
                        setHumbergarIcon();
                    }
                    return false;
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            });
            diainfosearch.setDiainfo("true");
            diainfosearch.setDetail("full");
            diainfosearch.setDetailAnalyze(true);
            diainfosearch.setDialogDisplay(false);
            diainfosearch.request();
            objRegistSearch = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final TransitBaseActivity this$0;

                public boolean onCanceled()
                {
                    setDiainfo(false);
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    setDiainfo(false);
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    chkRegistRail = bundle;
                    if (chkDiainfo != null && chkRegistRail != null)
                    {
                        chkDiainfoDetail();
                        setHumbergarIcon();
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            });
            objRegistSearch.setSearchType(getString(0x7f0d01d5));
            objRegistSearch.setWaitMsg(false);
            objRegistSearch.requestAsync(false);
            return;
        }
    }

    protected void chkDiainfoDetail()
    {
        if (chkDiainfo == null) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        Bundle bundle1;
        int i;
        bundle = chkDiainfo.getBundle(getString(0x7f0d0573));
        bundle1 = chkDiainfo.getBundle(getString(0x7f0d0572));
        i = 0;
_L14:
        if (i >= chkRegistRail.size()) goto _L4; else goto _L3
_L3:
        DiainfoData diainfodata = (DiainfoData)chkRegistRail.getSerializable(Integer.toString(i));
        if (bundle == null) goto _L6; else goto _L5
_L5:
        Iterator iterator1 = bundle.keySet().iterator();
_L9:
        if (!iterator1.hasNext()) goto _L6; else goto _L7
_L7:
        Bundle bundle2;
        Iterator iterator2;
        bundle2 = bundle.getBundle((String)iterator1.next());
        iterator2 = bundle2.keySet().iterator();
_L11:
        if (!iterator2.hasNext()) goto _L9; else goto _L8
_L8:
        Bundle bundle3;
        Iterator iterator3;
        bundle3 = bundle2.getBundle((String)iterator2.next());
        iterator3 = bundle3.keySet().iterator();
_L13:
        if (!iterator3.hasNext()) goto _L11; else goto _L10
_L10:
        if (!isExistDiainfo(diainfodata, (DiainfoData)bundle3.getSerializable((String)iterator3.next()))) goto _L13; else goto _L12
_L12:
        setDiainfo(true);
        return;
_L2:
        setDiainfo(false);
        return;
_L6:
        if (bundle1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Iterator iterator = bundle1.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
        } while (!isExistDiainfo(diainfodata, (DiainfoData)bundle1.getSerializable((String)iterator.next())));
        setDiainfo(true);
        return;
        i++;
          goto _L14
_L4:
        setDiainfo(false);
        return;
    }

    protected void closeProgressDialog()
    {
        try
        {
            if (progressDialog != null && progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }

    public void controlButton(Button button, boolean flag)
    {
        if (flag)
        {
            button.setSelected(true);
            button.setTextColor(-1);
            button.setEnabled(false);
            return;
        } else
        {
            button.setSelected(false);
            button.setTextColor(0xff000000);
            button.setEnabled(true);
            return;
        }
    }

    protected void delClickListener()
    {
    }

    protected void dispAd(Context context, String s, String s1)
    {
        dispAd(context, s, s1, null);
    }

    protected void dispAd(Context context, String s, String s1, AdViewListener adviewlistener)
    {
        if (!s1.equals("Z")) goto _L2; else goto _L1
_L1:
        s1 = "pv";
_L4:
        boolean flag = getResources().getBoolean(0x7f080008);
        AdViewForInstance adviewforinstance = new AdViewForInstance(context, getString(0x7f0d001e), s1, flag);
        adviewforinstance.spaceid(s);
        BearerToken bearertoken = TransitUtil.getAccessToken(this);
        if (bearertoken != null)
        {
            adviewforinstance.setAccessToken(bearertoken.getAccessToken());
        }
        if (adviewlistener == null)
        {
            adviewlistener = new AdviewListener();
        }
        adviewforinstance.setAdViewListener(adviewlistener);
        adviewforinstance.requestFreshAd();
        return;
_L2:
        if (s1.equals("NM"))
        {
            s1 = "top";
        } else
        if (s1.equals("IM"))
        {
            s1 = "bottom";
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if (keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 2: default 36
    //                   4: 51
    //                   82: 42;
           goto _L2 _L3 _L4
_L2:
        return super.dispatchKeyEvent(keyevent);
_L4:
        mDrawer.toggleLeftDrawer();
        return true;
_L3:
        if (!mDrawer.isClosed())
        {
            mDrawer.closeLeftSide();
            return true;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean endApi(ApiBase apibase, Object obj)
    {
label0:
        {
            Object obj1 = apibase.getResult();
            YolpApiBase yolpapibase = (YolpApiBase)apibase;
            if (bError)
            {
                return false;
            }
            if (obj1 == null || !(obj1 instanceof Bundle))
            {
                YolpError yolperror = yolpapibase.getError();
                if (yolperror.getCode() == null || !yolperror.getCode().equals(getString(0x7f0d0516)))
                {
                    break label0;
                }
                obj1 = new Bundle();
            }
            String s = ((TimeTableSearch)yolpapibase).getKind();
            if (s != null)
            {
                if (Integer.parseInt(s) == 1)
                {
                    objOrdinaryData = (Bundle)obj1;
                } else
                if (Integer.parseInt(s) == 4)
                {
                    objHolidayData = (Bundle)obj1;
                } else
                {
                    objSaturdayData = (Bundle)obj1;
                }
                if (objOrdinaryData != null && objHolidayData != null && objSaturdayData != null)
                {
                    Bundle bundle = new Bundle();
                    bundle.putBundle(Integer.toString(1), objOrdinaryData);
                    bundle.putBundle(Integer.toString(2), objSaturdayData);
                    bundle.putBundle(Integer.toString(4), objHolidayData);
                    endGetAllTimetable(bundle, ((TimeTableSearch)yolpapibase).getStation(), nNowWeek);
                    objOrdinaryData = null;
                    objSaturdayData = null;
                    objHolidayData = null;
                    m_progDialog.dismiss();
                }
            } else
            {
                m_progDialog.dismiss();
            }
            return true;
        }
        bError = true;
        m_progDialog.dismiss();
        endGetAllTimetable(null, null, nNowWeek);
        objOrdinaryData = null;
        objSaturdayData = null;
        objHolidayData = null;
        return false;
    }

    protected void endGetAllTimetable(Bundle bundle, StationData stationdata, int i)
    {
    }

    protected void getAllTimetable(StationData stationdata)
    {
        bError = false;
        TimeTableSearch timetablesearch = new TimeTableSearch(this);
        timetablesearch.setStation(stationdata);
        timetablesearch.setCode(stationdata.getStationId());
        timetablesearch.setId(stationdata.getDirid());
        timetablesearch.setKind(1);
        timetablesearch.executeAsync(this, false);
        TimeTableSearch timetablesearch1 = new TimeTableSearch(this);
        timetablesearch1.setStation(stationdata);
        timetablesearch1.setCode(stationdata.getStationId());
        timetablesearch1.setId(stationdata.getDirid());
        timetablesearch1.setKind(2);
        timetablesearch1.executeAsync(this, false);
        TimeTableSearch timetablesearch2 = new TimeTableSearch(this);
        timetablesearch2.setStation(stationdata);
        timetablesearch2.setCode(stationdata.getStationId());
        timetablesearch2.setId(stationdata.getDirid());
        timetablesearch2.setKind(4);
        timetablesearch2.executeAsync(this, false);
        m_progDialog = new ProgressDialog(this);
        m_progDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        m_progDialog.setMessage(getString(0x7f0d04a1));
        m_progDialog.setProgressStyle(0);
        m_progDialog.show();
    }

    protected void getAllTimetableToday(final StationData objStation)
    {
        bError = false;
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        TimeTableSearch timetablesearch = new TimeTableSearch(this);
        timetablesearch.setStation(objStation);
        timetablesearch.setCode(objStation.getStationId());
        timetablesearch.setId(objStation.getDirid());
        timetablesearch.setDate(simpledateformat.format(new Date()));
        timetablesearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final TransitBaseActivity this$0;
            final StationData val$objStation;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                Object obj1;
                TimeTableSearch timetablesearch2;
                TimeTableSearch timetablesearch3;
label0:
                {
label1:
                    {
                        obj1 = apibase.getResult();
                        TimeTableSearch timetablesearch1 = (TimeTableSearch)apibase;
                        if (obj1 == null || !(obj1 instanceof Bundle))
                        {
                            YolpError yolperror = timetablesearch1.getError();
                            if (yolperror.getCode() == null || !yolperror.getCode().equals(getString(0x7f0d0516)))
                            {
                                break label1;
                            }
                            obj1 = new Bundle();
                        }
                        nNowWeek = timetablesearch1.getTodayKind();
                        timetablesearch2 = new TimeTableSearch(TransitBaseActivity.this);
                        timetablesearch2.setStation(objStation);
                        timetablesearch2.setCode(objStation.getStationId());
                        timetablesearch2.setId(objStation.getDirid());
                        timetablesearch3 = new TimeTableSearch(TransitBaseActivity.this);
                        timetablesearch3.setStation(objStation);
                        timetablesearch3.setCode(objStation.getStationId());
                        timetablesearch3.setId(objStation.getDirid());
                        if (nNowWeek == 1)
                        {
                            objOrdinaryData = (Bundle)obj1;
                            timetablesearch2.setKind(2);
                            timetablesearch2.executeAsync(TransitBaseActivity.this, false);
                            timetablesearch3.setKind(4);
                            timetablesearch3.executeAsync(TransitBaseActivity.this, false);
                            return false;
                        }
                        break label0;
                    }
                    bError = true;
                    m_progDialog.dismiss();
                    endGetAllTimetable(null, null, nNowWeek);
                    objOrdinaryData = null;
                    objSaturdayData = null;
                    objHolidayData = null;
                    return false;
                }
                if (nNowWeek == 4)
                {
                    objHolidayData = (Bundle)obj1;
                    timetablesearch2.setKind(1);
                    timetablesearch2.executeAsync(TransitBaseActivity.this, false);
                    timetablesearch3.setKind(2);
                    timetablesearch3.executeAsync(TransitBaseActivity.this, false);
                    return false;
                } else
                {
                    timetablesearch2.setKind(1);
                    timetablesearch2.executeAsync(TransitBaseActivity.this, false);
                    timetablesearch3.setKind(4);
                    timetablesearch3.executeAsync(TransitBaseActivity.this, false);
                    objSaturdayData = (Bundle)obj1;
                    return false;
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                objStation = stationdata;
                super();
            }
        }, false);
        m_progDialog = new ProgressDialog(this);
        m_progDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        m_progDialog.setMessage(getString(0x7f0d04a1));
        m_progDialog.setProgressStyle(0);
        m_progDialog.show();
    }

    public ArrayList getAppMetaInfo(boolean flag, String s)
    {
        ArrayList arraylist = new ArrayList();
        String s1;
        Hashtable hashtable;
        Hashtable hashtable1;
        if (s != null)
        {
            s1 = s;
        } else
        {
            s1 = getString(0x7f0d0052);
        }
        hashtable = new Hashtable(1);
        hashtable.put("os", "android");
        hashtable.put("devicetype", "phone");
        if (flag)
        {
            hashtable.put("installurl", getString(0x7f0d054e));
        } else
        {
            hashtable.put("installurl", getString(0x7f0d054f));
        }
        hashtable.put("executeurl", (new StringBuilder()).append(getString(0x7f0d0053)).append(getString(0x7f0d0052)).append(s1).toString());
        arraylist.add(hashtable);
        hashtable1 = new Hashtable(1);
        hashtable1.put("os", "ios");
        hashtable1.put("devicetype", "phone");
        if (flag)
        {
            hashtable1.put("installurl", getString(0x7f0d054e));
        } else
        {
            hashtable1.put("installurl", getString(0x7f0d0550));
        }
        hashtable1.put("executeurl", (new StringBuilder()).append(getString(0x7f0d0054)).append(getString(0x7f0d0051)).append(s1).toString());
        arraylist.add(hashtable1);
        return arraylist;
    }

    protected int getCountdownType()
    {
        return CountdownUtil.getCountdownType(this);
    }

    public String getDensity(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        if (displaymetrics.densityDpi == 320)
        {
            return context.getString(0x7f0d00d0);
        }
        if (displaymetrics.densityDpi == 240)
        {
            return context.getString(0x7f0d00cd);
        }
        if (displaymetrics.densityDpi == 160)
        {
            return context.getString(0x7f0d00cf);
        }
        if (displaymetrics.densityDpi == 120)
        {
            return context.getString(0x7f0d00ce);
        } else
        {
            return null;
        }
    }

    protected boolean getDiainfoMenuState()
    {
        ListItemImageText listitemimagetext = (ListItemImageText)findViewById(0x7f0a028e);
        if (listitemimagetext == null)
        {
            return isDiainfo();
        }
        return listitemimagetext.findViewById(0x7f0a0223).getVisibility() == 0;
    }

    protected String getRouteIdString()
    {
        return "";
    }

    public String getVersionName()
    {
        String s;
        try
        {
            s = getPackageManager().getPackageInfo(getPackageName(), 1).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return "";
        }
        return s;
    }

    protected void init()
    {
    }

    protected boolean isAlarm()
    {
        return (new Alarm(this)).countAlarmData() != 0;
    }

    protected boolean isDiainfo()
    {
        Map map = (new SetSharedPreferences(this, getString(0x7f0d00c2))).getSharedPreferenceData();
        if (map != null && !map.isEmpty())
        {
            String s = (String)map.get(getString(0x7f0d01ad));
            if (s != null && s.equals("true"))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isNetworkAvailable()
    {
        for (NetworkInfo networkinfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo(); networkinfo == null || !networkinfo.isAvailable() || !networkinfo.isConnected();)
        {
            return false;
        }

        return true;
    }

    public boolean leadToAppOnMarket(String s)
    {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("market://details?id=").append(s).toString())));
        return true;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Toast.makeText(this, "market \u30A2\u30D7\u30EA\u304C\u5B58\u5728\u3057\u307E\u305B\u3093\u3002", 1).show();
_L2:
        return false;
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
label0:
        {
            super.onActivityResult(i, j, intent);
            if (-1 == j && getResources().getInteger(0x7f0c004c) == i)
            {
                registMyroute(new ResultDB(this), (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c)), 2, intent.getIntExtra(getString(0x7f0d01e4), 0));
            }
            if (i == 1000)
            {
                if (!YTcookieChecker.chkYTcookie())
                {
                    break label0;
                }
                setPushDiainfo();
            }
            return;
        }
        onLoginResult();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle)
    {
        Intent intent;
        super.onCreate(bundle);
        imm = (InputMethodManager)getSystemService("input_method");
        intent = getIntent();
        if ("android.intent.action.MAIN".equals(intent.getAction()))
        {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            (new SkinMeta(this, displaymetrics.densityDpi)).update();
        }
        sRdPath = getString(0x7f0d0560);
        if (!(this instanceof Transit)) goto _L2; else goto _L1
_L1:
        sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d044c)).toString();
_L4:
        final SharedPreferences settings;
        int i = intent.getIntExtra(getString(0x7f0d01ca), 1);
        if (i == -1 || i == -2)
        {
            i = 1;
        }
        menuId = i;
        YStrictMode.permitAll();
        startCount();
        setSlideMenue();
        settings = getSharedPreferences(getString(0x7f0d04e2), 0);
        boolean flag = settings.getBoolean(getString(0x7f0d039c), true);
        if (getResources().getBoolean(0x7f080007) && flag)
        {
            if (!getResources().getBoolean(0x7f080006))
            {
                break; /* Loop/switch isn't completed */
            }
            (new RuleforPreinstallDialoig(this)).showDialog(new jp.co.yahoo.android.apps.transit.viewparts.RuleforPreinstallDialoig.RuleDialogListener() {

                final TransitBaseActivity this$0;
                final SharedPreferences val$settings;

                public void onCancel()
                {
                    android.content.SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(getString(0x7f0d039c), true);
                    editor.commit();
                    finish();
                }

                public void onOK()
                {
                    init();
                    android.content.SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(getString(0x7f0d039c), false);
                    editor.commit();
                }

            
            {
                this$0 = TransitBaseActivity.this;
                settings = sharedpreferences;
                super();
            }
            });
        }
        return;
_L2:
        if (this instanceof HomeDatetimeConditionActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d044b)).toString();
        } else
        if (this instanceof SearchResultListActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d044a)).toString();
        } else
        if (this instanceof OtherCandidatesActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d044a)).toString();
        } else
        if (this instanceof SearchResultActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d0447)).toString();
        } else
        if (this instanceof StationInfo)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d0447)).append("/").append(getString(0x7f0d041b)).toString();
        } else
        if (this instanceof SearchResultTeikiActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d0447)).append("/").append(getString(0x7f0d0405)).toString();
        } else
        if (this instanceof InputSearch)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d0448)).toString();
        } else
        if (this instanceof HomeDatetimeConditionActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0446)).append("/").append(getString(0x7f0d044b)).toString();
        } else
        if (this instanceof MyPageActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d043f)).toString();
        } else
        if (this instanceof TimeTableTopActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0450)).append("/").append(getString(0x7f0d0452)).toString();
        } else
        if (this instanceof TimeTableActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0450)).append("/").append(getString(0x7f0d0451)).toString();
        } else
        if (this instanceof DiainfoActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0453)).toString();
        } else
        if (this instanceof DiainfoDetailActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0453)).append("/").append(getString(0x7f0d0454)).toString();
        } else
        if (this instanceof OthersPushDiainfoActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0453)).append("/").append(getString(0x7f0d0455)).toString();
        } else
        if (this instanceof OthersActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d0445)).toString();
        } else
        if (this instanceof CountdownActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d044e)).append("/").append(getString(0x7f0d044f)).toString();
        } else
        if (this instanceof SettingTopActivity)
        {
            sRdPath = (new StringBuilder()).append(sRdPath).append(getString(0x7f0d044e)).append("/").append(getString(0x7f0d044d)).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
        YJAgreementHelper.show(this, new jp.co.yahoo.android.common.agreementlib.YJAgreementHelper.OnAggreementListener() {

            final TransitBaseActivity this$0;
            final SharedPreferences val$settings;

            public void onAgree()
            {
                init();
                android.content.SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(getString(0x7f0d039c), false);
                editor.commit();
            }

            public void onDisagree()
            {
                android.content.SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(getString(0x7f0d039c), true);
                editor.commit();
                finish();
            }

            
            {
                this$0 = TransitBaseActivity.this;
                settings = sharedpreferences;
                super();
            }
        });
        return;
    }

    protected void onLoginResult()
    {
    }

    protected void onOpenSlideMenu()
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 16908332 16908332: default 24
    //                   16908332 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        mDrawer.toggleLeftDrawer();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onResume()
    {
        if (SingletonMap.getInstance().size() > 0) goto _L2; else goto _L1
_L1:
        touchRD(getString(0x7f0d0553));
        chkDiainfo();
        if (TransitUtil.isCheckDiainfo(this))
        {
            TransitUtil.setCheckDiainfo(this, false);
        }
_L4:
        SingletonMap.getInstance().put((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString(), Boolean.valueOf(true));
        boolean flag = isDiainfo();
        if (flag != getDiainfoMenuState())
        {
            setDiainfoMenuState(flag);
            setHumbergarIcon();
        }
        super.onResume();
        return;
_L2:
        if (TransitUtil.isCheckDiainfo(this))
        {
            TransitUtil.setCheckDiainfo(this, false);
            chkDiainfo();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onStop()
    {
        SingletonMap.getInstance().put((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString(), Boolean.valueOf(false));
        (new Timer()).schedule(new TimerTask() {

            final TransitBaseActivity this$0;

            public void run()
            {
                if (SingletonMap.getInstance().containsKey((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString()) && !((Boolean)SingletonMap.getInstance().get((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString())).booleanValue())
                {
                    SingletonMap.getInstance().remove((new StringBuilder()).append(getLocalClassName()).append(getRouteIdString()).toString());
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        }, 3000L);
        super.onStop();
    }

    protected void registMyroute(ResultDB resultdb, ConditionData conditiondata)
    {
        if (resultdb.countMyroute() >= Integer.parseInt(resultdb.getMaxMyroute()))
        {
            showDeleteMyrouteDialog(resultdb, conditiondata);
            return;
        } else
        {
            registMyroute(resultdb, conditiondata, 0, 0);
            return;
        }
    }

    protected void setDiainfo(boolean flag)
    {
        SetSharedPreferences setsharedpreferences = new SetSharedPreferences(this, getString(0x7f0d00c2));
        HashMap hashmap = new HashMap();
        if (flag)
        {
            hashmap.put(getString(0x7f0d01ad), "true");
        } else
        {
            hashmap.put(getString(0x7f0d01ad), "false");
        }
        setsharedpreferences.setSharedPreferenceData(hashmap);
        setDiainfoMenuState(flag);
    }

    protected void setDiainfoMenuState(boolean flag)
    {
        ListItemImageText listitemimagetext = (ListItemImageText)findViewById(0x7f0a028e);
        if (listitemimagetext == null)
        {
            return;
        }
        if (flag)
        {
            TextView textview1 = (TextView)listitemimagetext.findViewById(0x7f0a0222);
            textview1.setTypeface(null, 1);
            textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0201fc, 0);
            textview1.setCompoundDrawablePadding(getResources().getDimensionPixelSize(0x7f0b0034));
            return;
        } else
        {
            TextView textview = (TextView)listitemimagetext.findViewById(0x7f0a0222);
            textview.setTypeface(null, 0);
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return;
        }
    }

    protected void setHumbergarIcon()
    {
        if (isDiainfo() || isAlarm())
        {
            if (PopupNew.isShow(this, getString(0x7f0d03a3), 71))
            {
                getSupportActionBar().setIcon(0x7f0200da);
                return;
            } else
            {
                getSupportActionBar().setIcon(0x7f0200d9);
                return;
            }
        }
        if (PopupNew.isShow(this, getString(0x7f0d03a3), 71))
        {
            getSupportActionBar().setIcon(0x7f0200d8);
            return;
        } else
        {
            getSupportActionBar().setIcon(0x7f0200d7);
            return;
        }
    }

    protected void setOtherMenuState(boolean flag)
    {
        ListItemImageText listitemimagetext = (ListItemImageText)findViewById(0x7f0a028f);
        if (listitemimagetext == null)
        {
            return;
        }
        if (flag)
        {
            ((ImageView)listitemimagetext.findViewById(0x7f0a0223)).setVisibility(0);
            return;
        } else
        {
            ((ImageView)listitemimagetext.findViewById(0x7f0a0223)).setVisibility(8);
            return;
        }
    }

    protected void setPushDiainfo()
    {
        BearerToken bearertoken = TransitUtil.getAccessToken(this);
        if ((new PushDiainfoManager(this)).setRailPushForYid(bearertoken, new jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener() {

        final TransitBaseActivity this$0;

        public void onCanceled()
        {
            closeProgressDialog();
            onLoginResult();
        }

        public void onError(int i, String s, String s1, String s2)
        {
            closeProgressDialog();
            onLoginResult();
        }

        public void onSuccess(String s, String s1)
        {
            closeProgressDialog();
            onLoginResult();
        }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
    }))
        {
            showProgressDialog();
            return;
        } else
        {
            onLoginResult();
            return;
        }
    }

    protected void setSlideMenue()
    {
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        LinearLayout linearlayout2;
        LinearLayout linearlayout3;
        LinearLayout linearlayout4;
        LinearLayout linearlayout5;
        LinearLayout linearlayout6;
        LinearLayout linearlayout7;
        LinearLayout linearlayout8;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        setHumbergarIcon();
        mDrawer = new YSimpleSideDrawer(this);
        mDrawer.setLeftBehindContentView(0x7f030087);
        mDrawer.setOverlayBackgroundColor(Color.parseColor("#AA000000"));
        jp.co.yahoo.android.common.hamburger.YSimpleSideDrawer.StateListener statelistener = new jp.co.yahoo.android.common.hamburger.YSimpleSideDrawer.StateListener() {

            final TransitBaseActivity this$0;

            public void onStateChanged(int i)
            {
                if (i == 2)
                {
                    imm.hideSoftInputFromWindow(mDrawer.getWindowToken(), 0);
                    YHBGRd.sendAsync(getApplicationContext(), new String[] {
                        "open"
                    });
                    setDiainfoMenuState(isDiainfo());
                    setOtherMenuState(isAlarm());
                    setHumbergarIcon();
                    onOpenSlideMenu();
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        };
        mDrawer.setStateListener(statelistener);
        YHBGRecommendApps yhbgrecommendapps = new YHBGRecommendApps(this);
        yhbgrecommendapps.setJsonUrl(getString(0x7f0d045f));
        yhbgrecommendapps.updateIfNecessary(new Runnable() {

            final TransitBaseActivity this$0;

            public void run()
            {
                Message message = Message.obtain(mHandler, 1);
                mHandler.sendMessage(message);
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        });
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            final TransitBaseActivity this$0;

            public void onClick(View view)
            {
                int i;
                Intent intent;
                i = ((Integer)view.getTag()).intValue();
                touchMenuRD(i);
                intent = null;
                i;
                JVM INSTR tableswitch 1 9: default 72
            //                           1 117
            //                           2 157
            //                           3 197
            //                           4 177
            //                           5 137
            //                           6 217
            //                           7 237
            //                           8 248
            //                           9 260;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
                if (intent != null)
                {
                    intent.putExtra(getString(0x7f0d01ca), i);
                    intent.setAction("android.intent.action.VIEW");
                    startActivity(intent);
                }
                mDrawer.closeLeftSide();
                return;
_L2:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/Transit);
                continue; /* Loop/switch isn't completed */
_L6:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
                continue; /* Loop/switch isn't completed */
_L3:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/TimeTableTopActivity);
                continue; /* Loop/switch isn't completed */
_L5:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/RailmapActivity);
                continue; /* Loop/switch isn't completed */
_L4:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/MyPageActivity);
                continue; /* Loop/switch isn't completed */
_L7:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/OthersActivity);
                continue; /* Loop/switch isn't completed */
_L8:
                intent = showCountdown();
                continue; /* Loop/switch isn't completed */
_L9:
                showAroundStation();
                intent = null;
                continue; /* Loop/switch isn't completed */
_L10:
                intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/SettingTopActivity);
                if (true) goto _L1; else goto _L11
_L11:
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        };
        linearlayout = (LinearLayout)findViewById(0x7f0a028a);
        linearlayout.setTag(Integer.valueOf(1));
        linearlayout.setOnClickListener(onclicklistener);
        linearlayout1 = (LinearLayout)findViewById(0x7f0a028b);
        linearlayout1.setTag(Integer.valueOf(3));
        linearlayout1.setOnClickListener(onclicklistener);
        linearlayout2 = (LinearLayout)findViewById(0x7f0a028d);
        linearlayout2.setTag(Integer.valueOf(4));
        linearlayout2.setOnClickListener(onclicklistener);
        linearlayout3 = (LinearLayout)findViewById(0x7f0a028c);
        linearlayout3.setTag(Integer.valueOf(2));
        linearlayout3.setOnClickListener(onclicklistener);
        linearlayout4 = (LinearLayout)findViewById(0x7f0a028e);
        linearlayout4.setTag(Integer.valueOf(5));
        linearlayout4.setOnClickListener(onclicklistener);
        linearlayout5 = (LinearLayout)findViewById(0x7f0a028f);
        linearlayout5.setTag(Integer.valueOf(6));
        linearlayout5.setOnClickListener(onclicklistener);
        linearlayout6 = (LinearLayout)mDrawer.findViewById(0x7f0a0291);
        linearlayout6.setTag(Integer.valueOf(7));
        linearlayout6.setOnClickListener(onclicklistener);
        linearlayout7 = (LinearLayout)mDrawer.findViewById(0x7f0a0292);
        linearlayout7.setTag(Integer.valueOf(8));
        linearlayout7.setOnClickListener(onclicklistener);
        linearlayout8 = (LinearLayout)mDrawer.findViewById(0x7f0a0293);
        linearlayout8.setTag(Integer.valueOf(9));
        linearlayout8.setOnClickListener(onclicklistener);
        menuId;
        JVM INSTR tableswitch 2 9: default 432
    //                   2 443
    //                   3 450
    //                   4 457
    //                   5 464
    //                   6 471
    //                   7 478
    //                   8 485
    //                   9 492;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        LinearLayout linearlayout9 = linearlayout;
_L11:
        linearlayout9.setSelected(true);
        return;
_L2:
        linearlayout9 = linearlayout3;
        continue; /* Loop/switch isn't completed */
_L3:
        linearlayout9 = linearlayout1;
        continue; /* Loop/switch isn't completed */
_L4:
        linearlayout9 = linearlayout2;
        continue; /* Loop/switch isn't completed */
_L5:
        linearlayout9 = linearlayout4;
        continue; /* Loop/switch isn't completed */
_L6:
        linearlayout9 = linearlayout5;
        continue; /* Loop/switch isn't completed */
_L7:
        linearlayout9 = linearlayout6;
        continue; /* Loop/switch isn't completed */
_L8:
        linearlayout9 = linearlayout7;
        continue; /* Loop/switch isn't completed */
_L9:
        linearlayout9 = linearlayout8;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public void setTitle(String s)
    {
        super.setTitle(s);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setTitle(s);
        }
    }

    protected void showAroundStation()
    {
        LocationSearch locationsearch = new LocationSearch(this, new jp.co.yahoo.android.apps.transit.timer.api.LocationSearch.LocationSearchListener() {

            final TransitBaseActivity this$0;

            public void onError(String s, String s1)
            {
                showErrorMessageDialog(s1, getString(0x7f0d014f));
            }

            public void onSuccess(String s, Bundle bundle1)
            {
                Bundle bundle2 = bundle1.getBundle(getString(0x7f0d0240));
                if (bundle2 == null || bundle2.size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d0119), getString(0x7f0d014f));
                    return;
                } else
                {
                    launchStation(bundle2);
                    return;
                }
            }

            public void onTimeout(String s, String s1)
            {
                showErrorMessageDialog(s1, getString(0x7f0d014f));
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01b6), "10");
        bundle.putString(getString(0x7f0d018e), "dist");
        locationsearch.getCurrentStation(bundle);
    }

    protected void showDatePickerDialog(String s, int i, int j, int k, final android.app.DatePickerDialog.OnDateSetListener listener)
    {
        LinearLayout linearlayout;
        final DatePicker datePicker;
        int l;
        int i1;
        int j1;
        if (isFinishing())
        {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, j, k);
        linearlayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030054, null);
        final TextView dateDisplay = (TextView)linearlayout.findViewById(0x7f0a009e);
        datePicker = (DatePicker)linearlayout.findViewById(0x7f0a00a0);
        dateDisplay.setVisibility(0);
        datePicker.setVisibility(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy\u5E74M\u6708d\u65E5\uFF08E\uFF09", Locale.JAPANESE);
        dateDisplay.setText(simpledateformat.format(calendar.getTime()));
        android.widget.DatePicker.OnDateChangedListener ondatechangedlistener = new android.widget.DatePicker.OnDateChangedListener() {

            final TransitBaseActivity this$0;
            final TextView val$dateDisplay;

            public void onDateChanged(DatePicker datepicker, int i2, int j2, int k2)
            {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(i2, j2, k2);
                SimpleDateFormat simpledateformat1 = new SimpleDateFormat("yyyy\u5E74M\u6708d\u65E5\uFF08E\uFF09", Locale.JAPANESE);
                dateDisplay.setText(simpledateformat1.format(calendar1.getTime()));
            }

            
            {
                this$0 = TransitBaseActivity.this;
                dateDisplay = textview;
                super();
            }
        };
        datePicker.init(i, j, k, ondatechangedlistener);
        l = Resources.getSystem().getIdentifier("day", "id", "android");
        i1 = Resources.getSystem().getIdentifier("month", "id", "android");
        j1 = Resources.getSystem().getIdentifier("year", "id", "android");
        if (!Build.MODEL.equals("SO-03C") && !Build.MODEL.equals("SO-03D")) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L5:
        if (flag)
        {
            View view = datePicker.findViewById(l);
            View view1 = datePicker.findViewById(i1);
            View view2 = datePicker.findViewById(j1);
            TransitDialogBuilder transitdialogbuilder;
            android.app.AlertDialog.Builder builder;
            android.content.DialogInterface.OnClickListener onclicklistener;
            String as[];
            int k1;
            boolean flag1;
            boolean flag2;
            int l1;
            boolean flag3;
            if (view == null || view1 == null)
            {
                ((LinearLayout)datePicker.getChildAt(0)).setBackgroundColor(0xff000000);
            } else
            {
                view.setBackgroundColor(0xff000000);
                view1.setBackgroundColor(0xff000000);
                view2.setBackgroundColor(0xff000000);
            }
        }
        transitdialogbuilder = new TransitDialogBuilder(this);
        builder = transitdialogbuilder.setTitle(s).setView(linearlayout);
        onclicklistener = new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;
            final DatePicker val$datePicker;
            final android.app.DatePickerDialog.OnDateSetListener val$listener;

            public void onClick(DialogInterface dialoginterface, int i2)
            {
                ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(datePicker.getWindowToken(), 2);
                if (listener != null)
                {
                    int j2 = datePicker.getYear();
                    int k2 = datePicker.getMonth();
                    int l2 = datePicker.getDayOfMonth();
                    listener.onDateSet(null, j2, k2, l2);
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                datePicker = datepicker;
                listener = ondatesetlistener;
                super();
            }
        };
        builder.setPositiveButton(0x7f0d007f, onclicklistener).setNegativeButton(0x7f0d0071, null).show();
        return;
_L2:
        if (Build.MODEL.equals("SO-01B") || Build.MODEL.equals("SO-01C") || Build.MODEL.equals("SO-02C") || Build.MODEL.equals("SO-02D") || Build.MODEL.equals("IS11S")) goto _L4; else goto _L3
_L3:
        flag3 = Build.MODEL.equals("IS12S");
        flag = false;
        if (!flag3) goto _L5; else goto _L4
_L4:
        as = android.os.Build.VERSION.RELEASE.split("\\.");
        if (2 < Integer.valueOf(as[0]).intValue() || 2 == as.length && "2".equals(as[0]) && 3 < Integer.valueOf(as[1].substring(0, 1)).intValue()) goto _L7; else goto _L6
_L6:
        k1 = as.length;
        flag = false;
        if (3 > k1) goto _L5; else goto _L8
_L8:
        flag1 = "2".equals(as[0]);
        flag = false;
        if (!flag1) goto _L5; else goto _L9
_L9:
        flag2 = "3".equals(as[1]);
        flag = false;
        if (!flag2) goto _L5; else goto _L10
_L10:
        l1 = Integer.valueOf(as[2].substring(0, 1)).intValue();
        flag = false;
        if (3 >= l1) goto _L5; else goto _L7
_L7:
        flag = true;
          goto _L5
    }

    protected void showErrorMessageDialog(String s, String s1)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s).setTitleWarnning(s1).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    public void showMessageDialog(String s, String s1)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s).setTitleInfo(s1).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    protected void showMessageDialog(String s, String s1, String s2)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s).setTitleInfo(s1).setPositiveButton(s2, new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    protected void showProgressDialog()
    {
        if (progressDialog == null)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
            progressDialog.setMessage(getString(0x7f0d04a1));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
        }
        if (!progressDialog.isShowing())
        {
            progressDialog.show();
        }
    }

    protected boolean showSelectTicketTypeDialog(final android.view.View.OnClickListener listener)
    {
        boolean flag = true;
        final SaveCondition save = new SaveCondition(this, false);
        if (save.isSavedConditionTicket())
        {
            flag = false;
        } else
        if (!isFinishing())
        {
            Context context = getApplicationContext();
            ConditionData conditiondata = new ConditionData();
            conditiondata.ticket = context.getString(0x7f0d0581);
            save.loadCond();
            save.setCond(conditiondata, false, false, false, flag);
            (new TransitDialogBuilder(this)).setMessage(getString(0x7f0d04b6)).setTitle(getString(0x7f0d04b7)).setCancelable(false).setPositiveButton(getString(0x7f0d04b3), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;
                final android.view.View.OnClickListener val$listener;
                final SaveCondition val$save;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Context context1 = getApplicationContext();
                    ConditionData conditiondata1 = new ConditionData();
                    conditiondata1.ticket = context1.getString(0x7f0d0582);
                    save.setCond(conditiondata1, false, false, false, true);
                    if (listener != null)
                    {
                        listener.onClick(null);
                    }
                }

            
            {
                this$0 = TransitBaseActivity.this;
                save = savecondition;
                listener = onclicklistener;
                super();
            }
            }).setNegativeButton(getString(0x7f0d04b4), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;
                final android.view.View.OnClickListener val$listener;
                final SaveCondition val$save;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Context context1 = getApplicationContext();
                    ConditionData conditiondata1 = new ConditionData();
                    conditiondata1.ticket = context1.getString(0x7f0d0583);
                    save.setCond(conditiondata1, false, false, false, true);
                    if (listener != null)
                    {
                        listener.onClick(null);
                    }
                }

            
            {
                this$0 = TransitBaseActivity.this;
                save = savecondition;
                listener = onclicklistener;
                super();
            }
            }).show();
            return flag;
        }
        return flag;
    }

    protected void showSettingDialog(boolean flag, String s)
    {
        if (isFinishing())
        {
            return;
        }
        if (s == null || s.equals(""))
        {
            s = getString(0x7f0d04c1);
        }
        (new TransitDialogBuilder(this)).setTitle(getString(0x7f0d04c4)).setMessage(s).setPositiveButton(getString(0x7f0d007e), new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/SettingStationActivity);
                intent.putExtra(getString(0x7f0d01ca), 9);
                startActivityForResult(intent, getResources().getInteger(0x7f0c005f));
            }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
        }).show();
    }

    public void showSimpleErrorMessageDialog(String s)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s).setTitleWarnning(getString(0x7f0d015e)).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    public void showSimpleErrorMessageDialog(String s, android.content.DialogInterface.OnClickListener onclicklistener, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s).setTitleWarnning(getString(0x7f0d015e)).setPositiveButton(getString(0x7f0d015c), onclicklistener).setOnCancelListener(oncancellistener).show();
            return;
        }
    }

    protected void showSingleChoiceListDialog(String s, int i, String s1, CharSequence acharsequence[], int j, int k, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        showSingleChoiceListDialog(s, i, s1, acharsequence, j, k, onclicklistener, null);
    }

    protected void showSingleChoiceListDialog(String s, int i, String s1, CharSequence acharsequence[], int j, int k, final android.content.DialogInterface.OnClickListener listener, 
            final android.content.DialogInterface.OnClickListener cancelListener)
    {
        if (isFinishing())
        {
            return;
        }
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(1);
        if (!TransitUtil.isEmpty(s1))
        {
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030056, null);
            ((TextView)view.findViewById(0x7f0a01f4)).setText(s1);
            linearlayout.addView(view, -1, -2);
        }
        linearlayout.addView((ImageView)getLayoutInflater().inflate(0x7f030059, null));
        final ListView listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter(this, 0x7f030078, acharsequence));
        listView.setChoiceMode(1);
        listView.setItemChecked(j, true);
        listView.setDivider(getResources().getDrawable(0x7f020165));
        linearlayout.addView(listView, -1, -2);
        linearlayout.addView((ImageView)getLayoutInflater().inflate(0x7f030059, null));
        TransitDialogBuilder transitdialogbuilder = (TransitDialogBuilder)(new TransitDialogBuilder(this)).setView(linearlayout).setPositiveButton(k, new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;
            final ListView val$listView;
            final android.content.DialogInterface.OnClickListener val$listener;

            public void onClick(DialogInterface dialoginterface, int l)
            {
                if (listener != null)
                {
                    int i1 = listView.getCheckedItemPosition();
                    listener.onClick(null, i1);
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                listener = onclicklistener;
                listView = listview;
                super();
            }
        }).setNegativeButton(0x7f0d0071, new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;
            final android.content.DialogInterface.OnClickListener val$cancelListener;

            public void onClick(DialogInterface dialoginterface, int l)
            {
                if (cancelListener != null)
                {
                    cancelListener.onClick(null, -1);
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                cancelListener = onclicklistener;
                super();
            }
        }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final TransitBaseActivity this$0;
            final android.content.DialogInterface.OnClickListener val$cancelListener;

            public void onCancel(DialogInterface dialoginterface)
            {
                if (cancelListener != null)
                {
                    cancelListener.onClick(null, -1);
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                cancelListener = onclicklistener;
                super();
            }
        });
        if (i == 0)
        {
            transitdialogbuilder.setTitle(s);
        } else
        {
            transitdialogbuilder.setTitleIcon(s, i);
        }
        transitdialogbuilder.show();
    }

    protected void showSingleChoiceListDialog(String s, String s1, int i, int j, android.content.DialogInterface.OnClickListener onclicklistener, android.content.DialogInterface.OnClickListener onclicklistener1)
    {
        showSingleChoiceListDialog(s, 0, s1, getResources().getTextArray(i), j, 0x7f0d0074, onclicklistener, onclicklistener1);
    }

    protected void showTimePickerDialog(String s, int i, int j, final android.app.TimePickerDialog.OnTimeSetListener listener)
    {
        LinearLayout linearlayout;
        final TimePicker timePicker;
        int k;
        int l;
        if (isFinishing())
        {
            return;
        }
        linearlayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030054, null);
        final TextView timeDisplay = (TextView)linearlayout.findViewById(0x7f0a009f);
        timePicker = (TimePicker)linearlayout.findViewById(0x7f0a00a1);
        timeDisplay.setVisibility(0);
        timePicker.setVisibility(0);
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        timeDisplay.setText(String.format("%02d\uFF1A%02d", aobj));
        timePicker.setIs24HourView(Boolean.valueOf(true));
        timePicker.setCurrentHour(Integer.valueOf(i));
        timePicker.setCurrentMinute(Integer.valueOf(j));
        android.widget.TimePicker.OnTimeChangedListener ontimechangedlistener = new android.widget.TimePicker.OnTimeChangedListener() {

            final TransitBaseActivity this$0;
            final TextView val$timeDisplay;

            public void onTimeChanged(TimePicker timepicker, int k1, int l1)
            {
                Object aobj1[] = new Object[2];
                aobj1[0] = Integer.valueOf(k1);
                aobj1[1] = Integer.valueOf(l1);
                String s1 = String.format("%02d\uFF1A%02d", aobj1);
                timeDisplay.setText(s1);
            }

            
            {
                this$0 = TransitBaseActivity.this;
                timeDisplay = textview;
                super();
            }
        };
        timePicker.setOnTimeChangedListener(ontimechangedlistener);
        k = Resources.getSystem().getIdentifier("hour", "id", "android");
        l = Resources.getSystem().getIdentifier("minute", "id", "android");
        if (!Build.MODEL.equals("SO-03C") && !Build.MODEL.equals("SO-03D")) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L5:
        if (flag)
        {
            View view = timePicker.findViewById(k);
            View view1 = timePicker.findViewById(l);
            TransitDialogBuilder transitdialogbuilder;
            android.app.AlertDialog.Builder builder;
            android.content.DialogInterface.OnClickListener onclicklistener;
            String as[];
            int i1;
            boolean flag1;
            boolean flag2;
            int j1;
            boolean flag3;
            if (view == null || view1 == null)
            {
                ((LinearLayout)timePicker.getChildAt(0)).setBackgroundColor(0xff000000);
            } else
            {
                view.setBackgroundColor(0xff000000);
                view1.setBackgroundColor(0xff000000);
            }
        }
        transitdialogbuilder = new TransitDialogBuilder(this);
        builder = transitdialogbuilder.setTitle(s).setView(linearlayout);
        onclicklistener = new android.content.DialogInterface.OnClickListener() {

            final TransitBaseActivity this$0;
            final android.app.TimePickerDialog.OnTimeSetListener val$listener;
            final TimePicker val$timePicker;

            public void onClick(DialogInterface dialoginterface, int k1)
            {
                ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(timePicker.getWindowToken(), 2);
                if (listener != null)
                {
                    int l1 = timePicker.getCurrentHour().intValue();
                    int i2 = timePicker.getCurrentMinute().intValue();
                    listener.onTimeSet(null, l1, i2);
                }
            }

            
            {
                this$0 = TransitBaseActivity.this;
                timePicker = timepicker;
                listener = ontimesetlistener;
                super();
            }
        };
        builder.setPositiveButton(0x7f0d007f, onclicklistener).setNegativeButton(0x7f0d0071, null).show();
        return;
_L2:
        if (Build.MODEL.equals("SO-01B") || Build.MODEL.equals("SO-01C") || Build.MODEL.equals("SO-02C") || Build.MODEL.equals("SO-02D") || Build.MODEL.equals("IS11S")) goto _L4; else goto _L3
_L3:
        flag3 = Build.MODEL.equals("IS12S");
        flag = false;
        if (!flag3) goto _L5; else goto _L4
_L4:
        as = android.os.Build.VERSION.RELEASE.split("\\.");
        if (2 < Integer.valueOf(as[0]).intValue() || 2 == as.length && "2".equals(as[0]) && 3 < Integer.valueOf(as[1].substring(0, 1)).intValue()) goto _L7; else goto _L6
_L6:
        i1 = as.length;
        flag = false;
        if (3 > i1) goto _L5; else goto _L8
_L8:
        flag1 = "2".equals(as[0]);
        flag = false;
        if (!flag1) goto _L5; else goto _L9
_L9:
        flag2 = "3".equals(as[1]);
        flag = false;
        if (!flag2) goto _L5; else goto _L10
_L10:
        j1 = Integer.valueOf(as[2].substring(0, 1)).intValue();
        flag = false;
        if (3 >= j1) goto _L5; else goto _L7
_L7:
        flag = true;
          goto _L5
    }

    protected void showdelMessageDialog(String s)
    {
        showdelMessageDialog(s, true);
    }

    protected void showdelMessageDialog(String s, final boolean dispMessage)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            AlertDialog alertdialog = (new TransitDialogBuilder(this)).setMessage(s).create();
            alertdialog.setButton(-2, getString(0x7f0d00c6), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

            
            {
                this$0 = TransitBaseActivity.this;
                super();
            }
            });
            alertdialog.setButton(-1, getString(0x7f0d00c7), new android.content.DialogInterface.OnClickListener() {

                final TransitBaseActivity this$0;
                final Context val$context;
                final boolean val$dispMessage;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    String s2;
                    delClickListener();
                    s2 = getString(0x7f0d00ca);
                    String s1 = s2;
_L2:
                    if (dispMessage && s1.length() > 0)
                    {
                        Toast.makeText(context, s1, 0).show();
                    }
                    return;
                    Exception exception;
                    exception;
                    s1 = getString(0x7f0d00c8);
                    if (true) goto _L2; else goto _L1
_L1:
                }

            
            {
                this$0 = TransitBaseActivity.this;
                dispMessage = flag;
                context = context1;
                super();
            }
            });
            alertdialog.show();
            return;
        }
    }

    public void startActivity(Intent intent)
    {
        if (intent.getIntExtra(getString(0x7f0d01ca), -1) == -1)
        {
            intent.putExtra(getString(0x7f0d01ca), -2);
        }
        super.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        int j = intent.getIntExtra(getString(0x7f0d01ca), -1);
        if (j != -2) goto _L2; else goto _L1
_L1:
        intent.removeExtra(getString(0x7f0d01ca));
_L4:
        super.startActivityForResult(intent, i);
        return;
_L2:
        if (j == -1)
        {
            intent.putExtra(getString(0x7f0d01ca), menuId);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void startActivityInCurrentMenu(Intent intent)
    {
        intent.putExtra(getString(0x7f0d01ca), menuId);
        super.startActivity(intent);
    }

    protected void startCount()
    {
        if (SingletonMap.getInstance().size() <= 0)
        {
            touchRD(getString(0x7f0d055f));
            if (this instanceof Transit)
            {
                touchPriRD();
            }
            Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/PushDiainfoService);
            intent.setAction("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_APP_START");
            getApplicationContext().startService(intent);
            chkDiainfo();
            SingletonMap.getInstance().put(getLocalClassName(), Boolean.valueOf(true));
        }
    }

    public void startKakaoLink(String s, String s1)
    {
        String s2;
        String s3;
        s2 = "2.0";
        s3 = "jp.co.yahoo.android.apps.transit";
        KakaoLink kakaolink;
        try
        {
            PackageInfo packageinfo = getPackageManager().getPackageInfo(getPackageName(), 1);
            s2 = packageinfo.versionName;
            s3 = packageinfo.packageName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            try
            {
                namenotfoundexception.printStackTrace();
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                unsupportedencodingexception.printStackTrace();
                return;
            }
        }
        kakaolink = (new jp.co.yahoo.android.apps.transit.kakao.KakaoLink.BuilderV2(this)).setAppId(s3).setAppName(getString(0x7f0d0056)).setAppVersion(s2).setEncoding("UTF-8").setMessage(s).setMetadata(getAppMetaInfo(false, s1)).build();
        if (kakaolink.isAvailable())
        {
            startActivity(kakaolink.getIntent());
            return;
        }
        Toast.makeText(this, getString(0x7f0d0114), 1).show();
        return;
    }

    public void startKakaoTalkchatPlus(String s, String s1, String s2)
    {
        String s3;
        String s4;
        s3 = "2.0";
        s4 = "jp.co.yahoo.android.apps.transit";
        KakaoLink kakaolink;
        try
        {
            PackageInfo packageinfo = getPackageManager().getPackageInfo(getPackageName(), 1);
            s3 = packageinfo.versionName;
            s4 = packageinfo.packageName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            try
            {
                namenotfoundexception.printStackTrace();
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                unsupportedencodingexception.printStackTrace();
                return;
            }
        }
        kakaolink = (new jp.co.yahoo.android.apps.transit.kakao.KakaoLink.BuilderV2_2(this)).setAppId(s4).setAppName(getString(0x7f0d0056)).setAppVersion(s3).setEncoding("UTF-8").setClientToken(s1).setMessage(s).setMetadata(getAppMetaInfo(true, s2)).build();
        if (kakaolink.isAvailable())
        {
            startActivity(kakaolink.getIntent());
            return;
        }
        Toast.makeText(this, getString(0x7f0d0114), 1).show();
        return;
    }

    public void touchPriRD()
    {
        try
        {
            YDistributionUtils ydistributionutils = new YDistributionUtils(getApplicationContext());
            ydistributionutils.updateFromAssetFile();
            String s = getString(0x7f0d0180);
            String s1 = ydistributionutils.getString(s);
            if (!TransitUtil.isEmpty(s1))
            {
                TransitUtil.touchRD((new StringBuilder()).append(getString(0x7f0d0556)).append(s).append("_").append(s1).append("/").append(getString(0x7f0d0561)).toString());
            }
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void touchRD(String s)
    {
        TransitUtil.touchRD(s);
    }

    public void touchTapRD(String s)
    {
        TransitUtil.touchRD((new StringBuilder()).append(sRdPath).append("/").append(s).append("/").append(getString(0x7f0d0561)).toString());
    }

    protected void versionCheck()
    {
        if (getIntent() != null)
        {
            YAppInfoChecker yappinfochecker = YAppInfoChecker.getInstanse();
            yappinfochecker.setUrl(getString(0x7f0d0585));
            yappinfochecker.setVersionCheckListener(new YVersionCheckListener() {

                final TransitBaseActivity this$0;
                final Activity val$activity;

                public void onAppInfoDownloadCompleted()
                {
                    YAppInfoChecker yappinfochecker1 = YAppInfoChecker.getInstanse();
                    if (!yappinfochecker1.isLatestVersion(getApplicationContext()))
                    {
                        yappinfochecker1.showUpdateDialog(activity);
                    }
                }

                public void onAppInfoDownloadTimeout()
                {
                }

                public void onDismissUpdateDialog()
                {
                }

            
            {
                this$0 = TransitBaseActivity.this;
                activity = activity1;
                super();
            }
            });
            yappinfochecker.download();
        }
    }




/*
    static Bundle access$1002(TransitBaseActivity transitbaseactivity, Bundle bundle)
    {
        transitbaseactivity.objHolidayData = bundle;
        return bundle;
    }

*/







/*
    static boolean access$502(TransitBaseActivity transitbaseactivity, boolean flag)
    {
        transitbaseactivity.bError = flag;
        return flag;
    }

*/




/*
    static int access$702(TransitBaseActivity transitbaseactivity, int i)
    {
        transitbaseactivity.nNowWeek = i;
        return i;
    }

*/


/*
    static Bundle access$802(TransitBaseActivity transitbaseactivity, Bundle bundle)
    {
        transitbaseactivity.objOrdinaryData = bundle;
        return bundle;
    }

*/


/*
    static Bundle access$902(TransitBaseActivity transitbaseactivity, Bundle bundle)
    {
        transitbaseactivity.objSaturdayData = bundle;
        return bundle;
    }

*/
}
