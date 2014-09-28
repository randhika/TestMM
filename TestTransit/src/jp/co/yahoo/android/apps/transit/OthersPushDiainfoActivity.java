// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.PushSubscriptionDB;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersPushNoticeActivity, DiainfoAllCategory

public class OthersPushDiainfoActivity extends TransitBaseActivity
    implements android.widget.CompoundButton.OnCheckedChangeListener, jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener
{

    private boolean allFlag;
    private Button btnSet;
    private CheckListView chkList;
    private PushSubscriptionDB db;
    private LinearLayout detailSetting;
    private jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener getPushListener;
    private boolean isCanceledLogin;
    private boolean isFirstSpinner;
    private boolean isFirstToggle;
    private boolean isGettingPush;
    private boolean isSettingPush;
    private LinearLayout noRegist;
    private int prefTime;
    private PushDiainfoManager pushMgr;
    private Bundle pushSettings;
    private Bundle registInfo;
    private RegistSearchSSO registSearch;
    private Boolean restoreAllFlag;
    private Spinner timeSpnr;
    private CompoundButton toggle;
    private TextView txtSet;

    public OthersPushDiainfoActivity()
    {
        allFlag = false;
        restoreAllFlag = null;
        isGettingPush = false;
        isSettingPush = false;
        isCanceledLogin = false;
        isFirstSpinner = true;
        isFirstToggle = true;
        getPushListener = new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final OthersPushDiainfoActivity this$0;

            public boolean onCanceled()
            {
                isGettingPush = false;
                closeProgressDialog();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                isGettingPush = false;
                closeProgressDialog();
                if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                {
                    TransitUtil.logout(OthersPushDiainfoActivity.this);
                    getPushDiainfoSetting();
                    return false;
                } else
                {
                    showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                    return false;
                }
            }

            public boolean onSuccess(Bundle bundle)
            {
                isGettingPush = false;
                pushSettings = bundle;
                if (restoreAllFlag != null)
                {
                    allFlag = restoreAllFlag.booleanValue();
                    restoreAllFlag = null;
                } else
                {
                    boolean flag = pushMgr.getDiainfoStopAllFlag(bundle);
                    OthersPushDiainfoActivity otherspushdiainfoactivity = OthersPushDiainfoActivity.this;
                    boolean flag1;
                    if (!flag)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    otherspushdiainfoactivity.allFlag = flag1;
                }
                if (toggle.isChecked() == allFlag)
                {
                    onCheckedChanged(null, allFlag);
                    return false;
                } else
                {
                    toggle.setChecked(allFlag);
                    return false;
                }
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
        };
    }

    private void getPushDiainfoSetting()
    {
        isGettingPush = true;
        taken = TransitUtil.getAccessToken(this);
        String s = TransitUtil.getYID(this);
        if (taken == null || TransitUtil.isEmpty(s))
        {
            (new Handler()).post(new Runnable() {

                final OthersPushDiainfoActivity this$0;

                public void run()
                {
                    if (isCanceledLogin)
                    {
                        isCanceledLogin = false;
                        return;
                    } else
                    {
                        TransitUtil.login(OthersPushDiainfoActivity.this);
                        return;
                    }
                }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
            });
            return;
        } else
        {
            showProgressDialog();
            pushMgr.getPushUsersTopicid(taken, s, true, getPushListener);
            return;
        }
    }

    private void getRegisteredRail(boolean flag)
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            if (flag)
            {
                TransitUtil.login(this);
            }
            return;
        } else
        {
            showProgressDialog();
            registSearch = new RegistSearchSSO(this, taken, this);
            registSearch.setSearchType(getString(0x7f0d01d5));
            registSearch.setMethod("GET");
            registSearch.requestAsync(false);
            return;
        }
    }

    private void setButtonEnabled(boolean flag)
    {
        toggle.setEnabled(flag);
        timeSpnr.setEnabled(flag);
    }

    private void setSetButtonEnabled(boolean flag)
    {
        btnSet.setEnabled(flag);
    }

    private void showPushSetting(boolean flag)
    {
        if (!allFlag)
        {
            detailSetting.setVisibility(8);
            setButtonEnabled(true);
            closeProgressDialog();
            return;
        }
        if (registInfo != null) goto _L2; else goto _L1
_L1:
        setButtonEnabled(false);
_L4:
        closeProgressDialog();
        return;
_L2:
        int i;
        detailSetting.setVisibility(0);
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = registInfo.size();
        if (i >= 1)
        {
            break; /* Loop/switch isn't completed */
        }
        noRegist.setVisibility(0);
        if (chkList != null)
        {
            chkList.removeAllViews();
        }
_L5:
        setButtonEnabled(true);
        if (true) goto _L4; else goto _L3
_L3:
        noRegist.setVisibility(8);
        ArrayList arraylist = new ArrayList(i);
        ArrayList arraylist1 = new ArrayList(i);
        int j = 0;
        while (j < i) 
        {
            DiainfoData diainfodata = (DiainfoData)registInfo.getSerializable(Integer.toString(j));
            arraylist.add(diainfodata);
            if (pushSettings == null)
            {
                arraylist1.add(Boolean.valueOf(false));
            } else
            {
                arraylist1.add(Boolean.valueOf(pushMgr.getDiainfoFlag(pushSettings, diainfodata)[1]));
            }
            j++;
        }
        if (chkList == null)
        {
            chkList = new CheckListView(this);
            chkList.setOnCheckedChangeListener(new jp.co.yahoo.android.apps.transit.viewparts.CheckListView.OnItemCheckedChangeListener() {

                final OthersPushDiainfoActivity this$0;

                public void onCheckedChanged(CompoundButton compoundbutton, boolean flag1)
                {
                    setSetButtonEnabled(true);
                }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
            });
            detailSetting.addView(chkList);
        }
        chkList.removeAllViews();
        chkList.setListItems(arraylist);
        chkList.setListChecks(arraylist1);
        chkList.showView();
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public void cancel(View view)
    {
        finish();
    }

    public void launchStation(View view)
    {
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/OthersPushNoticeActivity), getResources().getInteger(0x7f0c0041));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i == getResources().getInteger(0x7f0c003d) && j == -1)
        {
            getRegisteredRail(true);
        }
    }

    public boolean onCanceled()
    {
        closeProgressDialog();
        return false;
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        allFlag = flag;
        if (isFirstToggle)
        {
            isFirstToggle = false;
        } else
        {
            setSetButtonEnabled(true);
        }
        if (flag)
        {
            if (registInfo == null)
            {
                getRegisteredRail(true);
            } else
            {
                showPushSetting(false);
            }
            txtSet.setText(0x7f0d03b9);
            return;
        } else
        {
            showPushSetting(true);
            txtSet.setText(0x7f0d03ba);
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002c);
        setTitle(getString(0x7f0d03ad));
        pushMgr = new PushDiainfoManager(this);
        prefTime = pushMgr.loadPushTime();
        txtSet = (TextView)findViewById(0x7f0a0112);
        toggle = (CompoundButton)findViewById(0x7f0a0135);
        toggle.setOnCheckedChangeListener(this);
        String as[] = new String[2];
        as[0] = getString(0x7f0d03bc);
        as[1] = getString(0x7f0d03bd);
        timeSpnr = (Spinner)findViewById(0x7f0a0137);
        ArrayAdapter arrayadapter = new ArrayAdapter(this, 0x1090008, as);
        arrayadapter.setDropDownViewResource(0x7f030077);
        timeSpnr.setAdapter(arrayadapter);
        timeSpnr.setSelection(prefTime);
        timeSpnr.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final OthersPushDiainfoActivity this$0;

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                prefTime = i;
                if (isFirstSpinner)
                {
                    isFirstSpinner = false;
                    return;
                } else
                {
                    setSetButtonEnabled(true);
                    return;
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
        });
        btnSet = (Button)findViewById(0x7f0a013a);
        noRegist = (LinearLayout)findViewById(0x7f0a0134);
        detailSetting = (LinearLayout)findViewById(0x7f0a0073);
        setSetButtonEnabled(false);
        setButtonEnabled(false);
        if (pushMgr.checkPushEnable(this))
        {
            getPushDiainfoSetting();
        }
        dispAd(this, "2080342781", "pv");
    }

    public boolean onError(APIError apierror)
    {
        closeProgressDialog();
        registInfo = null;
        if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
        {
            TransitUtil.login(this);
        } else
        {
            showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
            showPushSetting(true);
        }
        return false;
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            if (isGettingPush)
            {
                isGettingPush = false;
                getPushDiainfoSetting();
                return;
            }
            if (isSettingPush)
            {
                isSettingPush = false;
                saveAndBack(null);
                return;
            } else
            {
                getRegisteredRail(true);
                return;
            }
        } else
        {
            isGettingPush = false;
            isSettingPush = false;
            isCanceledLogin = true;
            return;
        }
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        prefTime = bundle.getInt("prefTime");
        timeSpnr.setSelection(prefTime);
        restoreAllFlag = Boolean.valueOf(bundle.getBoolean("allFlag"));
        isGettingPush = bundle.getBoolean("isGettingPush");
        isSettingPush = bundle.getBoolean("isSettingPush");
    }

    protected void onResume()
    {
        HashMap hashmap;
label0:
        {
            super.onResume();
            if (toggle.isChecked() && registInfo != null)
            {
                getRegisteredRail(false);
                if (db == null)
                {
                    db = new PushSubscriptionDB(this);
                }
                hashmap = db.getDiainfoAll();
                if (!hashmap.isEmpty())
                {
                    break label0;
                }
                pushSettings = null;
            }
            return;
        }
        if (pushSettings == null)
        {
            pushSettings = new Bundle();
            pushSettings.putSerializable("result", hashmap);
            return;
        } else
        {
            pushSettings.remove("result");
            pushSettings.putSerializable("result", hashmap);
            return;
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt("prefTime", prefTime);
        bundle.putBoolean("allFlag", allFlag);
        bundle.putBoolean("isGettingPush", isGettingPush);
        bundle.putBoolean("isSettingPush", isSettingPush);
    }

    public boolean onSuccess(Bundle bundle)
    {
        closeProgressDialog();
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        registInfo = bundle;
        showPushSetting(true);
        return false;
    }

    public volatile boolean onSuccess(Object obj)
    {
        return onSuccess((Bundle)obj);
    }

    public void registerRail(View view)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/DiainfoAllCategory);
        intent.putExtra(getString(0x7f0d01af), true);
        startActivityForResult(intent, getResources().getInteger(0x7f0c003d));
    }

    public void saveAndBack(View view)
    {
        touchTapRD(getString(0x7f0d03d4));
        isSettingPush = true;
        taken = TransitUtil.getAccessToken(this);
        String s = TransitUtil.getYID(this);
        if (taken == null || TransitUtil.isEmpty(s))
        {
            TransitUtil.login(this);
            return;
        }
        showProgressDialog();
        boolean flag = allFlag;
        ArrayList arraylist = null;
        ArrayList arraylist1 = null;
        if (flag)
        {
            if (chkList == null)
            {
                arraylist = new ArrayList();
                arraylist1 = new ArrayList();
            } else
            {
                ArrayList arraylist2 = chkList.getCheckItems();
                arraylist = new ArrayList(arraylist2.size());
                for (Iterator iterator = arraylist2.iterator(); iterator.hasNext(); arraylist.add((DiainfoData)iterator.next())) { }
                ArrayList arraylist3 = chkList.getNoCheckItems();
                arraylist1 = new ArrayList(arraylist3.size());
                Iterator iterator1 = arraylist3.iterator();
                while (iterator1.hasNext()) 
                {
                    arraylist1.add((DiainfoData)iterator1.next());
                }
            }
        }
        pushMgr.registerPushMulti(taken, allFlag, arraylist, arraylist1, new jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener() {

            final OthersPushDiainfoActivity this$0;

            public void onCanceled()
            {
                isSettingPush = false;
                closeProgressDialog();
            }

            public void onError(int i, String s1, String s2, String s3)
            {
                isSettingPush = false;
                closeProgressDialog();
                if (s1 != null && s1.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                {
                    TransitUtil.logout(OthersPushDiainfoActivity.this);
                    saveAndBack(null);
                    return;
                } else
                {
                    showErrorMessageDialog(s3, s2);
                    return;
                }
            }

            public void onSuccess(String s1, String s2)
            {
                isSettingPush = false;
                pushMgr.savePushTime(prefTime);
                closeProgressDialog();
                if (isFinishing())
                {
                    return;
                } else
                {
                    (new TransitDialogBuilder(OthersPushDiainfoActivity.this)).setMessage(s2).setTitleInfo(s1).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                        final _cls2 this$1;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.cancel();
                            setResult(-1);
                            finish();
                        }

            
            {
                this$1 = _cls2.this;
                super();
            }
                    }).show();
                    return;
                }
            }

            
            {
                this$0 = OthersPushDiainfoActivity.this;
                super();
            }
        });
    }



