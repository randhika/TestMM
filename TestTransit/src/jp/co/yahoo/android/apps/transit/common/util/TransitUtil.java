// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.ActionBar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.SetSharedPreferences;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.common.hamburger.YHBGRd;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;
import jp.co.yahoo.yconnect.data.DataManager;
import jp.co.yahoo.yconnect.data.storage.SecretStorageException;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            YBrowserFRUtils

public class TransitUtil
{

    public TransitUtil()
    {
    }

    public static ConditionData bundle2ConditionData(Context context, Bundle bundle)
    {
        ConditionData conditiondata = new ConditionData();
        conditiondata.startName = bundle.getString(context.getString(0x7f0d0192));
        conditiondata.startCode = bundle.getString(context.getString(0x7f0d018f));
        conditiondata.startLat = bundle.getString(context.getString(0x7f0d0190));
        conditiondata.startLon = bundle.getString(context.getString(0x7f0d0191));
        conditiondata.goalName = bundle.getString(context.getString(0x7f0d0188));
        conditiondata.goalCode = bundle.getString(context.getString(0x7f0d0185));
        conditiondata.goalLat = bundle.getString(context.getString(0x7f0d0186));
        conditiondata.goalLon = bundle.getString(context.getString(0x7f0d0187));
        String s = bundle.getString(context.getString(0x7f0d019c));
        if (s != null)
        {
            conditiondata.viaName = comvertArryList(s.split(",", -1));
        }
        String s1 = bundle.getString(context.getString(0x7f0d019c));
        if (s1 != null)
        {
            conditiondata.viaCode = comvertArryList(s1.split(",", -1));
        }
        conditiondata.type = bundle.getInt(context.getString(0x7f0d0194));
        conditiondata.express = bundle.getBoolean(context.getString(0x7f0d019b));
        conditiondata.superExpress = bundle.getBoolean(context.getString(0x7f0d0196));
        conditiondata.airplane = bundle.getBoolean(context.getString(0x7f0d0195));
        conditiondata.highwayBus = bundle.getBoolean(context.getString(0x7f0d0197));
        conditiondata.localBus = bundle.getBoolean(context.getString(0x7f0d019a));
        conditiondata.ferry = bundle.getBoolean(context.getString(0x7f0d0198));
        conditiondata.afterFinal = bundle.getBoolean(context.getString(0x7f0d0183));
        conditiondata.midnightBus = bundle.getBoolean(context.getString(0x7f0d0199));
        conditiondata.sort = bundle.getInt(context.getString(0x7f0d018e));
        conditiondata.walkSpeed = bundle.getInt(context.getString(0x7f0d019e));
        conditiondata.seatKind = bundle.getInt(context.getString(0x7f0d018d));
        conditiondata.year = bundle.getInt(context.getString(0x7f0d019f));
        conditiondata.month = bundle.getInt(context.getString(0x7f0d018b));
        conditiondata.day = bundle.getInt(context.getString(0x7f0d0184));
        conditiondata.hour = bundle.getInt(context.getString(0x7f0d0189));
        conditiondata.minite = bundle.getInt(context.getString(0x7f0d018a));
        conditiondata.ticket = null;
        return conditiondata;
    }

    public static NaviSearchData bundle2NaviSearchData(Context context, Bundle bundle)
    {
        NaviSearchData navisearchdata = new NaviSearchData();
        navisearchdata.routes = getNaviRoutes(context, bundle.getBundle(context.getString(0x7f0d020b)));
        navisearchdata.points = getNaviDictionary(context, bundle.getBundle(context.getString(0x7f0d01e5)));
        navisearchdata.url = bundle.getString(context.getString(0x7f0d022e));
        return navisearchdata;
    }

    public static byte[] bundle2byte(Bundle bundle)
    {
        Parcel parcel = Parcel.obtain();
        bundle.writeToParcel(parcel, 0);
        byte abyte0[] = parcel.marshall();
        parcel.recycle();
        return abyte0;
    }

    public static Bundle byte2bundle(byte abyte0[])
    {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        parcel.setDataPosition(0);
        Bundle bundle = parcel.readBundle();
        parcel.recycle();
        return bundle;
    }

    public static Object byte2object(byte abyte0[])
    {
        Object obj;
        try
        {
            obj = (new ObjectInputStream(new ByteArrayInputStream(abyte0))).readObject();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            classnotfoundexception.printStackTrace();
            return null;
        }
        return obj;
    }

