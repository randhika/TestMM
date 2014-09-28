// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultViewBase, TransitBaseActivity, RidingPositionActivity

public class SearchResultView extends SearchResultViewBase
{
    private static class ImakokoHandler extends Handler
    {

        private final WeakReference refView;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 0: default 24
        //                       0 25;
               goto _L1 _L2
_L1:
            SearchResultView searchresultview;
            return;
_L2:
            if ((searchresultview = (SearchResultView)refView.get()) != null)
            {
                Calendar calendar = Calendar.getInstance();
                int i = calendar.get(13);
                int j = calendar.get(14);
                int k = -1;
                if (i == 0)
                {
                    k = 60000 - j;
                } else
                if (i > 58)
                {
                    k = 61000 - j;
                } else
                if (i < 2)
                {
                    k = 59000 - j;
                }
                if (k != -1)
                {
                    if (searchresultview.setImakokoAnimation(false))
                    {
                        searchresultview.handler.sendMessageDelayed(obtainMessage(), k);
                        return;
                    } else
                    {
                        searchresultview.handler.removeMessages(0);
                        return;
                    }
                } else
                {
                    searchresultview.handler.sendMessageDelayed(obtainMessage(), 60000 - i * 1000 - j);
                    return;
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public ImakokoHandler(SearchResultView searchresultview)
        {
            refView = new WeakReference(searchresultview);
        }
    }

    public static interface SearchResultCallback
    {

        public abstract void onChangedImakoko(boolean flag);

        public abstract void scrollToImakoko(int i);
    }


    private SearchResultCallback callback;
    private boolean firstTime;
    private ImakokoHandler handler;
    private ImageView imakokoIcon;
    private int imakokoPosition;
    private int imakokoSsPosition;
    private boolean isAirPlane;
    private LinearLayout layout;
    private ArrayList recvEdges;
    private int staTypeInsert;
    private int staTypeLandmark;
    private int staTypeReturn;
    private int staTypeStation;
    private int trafficAirLine;
    private int trafficAirplane;
    private int trafficExpresswayBus;
    private int trafficIntercityBus;
    private int trafficLandmark;
    private int trafficRouteBus;
    private int trafficShip;
    private int trafficWalk;

    public SearchResultView(Context context, SearchResultCallback searchresultcallback)
    {
        super(context);
        isAirPlane = false;
        imakokoIcon = null;
        imakokoPosition = 0;
        imakokoSsPosition = 0;
        firstTime = true;
        callback = searchresultcallback;
    }

    private int changeColorFormat(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("%09d", aobj);
        String as[] = new String[3];
        for (int j = 0; j < 3; j++)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(Integer.parseInt(s.substring(j * 3, 3 + j * 3)));
            as[j] = String.format("%02x", aobj1);
        }

        return Color.parseColor("#" + as[0] + as[1] + as[2]);
    }

    private Calendar getTimeInMillisFrom(String s)
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

    private AlphaAnimation imakokoAnimation()
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
        alphaanimation.setRepeatMode(2);
        alphaanimation.setDuration(1500L);
        alphaanimation.setRepeatCount(-1);
        return alphaanimation;
    }

    private boolean isAfterTime(Calendar calendar, Calendar calendar1, boolean flag)
    {
        if (calendar1 != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.getTimeInMillis() / 60000L < calendar1.getTimeInMillis() / 60000L) goto _L1; else goto _L3
_L3:
        return true;
        if (calendar.getTimeInMillis() / 60000L <= calendar1.getTimeInMillis() / 60000L) goto _L1; else goto _L4
_L4:
        return true;
    }

    private boolean isBeforeTime(Calendar calendar, Calendar calendar1, boolean flag)
    {
        if (calendar1 != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.getTimeInMillis() / 60000L > calendar1.getTimeInMillis() / 60000L) goto _L1; else goto _L3
_L3:
        return true;
        if (calendar.getTimeInMillis() / 60000L >= calendar1.getTimeInMillis() / 60000L) goto _L1; else goto _L4
_L4:
        return true;
    }

    private boolean isEqualTime(Calendar calendar, Calendar calendar1)
    {
        while (calendar1 == null || calendar.getTimeInMillis() / 60000L != calendar1.getTimeInMillis() / 60000L) 
        {
            return false;
        }
        return true;
    }

    private String returnTime(Calendar calendar, Calendar calendar1)
    {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(calendar1.getTimeInMillis() - calendar.getTimeInMillis() - (long)calendar2.getTimeZone().getRawOffset());
        String as[] = (new SimpleDateFormat("HH:mm")).format(calendar2.getTime()).split(":");
        String s = as[0];
        int i = 0;
        if (s != null)
        {
            String s1 = as[0];
            i = 0;
            if (s1 != "00")
            {
                i = 60 * Integer.parseInt(as[0]);
            }
        }
        return (new StringBuilder()).append(i + Integer.parseInt(as[1])).append("\u5206").toString();
    }