/*
    static int access$002(OthersPushDiainfoActivity otherspushdiainfoactivity, int i)
    {
        otherspushdiainfoactivity.prefTime = i;
        return i;
    }

*/




/*
    static boolean access$102(OthersPushDiainfoActivity otherspushdiainfoactivity, boolean flag)
    {
        otherspushdiainfoactivity.isFirstSpinner = flag;
        return flag;
    }

*/




/*
    static boolean access$302(OthersPushDiainfoActivity otherspushdiainfoactivity, boolean flag)
    {
        otherspushdiainfoactivity.isSettingPush = flag;
        return flag;
    }

*/




/*
    static boolean access$502(OthersPushDiainfoActivity otherspushdiainfoactivity, boolean flag)
    {
        otherspushdiainfoactivity.isCanceledLogin = flag;
        return flag;
    }

*/


/*
    static boolean access$602(OthersPushDiainfoActivity otherspushdiainfoactivity, boolean flag)
    {
        otherspushdiainfoactivity.isGettingPush = flag;
        return flag;
    }

*/


/*
    static Bundle access$702(OthersPushDiainfoActivity otherspushdiainfoactivity, Bundle bundle)
    {
        otherspushdiainfoactivity.pushSettings = bundle;
        return bundle;
    }

*/



/*
    static Boolean access$802(OthersPushDiainfoActivity otherspushdiainfoactivity, Boolean boolean1)
    {
        otherspushdiainfoactivity.restoreAllFlag = boolean1;
        return boolean1;
    }

*/



/*
    static boolean access$902(OthersPushDiainfoActivity otherspushdiainfoactivity, boolean flag)
    {
        otherspushdiainfoactivity.allFlag = flag;
        return flag;
    }

*/
}
