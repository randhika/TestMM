// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.CampainBanner;
import jp.co.yahoo.android.apps.transit.common.CreateShortcut;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TopTutorialDialogFragment;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.common.YAppInfoChecker;
import jp.co.yahoo.android.common.login.YJDNErrorData;
import jp.co.yahoo.android.common.login.YLoginServiceListener;
import jp.co.yahoo.android.common.login.YLoginServiceManager;
import jp.co.yahoo.android.yjvoice.YJVONbestResult;
import jp.co.yahoo.android.yjvoice.YJVORecognizeListener;
import jp.co.yahoo.android.yjvoice.YJVORecognizeResult;
import jp.co.yahoo.android.yjvoice.YJVO_FILTER;
import jp.co.yahoo.android.yjvoice.YJVO_STATE;
import jp.co.yahoo.android.yjvoice.YJVO_TYPE;
import jp.co.yahoo.android.yjvoice.screen.YJVOVRecognizer;
import jp.co.yahoo.applicot.Applicot;
import org.apache.http.Header;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, InputSearch, RailmapActivity, MyPageActivity, 
//            HomeDatetimeConditionActivity, HomeDetailConditionActivity, HomeWalkConditionActivity, OthersAdressSearchActivity, 
//            SearchResultListActivity

public class Transit extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener, jp.co.yahoo.android.apps.transit.viewparts.TopTutorialDialogFragment.TopTutorialDialogListener
{

    public static final String FORMAT_DATE_JP = "%d\u5E74%d\u6708%d\u65E5(%s)";
    public static final String FORMAT_TIME_JP = "%02d\u6642%02d\u5206";
    public static final String FORMAT_TIME_WITH_COLON = "%02d:%02d";
    static final int MAPOPEN_REQUEST_CODE = 2;
    static final int VOICE_REQUEST_CODE = 100;
    private boolean bShortcutCreate;
    private ConditionData conditionData;
    private TextView conditionText;
    private TextView goalName;
    private Intent intent;
    private boolean isShowMarket;
    private LinearLayout lnVia1;
    private LinearLayout lnVia2;
    private LinearLayout lnVia3;
    private ArrayList lnViaList;
    private LocationSearch location;
    private YJVOVRecognizer mRecognizer;
    private int nViaCount;
    protected YLoginServiceManager objServiceManager;
    private int reqCode;
    private int searchType;
    private String setAddressData;
    private TextView startName;
    private Context transitContext;

    public Transit()
    {
        isShowMarket = false;
        searchType = 2;
        bShortcutCreate = false;
        nViaCount = 0;
    }

