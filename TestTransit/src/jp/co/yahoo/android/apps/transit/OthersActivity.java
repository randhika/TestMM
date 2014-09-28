// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.RequestRecommend;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.History;
import jp.co.yahoo.android.apps.transit.db.PushSubscriptionDB;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            LoginBaseActivity, OthresAboutActivity, AlarmConfirm, DiainfoActivity, 
//            OthersPushDiainfoActivity, OthersEditStationActivity, OthersEditRailActivity, OthresCopyrightActivity, 
//            HistoryEdit, OthersAddressActivity

public class OthersActivity extends LoginBaseActivity
{

    private History history;
    private ImageView imgPushDiainfo;
    private ImageView imgResistRail;
    private ImageView imgResistStation;
    private boolean isDelHistory;
    private boolean isLogin;
    private LinearLayout lnNoLogin;
    private LinearLayout lnOnLogin;
    private LinearLayout lnPushDiainfo;
    private LinearLayout lnResistRail;
    private LinearLayout lnResistStation;
    private Context prefContext;
    private PushSubscriptionDB pushDb;
    private TextView txtPushDiainfo;
    private TextView txtPushDiainfoSet;
    private TextView txtResistRail;
    private TextView txtResistStation;

    public OthersActivity()
    {
        isDelHistory = false;
        isLogin = false;
    }