    public static ArrayList comvertArryList(String as[])
    {
        ArrayList arraylist;
        if (as == null || as.length < 1)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList(as.length);
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                arraylist.add(as[j]);
                j++;
            }
        }
        return arraylist;
    }

    public static Uri condToUri(String s, ConditionData conditiondata, Context context, boolean flag, boolean flag1)
    {
        return condToUri(s, conditiondata, context, flag, flag1, false);
    }

    public static Uri condToUri(String s, ConditionData conditiondata, Context context, boolean flag, boolean flag1, boolean flag2)
    {
        StringBuilder stringbuilder;
        String s1;
        if (flag1)
        {
            stringbuilder = (new StringBuilder(context.getString(0x7f0d0569))).append("?v=1&");
        } else
        if (isEmpty(s))
        {
            stringbuilder = (new StringBuilder(context.getString(0x7f0d005c))).append("?");
        } else
        {
            stringbuilder = new StringBuilder(s);
        }
        if (!isEmpty(conditiondata.startName))
        {
            StringBuilder stringbuilder1 = new StringBuilder(conditiondata.startName);
            Object aobj[];
            Object aobj1[];
            Object aobj2[];
            Object aobj3[];
            Object aobj4[];
            if (!flag1 && !stringbuilder1.equals(context.getString(0x7f0d0289)) && conditiondata.startLon != null && conditiondata.startLat != null)
            {
                stringbuilder1.append(",").append(conditiondata.startLat).append(",").append(conditiondata.startLon);
            }
            stringbuilder.append(context.getString(0x7f0d037c)).append("=").append(stringbuilder1).append("&");
        }
        if (!isEmpty(conditiondata.startCode))
        {
            stringbuilder.append(context.getString(0x7f0d037d)).append("=").append(conditiondata.startCode).append("&");
        }
        if (!isEmpty(conditiondata.goalName))
        {
            StringBuilder stringbuilder2 = new StringBuilder(conditiondata.goalName);
            if (!flag1 && !stringbuilder2.equals(context.getString(0x7f0d0289)) && conditiondata.goalLat != null && conditiondata.goalLon != null)
            {
                stringbuilder2.append(",").append(conditiondata.goalLat).append(",").append(conditiondata.goalLon);
            }
            stringbuilder.append(context.getString(0x7f0d038c)).append("=").append(stringbuilder2).append("&");
        }
        if (!isEmpty(conditiondata.goalCode))
        {
            stringbuilder.append(context.getString(0x7f0d038d)).append("=").append(conditiondata.goalCode).append("&");
        }
        if (conditiondata.viaCode != null && conditiondata.viaCode.size() > 1)
        {
            stringbuilder.append(context.getString(0x7f0d0393)).append("=").append(join(conditiondata.viaCode, ",")).append("&");
        } else
        if (conditiondata.viaCode != null && conditiondata.viaCode.size() == 1)
        {
            stringbuilder.append(context.getString(0x7f0d0391)).append("=").append((String)conditiondata.viaCode.get(0)).append("&");
        }
        if (conditiondata.viaName != null && conditiondata.viaName.size() > 1)
        {
            stringbuilder.append(context.getString(0x7f0d0394)).append("=").append(join(conditiondata.viaName, ",")).append("&");
        } else
        if (conditiondata.viaName != null && conditiondata.viaName.size() == 1)
        {
            stringbuilder.append(context.getString(0x7f0d0390)).append("=").append((String)conditiondata.viaName.get(0)).append("&");
        }
        if (!flag2)
        {
            stringbuilder.append(context.getString(0x7f0d0385)).append("=transit&");
        }
        if (!flag)
        {
            if (conditiondata.type > 0)
            {
                int j = conditiondata.type;
                stringbuilder.append(context.getString(0x7f0d038f)).append("=");
                if (j == context.getResources().getInteger(0x7f0c006b) && flag2)
                {
                    j = context.getResources().getInteger(0x7f0c006c);
                }
                if (j == context.getResources().getInteger(0x7f0c006d))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c006d)).append("&");
                } else
                if (j == context.getResources().getInteger(0x7f0c006f))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c006f)).append("&");
                } else
                if (j == context.getResources().getInteger(0x7f0c006a))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c006a)).append("&");
                } else
                if (j == context.getResources().getInteger(0x7f0c0070) || j == context.getResources().getInteger(0x7f0c006b))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c0070)).append("&");
                } else
                if (j == context.getResources().getInteger(0x7f0c006e))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c006e)).append("&");
                } else
                if (j == context.getResources().getInteger(0x7f0c006c))
                {
                    stringbuilder.append(context.getResources().getInteger(0x7f0c006c)).append("&");
                }
                if (j != context.getResources().getInteger(0x7f0c006c))
                {
                    aobj = new Object[1];
                    aobj[0] = Integer.valueOf(conditiondata.year);
                    String s2 = String.format("%02d", aobj);
                    aobj1 = new Object[1];
                    aobj1[0] = Integer.valueOf(conditiondata.month);
                    String s3 = String.format("%02d", aobj1);
                    aobj2 = new Object[1];
                    aobj2[0] = Integer.valueOf(conditiondata.day);
                    String s4 = String.format("%02d", aobj2);
                    aobj3 = new Object[1];
                    aobj3[0] = Integer.valueOf(conditiondata.hour);
                    String s5 = String.format("%02d", aobj3);
                    aobj4 = new Object[1];
                    aobj4[0] = Integer.valueOf(conditiondata.minite);
                    String s6 = String.format("%02d", aobj4);
                    if (flag1)
                    {
                        stringbuilder.append(context.getString(0x7f0d0396)).append("=").append(s2).append(s3).append("&");
                        stringbuilder.append(context.getString(0x7f0d0376)).append("=").append(s4).append("&");
                        stringbuilder.append(context.getString(0x7f0d037e)).append("=").append(s5).append("&");
                        stringbuilder.append(context.getString(0x7f0d0380)).append("=").append(s6.substring(0, 1)).append("&");
                        stringbuilder.append(context.getString(0x7f0d0381)).append("=").append(s6.substring(1, 2)).append("&");
                    } else
                    {
                        stringbuilder.append(context.getString(0x7f0d0376)).append("=").append(s2).append(s3).append(s4).append("&");
                        stringbuilder.append(context.getString(0x7f0d038b)).append("=").append(s5).append(s6).append("&");
                    }
                }
            }
        } else
        if (flag2)
        {
            stringbuilder.append(context.getString(0x7f0d038f)).append("=");
            stringbuilder.append(context.getResources().getInteger(0x7f0c006c)).append("&");
        }
        if (conditiondata.paramMode != null)
        {
            stringbuilder.append(context.getString(0x7f0d0382)).append("=").append(conditiondata.paramMode).append("&");
        }
        if (!isEmpty(conditiondata.ticket))
        {
            stringbuilder.append(context.getString(0x7f0d038a)).append("=").append(conditiondata.ticket).append("&");
        }
        if (conditiondata.type > 0)
        {
            stringbuilder.append(context.getString(0x7f0d018c)).append("=").append(conditiondata.searchType).append("&");
        }
        if (conditiondata.sort >= 0)
        {
            stringbuilder.append(context.getString(0x7f0d0387)).append("=").append(conditiondata.sort).append("&");
        }
        if (conditiondata.walkSpeed > 0)
        {
            stringbuilder.append(context.getString(0x7f0d0395)).append("=");
            if (!flag1)
            {
                stringbuilder.append(conditiondata.walkSpeed).append("&");
            } else
            {
                int i = conditiondata.walkSpeed;
                byte byte0;
                if (i == context.getResources().getInteger(0x7f0c0031))
                {
                    byte0 = 4;
                } else
                if (i == context.getResources().getInteger(0x7f0c0034))
                {
                    byte0 = 3;
                } else
                if (i == context.getResources().getInteger(0x7f0c0033))
                {
                    byte0 = 1;
                } else
                {
                    byte0 = 2;
                }
                stringbuilder.append(Integer.toString(byte0)).append("&");
            }
        }
        if (conditiondata.seatKind > 0)
        {
            stringbuilder.append(context.getString(0x7f0d0379)).append("=").append(conditiondata.seatKind).append("&");
        }
        if (conditiondata.mtf > 0)
        {
            stringbuilder.append(context.getString(0x7f0d0383)).append("=").append(conditiondata.mtf).append("&");
        }
        if (!conditiondata.superExpress)
        {
            stringbuilder.append(context.getString(0x7f0d038e)).append("=0&");
        } else
        {
            stringbuilder.append(context.getString(0x7f0d038e)).append("=1&");
        }
        s1 = context.getString(0x7f0d0375);
        if (flag1)
        {
            s1 = context.getString(0x7f0d0368);
        }
        if (!conditiondata.express)
        {
            stringbuilder.append(s1).append("=0&");
        } else
        {
            stringbuilder.append(s1).append("=1&");
        }
        if (!conditiondata.airplane)
        {
            stringbuilder.append(context.getString(0x7f0d0373)).append("=0&");
        } else
        {
            stringbuilder.append(context.getString(0x7f0d0373)).append("=1&");
        }
        if (!conditiondata.highwayBus)
        {
            stringbuilder.append(context.getString(0x7f0d037a)).append("=0&");
        } else
        {
            stringbuilder.append(context.getString(0x7f0d037a)).append("=1&");
        }
        if (!conditiondata.localBus)
        {
            stringbuilder.append(context.getString(0x7f0d0386)).append("=0&");
        } else
        {
            stringbuilder.append(context.getString(0x7f0d0386)).append("=1&");
        }
        if (!conditiondata.ferry)
        {
            stringbuilder.append(context.getString(0x7f0d037b)).append("=0&");
        } else
        {
            stringbuilder.append(context.getString(0x7f0d037b)).append("=1");
        }
        if (!flag2);
        return Uri.parse(stringbuilder.toString());
    }

    public static Uri condToUri(ConditionData conditiondata, Context context)
    {
        return condToUri((new StringBuilder()).append(context.getString(0x7f0d005c)).append("?").toString(), conditiondata, context, true, false);
    }

    public static boolean deleteUserInfoData(Activity activity)
    {
        try
        {
            (new DataManager(activity, DataManager.loadSecretKey(activity, "default_yid"), "default_yid")).deleteUserInfo();
        }
        catch (SecretStorageException secretstorageexception)
        {
            return false;
        }
        return true;
    }

    public static float dpToPx(Context context, float f)
    {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static BearerToken getAccessToken(Context context)
    {
        BearerToken bearertoken;
        try
        {
            bearertoken = (new DataManager(context, DataManager.loadSecretKey(context, "default_yid"), "default_yid")).loadAccessToken();
        }
        catch (SecretStorageException secretstorageexception)
        {
            return null;
        }
        catch (NullPointerException nullpointerexception)
        {
            return null;
        }
        catch (Exception exception)
        {
            return null;
        }
        return bearertoken;
    }

    public static int getActionBarHeight(TransitBaseActivity transitbaseactivity)
    {
        int i;
        TypedValue typedvalue;
        i = transitbaseactivity.getSupportActionBar().getHeight();
        if (i != 0)
        {
            return i;
        }
        typedvalue = new TypedValue();
        if (android.os.Build.VERSION.SDK_INT < 11) goto _L2; else goto _L1
_L1:
        if (transitbaseactivity.getTheme().resolveAttribute(0x10102eb, typedvalue, true))
        {
            i = TypedValue.complexToDimensionPixelSize(typedvalue.data, transitbaseactivity.getResources().getDisplayMetrics());
        }
_L4:
        return i;
_L2:
        if (transitbaseactivity.getTheme().resolveAttribute(0x7f010002, typedvalue, true))
        {
            i = TypedValue.complexToDimensionPixelSize(typedvalue.data, transitbaseactivity.getResources().getDisplayMetrics());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static String getCurrentDate(String s)
    {
        Date date = new Date();
        if (isEmpty(s))
        {
            s = "yyyyMMddhhmm";
        }
        return (new SimpleDateFormat(s)).format(date);
    }

    public static int getDayOfWeek(Calendar calendar)
    {
        int i = 1 + calendar.get(2);
        int j = calendar.get(5);
        int k = calendar.get(11);
        int l;
        if (i == 1 && j == 1)
        {
            l = calendar.get(7);
        } else
        if (k < 4)
        {
            l = -1 + calendar.get(7);
        } else
        {
            l = calendar.get(7);
        }
        if (l == 1 || l == 8)
        {
            return 4;
        }
        return l != 7 && l != 0 ? 1 : 2;
    }

    public static int getDisplaySizeWidth(Context context)
    {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    public static int getDisplayWidth(Context context)
    {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private static ArrayList getEdges(Context context, Bundle bundle)
    {
        ArrayList arraylist;
        if (bundle == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            int i = bundle.size();
            int j = 0;
            while (j < i) 
            {
                Bundle bundle1 = bundle.getBundle(Integer.toString(j));
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge();
                edge.edgeid = Integer.parseInt(bundle1.getString(context.getString(0x7f0d01f2)));
                edge.departureDatetime = bundle1.getString(context.getString(0x7f0d01fc));
                edge.arrivalDatetime = bundle1.getString(context.getString(0x7f0d01f1));
                edge.color = bundle1.getInt(context.getString(0x7f0d01ec));
                if (bundle1.getString(context.getString(0x7f0d01ee)) != null)
                {
                    edge.distance = Integer.parseInt(bundle1.getString(context.getString(0x7f0d01ee)));
                }
                edge.trainId = bundle1.getString(context.getString(0x7f0d0208));
                edge.passStCount = bundle1.getInt(context.getString(0x7f0d01fd));
                edge.time = bundle1.getInt(context.getString(0x7f0d0205));
                edge.timeType = bundle1.getString(context.getString(0x7f0d0206));
                edge.traffic = bundle1.getInt(context.getString(0x7f0d0207));
                edge.arrivalTrackNumber = bundle1.getString(context.getString(0x7f0d01e9));
                edge.departureTrackNumber = bundle1.getString(context.getString(0x7f0d01ed));
                edge.fromState = bundle1.getInt(context.getString(0x7f0d01ef));
                if (bundle1.getString(context.getString(0x7f0d01ea)) != null)
                {
                    edge.attentionId = Integer.parseInt(bundle1.getString(context.getString(0x7f0d01ea)));
                    edge.attentionText = bundle1.getString(context.getString(0x7f0d01eb));
                }
                edge.StopStations = getStopStations(context, bundle1.getBundle(context.getString(0x7f0d01fe)));
                edge.RidingPosition = getRidingPosition(context, bundle1.getBundle(context.getString(0x7f0d01f7)));
                edge.startPointTarget = bundle1.getString(context.getString(0x7f0d01fb));
                edge.goalPointTarget = bundle1.getString(context.getString(0x7f0d01f0));
                edge.railDispName = bundle1.getString(context.getString(0x7f0d01f5));
                edge.railname = bundle1.getString(context.getString(0x7f0d01f6));
                edge.AirportTicketLinkSP = bundle1.getString(context.getString(0x7f0d01e8));
                edge.AirportDPLinkSP = bundle1.getString(context.getString(0x7f0d01e6));
                edge.AirportDPLinkSPDisp = bundle1.getString(context.getString(0x7f0d01e7));
                arraylist.add(edge);
                j++;
            }
        }
        return arraylist;
    }

    public static String getEllipsisString(String s, float f, float f1)
    {
        Paint paint = new Paint();
        paint.setTextSize(f1);
        paint.setSubpixelText(true);
        if (paint.measureText(s) <= f)
        {
            return s;
        }
        int i = paint.breakText(s, true, f, null);
        String s1 = s.substring(0, i - 1);
        if (paint.measureText(s1) + paint.measureText("\u2026") > f)
        {
            s1 = s.substring(0, i - 2);
        }
        return (new StringBuilder()).append(s1).append("\u2026").toString();
    }

    public static int getLatLngInt(String s)
    {
        double d;
        try
        {
            d = Double.parseDouble(s);
        }
        catch (Exception exception)
        {
            return 0;
        }
        return (int)(d * 1000000D);
    }

    public static String getLatLngString(int i)
    {
        return Double.toString((double)i / 1000000D);
    }

    private static HashMap getNaviDictionary(Context context, Bundle bundle)
    {
        HashMap hashmap;
        if (bundle == null)
        {
            hashmap = null;
        } else
        {
            hashmap = new HashMap();
            Iterator iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) 
            {
                Bundle bundle1 = bundle.getBundle((String)iterator.next());
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData();
                navipointdata.target = bundle1.getString(context.getString(0x7f0d0225));
                navipointdata.stationName = bundle1.getString(context.getString(0x7f0d0226));
                navipointdata.stationCode = bundle1.getString(context.getString(0x7f0d0223));
                if (bundle1.getString(context.getString(0x7f0d01e3)) != null)
                {
                    navipointdata.areaCode = Integer.parseInt(bundle1.getString(context.getString(0x7f0d01e3)));
                }
                navipointdata.type = bundle1.getInt(context.getString(0x7f0d0227));
                if (navipointdata.type == 128)
                {
                    String as1[] = bundle1.getString(context.getString(0x7f0d0210)).split(",");
                    if (as1 != null && as1.length > 1)
                    {
                        navipointdata.nearStLon = as1[0];
                        navipointdata.nearStLat = as1[1];
                    }
                }
                String as[] = bundle1.getString(context.getString(0x7f0d0224)).split(",");
                if (as != null && as.length > 1)
                {
                    navipointdata.lon = as[0];
                    navipointdata.lat = as[1];
                }
                hashmap.put(navipointdata.target, navipointdata);
            }
        }
        return hashmap;
    }

    private static ArrayList getNaviRoutes(Context context, Bundle bundle)
    {
        ArrayList arraylist;
        if (bundle == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            int i = 0;
            while (bundle.containsKey(Integer.toString(i))) 
            {
                Bundle bundle1 = bundle.getBundle(Integer.toString(i));
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData();
                naviroutedata.startTime = bundle1.getString(context.getString(0x7f0d021d));
                naviroutedata.goalTime = bundle1.getString(context.getString(0x7f0d021c));
                naviroutedata.totaltime = bundle1.getInt(context.getString(0x7f0d0222));
                naviroutedata.totaldistance = bundle1.getInt(context.getString(0x7f0d021f));
                naviroutedata.totalPrice = bundle1.getString(context.getString(0x7f0d0221));
                naviroutedata.totalExpPrice = bundle1.getString(context.getString(0x7f0d0220));
                Bundle bundle2 = bundle1.getBundle(context.getString(0x7f0d0229));
                naviroutedata.Teiki1 = bundle2.getString(context.getString(0x7f0d0212));
                naviroutedata.Teiki3 = bundle2.getString(context.getString(0x7f0d0213));
                naviroutedata.Teiki6 = bundle2.getString(context.getString(0x7f0d0214));
                naviroutedata.cheap = bundle1.getBoolean(context.getString(0x7f0d0219));
                naviroutedata.easy = bundle1.getBoolean(context.getString(0x7f0d021a));
                naviroutedata.fast = bundle1.getBoolean(context.getString(0x7f0d021b));
                naviroutedata.distance = bundle1.getInt(context.getString(0x7f0d021f));
                naviroutedata.transfercount = bundle1.getInt(context.getString(0x7f0d021e));
                naviroutedata.edges = getEdges(context, bundle1.getBundle(context.getString(0x7f0d0209)));
                naviroutedata.edgePrice = getPrices(context, bundle1.getBundle(context.getString(0x7f0d0218)));
                naviroutedata.edgeExpPrice = getPrices(context, bundle1.getBundle(context.getString(0x7f0d020a)));
                arraylist.add(naviroutedata);
                i++;
            }
        }
        return arraylist;
    }

    public static int getNowWeek()
    {
        Calendar calendar = Calendar.getInstance();
        int i;
        if (calendar.get(11) < 3)
        {
            i = -1 + calendar.get(7);
        } else
        {
            i = calendar.get(7);
        }
        if (i == 1 || i == 8)
        {
            return 4;
        }
        return i != 7 && i != 0 ? 1 : 2;
    }

    private static ArrayList getPrices(Context context, Bundle bundle)
    {
        ArrayList arraylist;
        if (bundle == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            Iterator iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price price = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price();
                Bundle bundle1 = bundle.getBundle(s);
                price.edgeFrom = bundle1.getInt(context.getString(0x7f0d0211));
                price.edgeTo = bundle1.getInt(context.getString(0x7f0d0215));
                price.value = bundle1.getString(context.getString(0x7f0d0217));
                price.type = bundle1.getString(context.getString(0x7f0d0216));
                arraylist.add(price);
            }
        }
        return arraylist;
    }

    private static ArrayList getRidingPosition(Context context, Bundle bundle)
    {
        ArrayList arraylist;
        if (bundle == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            int i = bundle.size();
            int j = 0;
            while (j < i) 
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition ridingposition = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPosition();
                Bundle bundle1 = bundle.getBundle(Integer.toString(j));
                if (!isEmpty(bundle1.getString("Direction")))
                {
                    ridingposition.direction = bundle1.getString("Direction");
                }
                ridingposition.isFrontFirstCar = bundle1.getInt("IsFrontFirstCar");
                Bundle bundle2 = bundle1.getBundle("Car");
                ridingposition.Cars = new ArrayList();
                int k = 0;
                if (bundle2 != null)
                {
                    for (; bundle2.containsKey(Integer.toString(k)); k++)
                    {
                        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar ridingpositioncar = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionCar();
                        Bundle bundle3 = bundle2.getBundle(Integer.toString(k));
                        ridingpositioncar.allOutflowsText = bundle3.getString("AllOutflowsText");
                        ridingpositioncar.numOfCar = bundle3.getString("NumOfCar");
                        Bundle bundle4 = bundle3.getBundle("Outflow");
                        ridingpositioncar.outflow = new ArrayList();
                        for (int l = 0; bundle4.containsKey(Integer.toString(l)); l++)
                        {
                            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow ridingpositionoutflow = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.RidingPositionOutflow();
                            Bundle bundle5 = bundle4.getBundle(Integer.toString(l));
                            ridingpositionoutflow.carNo = bundle5.getString("CarNo");
                            ridingpositionoutflow.Means = bundle5.getString("Means");
                            ridingpositioncar.outflow.add(ridingpositionoutflow);
                        }

                        ridingposition.Cars.add(ridingpositioncar);
                    }

                }
                arraylist.add(ridingposition);
                j++;
            }
        }
        return arraylist;
    }

    private static ArrayList getStopStations(Context context, Bundle bundle)
    {
        ArrayList arraylist;
        if (bundle == null)
        {
            arraylist = null;
        } else
        {
            arraylist = new ArrayList();
            int i = bundle.size();
            int j = 0;
            while (j < i) 
            {
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation stopstation = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation();
                Bundle bundle1 = bundle.getBundle(Integer.toString(j));
                stopstation.code = bundle1.getString(context.getString(0x7f0d0202));
                stopstation.name = bundle1.getString(context.getString(0x7f0d0203));
                stopstation.departureTime = bundle1.getString(context.getString(0x7f0d0204));
                stopstation.departureUnixTimestamp = Long.valueOf(bundle1.getLong(context.getString(0x7f0d0201)));
                stopstation.arraivalTime = bundle1.getString(context.getString(0x7f0d01ff));
                stopstation.arrivalUnixTimestamp = Long.valueOf(bundle1.getLong(context.getString(0x7f0d0200)));
                arraylist.add(stopstation);
                j++;
            }
        }
        return arraylist;
    }

    public static int getTimeInt(String s)
    {
        int i;
        if (isEmpty(s) || s.length() > 4)
        {
            i = 0;
        } else
        {
            int j;
            int k;
            try
            {
                j = Integer.parseInt(s);
            }
            catch (Exception exception)
            {
                return 0;
            }
            k = j / 100;
            i = j % 100 + k * 60;
            if (i < 180)
            {
                return i + 1440;
            }
        }
        return i;
    }

    public static String getTotalTimeString(Context context, int i)
    {
        int j = i;
        int k = 0;
        if (60 <= j)
        {
            k = j / 60;
            j %= 60;
        }
        String s;
        if (k > 0)
        {
            s = Integer.toString(k) + context.getString(0x7f0d02d2) + Integer.toString(j) + context.getString(0x7f0d02d4);
        } else
        {
            s = Integer.toString(j) + context.getString(0x7f0d02d4);
        }
        return s.toString();
    }

    public static int getVersionCode(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionCode;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return -1;
        }
        return i;
    }

    public static String getVersionName(Context context)
    {
        String s;
        try
        {
            s = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionName;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return "";
        }
        return s;
    }

    public static int getWindowDisplayWidth(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT < 13)
        {
            return getDisplayWidth(context);
        } else
        {
            return getDisplaySizeWidth(context);
        }
    }

    public static String getYID(Context context)
    {
        String s;
        try
        {
            s = (new DataManager(context, DataManager.loadSecretKey(context, "default_yid"), "default_yid")).loadIdToken().getUserId();
        }
        catch (SecretStorageException secretstorageexception)
        {
            return null;
        }
        catch (NullPointerException nullpointerexception)
        {
            return null;
        }
        catch (Exception exception)
        {
            return null;
        }
        return s;
    }

    public static boolean isAppInstalled(Context context, String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        try
        {
            packagemanager.getPackageInfo(s, 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        return true;
    }

    public static boolean isAppInstalled(Context context, String s, int i)
    {
        PackageManager packagemanager = context.getPackageManager();
        int j;
        boolean flag;
        try
        {
            j = packagemanager.getPackageInfo(s, 0).versionCode;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        flag = false;
        if (j >= i)
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isBlank(Object obj)
    {
        return obj == null || obj.toString().equals("");
    }

    public static boolean isBlankMap(Map map)
    {
        for (Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            if (!isBlank(iterator.next()))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isCheckDiainfo(Context context)
    {
        Map map = (new SetSharedPreferences(context, context.getString(0x7f0d00c1))).getSharedPreferenceData();
        if (map != null && !map.isEmpty())
        {
            String s = (String)map.get(context.getString(0x7f0d01ad));
            if (s != null && s.equals("true"))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isContain(Bundle bundle, String s)
    {
        boolean flag = bundle.containsKey(s);
        boolean flag1 = false;
        if (flag)
        {
            Object obj = bundle.get(s);
            flag1 = false;
            if (obj != null)
            {
                boolean flag2 = bundle.get(s).equals("");
                flag1 = false;
                if (!flag2)
                {
                    flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static boolean isEmpty(String s)
    {
        return s == null || s.equals("") || s.length() == 0;
    }

    public static boolean isEmptyArry(ArrayList arraylist)
    {
        if (arraylist != null && arraylist.size() >= 1)
        {
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                if (!isEmpty((String)iterator.next()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return false;
        }
        return true;
    }

    public static boolean isNoEmptyArry(ArrayList arraylist)
    {
        if (arraylist == null || arraylist.size() < 1)
        {
            return true;
        }
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            if (isEmpty((String)iterator.next()))
            {
                return true;
            }
        }

        return false;
    }

    public static String join(List list, String s)
    {
        if (list == null)
        {
            return null;
        }
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++)
        {
            if (i > 0)
            {
                stringbuffer.append(s);
            }
            stringbuffer.append((String)list.get(i));
        }

        return stringbuffer.toString();
    }

    public static String join(String as[], String s)
    {
        if (as == null)
        {
            return null;
        }
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < as.length; i++)
        {
            if (i > 0)
            {
                stringbuffer.append(s);
            }
            stringbuffer.append(as[i]);
        }

        return stringbuffer.toString();
    }

    public static void login(Activity activity)
    {
        YHBGRd.sendAsync(activity.getApplicationContext(), new String[] {
            "login"
        });
        AppLoginExplicit apploginexplicit = AppLoginExplicit.getInstance();
        apploginexplicit.setClientId(activity.getString(0x7f0d0037));
        apploginexplicit.setCustomUriScheme(activity.getString(0x7f0d0568));
        apploginexplicit.login(activity, 1000);
    }

    public static void logout(Activity activity)
    {
        YHBGRd.sendAsync(activity.getApplicationContext(), new String[] {
            "logout"
        });
        deleteUserInfoData(activity);
        AppLoginExplicit apploginexplicit = AppLoginExplicit.getInstance();
        apploginexplicit.setClientId(activity.getString(0x7f0d0037));
        apploginexplicit.setCustomUriScheme(activity.getString(0x7f0d0568));
        apploginexplicit.logout(activity, 1100);
    }

    public static byte[] obj2byte(Object obj)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[];
        try
        {
            (new ObjectOutputStream(bytearrayoutputstream)).writeObject(obj);
            abyte0 = bytearrayoutputstream.toByteArray();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
        return abyte0;
    }

    public static void openURL(Context context, String s)
    {
        YBrowserFRUtils.openURL((Activity)context, s, context.getString(0x7f0d0597), 0xff000000);
    }

    public static float pxToDp(Context context, float f)
    {
        return f / context.getResources().getDisplayMetrics().density;
    }

    public static void removeGlobalOnLayoutListener(ViewTreeObserver viewtreeobserver, android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener)
    {
        viewtreeobserver.removeGlobalOnLayoutListener(ongloballayoutlistener);
    }

    public static void removeOnGlobalLayoutListener(ViewTreeObserver viewtreeobserver, android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener)
    {
        viewtreeobserver.removeOnGlobalLayoutListener(ongloballayoutlistener);
    }

    public static void setCheckDiainfo(Context context, boolean flag)
    {
        SetSharedPreferences setsharedpreferences = new SetSharedPreferences(context, context.getString(0x7f0d00c1));
        HashMap hashmap = new HashMap();
        if (flag)
        {
            hashmap.put(context.getString(0x7f0d01ad), "true");
        } else
        {
            hashmap.put(context.getString(0x7f0d01ad), "false");
        }
        setsharedpreferences.setSharedPreferenceData(hashmap);
    }

    public static void setEllipsisTextBeforeLabel(TextView textview, String s)
    {
        textview.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener(textview, s) {

            final String val$label;
            final TextView val$textView;

            public void onGlobalLayout()
            {
                Layout layout = textView.getLayout();
                boolean flag = false;
                if (layout != null)
                {
                    int k = layout.getLineCount();
                    flag = false;
                    if (k > 0)
                    {
                        int l = layout.getEllipsisCount(k - 1);
                        flag = false;
                        if (l > 0)
                        {
                            flag = true;
                        }
                    }
                }
                if (flag)
                {
                    String s1 = textView.getText().toString();
                    int i = textView.getWidth();
                    float f = textView.getTextSize();
                    Paint paint = new Paint();
                    paint.setTextSize(f);
                    paint.setSubpixelText(true);
                    float f1 = paint.measureText(label);
                    int j = (int)((float)i - f1);
                    String s2 = TransitUtil.getEllipsisString(s1.substring(0, s1.length() - label.length()), j, paint.getTextSize());
                    textView.setText((new StringBuilder()).append(s2).append(label).toString());
                }
                try
                {
                    if (android.os.Build.VERSION.SDK_INT < 16)
                    {
                        TransitUtil.removeGlobalOnLayoutListener(textView.getViewTreeObserver(), this);
                        return;
                    }
                }
                catch (Exception exception)
                {
                    return;
                }
                TransitUtil.removeOnGlobalLayoutListener(textView.getViewTreeObserver(), this);
                return;
            }

            
            {
                textView = textview;
                label = s;
                super();
            }
        });
    }

    public static void shareAnotherAppExecute(Context context, String s)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", s);
        Intent intent1 = Intent.createChooser(intent, context.getString(0x7f0d04b2));
        try
        {
            context.startActivity(intent1);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Toast.makeText(context, context.getString(0x7f0d0369), 1).show();
        }
    }

    public static String strTrimWidth(String s, int i, String s1)
    {
        if (s.length() <= i)
        {
            return s;
        }
        String s2;
        if (isEmpty(s1))
        {
            s2 = s.substring(0, i + 1);
        } else
        {
            s2 = (new StringBuilder()).append(s.substring(0, (i + 1) - s1.length())).append(s1).toString();
        }
        return s2;
    }

    public static Calendar timeStringToCalendar(String s)
    {
        if (isEmpty(s))
        {
            return null;
        } else
        {
            int i = Integer.parseInt(s.substring(0, 4));
            int j = Integer.parseInt(s.substring(4, 6));
            int k = Integer.parseInt(s.substring(6, 8));
            int l = Integer.parseInt(s.substring(8, 10));
            int i1 = Integer.parseInt(s.substring(10, 12));
            Calendar calendar = Calendar.getInstance();
            calendar.set(i, j - 1, k, l, i1);
            return calendar;
        }
    }

    public static Calendar toCalendar(String s)
    {
        if (s != null && s.trim().length() >= 8) goto _L2; else goto _L1
_L1:
        Calendar calendar = null;
_L4:
        return calendar;
_L2:
        int l;
        calendar = Calendar.getInstance();
        calendar.setLenient(false);
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(5, 7));
        int k = Integer.parseInt(s.substring(8, 10));
        calendar.get(11);
        calendar.get(12);
        calendar.get(13);
        calendar.get(14);
        calendar.clear();
        calendar.set(i, j - 1, k);
        switch (s.length())
        {
        default:
            return null;

        case 10: // '\n'
            break;

        case 16: // '\020'
            int k2 = Integer.parseInt(s.substring(11, 13));
            int l2 = Integer.parseInt(s.substring(14, 16));
            calendar.set(11, k2);
            calendar.set(12, l2);
            return calendar;

        case 19: // '\023'
            int l1 = Integer.parseInt(s.substring(11, 13));
            int i2 = Integer.parseInt(s.substring(14, 16));
            int j2 = Integer.parseInt(s.substring(17, 19));
            calendar.set(11, l1);
            calendar.set(12, i2);
            calendar.set(13, j2);
            return calendar;

        case 23: // '\027'
            l = Integer.parseInt(s.substring(11, 13));
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i1 = Integer.parseInt(s.substring(14, 16));
        int j1 = Integer.parseInt(s.substring(17, 19));
        int k1 = Integer.parseInt(s.substring(20, 23));
        calendar.set(11, l);
        calendar.set(12, i1);
        calendar.set(13, j1);
        calendar.set(14, k1);
        return calendar;
    }

    public static void touchRD(String s)
    {
        (new AsyncTask(s) {

            final String val$aURL;

            protected transient Boolean doInBackground(Void avoid[])
            {
                InputStream inputstream;
                HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(aURL)).openConnection();
                httpurlconnection.setInstanceFollowRedirects(false);
                httpurlconnection.setConnectTimeout(3000);
                httpurlconnection.setReadTimeout(3000);
                httpurlconnection.connect();
                inputstream = httpurlconnection.getInputStream();
                boolean flag = true;
                Exception exception;
                Exception exception1;
                IOException ioexception1;
                if (inputstream != null)
                {
                    try
                    {
                        inputstream.close();
                    }
                    catch (IOException ioexception2) { }
                }
                return Boolean.valueOf(flag);
                exception1;
                flag = false;
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
                null.close();
                flag = false;
                continue; /* Loop/switch isn't completed */
                ioexception1;
                flag = false;
                if (true) goto _L2; else goto _L1
_L1:
                break MISSING_BLOCK_LABEL_99;
_L2:
                break MISSING_BLOCK_LABEL_66;
                exception;
                if (false)
                {
                    try
                    {
                        null.close();
                    }
                    catch (IOException ioexception) { }
                }
                throw exception;
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected void onPostExecute(Boolean boolean1)
            {
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Boolean)obj);
            }

            
            {
                aURL = s;
                super();
            }
        }).execute(new Void[0]);
    }

    public static String trim(String s)
    {
        int i = s.length();
        int j = 0;
        char ac[];
        for (ac = s.toCharArray(); j < i && (ac[j] <= ' ' || ac[j] == '\u3000'); j++) { }
        for (; j < i && (ac[i - 1] <= ' ' || ac[i - 1] == '\u3000'); i--) { }
        if (j > 0 || i < s.length())
        {
            s = s.substring(j, i);
        }
        return s;
    }

    public static Bundle uriToCond(Uri uri, Bundle bundle, Context context)
    {
        boolean flag = StringUtil.isEmpty(uri.getQueryParameter(context.getString(0x7f0d0385)));
        boolean flag1 = false;
        if (!flag)
        {
            flag1 = true;
        }
        String s = uri.getQueryParameter(context.getString(0x7f0d037c));
        if (s == null)
        {
            s = "";
        }
        String s1 = uri.getQueryParameter(context.getString(0x7f0d037d));
        if (s1 != null && s1 != "")
        {
            bundle.putString(context.getString(0x7f0d018f), s1);
        }
        String s2;
        if (s.indexOf(",") > 0)
        {
            String as1[] = s.split(",");
            String s3;
            String s4;
            String s5;
            String s6;
            String s7;
            String s8;
            String s9;
            String s10;
            String s11;
            String s12;
            String s13;
            String s14;
            String s15;
            String s16;
            String s17;
            String s18;
            String s19;
            int i;
            int j;
            int k;
            String s20;
            int l;
            String s21;
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            String as[];
            if (as1.length == 3)
            {
                bundle.putString(context.getString(0x7f0d0192), as1[0]);
                bundle.putString(context.getString(0x7f0d0191), as1[1]);
                bundle.putString(context.getString(0x7f0d0190), as1[2]);
            } else
            {
                bundle.putString(context.getString(0x7f0d0192), s);
            }
        } else
        {
            bundle.putString(context.getString(0x7f0d0192), s);
        }
        s2 = uri.getQueryParameter(context.getString(0x7f0d038c));
        if (s2 == null)
        {
            s2 = "";
        }
        s3 = uri.getQueryParameter(context.getString(0x7f0d038d));
        if (s3 != null && s3 != "")
        {
            bundle.putString(context.getString(0x7f0d0185), s3);
        }
        if (s2.indexOf(",") > 0)
        {
            as = s2.split(",");
            if (as.length == 3)
            {
                bundle.putString(context.getString(0x7f0d0188), as[0]);
                bundle.putString(context.getString(0x7f0d0187), as[1]);
                bundle.putString(context.getString(0x7f0d0186), as[2]);
            } else
            {
                bundle.putString(context.getString(0x7f0d0188), s2);
            }
        } else
        {
            bundle.putString(context.getString(0x7f0d0188), s2);
        }
        s4 = uri.getQueryParameter(context.getString(0x7f0d0391));
        if (s4 != null && s4 != "")
        {
            bundle.putString(context.getString(0x7f0d019d), s4);
        }
        s5 = uri.getQueryParameter(context.getString(0x7f0d0390));
        s6 = context.getString(0x7f0d019c);
        if (s5 == null)
        {
            s5 = "";
        }
        bundle.putString(s6, s5);
        s7 = uri.getQueryParameter(context.getString(0x7f0d0376));
        if (s7 != null && 8 == s7.length())
        {
            try
            {
                k1 = Integer.parseInt(s7.substring(0, 4));
                l1 = Integer.parseInt(s7.substring(4, 6));
                i2 = Integer.parseInt(s7.substring(6, 8));
                bundle.putInt(context.getString(0x7f0d019f), k1);
                bundle.putInt(context.getString(0x7f0d018b), l1);
                bundle.putInt(context.getString(0x7f0d0184), i2);
            }
            catch (Exception exception7)
            {
                Calendar calendar1 = Calendar.getInstance();
                bundle.putInt(context.getString(0x7f0d019f), calendar1.get(1));
                bundle.putInt(context.getString(0x7f0d018b), 1 + calendar1.get(2));
                bundle.putInt(context.getString(0x7f0d0184), calendar1.get(5));
            }
        }
        if (flag1)
        {
            s21 = uri.getQueryParameter(context.getString(0x7f0d038b));
            if (s21 != null && 4 == s21.length())
            {
                try
                {
                    i1 = Integer.parseInt(s21.substring(0, 2));
                    j1 = Integer.parseInt(s21.substring(2, 4));
                    bundle.putInt(context.getString(0x7f0d0189), i1);
                    bundle.putInt(context.getString(0x7f0d018a), j1);
                }
                catch (Exception exception6)
                {
                    Calendar calendar = Calendar.getInstance();
                    bundle.putInt(context.getString(0x7f0d0189), calendar.get(11));
                    bundle.putInt(context.getString(0x7f0d018a), calendar.get(12));
                }
            }
        }
        if (flag1)
        {
            s20 = uri.getQueryParameter(context.getString(0x7f0d038f));
            Exception exception;
            Exception exception1;
            Exception exception2;
            Exception exception3;
            Exception exception4;
            if (!StringUtil.isEmpty(s20))
            {
                try
                {
                    l = Integer.parseInt(s20);
                    bundle.putInt(context.getString(0x7f0d0194), l);
                }
                catch (Exception exception5) { }
            }
        } else
        {
            bundle.putInt(context.getString(0x7f0d0194), context.getResources().getInteger(0x7f0c006a));
        }
        s8 = uri.getQueryParameter(context.getString(0x7f0d0387));
        if (!StringUtil.isEmpty(s8))
        {
            try
            {
                k = Integer.parseInt(s8);
                bundle.putInt(context.getString(0x7f0d018e), k);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception4) { }
        }
        s9 = uri.getQueryParameter(context.getString(0x7f0d0395));
        if (!StringUtil.isEmpty(s9))
        {
            try
            {
                j = Integer.parseInt(s9);
                bundle.putInt(context.getString(0x7f0d019e), j);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception3) { }
        }
        s10 = uri.getQueryParameter(context.getString(0x7f0d0379));
        if (!StringUtil.isEmpty(s10))
        {
            try
            {
                i = Integer.parseInt(s10);
                bundle.putInt(context.getString(0x7f0d018d), i);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception2) { }
        }
        s11 = uri.getQueryParameter(context.getString(0x7f0d038e));
        if (!StringUtil.isEmpty(s11) && "0".equals(s11))
        {
            bundle.putBoolean(context.getString(0x7f0d019b), false);
        }
        s12 = uri.getQueryParameter(context.getString(0x7f0d0375));
        if (!StringUtil.isEmpty(s12) && "0".equals(s12))
        {
            bundle.putBoolean(context.getString(0x7f0d0196), false);
        }
        s13 = uri.getQueryParameter(context.getString(0x7f0d0373));
        if (!StringUtil.isEmpty(s13) && "0".equals(s13))
        {
            bundle.putBoolean(context.getString(0x7f0d0195), false);
        }
        s14 = uri.getQueryParameter(context.getString(0x7f0d037a));
        if (!StringUtil.isEmpty(s14) && "0".equals(s14))
        {
            bundle.putBoolean(context.getString(0x7f0d0197), false);
        }
        s15 = uri.getQueryParameter(context.getString(0x7f0d0386));
        if (!StringUtil.isEmpty(s15) && "0".equals(s15))
        {
            bundle.putBoolean(context.getString(0x7f0d019a), false);
        }
        s16 = uri.getQueryParameter(context.getString(0x7f0d037b));
        if (!StringUtil.isEmpty(s16) && "0".equals(s16))
        {
            bundle.putBoolean(context.getString(0x7f0d0198), false);
        }
        s17 = uri.getQueryParameter(context.getString(0x7f0d0383));
        if (!StringUtil.isEmpty(s17))
        {
            try
            {
                bundle.putInt(context.getString(0x7f0d0383), Integer.parseInt(s17));
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1) { }
        }
        s18 = uri.getQueryParameter(context.getString(0x7f0d0382));
        if (!StringUtil.isEmpty(s18))
        {
            bundle.putString(context.getString(0x7f0d0382), s18);
        }
        s19 = uri.getQueryParameter(context.getString(0x7f0d018c));
        if (!StringUtil.isEmpty(s19))
        {
            try
            {
                bundle.putInt(context.getString(0x7f0d018c), Integer.parseInt(s19));
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                return bundle;
            }
        }
        return bundle;
    }

    public static ConditionData uriToCond(Uri uri, ConditionData conditiondata, Context context)
    {
        return uriToCond(uri, conditiondata, context, false);
    }

    public static ConditionData uriToCond(Uri uri, ConditionData conditiondata, Context context, boolean flag)
    {
        boolean flag1 = flag;
        if (!flag1 && !StringUtil.isEmpty(uri.getQueryParameter(context.getString(0x7f0d0385))))
        {
            flag1 = true;
        }
        String s = uri.getQueryParameter(context.getString(0x7f0d037c));
        if (s == null)
        {
            s = "";
        }
        String s1 = uri.getQueryParameter(context.getString(0x7f0d037d));
        if (s1 != null && s1 != "")
        {
            conditiondata.startCode = s1;
        }
        String s2;
        if (s.indexOf(",") > 0)
        {
            String as1[] = s.split(",");
            String s3;
            String s4;
            String s5;
            String s9;
            String s10;
            String s11;
            String s12;
            String s13;
            String s20;
            String s21;
            String s22;
            String s23;
            int i;
            int j;
            int k;
            int l;
            int i1;
            String s24;
            int j1;
            int k1;
            String as[];
            if (as1.length == 3)
            {
                conditiondata.startName = as1[0];
                conditiondata.startLat = as1[1];
                conditiondata.startLon = as1[2];
            } else
            {
                conditiondata.startName = s;
            }
        } else
        {
            conditiondata.startName = s;
        }
        s2 = uri.getQueryParameter(context.getString(0x7f0d038c));
        if (s2 == null)
        {
            s2 = "";
        }
        s3 = uri.getQueryParameter(context.getString(0x7f0d038d));
        if (!isEmpty(s3))
        {
            conditiondata.goalCode = s3;
        }
        if (s2.indexOf(",") > 0)
        {
            as = s2.split(",");
            if (as.length == 3)
            {
                conditiondata.goalName = as[0];
                conditiondata.goalLat = as[1];
                conditiondata.goalLon = as[2];
            } else
            {
                conditiondata.goalName = s2;
            }
        } else
        {
            conditiondata.goalName = s2;
        }
        s4 = uri.getQueryParameter(context.getString(0x7f0d0393));
        s5 = uri.getQueryParameter(context.getString(0x7f0d0394));
        if (!isEmpty(s4) && !isEmpty(s5))
        {
            conditiondata.viaName = comvertArryList(s5.split(",", -1));
            conditiondata.viaCode = comvertArryList(s4.split(",", -1));
        } else
        {
            String s6 = uri.getQueryParameter(context.getString(0x7f0d0392));
            String s7 = uri.getQueryParameter(context.getString(0x7f0d0391));
            String s8 = uri.getQueryParameter(context.getString(0x7f0d0390));
            if (!isEmpty(s6))
            {
                conditiondata.viaCode = comvertArryList(s6.split(",", -1));
            } else
            if (!isEmpty(s7))
            {
                conditiondata.viaCode = comvertArryList(s7.split(",", -1));
            }
            if (!isEmpty(s8))
            {
                conditiondata.viaName = comvertArryList(s8.split(",", -1));
            }
        }
        if (!flag1) goto _L2; else goto _L1
_L1:
        s24 = uri.getQueryParameter(context.getString(0x7f0d038f));
        if (StringUtil.isEmpty(s24))
        {
            break MISSING_BLOCK_LABEL_375;
        }
        j1 = Integer.parseInt(s24);
        k1 = context.getResources().getInteger(0x7f0c006c);
        if (j1 != k1)
        {
            break MISSING_BLOCK_LABEL_369;
        }
        j1 = context.getResources().getInteger(0x7f0c006b);
        conditiondata.type = j1;
_L4:
        s9 = uri.getQueryParameter(context.getString(0x7f0d0376));
        String s14;
        String s15;
        String s16;
        String s17;
        String s18;
        String s19;
        if (s9 != null && 8 == s9.length())
        {
            try
            {
                k = Integer.parseInt(s9.substring(0, 4));
                l = Integer.parseInt(s9.substring(4, 6));
                i1 = Integer.parseInt(s9.substring(6, 8));
                conditiondata.year = k;
                conditiondata.month = l;
                conditiondata.day = i1;
            }
            catch (Exception exception6)
            {
                Calendar calendar1 = Calendar.getInstance();
                conditiondata.year = calendar1.get(1);
                conditiondata.month = 1 + calendar1.get(2);
                conditiondata.day = calendar1.get(5);
            }
        } else
        if (conditiondata.type == context.getResources().getInteger(0x7f0c0070) || conditiondata.type == context.getResources().getInteger(0x7f0c006e))
        {
            conditiondata.type = context.getResources().getInteger(0x7f0c006b);
        }
        if (flag1)
        {
            s23 = uri.getQueryParameter(context.getString(0x7f0d038b));
            if (s23 != null && 4 == s23.length())
            {
                try
                {
                    i = Integer.parseInt(s23.substring(0, 2));
                    j = Integer.parseInt(s23.substring(2, 4));
                    conditiondata.hour = i;
                    conditiondata.minite = j;
                }
                catch (Exception exception5)
                {
                    Calendar calendar = Calendar.getInstance();
                    conditiondata.hour = calendar.get(11);
                    conditiondata.minite = calendar.get(12);
                }
            }
        }
        s10 = uri.getQueryParameter(context.getString(0x7f0d0387));
        if (!StringUtil.isEmpty(s10))
        {
            try
            {
                conditiondata.sort = Integer.parseInt(s10);
            }
            catch (Exception exception4) { }
        }
        s11 = uri.getQueryParameter(context.getString(0x7f0d0395));
        if (!StringUtil.isEmpty(s11))
        {
            try
            {
                conditiondata.walkSpeed = Integer.parseInt(s11);
            }
            catch (Exception exception3) { }
        }
        s12 = uri.getQueryParameter(context.getString(0x7f0d0379));
        if (!StringUtil.isEmpty(s12))
        {
            try
            {
                conditiondata.seatKind = Integer.parseInt(s12);
            }
            catch (Exception exception2) { }
        }
        s13 = uri.getQueryParameter(context.getString(0x7f0d038a));
        if (!StringUtil.isEmpty(s13))
        {
            conditiondata.ticket = s13;
        }
        s14 = uri.getQueryParameter(context.getString(0x7f0d038e));
        if (!StringUtil.isEmpty(s14))
        {
            if ("0".equals(s14))
            {
                conditiondata.superExpress = false;
            } else
            if ("1".equals(s14))
            {
                conditiondata.superExpress = true;
            }
        }
        s15 = uri.getQueryParameter(context.getString(0x7f0d0375));
        if (!StringUtil.isEmpty(s15))
        {
            if ("0".equals(s15))
            {
                conditiondata.express = false;
            } else
            if ("1".equals(s15))
            {
                conditiondata.express = true;
            }
        }
        s16 = uri.getQueryParameter(context.getString(0x7f0d0373));
        if (!StringUtil.isEmpty(s16))
        {
            if ("0".equals(s16))
            {
                conditiondata.airplane = false;
            } else
            if ("1".equals(s16))
            {
                conditiondata.airplane = true;
            }
        }
        s17 = uri.getQueryParameter(context.getString(0x7f0d037a));
        if (!StringUtil.isEmpty(s17))
        {
            if ("0".equals(s17))
            {
                conditiondata.highwayBus = false;
            } else
            if ("1".equals(s17))
            {
                conditiondata.highwayBus = true;
            }
        }
        s18 = uri.getQueryParameter(context.getString(0x7f0d0386));
        if (!StringUtil.isEmpty(s18))
        {
            if ("0".equals(s18))
            {
                conditiondata.localBus = false;
            } else
            if ("1".equals(s18))
            {
                conditiondata.localBus = true;
            }
        }
        s19 = uri.getQueryParameter(context.getString(0x7f0d037b));
        if (!StringUtil.isEmpty(s19))
        {
            if ("0".equals(s19))
            {
                conditiondata.ferry = false;
            } else
            if ("1".equals(s19))
            {
                conditiondata.ferry = true;
            }
        }
        s20 = uri.getQueryParameter(context.getString(0x7f0d0383));
        if (!StringUtil.isEmpty(s20))
        {
            try
            {
                conditiondata.mtf = Integer.parseInt(s20);
            }
            catch (Exception exception1) { }
        }
        s21 = uri.getQueryParameter(context.getString(0x7f0d0382));
        if (!StringUtil.isEmpty(s21))
        {
            conditiondata.paramMode = s21;
        }
        s22 = uri.getQueryParameter(context.getString(0x7f0d018c));
        if (!StringUtil.isEmpty(s22))
        {
            try
            {
                conditiondata.searchType = Integer.parseInt(s22);
            }
            catch (Exception exception)
            {
                return conditiondata;
            }
        }
        return conditiondata;
_L2:
        conditiondata.type = context.getResources().getInteger(0x7f0c006a);
        continue; /* Loop/switch isn't completed */
        Exception exception7;
        exception7;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