    private void addressDialog(int i)
    {
        int j;
        String s;
        final int nReqest;
        if (i == 5)
        {
            j = getResources().getInteger(0x7f0c0052);
            s = getString(0x7f0d0359);
        } else
        if (i == 6)
        {
            j = getResources().getInteger(0x7f0c0051);
            s = getString(0x7f0d035a);
        } else
        {
            j = getResources().getInteger(0x7f0c0050);
            s = getString(0x7f0d0358);
        }
        nReqest = j;
        (new TransitDialogBuilder(this)).setTitleWarnning(getString(0x7f0d0116)).setCancelable(true).setPositiveButton(getString(0x7f0d015d), new android.content.DialogInterface.OnClickListener() {

            final Transit this$0;
            final int val$nReqest;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Intent intent1 = new Intent(Transit.this, jp/co/yahoo/android/apps/transit/OthersAdressSearchActivity);
                intent1.putExtra(getString(0x7f0d01df), nReqest);
                startActivityForResult(intent1, getResources().getInteger(0x7f0c004a));
            }

            
            {
                this$0 = Transit.this;
                nReqest = i;
                super();
            }
        }).setNegativeButton(getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

            final Transit this$0;

            public void onClick(DialogInterface dialoginterface, int k)
            {
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        }).setMessage(s).show();
    }

    private void doSearch(String s, String s1)
    {
label0:
        {
            if (StringUtil.isEmpty(s) && StringUtil.isEmpty(s1))
            {
                showSimpleErrorMessageDialog(getString(0x7f0d011f));
                return;
            }
            if (StringUtil.isEmpty(s))
            {
                showSimpleErrorMessageDialog(getString(0x7f0d011e));
                return;
            }
            if (StringUtil.isEmpty(s1))
            {
                showSimpleErrorMessageDialog(getString(0x7f0d011d));
                return;
            }
            if (s.equals(s1))
            {
                showSimpleErrorMessageDialog(getString(0x7f0d013e));
                return;
            }
            if (conditionData.viaName == null || conditionData.viaName.size() <= 0)
            {
                break label0;
            }
            Iterator iterator = conditionData.viaName.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
            } while (((String)iterator.next()).indexOf(",") == -1);
            showSimpleErrorMessageDialog(getString(0x7f0d0156));
            return;
        }
        conditionData.startName = s;
        conditionData.goalName = s1;
        offAfterFinal();
        if (getResources().getInteger(0x7f0c006b) == conditionData.type)
        {
            setCurrentDatetimeToConditions();
        }
        NaviSearch navisearch = new NaviSearch(transitContext, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final Transit this$0;

            public boolean onCanceled()
            {
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s2 = apierror.getMessage();
                if (StringUtil.isEmpty(s2))
                {
                    s2 = getString(0x7f0d0108);
                }
                showSimpleErrorMessageDialog(s2);
                updateConditionView();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData navisearchdata = NaviSearch.m_routeList;
                if (conditionData.mtf != -1)
                {
                    Intent intent1 = new Intent(Transit.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                    intent1.putExtra(getString(0x7f0d0231), -1 + conditionData.mtf);
                    intent1.putExtra(getString(0x7f0d0232), navisearchdata);
                    intent1.putExtra(getString(0x7f0d022c), conditionData);
                    startActivityForResult(intent1, getResources().getInteger(0x7f0c0058));
                } else
                {
                    Intent intent2 = new Intent(Transit.this, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                    intent2.putExtra(getString(0x7f0d0232), navisearchdata);
                    intent2.putExtra(getString(0x7f0d022c), conditionData);
                    startActivityForResult(intent2, getResources().getInteger(0x7f0c0059));
                }
                return false;
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        });
        navisearch.setCondition(conditionData);
        navisearch.exec();
    }

    public static String getDayOfWeekJP(int i, int j, int k)
    {
        switch ((new GregorianCalendar(i, j - 1, k)).get(7))
        {
        default:
            return "";

        case 1: // '\001'
            return "\u65E5";

        case 2: // '\002'
            return "\u6708";

        case 3: // '\003'
            return "\u706B";

        case 4: // '\004'
            return "\u6C34";

        case 5: // '\005'
            return "\u6728";

        case 6: // '\006'
            return "\u91D1";

        case 7: // '\007'
            return "\u571F";
        }
    }

    private void goAdreess(int i)
    {
        getString(0x7f0d00c3);
        String s;
        SetSharedPreferences setsharedpreferences;
        if (i == 5)
        {
            s = getString(0x7f0d00c5);
        } else
        if (i == 6)
        {
            s = getString(0x7f0d00c4);
        } else
        {
            s = getString(0x7f0d00c3);
        }
        setsharedpreferences = new SetSharedPreferences(this, s);
        setAddressData = setsharedpreferences.getStringSharedPreferenceData(getString(0x7f0d01a2));
        conditionData.goalLon = setsharedpreferences.getStringSharedPreferenceData(getString(0x7f0d01a4));
        conditionData.goalLat = setsharedpreferences.getStringSharedPreferenceData(getString(0x7f0d01a3));
        conditionData.goalName = setAddressData;
        conditionData.startCode = null;
        conditionData.goalCode = null;
        if (setAddressData == "")
        {
            touchTapRD(getString(0x7f0d0426));
            addressDialog(i);
            return;
        } else
        {
            touchTapRD(getString(0x7f0d0427));
            searchType = i;
            conditionData.searchType = searchType;
            setCurrentPlace();
            return;
        }
    }

    private void initDialog()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(0x7f0d04df), 0);
        int i = TransitUtil.getVersionCode(this);
        int j = sharedpreferences.getInt(getString(0x7f0d039d), -1);
        android.content.SharedPreferences.Editor editor;
        if (j == -1)
        {
            if (!showSelectTicketTypeDialog(new android.view.View.OnClickListener() {

        final Transit this$0;

        public void onClick(View view)
        {
            SaveCondition savecondition = new SaveCondition(Transit.this);
            conditionData.ticket = savecondition.getCond().ticket;
            showLogin();
        }

            
            {
                this$0 = Transit.this;
                super();
            }
    }))
            {
                showLogin();
            }
        } else
        if (i != j)
        {
            showLogin();
        } else
        {
            initTransit();
        }
        editor = sharedpreferences.edit();
        editor.putInt(getString(0x7f0d039d), i);
        editor.commit();
    }

    private void offAfterFinal()
    {
        conditionData.afterFinal = false;
        conditionData.midnightBus = false;
    }

    private void reflectReceivedParametersToConditions(Uri uri)
    {
        conditionData = TransitUtil.uriToCond(uri, conditionData, this);
        offAfterFinal();
    }

    private void setConditions(String s, StationData stationdata)
    {
        String s1 = stationdata.getLon();
        String s2 = stationdata.getLat();
        if (stationdata.getnNaviType() != 128 && stationdata.getnNaviType() != 3)
        {
            s1 = null;
            s2 = null;
        }
        if (TransitUtil.isEmpty(s) || s.equals(getString(0x7f0d0576)))
        {
            startName.setText(stationdata.getName());
            conditionData.startName = stationdata.getName();
            conditionData.startLon = s1;
            conditionData.startLat = s2;
            conditionData.startCode = stationdata.getId();
        } else
        {
            if (s.equals(getString(0x7f0d0575)))
            {
                goalName.setText(stationdata.getName());
                conditionData.goalName = stationdata.getName();
                conditionData.goalLon = s1;
                conditionData.goalLat = s2;
                conditionData.goalCode = stationdata.getId();
                return;
            }
            if (s.startsWith(getString(0x7f0d0577)))
            {
                mergeVia(stationdata, Integer.parseInt(String.valueOf(s.charAt(-1 + s.length()))));
                return;
            }
        }
    }

    private void setCurrentPlace()
    {
        setCurrentPlace(true, null);
    }

    private void setCurrentPlace(boolean flag, String s)
    {
        location = new LocationSearch(this, this);
        if (!flag || s != null)
        {
            location.setDialogMessage(flag, s);
            location.setErroMsg(false);
        }
        location.getCurrentAddress();
    }

    private void setVoice()
    {
        try
        {
            mRecognizer = new YJVOVRecognizer();
            mRecognizer.init(new YJVORecognizeListener() {

                final Transit this$0;

                public void onRecognizeResult(int i, YJVORecognizeResult yjvorecognizeresult)
                {
                    (new Handler(getMainLooper())).post(new Runnable() {

                        final _cls11 this$1;

                        public void run()
                        {
                            YJVORecognizeResult yjvorecognizeresult = mRecognizer.getResult(0);
                            if (yjvorecognizeresult.type != YJVO_TYPE.NBEST) goto _L2; else goto _L1
_L1:
                            String s = ((YJVONbestResult)yjvorecognizeresult.result).getTranscribe(0);
                            if (!TransitUtil.isEmpty(s)) goto _L3; else goto _L2
_L2:
                            String as[];
                            return;
_L3:
                            if ((as = s.split(" ", 0)) == null || as.length <= 2) goto _L2; else goto _L4
_L4:
                            String s1;
                            String s2;
                            boolean flag;
                            int i;
                            int j;
                            s1 = "";
                            s2 = "";
                            flag = false;
                            i = as.length;
                            j = 0;
_L6:
                            String s3;
                            if (j >= i)
                            {
                                break; /* Loop/switch isn't completed */
                            }
                            s3 = as[j];
                            if (!TransitUtil.isEmpty(s3))
                            {
label0:
                                {
                                    if (!s3.equals(getString(0x7f0d032f)))
                                    {
                                        break label0;
                                    }
                                    flag = true;
                                }
                            }
_L7:
                            j++;
                            if (true) goto _L6; else goto _L5
                            if (!s3.equals(getString(0x7f0d032c)))
                            {
                                break MISSING_BLOCK_LABEL_227;
                            }
_L5:
                            startName.setText(s1);
                            goalName.setText(s2);
                            setDefaultCondition();
                            conditionData.startName = as[0];
                            conditionData.goalName = as[2];
                            return;
                            if (flag)
                            {
                                s2 = (new StringBuilder()).append(s2).append(s3).toString();
                            } else
                            {
                                s1 = (new StringBuilder()).append(s1).append(s3).toString();
                            }
                              goto _L7
                        }

            
            {
                this$1 = _cls11.this;
                super();
            }
                    });
                }

                public void onRecognizeState(YJVO_STATE yjvo_state)
                {
                }

                public void onRecordingStart()
                {
                }

                public void onVolumeChanged(short word0)
                {
                }

            
            {
                this$0 = Transit.this;
                super();
            }
            }, this);
            mRecognizer.setPrompt(getString(0x7f0d032d));
            mRecognizer.setApplicationData(getString(0x7f0d059c), TransitUtil.getVersionName(this));
            mRecognizer.setFilter(YJVO_FILTER.NUMBER);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void showLogin()
    {
        if (TransitUtil.getAccessToken(this) != null)
        {
            initTransit();
            return;
        } else
        {
            objServiceManager = new YLoginServiceManager(new YLoginServiceListener() {

                final Transit this$0;

                public void onLogin()
                {
                }

                public void onLoginCanceled()
                {
                }

                public void onLoginFailed(String s)
                {
                }

                public void onLogout()
                {
                }

                public void onServiceConnected()
                {
                    if (objServiceManager.isLogin())
                    {
                        (new TransitDialogBuilder(Transit.this)).setMessage(getString(0x7f0d00ff)).setTitle(getString(0x7f0d0100)).setCancelable(false).setPositiveButton(getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                            final _cls6 this$1;

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                TransitUtil.login(_fld0);
                                objServiceManager.logout();
                            }

            
            {
                this$1 = _cls6.this;
                super();
            }
                        }).show();
                        return;
                    } else
                    {
                        initTransit();
                        return;
                    }
                }

                public void onServiceDisconnected()
                {
                }

                public void onUpdateCredential()
                {
                }

                public void onYJDNCanceled(boolean flag, String as[], Object aobj[])
                {
                }

                public void onYJDNDownloadFailed(YJDNErrorData yjdnerrordata, byte abyte0[], Header aheader[], int i, String s, Object obj)
                {
                }

                public void onYJDNDownloadFailedAtConverter(String s, boolean flag, String as[], Object aobj[])
                {
                }

                public void onYJDNDownloaded(byte abyte0[], Header aheader[], int i, String s, Object obj)
                {
                }

                public void onYJDNDownloadedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
                {
                }

                public void onYJDNHttpError(byte abyte0[], Header aheader[], int i, boolean flag, String s, Object obj)
                {
                }

                public void onYJDNResponsed(byte abyte0[], Header aheader[], int i, String s, Object obj)
                {
                }

                public void onYJDNResponsedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
                {
                }

            
            {
                this$0 = Transit.this;
                super();
            }
            }, getString(0x7f0d033a));
            objServiceManager.bindService(this);
            return;
        }
    }

    private boolean showTutorialDialog()
    {
        SharedPreferences sharedpreferences;
        int i;
        int j;
        boolean flag;
        boolean flag1;
        sharedpreferences = getSharedPreferences(getString(0x7f0d04df), 0);
        i = TransitUtil.getVersionCode(this);
        j = sharedpreferences.getInt(getString(0x7f0d039e), -1);
        flag = false;
        flag1 = false;
        if (i != 73) goto _L2; else goto _L1
_L1:
        if (j != -1) goto _L4; else goto _L3
_L3:
        if (sharedpreferences.getBoolean(getString(0x7f0d039c), true))
        {
            flag1 = true;
            android.content.SharedPreferences.Editor editor1 = sharedpreferences.edit();
            editor1.putBoolean(getString(0x7f0d039c), false);
            editor1.commit();
        } else
        {
            flag1 = true;
            flag = true;
        }
_L2:
        if (i != j || flag1)
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            if (i != j)
            {
                editor.putInt(getString(0x7f0d039e), i);
            }
            if (flag1)
            {
                editor.putInt(getString(0x7f0d03a6), i);
            }
            editor.commit();
        }
        if (flag1)
        {
            FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentByTag("tutorial");
            if (fragment != null)
            {
                fragmenttransaction.remove(fragment);
                fragmenttransaction.commit();
            }
            fragmenttransaction.addToBackStack(null);
            TopTutorialDialogFragment.newInstance(flag, new int[] {
                0x7f020137
            }).show(getSupportFragmentManager(), "tutorial");
        }
        return flag1;
