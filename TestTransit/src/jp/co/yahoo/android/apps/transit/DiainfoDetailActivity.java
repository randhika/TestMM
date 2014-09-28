// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.DiainfoSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            DiainfoBaseActivity

public class DiainfoDetailActivity extends DiainfoBaseActivity
    implements android.view.View.OnClickListener, jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener
{

    private Bundle condition;
    private DiainfoSearch diaSearch;
    private LayoutInflater inflater;
    private boolean isOnRegist;
    private boolean isOnRegistPush;
    private boolean isOnUnregistPush;
    private boolean isRegisteringPush;
    private boolean isUnregisteringPush;
    private PushDiainfoManager pushMgr;
    private Resources res;

    public DiainfoDetailActivity()
    {
    }

    private void getDiaInfo(boolean flag)
    {
        if (flag || diainfo == null)
        {
            diaSearch = new DiainfoSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

                final DiainfoDetailActivity this$0;

                public boolean onCanceled()
                {
                    showDiainfo();
                    if (isOnRegist)
                    {
                        isOnRegist = false;
                    } else
                    if (isOnRegistPush)
                    {
                        isOnRegistPush = false;
                        return false;
                    }
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                    showDiainfo();
                    if (isOnRegist)
                    {
                        isOnRegist = false;
                    } else
                    {
                        if (isOnRegistPush)
                        {
                            isOnRegistPush = false;
                            return false;
                        }
                        if (isOnUnregistPush)
                        {
                            isOnUnregistPush = false;
                            return false;
                        }
                    }
                    return false;
                }

                public boolean onSuccess(ApiBase apibase, Object obj)
                {
                    Bundle bundle = ((DiainfoSearch)apibase).getResult();
                    if (bundle != null) goto _L2; else goto _L1
_L1:
                    showErrorMessageDialog(getString(0x7f0d0133), getString(0x7f0d014f));
                    if (!isOnRegist) goto _L4; else goto _L3
_L3:
                    isOnRegist = false;
_L6:
                    return false;
_L4:
                    if (isOnRegistPush)
                    {
                        isOnRegistPush = false;
                        return false;
                    }
                    if (isOnUnregistPush)
                    {
                        isOnUnregistPush = false;
                        return false;
                    }
                    continue; /* Loop/switch isn't completed */
_L2:
                    diainfo = (DiainfoData)bundle.getSerializable("0");
                    showDiainfo();
                    if (isOnRegist)
                    {
                        postRegist(diainfo);
                        isOnRegist = false;
                        return false;
                    }
                    if (isOnRegistPush)
                    {
                        registerDiainfoPush(diainfo);
                        isOnRegistPush = false;
                        return false;
                    }
                    if (isOnUnregistPush)
                    {
                        unregisterDiainfoPush(diainfo);
                        isOnUnregistPush = false;
                        return false;
                    }
                    if (true) goto _L6; else goto _L5
_L5:
                }

            
            {
                this$0 = DiainfoDetailActivity.this;
                super();
            }
            });
            diaSearch.setDetail("full");
            diaSearch.setRail(condition.getString(getString(0x7f0d01d9)));
            diaSearch.setRange(condition.getString(getString(0x7f0d01dc)));
            diaSearch.setType(condition.getString(getString(0x7f0d01da)));
            diaSearch.setDetailAnalyze(true);
            diaSearch.request();
        }
    }

    private void registerDiainfoPush()
    {
        if (diainfo != null)
        {
            if (pushMgr == null)
            {
                pushMgr = new PushDiainfoManager(this);
            }
            if (pushMgr.checkPushEnable(this))
            {
                taken = TransitUtil.getAccessToken(this);
                if (taken == null)
                {
                    isRegisteringPush = true;
                    TransitUtil.login(this);
                    return;
                } else
                {
                    registerDiainfoPush(diainfo);
                    return;
                }
            }
        }
    }

    private void registerDiainfoPush(DiainfoData diainfodata)
    {
        showProgressDialog();
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        pushMgr.registerPushSingle(taken, diainfodata, this);
    }

    private void unregisterDiainfoPush()
    {
        if (diainfo == null)
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleInfo(getString(0x7f0d00fc)).setMessage(getString(0x7f0d00fb)).setPositiveButton(getString(0x7f0d0081), new android.content.DialogInterface.OnClickListener() {

                final DiainfoDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                    unregisterDiainfoPush(diainfo);
                }

            
            {
                this$0 = DiainfoDetailActivity.this;
                super();
            }
            }).setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

                final DiainfoDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = DiainfoDetailActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    private void unregisterDiainfoPush(final DiainfoData diainfo)
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            isUnregisteringPush = true;
            TransitUtil.login(this);
            return;
        }
        showProgressDialog();
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        pushMgr.unregisterPushSingle(taken, diainfo, new jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener() {

            final DiainfoDetailActivity this$0;
            final DiainfoData val$diainfo;

            public void onCanceled()
            {
                closeProgressDialog();
            }

            public void onError(int i, String s, String s1, String s2)
            {
                closeProgressDialog();
                if (s != null)
                {
                    try
                    {
                        if (s.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                        {
                            TransitUtil.logout(DiainfoDetailActivity.this);
                            unregisterDiainfoPush(diainfo);
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalargumentexception)
                    {
                        return;
                    }
                }
                if (s == null)
                {
                    break MISSING_BLOCK_LABEL_67;
                }
                if (s.equals("0"))
                {
                    findViewById(0x7f0a007b).setVisibility(8);
                }
                showErrorMessageDialog(s2, s1);
                return;
            }

            public void onSuccess(String s, String s1)
            {
                closeProgressDialog();
                findViewById(0x7f0a007b).setVisibility(8);
            }

            
            {
                this$0 = DiainfoDetailActivity.this;
                diainfo = diainfodata;
                super();
            }
        });
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if (!isRegisteringPush) goto _L2; else goto _L1
_L1:
        if (i != 1000) goto _L4; else goto _L3
_L3:
        if (!YTcookieChecker.chkYTcookie()) goto _L6; else goto _L5
_L5:
        setPushDiainfo();
_L8:
        return;
_L6:
        onLoginResult();
        return;
_L4:
        isRegisteringPush = false;
        if (j == -1 && i == getResources().getInteger(0x7f0c0055) && isRegistEdit)
        {
            if (diainfo == null)
            {
                isOnRegistPush = true;
                return;
            } else
            {
                registerDiainfoPush(diainfo);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (!isUnregisteringPush)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i == 1000)
        {
            if (YTcookieChecker.chkYTcookie())
            {
                setPushDiainfo();
                return;
            } else
            {
                onLoginResult();
                return;
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
        super.onActivityResult(i, j, intent);
        if (j == -1 && i == getResources().getInteger(0x7f0c0055) && isRegistEdit)
        {
            if (diainfo == null)
            {
                isOnRegist = true;
                return;
            } else
            {
                postRegist(diainfo);
                return;
            }
        }
        if (true) goto _L8; else goto _L9
_L9:
    }

    public void onCanceled()
    {
        closeProgressDialog();
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
        default:
            return;

        case 2131361910: 
            getDiaInfo(true);
            return;

        case 2131361913: 
            touchTapRD(getString(0x7f0d040f));
            postRegist(diainfo);
            return;

        case 2131362291: 
            touchTapRD(getString(0x7f0d03f4));
            TransitUtil.openURL(this, getString(0x7f0d056c));
            return;

        case 2131361914: 
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0409)).append("/").append(getString(0x7f0d03c0)).toString());
            registerDiainfoPush();
            return;

        case 2131361916: 
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0409)).append("/").append(getString(0x7f0d03d5)).toString());
            unregisterDiainfoPush();
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001b);
        res = getResources();
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        setTitle(getString(0x7f0d00f7));
        Intent intent = getIntent();
        boolean flag;
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        android.view.View.OnClickListener onclicklistener;
        if ("android.intent.action.VIEW".equals(intent.getAction()))
        {
            Uri uri = getIntent().getData();
            if (uri == null)
            {
                showErrorMessageDialog(getString(0x7f0d0106), getString(0x7f0d014f));
                return;
            }
            String s = uri.getQueryParameter(getString(0x7f0d01d8));
            String s1 = uri.getQueryParameter(getString(0x7f0d01dc));
            String s2 = uri.getQueryParameter(getString(0x7f0d01da));
            condition = new Bundle();
            condition.putString(getString(0x7f0d01d9), s);
            condition.putString(getString(0x7f0d01dc), s1);
            condition.putString(getString(0x7f0d01da), s2);
        } else
        {
            diainfo = (DiainfoData)intent.getSerializableExtra(getString(0x7f0d01ad));
            condition = intent.getBundleExtra(getString(0x7f0d022c));
        }
        flag = intent.getBooleanExtra(getString(0x7f0d01be), false);
        super.setRegistEdit(true, condition);
        if (diainfo == null)
        {
            if (condition != null)
            {
                getDiaInfo(false);
            } else
            {
                setResult(0);
            }
        } else
        {
            showDiainfo();
        }
        ((Button)findViewById(0x7f0a0076)).setOnClickListener(this);
        if (flag)
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d03e3)).append("/").append(getString(0x7f0d0409)).toString());
            findViewById(0x7f0a007b).setVisibility(0);
            findViewById(0x7f0a0078).setVisibility(8);
            ((Button)findViewById(0x7f0a007c)).setOnClickListener(this);
        } else
        {
            ((Button)findViewById(0x7f0a0079)).setOnClickListener(this);
            ((Button)findViewById(0x7f0a007a)).setOnClickListener(this);
        }
        ((Button)findViewById(0x7f0a01f3)).setOnClickListener(this);
        linearlayout = (LinearLayout)findViewById(0x7f0a007e);
        linearlayout1 = (LinearLayout)inflater.inflate(0x7f030072, null);
        ((TextView)linearlayout1.findViewById(0x7f0a0222)).setText(getString(0x7f0d00f9));
        ((TextView)linearlayout1.findViewById(0x7f0a0222)).setTextColor(getResources().getColor(0x7f090004));
        ((ImageView)linearlayout1.findViewById(0x7f0a0221)).setImageDrawable(res.getDrawable(0x7f0202ac));
        onclicklistener = new android.view.View.OnClickListener() {

            final DiainfoDetailActivity this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0435));
                try
                {
                    String s3 = getString(0x7f0d0570);
                    Object aobj[] = new Object[1];
                    aobj[0] = URLEncoder.encode(diainfo.getRailName(), "UTF-8");
                    String s4 = String.format(s3, aobj);
                    TransitUtil.openURL(DiainfoDetailActivity.this, s4);
                    return;
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }

            
            {
                this$0 = DiainfoDetailActivity.this;
                super();
            }
        };
        linearlayout1.setOnClickListener(onclicklistener);
        linearlayout.addView(linearlayout1);
        dispAd(this, "2080124766", "pv,bottom");
    }

    public void onError(int i, String s, String s1, String s2)
    {
        boolean flag;
        closeProgressDialog();
        flag = true;
        if (s == null) goto _L2; else goto _L1
_L1:
        if (!s.equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN)) goto _L2; else goto _L3
