// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            StringUtil

public class CampainBanner
{
    public static interface BannerListener
    {

        public abstract void onBannerClose();
    }

    private class ReadCampainSetting extends AsyncTask
    {

        String close;
        String desc;
        String dlgdesc;
        String dlgtitle;
        String img;
        String open;
        final CampainBanner this$0;

        private String doGetString(String s)
        {
            String s1;
            try
            {
                HttpGet httpget = new HttpGet(s);
                BasicHttpParams basichttpparams = new BasicHttpParams();
                basichttpparams.setIntParameter("http.connection.timeout", 10000);
                basichttpparams.setIntParameter("http.socket.timeout", 10000);
                DefaultHttpClient defaulthttpclient = new DefaultHttpClient(basichttpparams);
                httpget.setHeader("Connection", "Keep-Alive");
                HttpResponse httpresponse = defaulthttpclient.execute(httpget);
                if (httpresponse.getStatusLine().getStatusCode() != 200)
                {
                    throw new Exception("");
                }
                s1 = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
            }
            catch (Exception exception)
            {
                return null;
            }
            return s1;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient String[] doInBackground(Void avoid[])
        {
            String s;
            JSONObject jsonobject;
            String s1;
            String s2;
            String as[];
            try
            {
                s = doGetString("http://i.yimg.jp/dl/transit/conf/appBanner/android_banner.json");
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return null;
            }
            if (s == null)
            {
                return null;
            }
            if (StringUtil.isEmpty(s.trim()))
            {
                return null;
            }
            jsonobject = new JSONObject(s);
            s1 = jsonobject.optString("url");
            if (StringUtil.isEmpty(s1))
            {
                return null;
            }
            desc = jsonobject.optString("desc");
            open = jsonobject.optString("open");
            close = jsonobject.optString("close");
            img = jsonobject.optString("image_android");
            dlgtitle = jsonobject.optString("dlg_title");
            dlgdesc = jsonobject.optString("dlg_desc");
            pref = context.getSharedPreferences("campain_banner", 0);
            if (pref.getBoolean((new StringBuilder()).append("campain_banner_closed").append(open).toString(), false))
            {
                return null;
            }
            s2 = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
            if (!StringUtil.isEmpty(open) && s2.compareTo(open) < 0)
            {
                return null;
            }
            if (!StringUtil.isEmpty(close) && close.compareTo(s2) < 0)
            {
                return null;
            }
            as = new String[3];
            as[0] = s1;
            as[1] = desc;
            as[2] = img;
            return as;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String[])obj);
        }

        protected void onPostExecute(String as[])
        {
            String s1;
            String s2;
            super.onPostExecute(as);
            if (as == null || 3 != as.length)
            {
                break MISSING_BLOCK_LABEL_189;
            }
            String s = as[0];
            as[1];
            s1 = as[2];
            s2 = (new StringBuilder()).append(s).append("?apptype=").append(sAptype).append("&ver=").append(getVersionName()).toString();
            LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f03003f, null);
            ImageButton imagebutton = (ImageButton)linearlayout.findViewById(0x7f0a0197);
            imagebutton.setImageBitmap(BitmapFactory.decodeStream((new URL(s1)).openStream()));
            imagebutton.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            imagebutton.setOnClickListener(s2. new android.view.View.OnClickListener() {

                final ReadCampainSetting this$1;
                final String val$urlto;

                public void onClick(View view)
                {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(urlto));
                    context.startActivity(intent);
                }

            
            {
                this$1 = final_readcampainsetting;
                urlto = String.this;
                super();
            }
            });
            ((ImageButton)linearlayout.findViewById(0x7f0a0198)).setOnClickListener(new android.view.View.OnClickListener() {

                final ReadCampainSetting this$1;

                public void onClick(View view)
                {
                    if (dlgtitle != null && !dlgtitle.equals("") && dlgdesc != null && !dlgdesc.equals(""))
                    {
                        showDialog();
                        return;
                    } else
                    {
                        bannerClose(open);
                        return;
                    }
                }

            
            {
                this$1 = ReadCampainSetting.this;
                super();
            }
            });
            lyParent.addView(linearlayout);
            return;
            Exception exception;
            exception;
            exception.printStackTrace();
            return;
        }

        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        protected void showDialog()
        {
            (new TransitDialogBuilder(context)).setMessage(dlgdesc).setTitle(dlgtitle).setNegativeButton("\u30AD\u30E3\u30F3\u30BB\u30EB", new android.content.DialogInterface.OnClickListener() {

                final ReadCampainSetting this$1;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$1 = ReadCampainSetting.this;
                super();
            }
            }).setPositiveButton("\u30D0\u30CA\u30FC\u3092\u6D88\u3059", new android.content.DialogInterface.OnClickListener() {

                final ReadCampainSetting this$1;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    bannerClose(open);
                }

            
            {
                this$1 = ReadCampainSetting.this;
                super();
            }
            }).show();
        }

        private ReadCampainSetting()
        {
            this$0 = CampainBanner.this;
            super();
            desc = null;
            open = null;
            close = null;
            img = null;
            dlgtitle = null;
            dlgdesc = null;
        }

    }


    private static final String PREF_BUNNER = "campain_banner";
    private static final String PREF_BUNNER_KEY = "campain_banner_closed";
    private final String CAMPAIN_SET_URL = "http://i.yimg.jp/dl/transit/conf/appBanner/android_banner.json";
    private Context context;
    private BannerListener listener;
    private ViewGroup lyParent;
    private SharedPreferences pref;
    private String sAptype;

    public CampainBanner(Context context1)
    {
        pref = null;
        lyParent = null;
        sAptype = "";
        context = null;
        listener = null;
        context = context1;
    }

    public void bannerClose(String s)
    {
        lyParent.setVisibility(8);
        android.content.SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean((new StringBuilder()).append("campain_banner_closed").append(s).toString(), true);
        editor.commit();
        if (listener != null)
        {
            listener.onBannerClose();
        }
    }

    public String getVersionName()
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

    public void loadCampainBanner(ViewGroup viewgroup)
    {
        lyParent = viewgroup;
        (new ReadCampainSetting()).execute(new Void[0]);
    }

    public void setListener(BannerListener bannerlistener)
    {
        listener = bannerlistener;
    }



/*
    static SharedPreferences access$102(CampainBanner campainbanner, SharedPreferences sharedpreferences)
    {
        campainbanner.pref = sharedpreferences;
        return sharedpreferences;
    }

*/



}
