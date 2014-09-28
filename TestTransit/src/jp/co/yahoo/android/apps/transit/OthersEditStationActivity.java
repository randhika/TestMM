// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.AuthApiBase;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitStationSearchActivity

public class OthersEditStationActivity extends TransitStationSearchActivity
{

    private Button btnDelete;
    private Button btnRegist;
    private CheckListView chkList;
    private ArrayList listItems;
    private LinearLayout listRegist;

    public OthersEditStationActivity()
    {
    }

    private void deleteRegist()
    {
        setRegist();
        ArrayList arraylist = chkList.getNoCheckItems();
        registSearch.setMethod("POST");
        Object obj;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); registSearch.setStationName(obj.toString()))
        {
            obj = iterator.next();
        }

        registSearch.setType(getString(0x7f0d057d));
        registSearch.setMsg(getString(0x7f0d04a6));
        registSearch.requestAsync(true);
    }

    private void registStation(StationData stationdata)
    {
        setRegist();
        if (taken == null)
        {
            showErrorMessageDialog(getString(0x7f0d010f), getString(0x7f0d014f));
            return;
        } else
        {
            registSearch.setType(getString(0x7f0d057e));
            registSearch.setStationName(stationdata.getName());
            registSearch.setMethod("POST");
            registSearch.requestAsync(true);
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
        ArrayList arraylist = new ArrayList();
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

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002b);
        setTitle(getString(0x7f0d0470));
        setReqCode(getResources().getInteger(0x7f0c0056));
        chkList = new CheckListView(this);
        listRegist = (LinearLayout)findViewById(0x7f0a012f);
        btnRegist = (Button)findViewById(0x7f0a0132);
        btnRegist.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersEditStationActivity this$0;

            public void onClick(View view)
            {
                getKeySearchStation();
            }

            
            {
                this$0 = OthersEditStationActivity.this;
                super();
            }
        });
        btnDelete = (Button)findViewById(0x7f0a00ee);
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {

            final OthersEditStationActivity this$0;

            public void onClick(View view)
            {
                if (chkList.getCheckItems().size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d012d), getString(0x7f0d0150));
                    return;
                } else
                {
                    showdelMessageDialog(getString(0x7f0d00cc));
                    return;
                }
            }

            
            {
                this$0 = OthersEditStationActivity.this;
                super();
            }
        });
        setRegist();
        if (taken == null)
        {
            TransitUtil.login(this);
        } else
        {
            registSearch.setMethod("GET");
            registSearch.requestAsync(true);
        }
        dispAd(this, "2080124755", "pv");
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            setRegist();
            registSearch.setMethod("GET");
            registSearch.requestAsync(true);
        }
    }

    protected void onSuccessStation(StationData stationdata)
    {
        registStation(stationdata);
    }

    protected void setRegist()
    {
        taken = TransitUtil.getAccessToken(this);
        if (taken == null)
        {
            return;
        } else
        {
            registSearch = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

                final OthersEditStationActivity this$0;

                public boolean onCanceled()
                {
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    if (apierror.getCode().equals(AuthApiBase.ERR_CODE_EXPIRED_TOKEN))
                    {
                        TransitUtil.login(OthersEditStationActivity.this);
                    } else
                    {
                        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                    }
                    return false;
                }

                public boolean onSuccess(Bundle bundle)
                {
                    if (registSearch.getMethod() == "GET")
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
                        return false;
                    }
                    String s = registSearch.getType();
                    if (s != null && s.equals(getString(0x7f0d057e)))
                    {
                        Toast.makeText(OthersEditStationActivity.this, getString(0x7f0d00ab), 0).show();
                    }
                    if (taken == null)
                    {
                        setResult(-1);
                    }
                    setRegist();
                    registSearch.setMethod("GET");
                    registSearch.requestAsync(true);
                    return false;
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = OthersEditStationActivity.this;
                super();
            }
            });
            return;
        }
    }


}
