// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.api.RegistSearchSSO;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.apps.transit.viewparts.TransitVerticalDialogBuilder;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.sso.YTcookieChecker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersEditRailActivity

public class DiainfoBaseActivity extends TransitBaseActivity
{

    protected DiainfoData diainfo;
    protected boolean isRegist;
    protected boolean isRegistEdit;
    protected RegistSearchSSO registSearch;
    protected Bundle returnCond;
    protected BearerToken taken;

    public DiainfoBaseActivity()
    {
        isRegist = false;
        isRegistEdit = false;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        isRegist = getIntent().getBooleanExtra(getString(0x7f0d01af), false);
    }

    protected void onLoginResult()
    {
        super.onLoginResult();
        if (YTcookieChecker.chkYTcookie())
        {
            postRegist(diainfo);
        }
    }

    protected void postRegist(DiainfoData diainfodata)
    {
        diainfo = diainfodata;
        setRegist();
        if (diainfodata == null)
        {
            return;
        }
        if (taken == null)
        {
            TransitUtil.login(this);
            return;
        } else
        {
            ArrayList arraylist = new ArrayList(1);
            Bundle bundle = new Bundle();
            bundle.putString("Name", diainfodata.getRailName());
            bundle.putString("RailCode", diainfodata.getRailCode());
            bundle.putString("RailRangeCode", diainfodata.getRailRangeCode());
            arraylist.add(bundle);
            registSearch.setPostData(arraylist);
            registSearch.setMethod("POST");
            registSearch.requestAsync(true);
            return;
        }
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

                final DiainfoBaseActivity this$0;

                public boolean onCanceled()
                {
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    if (isRegistEdit && apierror != null && apierror.getCode() != null && apierror.getCode().equals("40001"))
                    {
                        showRegistOverDialog();
                        return false;
                    } else
                    {
                        showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                        return false;
                    }
                }

                public boolean onSuccess(Bundle bundle)
                {
                    if (isRegist)
                    {
                        showRegistComplete();
                        return false;
                    } else
                    {
                        Toast.makeText(DiainfoBaseActivity.this, getString(0x7f0d00aa), 0).show();
                        return false;
                    }
                }

                public volatile boolean onSuccess(Object obj)
                {
                    return onSuccess((Bundle)obj);
                }

            
            {
                this$0 = DiainfoBaseActivity.this;
                super();
            }
            });
            registSearch.setSearchType(getString(0x7f0d01d5));
            return;
        }
    }

    protected void setRegistEdit(boolean flag, Bundle bundle)
    {
        isRegistEdit = flag;
        returnCond = bundle;
    }

    protected void showRegistComplete()
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(getString(0x7f0d00aa)).setPositiveButton(getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                final DiainfoBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    setResult(-1);
                    finish();
                }

            
            {
                this$0 = DiainfoBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    protected void showRegistOverDialog()
    {
        (new TransitVerticalDialogBuilder(this)).setTitle(getString(0x7f0d0153)).setMessage(getString(0x7f0d0467)).setPositiveButton(0x7f0d0466, new android.content.DialogInterface.OnClickListener() {

            final DiainfoBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                Intent intent = new Intent(DiainfoBaseActivity.this, jp/co/yahoo/android/apps/transit/OthersEditRailActivity);
                intent.putExtra(getString(0x7f0d01d2), true);
                startActivityForResult(intent, getResources().getInteger(0x7f0c0055));
            }

            
            {
                this$0 = DiainfoBaseActivity.this;
                super();
            }
        }).setNegativeButton(0x7f0d0071, new android.content.DialogInterface.OnClickListener() {

            final DiainfoBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = DiainfoBaseActivity.this;
                super();
            }
        }).show();
    }
}