    private void setImakokoIcon(ImageView imageview, int i, int j)
    {
        if (imakokoIcon != null) goto _L2; else goto _L1
_L1:
        if (callback != null)
        {
            callback.onChangedImakoko(true);
        }
_L4:
        imageview.setVisibility(0);
        imageview.startAnimation(imakokoAnimation());
        imakokoIcon = imageview;
        imakokoPosition = i;
        if (j != -1)
        {
            imakokoSsPosition = j;
        }
        return;
_L2:
        if (!imakokoIcon.equals(imageview))
        {
            imakokoIcon.clearAnimation();
            imakokoIcon.setVisibility(8);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void setPriceLine(ArrayList arraylist, int i, View view, boolean flag)
    {
        LinearLayout linearlayout1;
        int j;
        ImageView imageview;
        ImageView imageview1;
        TextView textview1;
        String s;
        int i1;
        int l1;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price price;
        RelativeLayout relativelayout = (RelativeLayout)view.findViewById(0x7f0a0267);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a0252);
        linearlayout1 = (LinearLayout)view.findViewById(0x7f0a0248);
        int k;
        int l;
        TextView textview;
        Object obj;
        int j1;
        int k1;
        int i2;
        android.widget.LinearLayout.LayoutParams layoutparams;
        int j2;
        if (flag)
        {
            j = 0x7f0202ad;
            k = 0x7f0202ae;
            l = 0x7f0202af;
            imageview = (ImageView)linearlayout.findViewById(0x7f0a0253);
            imageview1 = (ImageView)linearlayout.findViewById(0x7f0a0254);
            textview = (TextView)relativelayout.findViewById(0x7f0a026b);
            textview1 = (TextView)relativelayout.findViewById(0x7f0a026a);
            obj = relativelayout.findViewById(0x7f0a0269);
        } else
        {
            j = 0x7f0202b0;
            k = 0x7f0202b1;
            l = 0x7f0202b2;
            imageview = (ImageView)linearlayout.findViewById(0x7f0a0255);
            imageview1 = (ImageView)linearlayout.findViewById(0x7f0a0256);
            textview = (TextView)relativelayout.findViewById(0x7f0a0268);
            obj = textview;
            textview1 = null;
        }
        s = "0";
        i1 = 0;
_L4:
        j1 = arraylist.size();
        k1 = i1;
        l1 = 0;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        if (k1 >= j1) goto _L2; else goto _L1
_L1:
        price = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Price)arraylist.get(i1);
        j2 = i1;
        l1 = 0;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        if (100 >= j2) goto _L3; else goto _L2
_L2:
        if (i != 0)
        {
            ImageView imageview2;
            int k2;
            int l2;
            String s1;
            boolean flag6;
            boolean flag7;
            if (flag)
            {
                imageview2 = (ImageView)linearlayout1.findViewById(0x7f0a0249);
            } else
            {
                imageview2 = (ImageView)linearlayout1.findViewById(0x7f0a024a);
            }
            if (flag3)
            {
                imageview2.setBackgroundResource(j);
                imageview2.setVisibility(0);
            } else
            {
                imageview2.setVisibility(4);
            }
        }
        if (flag5)
        {
            imageview.setBackgroundResource(l);
            imageview.setVisibility(0);
        } else
        if (flag2)
        {
            imageview.setBackgroundResource(j);
            imageview.setVisibility(0);
        } else
        {
            imageview.setVisibility(8);
        }
        if (flag1)
        {
            imageview1.setBackgroundResource(k);
            imageview1.setVisibility(0);
        } else
        if (flag2)
        {
            imageview1.setBackgroundResource(j);
            imageview1.setVisibility(0);
        } else
        {
            imageview1.setVisibility(8);
        }
        if (flag2)
        {
            linearlayout.setVisibility(0);
        }
        if (flag5 && !s.equals("0"))
        {
            textview.setText((new StringBuilder()).append(s).append(res.getString(0x7f0d0339)).toString());
            ((View) (obj)).setVisibility(0);
            if (flag4)
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0x7f0201ba, 0, 0, 0);
                if (flag)
                {
                    l1 += res.getDimensionPixelSize(0x7f0b0038);
                }
            }
            if (l1 != 0)
            {
                i2 = l1 + res.getDimensionPixelSize(0x7f0b0037);
                layoutparams = (android.widget.LinearLayout.LayoutParams)relativelayout.getLayoutParams();
                if (i2 > layoutparams.width)
                {
                    layoutparams.width = i2;
                    relativelayout.setLayoutParams(layoutparams);
                }
            }
        }
        return;
_L3:
label0:
        {
            k2 = price.edgeFrom;
            l2 = price.edgeTo;
            if (i >= k2 && l2 >= i)
            {
                break label0;
            }
            i1++;
        }
          goto _L4
        flag2 = true;
        l1 = 0;
        if (flag)
        {
            s1 = res.getString(0x7f0d027c);
            flag6 = TextUtils.isEmpty(price.type);
            l1 = 0;
            if (!flag6)
            {
                if (price.type.equals("Green"))
                {
                    s1 = res.getString(0x7f0d027b);
                    if (price.previousTaxFare)
                    {
                        l1 = 0 + res.getDimensionPixelSize(0x7f0b0039) / 2;
                    } else
                    {
                        l1 = 0 + res.getDimensionPixelSize(0x7f0b0039);
                    }
                } else
                if (price.type.equals("Reserved"))
                {
                    s1 = res.getString(0x7f0d027d);
                    l1 = 0;
                } else
                {
                    flag7 = price.type.equals("Free");
                    l1 = 0;
                    if (flag7)
                    {
                        s1 = res.getString(0x7f0d027a);
                        l1 = 0;
                    }
                }
            }
            textview1.setText(s1);
        }
        s = price.value;
        flag4 = price.previousTaxFare;
        flag3 = false;
        if (k2 < i)
        {
            flag3 = false;
            if (i <= l2)
            {
                flag3 = true;
            }
        }
        flag5 = false;
        if (k2 == i)
        {
            flag5 = true;
        }
        flag1 = false;
        if (l2 == i)
        {
            flag1 = true;
        }
          goto _L2
    }

    private void setRepeatImage(ImageView imageview, LightingColorFilter lightingcolorfilter, boolean flag)
    {
        Drawable drawable = imageview.getBackground();
        if (drawable != null)
        {
            if (lightingcolorfilter != null)
            {
                drawable.setColorFilter(lightingcolorfilter);
            }
            if (flag && (drawable instanceof BitmapDrawable))
            {
                BitmapDrawable bitmapdrawable = (BitmapDrawable)drawable;
                bitmapdrawable.mutate();
                bitmapdrawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT);
            }
        }
    }

    private void setStopStations(View view, ArrayList arraylist, Button button, int i)
    {
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a0265);
        if (arraylist != null)
        {
            LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
            int j = 1;
            do
            {
label0:
                {
label1:
                    {
                        if (j < arraylist.size())
                        {
                            if (arraylist.get(j) == null)
                            {
                                break label0;
                            }
                            if (arraylist.size() != j + 1)
                            {
                                break label1;
                            }
                        }
                        int k = -1 + arraylist.size();
                        RelativeLayout relativelayout;
                        TextView textview;
                        TextView textview1;
                        if (k > 1)
                        {
                            button.setText((new StringBuilder()).append(Integer.toString(k)).append(res.getString(0x7f0d0304)).toString());
                            button.setVisibility(0);
                            button.setTag(linearlayout);
                            button.setOnClickListener(this);
                        } else
                        {
                            button.setVisibility(8);
                        }
                        linearlayout.setVisibility(8);
                        return;
                    }
                    relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f0300ac, null);
                    relativelayout.setTag((new StringBuilder()).append(String.valueOf(j)).append(" ").append(String.valueOf(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist.get(j)).departureUnixTimestamp)).toString());
                    textview = (TextView)relativelayout.findViewById(0x7f0a0328);
                    textview1 = (TextView)relativelayout.findViewById(0x7f0a0327);
                    textview.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist.get(j)).name);
                    setRepeatImage((ImageView)relativelayout.findViewById(0x7f0a032a), new LightingColorFilter(0xffffff, i), false);
                    textview1.setText(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist.get(j)).departureTime);
                    linearlayout.addView(relativelayout);
                }
                j++;
            } while (true);
        } else
        {
            button.setVisibility(8);
            linearlayout.setVisibility(8);
            return;
        }
    }

    private void startImakokoUpdate()
    {
        handler = new ImakokoHandler(this);
        Message message = new Message();
        message.what = 0;
        handler.sendMessageDelayed(message, 1000L);
    }

    public void clearImakokoAnimation()
    {
        if (imakokoIcon != null)
        {
            imakokoIcon.clearAnimation();
            imakokoIcon.setVisibility(8);
            imakokoIcon = null;
        }
        imakokoPosition = 0;
        imakokoSsPosition = 0;
    }

    public ImageView getImakokoIcon()
    {
        return imakokoIcon;
    }

    public boolean isAirPlane()
    {
        return isAirPlane;
    }

    public void onClick(View view)
    {
        int i;
        super.onClick(view);
        i = view.getId();
        if (i != 0x7f0a023c) goto _L2; else goto _L1
_L1:
        String as[] = (String[])(String[])view.getTag();
        String s1 = as[0];
        String s2 = as[1];
        String s3 = as[2];
        String s4 = as[3];
        String s5 = as[4];
        String s6 = s1.substring(8, 12);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(s1.substring(0, 4)), -1 + Integer.parseInt(s1.substring(4, 6)), Integer.parseInt(s1.substring(6, 8)));
        if (Integer.parseInt(s6) > 0 && Integer.parseInt(s6) < 300)
        {
            calendar.add(5, -1);
        }
        StringBuilder stringbuilder = (new StringBuilder()).append(Integer.toString(calendar.get(1)));
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(1 + calendar.get(2));
        StringBuilder stringbuilder1 = stringbuilder.append(String.format("%02d", aobj1));
        Object aobj2[] = new Object[1];
        aobj2[0] = Integer.valueOf(calendar.get(5));
        setTimeTable(s2, s3, s4, s5, stringbuilder1.append(String.format("%02d", aobj2)).toString());