_L4:
        flag = false;
        flag1 = false;
        if (i != j)
        {
            flag1 = true;
            flag = true;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    private void updateConditionView()
    {
        Resources resources;
        String s;
        int i;
        int j;
        int k;
        int l;
        int i1;
        String s1;
        int j1;
        Object aobj[];
        String s2;
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        Object aobj1[];
        ImageView imageview;
        TextView textview;
        ImageView imageview1;
        TextView textview1;
        StringBuffer stringbuffer2;
        StringBuffer stringbuffer3;
        Object aobj2[];
        StringBuffer stringbuffer4;
        StringBuffer stringbuffer5;
        StringBuffer stringbuffer6;
        try
        {
            resources = getResources();
            startName.setText(conditionData.startName);
            goalName.setText(conditionData.goalName);
            updateViaStation();
        }
        catch (Exception exception)
        {
            return;
        }
        s = "";
        i = conditionData.year;
        j = conditionData.month;
        k = conditionData.day;
        l = conditionData.hour;
        i1 = conditionData.minite;
        s1 = getDayOfWeekJP(i, j, k);
        j1 = conditionData.type;
        aobj = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = s1;
        s2 = String.format("%d\u5E74%d\u6708%d\u65E5(%s)", aobj);
        if (j1 != resources.getInteger(0x7f0c006b)) goto _L2; else goto _L1
_L1:
        s = getString(0x7f0d02ed) + "\u3000" + getString(0x7f0d02ef);
_L5:
        conditionText.setText(s);
        imageview = (ImageView)findViewById(0x7f0a0095);
        textview = (TextView)findViewById(0x7f0a0096);
        if (conditionData.walkSpeed != resources.getInteger(0x7f0c0031)) goto _L4; else goto _L3
_L3:
        imageview.setImageResource(0x7f0201e2);
        textview.setText(getString(0x7f0d0331));
_L9:
        imageview1 = (ImageView)findViewById(0x7f0a0097);
        textview1 = (TextView)findViewById(0x7f0a0098);
        if (conditionData.superExpress && conditionData.express && conditionData.airplane && conditionData.highwayBus && conditionData.localBus && conditionData.ferry)
        {
            imageview1.setImageResource(0x7f0201c4);
            textview1.setText(getString(0x7f0d0273));
            return;
        }
        break MISSING_BLOCK_LABEL_832;
_L2:
label0:
        {
            if (j1 != resources.getInteger(0x7f0c0070))
            {
                break label0;
            }
            stringbuffer = new StringBuffer(s2);
            stringbuffer1 = stringbuffer.append(" ");
            aobj1 = new Object[2];
            aobj1[0] = Integer.valueOf(l);
            aobj1[1] = Integer.valueOf(i1);
            s = stringbuffer1.append(String.format("%02d:%02d", aobj1)).append(getString(0x7f0d02f3)).toString();
        }
          goto _L5
label1:
        {
            if (j1 != resources.getInteger(0x7f0c006e))
            {
                break label1;
            }
            stringbuffer2 = new StringBuffer(s2);
            stringbuffer3 = stringbuffer2.append(" ");
            aobj2 = new Object[2];
            aobj2[0] = Integer.valueOf(l);
            aobj2[1] = Integer.valueOf(i1);
            s = stringbuffer3.append(String.format("%02d:%02d", aobj2)).append(getString(0x7f0d02f1)).toString();
        }
          goto _L5
label2:
        {
            if (j1 != resources.getInteger(0x7f0c006d))
            {
                break label2;
            }
            stringbuffer4 = new StringBuffer(s2);
            s = stringbuffer4.append(" ").append(getString(0x7f0d02f0)).toString();
        }
          goto _L5
        if (j1 != resources.getInteger(0x7f0c006f)) goto _L7; else goto _L6
_L6:
        stringbuffer5 = new StringBuffer(s2);
        s = stringbuffer5.append(" ").append(getString(0x7f0d02f2)).toString();
          goto _L5
_L7:
        if (j1 != resources.getInteger(0x7f0c006a)) goto _L5; else goto _L8
_L8:
        stringbuffer6 = new StringBuffer(s2);
        s = stringbuffer6.append(" ").append(getString(0x7f0d02ee)).toString();
          goto _L5
_L4:
label3:
        {
            if (conditionData.walkSpeed != resources.getInteger(0x7f0c0032))
            {
                break label3;
            }
            imageview.setImageResource(0x7f0201e3);
            textview.setText(getString(0x7f0d0332));
        }
          goto _L9
label4:
        {
            if (conditionData.walkSpeed != resources.getInteger(0x7f0c0033))
            {
                break label4;
            }
            imageview.setImageResource(0x7f0201e4);
            textview.setText(getString(0x7f0d0334));
        }
          goto _L9
label5:
        {
            if (conditionData.walkSpeed != resources.getInteger(0x7f0c0034))
            {
                break label5;
            }
            imageview.setImageResource(0x7f0201e5);
            textview.setText(getString(0x7f0d0336));
        }
          goto _L9
        imageview.setImageResource(0x7f0201e3);
        textview.setText(getString(0x7f0d0332));
          goto _L9
        imageview1.setImageResource(0x7f0201c5);
        textview1.setText(getString(0x7f0d0274));
        return;
          goto _L5
    }

    public void addVia(View view)
    {
        if (nViaCount > 2)
        {
            return;
        }
        if (nViaCount != 0) goto _L2; else goto _L1
_L1:
        lnVia1.setVisibility(0);
_L4:
        nViaCount = 1 + nViaCount;
        launchInput((new StringBuilder()).append(getString(0x7f0d0577)).append(Integer.toString(nViaCount)).toString(), null, (new StringBuilder()).append(getString(0x7f0d0167)).append(Integer.toString(nViaCount)).append(getString(0x7f0d0168)).toString(), 5);
        return;
_L2:
        if (nViaCount == 1)
        {
            lnVia2.setVisibility(0);
        } else
        if (nViaCount == 2)
        {
            lnVia3.setVisibility(0);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void getSearchQuery()
    {
        ((Button)findViewById(0x7f0a0099)).setOnClickListener(new android.view.View.OnClickListener() {

            final Transit this$0;

            public void onClick(View view)
            {
                if (StringUtil.isEmpty(startName.getText().toString()) && StringUtil.isEmpty(goalName.getText().toString()))
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d011f));
                } else
                {
                    if (StringUtil.isEmpty(goalName.getText().toString()))
                    {
                        showSimpleErrorMessageDialog(getString(0x7f0d011d));
                        return;
                    }
                    if (bShortcutCreate)
                    {
                        CreateShortcut createshortcut = new CreateShortcut(Transit.this);
                        createshortcut.setBroadcastMode(false);
                        createshortcut.showShortcutDialog(conditionData, new jp.co.yahoo.android.apps.transit.common.CreateShortcut.OnShortcutCretaeListener() {

                            final _cls7 this$1;

                            public void onCancel()
                            {
                            }

                            public void onCreate(Intent intent1)
                            {
                                setResult(-1, intent1);
                                finish();
                            }

            
            {
                this$1 = _cls7.this;
                super();
            }
                        });
                        return;
                    }
                    touchTapRD(getString(0x7f0d042b));
                    if (conditionData.searchType > 4)
                    {
                        if (startName.getText().toString().equals(getString(0x7f0d0289)) && (goalName.getText().toString().equals(getString(0x7f0d028b)) || goalName.getText().toString().equals(getString(0x7f0d0338)) || goalName.getText().toString().equals(getString(0x7f0d02a4))))
                        {
                            goAdreess(conditionData.searchType);
                            return;
                        }
                    } else
                    {
                        if (startName.getText().toString().equals(getString(0x7f0d0289)))
                        {
                            searchType = 2;
                            setCurrentPlace();
                            conditionData.searchType = searchType;
                            return;
                        }
                        if (goalName.getText().toString().equals(getString(0x7f0d0289)))
                        {
                            searchType = 3;
                            setCurrentPlace();
                            conditionData.searchType = searchType;
                            return;
                        }
                        String s = startName.getText().toString();
                        String s1 = goalName.getText().toString();
                        if (searchType < 4)
                        {
                            searchType = 1;
                        }
                        conditionData.searchType = searchType;
                        doSearch(s, s1);
                        return;
                    }
                }
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        });
    }

    public void getStationForVoice()
    {
        touchTapRD(getString(0x7f0d0431));
        if (mRecognizer != null && !mRecognizer.isRecognizing())
        {
            mRecognizer.recognizeStart();
        }
    }

    protected void init()
    {
        super.init();
        boolean flag = getSharedPreferences(getString(0x7f0d04e2), 0).getBoolean(getString(0x7f0d039c), true);
        if (getResources().getBoolean(0x7f080007) && flag)
        {
            initTransit();
        } else
        if (!showTutorialDialog() && !isShowMarket)
        {
            initDialog();
            return;
        }
    }

    protected void initTransit()
    {
        String s = getIntent().getScheme();
        if (s == null || !s.equals(getString(0x7f0d0053))) goto _L2; else goto _L1
_L1:
        Uri uri2;
        String s2;
        String s3;
        String s4;
        uri2 = getIntent().getData();
        s2 = uri2.getQueryParameter(getString(0x7f0d0383));
        s3 = uri2.getQueryParameter(getString(0x7f0d0382));
        s4 = uri2.getQueryParameter(getString(0x7f0d038a));
        touchRD(getString(0x7f0d0555));
        if (TransitUtil.isEmpty(s3) || !s3.equals(getString(0x7f0d0579))) goto _L4; else goto _L3
_L3:
        reflectReceivedParametersToConditions(uri2);
        if (TransitUtil.isEmpty(s4))
        {
            conditionData.ticket = null;
        }
        updateConditionView();
        ((Button)findViewById(0x7f0a0099)).performClick();
_L6:
        return;
_L4:
        if (TransitUtil.isInteger(s2))
        {
            reflectReceivedParametersToConditions(uri2);
            if (TransitUtil.isEmpty(s4))
            {
                conditionData.ticket = null;
            }
            updateConditionView();
            ((Button)findViewById(0x7f0a0099)).performClick();
            return;
        } else
        {
            Toast.makeText(this, getString(0x7f0d0351), 1).show();
            return;
        }
_L2:
        if ("android.intent.action.EDIT".equals(getIntent().getAction()))
        {
            if (reqCode != getResources().getInteger(0x7f0c004c))
            {
                Uri uri1 = getIntent().getData();
                if (uri1 == null)
                {
                    continue; /* Loop/switch isn't completed */
                }
                reflectReceivedParametersToConditions(uri1);
            }
            updateConditionView();
            return;
        }
        if ("android.intent.action.VIEW".equals(getIntent().getAction()))
        {
            Uri uri = getIntent().getData();
            int j;
            if (uri != null)
            {
                reflectReceivedParametersToConditions(uri);
            } else
            if (reqCode != getResources().getInteger(0x7f0c004c))
            {
                touchRD(getString(0x7f0d055e));
                String s1 = getIntent().getStringExtra("uri");
                if (TransitUtil.isEmpty(s1))
                {
                    continue; /* Loop/switch isn't completed */
                }
                reflectReceivedParametersToConditions(Uri.parse(s1));
            }
            updateConditionView();
            j = conditionData.searchType;
            if (j >= 4)
            {
                goAdreess(j);
                return;
            } else
            {
                ((Button)findViewById(0x7f0a0099)).performClick();
                return;
            }
        }
        if ("android.intent.action.CREATE_SHORTCUT".equals(getIntent().getAction()))
        {
            touchRD(getString(0x7f0d055d));
            bShortcutCreate = true;
            ((Button)findViewById(0x7f0a0099)).setText(getString(0x7f0d02fc));
            ((LinearLayout)findViewById(0x7f0a0092)).setClickable(false);
            ((ImageView)findViewById(0x7f0a0094)).setVisibility(8);
            return;
        }
        int i = conditionData.searchType;
        if (i >= 4)
        {
            updateConditionView();
            goAdreess(i);
            return;
        }
        if (getResources().getBoolean(0x7f080007))
        {
            versionCheck();
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void launchInput(String s, String s1, String s2, int i)
    {
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/InputSearch);
        intent1.putExtra(getString(0x7f0d0243), s);
        intent1.putExtra(getString(0x7f0d01c9), i);
        intent1.putExtra(getString(0x7f0d01c3), true);
        intent1.putExtra(getString(0x7f0d022c), conditionData);
        intent1.putExtra(getString(0x7f0d01a7), s1);
        intent1.putExtra(getString(0x7f0d022d), s2);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c0046));
    }

    protected void mergeVia(StationData stationdata, int i)
    {
        if (conditionData.viaName == null || conditionData.viaCode == null)
        {
            conditionData.viaName = new ArrayList();
            conditionData.viaCode = new ArrayList();
        }
        if (stationdata == null) goto _L2; else goto _L1
_L1:
        if (!TransitUtil.isEmpty(stationdata.getName())) goto _L3; else goto _L2
_L2:
        if (conditionData.viaName.size() >= i && conditionData.viaCode.size() >= i)
        {
            conditionData.viaName.remove(i - 1);
            conditionData.viaCode.remove(i - 1);
        }
_L9:
        updateViaStation();
        return;
_L3:
        if (stationdata.getName() != null) goto _L5; else goto _L4
_L4:
        String s = "";
_L6:
        if (stationdata.getId() != null)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        String s1 = "";
_L7:
        Exception exception;
        if (i <= conditionData.viaName.size())
        {
            conditionData.viaName.set(i - 1, s);
            conditionData.viaCode.set(i - 1, s1);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_246;
_L5:
        s = stationdata.getName();
          goto _L6
        s1 = stationdata.getId();
          goto _L7
        try
        {
            conditionData.viaName.add(s);
            conditionData.viaCode.add(s1);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            conditionData.viaName = new ArrayList();
            conditionData.viaCode = new ArrayList();
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected void onActivityResult(int i, int j, Intent intent1)
    {
        super.onActivityResult(i, j, intent1);
        ConditionData conditiondata = conditionData;
        if (i != 2)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        Bundle bundle;
        StationData stationdata1;
        StationData stationdata2;
        try
        {
            bundle = intent1.getBundleExtra(getString(0x7f0d023e));
        }
        catch (Exception exception)
        {
            conditionData = conditiondata;
            try
            {
                updateConditionView();
                return;
            }
            catch (Exception exception1)
            {
                return;
            }
        }
        if (bundle == null)
        {
            return;
        }
        if (bundle.containsKey(getString(0x7f0d0192)))
        {
            stationdata2 = (StationData)bundle.getSerializable(getString(0x7f0d0192));
            setConditions(getString(0x7f0d0576), stationdata2);
        }
        if (bundle.containsKey(getString(0x7f0d0188)))
        {
            stationdata1 = (StationData)bundle.getSerializable(getString(0x7f0d0188));
            setConditions(getString(0x7f0d0575), stationdata1);
            return;
        }
        break MISSING_BLOCK_LABEL_419;
        ConditionData conditiondata2;
        StationData stationdata;
        String s1;
        if (getResources().getInteger(0x7f0c0046) != i)
        {
            break MISSING_BLOCK_LABEL_281;
        }
        conditiondata2 = (ConditionData)intent1.getSerializableExtra(getString(0x7f0d022c));
        stationdata = (StationData)intent1.getSerializableExtra(getString(0x7f0d023e));
        s1 = intent1.getStringExtra(getString(0x7f0d0243));
        if (j != -1 || stationdata != null)
        {
            break MISSING_BLOCK_LABEL_257;
        }
        stationdata = new StationData();
        if (TransitUtil.isEmpty(s1) || s1.equals(getString(0x7f0d0576)))
        {
            stationdata.setName(getString(0x7f0d0289));
        }
        if (conditiondata2 == null)
        {
            break MISSING_BLOCK_LABEL_268;
        }
        conditionData = conditiondata2;
        setConditions(s1, stationdata);
        updateConditionView();
        return;
        if (getResources().getInteger(0x7f0c0059) != i && getResources().getInteger(0x7f0c0058) != i)
        {
            break MISSING_BLOCK_LABEL_374;
        }
        if (-1 != j)
        {
            break MISSING_BLOCK_LABEL_369;
        }
        String s;
        conditionData = (ConditionData)intent1.getSerializableExtra(getString(0x7f0d022c));
        s = intent1.getStringExtra(getString(0x7f0d01b9));
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_364;
        }
        if (s.length() > 0)
        {
            showSimpleErrorMessageDialog(s);
        }
        updateConditionView();
        return;
        updateConditionView();
        return;
        ConditionData conditiondata1;
        if (getResources().getInteger(0x7f0c003c) != i)
        {
            break MISSING_BLOCK_LABEL_419;
        }
        conditiondata1 = (ConditionData)intent1.getSerializableExtra(getString(0x7f0d022c));
        if (conditiondata1 == null)
        {
            break MISSING_BLOCK_LABEL_419;
        }
        conditionData = conditiondata1;
        updateConditionView();
    }

    public void onCanceled(boolean flag)
    {
        if (flag)
        {
            touchRD(getString(0x7f0d0564));
        }
        initDialog();
    }

    public void onCloseClick(DialogFragment dialogfragment, boolean flag)
    {
        dialogfragment.dismiss();
        if (flag)
        {
            touchRD(getString(0x7f0d0564));
        }
        initDialog();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Applicot.init(this, getString(0x7f0d005e), getString(0x7f0d005d));
        getWindow().setSoftInputMode(3);
        setContentView(0x7f03001d);
        setTitle(getString(0x7f0d0345));
        transitContext = this;
        startName = (TextView)findViewById(0x7f0a0087);
        goalName = (TextView)findViewById(0x7f0a008e);
        conditionText = (TextView)findViewById(0x7f0a0093);
        lnVia1 = (LinearLayout)findViewById(0x7f0a0089);
        lnVia2 = (LinearLayout)findViewById(0x7f0a008a);
        lnVia3 = (LinearLayout)findViewById(0x7f0a008b);
        lnViaList = new ArrayList();
        lnViaList.add(lnVia1);
        lnViaList.add(lnVia2);
        lnViaList.add(lnVia3);
        intent = getIntent();
        reqCode = intent.getIntExtra(getString(0x7f0d01df), 0);
        ConditionData conditiondata = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        String s = intent.getStringExtra(getString(0x7f0d01b9));
        if (s != null && s.length() > 0)
        {
            showSimpleErrorMessageDialog(s);
        }
        android.view.View.OnClickListener onclicklistener;
        android.view.View.OnClickListener onclicklistener1;
        if (conditiondata != null)
        {
            conditionData = conditiondata;
            if (conditionData.ticket == null)
            {
                conditionData.ticket = getString(0x7f0d0581);
            }
        } else
        {
            setDefaultCondition();
            conditionData = (new SaveCondition(this)).reverseMergeCond(conditionData);
        }
        updateConditionView();
        setVoice();
        getSearchQuery();
        startName.setOnClickListener(new android.view.View.OnClickListener() {

            final Transit this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0425));
                String s1 = startName.getText().toString();
                if (s1.equals(getString(0x7f0d0289)))
                {
                    s1 = "";
                }
                launchInput(getString(0x7f0d0576), s1, getString(0x7f0d0166), 3);
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        });
        goalName.setOnClickListener(new android.view.View.OnClickListener() {

            final Transit this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d042e));
                String s1 = goalName.getText().toString();
                if (s1.equals(getString(0x7f0d0289)))
                {
                    s1 = "";
                }
                launchInput(getString(0x7f0d0575), s1, getString(0x7f0d0165), 3);
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        });
        onclicklistener = new android.view.View.OnClickListener() {

            final Transit this$0;

            public void onClick(View view)
            {
                touchTapRD(getString(0x7f0d0430));
                String s1 = (new StringBuilder()).append(getString(0x7f0d0577)).append(view.getTag()).toString();
                launchInput(s1, ((TextView)view).getText().toString(), (new StringBuilder()).append(getString(0x7f0d0167)).append(view.getTag()).append(getString(0x7f0d0168)).toString(), 5);
            }

            
            {
                this$0 = Transit.this;
                super();
            }
        };
        onclicklistener1 = new android.view.View.OnClickListener() ;
        for (int i = 0; i < lnViaList.size(); i++)
        {
            LinearLayout linearlayout = (LinearLayout)lnViaList.get(i);
            linearlayout.findViewById(0x7f0a032e).setTag(Integer.toString(i + 1));
            linearlayout.findViewById(0x7f0a032e).setOnClickListener(onclicklistener);
            linearlayout.findViewById(0x7f0a029a).setTag(Integer.toString(i + 1));
            linearlayout.findViewById(0x7f0a029a).setOnClickListener(onclicklistener1);
        }

        startCount();
        (new CampainBanner(this)).loadCampainBanner((LinearLayout)findViewById(0x7f0a009b));
        boolean flag = getSharedPreferences(getString(0x7f0d04e2), 0).getBoolean(getString(0x7f0d039c), true);
        if (!getResources().getBoolean(0x7f080007) || !flag)
        {
            init();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d029a)).setIcon(0x7f020127), 1);
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, getString(0x7f0d0296)).setIcon(0x7f0200db), 1);
        return true;
    }

    protected void onDestroy()
    {
        onDestroy();
        if (getResources().getBoolean(0x7f080007))
        {
            YAppInfoChecker.terminate();
        }
    }

    public void onError(String s, String s1)
    {
        if (s1 != null)
        {
            showSimpleErrorMessageDialog(s1);
        }
    }

    protected void onLoginResult()
    {
        onLoginResult();
        initTransit();
    }

    public void onMapOpen()
    {
        touchTapRD(getString(0x7f0d042a));
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/RailmapActivity);
        if (location != null && location.getLocation() != null)
        {
            intent1.putExtra(getString(0x7f0d01a3), Double.toString(location.getLocation().getLatitude()));
            intent1.putExtra(getString(0x7f0d01a4), Double.toString(location.getLocation().getLongitude()));
        }
        intent1.putExtra(getString(0x7f0d022c), conditionData);
        startActivityForResult(intent1, 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        onOptionsItemSelected(menuitem);
        menuitem.getItemId();
        JVM INSTR tableswitch 0 1: default 36
    //                   0 38
    //                   1 45;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        getStationForVoice();
        continue; /* Loop/switch isn't completed */
_L3:
        touchTapRD(getString(0x7f0d0429));
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/MyPageActivity);
        intent1.putExtra(getString(0x7f0d01a5), 2);
        intent1.putExtra(getString(0x7f0d0247), 0);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c004b));
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        onRestoreInstanceState(bundle);
        ConditionData conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        if (conditiondata != null)
        {
            conditionData = conditiondata;
            updateConditionView();
        }
        isShowMarket = bundle.getBoolean("show_market");
    }

    protected void onResume()
    {
        onResume();
        try
        {
            dispAd(this, "2080078815", "pv");
        }
        catch (Exception exception) { }
        if (isShowMarket)
        {
            isShowMarket = false;
            initDialog();
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        if (conditionData != null)
        {
            bundle.putSerializable(getString(0x7f0d022c), conditionData);
        }
        bundle.putBoolean("show_market", isShowMarket);
    }

    public void onSuccess(String s, Bundle bundle)
    {
        String s1 = bundle.getString(getString(0x7f0d01a2));
        String s2 = bundle.getString(getString(0x7f0d01a3));
        String s3 = bundle.getString(getString(0x7f0d01a4));
        if (searchType == 2)
        {
            startName.setText(s1);
            conditionData.startLon = s3;
            conditionData.startLat = s2;
            doSearch(s1, goalName.getText().toString());
            return;
        }
        if (searchType == 3)
        {
            goalName.setText(s1);
            conditionData.goalLon = s3;
            conditionData.goalLat = s2;
            doSearch(startName.getText().toString(), s1);
            return;
        }
        if (searchType >= 4)
        {
            startName.setText(s1);
            conditionData.startLon = s3;
            conditionData.startLat = s2;
            doSearch(startName.getText().toString(), setAddressData);
            return;
        } else
        {
            doSearch(startName.getText().toString(), goalName.getText().toString());
            return;
        }
    }

    public void onSuccess(JSONObject jsonobject)
    {
    }

    public void onTimeout(String s, String s1)
    {
        if (s1 != null)
        {
            showSimpleErrorMessageDialog(s1);
        }
    }

    public void onVoteClick(DialogFragment dialogfragment, boolean flag)
    {
        dialogfragment.dismiss();
        if (flag)
        {
            touchRD(getString(0x7f0d0565));
        }
        android.content.SharedPreferences.Editor editor = getSharedPreferences("review", 0).edit();
        editor.putInt("review_key", 3);
        editor.commit();
        if (leadToAppOnMarket(getPackageName()))
        {
            isShowMarket = true;
            return;
        } else
        {
            initDialog();
            return;
        }
    }

    public void openDateTimeCondition(View view)
    {
        touchTapRD(getString(0x7f0d042d));
        conditionData.startName = startName.getText().toString();
        conditionData.goalName = goalName.getText().toString();
        if (getResources().getInteger(0x7f0c006b) == conditionData.type)
        {
            setCurrentDatetimeToConditions();
        }
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/HomeDatetimeConditionActivity);
        intent1.putExtra(getString(0x7f0d022c), conditionData);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003c));
    }

    public void openDetailCondition(View view)
    {
        touchTapRD(getString(0x7f0d042c));
        conditionData.startName = startName.getText().toString();
        conditionData.goalName = goalName.getText().toString();
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/HomeDetailConditionActivity);
        intent1.putExtra(getString(0x7f0d022c), conditionData);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003c));
    }

    public void openWalkCondition(View view)
    {
        touchTapRD(getString(0x7f0d042f));
        conditionData.startName = startName.getText().toString();
        conditionData.goalName = goalName.getText().toString();
        Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/HomeWalkConditionActivity);
        intent1.putExtra(getString(0x7f0d022c), conditionData);
        startActivityForResult(intent1, getResources().getInteger(0x7f0c003c));
    }

    public void reverseStartGoal(View view)
    {
        touchTapRD(getString(0x7f0d0424));
        String s = startName.getText().toString();
        String s1 = conditionData.startLon;
        String s2 = conditionData.startLat;
        String s3 = conditionData.startCode;
        startName.setText(goalName.getText().toString());
        conditionData.startName = goalName.getText().toString();
        conditionData.startLon = conditionData.goalLon;
        conditionData.startLat = conditionData.goalLat;
        conditionData.startCode = conditionData.goalCode;
        goalName.setText(s);
        conditionData.goalName = s;
        conditionData.goalLon = s1;
        conditionData.goalLat = s2;
        conditionData.goalCode = s3;
        if (conditionData.viaName != null && conditionData.viaName.size() > 0)
        {
            Collections.reverse(conditionData.viaName);
            Collections.reverse(conditionData.viaCode);
            updateViaStation();
        }
    }

    public void setCurrentDatetimeToConditions()
    {
        Calendar calendar = Calendar.getInstance();
        conditionData.year = calendar.get(1);
        conditionData.month = 1 + calendar.get(2);
        conditionData.day = calendar.get(5);
        conditionData.hour = calendar.get(11);
        conditionData.minite = calendar.get(12);
    }

    protected void setDefaultCondition()
    {
        conditionData = new ConditionData();
        conditionData.startName = getResources().getString(0x7f0d0289);
        conditionData.goalName = null;
        conditionData.viaName = null;
        conditionData.currentPage = -1;
    }

    protected void updateViaStation()
    {
        lnVia1.setVisibility(8);
        ((TextView)lnVia1.findViewById(0x7f0a032e)).setText("");
        lnVia2.setVisibility(8);
        ((TextView)lnVia2.findViewById(0x7f0a032e)).setText("");
        lnVia3.setVisibility(8);
        ((TextView)lnVia3.findViewById(0x7f0a032e)).setText("");
        nViaCount = 0;
        if (conditionData.viaName != null && conditionData.viaName.size() > 0)
        {
            int i = 0;
            while (i < conditionData.viaName.size()) 
            {
                if (!TransitUtil.isEmpty((String)conditionData.viaName.get(i)))
                {
                    ((LinearLayout)lnViaList.get(i)).setVisibility(0);
                    ((TextView)((LinearLayout)lnViaList.get(i)).findViewById(0x7f0a032e)).setText((CharSequence)conditionData.viaName.get(i));
                    nViaCount = 1 + nViaCount;
                }
                i++;
            }
        }
        ImageView imageview = (ImageView)findViewById(0x7f0a0090);
        if (nViaCount >= 3)
        {
            imageview.setVisibility(8);
            return;
        } else
        {
            imageview.setVisibility(0);
            return;
        }
    }







/*
    static int access$210(Transit transit)
    {
        int i = transit.nViaCount;
        transit.nViaCount = i - 1;
        return i;
    }

*/







/*
    static int access$702(Transit transit, int i)
    {
        transit.searchType = i;
        return i;
    }

*/


}
