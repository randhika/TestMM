// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.api.AddressSearch;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, OthersActivity, OhtersAddressListActivity

public class OthersAdressSearchActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener, android.view.View.OnClickListener
{

    private String address;
    private EditText addressTextBox;
    private Button delText;
    private int nReqCode;
    private AddressSearch objSearch;
    private Intent returnIntent;
    private String sDelText;
    private SetSharedPreferences sp;

    public OthersAdressSearchActivity()
    {
        nReqCode = 0;
    }

    private void doSearch(String s)
    {
        objSearch = new AddressSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final OthersAdressSearchActivity this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                showErrorMessageDialog(apierror.getMessage(), getString(0x7f0d014f));
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                Bundle bundle = objSearch.getResults();
                if (bundle == null || bundle.size() == 0)
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d0122));
                } else
                {
                    Intent intent = new Intent(OthersAdressSearchActivity.this, jp/co/yahoo/android/apps/transit/OhtersAddressListActivity);
                    intent.putExtra("address", bundle);
                    intent.putExtra(getString(0x7f0d01df), nReqCode);
                    startActivityForResult(intent, getResources().getInteger(0x7f0c0046));
                }
                return false;
            }

            
            {
                this$0 = OthersAdressSearchActivity.this;
                super();
            }
        });
        objSearch.setResultCount(100);
        objSearch.setQuery(s);
        objSearch.request();
    }

    private void retuenPrefTop()
    {
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/OthersActivity), getResources().getInteger(0x7f0c004a));
        finish();
    }

    public void delClickListener()
    {
        sp.delSharedPreferenceData();
        retuenPrefTop();
    }

    public void onClick(View view)
    {
        if (view.getId() == 0x7f0a00bb)
        {
            (new LocationSearch(this, this)).getCurrentAddress();
        } else
        {
            if (view.getId() == 0x7f0a012b)
            {
                String s = addressTextBox.getText().toString();
                if (StringUtil.isEmpty(s))
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d039b));
                    return;
                } else
                {
                    doSearch(s);
                    return;
                }
            }
            if (view.getId() == 0x7f0a012c)
            {
                showdelMessageDialog(sDelText);
                return;
            }
            if (view.getId() == 0x7f0a012a)
            {
                ((TextView)findViewById(0x7f0a0129)).setText("");
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030028);
        getWindow().setSoftInputMode(5);
        returnIntent = new Intent();
        setResult(0, returnIntent);
        nReqCode = getIntent().getIntExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0050));
        Button button = (Button)findViewById(0x7f0a00bb);
        Button button1 = (Button)findViewById(0x7f0a012b);
        Button button2 = (Button)findViewById(0x7f0a012c);
        delText = (Button)findViewById(0x7f0a012a);
        addressTextBox = (EditText)findViewById(0x7f0a0129);
        addressTextBox.addTextChangedListener(new TextWatcher() {

            final OthersAdressSearchActivity this$0;

            public void afterTextChanged(Editable editable)
            {
                if (editable.length() < 1)
                {
                    delText.setVisibility(8);
                    return;
                } else
                {
                    delText.setVisibility(0);
                    return;
                }
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            
            {
                this$0 = OthersAdressSearchActivity.this;
                super();
            }
        });
        sp = null;
        String s;
        String s1;
        if (nReqCode == getResources().getInteger(0x7f0c0052))
        {
            sp = new SetSharedPreferences(this, getString(0x7f0d00c5));
            setTitle(getString(0x7f0d0543));
            sDelText = getString(0x7f0d039a);
            s = "2080302609";
        } else
        if (nReqCode == getResources().getInteger(0x7f0c0051))
        {
            sp = new SetSharedPreferences(this, getString(0x7f0d00c4));
            setTitle(getString(0x7f0d0542));
            sDelText = getString(0x7f0d0399);
            s = "2080302610";
        } else
        {
            sp = new SetSharedPreferences(this, getString(0x7f0d00c3));
            setTitle(getString(0x7f0d0541));
            sDelText = getString(0x7f0d0398);
            s = "2080302608";
        }
        s1 = sp.getStringSharedPreferenceData("address");
        addressTextBox.setText(s1);
        addressTextBox.setSelection(addressTextBox.getText().length());
        if (s1 == null || "".equals(s1))
        {
            button2.setVisibility(8);
        }
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        delText.setOnClickListener(this);
        dispAd(this, s, "pv");
    }

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    public void onStop()
    {
        super.onStop();
        if (objSearch != null)
        {
            objSearch.stopRequest();
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        address = bundle.getString(getString(0x7f0d01a2));
        addressTextBox.setText(address);
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }



}