_L4:
        return;
_L2:
        if (i == 0x7f0a0247)
        {
            ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d0413));
            Object aobj[] = (Object[])(Object[])view.getTag();
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)aobj[0];
            String s = (String)aobj[1];
            ArrayList arraylist = edge.RidingPosition;
            Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/RidingPositionActivity);
            intent.putExtra(res.getString(0x7f0d01fa), s);
            intent.putExtra(res.getString(0x7f0d01f7), arraylist);
            intent.putExtra(res.getString(0x7f0d01f8), 0);
            intent.putExtra(res.getString(0x7f0d01f9), 0);
            ((TransitBaseActivity)context).startActivityInCurrentMenu(intent);
            return;
        }
        if (i != 0x7f0a0259)
        {
            break; /* Loop/switch isn't completed */
        }
        ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d0402));
        LinearLayout linearlayout = (LinearLayout)view.getTag();
        Object obj;
        if (linearlayout.getVisibility() == 8)
        {
            linearlayout.setVisibility(0);
            view.setBackgroundResource(0x7f020262);
        } else
        {
            linearlayout.setVisibility(8);
            view.setBackgroundResource(0x7f020263);
        }
        obj = ((View)view.getParent().getParent().getParent().getParent().getParent()).getTag();
        if ((obj instanceof String) && Integer.parseInt((String)obj) == imakokoPosition)
        {
            pauseImakokoUpdate();
            if (setImakokoAnimation(false))
            {
                startImakokoUpdate();
                return;
            }
            if (callback != null)
            {
                callback.onChangedImakoko(false);
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i == 0x7f0a0261)
        {
            startBrowser((String)view.getTag());
            return;
        }
        if (i == 0x7f0a0262)
        {
            startBrowser((String)view.getTag());
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void pauseImakokoUpdate()
    {
        if (handler != null)
        {
            handler.removeMessages(0);
        }
    }

    public void setChildren(LinearLayout linearlayout, Context context, int i, jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata, HashMap hashmap)
    {
        layout = linearlayout;
        ArrayList arraylist = naviroutedata.edgePrice;
        ArrayList arraylist1 = naviroutedata.edgeExpPrice;
        recvEdges = naviroutedata.edges;
        res = context.getResources();
        staTypeLandmark = res.getInteger(0x7f0c0012);
        staTypeReturn = res.getInteger(0x7f0c000d);
        staTypeInsert = res.getInteger(0x7f0c000b);
        staTypeStation = res.getInteger(0x7f0c0016);
        trafficAirplane = res.getInteger(0x7f0c0017);
        trafficLandmark = res.getInteger(0x7f0c001a);
        trafficShip = res.getInteger(0x7f0c001d);
        trafficExpresswayBus = res.getInteger(0x7f0c0018);
        trafficIntercityBus = res.getInteger(0x7f0c0019);
        trafficRouteBus = res.getInteger(0x7f0c001c);
        trafficAirLine = res.getInteger(0x7f0c0017);
        trafficWalk = res.getInteger(0x7f0c001f);
        int j = 0;
        int k = res.getInteger(0x7f0c006a);
        int l = conditionData.type;
        boolean flag = false;
        if (k == l)
        {
            flag = true;
        }
        int i1 = recvEdges.size();
        int j1 = 0;
        do
        {
label0:
            {
                if (j1 < i1)
                {
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j);
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1;
                    RelativeLayout relativelayout;
                    ImageView imageview;
                    TextView textview;
                    TextView textview1;
                    View view;
                    LinearLayout linearlayout1;
                    LinearLayout linearlayout2;
                    Button button;
                    TextView textview2;
                    TextView textview3;
                    LinearLayout linearlayout3;
                    View view1;
                    jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData navipointdata;
                    String s;
                    String s1;
                    StringBuffer stringbuffer;
                    String s2;
                    String s3;
                    int k1;
                    int l1;
                    int i2;
                    int j2;
                    int k2;
                    int l2;
                    int i3;
                    int j3;
                    if (j > 0)
                    {
                        edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j - 1);
                    } else
                    {
                        edge1 = null;
                    }
                    relativelayout = (RelativeLayout)LayoutInflater.from(context).inflate(0x7f030076, null);
                    relativelayout.setTag(Integer.toString(j));
                    imageview = (ImageView)relativelayout.findViewById(0x7f0a024e);
                    textview = (TextView)relativelayout.findViewById(0x7f0a0243);
                    textview1 = (TextView)relativelayout.findViewById(0x7f0a025f);
                    view = relativelayout.findViewById(0x7f0a0244);
                    linearlayout1 = (LinearLayout)relativelayout.findViewById(0x7f0a023d);
                    linearlayout2 = (LinearLayout)relativelayout.findViewById(0x7f0a0240);
                    button = (Button)relativelayout.findViewById(0x7f0a0259);
                    textview2 = (TextView)relativelayout.findViewById(0x7f0a025e);
                    textview3 = (TextView)relativelayout.findViewById(0x7f0a0260);
                    linearlayout3 = (LinearLayout)relativelayout.findViewById(0x7f0a0251);
                    view1 = relativelayout.findViewById(0x7f0a0247);
                    navipointdata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget);
                    s = navipointdata.stationName;
                    textview.setText(s);
                    if (s.length() > 10)
                    {
                        textview.setTextSize(0, res.getDimension(0x7f0b0045));
                    }
                    s1 = edge.departureTrackNumber;
                    stringbuffer = new StringBuffer();
                    if (!TextUtils.isEmpty(s1))
                    {
                        stringbuffer.append(s1);
                        stringbuffer.append(context.getString(0x7f0d026c));
                    }
                    s2 = edge.arrivalTrackNumber;
                    if (!TextUtils.isEmpty(s2) && stringbuffer.length() > 0)
                    {
                        stringbuffer.append("/");
                        stringbuffer.append(s2);
                        stringbuffer.append(context.getString(0x7f0d025c));
                    }
                    if (stringbuffer.length() > 0)
                    {
                        textview1.setText(stringbuffer);
                        textview1.setVisibility(0);
                    }
                    s3 = navipointdata.stationCode;
                    k1 = navipointdata.type;
                    if (staTypeStation == (k1 & staTypeStation))
                    {
                        textview.setTextColor(res.getColor(0x7f090069));
                        if (s3 != null && 5 == s3.length())
                        {
                            StationData stationdata = new StationData();
                            stationdata.setId(s3);
                            stationdata.setName(navipointdata.stationName);
                            stationdata.setLon(navipointdata.lon);
                            stationdata.setLat(navipointdata.lat);
                            textview.setTag(stationdata);
                            textview.setOnClickListener(this);
                        }
                    }
                    if (j == 0 || -1 + recvEdges.size() == j)
                    {
                        view.setTag(navipointdata);
                        view.setOnClickListener(this);
                        view.setVisibility(0);
                    }
                    l1 = edge.traffic;
                    i2 = 0x7f0201f7;
                    j2 = 0x7f0201dd;
                    k2 = 0;
                    l2 = 0x7f020218;
                    if (trafficShip == (l1 & trafficShip))
                    {
                        i2 = 0x7f0201f3;
                        j2 = 0x7f0201d6;
                        l2 = 0x7f020217;
                    } else
                    if (trafficRouteBus == (l1 & trafficRouteBus) || trafficIntercityBus == (l1 & trafficIntercityBus))
                    {
                        i2 = 0x7f0201f5;
                        j2 = 0x7f020197;
                        k2 = 0;
                    } else
                    if (trafficExpresswayBus == (l1 & trafficExpresswayBus))
                    {
                        i2 = 0x7f0201f5;
                        j2 = 0x7f0201b7;
                        k2 = 0;
                    } else
                    if (trafficAirLine == (l1 & trafficAirLine))
                    {
                        i2 = 0x7f0201ef;
                        j2 = 0x7f020189;
                        l2 = 0x7f020216;
                        k2 = 0;
                    } else
                    if (trafficWalk == (l1 & trafficWalk))
                    {
                        j2 = 0x7f0201e1;
                        l2 = 0x7f020219;
                        k2 = context.getResources().getColor(0x7f090048);
                    } else
                    {
                        int i6 = trafficLandmark;
                        int j6 = l1 & trafficLandmark;
                        k2 = 0;
                        if (i6 == j6)
                        {
                            j2 = 0x7f0201e1;
                            l2 = 0x7f020219;
                            k2 = context.getResources().getColor(0x7f090049);
                        }
                    }
                    if (k2 == 0)
                    {
                        k2 = changeColorFormat(edge.color);
                    }
                    if (edge1 != null)
                    {
                        ImageView imageview3 = (ImageView)relativelayout.findViewById(0x7f0a024b);
                        int j5 = edge1.traffic;
                        int k5 = 0x7f020218;
                        ImageView imageview1;
                        ImageView imageview2;
                        View view2;
                        LightingColorFilter lightingcolorfilter;
                        int k4;
                        Calendar calendar1;
                        String s12;
                        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData anavipointdata[];
                        TextView textview8;
                        ArrayList arraylist2;
                        String s17;
                        LinearLayout linearlayout4;
                        String s18;
                        String as[];
                        String s19;
                        int l5;
                        LightingColorFilter lightingcolorfilter1;
                        if (trafficShip == (j5 & trafficShip))
                        {
                            l5 = changeColorFormat(edge1.color);
                            k5 = 0x7f020217;
                        } else
                        if (trafficAirLine == (j5 & trafficAirLine))
                        {
                            l5 = changeColorFormat(edge1.color);
                            k5 = 0x7f020216;
                        } else
                        if (trafficWalk == (j5 & trafficWalk))
                        {
                            l5 = context.getResources().getColor(0x7f090048);
                            k5 = 0x7f020219;
                        } else
                        if (trafficLandmark == (j5 & trafficLandmark))
                        {
                            l5 = context.getResources().getColor(0x7f090049);
                            k5 = 0x7f020219;
                        } else
                        {
                            l5 = changeColorFormat(edge1.color);
                        }
                        lightingcolorfilter1 = new LightingColorFilter(0xffffff, l5);
                        imageview3.setBackgroundResource(k5);
                        setRepeatImage(imageview3, lightingcolorfilter1, true);
                        imageview3.setVisibility(0);
                    }
                    i3 = navipointdata.type;
                    if (j == 0)
                    {
                        if (staTypeLandmark == (i3 & staTypeLandmark))
                        {
                            i2 = 0x7f0201a1;
                        } else
                        {
                            i2 = 0x7f0201a2;
                        }
                        imageview.setBackgroundResource(0);
                    } else
                    if (-1 + recvEdges.size() == j)
                    {
                        if (staTypeLandmark == (i3 & staTypeLandmark))
                        {
                            i2 = 0x7f02018f;
                        } else
                        {
                            i2 = 0x7f020190;
                        }
                        imageview.setBackgroundResource(0);
                    }
                    j3 = edge.fromState;
                    if (staTypeReturn == (j3 & staTypeReturn))
                    {
                        i2 = 0x7f0201d3;
                    } else
                    if (staTypeInsert == (j3 & staTypeInsert))
                    {
                        i2 = 0x7f0201ce;
                    }
                    imageview.setImageResource(i2);
                    if (!flag)
                    {
                        TextView textview9 = (TextView)linearlayout2.findViewById(0x7f0a0241);
                        TextView textview10 = (TextView)linearlayout1.findViewById(0x7f0a023e);
                        TextView textview11 = (TextView)linearlayout2.findViewById(0x7f0a0242);
                        TextView textview12 = (TextView)linearlayout1.findViewById(0x7f0a023f);
                        String s13;
                        String s14;
                        int l4;
                        boolean flag1;
                        if (j == 0)
                        {
                            textview11.setVisibility(8);
                        } else
                        if (-1 + recvEdges.size() == j)
                        {
                            textview12.setVisibility(8);
                        }
                        s13 = edge.departureDatetime;
                        s14 = edge.timeType;
                        l4 = edge.traffic;
                        flag1 = true;
                        if ("None".equals(s14) || "Interval".equals(s14) || "OutSide".equals(s14))
                        {
                            flag1 = false;
                        } else
                        if ("Average".equals(s14) && l4 != trafficWalk && l4 != trafficLandmark)
                        {
                            flag1 = false;
                        }
                        if (s13 != null && l4 != trafficWalk && l4 != trafficLandmark)
                        {
                            String s24 = s13.substring(8, 10);
                            String s25 = s13.substring(10);
                            if (!flag1)
                            {
                                if (j == 0 && textview11.getVisibility() != 0)
                                {
                                    textview9.setText((new StringBuilder()).append("(").append(s24).append(":").append(s25).append(")").toString());
                                } else
                                {
                                    textview9.setText((new StringBuilder()).append("(").append(s24).append(":").append(s25).toString());
                                    String s26 = textview11.getText().toString();
                                    textview11.setText((new StringBuilder()).append(s26).append(")").toString());
                                }
                                textview9.setTextAppearance(context, 0x7f0e00bd);
                            } else
                            {
                                textview9.setText((new StringBuilder()).append(s24).append(":").append(s25).append("").toString());
                            }
                            linearlayout2.setVisibility(0);
                        } else
                        if ((l4 == trafficWalk || l4 == trafficLandmark) && j1 == 0)
                        {
                            String s15 = s13.substring(8, 10);
                            String s16 = s13.substring(10);
                            textview9.setText((new StringBuilder()).append(s15).append(":").append(s16).append("").toString());
                            linearlayout2.setVisibility(0);
                        }
                        if (edge1 != null)
                        {
                            s19 = edge1.arrivalDatetime;
                            String s20 = edge1.timeType;
                            int i5 = edge1.traffic;
                            boolean flag2 = true;
                            if ("None".equals(s20) || "Interval".equals(s20) || "OutSide".equals(s20))
                            {
                                flag2 = false;
                            } else
                            if ("Average".equals(s20) && i5 != trafficWalk && i5 != trafficLandmark)
                            {
                                flag2 = false;
                            }
                            if (s19 != null)
                            {
                                String s21 = s19.substring(8, 10);
                                String s22 = s19.substring(10);
                                if (!flag2)
                                {
                                    if (-1 + recvEdges.size() == j && textview12.getVisibility() != 0)
                                    {
                                        textview10.setText((new StringBuilder()).append("(").append(s21).append(":").append(s22).append(")").toString());
                                    } else
                                    {
                                        textview10.setText((new StringBuilder()).append("(").append(s21).append(":").append(s22).toString());
                                        String s23 = textview12.getText().toString();
                                        textview12.setText((new StringBuilder()).append(s23).append(")").toString());
                                    }
                                    textview10.setTextAppearance(context, 0x7f0e00bd);
                                } else
                                {
                                    textview10.setText((new StringBuilder()).append(s21).append(":").append(s22).toString());
                                }
                                linearlayout1.setVisibility(0);
                            }
                        }
                        s17 = edge.trainId;
                        linearlayout4 = (LinearLayout)relativelayout.findViewById(0x7f0a023c);
                        if (s17 != null && s17 != "" && edge.traffic == context.getResources().getInteger(0x7f0c001e))
                        {
                            s18 = navipointdata.stationCode;
                            as = (new String[] {
                                edge.departureDatetime, textview9.getText().toString().replaceAll("\\(", "").replaceAll("\\)", ""), s18, s, s17
                            });
                            textview9.setTextColor(res.getColor(0x7f090069));
                            textview9.setTypeface(null, 1);
                            textview11.setTextColor(res.getColor(0x7f090069));
                            textview11.setTypeface(null, 1);
                            linearlayout4.setClickable(true);
                            linearlayout4.setTag(as);
                            linearlayout4.setOnClickListener(this);
                        }
                        setStopStations(linearlayout3, edge.StopStations, button, k2);
                    } else
                    {
                        int k3 = edge.time;
                        int l3 = k3;
                        int i4 = 0;
                        if (60 <= l3)
                        {
                            i4 = k3 / 60;
                            k3 %= 60;
                        }
                        StringBuffer stringbuffer1 = new StringBuffer();
                        int j4;
                        TextView textview4;
                        if (i4 > 0)
                        {
                            stringbuffer1.append(Integer.toString(i4)).append(res.getString(0x7f0d0324));
                            if (k3 > 0)
                            {
                                stringbuffer1.append("\n").append(Integer.toString(k3)).append(res.getString(0x7f0d0325));
                            }
                        } else
                        {
                            stringbuffer1.append(Integer.toString(k3)).append(res.getString(0x7f0d0325));
                        }
                        j4 = edge.passStCount;
                        if (j4 > 0)
                        {
                            stringbuffer1.append("\n(").append(Integer.toString(j4)).append(res.getString(0x7f0d0304)).append(")");
                        }
                        textview4 = (TextView)relativelayout.findViewById(0x7f0a0258);
                        textview4.setText(stringbuffer1.toString());
                        textview4.setVisibility(0);
                        button.setVisibility(8);
                    }
                    if (edge.departureDatetime != null)
                    {
                        imageview1 = (ImageView)relativelayout.findViewById(0x7f0a0250);
                        imageview2 = (ImageView)relativelayout.findViewById(0x7f0a024c);
                        view2 = relativelayout.findViewById(0x7f0a025d);
                        view2.setBackgroundResource(j2);
                        lightingcolorfilter = new LightingColorFilter(0xffffff, k2);
                        imageview1.setBackgroundResource(l2);
                        setRepeatImage(imageview1, lightingcolorfilter, true);
                        imageview1.setVisibility(0);
                        imageview2.setBackgroundResource(l2);
                        setRepeatImage(imageview2, lightingcolorfilter, true);
                        imageview2.setVisibility(0);
                        if (j != 0)
                        {
                            setRepeatImage(imageview, lightingcolorfilter, false);
                        }
                        if (arraylist != null)
                        {
                            setPriceLine(arraylist, j, relativelayout, false);
                        }
                        if (arraylist1 != null)
                        {
                            setPriceLine(arraylist1, j, relativelayout, true);
                        }
                        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge2 = null;
                        if (j > 0)
                        {
                            edge2 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j - 1);
                        }
                        String s4 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j)).startPointTarget)).stationName;
                        if (edge2 != null)
                        {
                            arraylist2 = edge2.RidingPosition;
                            if (arraylist2 != null)
                            {
                                if (arraylist2.size() <= 1)
                                {
                                    view1.setTag(((Object) (new Object[] {
                                        edge2, s4
                                    })));
                                    view1.setOnClickListener(this);
                                    view1.setVisibility(0);
                                }
                            } else
                            {
                                view1.setVisibility(8);
                            }
                        }
                        k4 = edge.attentionId;
                        if (k4 != 0)
                        {
                            textview8 = (TextView)linearlayout3.findViewById(0x7f0a0266);
                            textview8.setText((new StringBuilder()).append("\u203B").append(k4).toString());
                            textview8.setVisibility(0);
                        }
                        if (trafficLandmark == (l1 & trafficLandmark))
                        {
                            Calendar calendar;
                            if (j1 == 0)
                            {
                                calendar = getTimeInMillisFrom(edge.departureDatetime);
                            } else
                            {
                                calendar = getTimeInMillisFrom(edge1.arrivalDatetime);
                            }
                            calendar1 = getTimeInMillisFrom(edge.arrivalDatetime);
                            s12 = returnTime(calendar, calendar1);
                            view2.setVisibility(8);
                            if (edge.railDispName == null || edge.railDispName.equals("") || edge.railDispName.length() == 0)
                            {
                                textview2.setVisibility(8);
                                relativelayout.findViewById(0x7f0a025b).setVisibility(8);
                            } else
                            {
                                textview2.setText(edge.railDispName);
                            }
                            if (hashmap.get(edge.goalPointTarget) != null)
                            {
                                anavipointdata = new jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData[2];
                                anavipointdata[0] = navipointdata;
                                anavipointdata[1] = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.goalPointTarget);
                                textview3.setTag(anavipointdata);
                                textview3.setOnClickListener(this);
                                textview3.setText((new StringBuilder()).append(res.getString(0x7f0d02df)).append("(").append(s12).append(")").toString());
                                ((View)textview3.getParent()).setVisibility(0);
                            }
                        } else
                        {
                            String s5 = edge.railDispName;
                            String s6;
                            View view3;
                            String s8;
                            String s9;
                            String s10;
                            if (!TextUtils.isEmpty(s5))
                            {
                                s6 = s5;
                            } else
                            {
                                s6 = edge.railname;
                            }
                            textview2.setText(s6);
                            if (edge1 != null)
                            {
                                if (l1 == res.getInteger(0x7f0c001f))
                                {
                                    String s11 = returnTime(getTimeInMillisFrom(edge1.arrivalDatetime), getTimeInMillisFrom(edge.arrivalDatetime));
                                    textview2.setText((new StringBuilder()).append(res.getString(0x7f0d0330)).append("(").append(s11).append(")").toString());
                                }
                            } else
                            if (j1 == 0 && l1 == res.getInteger(0x7f0c001f))
                            {
                                String s7 = returnTime(getTimeInMillisFrom(edge.departureDatetime), getTimeInMillisFrom(edge.arrivalDatetime));
                                textview2.setText((new StringBuilder()).append(res.getString(0x7f0d0330)).append("(").append(s7).append(")").toString());
                            }
                            view3 = relativelayout.findViewById(0x7f0a025c);
                            if (s5.indexOf("\u65E5\u672C\u822A\u7A7A") >= 0)
                            {
                                view3.setVisibility(0);
                                view3.setBackgroundResource(0x7f020223);
                                textview2.setTextSize(14F);
                            } else
                            if (s5.indexOf("\u5168\u65E5\u672C\u7A7A\u8F38") >= 0)
                            {
                                view3.setVisibility(0);
                                view3.setBackgroundResource(0x7f020222);
                                textview2.setTextSize(14F);
                            }
                            s8 = edge.AirportTicketLinkSP;
                            if (s8 != null && s8.length() > 0)
                            {
                                TextView textview7 = (TextView)relativelayout.findViewById(0x7f0a0261);
                                textview7.setVisibility(0);
                                relativelayout.findViewById(0x7f0a0263).setVisibility(0);
                                textview7.setTag(s8);
                                textview7.setOnClickListener(this);
                                relativelayout.setBackgroundColor(res.getColor(0x7f090003));
                            }
                            s9 = edge.AirportDPLinkSP;
                            if (s9 != null && s9.length() > 0)
                            {
                                TextView textview6 = (TextView)relativelayout.findViewById(0x7f0a0262);
                                textview6.setVisibility(0);
                                relativelayout.findViewById(0x7f0a0263).setVisibility(0);
                                textview6.setTag(s9);
                                textview6.setOnClickListener(this);
                                relativelayout.setBackgroundColor(res.getColor(0x7f090003));
                            }
                            s10 = edge.AirportDPLinkSPDisp;
                            if (s10 != null && s10.endsWith("false"))
                            {
                                TextView textview5 = (TextView)relativelayout.findViewById(0x7f0a0262);
                                textview5.setBackgroundResource(0x7f0200c3);
                                textview5.setVisibility(0);
                                textview5.setClickable(false);
                                relativelayout.setBackgroundColor(res.getColor(0x7f090003));
                            }
                        }
                        if (trafficAirplane == (l1 & trafficAirplane))
                        {
                            isAirPlane = true;
                        }
                        if (j == 0)
                        {
                            ((LinearLayout)relativelayout.findViewById(0x7f0a0248)).setVisibility(8);
                        }
                    } else
                    {
                        linearlayout3.setVisibility(8);
                        ((LinearLayout)relativelayout.findViewById(0x7f0a0248)).setVisibility(8);
                    }
                    linearlayout.addView(relativelayout, j);
                    j++;
                    if (500 >= j)
                    {
                        break label0;
                    }
                }
                return;
            }
            j1++;
        } while (true);
    }

    public boolean setImakokoAnimation(boolean flag)
    {
        Calendar calendar;
        int i;
        int j;
        if (res.getInteger(0x7f0c006a) == conditionData.type)
        {
            return false;
        }
        if (recvEdges.size() < 1)
        {
            return false;
        }
        calendar = Calendar.getInstance();
        i = recvEdges.size();
        if (i > 1)
        {
            String s6 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(i - 2)).arrivalDatetime;
            if (!TextUtils.isEmpty(s6) && isAfterTime(calendar, getTimeInMillisFrom(s6), false))
            {
                clearImakokoAnimation();
                if (callback != null)
                {
                    callback.onChangedImakoko(false);
                }
                return false;
            }
        }
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(0);
        String s = edge.departureDatetime;
        if (!TextUtils.isEmpty(s))
        {
            Calendar calendar6 = getTimeInMillisFrom(s);
            if (isBeforeTime(calendar, calendar6, false))
            {
                return true;
            }
            if (isEqualTime(calendar, calendar6))
            {
                View view6 = layout.findViewWithTag(Integer.toString(0));
                setImakokoIcon((ImageView)view6.findViewById(0x7f0a024f), 0, 0);
                if (flag)
                {
                    ArrayList arraylist3 = edge.StopStations;
                    View view7 = view6.findViewById(0x7f0a0265);
                    if (view7 != null && arraylist3 != null && arraylist3.size() > 1)
                    {
                        view7.setVisibility(0);
                        ((Button)view6.findViewById(0x7f0a0259)).setBackgroundResource(0x7f020263);
                    }
                }
                return true;
            }
        }
        j = imakokoPosition;
