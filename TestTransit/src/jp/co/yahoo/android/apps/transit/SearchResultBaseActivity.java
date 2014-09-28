// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.YjpnShortUrl;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.apps.transit.viewparts.TransitVerticalDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, MyPageActivity, SearchResultListActivity

public class SearchResultBaseActivity extends TransitBaseActivity
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener
{

    public static final String FORMAT_DIA_WARN = "\u203B%s %s";
    public static final String FORMAT_TIME_GOAL_FOR_ROUTE = "%02d:%02d";
    public static final String FORMAT_TIME_START_FOR_ROUTE = "%02d:%02d";
    private static final String MAP_APP_PKG_NAME = "jp.co.yahoo.android.apps.map";
    protected String GoalTime;
    protected String StartTime;
    protected boolean bFromMemo;
    protected ConditionData conditionData;
    protected Context context;
    private Dialog diaApp;
    protected int featureCount;
    protected boolean isAverage;
    protected boolean isImakoko;
    protected Resources res;
    protected int result_id;
    protected NaviSearchData results;
    private int selectShareMenu;
    private YjpnShortUrl shortUrlApi;
    private SparseArray webShortUrls;

    public SearchResultBaseActivity()
    {
        bFromMemo = false;
        selectShareMenu = -1;
    }

    private void copyTextExecute()
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            copyToClipboard(getRouteData());
        } else
        {
            copyToClipboardUnder11(getRouteData());
        }
        Toast.makeText(this, getString(0x7f0d034f), 1).show();
    }

    private void copyToClipboard(String s)
    {
        ClipboardManager clipboardmanager = (ClipboardManager)getSystemService("clipboard");
        android.content.ClipData.Item item = new android.content.ClipData.Item(s);
        clipboardmanager.setPrimaryClip(new ClipData("data", new String[] {
            "text/plain"
        }, item));
    }

    private void copyToClipboardUnder11(String s)
    {
        ((android.text.ClipboardManager)getSystemService("clipboard")).setText(s);
    }

    private boolean getRouteShortUrl()
    {
        String s;
        if (webShortUrls != null)
        {
            s = (String)webShortUrls.get(result_id);
        } else
        {
            s = null;
        }
        while (!TextUtils.isEmpty(s) || TextUtils.isEmpty(results.webUrl)) 
        {
            return false;
        }
        shortUrlApi = new YjpnShortUrl(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultBaseActivity this$0;

            public boolean onCanceled()
            {
                String s1 = getString(0x7f0d0109);
                Toast.makeText(SearchResultBaseActivity.this, s1, 1).show();
                shareMenuExecute();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s1 = apierror.getMessage();
                if (TextUtils.isEmpty(s1))
                {
                    s1 = getString(0x7f0d0109);
                }
                Toast.makeText(SearchResultBaseActivity.this, s1, 1).show();
                shareMenuExecute();
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                String s1 = shortUrlApi.getShortUrl();
                if (!TextUtils.isEmpty(s1))
                {
                    if (webShortUrls == null)
                    {
                        webShortUrls = new SparseArray();
                    }
                    webShortUrls.put(result_id, s1);
                }
                shareMenuExecute();
                return false;
            }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
        });
        shortUrlApi.setUrl(getWebLongUrl());
        shortUrlApi.request();
        return true;
    }

    private long getTimeInMillisFrom(String s)
    {
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6));
        int k = Integer.parseInt(s.substring(6, 8));
        int l = Integer.parseInt(s.substring(8, 10));
        int i1 = Integer.parseInt(s.substring(10, 12));
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, j - 1, k, l, i1);
        return calendar.getTimeInMillis();
    }

    private String getWebLongUrl()
    {
        StringBuilder stringbuilder = new StringBuilder(results.webUrl);
        if (conditionData.mtf > 0)
        {
            stringbuilder.append("&");
            stringbuilder.append(getString(0x7f0d0383));
            stringbuilder.append("=");
            stringbuilder.append(conditionData.mtf);
        }
        return stringbuilder.toString();
    }

    private void launchMyPageEdit()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/MyPageActivity);
        intent.putExtra(getString(0x7f0d01a5), 0);
        intent.putExtra(getString(0x7f0d0247), 1);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0058));
        startActivityForResult(intent, getResources().getInteger(0x7f0c004b));
    }

    private void launchTimer(String s, String s1, String s2, int i)
    {
        String s3 = (new StringBuilder()).append(getString(0x7f0d005b)).append("?").toString();
        String s4 = (new StringBuilder()).append(s3).append(getString(0x7f0d0389)).append("=").append(s).append("&").append(getString(0x7f0d037f)).append("=").append(s1).append("&").append(getString(0x7f0d0388)).append("=").append(s2).append("&").append(getString(0x7f0d0374)).append("=").append(i).toString();
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(s4));
        startActivityInCurrentMenu(intent);
    }

    private void saveDisplayMemo(ResultDB resultdb, int i, int j)
    {
        String s;
        NaviSearchData navisearchdata = getSaveData(result_id);
        resultdb.addSearchResultsMemo(conditionData, navisearchdata, result_id);
        s = getString(0x7f0d049d);
        i;
        JVM INSTR tableswitch 1 2: default 56
    //                   1 88
    //                   2 109;
           goto _L1 _L2 _L3
_L1:
        String s1 = getString(0x7f0d00ad, new Object[] {
            s
        });
_L5:
        showSaveMemoDialog(getString(0x7f0d00b0), s1);
        return;
_L2:
        s1 = getString(0x7f0d00af, new Object[] {
            s
        });
        continue; /* Loop/switch isn't completed */
_L3:
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = s;
        s1 = getString(0x7f0d00ae, aobj);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private void sendCalenderExecute()
    {
        try
        {
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("title", getString(0x7f0d02fa));
            intent.putExtra("eventLocation", getRouteTitle());
            intent.putExtra("description", getRouteData());
            intent.putExtra("beginTime", getTimeInMillisFrom(StartTime));
            intent.putExtra("endTime", getTimeInMillisFrom(GoalTime));
            startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Toast.makeText(this, getString(0x7f0d011a), 1).show();
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void sendLineExecute()
    {
        String s1 = URLEncoder.encode(getRouteData(), "UTF-8");
        String s = s1;
_L1:
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse((new StringBuilder()).append(getString(0x7f0d0055)).append(s).toString()));
        UnsupportedEncodingException unsupportedencodingexception;
        try
        {
            startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Toast.makeText(this, getString(0x7f0d0120), 1).show();
        }
        break MISSING_BLOCK_LABEL_96;
        unsupportedencodingexception;
        s = getRouteData(false);
          goto _L1
    }

    private void sendMail()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03f4)).toString());
        if (getRouteShortUrl())
        {
            selectShareMenu = 2;
            return;
        } else
        {
            sendMailExecute();
            return;
        }
    }

    private void sendMailExecute()
    {
        try
        {
            Uri uri = Uri.parse("mailto:");
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", getRouteData());
            intent.setData(uri);
            startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Toast.makeText(this, getString(0x7f0d0121), 1).show();
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void shareMenuExecute()
    {
        selectShareMenu;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 48
    //                   1 54
    //                   2 61
    //                   3 68
    //                   4 75
    //                   5 48
    //                   6 82;
           goto _L1 _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
        selectShareMenu = -1;
        return;
_L2:
        sendLineExecute();
        continue; /* Loop/switch isn't completed */
_L3:
        sendMailExecute();
        continue; /* Loop/switch isn't completed */
_L4:
        copyTextExecute();
        continue; /* Loop/switch isn't completed */
_L5:
        sendCalenderExecute();
        continue; /* Loop/switch isn't completed */
_L6:
        TransitUtil.shareAnotherAppExecute(this, getRouteData());
        if (true) goto _L1; else goto _L7
_L7:
    }

    private void showDeleteMemoDialog(final ResultDB sql)
    {
        String s = getString(0x7f0d0154);
        Object aobj[] = new Object[2];
        aobj[0] = getString(0x7f0d049d);
        aobj[1] = sql.getMaxSearchResultsMemo();
        showSingleChoiceListDialog(s, getString(0x7f0d013f, aobj), 0x7f070009, 0, new android.content.DialogInterface.OnClickListener() {

            final SearchResultBaseActivity this$0;
            final ResultDB val$sql;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (i == 0)
                {
                    saveDisplayMemo(sql, 1, 0);
                    return;
                } else
                {
                    launchMyPageEdit();
                    return;
                }
            }

            
            {
                this$0 = SearchResultBaseActivity.this;
                sql = resultdb;
                super();
            }
        }, null);
    }

    private void showDeleteSelectMemoDialog(ResultDB resultdb)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            String s = getString(0x7f0d0154);
            Object aobj[] = new Object[2];
            aobj[0] = getString(0x7f0d049d);
            aobj[1] = resultdb.getMaxSearchResultsMemo();
            String s1 = getString(0x7f0d013f, aobj);
            (new TransitVerticalDialogBuilder(this)).setTitle(s).setMessage(s1).setPositiveButton(0x7f0d0489, new android.content.DialogInterface.OnClickListener() {

                final SearchResultBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    launchMyPageEdit();
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0d0071, new android.content.DialogInterface.OnClickListener() {

                final SearchResultBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    private void showSaveMemoDialog(String s, String s1)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(s1).setTitleInfo(s).setPositiveButton(getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                final SearchResultBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
            }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final SearchResultBaseActivity this$0;

                public void onCancel(DialogInterface dialoginterface)
                {
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    private void usefulDialog(String s, CharSequence acharsequence[], android.content.DialogInterface.OnClickListener onclicklistener)
    {
        (new TransitDialogBuilder(this)).setTitle(s).setItems(acharsequence, onclicklistener).setNegativeButton("\u30AD\u30E3\u30F3\u30BB\u30EB", new android.content.DialogInterface.OnClickListener() {

            final SearchResultBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
        }).show();
    }

    protected void copyText()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03d1)).toString());
        if (getRouteShortUrl())
        {
            selectShareMenu = 3;
            return;
        } else
        {
            copyTextExecute();
            return;
        }
    }

    protected void copyUrl()
    {
        ((ClipboardManager)getSystemService("clipboard")).setText((new StringBuilder()).append(TransitUtil.condToUri(null, conditionData, this, false, true).toString()).append(getString(0x7f0d0383)).append("=").append(Integer.toString(result_id)).toString());
        Toast.makeText(this, getString(0x7f0d034f), 1).show();
    }

    protected String getRouteData()
    {
        return getRouteData(true);
    }

    protected String getRouteData(boolean flag)
    {
        StringBuffer stringbuffer = new StringBuffer();
        HashMap hashmap = results.points;
        ArrayList arraylist = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(result_id)).edges;
        int i = arraylist.size();
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(0);
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget);
        stringbuffer.append((new StringBuilder()).append(navipointdata.stationName).append(" \u21D2 ").toString());
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(-1 + arraylist.size())).startPointTarget);
        stringbuffer.append((new StringBuilder()).append(navipointdata1.stationName).append("\n").toString());
        if (!isAverage)
        {
            stringbuffer.append((new StringBuilder()).append(getSearchDate()).append("\n").toString());
            String s9 = edge.departureDatetime;
            String s10 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(-2 + arraylist.size())).arrivalDatetime;
            stringbuffer.append((new StringBuilder()).append(s9.substring(8, 10)).append(":").append(s9.substring(10)).toString());
            stringbuffer.append(" \u21D2 ");
            stringbuffer.append((new StringBuilder()).append(s10.substring(8, 10)).append(":").append(s10.substring(10)).append("\n").toString());
        }
        stringbuffer.append("------------------------------\n");
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(result_id);
        stringbuffer.append((new StringBuilder()).append(getString(0x7f0d031d)).append("\u3000").toString());
        stringbuffer.append((new StringBuilder()).append(TransitUtil.getTotalTimeString(context, naviroutedata.totaltime)).append("\n").toString());
        stringbuffer.append((new StringBuilder()).append(getString(0x7f0d031c)).append("\u3000").toString());
        stringbuffer.append((new StringBuilder()).append(naviroutedata.totalPrice).append(getString(0x7f0d02da)).append("\n").toString());
        stringbuffer.append((new StringBuilder()).append(getString(0x7f0d031a)).append("\u3000").toString());
        stringbuffer.append((new StringBuilder()).append(naviroutedata.transfercount).append(getString(0x7f0d02d1)).append("\n").toString());
        stringbuffer.append((new StringBuilder()).append(getString(0x7f0d031b)).append("\u3000").toString());
        double d = (double)naviroutedata.totaldistance / 10D;
        stringbuffer.append((new StringBuilder()).append(d).append(getString(0x7f0d02d3)).append("\n").toString());
        stringbuffer.append("------------------------------\n\n");
        int j = 0;
        while (j < i) 
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge2 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(j);
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata7 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge2.startPointTarget);
            String s7 = edge2.departureDatetime;
            String s8 = edge2.arrivalDatetime;
            if (s7 == null)
            {
                continue;
            }
            stringbuffer.append((new StringBuilder()).append("\u25A0").append(navipointdata7.stationName).append("\n").toString());
            int i1 = edge2.traffic;
            int j1 = getResources().getInteger(0x7f0c001a);
            boolean flag1 = false;
            if (i1 == j1)
            {
                flag1 = true;
            }
            int k1 = i - 2;
            int l1 = j;
            boolean flag2 = false;
            if (l1 == k1)
            {
                flag2 = true;
            }
            if (flag2 && flag1 && !TextUtils.isEmpty(edge2.railDispName))
            {
                stringbuffer.append("\u2193 ");
                stringbuffer.append((new StringBuilder()).append(edge2.railDispName).append("\n").toString());
            }
            if (!isAverage)
            {
                stringbuffer.append("\u2193 ");
                stringbuffer.append((new StringBuilder()).append(s7.substring(8, 10)).append(":").append(s7.substring(10)).toString());
                stringbuffer.append("\uFF5E");
                stringbuffer.append((new StringBuilder()).append(s8.substring(8, 10)).append(":").append(s8.substring(10)).append("\n").toString());
            } else
            {
                stringbuffer.append((new StringBuilder()).append("\u2193 ").append(Integer.toString(edge2.time)).append(getString(0x7f0d0325)).append("\n").toString());
            }
            if (flag1)
            {
                stringbuffer.append((new StringBuilder()).append("\u2193 ").append(getString(0x7f0d0330)).append("\n").toString());
            }
            if ((!flag2 || !flag1) && !TextUtils.isEmpty(edge2.railDispName))
            {
                stringbuffer.append("\u2193 ");
                stringbuffer.append((new StringBuilder()).append(edge2.railDispName).append("\n").toString());
            }
            j++;
        }
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(-2 + arraylist.size());
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata2 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge1.goalPointTarget);
        String s = edge1.arrivalDatetime;
        stringbuffer.append((new StringBuilder()).append("\u25A0").append(navipointdata2.stationName).append("\n").toString());
        GoalTime = s;
        StartTime = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(0)).departureDatetime;
        stringbuffer.append("---\n");
        ArrayList arraylist1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(result_id)).edgePrice;
        ArrayList arraylist2 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(result_id)).edgeExpPrice;
        if (arraylist1 != null && arraylist1.size() > 0 || arraylist2 != null && arraylist2.size() > 0)
        {
            stringbuffer.append((new StringBuilder()).append("(").append(getString(0x7f0d026e)).append(")\n").toString());
        }
        if (arraylist1 != null)
        {
            for (int l = 0; l < arraylist1.size(); l++)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price price1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price)arraylist1.get(l);
                String s5 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(price1.edgeFrom)).startPointTarget;
                String s6 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(price1.edgeTo)).goalPointTarget;
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata5 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(s5);
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata6 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(s6);
                stringbuffer.append(navipointdata5.stationName);
                stringbuffer.append("\uFF5E");
                stringbuffer.append(navipointdata6.stationName);
                stringbuffer.append("\u3000");
                stringbuffer.append((new StringBuilder()).append(price1.value).append(getString(0x7f0d0339)).toString());
                stringbuffer.append("\n");
            }

        }
        if (arraylist2 != null)
        {
            for (int k = 0; k < arraylist2.size(); k++)
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price price = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price)arraylist2.get(k);
                String s3 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(price.edgeFrom)).startPointTarget;
                String s4 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(price.edgeTo)).goalPointTarget;
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata3 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(s3);
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata4 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(s4);
                stringbuffer.append(navipointdata3.stationName);
                stringbuffer.append("\uFF5E");
                stringbuffer.append(navipointdata4.stationName);
                stringbuffer.append("\u3000");
                stringbuffer.append((new StringBuilder()).append(price.value).append(getString(0x7f0d0339)).toString());
                stringbuffer.append((new StringBuilder()).append(" (").append(getString(0x7f0d027c)).append(")").toString());
                stringbuffer.append("\n");
            }

        }
        if (flag && !TextUtils.isEmpty(results.webUrl))
        {
            stringbuffer.append((new StringBuilder()).append("\n").append(getString(0x7f0d02fb)).append("\n").toString());
            String s1;
            String s2;
            if (webShortUrls != null)
            {
                s1 = (String)webShortUrls.get(result_id);
            } else
            {
                s1 = null;
            }
            if (!TextUtils.isEmpty(s1))
            {
                s2 = s1;
            } else
            {
                s2 = getWebLongUrl();
            }
            stringbuffer.append((new StringBuilder()).append(s2).append("\n").toString());
        }
        stringbuffer.append((new StringBuilder()).append("\n[").append(getString(0x7f0d0056)).append("]\n").toString());
        return stringbuffer.toString();
    }

    protected String getRouteTitle()
    {
        StringBuffer stringbuffer = new StringBuffer(conditionData.startName);
        stringbuffer.append("\u2192").append(conditionData.goalName);
        return stringbuffer.toString();
    }

    public NaviSearchData getSaveData(int i)
    {
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(i);
        NaviSearchData navisearchdata = (NaviSearchData)results.clone();
        navisearchdata.routes = null;
        navisearchdata.routes = new ArrayList();
        navisearchdata.routes.add(naviroutedata);
        return navisearchdata;
    }

    public String getSearchDate()
    {
        String s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(0)).startTime;
        return (new StringBuilder()).append(s.substring(0, 4)).append("\u5E74").append(s.substring(4, 6)).append("\u6708").append(s.substring(6, 8)).append("\u65E5").toString();
    }

    public void leadToMapAppOnMarket(View view)
    {
        AlertDialog alertdialog = (new TransitDialogBuilder(this)).setNegativeButton(getString(0x7f0d033b), new android.content.DialogInterface.OnClickListener() {

            final SearchResultBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
        }).setView(((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030086, null)).create();
        touchTapRD((new StringBuilder()).append(getString(0x7f0d03f6)).append("/").append(getString(0x7f0d03ed)).toString());
        try
        {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=jp.co.yahoo.android.apps.map")));
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            if (alertdialog != null && alertdialog.isShowing())
            {
                alertdialog.dismiss();
            }
            Toast.makeText(this, "market \u30A2\u30D7\u30EA\u304C\u5B58\u5728\u3057\u307E\u305B\u3093\u3002", 1).show();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
        if (alertdialog == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        if (alertdialog.isShowing())
        {
            alertdialog.dismiss();
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j && getResources().getInteger(0x7f0c004b) == i)
        {
            saveDisplayMemo(new ResultDB(this), 2, intent.getIntExtra(getString(0x7f0d01e4), 0));
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = getIntent();
        context = this;
        res = getResources();
        conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        results = (NaviSearchData)intent.getSerializableExtra(getString(0x7f0d0232));
        result_id = intent.getIntExtra(getString(0x7f0d0231), -1);
        if (result_id == -1)
        {
            if (conditionData.resultId >= 0)
            {
                result_id = conditionData.resultId;
            } else
            {
                result_id = 1;
            }
        }
        conditionData.resultId = result_id;
        bFromMemo = intent.getBooleanExtra(getString(0x7f0d01bd), false);
        featureCount = 0;
        featureCount = results.routes.size();
        isAverage = false;
        if (res.getInteger(0x7f0c006a) == conditionData.type)
        {
            isAverage = true;
        }
        isImakoko = intent.getBooleanExtra(getString(0x7f0d020e), false);
        if (!isImakoko && conditionData.paramMode == getString(0x7f0d0579))
        {
            isImakoko = true;
        }
    }

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(getString(0x7f0d010c), getString(0x7f0d014f));
    }

    public void onSuccess(String s, Bundle bundle)
    {
        ArrayList arraylist = new ArrayList(4);
        final Bundle tmpSt = bundle.getBundle(getString(0x7f0d0240));
        if (tmpSt == null || tmpSt.size() < 1)
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0119));
            return;
        }
        for (int i = 0; i < 4 && i < tmpSt.size(); i++)
        {
            arraylist.add(((StationData)tmpSt.getSerializable(String.valueOf(i))).getName());
        }

        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(this);
        if (arraylist.size() > 0)
        {
            String as[] = new String[arraylist.size()];
            for (int j = 0; j < arraylist.size(); j++)
            {
                as[j] = (CharSequence)arraylist.get(j);
            }

            transitdialogbuilder.setTitle(getString(0x7f0d02d0)).setItems(as, new android.content.DialogInterface.OnClickListener() {

                final SearchResultBaseActivity this$0;
                final Bundle val$tmpSt;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    Calendar calendar = Calendar.getInstance();
                    ConditionData conditiondata = (ConditionData)conditionData.clone();
                    StationData stationdata = (StationData)tmpSt.getSerializable(String.valueOf(k));
                    conditiondata.startName = stationdata.getName();
                    conditiondata.startCode = stationdata.getId();
                    conditiondata.year = calendar.get(1);
                    conditiondata.month = 1 + calendar.get(2);
                    conditiondata.day = calendar.get(11);
                    conditiondata.hour = calendar.get(11);
                    conditiondata.minite = calendar.get(12);
                    conditiondata.type = getResources().getInteger(0x7f0c006b);
                    if (conditiondata.ticket == null)
                    {
                        ConditionData conditiondata1 = (new SaveCondition(context)).getCond();
                        if (conditiondata1 != null)
                        {
                            conditiondata.ticket = conditiondata1.ticket;
                        }
                    }
                    NaviSearch navisearch = new NaviSearch(context, conditiondata. new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

                        final _cls11 this$1;
                        final ConditionData val$conditionData;

                        public boolean onCanceled()
                        {
                            return false;
                        }

                        public boolean onError(APIError apierror)
                        {
                            String s = apierror.getMessage();
                            if (StringUtil.isEmpty(s))
                            {
                                s = getString(0x7f0d0108);
                            }
                            showSimpleErrorMessageDialog(s);
                            return false;
                        }

                        public boolean onSuccess(ApiBase apibase, Object obj)
                        {
                            NaviSearchData navisearchdata = NaviSearch.m_routeList;
                            Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
                            intent.putExtra(getString(0x7f0d0232), navisearchdata);
                            intent.putExtra(getString(0x7f0d022c), conditionData);
                            startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
                            return false;
                        }

            
            {
                this$1 = final__pcls11;
                conditionData = ConditionData.this;
                super();
            }
                    });
                    navisearch.setCondition(conditiondata);
                    navisearch.exec();
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                tmpSt = bundle;
                super();
            }
            }).setNegativeButton(getString(0x7f0d0072), new android.content.DialogInterface.OnClickListener() {

                final SearchResultBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
            });
            transitdialogbuilder.create().show();
            return;
        } else
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0119));
            return;
        }
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(getString(0x7f0d010c), getString(0x7f0d014f));
    }

    protected void researchFromNear()
    {
        LocationSearch locationsearch = new LocationSearch(this, this);
        Bundle bundle = new Bundle();
        bundle.putString(getString(0x7f0d01b6), "10");
        bundle.putString(getString(0x7f0d018e), "dist");
        locationsearch.getCurrentStation(bundle);
    }

    protected void saveResults()
    {
        touchTapRD(context.getString(0x7f0d03f7));
        ResultDB resultdb = new ResultDB(context);
        int i = resultdb.countSearchResultMemo(getString(0x7f0d048f));
        int j = Integer.parseInt(resultdb.getMaxSearchResultsMemo());
        int l;
        if (i >= j)
        {
            l = 1;
        } else
        {
            int k = resultdb.countSearchResultsMemo();
            l = 0;
            if (k >= j)
            {
                if (i > 0)
                {
                    l = 1;
                } else
                {
                    l = 2;
                }
            }
        }
        switch (l)
        {
        default:
            return;

        case 0: // '\0'
            saveDisplayMemo(resultdb, 0, 0);
            return;

        case 1: // '\001'
            showDeleteMemoDialog(resultdb);
            return;

        case 2: // '\002'
            showDeleteSelectMemoDialog(resultdb);
            break;
        }
    }

    protected void sendCalender()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03cc)).toString());
        if (getRouteShortUrl())
        {
            selectShareMenu = 4;
            return;
        } else
        {
            sendCalenderExecute();
            return;
        }
    }

    protected void sendKakaoLink()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03ef)).toString());
        startKakaoLink(getRouteData(false), TransitUtil.condToUri("?", conditionData, context, false, false).toString());
    }

    protected void sendLine()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03f3)).toString());
        if (getRouteShortUrl())
        {
            selectShareMenu = 1;
            return;
        } else
        {
            sendLineExecute();
            return;
        }
    }

    protected void shareAnotherApp()
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d0418)).append("/").append(getString(0x7f0d03c6)).toString());
        if (getRouteShortUrl())
        {
            selectShareMenu = 6;
            return;
        } else
        {
            TransitUtil.shareAnotherAppExecute(context, getRouteData());
            return;
        }
    }

    public void showShareMenu()
    {
        touchTapRD(getString(0x7f0d0418));
        context = this;
        String s = getString(0x7f0d0479);
        CharSequence acharsequence[] = new CharSequence[7];
        acharsequence[0] = getString(0x7f0d028f);
        acharsequence[1] = getString(0x7f0d0292);
        acharsequence[2] = getString(0x7f0d0294);
        acharsequence[3] = getString(0x7f0d0268);
        acharsequence[4] = getString(0x7f0d0261);
        acharsequence[5] = getString(0x7f0d0315);
        acharsequence[6] = getString(0x7f0d0267);
        usefulDialog(s, acharsequence, new android.content.DialogInterface.OnClickListener() {

            final SearchResultBaseActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                switch (i)
                {
                default:
                    return;

                case 0: // '\0'
                    sendKakaoLink();
                    return;

                case 1: // '\001'
                    sendLine();
                    return;

                case 2: // '\002'
                    sendMail();
                    return;

                case 3: // '\003'
                    copyText();
                    return;

                case 4: // '\004'
                    sendCalender();
                    return;

                case 5: // '\005'
                    startTimer(result_id);
                    return;

                case 6: // '\006'
                    shareAnotherApp();
                    break;
                }
            }

            
            {
                this$0 = SearchResultBaseActivity.this;
                super();
            }
        });
    }

    protected void startTimer(int i)
    {
label0:
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d0437)).append("/").append(getString(0x7f0d0421)).toString());
            if (getResources().getInteger(0x7f0c006a) == conditionData.type)
            {
                showErrorMessageDialog(getString(0x7f0d0134), null);
                return;
            }
            ArrayList arraylist = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)results.routes.get(i)).edges;
            HashMap hashmap = results.points;
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge _tmp = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(0);
            int j = 0;
            String s;
            int l;
            String s1;
            String s2;
            do
            {
                if (j >= arraylist.size())
                {
                    break label0;
                }
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(j);
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget);
                int k = navipointdata.type;
                s = edge.railDispName;
                if (1 == (k & 1) && s != null && !s.equals(getString(0x7f0d0330)))
                {
                    l = navipointdata.areaCode;
                    s1 = navipointdata.stationName;
                    s2 = edge.departureDatetime;
                    if (s2 != null)
                    {
                        break;
                    }
                }
                j++;
            } while (true);
            Date date = new Date();
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmm");
            long l1 = Long.parseLong(simpledateformat.format(date));
            long l2 = Long.parseLong(s2);
            if (l2 <= l1)
            {
                showSimpleErrorMessageDialog(getString(0x7f0d0137));
                return;
            }
            long l3 = l2 / 10000L;
            long l4 = (l2 % 10000L) / 100L;
            long l5 = l2 % 10000L % 100L;
            long l6 = l1 / 10000L;
            if (l4 <= 3L)
            {
                l3--;
                l4 += 24L;
            }
            if (l6 != l3)
            {
                showSimpleErrorMessageDialog(getString(0x7f0d0135));
                return;
            }
            String s3;
            String s4;
            if (l4 < 10L)
            {
                s3 = (new StringBuilder()).append("").append("0").append(Long.toString(l4)).toString();
            } else
            {
                s3 = (new StringBuilder()).append("").append(Long.toString(l4)).toString();
            }
            if (l5 < 10L)
            {
                s4 = (new StringBuilder()).append(s3).append("0").append(Long.toString(l5)).toString();
            } else
            {
                s4 = (new StringBuilder()).append(s3).append(Long.toString(l5)).toString();
            }
            launchTimer(s1, s, s4, l);
            return;
        }
        showSimpleErrorMessageDialog(getString(0x7f0d0136));
    }





/*
    static SparseArray access$202(SearchResultBaseActivity searchresultbaseactivity, SparseArray sparsearray)
    {
        searchresultbaseactivity.webShortUrls = sparsearray;
        return sparsearray;
    }

*/



}
