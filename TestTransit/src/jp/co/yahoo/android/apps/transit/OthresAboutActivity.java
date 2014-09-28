// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.GetKousyou;
import jp.co.yahoo.android.apps.transit.api.data.APIError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class OthresAboutActivity extends TransitBaseActivity
{

    private TextView kousyouTextView;
    private GetKousyou objSearch;

    public OthresAboutActivity()
    {
    }

    protected void getKousyou()
    {
        kousyouTextView = (TextView)findViewById(0x7f0a013c);
        objSearch = new GetKousyou(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final OthresAboutActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                kousyouTextView.setText(getString(0x7f0d0115));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                String s = objSearch.kousyou();
                if (s == "" || s == null)
                {
                    kousyouTextView.setText(getString(0x7f0d0115));
                } else
                {
                    kousyouTextView.setText(s);
                }
                return false;
            }

            
            {
                this$0 = OthresAboutActivity.this;
                super();
            }
        });
        objSearch.setDialogDisplay(false);
        objSearch.request();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002e);
        setTitle(getString(0x7f0d001d));
        android.text.method.MovementMethod movementmethod = LinkMovementMethod.getInstance();
        TextView textview = (TextView)findViewById(0x7f0a013b);
        textview.setMovementMethod(movementmethod);
        String s = getString(0x7f0d0015);
        Object aobj[] = new Object[2];
        aobj[0] = getString(0x7f0d0017);
        aobj[1] = getString(0x7f0d0016);
        String s1 = s.replace("PROMO_LINK", String.format("<a href=\"%s\">%s</a>", aobj));
        String s2 = getString(0x7f0d000f);
        Object aobj1[] = new Object[2];
        aobj1[0] = getString(0x7f0d0011);
        aobj1[1] = getString(0x7f0d0010);
        String s3 = s2.replace("HELP_LINK", String.format("<a href=\"%s\">%s</a>", aobj1));
        textview.setText(Html.fromHtml((new StringBuilder()).append(getString(0x7f0d000d)).append("<br>").append(s1).append("<br>").append(s3).toString()));
        getKousyou();
        dispAd(this, "2080078817", "pv");
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
    }


}
