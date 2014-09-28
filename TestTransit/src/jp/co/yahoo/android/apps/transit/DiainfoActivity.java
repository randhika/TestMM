// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.PushSubscriptionDB;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.android.apps.transit.viewparts.PopupNew;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersPushDiainfoActivity, DiainfoRailList, DiainfoAreaListActivity, 
//            DiainfoDetailActivity, DiainfoAllCategory

public class DiainfoActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{

    private boolean bDiainfoCompleted;
    private boolean bError;
    private boolean bNearStationCompleted;
    private boolean bPushSettingsCompleted;
    private boolean bRegistCompleted;
    private boolean bRetry;
    protected Bundle diainfo;
    private LinearLayout lsArround;
    private LinearLayout lsNoArround;
    private LinearLayout lsNologin;
    private LinearLayout lsRegist;
    protected Handler mainHandler;
    protected Bundle nearStations;
    private LocationSearch objLocSearch;
    private PushDiainfoManager objPushMgr;
    private RegistSearchSSO objRegistSearch;
    private DiainfoSearch objSearch;
    private PopupNew popup;
    protected ProgressDialog progressDialog;
    protected Bundle pushSettings;
    protected Bundle registinfo;
    private TextView txtAroundNow;

    public DiainfoActivity()
    {
        bRegistCompleted = false;
        bDiainfoCompleted = false;
        bNearStationCompleted = false;
        bPushSettingsCompleted = false;
        bRetry = false;
        bError = false;
    }

    private void cancelAroundSearch()
    {
        if (!bNearStationCompleted)
        {
            bNearStationCompleted = true;
            lsNoArround.setVisibility(0);
            txtAroundNow.setVisibility(8);
            setCompleted();
        }
    }

    private void getPushDiainfoSetting()
    {
        jp.co.yahoo.yconnect.core.oauth2.BearerToken bearertoken = TransitUtil.getAccessToken(this);
        String s = TransitUtil.getYID(this);
        if (bearertoken == null || TransitUtil.isEmpty(s))
        {
            bPushSettingsCompleted = true;
            return;
        } else
        {
            objPushMgr = new PushDiainfoManager(this);
            objPushMgr.getPushUsersTopicid(bearertoken, s, true, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final DiainfoActivity this$0;

                public boolean onCanceled()
                {
                    bPushSettingsCompleted = true;
                    setCompleted();
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    bPushSettingsCompleted = true;
                    setCompleted();
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    pushSettings = bundle;
                    bPushSettingsCompleted = true;
                    setCompleted();
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
            });
            return;
        }
    }