    public void aboutThisApp(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthresAboutActivity), getResources().getInteger(0x7f0c0037));
    }

    protected void changeLogin(boolean flag)
    {
        txtResistStation.setEnabled(flag);
        lnResistStation.setEnabled(flag);
        txtResistRail.setEnabled(flag);
        lnResistRail.setEnabled(flag);
        txtPushDiainfo.setEnabled(flag);
        lnPushDiainfo.setEnabled(flag);
        if (flag)
        {
            lnOnLogin.setVisibility(0);
            lnNoLogin.setVisibility(8);
            ((TextView)findViewById(0x7f0a0101)).setText((new StringBuffer()).append(m_YID).append(" ").append(getString(0x7f0d036d)));
            imgResistStation.setVisibility(0);
            txtResistStation.setText(0x7f0d0470);
            imgResistRail.setVisibility(0);
            txtResistRail.setText(0x7f0d0469);
            imgPushDiainfo.setVisibility(0);
            txtPushDiainfo.setText(0x7f0d03a9);
            txtPushDiainfoSet.setVisibility(0);
            if (pushDb == null)
            {
                pushDb = new PushSubscriptionDB(this);
            }
            if (pushDb.getDiainfoFlag("diainfo_stopAll"))
            {
                txtPushDiainfoSet.setText(getString(0x7f0d03ba));
                return;
            } else
            {
                txtPushDiainfoSet.setText(getString(0x7f0d03b9));
                return;
            }
        } else
        {
            lnOnLogin.setVisibility(8);
            lnNoLogin.setVisibility(0);
            imgResistStation.setVisibility(8);
            txtResistStation.setText(0x7f0d0473);
            imgResistRail.setVisibility(8);
            txtResistRail.setText(0x7f0d046e);
            imgPushDiainfo.setVisibility(8);
            txtPushDiainfo.setText(0x7f0d03aa);
            txtPushDiainfoSet.setVisibility(8);
            return;
        }
    }

    protected void changeLoginByState()
    {
        if (!TransitUtil.isEmpty(m_YID))
        {
            changeLogin(true);
            return;
        } else
        {
            changeLogin(false);
            return;
        }
    }

    public void delAlarm(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/AlarmConfirm), getResources().getInteger(0x7f0c0038));
    }

    public void delClickListener()
    {
        if (isDelHistory)
        {
            history.deleteAll();
        }
    }

    public void delHistory(View view)
    {
        if (history.isHistory())
        {
            isDelHistory = true;
            showdelMessageDialog(getString(0x7f0d00c9));
            return;
        } else
        {
            showSimpleErrorMessageDialog(getString(0x7f0d00cb));
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 != j) goto _L2; else goto _L1
_L1:
        if (getResources().getInteger(0x7f0c0037) != i) goto _L4; else goto _L3
_L3:
        if (intent.getIntExtra(getString(0x7f0d0181), 0) == getResources().getInteger(0x7f0c0023))
        {
            int k = intent.getIntExtra(getString(0x7f0d017d), 0);
            if (k > 0)
            {
                int l = getResources().getInteger(0x7f0c0001);
                Intent intent1 = null;
                if (k == l)
                {
                    intent1 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
                }
                if (intent1 != null)
                {
                    intent1.setAction("android.intent.action.VIEW");
                    startActivityInCurrentMenu(intent1);
                    finish();
                } else
                {
                    finish();
                }
            }
        }
_L2:
        if (i == 1100)
        {
            m_YID = null;
            isLogin = false;
            changeLoginByState();
        }
        return;
_L4:
        if (getResources().getInteger(0x7f0c0038) == i)
        {
            setAlerm();
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030025);
        setTitle(getString(0x7f0d0397));
        prefContext = this;
        history = new History(prefContext);
        ((TextView)findViewById(0x7f0a0117)).setText(getVersionName());
        txtResistStation = (TextView)findViewById(0x7f0a010b);
        imgResistStation = (ImageView)findViewById(0x7f0a010c);
        lnResistStation = (LinearLayout)findViewById(0x7f0a010a);
        txtResistRail = (TextView)findViewById(0x7f0a010e);
        imgResistRail = (ImageView)findViewById(0x7f0a010f);
        lnResistRail = (LinearLayout)findViewById(0x7f0a010d);
        txtPushDiainfo = (TextView)findViewById(0x7f0a0111);
        imgPushDiainfo = (ImageView)findViewById(0x7f0a0113);
        lnPushDiainfo = (LinearLayout)findViewById(0x7f0a0110);
        txtPushDiainfoSet = (TextView)findViewById(0x7f0a0112);
        lnOnLogin = (LinearLayout)findViewById(0x7f0a0100);
        lnNoLogin = (LinearLayout)findViewById(0x7f0a00ff);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            final OthersActivity this$0;

            public void onClick(View view)
            {
                isLogin = true;
                if (!TransitUtil.isEmpty(m_YID))
                {
                    logout();
                    return;
                } else
                {
                    login();
                    return;
                }
            }

            
            {
                this$0 = OthersActivity.this;
                super();
            }
        };
        Button button = (Button)findViewById(0x7f0a0069);
        Button button1 = (Button)findViewById(0x7f0a0102);
        button.setOnClickListener(onclicklistener);
        button1.setOnClickListener(onclicklistener);
        changeLoginByState();
        dispAd(this, "2080082925", "pv,bottom");
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            m_YID = TransitUtil.getYID(this);
            changeLoginByState();
        }
        isLogin = false;
    }

    protected void onLogout()
    {
        super.onLogout();
        m_YID = null;
        changeLoginByState();
    }

    public void onPushDiainfo(View view)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0436)).append("/").append(getString(0x7f0d0409)).toString());
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthersPushDiainfoActivity), getResources().getInteger(0x7f0c0043));
    }

    public void onRegistRail(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthersEditStationActivity), getResources().getInteger(0x7f0c0056));
    }

    public void onRegistStation(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthersEditRailActivity), getResources().getInteger(0x7f0c0055));
    }

    protected void onResume()
    {
        super.onResume();
        if (!isLogin)
        {
            m_YID = TransitUtil.getYID(this);
            changeLoginByState();
        }
        setAlerm();
    }

    public void openCopyright(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthresCopyrightActivity), getResources().getInteger(0x7f0c0037));
    }

    public void openEditHistory(View view)
    {
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/HistoryEdit), getResources().getInteger(0x7f0c0045));
    }

    public void openPoricy(View view)
    {
        TransitUtil.openURL(this, getString(0x7f0d0014));
    }

    public void openRecommend(View view)
    {
        RequestRecommend.recommend(this);
    }

    public void openSoftware(View view)
    {
        TransitUtil.openURL(this, getString(0x7f0d0018));
    }

    public void openTerm(View view)
    {
        TransitUtil.openURL(this, getString(0x7f0d001b));
    }

    public void setAlerm()
    {
        TextView textview = (TextView)findViewById(0x7f0a0106);
        ImageView imageview = (ImageView)findViewById(0x7f0a0107);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0a0105);
        ImageView imageview1 = (ImageView)findViewById(0x7f0a0108);
        TextView textview1 = (TextView)findViewById(0x7f0a0109);
        if (isAlarm())
        {
            relativelayout.setClickable(true);
            textview.setEnabled(true);
            imageview.setEnabled(true);
            imageview.setImageResource(0x7f02018b);
            imageview1.setVisibility(0);
            textview1.setVisibility(8);
            return;
        } else
        {
            relativelayout.setClickable(false);
            textview.setEnabled(false);
            imageview.setEnabled(false);
            imageview.setImageResource(0x7f02018a);
            imageview1.setVisibility(8);
            textview1.setVisibility(0);
            return;
        }
    }

    public void setMyAddress(View view)
    {
        startActivityForResult(new Intent(prefContext, jp/co/yahoo/android/apps/transit/OthersAddressActivity), getResources().getInteger(0x7f0c004a));
    }


/*
    static boolean access$002(OthersActivity othersactivity, boolean flag)
    {
        othersactivity.isLogin = flag;
        return flag;
    }

*/
}
