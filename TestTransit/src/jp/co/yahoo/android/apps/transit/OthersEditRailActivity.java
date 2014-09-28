// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.PushDiainfoManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, DiainfoAllCategory

public class OthersEditRailActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.common.PushDiainfoManager.PushManagerListener
{

    private Button btnDelete;
    private CheckListView chkList;
    private boolean isOnRegist;
    private LinearLayout listRegist;
    private PushDiainfoManager pushMgr;
    private RegistSearchSSO regist;
    private BearerToken taken;

    public OthersEditRailActivity()
    {
        isOnRegist = false;
    }

    private void checkPushSetting()
    {
        if (taken == null)
        {
            onSuccess(null, null);
            return;
        }
        String s = TransitUtil.getYID(this);
        if (TransitUtil.isEmpty(s))
        {
            onSuccess(null, null);
            return;
        }
        ArrayList arraylist = chkList.getCheckItems();
        ArrayList arraylist1 = new ArrayList(arraylist.size());
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); arraylist1.add((DiainfoData)iterator.next())) { }
        if (pushMgr == null)
        {
            pushMgr = new PushDiainfoManager(this);
        }
        pushMgr.checkDeletedAndPush(taken, s, arraylist1, this);
    }

    private void deleteRegist()
    {
        setRegist();
        ArrayList arraylist = chkList.getNoCheckItems();
        ArrayList arraylist1 = new ArrayList();
        Bundle bundle;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); arraylist1.add(bundle))
        {
            Object obj = iterator.next();
            bundle = new Bundle();
            DiainfoData diainfodata = (DiainfoData)obj;
            bundle.putString("RailCode", diainfodata.getRailCode());
            bundle.putString("RailRangeCode", diainfodata.getRailRangeCode());
        }

        regist.setType(getString(0x7f0d057d));
        regist.setPostData(arraylist1);
        regist.setMethod("POST");
        regist.setMsg(getString(0x7f0d04a6));
        if (isOnRegist)
        {
            if (taken != null)
            {
                showProgressDialog();
            }
            regist.requestAsync(false);
            return;
        } else
        {
            regist.requestAsync(true);
            return;
        }
    }

    private void launchDiainfo()
    {
        setResult(-1);
        finish();
    }

    private void setRegist()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            return;
        } else
        {
            regist = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final OthersEditRailActivity this$0;

                public boolean onCanceled()
                {
                    if (isOnRegist)
                    {
                        closeProgressDialog();
                    }
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    if (isOnRegist)
                    {
                        closeProgressDialog();
                    }
                    if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                    {
                        TransitUtil.login(OthersEditRailActivity.this);
                    } else
                    {
                        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    if (regist.getMethod() == "GET")
                    {
                        TextView textview = (TextView)findViewById(0x7f0a0134);
                        if (bundle == null || bundle.size() < 1)
                        {
                            textview.setVisibility(0);
                        } else
                        {
                            textview.setVisibility(8);
                        }
                        showRegistList(bundle);
                    } else
                    if (taken == null)
                    {
                        if (isOnRegist)
                        {
                            closeProgressDialog();
                            return false;
                        }
                    } else
                    if (isOnRegist)
                    {
                        checkPushSetting();
                        return false;
                    } else
                    {
                        setRegist();
                        regist.setSearchType(getString(0x7f0d01d5));
                        regist.setMethod("GET");
                        regist.requestAsync(true);
                        return false;
                    }
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = OthersEditRailActivity.this;
                super();
            }
            });
            regist.setSearchType(getString(0x7f0d01d5));
            return;
        }
    }

    private void showRegistList(Bundle bundle)
    {
        if (bundle == null || bundle.size() < 1)
        {
            showRegistList(new ArrayList());
            return;
        }
        TextView textview = (TextView)findViewById(0x7f0a0131);
        ArrayList arraylist;
        if (bundle.size() >= 11)
        {
            textview.setText(getString(0x7f0d0468));
        } else
        {
            textview.setText(getString(0x7f0d046f));
        }
        arraylist = new ArrayList();
        for (int i = 0; i < bundle.size(); i++)
        {
            arraylist.add(bundle.get(String.valueOf(i)));
        }

        showRegistList(arraylist);
    }

    private void showRegistList(ArrayList arraylist)
    {
        chkList = new CheckListView(this);
        chkList.setListItems(arraylist);
        listRegist.removeAllViews();
        listRegist.addView(chkList);
        chkList.showView();
    }

    protected void delClickListener()
    {
        deleteRegist();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == -1 && i == getResources().getInteger(0x7f0c003d))
        {
            Intent intent1 = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/OthersEditRailActivity);
            intent1.setAction("android.intent.action.VIEW");
            startActivityInCurrentMenu(intent1);
            finish();
        }
    }

    public void onCanceled()
    {
        closeProgressDialog();
        if (isOnRegist)
        {
            launchDiainfo();
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002a);
        setTitle(getString(0x7f0d0469));
        isOnRegist = getIntent().getBooleanExtra(getString(0x7f0d01d2), false);
        chkList = new CheckListView(this);
        listRegist = (LinearLayout)findViewById(0x7f0a012f);
        setRegist();
        btnDelete = (Button)findViewById(0x7f0a00ee);
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersEditRailActivity this$0;

            public void onClick(View view)
            {
                if (chkList.getCheckItems().size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d012b), getString(0x7f0d0150));
                    return;
                } else
                {
                    showdelMessageDialog(getString(0x7f0d00cc));
                    return;
                }
            }

            
            {
                this$0 = OthersEditRailActivity.this;
                super();
            }
        });
        Button button = (Button)findViewById(0x7f0a0132);
        if (isOnRegist)
        {
            button.setVisibility(8);
        } else
        {
            button.setOnClickListener(new android.view.View.OnClickListener() {

                final OthersEditRailActivity this$0;

                public void onClick(View view)
                {
                    Intent intent = new Intent(OthersEditRailActivity.this, jp/co/yahoo/android/apps/transit/DiainfoAllCategory);
                    intent.putExtra(getString(0x7f0d01af), true);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c003d));
                }

            
            {
                this$0 = OthersEditRailActivity.this;
                super();
            }
            });
        }
        if (taken == null)
        {
            TransitUtil.login(this);
        } else
        {
            regist.setMethod("GET");
            regist.requestAsync(true);
        }
        dispAd(this, "2080124754", "pv");
    }

    public void onError(int i, String s, String s1, String s2)
    {
        closeProgressDialog();
        if (isOnRegist)
        {
            launchDiainfo();
        }
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            setRegist();
            regist.setMethod("GET");
            regist.requestAsync(true);
        }
    }

    public void onSuccess(String s, String s1)
    {
        closeProgressDialog();
        if (isOnRegist)
        {
            launchDiainfo();
        }
    }







}