_L7:
        if (j >= i || j > 500) goto _L2; else goto _L1
_L1:
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1;
        Calendar calendar1;
        View view4;
        ArrayList arraylist2;
        View view5;
        int k;
        int l;
        edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j);
        String s1 = edge1.departureDatetime;
        String s2 = edge1.arrivalDatetime;
        Calendar calendar2;
        boolean flag1;
        if (TextUtils.isEmpty(s1))
        {
            calendar1 = null;
        } else
        {
            calendar1 = getTimeInMillisFrom(s1);
        }
        if (TextUtils.isEmpty(s2))
        {
            calendar2 = null;
        } else
        {
            calendar2 = getTimeInMillisFrom(s2);
        }
        if (!isAfterTime(calendar, calendar1, false) || !isBeforeTime(calendar, calendar2, false))
        {
            break MISSING_BLOCK_LABEL_1020;
        }
        view4 = layout.findViewWithTag(Integer.toString(j));
        arraylist2 = edge1.StopStations;
        view5 = view4.findViewById(0x7f0a0265);
        flag1 = false;
        if (view5 != null)
        {
            flag1 = false;
            if (arraylist2 != null)
            {
                int j2 = arraylist2.size();
                flag1 = false;
                int i1;
                if (j2 > 1)
                {
                    if (flag)
                    {
                        view5.setVisibility(0);
                        ((Button)view4.findViewById(0x7f0a0259)).setBackgroundResource(0x7f020263);
                        flag1 = true;
                    } else
                    {
                        int k2 = view5.getVisibility();
                        flag1 = false;
                        if (k2 == 0)
                        {
                            flag1 = true;
                        }
                    }
                }
            }
        }
        if (!flag1) goto _L4; else goto _L3