_L3:
        TransitUtil.logout(this);
        registerDiainfoPush();
        flag = false;
        break MISSING_BLOCK_LABEL_32;
_L7:
        if (s == null) goto _L5; else goto _L4
_L4:
        if (!s.equals("40001")) goto _L5; else goto _L6
_L6:
        isRegisteringPush = true;
        showRegistOverDialog();
        flag = false;
_L5:
        if (flag)
        {
            try
            {
                showErrorMessageDialog(s2, s1);
                return;
            }
            catch (IllegalArgumentException illegalargumentexception) { }
        }
        return;
_L2:
        i;
        JVM INSTR tableswitch 2 2: default 96
    //                   2 45;
           goto _L5 _L7
    }

    protected void onLoginResult()
    {
        if (!YTcookieChecker.chkYTcookie()) goto _L2; else goto _L1
_L1:
        if (!isRegisteringPush) goto _L4; else goto _L3
_L3:
        isRegisteringPush = false;
        taken = TransitUtil.getAccessToken(this);
        if (taken == null) goto _L2; else goto _L5
_L5:
        if (diainfo != null) goto _L7; else goto _L6
_L6:
        isOnRegistPush = true;
_L2:
        return;
_L7:
        registerDiainfoPush(diainfo);
        return;
_L4:
        if (isUnregisteringPush)
        {
            isUnregisteringPush = false;
            taken = TransitUtil.getAccessToken(this);
            if (taken != null)
            {
                if (diainfo == null)
                {
                    isOnUnregistPush = true;
                    return;
                } else
                {
                    unregisterDiainfoPush(diainfo);
                    return;
                }
            }
        }
        if (true) goto _L2; else goto _L8
_L8:
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        isRegisteringPush = bundle.getBoolean("isRegisteringPush");
        isUnregisteringPush = bundle.getBoolean("isUnregisteringPush");
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("isRegisteringPush", isRegisteringPush);
        bundle.putBoolean("isUnregisteringPush", isUnregisteringPush);
    }

    public void onStop()
    {
        super.onStop();
        if (diaSearch != null)
        {
            diaSearch.stopRequest();
        }
    }

    public void onSuccess(String s, String s1)
    {
        closeProgressDialog();
        try
        {
            (new TransitDialogBuilder(this)).setMessage(s1).setTitleInfo(s).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final DiainfoDetailActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

            
            {
                this$0 = DiainfoDetailActivity.this;
                super();
            }
            }).show();
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }

    protected void showDiainfo()
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0077);
        linearlayout.removeAllViews();
        TextView textview = (TextView)findViewById(0x7f0a0075);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM'\u6708'dd'\u65E5 'HH\u6642mm\u5206");
        if (diainfo != null)
        {
            int i = getResources().getDimensionPixelSize(0x7f0b0033);
            textview.setText(diainfo.getRailName());
            ArrayList arraylist = diainfo.getDetailinfo();
            if (arraylist == null)
            {
                LinearLayout linearlayout2 = (LinearLayout)inflater.inflate(0x7f03006f, null);
                ((TextView)linearlayout2.findViewById(0x7f0a0220)).setTextColor(getResources().getColor(0x7f090041));
                linearlayout2.setBackgroundResource(0x7f090042);
                linearlayout2.setPadding(i, i, i, i);
                linearlayout.addView(linearlayout2);
            } else
            {
                Iterator iterator = arraylist.iterator();
                while (iterator.hasNext()) 
                {
                    jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail diainfodatadetail = (jp.co.yahoo.android.apps.transit.api.data.DiainfoData.DiainfoDataDetail)iterator.next();
                    LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f03006f, null);
                    TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a0220);
                    ImageView imageview = (ImageView)linearlayout1.findViewById(0x7f0a021f);
                    String s = diainfodatadetail.getMessage();
                    String s1 = simpledateformat.format(TransitUtil.toCalendar(diainfodatadetail.getUpdateDate()).getTime());
                    String s2 = (new StringBuilder()).append(s).append("(").append(s1).append("\u63B2\u8F09)").toString();
                    textview1.setTextColor(getResources().getColor(0x7f090051));
                    textview1.setText(s2);
                    linearlayout1.setBackgroundResource(0x7f090050);
                    linearlayout1.setPadding(i, i, i, i);
                    imageview.setImageResource(0x7f0201fc);
                    linearlayout.addView(linearlayout1);
                }
            }
        }
    }



/*
    static boolean access$002(DiainfoDetailActivity diainfodetailactivity, boolean flag)
    {
        diainfodetailactivity.isOnRegist = flag;
        return flag;
    }

*/



/*
    static boolean access$102(DiainfoDetailActivity diainfodetailactivity, boolean flag)
    {
        diainfodetailactivity.isOnRegistPush = flag;
        return flag;
    }

*/



/*
    static boolean access$202(DiainfoDetailActivity diainfodetailactivity, boolean flag)
    {
        diainfodetailactivity.isOnUnregistPush = flag;
        return flag;
    }

*/


}