    private void showOthersPushDiainfo()
    {
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/OthersPushDiainfoActivity), getResources().getInteger(0x7f0c0043));
    }

    public void aroundSearch()
    {
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01c4), "");
        bundle.putString("results", "21");
        bundle.putString("dist", "2");
        objLocSearch = new LocationSearch(this, this);
        objLocSearch.setErroMsg(false);
        objLocSearch.setDialogMessage(false, null);
        objLocSearch.getCurrentStation(bundle);
    }

    public void aroundSearch(View view)
    {
        bRetry = true;
        lsNoArround.setVisibility(8);
        txtAroundNow.setVisibility(0);
        if (progressDialog != null && !progressDialog.isShowing() && !isFinishing())
        {
            progressDialog.show();
        }
        aroundSearch();
    }

    protected ArrayList getDiainfoList()
    {
        ArrayList arraylist = new ArrayList();
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01db), getString(0x7f0d00e4));
        bundle.putString(getString(0x7f0d01da), getString(0x7f0d0573));
        Bundle bundle1;
        if (diainfo != null && diainfo.containsKey(getString(0x7f0d0573)))
        {
            bundle.putBoolean(getString(0x7f0d01ad), true);
        } else
        {
            bundle.putBoolean(getString(0x7f0d01ad), false);
        }
        arraylist.add(bundle);
        bundle1 = new Bundle();
        bundle1.putString(getString(0x7f0d01db), getString(0x7f0d00e3));
        bundle1.putString(getString(0x7f0d01da), getString(0x7f0d0572));
        if (diainfo != null && diainfo.containsKey(getString(0x7f0d0572)))
        {
            bundle1.putBoolean(getString(0x7f0d01ad), true);
        } else
        {
            bundle1.putBoolean(getString(0x7f0d01ad), false);
        }
        arraylist.add(bundle1);
        return arraylist;
    }

    public void loginRequest(View view)
    {
        TransitUtil.login(this);
    }

    protected void lounchDiaInfo(String s)
    {
        Intent intent;
        if (s.equals(getString(0x7f0d0572)))
        {
            intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoRailList);
        } else
        {
            intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoAreaListActivity);
        }
        intent.putExtra(getString(0x7f0d01da), s);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0063));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == -1 && i == getResources().getInteger(0x7f0c0043))
        {
            if (objPushMgr == null)
            {
                Intent intent1 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
                intent1.setAction("android.intent.action.VIEW");
                startActivityInCurrentMenu(intent1);
                finish();
            } else
            {
                PushSubscriptionDB pushsubscriptiondb = new PushSubscriptionDB(this);
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", pushsubscriptiondb.getDiainfoAll());
                if (!objPushMgr.equalsDiainfoFlag(pushSettings, bundle))
                {
                    Intent intent2 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
                    intent2.setAction("android.intent.action.VIEW");
                    startActivityInCurrentMenu(intent2);
                    finish();
                    return;
                }
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030018);
        setTitle(getString(0x7f0d00f7));
        dispAd(this, "2080078824", "pv,bottom");
        lsNologin = (LinearLayout)findViewById(0x7f0a0068);
        lsNoArround = (LinearLayout)findViewById(0x7f0a006c);
        txtAroundNow = (TextView)findViewById(0x7f0a006d);
        ((Button)findViewById(0x7f0a01f3)).setOnClickListener(new android.view.View.OnClickListener() {

            final DiainfoActivity this$0;

            public void onClick(View view)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0423)).append("/").append(getString(0x7f0d03f4)).toString());
                TransitUtil.openURL(DiainfoActivity.this, getString(0x7f0d056c));
            }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
        });
        aroundSearch(lsArround);
        objSearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final DiainfoActivity this$0;

            public boolean onCanceled()
            {
                bDiainfoCompleted = true;
                setCompleted();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                bDiainfoCompleted = true;
                bError = true;
                setCompleted();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                bDiainfoCompleted = true;
                diainfo = objSearch.getResult();
                showDiainfoList(diainfo);
                setCompleted();
                return false;
            }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
        });
        objSearch.setDiainfo("true");
        objSearch.setDetail("full");
        objSearch.setDetailAnalyze(true);
        objSearch.setDialogDisplay(false);
        setRegist();
        getPushDiainfoSetting();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        progressDialog.setMessage(getString(0x7f0d04a2));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        if (!isFinishing())
        {
            progressDialog.show();
        }
        objSearch.request();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d03ad)).setIcon(0x7f020109), 1);
        if (PopupNew.isShow(this, getString(0x7f0d03a5), -1))
        {
            (new Handler()).post(new Runnable() {

                final DiainfoActivity this$0;

                public void run()
                {
                    View view = findViewById(0);
                    if (view != null)
                    {
                        float f = view.getWidth();
                        float f1 = TransitUtil.dpToPx(DiainfoActivity.this, 133F);
                        float f2 = TransitUtil.dpToPx(DiainfoActivity.this, 7F);
                        float f3 = f - f1 - f2;
                        popup = new PopupNew(DiainfoActivity.this, view);
                        popup.display((int)f3, 0, -1, getString(0x7f0d03a5), 0x7f02029e, false);
                    }
                }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
            });
        }
        return true;
    }

    public void onError(String s, String s1)
    {
        cancelAroundSearch();
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
            intent.setAction("android.intent.action.VIEW");
            startActivityInCurrentMenu(intent);
            finish();
        }
    }

    protected void onOpenSlideMenu()
    {
        super.onOpenSlideMenu();
        if (popup != null)
        {
            popup.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        super.onOptionsItemSelected(menuitem);
        menuitem.getItemId();
        JVM INSTR tableswitch 0 0: default 32
    //                   0 34;
           goto _L1 _L2
_L1:
        return true;
_L2:
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0423)).append("/").append(getString(0x7f0d0409)).toString());
        if (popup != null)
        {
            popup.dismiss();
        }
        showOthersPushDiainfo();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onPause()
    {
        super.onPause();
        if (popup != null)
        {
            popup.dismiss();
        }
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
            if (!bDiainfoCompleted)
            {
                bDiainfoCompleted = true;
                setCompleted();
            }
        }
        if (objLocSearch != null)
        {
            objLocSearch.stopRequest();
            cancelAroundSearch();
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        bNearStationCompleted = true;
        nearStations = bundle;
        if (bundle != null)
        {
            nearStations = bundle.getBundle(getString(0x7f0d0240));
        }
        setCompleted();
    }

    public void onTimeout(String s, String s1)
    {
        cancelAroundSearch();
    }

    protected void setCompleted()
    {
        if (bDiainfoCompleted && bRegistCompleted && bPushSettingsCompleted)
        {
            try
            {
                progressDialog.dismiss();
            }
            catch (IllegalArgumentException illegalargumentexception) { }
            showRegistInfoList();
        }
        if (bNearStationCompleted && bDiainfoCompleted)
        {
            showNearStationList();
        }
        if (bNearStationCompleted && bDiainfoCompleted && bRegistCompleted && bPushSettingsCompleted && bError)
        {
            showErrorMessageDialog(getString(0x7f0d00b2), getString(0x7f0d015e));
            bError = false;
        }
    }

    protected void setRegist()
    {
        jp.co.yahoo.yconnect.core.oauth2.BearerToken bearertoken = TransitUtil.getAccessToken(this);
        if (bearertoken == null)
        {
            bRegistCompleted = true;
            if (lsRegist != null)
            {
                lsRegist.setVisibility(8);
            }
            lsNologin.setVisibility(0);
            setDiainfo(false);
            return;
        } else
        {
            objRegistSearch = new RegistSearchSSO(this, bearertoken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final DiainfoActivity this$0;

                public boolean onCanceled()
                {
                    bRegistCompleted = true;
                    if (lsRegist != null)
                    {
                        lsRegist.setVisibility(8);
                    }
                    lsNologin.setVisibility(0);
                    bError = true;
                    setCompleted();
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    bRegistCompleted = true;
                    if (lsRegist != null)
                    {
                        lsRegist.setVisibility(8);
                    }
                    lsNologin.setVisibility(0);
                    bError = true;
                    setCompleted();
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    registinfo = bundle;
                    bRegistCompleted = true;
                    LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0067);
                    if (registinfo == null)
                    {
                        linearlayout.setVisibility(0);
                    } else
                    {
                        linearlayout.setVisibility(8);
                    }
                    setCompleted();
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
            });
            objRegistSearch.setSearchType(getString(0x7f0d01d5));
            objRegistSearch.setWaitMsg(false);
            objRegistSearch.requestAsync(false);
            return;
        }
    }

    protected void showDiainfoList(Bundle bundle)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0062);
        if (bundle == null)
        {
            return;
        }
        Iterator iterator = getDiainfoList().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (!((Bundle)iterator.next()).getBoolean(getString(0x7f0d01ad)))
            {
                continue;
            }
            ((ImageView)linearlayout.findViewById(0x7f0a0065)).setVisibility(0);
            break;
        } while (true);
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            final DiainfoActivity this$0;

            public void onClick(View view)
            {
                touchTapRD((new StringBuilder()).append(getString(0x7f0d0416)).append("/").append(getString(0x7f0d03c5)).toString());
                Intent intent = new Intent(DiainfoActivity.this, jp/co/yahoo/android/apps/transit/DiainfoAllCategory);
                startActivityForResult(intent, getResources().getInteger(0x7f0c003d));
            }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
        });
    }

    protected void showNearStationList()
    {
        if (nearStations != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        LayoutInflater layoutinflater;
        ArrayList arraylist;
        int i;
        if (lsArround == null)
        {
            lsArround = (LinearLayout)findViewById(0x7f0a006b);
        } else
        if (!bRetry)
        {
            return;
        }
        lsNoArround.setVisibility(8);
        txtAroundNow.setVisibility(8);
        layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
        arraylist = new ArrayList();
        i = 0;
        while (i < nearStations.size()) 
        {
            DiainfoData diainfodata2 = ((StationData)nearStations.getSerializable(Integer.toString(i))).getDiainfo();
            if (diainfodata2 != null && diainfodata2.getRailCode() != null)
            {
                Iterator iterator3 = arraylist.iterator();
                boolean flag3;
                do
                {
                    boolean flag2 = iterator3.hasNext();
                    flag3 = false;
                    if (!flag2)
                    {
                        break;
                    }
                    DiainfoData diainfodata3 = (DiainfoData)iterator3.next();
                    if (diainfodata3 == null || diainfodata3.getRailCode() == null || !diainfodata3.getRailCode().equals(diainfodata2.getRailCode()))
                    {
                        continue;
                    }
                    flag3 = true;
                    break;
                } while (true);
                if (!flag3)
                {
                    arraylist.add(diainfodata2);
                }
            }
            i++;
        }
        Bundle bundle = diainfo;
        Bundle bundle1 = null;
        if (bundle != null)
        {
            bundle1 = diainfo.getBundle(getString(0x7f0d0573));
        }
        Iterator iterator = arraylist.iterator();
        while (iterator.hasNext()) 
        {
            DiainfoData diainfodata = (DiainfoData)iterator.next();
            boolean flag = false;
            if (bundle1 != null)
            {
                boolean flag1 = bundle1.containsKey(diainfodata.getRailAreaCode());
                flag = false;
                if (flag1)
                {
                    Bundle bundle2 = bundle1.getBundle(diainfodata.getRailAreaCode());
                    Iterator iterator1 = bundle2.keySet().iterator();
label0:
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        Bundle bundle3 = bundle2.getBundle((String)iterator1.next());
                        Iterator iterator2 = bundle3.keySet().iterator();
                        DiainfoData diainfodata1;
                        do
                        {
                            if (!iterator2.hasNext())
                            {
                                continue label0;
                            }
                            diainfodata1 = (DiainfoData)bundle3.getSerializable((String)iterator2.next());
                        } while (!diainfodata.getRailCode().equals(diainfodata1.getRailCode()) || !diainfodata.getRailRangeCode().equals(diainfodata1.getRailRangeCode()));
                        flag = true;
                    } while (true);
                }
            }
            View view = layoutinflater.inflate(0x7f03006e, null);
            ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
            lsArround.addView(view);
            lsArround.addView(imageview);
            ((TextView)view.findViewById(0x7f0a021d)).setText(diainfodata.getRailName());
            if (flag)
            {
                ((ImageView)view.findViewById(0x7f0a021e)).setVisibility(0);
            }
            view.setClickable(true);
            view.setTag(diainfodata);
            android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                final DiainfoActivity this$0;

                public void onClick(View view1)
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0416)).append("/").append(getString(0x7f0d03e8)).toString());
                    Intent intent = new Intent(DiainfoActivity.this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
                    DiainfoData diainfodata4 = (DiainfoData)view1.getTag();
                    Bundle bundle4 = new Bundle();
                    bundle4.putString(getString(0x7f0d01d9), diainfodata4.getRailCode());
                    bundle4.putString(getString(0x7f0d01a8), diainfodata4.getCpId());
                    bundle4.putString(getString(0x7f0d01dc), diainfodata4.getRailRangeCode());
                    intent.putExtra(getString(0x7f0d022c), bundle4);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c0040));
                }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
            };
            view.setOnClickListener(onclicklistener);
            lsArround.setVisibility(0);
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void showRegistInfoList()
    {
_L2:
        return;
        if (registinfo == null || lsRegist != null) goto _L2; else goto _L1
_L1:
        lsRegist = (LinearLayout)findViewById(0x7f0a0066);
        if (lsNologin != null && lsNologin.getVisibility() == 0)
        {
            lsNologin.setVisibility(8);
        }
        LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
        Bundle bundle = diainfo;
        Bundle bundle1 = null;
        Bundle bundle2 = null;
        if (bundle != null)
        {
            bundle2 = diainfo.getBundle(getString(0x7f0d0573));
            bundle1 = diainfo.getBundle(getString(0x7f0d0572));
        }
        DiainfoData diainfodata = null;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = objPushMgr.getDiainfoStopAllFlag(pushSettings);
        int i = 0;
        do
        {
            int j = registinfo.size();
            if (i >= j)
            {
                continue;
            }
            DiainfoData diainfodata1;
            boolean flag3;
label0:
            {
                diainfodata1 = (DiainfoData)registinfo.getSerializable(Integer.toString(i));
                flag3 = false;
                if (bundle2 != null)
                {
                    Iterator iterator2 = bundle2.keySet().iterator();
                    do
                    {
                        if (!iterator2.hasNext())
                        {
                            break;
                        }
                        Bundle bundle3 = bundle2.getBundle((String)iterator2.next());
                        Iterator iterator3 = bundle3.keySet().iterator();
label1:
                        do
                        {
                            if (!iterator3.hasNext())
                            {
                                break;
                            }
                            Bundle bundle4 = bundle3.getBundle((String)iterator3.next());
                            Iterator iterator4 = bundle4.keySet().iterator();
                            do
                            {
                                if (!iterator4.hasNext())
                                {
                                    continue label1;
                                }
                                diainfodata = (DiainfoData)bundle4.getSerializable((String)iterator4.next());
                            } while (!diainfodata1.getRailCode().equals(diainfodata.getRailCode()) || !diainfodata1.getRailRangeCode().equals(diainfodata.getRailRangeCode()));
                            flag3 = true;
                        } while (!flag3);
                    } while (!flag3);
                }
                if (bundle1 == null || flag3)
                {
                    break label0;
                }
                Iterator iterator1 = bundle1.keySet().iterator();
                DiainfoData diainfodata2;
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break label0;
                    }
                    diainfodata2 = (DiainfoData)bundle1.getSerializable((String)iterator1.next());
                } while (!diainfodata1.getRailCode().equals(diainfodata2.getRailCode()));
                flag3 = true;
            }
            if (!flag2)
            {
                flag1 = objPushMgr.getDiainfoFlag(pushSettings, diainfodata1)[1];
            }
            View view;
            if (flag3 && diainfodata != null)
            {
                flag = true;
                view = layoutinflater.inflate(0x7f030071, null);
                ((ImageView)view.findViewById(0x7f0a021e)).setVisibility(0);
                TextView textview1 = (TextView)view.findViewById(0x7f0a0064);
                ArrayList arraylist = diainfodata.getDetailinfo();
                if (arraylist != null && arraylist.size() > 1)
                {
                    jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail diainfodatadetail1 = null;
                    String s1 = null;
                    Iterator iterator = arraylist.iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail diainfodatadetail2 = (jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail)iterator.next();
                        if (s1 == null)
                        {
                            s1 = diainfodatadetail2.getUpdateDate();
                            diainfodatadetail1 = diainfodatadetail2;
                        } else
                        {
                            String s4 = diainfodatadetail2.getUpdateDate();
                            if (s1.compareTo(s4) < 0)
                            {
                                s1 = diainfodatadetail2.getUpdateDate();
                                diainfodatadetail1 = diainfodatadetail2;
                            }
                        }
                    } while (true);
                    ImageView imageview;
                    TextView textview;
                    android.view.View.OnClickListener onclicklistener;
                    String s2;
                    StringBuilder stringbuilder;
                    String s3;
                    Object aobj[];
                    if (diainfodatadetail1.getStatusCode() == "000200010005")
                    {
                        s2 = getString(0x7f0d00f6);
                    } else
                    {
                        s2 = diainfodatadetail1.getStatus();
                    }
                    stringbuilder = (new StringBuilder()).append(s2).append("\uFF08");
                    s3 = getString(0x7f0d00e0);
                    aobj = new Object[1];
                    aobj[0] = Integer.toString(-1 + arraylist.size());
                    textview1.setText(stringbuilder.append(String.format(s3, aobj)).append("\uFF09").toString());
                } else
                if (arraylist != null && arraylist.size() == 1)
                {
                    jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail diainfodatadetail = (jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail)arraylist.get(0);
                    String s;
                    if (diainfodatadetail.getStatusCode() == "000200010005")
                    {
                        s = getString(0x7f0d00f6);
                    } else
                    {
                        s = diainfodatadetail.getStatus();
                    }
                    textview1.setText(s);
                } else
                {
                    view = layoutinflater.inflate(0x7f03006e, null);
                }
            } else
            {
                view = layoutinflater.inflate(0x7f03006e, null);
            }
            imageview = (ImageView)layoutinflater.inflate(0x7f030059, null);
            lsRegist.addView(view);
            lsRegist.addView(imageview);
            textview = (TextView)view.findViewById(0x7f0a021d);
            textview.setText(diainfodata1.getRailName());
            if (flag1)
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0201cf, 0);
            }
            view.setClickable(true);
            view.setTag(diainfodata1);
            onclicklistener = new android.view.View.OnClickListener() {

                final DiainfoActivity this$0;

                public void onClick(View view1)
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d0416)).append("/").append(getString(0x7f0d040d)).toString());
                    Intent intent = new Intent(DiainfoActivity.this, jp/co/yahoo/android/apps/transit/DiainfoDetailActivity);
                    DiainfoData diainfodata3 = (DiainfoData)view1.getTag();
                    Bundle bundle5 = new Bundle();
                    bundle5.putString(getString(0x7f0d01d9), diainfodata3.getRailCode());
                    bundle5.putString(getString(0x7f0d01a8), diainfodata3.getCpId());
                    bundle5.putString(getString(0x7f0d01dc), diainfodata3.getRailRangeCode());
                    intent.putExtra(getString(0x7f0d022c), bundle5);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c0040));
                }

            
            {
                this$0 = DiainfoActivity.this;
                super();
            }
            };
            view.setOnClickListener(onclicklistener);
            lsRegist.setVisibility(0);
            if (flag)
            {
                setDiainfo(true);
            } else
            {
                setDiainfo(false);
            }
            i++;
        } while (true);
        if (true) goto _L2; else goto _L3
_L3:
    }


/*
    static boolean access$002(DiainfoActivity diainfoactivity, boolean flag)
    {
        diainfoactivity.bDiainfoCompleted = flag;
        return flag;
    }

*/



/*
    static boolean access$202(DiainfoActivity diainfoactivity, boolean flag)
    {
        diainfoactivity.bError = flag;
        return flag;
    }

*/



/*
    static PopupNew access$302(DiainfoActivity diainfoactivity, PopupNew popupnew)
    {
        diainfoactivity.popup = popupnew;
        return popupnew;
    }

*/


/*
    static boolean access$402(DiainfoActivity diainfoactivity, boolean flag)
    {
        diainfoactivity.bRegistCompleted = flag;
        return flag;
    }

*/




/*
    static boolean access$702(DiainfoActivity diainfoactivity, boolean flag)
    {
        diainfoactivity.bPushSettingsCompleted = flag;
        return flag;
    }

*/
}