_L3:
        k = arraylist2.size();
        l = imakokoSsPosition;
_L5:
        if (l < k)
        {
            i1 = k - 1;
            if (l != i1)
            {
label0:
                {
                    String s4 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l)).departureTime;
                    int j1 = k - 2;
                    int k1;
                    String s5;
                    long l1;
                    int i2;
                    long l2;
                    long l3;
                    Calendar calendar4;
                    Calendar calendar5;
                    if (l == j1)
                    {
                        ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).arraivalTime;
                    } else
                    {
                        ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).departureTime;
                    }
                    k1 = k - 2;
                    if (l == k1)
                    {
                        s5 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).arraivalTime;
                    } else
                    {
                        s5 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).departureTime;
                    }
                    if (TextUtils.isEmpty(s4) || TextUtils.isEmpty(s5))
                    {
                        break label0;
                    }
                    l1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l)).departureUnixTimestamp.longValue();
                    i2 = k - 2;
                    if (l == i2)
                    {
                        l2 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).arrivalUnixTimestamp.longValue();
                    } else
                    {
                        l2 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.StopStation)arraylist2.get(l + 1)).departureUnixTimestamp.longValue();
                    }
                    l3 = TimeZone.getTimeZone("Asia/Tokyo").getRawOffset() - TimeZone.getDefault().getRawOffset();
                    calendar4 = Calendar.getInstance();
                    calendar5 = Calendar.getInstance();
                    calendar4.setTimeInMillis(l3 + 1000L * l1);
                    calendar5.setTimeInMillis(l3 + 1000L * l2);
                    if (l == 0)
                    {
                        if (!isAfterTime(calendar, calendar4, false) || !isBeforeTime(calendar, calendar5, false))
                        {
                            break label0;
                        }
                        setImakokoIcon((ImageView)view4.findViewById(0x7f0a0264), j, l);
                    } else
                    if (isEqualTime(calendar, calendar4))
                    {
                        setImakokoIcon((ImageView)view5.findViewWithTag((new StringBuilder()).append(String.valueOf(l)).append(" ").append(String.valueOf(l1)).toString()).findViewById(0x7f0a032b), j, l);
                    } else
                    {
                        if (!isAfterTime(calendar, calendar4, false) || !isBeforeTime(calendar, calendar5, false))
                        {
                            break label0;
                        }
                        setImakokoIcon((ImageView)view5.findViewWithTag((new StringBuilder()).append(String.valueOf(l)).append(" ").append(String.valueOf(l1)).toString()).findViewById(0x7f0a032c), j, l);
                    }
                }
            }
        }
