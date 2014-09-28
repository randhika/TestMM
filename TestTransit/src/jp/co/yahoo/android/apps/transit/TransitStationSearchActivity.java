// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, StationInfoList, InputSearch

public class TransitStationSearchActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{

    private boolean bRegist;
    private int nReqCode;
    protected RegistSearchSSO registSearch;
    private String sNoMsg;
    private String sTitleAround;
    private String sTitleKeyword;
    private String sTitleRegist;
    protected BearerToken taken;

    public TransitStationSearchActivity()
    {
        sTitleRegist = "";
        sTitleAround = "";
        sTitleKeyword = "";
        nReqCode = 0;
        bRegist = true;
        sNoMsg = null;
    }

    private void launchStation(Bundle bundle, Bundle bundle1, String s)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/StationInfoList);
        if (bundle != null)
        {
            Bundle bundle2 = new Bundle();
            bundle2.putBundle(getString(0x7f0d0240), bundle);
            intent.putExtra(getString(0x7f0d022c), bundle2);
        } else
        {
            intent.putExtra(getString(0x7f0d022c), bundle1);
        }
        intent.putExtra(getString(0x7f0d01df), nReqCode);
        intent.putExtra(getString(0x7f0d01d3), s);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0063));
    }

    protected void getAroundStation()
    {
        LocationSearch locationsearch = new LocationSearch(this, this);
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01b6), "10");
        bundle.putString(getString(0x7f0d018e), "dist");
        sNoMsg = getString(0x7f0d0119);
        locationsearch.getCurrentStation(new Bundle());
    }

    protected void getKeySearchStation()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/InputSearch);
        intent.putExtra(getString(0x7f0d0233), 1);
        intent.putExtra(getString(0x7f0d01c3), false);
        intent.putExtra(getString(0x7f0d01de), false);
        intent.putExtra(getString(0x7f0d01df), nReqCode);
        intent.putExtra(getString(0x7f0d01d3), sTitleKeyword);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0046));
    }

    protected void getRailStation(String s, String s1)
    {
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01e4), "100");
        bundle.putString(getString(0x7f0d01a8), s1);
        bundle.putString(getString(0x7f0d01d9), s);
        bundle.putString(getString(0x7f0d01b6), "10");
        bundle.putString(getString(0x7f0d018e), "dist");
        sNoMsg = getString(0x7f0d0119);
        (new LocationSearch(this, this)).getCurrentStation(bundle);
    }

    protected void getResistStation()
    {
        if (registSearch != null)
        {
            registSearch.execute(new Void[0]);
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 != j) goto _L2; else goto _L1
_L1:
        if (getResources().getInteger(0x7f0c0063) != i) goto _L4; else goto _L3
_L3:
        onSuccessStation((StationData)intent.getSerializableExtra(getString(0x7f0d023e)));
_L6:
        return;
_L4:
        if (getResources().getInteger(0x7f0c0046) == i)
        {
            StationData stationdata = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
            if (stationdata != null && !TransitUtil.isEmpty(stationdata.getName()))
            {
                if (TransitUtil.isEmpty(stationdata.getId()))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(getString(0x7f0d0241), stationdata.getName());
                    bundle.putString(getString(0x7f0d024b), "4");
                    bundle.putString(getString(0x7f0d018e), "hybrid+-int_custom02");
                    launchStation(null, bundle, "");
                    return;
                } else
                {
                    onSuccessStation(stationdata);
                    return;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (getResources().getInteger(0x7f0c0063) == i && intent != null && intent.hasExtra(getString(0x7f0d01cd)))
        {
            showErrorMessageDialog(intent.getStringExtra(getString(0x7f0d01cd)), getString(0x7f0d014f));
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300ab);
        sTitleRegist = getString(0x7f0d0546);
        sTitleAround = getString(0x7f0d0544);
        sTitleKeyword = getString(0x7f0d0545);
        setRegist();
    }

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    public void onSuccess(String s, Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle(getString(0x7f0d0240));
        if (bundle1 == null || bundle1.size() < 1)
        {
            if (sNoMsg != null)
            {
                showErrorMessageDialog(sNoMsg, getString(0x7f0d014f));
                return;
            } else
            {
                showErrorMessageDialog(getString(0x7f0d012e), getString(0x7f0d014f));
                return;
            }
        } else
        {
            launchStation(bundle1, null, sTitleAround);
            return;
        }
    }

    protected void onSuccessStation(StationData stationdata)
    {
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    protected void setRegist()
    {
label0:
        {
            if (bRegist)
            {
                taken = TransitUtil.getAccessToken(this);
                if (taken != null)
                {
                    break label0;
                }
            }
            return;
        }
        registSearch = new RegistSearchSSO(this, taken, new jp.co.yahoo.android.apps.transit.api.AuthApiBase.AuthApiListener() {

            final TransitStationSearchActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(Bundle bundle)
            {
                if (bundle == null || bundle.size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d0128), getString(0x7f0d014f));
                    return false;
                } else
                {
                    launchStation(bundle, null, sTitleRegist);
                    return false;
                }
            }

            public volatile boolean onSuccess(Object obj)
            {
                return onSuccess((Bundle)obj);
            }

            
            {
                this$0 = TransitStationSearchActivity.this;
                super();
            }
        });
    }

    protected void setReqCode(int i)
    {
        nReqCode = i;
    }


}