_L2:
        return true;
        l++;
          goto _L5
_L4:
        setImakokoIcon((ImageView)view4.findViewById(0x7f0a0264), j, -1);
          goto _L2
        if (j == 0 && isEqualTime(calendar, calendar1))
        {
            View view2 = layout.findViewWithTag(Integer.toString(j));
            setImakokoIcon((ImageView)view2.findViewById(0x7f0a024f), j, 0);
            if (flag)
            {
                ArrayList arraylist1 = edge1.StopStations;
                View view3 = view2.findViewById(0x7f0a0265);
                if (view3 != null && arraylist1 != null && arraylist1.size() > 1)
                {
                    view3.setVisibility(0);
                    ((Button)view2.findViewById(0x7f0a0259)).setBackgroundResource(0x7f020263);
                }
            }
        } else
        {
label1:
            {
                if (j <= 0)
                {
                    break label1;
                }
                String s3 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)recvEdges.get(j - 1)).arrivalDatetime;
                Calendar calendar3;
                View view;
                if (TextUtils.isEmpty(s3))
                {
                    calendar3 = null;
                } else
                {
                    calendar3 = getTimeInMillisFrom(s3);
                }
                if ((j != i - 1 || !isEqualTime(calendar, calendar3)) && (!isAfterTime(calendar, calendar3, true) || !isBeforeTime(calendar, calendar1, true)))
                {
                    break label1;
                }
                view = layout.findViewWithTag(Integer.toString(j));
                setImakokoIcon((ImageView)view.findViewById(0x7f0a024f), j, 0);
                if (flag)
                {
                    ArrayList arraylist = edge1.StopStations;
                    View view1 = view.findViewById(0x7f0a0265);
                    if (view1 != null && arraylist != null && arraylist.size() > 1)
                    {
                        view1.setVisibility(0);
                        ((Button)view.findViewById(0x7f0a0259)).setBackgroundResource(0x7f020263);
                    }
                }
            }
        }
          goto _L2
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void setImakokoUpdate(boolean flag)
    {
        if (firstTime)
        {
            if (setImakokoAnimation(flag))
            {
                startImakokoUpdate();
                if (flag && imakokoIcon != null && callback != null)
                {
                    imakokoIcon.postDelayed(new Runnable() {

                        final SearchResultView this$0;

                        public void run()
                        {
                            View view2;
                            int k;
                            View view = layout.findViewWithTag(Integer.toString(imakokoPosition));
                            int i = view.getTop() + ((View)view.getParent()).getTop() + imakokoIcon.getTop();
                            View view1 = (View)imakokoIcon.getParent();
                            int j = i + view1.getTop();
                            view2 = (View)view1.getParent();
                            k = j + view2.getTop();
                            imakokoIcon.getId();
                            JVM INSTR tableswitch 2131362603 2131362604: default 120
                        //                                       2131362603 150
                        //                                       2131362604 205;
                               goto _L1 _L2 _L3
_L1:
                            int l = k + imakokoIcon.getHeight();
                            callback.scrollToImakoko(l);
                            return;
_L2:
                            View view4 = (View)view2.getParent();
                            int i1 = k + view4.getTop();
                            View view5 = (View)view4.getParent();
                            k = i1 + view5.getTop() + ((View)view5.getParent()).getTop();
                            continue; /* Loop/switch isn't completed */
_L3:
                            View view3 = (View)view2.getParent();
                            k = k + view3.getTop() + ((View)view3.getParent()).getTop();
                            if (true) goto _L1; else goto _L4
_L4:
                        }

            
            {
                this$0 = SearchResultView.this;
                super();
            }
                    }, 500L);
                }
            }
            firstTime = false;
        } else
        {
            if (handler != null)
            {
                handler.removeMessages(0);
            }
            clearImakokoAnimation();
            if (setImakokoAnimation(false))
            {
                startImakokoUpdate();
                return;
            }
            if (callback != null)
            {
                callback.onChangedImakoko(false);
                return;
            }
        }
    }

    public void setValues(ConditionData conditiondata)
    {
        conditionData = conditiondata;
    }





}
