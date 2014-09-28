// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.ads:
//            k, h, AdContainer, e, 
//            a, f, AdResponse, AdViewForInstance, 
//            r

public class CreateAdLayoutDisplay
    implements k
{
    public class JavascriptInterface
    {

        final CreateAdLayoutDisplay a;

        public void getAdWebViewMessage(String s1)
        {
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("JavascriptInterface.getAdWebViewMessage(): returnValue=").append(s1).toString());
            if ("ok".equals(s1))
            {
                CreateAdLayoutDisplay.t(a);
            }
        }

        public JavascriptInterface()
        {
            a = CreateAdLayoutDisplay.this;
            super();
        }
    }

    private class a
        implements Runnable
    {

        final CreateAdLayoutDisplay a;
        private String b;
        private URL c;

        public void run()
        {
            CreateAdLayoutDisplay.r(a);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(b));
            intent.addFlags(0x10000000);
            try
            {
                CreateAdLayoutDisplay.c(a).startActivity(intent);
            }
            catch (Exception exception)
            {
                jp.co.yahoo.android.ads.h.a(6, (new StringBuilder()).append("Could not open browser on ad click to ").append(c).toString(), exception);
            }
            CreateAdLayoutDisplay.d(a);
            CreateAdLayoutDisplay.s(a);
        }

        public a(String s1)
        {
            a = CreateAdLayoutDisplay.this;
            super();
            b = s1;
        }
    }

    private class b extends WebViewClient
    {

        final CreateAdLayoutDisplay a;

        public void onPageFinished(WebView webview, String s1)
        {
            super.onPageFinished(webview, s1);
            CreateAdLayoutDisplay.l(a).loadUrl("javascript:android.getAdWebViewMessage(getMessage())");
            jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("ExpandWebViewClient: onPageFinished=").append(s1).toString());
        }

        private b()
        {
            a = CreateAdLayoutDisplay.this;
            super();
        }

    }


    private final Context a;
    private jp.co.yahoo.android.ads.a b;
    private JSONObject c;
    private ProgressBar d;
    private double e;
    private double f;
    private double g;
    private AbsoluteLayout h;
    private WebView i;
    private RelativeLayout j;
    private FrameLayout k;
    private AdContainer l;
    private WebView m;
    private AtomicBoolean n;
    private AtomicBoolean o;
    private AtomicBoolean p;
    private AtomicBoolean q;
    private WebView r;
    private WebView s;

    public CreateAdLayoutDisplay(Context context, jp.co.yahoo.android.ads.a a1, JSONObject jsonobject)
    {
        a = context;
        b = a1;
        c = jsonobject;
    }

    static WebView a(CreateAdLayoutDisplay createadlayoutdisplay, WebView webview)
    {
        createadlayoutdisplay.r = webview;
        return webview;
    }

    static AbsoluteLayout a(CreateAdLayoutDisplay createadlayoutdisplay, AbsoluteLayout absolutelayout)
    {
        createadlayoutdisplay.h = absolutelayout;
        return absolutelayout;
    }

    static RelativeLayout a(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.j;
    }

    private String a(String s1, String s2)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s1);
        stringbuffer.append((new StringBuilder()).append("&IVaction=").append(s2).toString());
        stringbuffer.append((new StringBuilder()).append("&r=").append(Math.random()).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Expand Action Beacon URL \uFF1A").append(stringbuffer.toString()).toString());
        return stringbuffer.toString();
    }

    static String a(CreateAdLayoutDisplay createadlayoutdisplay, String s1, String s2)
    {
        return createadlayoutdisplay.a(s1, s2);
    }

    private String a(JSONArray jsonarray)
        throws JSONException
    {
        String s1 = "";
        for (int i1 = 0; i1 < jsonarray.length(); i1++)
        {
            String s2 = Long.toString(jsonarray.getLong(i1));
            if (!"".equals(s2))
            {
                s1 = (new StringBuilder()).append(s1).append(s2).append(",").toString();
            }
        }

        if (!"".equals(s1))
        {
            s1 = s1.substring(0, -1 + s1.length());
        }
        return s1;
    }

    private HashMap a(String s1, Double double1)
    {
        String as[] = s1.split(",", 0);
        double d1 = (double)(Integer.parseInt(as[2]) - Integer.parseInt(as[0])) * double1.doubleValue();
        double d2 = (double)(Integer.parseInt(as[3]) - Integer.parseInt(as[1])) * double1.doubleValue();
        double d3 = (double)Integer.parseInt(as[0]) * double1.doubleValue();
        double d4 = (double)Integer.parseInt(as[1]) * double1.doubleValue();
        HashMap hashmap = new HashMap();
        hashmap.put("width", Integer.valueOf((int)d1));
        hashmap.put("height", Integer.valueOf((int)d2));
        hashmap.put("x", Integer.valueOf((int)d3));
        hashmap.put("y", Integer.valueOf((int)d4));
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Coordinates : width = ").append(hashmap.get("width")).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Coordinates : height = ").append(hashmap.get("height")).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Coordinates : x = ").append(hashmap.get("x")).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Coordinates : y = ").append(hashmap.get("y")).toString());
        return hashmap;
    }

    private void a(View view)
    {
        this;
        JVM INSTR monitorenter ;
        ViewGroup viewgroup;
        View view1;
        d = new ProgressBar(a);
        d.setIndeterminate(true);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(100, 100);
        layoutparams.addRule(14, 21);
        layoutparams.addRule(15, 21);
        d.setLayoutParams(layoutparams);
        d.setVisibility(0);
        d.setId(1);
        viewgroup = (ViewGroup)((ViewGroup)view.getRootView()).getChildAt(0);
        view1 = viewgroup.findViewById(20);
        if (view1 == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        viewgroup.removeViewInLayout(view1);
        Rect rect = new Rect();
        ((Activity)a).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i1 = rect.height();
        e = 80D * l.getAdRatio();
        f = 376D * l.getAdRatio();
        g = (double)i1 - f - e;
        RelativeLayout relativelayout = new RelativeLayout(a);
        android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, (int)e);
        layoutparams1.addRule(12, 21);
        relativelayout.setLayoutParams(layoutparams1);
        relativelayout.setBackgroundColor(0xff333333);
        relativelayout.setId(22);
        ImageView imageview = new ImageView(a);
        imageview.setImageBitmap(jp.co.yahoo.android.ads.h.b("iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAIAAAABc2X6AAAAA3NCSVQFBgUzC42AAAASYklEQVR4nO1cf2xTR57/xh3Tceu077VxZfeIirdkr+aKVEeQu/gEVVP1VgRBj2S3aJOjVTFFKomOlqBTr1io5QyLqrSVejErpftAuyisxAnQFdXRUtVI5ZSslq6NDhavDrSvElH9pEbyu8bqmw0j3/0xz/Pm/bDjhNDeaff7x2gynjdvvvOZ74/5fuelJf5UHBAAhT+R0ocRBoA/nbLlzwjfQcnGtbc4SzDf7ezv7uMe588IfxcI0yZwEPFsXHfvF/f47n30/wBhkcSW/6MI22VPxMGNm3cJQln/WbcWuHPZvssI24kAwYCdrfXo7swKYYQJkGZLais5Dp51x16NdcSiq6JySI6uioozKM+WtVnt+tXrmqaVy2X3LJ3jA+E422bVHBfLhHAd6ox3xrvjiXgi9lTM3LcNSZ1Rpy9N5wv53KUckLuCeUv3um620xYo+SpCbb0BrLp9XDkk923r69/SH14ZbnYD24lUSHYym53M5gv5xjxY+LP5LMTLHSNsJzkkJ19O9m3rw7hpWW1Ihc8LmWOZfCHvLf93BWE3tp4rjXFyZzL5ctJUv15TV1VV/UJVVVXUBVKbFG2PdsY7o6ujkiR5Pjv9H9Oj742qqtpItpvDeUkIu6hzfeeBgweiK6OOdk3TlJNK/lL+7OTZBqLOKboq2r2he/++/fGn4o6fCCHKCSUznrlDnBsi3ABb4U1Du4cGXxl06KTC54WJ0xOZf80Q4lY+TXC+MtqzqWfsvTEctA07/evp1JupcrnsyafTjiwDwg7CkHk/0/033VYLheLNYuaDTOanmSXw6SQEfdv6lHFFkiTGAwDos3pyT1K9odZ7ZIkIs9mbdg+s9eOcy7I89sFY7MmYyO3hnxxOH0oT6kTVXG9s83u4FWXPAgDbDo5NgTEeenVo9J1Rkx8AQsjwnuF8Ie/J4TIh7GAgiCdOTERXRwGAUIIBF28W00fSp06ecqw3BoyDGBDIWAYMEpYAmzwHAgHjtsEYwIB1ogOFcqUMBFhd5Dy6Kvrx5MfR1VHTxwTYtWdX/nJ+mRCurZC59nbOMcbKh4qJLQVAkJ3MDr4wqFd08cWMN1mSMcaSJAX8AYwwIAtba34AhBKgZmkYBiFE13WDGLqum+0AGOGJUxO923rZs6RCBncO2va2A+ea7yJyd0/okRD4gFJqK5FZtyFcNWc2enS0q7uL1RGgU2dO9f99P5mvjY4wug+1SW2tD7VGw9GgFIzIkUAgEMRBiihegcEHyFezXbUxMcIUaBAFwQeBQMAf8MsPygij1vtaSZWAD+g8pVV6+t9Oy7Lcub4T+RBagZ7e8PSZ7BlKqDgaVME2fzt390RXRilQ7MOO0sltjV76h5cGdwyaC+pDZ//97PYfbre2OsbSA1IkHJHb5Ego4r/f3xpoBZ85A+zDpEosbhlRQAgxueDCj3wIAWoNtMIKCEmhFl8L9mE6T5EPZSezkUikK94FPggGg51/1fnR+Y8shKtOnCmlbDUZX3UQ9uKWAOno6Dh69ChC5oyzk9nntz5v7eEHpFBbKPRoKPhAsLW1FarAkEGACCXIh2iVgg9olaIqYpwTSihQOk+hCpX5CqoiMm+2UB+llLbe29pyb4vcKlMfDaBAhVQo0Oz57MYNGyOPRZAPRR6N0P+h07+dRoBEhNn8xd3KSpcMe52HOCknFO4SaLNaIp5QZ1SmmaQ2SZbkSFsE/NZKGbeNgD9g4W+PV1k6AkDU1XAbwG89y/Vtea6szWolrQQEcBCrN1SpTWLPDvYNqrdqwizIsFtLuxAWV4XLGBAEqL+v/4XtL/DZvzb8Wu6zHADgFVh6SJLDciQUgXsAAIw/GrRaw41WgAKl1A/+OTrX4m+hVYp8qDJfQYDIPGGoQtV6ilYppRSq5lO0hYIPKNDW+1vRChSAgE70yjeVG3+4sf1H29n+D4VDF351gc2TzdncTa6da0OYrbEnthjj7GSW+7rKT5Vde3YxlRtpi0hhSZZls6sBAzsHOtd2xtfFAeDwvxw+89EZEVsTc7vmNwyD49y7qTd9KM0G0yt6/9Z+c3fUjHCpVFJnVCCQv5qPPWE6Art2261UHZx97B3WjkIgcss1e39fP+dW1/XUkRQAYDC5jcgRk1nDMMBIdCfi6+JszJF/Hund1KtXdEIIqZhWx7htGIYBt8GYMwzDKM+VCSXM9vY+18tdF0KJFJTEtQAAHMRSmxRpiwCC5MtJzmByt1kXranIHStdMS3R6gIwTx0jzDUzAIy+N6rNaIBAapOksBQJRRjUQCHgDwCF2OoY17oYcOpgqm9LHyGE+RKEElY3iGF6F7Wy57kexi1/ls3SuG2IMa2IHAmHw5IkFW8W+ay613V3xjv5nB26GmrRL59zDZBdiwAAQOeGznDYPMprmqZ8qACAJNXkFln7wvScBC+ClamDqd4tvTqp4UxAr+gMVQ9s7c8CQAAC1kkQMAEiy7LcJuu6zibDqHdTL6+796wLYUdkUDiF9W3p43XlhKJpGgBgjKOhKMZY3CNMPtNH0qb8C2X6YJrjzDjUK7qF7aYe76dqwsx1OysxxkQnQCE7mbUxXLPxTpxrzwpauqafbbaXkuB9wVQqxW3va/te077UAKDydSX4UFB+UDbXsgqoiub+OAdVKF4vlmZKXd1d1EfpPEU+SuYp9dGNGzbe+vJW/lreT/1z83N+8M+ROT/1927qHT2SZn3E/m+99daFTy8gH/Lf64cqIIRolaIVCACKxeKNP9wAgOLvi9t/uD30SAgA0Ap064tbV39/lVl45EOWTfbwtJhHQm2OFfKhru6urVu2sj8Lnxfefvtt/qv2pYYxDj0cYjaAUOJv8VOgyIeK14u3tFsb/3YjAFDuDwD0PNOjzWi/+c/fIEDMJfzB5h8cPXpU7MPq6bfSuU9z4IPWFa1wD/CTFqmSG7+7UfwvS3qlNqnnmR5W17/Wpy5NsZlzhE2+EIBNhpFNbnl7Yn2CN2Y/svaPuQRXCrlLObYtzV1dk7fs+Wz6UJoQAjUtxeqpg6nBbf1s/M1bNo8eGfXsk/0ky84YBhhcDkvl0tSlKVFXAUD2vDWrxPqE0+7YdZMQl7bbXi4tsZh14v344sfgIn1Wz13MxVbHIpGIZfcIwQhnz2cJJak3U8IKAgCkDqXZ0goWyFrl1MF07mIOEEhIAm63KRSvFkszJfdhu3CloM/qzOsKrwxHQpFyuWw7jQk22XUeruUHuHc5NT0lBU0LLIdkfVaHOoQRlsJSdGWUDcV1Uu9zvSMHRwIQMMBYsEwdSuU+yZknZwQylsukrGu6OlMnxAEAAJ/mPuW7etfOXflCXowBiJy7Mg+UcNsLFCKhCOdW1/UG3DJ8tBlNm9HYAViWZDbOmckzBMiBNw8EUMCgRoMyddDkFiOsV3QgUJwtNhMVU2+q8IxZj0ajU5enRNsrIows6XXJMIuh2gZtjgghmqYx6wUIMMYTv5wAAgcOHQAAgxqe5cibI9lzWQI1SV4Mqao1NyksOaJ5oiTXkeGaRy0HZT7Q0uKPQIFUCAAoJxWDlNNHRj0jL/v37T977uxSxnfNTQ7KYmQLwIawTUuLK8F6iwkEMzh6ByRqJrDpUsIdhqWRrluyhjHm83f6W55aWtwPXIBhyQjXaGDHgDKu1FAlYPntBBCeOD4xjIeVk8rCA3kREwrOMMfWqasddpgjzLWXGJe7k3TRwI8HlHGl9hYC1vpa9bFjo8kdA0sbP4CtMINe0Tm2bu5svrTDXyVAbLIRkmFJNLBjQDmugLXeuF59bFxJ7kjWHag+hduENCUBm8ZifrXHacl+TmK9S3qJjyNu78VxO+6xUZO7k8O7h93tY+NjS+A5ELAQLukl0Y/g7U3JcPlWmSMfezLmGZRvQAM7BiaOTzjiZBjhwZ2DZsgewdixMcevY+NjgEA5sQh5ZikBRuWvyuLNAPYWLxm2Y8vqOtFNc8rGXeXMDzbDrU1nIrC4BVBOKMO7h919xo4tDudo1JqYelN1ICz61XVu8Qi3Y67fuM7H6o4LebNmuAUiagSMcHJn0pGOUU4qw3uGxT6sPnZ8TIzgNCCMsJXQo6CqqgNhsWxohwETSgpXCnzoxIaE822NubVHSUVsbTwznF39x8ab4lkMdBSuFZhlESNEoh321tKi5z01PWUNvaUXmqCJX0yYYwqI1eNW5Fnsz+o/G//Zgq8TYZiannLsKYCm7TAAECDqDZWLcXRVdKAJUyn45zW5fbERt5znXYI8W7a6IWFsCzBOTU9xbDnCTdhhLsOACSW5yRwfsWdDz4IMs5hDk9g6eGa2yuYnNaShV4d4gFGdUQuFgikRdo3Q0A6DIMNAAGDi3AR/QfKVJD951qPClULhSoF5y4MvDp460RS3nOddO3dBTfZEJ9mT+l/o5/XsuSyfs9OXrulqIfPgZam5/lBOKCyTAADvvv/u/n37m+fh7tHQq0Njx8ZYnVCy+e82l74qcYStfvVOS54xLQAgQDLj1p2NkddHBn48sFw3sZZMGGOebQCA7LlsqVQCu35uSkubw4kWDPDU9FTxmhU6G/7H4eiqqOOKzbdJ4bbw0KtD1u0mCpkPM2797DgPgyMubfLMs/I1eWAR3avXr7JsHQC0r2zXyzqdp+XZMs/9f2sUXRXt+uuuzHiG59YVRbnw6QUzSwgUg5l5t/KJqN4NgFpcGvkQqRIMVu68MltplVvXrl3LOmx8euOFX10IPRz66uuvyDffEs8Y4/Xr1re3t+99fW/H4x2sUdO0N954g8wTkwtBhhFCUAUs3ADwyi0BAItdIkvjsXLsgzExepg5lgEEifUJnrO8qxQOhxMbEuzmKrcULAzMlDnXOJ76mZVeCCOAKiBAAMDvKQAA+IB8Q4rXipuf38z2EsZ4zV+uyV3MySG5/S/ay5XyXYJakqTEukT74+1BFOzq7tq7dy//6efKz0+fO23dS6npI5YbwgjTqplzqHPHw5VbwoDFWx/al1rlvysbn97IOkQejXzv8e99dvEzhFFHewd+AJOvyTJKtSRJ8afi3499HwUQAHSt7zpw4AD/dfrX02+k3gCw7qWY92ZqmTCTW+/cknh/p2qOaEqycK8HI1z4XQEHMEvGAkD7yvZnn332s08+q9BKSA5FHou0PdRWIZU7RJuzKj8o0yrFgLdu2br3dQvb4rViMplEVcRxcvrPVUs/N7rF40ZYzKmzPtOXpuWHZa7AgsHg5m2br/72qqZp2If99/s72jsij0XwfZjO0+ajfxjhtkfaOlZ3rFm7JvZ4zH+/n+lbjHAqlRKPLupN9aVXXqp8UxHti6if3Xe26tzicdydBctqi+1MK4zsG0m+Yju+Zc9nlQ8V8TSPESaEkDlS0ktEJ+VKma0jqRAcxBhhHMQBFDCTFSHZEf0ghMSfjKffSYtvKV4rJl9JslSOB7aM6tzxaHjXUswz2XHm5eCLgyOvjzi8rtSbqcK1AjR3J79eCRQi4Uj6UNrxHUHuk9z+f9pv2zV2nt35JLFc4L70wjgDia+Np99Ju2+HH/7J4fzlPCEEkOsmc+OSkNgTseSeZGy1zdoRSjLvZ5TjijsnaPOfG97TauI2rT1SbVuFGkmSNLxveOBHHkfl4s3i2V+ezRfyzFRKQck9D53qQAAQxJ6IiTZWpMK1QvpQuni1yPedDWHh/lo9bJtCWIxmWpwTe3utjK+Npw6mGjshhc8LAKCqqjqjSpK0ZtUa3IobfO0AAKRC3n3/3YnTEyIG4IjMNCG9i0DYfLEDZ95uX+/EhsTQniH3RwtLIH1Wnzg5oZxSWDrO/S5HTMrB8+IRdksyI7s+c+9wjHC0I9q/rb9nU48tJ9AcEUqmLk6dPX926uKUuLIOfhaL7RJvxNeTZ3HtRVmKxWKJ7kT8qfiajjUNvtvSdV39Qs1fzk8Xpq9fvm5GHsUxvaLqi8K2aYTryPOCOLtngBGOxqIYMGBY07Gm9FWprJUJkFKppM/qzaQ1PHSy4y136bulxnrbw9LYvyxw7AWPdgE3h+617O0isWXlIr8f9vxel3+rgpz5R+spEFoErVO3HTnr/F0e0Qyo816v0ju3VLdEzjOz5UXyr0HtkWTep9m3OL4qdegh97vs+27BcolfiNf9FSy0lzKyA3MHqu4xv4MvxDmSDrl1f73XhE5y9ucaCLnkfKlzXob/42HODHsg49EfuZ5FXjsCu/p7jrb4cln/BwBH2+t7EY/zEFgYmnUv3V4X/+8KYRvOC/X0yPo0P/5y/L+Wu/ZfHuzk7Yc1U1/uud21/7bkKZlLGGG55/a/bRuUl8vOOGUAAAAASUVORK5CYII="));
        imageview.setAdjustViewBounds(true);
        imageview.setId(23);
        int j1 = (int)(40D * l.getAdRatio());
        android.widget.RelativeLayout.LayoutParams layoutparams2 = new android.widget.RelativeLayout.LayoutParams(j1, j1);
        layoutparams2.addRule(0, 24);
        layoutparams2.addRule(15);
        TextView textview = new TextView(a);
        textview.setText("\u9589\u3058\u308B");
        textview.setId(24);
        textview.setTextSize((int)(14D * l.getAdRatio()));
        textview.setGravity(17);
        textview.setTextColor(-1);
        android.widget.RelativeLayout.LayoutParams layoutparams3 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams3.addRule(11);
        layoutparams3.addRule(15);
        layoutparams3.rightMargin = 10;
        relativelayout.addView(imageview, layoutparams2);
        relativelayout.addView(textview, layoutparams3);
        k = null;
        if (g > 0.0D)
        {
            k = new FrameLayout(a);
            k.setBackgroundColor(0xff333333);
            android.widget.RelativeLayout.LayoutParams layoutparams6 = new android.widget.RelativeLayout.LayoutParams(-1, (int)g);
            k.setLayoutParams(layoutparams6);
            k.setId(25);
            k.setVisibility(0);
        }
        j = new RelativeLayout(a);
        android.widget.RelativeLayout.LayoutParams layoutparams4 = new android.widget.RelativeLayout.LayoutParams(-1, -2);
        j.setLayoutParams(layoutparams4);
        j.setBackgroundColor(0xff333333);
        j.setId(20);
        j.setVisibility(0);
        j.addView(relativelayout);
        j.addView(d);
        WindowManager windowmanager = (WindowManager)a.getSystemService("window");
        android.view.WindowManager.LayoutParams layoutparams5 = new android.view.WindowManager.LayoutParams();
        layoutparams5.width = -1;
        layoutparams5.height = -1;
        layoutparams5.type = 2;
        windowmanager.addView(j, layoutparams5);
        relativelayout.setOnClickListener(new android.view.View.OnClickListener() {

            final CreateAdLayoutDisplay a;

            public void onClick(View view2)
            {
                CreateAdLayoutDisplay.a(a).removeAllViews();
                ((WindowManager)CreateAdLayoutDisplay.c(a).getSystemService("window")).removeView(CreateAdLayoutDisplay.a(a));
                CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "close"));
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void a(FrameLayout framelayout, String s1)
    {
        if (framelayout != null && s1 != null && !"".equals(s1))
        {
            framelayout.setOnClickListener(new android.view.View.OnClickListener(s1) {

                final String a;
                final CreateAdLayoutDisplay b;

                public void onClick(View view)
                {
                    (new Thread(b. new a(a))).start();
                }

            
            {
                b = CreateAdLayoutDisplay.this;
                a = s1;
                super();
            }
            });
        }
    }

    private void a(String s1)
    {
        (new Thread(s1) {

            final String a;
            final CreateAdLayoutDisplay b;

            public void run()
            {
                jp.co.yahoo.android.ads.f.a(CreateAdLayoutDisplay.c(b), a);
            }

            
            {
                b = CreateAdLayoutDisplay.this;
                a = s1;
                super();
            }
        }).start();
    }

    static void a(CreateAdLayoutDisplay createadlayoutdisplay, View view)
    {
        createadlayoutdisplay.a(view);
    }

    static void a(CreateAdLayoutDisplay createadlayoutdisplay, FrameLayout framelayout, String s1)
    {
        createadlayoutdisplay.a(framelayout, s1);
    }

    static void a(CreateAdLayoutDisplay createadlayoutdisplay, String s1)
    {
        createadlayoutdisplay.a(s1);
    }

    static WebView b(CreateAdLayoutDisplay createadlayoutdisplay, WebView webview)
    {
        createadlayoutdisplay.m = webview;
        return webview;
    }

    private FrameLayout b(String s1)
    {
        FrameLayout framelayout = null;
        if (s1 != null)
        {
            boolean flag = "".equals(s1);
            framelayout = null;
            if (!flag)
            {
                FrameLayout framelayout1 = new FrameLayout(a);
                HashMap hashmap = a(s1, Double.valueOf(l.getAdRatio()));
                framelayout1.setLayoutParams(new android.widget.AbsoluteLayout.LayoutParams(((Integer)hashmap.get("width")).intValue(), ((Integer)hashmap.get("height")).intValue(), ((Integer)hashmap.get("x")).intValue(), ((Integer)hashmap.get("y")).intValue()));
                framelayout = framelayout1;
            }
        }
        return framelayout;
    }

    static FrameLayout b(CreateAdLayoutDisplay createadlayoutdisplay, String s1)
    {
        return createadlayoutdisplay.b(s1);
    }

    static jp.co.yahoo.android.ads.a b(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.b;
    }

    private void b(View view)
    {
        this;
        JVM INSTR monitorenter ;
        if (!jp.co.yahoo.android.ads.e.g(a) || jp.co.yahoo.android.ads.h.d(b.p())) goto _L2; else goto _L1
_L1:
        g();
        f();
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        h();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    static void b(CreateAdLayoutDisplay createadlayoutdisplay, View view)
    {
        createadlayoutdisplay.b(view);
    }

    static Context c(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.a;
    }

    static WebView c(CreateAdLayoutDisplay createadlayoutdisplay, WebView webview)
    {
        createadlayoutdisplay.i = webview;
        return webview;
    }

    private String c(String s1)
    {
        jp.co.yahoo.android.ads.h.a(3, "createExpandBeacon START");
        String s2 = String.format("%.11s", new Object[] {
            jp.co.yahoo.android.ads.h.a((new StringBuilder()).append(UUID.randomUUID().toString()).append("ysmaudid").toString(), "SHA-1", "%011d")
        });
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("IVsid : ").append(s2).toString());
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("http://ybx.yahoo.co.jp/clear.gif?bkey=gex");
        stringbuffer.append((new StringBuilder()).append("&").append(jp.co.yahoo.android.ads.h.c(s1)).toString());
        stringbuffer.append("&IVvisibletime=0&IVactiontime=0");
        stringbuffer.append((new StringBuilder()).append("&IVsid=").append(s2).toString());
        jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Expand Beacon URL \uFF1A").append(stringbuffer.toString()).toString());
        return stringbuffer.toString();
    }

    private void c()
    {
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                try
                {
                    if (CreateAdLayoutDisplay.a(a) != null && !jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).g()))
                    {
                        CreateAdLayoutDisplay.a(a).removeAllViews();
                        ((WindowManager)CreateAdLayoutDisplay.c(a).getSystemService("window")).removeView(CreateAdLayoutDisplay.a(a));
                        CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "close"));
                    }
                    return;
                }
                catch (Exception exception)
                {
                    jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("Remove expandLayout: ").append(exception.getMessage()).toString());
                }
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
    }

    private WebView d()
    {
        n = new AtomicBoolean(false);
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                CreateAdLayoutDisplay.a(a, new WebView(CreateAdLayoutDisplay.c(a)));
                AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.f(a), AdViewForInstance.getNeedWebViewResumeTimers());
                WebSettings websettings = CreateAdLayoutDisplay.f(a).getSettings();
                websettings.setJavaScriptEnabled(true);
                websettings.setCacheMode(1);
                CreateAdLayoutDisplay.f(a).setFocusable(false);
                CreateAdLayoutDisplay.f(a).setScrollBarStyle(0);
                CreateAdLayoutDisplay.f(a).setBackgroundColor(Color.parseColor("#00000000"));
                android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)((double)CreateAdLayoutDisplay.b(a).e() * CreateAdLayoutDisplay.e(a).getAdRatio()));
                CreateAdLayoutDisplay.f(a).setLayoutParams(layoutparams);
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(CreateAdLayoutDisplay.b(a).d());
                stringbuilder.append((new StringBuilder()).append("?v").append((new Random()).nextInt(0xdbba0)).toString());
                String s1 = "<html><head><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\" /><script>function getMessage() {return document.getElementById('message').innerHTML;}</script></head><body style='margin:0;padding:0;'><img src='#AD_IMAGE_URL#' onError='document.getElementById(\"message\").innerHTML=\"error\"' width=\"100%\" height=\"100%\"/><div id='message' style='display:none;'>ok</div></body></html>".replace("#AD_IMAGE_URL#", stringbuilder.toString());
                CreateAdLayoutDisplay.f(a).loadDataWithBaseURL("about:blank", s1, "text/html", "UTF-8", null);
                CreateAdLayoutDisplay.f(a).setId(10);
                CreateAdLayoutDisplay.g(a).set(true);
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        while (!n.get()) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedexception)
            {
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("InterruptedException: ").append(interruptedexception.getMessage()).toString());
            }
        }
        return r;
    }

    static WebView d(CreateAdLayoutDisplay createadlayoutdisplay, WebView webview)
    {
        createadlayoutdisplay.s = webview;
        return webview;
    }

    static void d(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        createadlayoutdisplay.c();
    }

    private WebView e()
    {
        p = new AtomicBoolean(false);
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                CreateAdLayoutDisplay.b(a, new WebView(CreateAdLayoutDisplay.c(a)));
                AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.h(a), AdViewForInstance.getNeedWebViewResumeTimers());
                CreateAdLayoutDisplay.h(a).setBackgroundColor(Color.parseColor("#00000000"));
                WebSettings websettings = CreateAdLayoutDisplay.h(a).getSettings();
                websettings.setJavaScriptEnabled(true);
                websettings.setCacheMode(1);
                (new r()).a(websettings);
                CreateAdLayoutDisplay.h(a).setFocusable(false);
                CreateAdLayoutDisplay.h(a).setScrollBarStyle(0);
                android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)(50D * CreateAdLayoutDisplay.e(a).getAdRatio()));
                CreateAdLayoutDisplay.h(a).setLayoutParams(layoutparams);
                CreateAdLayoutDisplay.h(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
                CreateAdLayoutDisplay.h(a).loadUrl(CreateAdLayoutDisplay.b(a).o());
                CreateAdLayoutDisplay.h(a).setId(10);
                CreateAdLayoutDisplay.i(a).set(true);
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        while (!p.get()) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedexception)
            {
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("InterruptedException: ").append(interruptedexception.getMessage()).toString());
            }
        }
        return m;
    }

    static AdContainer e(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.l;
    }

    static WebView f(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.r;
    }

    private void f()
    {
        this;
        JVM INSTR monitorenter ;
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                if (CreateAdLayoutDisplay.j(a) != null)
                {
                    CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.j(a));
                    CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.j(a));
                }
                if (jp.co.yahoo.android.ads.e.g(CreateAdLayoutDisplay.c(a)) && !jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).p()))
                {
                    CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.k(a));
                    CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.k(a));
                } else
                {
                    CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.l(a));
                    CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.l(a));
                }
                if (CreateAdLayoutDisplay.m(a) != null)
                {
                    CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.m(a));
                    CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.m(a));
                }
                CreateAdLayoutDisplay.n(a).setVisibility(4);
                CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).j());
                CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "expand"));
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static AtomicBoolean g(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.n;
    }

    private void g()
    {
        o = new AtomicBoolean(false);
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                CreateAdLayoutDisplay.c(a, new WebView(CreateAdLayoutDisplay.c(a)));
                AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.k(a), AdViewForInstance.getNeedWebViewResumeTimers());
                WebSettings websettings = CreateAdLayoutDisplay.k(a).getSettings();
                websettings.setJavaScriptEnabled(true);
                websettings.setCacheMode(1);
                (new r()).a(websettings);
                CreateAdLayoutDisplay.k(a).setFocusable(false);
                CreateAdLayoutDisplay.k(a).setVerticalScrollbarOverlay(true);
                CreateAdLayoutDisplay.k(a).setVerticalScrollBarEnabled(false);
                CreateAdLayoutDisplay.k(a).setHorizontalScrollbarOverlay(true);
                CreateAdLayoutDisplay.k(a).setHorizontalScrollBarEnabled(false);
                android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)CreateAdLayoutDisplay.o(a));
                layoutparams.addRule(3, 25);
                CreateAdLayoutDisplay.k(a).setLayoutParams(layoutparams);
                CreateAdLayoutDisplay.k(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
                StringBuffer stringbuffer = new StringBuffer();
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[0]))
                {
                    stringbuffer.append((new StringBuilder()).append("clickTAG=").append(jp.co.yahoo.android.ads.h.b(CreateAdLayoutDisplay.b(a).h()[0], "utf-8")).append("&amp;targetTAG=_top").toString());
                }
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[1]))
                {
                    stringbuffer.append((new StringBuilder()).append("&amp;clickTAG2=").append(jp.co.yahoo.android.ads.h.b(CreateAdLayoutDisplay.b(a).h()[1], "utf-8")).append("&amp;targetTAG2=_top").toString());
                }
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[2]))
                {
                    stringbuffer.append((new StringBuilder()).append("&amp;clickTAG3=").append(jp.co.yahoo.android.ads.h.b(CreateAdLayoutDisplay.b(a).h()[2], "utf-8")).append("&amp;targetTAG3=_top").toString());
                }
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[3]))
                {
                    stringbuffer.append((new StringBuilder()).append("&amp;clickTAG4=").append(jp.co.yahoo.android.ads.h.b(CreateAdLayoutDisplay.b(a).h()[3], "utf-8")).append("&amp;targetTAG4=_top").toString());
                }
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[4]))
                {
                    stringbuffer.append((new StringBuilder()).append("&amp;clickTAG5=").append(jp.co.yahoo.android.ads.h.b(CreateAdLayoutDisplay.b(a).h()[4], "utf-8")).append("&amp;targetTAG5=_top").toString());
                }
                StringBuffer stringbuffer1 = new StringBuffer();
                stringbuffer1.append("<div id=\"yahoo_rma_expand_flash_wrapper\">");
                stringbuffer1.append("<object id=\"yahoo_rma_expand_flash\" classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" style=\"width:100%;height:100%;\">");
                if (stringbuffer.length() != 0)
                {
                    stringbuffer1.append((new StringBuilder()).append("<param name=\"FlashVars\" value=\"").append(stringbuffer.toString()).append("\">").toString());
                }
                stringbuffer1.append((new StringBuilder()).append("<param name=\"movie\" value=\"").append(CreateAdLayoutDisplay.b(a).p()).append("\">").toString());
                stringbuffer1.append("<param name=\"loop\" value=\"true\">");
                stringbuffer1.append("<param name=\"quality\" value=\"high\">");
                stringbuffer1.append("<param name=\"allowScriptAccess\" value=\"always\">");
                stringbuffer1.append("<param name=\"wmode\" value=\"transparent\">");
                stringbuffer1.append((new StringBuilder()).append("<embed id=\"yahoo_rma_expand_img\" style=\"width:100%;height:100%;\" src=\"").append(CreateAdLayoutDisplay.b(a).p()).append("\" loop=\"true\" quality=\"high\" type=\"application/x-shockwave-flash\" ").append("allowscriptaccess=\"always\" swliveconnect=\"false\" wmode=\"transparent\"").toString());
                String s1;
                if (stringbuffer.length() != 0)
                {
                    stringbuffer1.append((new StringBuilder()).append(" flashvars=\"").append(stringbuffer.toString()).append("\">").toString());
                } else
                {
                    stringbuffer1.append(">");
                }
                stringbuffer1.append("</object></div>");
                s1 = "<!DOCTYPE html><html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\"/><style type=\"text/css\">body {margin:0px;}</style><script language=\"JavaScript\">window.onload = function(){var swf = document.getElementById(\"yahoo_rma_expand_flash\"); if(swf){ swf.focus(); }};</script></head><body>#contents#</body></html>".replaceAll("#contents#", stringbuffer1.toString());
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("flash html : ").append(s1).toString());
                CreateAdLayoutDisplay.k(a).loadDataWithBaseURL(null, s1, "text/html", "utf-8", null);
                CreateAdLayoutDisplay.k(a).setId(21);
                CreateAdLayoutDisplay.p(a).set(true);
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        while (!o.get()) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedexception)
            {
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("InterruptedException: ").append(interruptedexception.getMessage()).toString());
            }
        }
    }

    static WebView h(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.m;
    }

    private void h()
    {
        q = new AtomicBoolean(false);
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                CreateAdLayoutDisplay.d(a, new WebView(CreateAdLayoutDisplay.c(a)));
                AdViewForInstance.callWebViewResumeTimers(CreateAdLayoutDisplay.l(a), AdViewForInstance.getNeedWebViewResumeTimers());
                WebSettings websettings = CreateAdLayoutDisplay.l(a).getSettings();
                websettings.setJavaScriptEnabled(true);
                websettings.setCacheMode(1);
                CreateAdLayoutDisplay.l(a).setFocusable(false);
                CreateAdLayoutDisplay.l(a).setBackgroundColor(Color.parseColor("#00000000"));
                CreateAdLayoutDisplay.l(a).setVerticalScrollbarOverlay(true);
                CreateAdLayoutDisplay.l(a).setVerticalScrollBarEnabled(false);
                CreateAdLayoutDisplay.l(a).setHorizontalScrollbarOverlay(true);
                CreateAdLayoutDisplay.l(a).setHorizontalScrollBarEnabled(false);
                android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)CreateAdLayoutDisplay.o(a));
                layoutparams.addRule(3, 25);
                CreateAdLayoutDisplay.l(a).setLayoutParams(layoutparams);
                CreateAdLayoutDisplay.l(a).setInitialScale((int)(100D * CreateAdLayoutDisplay.e(a).getAdRatio()));
                String s1 = "<html><head><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0\" /><script>function getMessage() {return document.getElementById('message').innerHTML;}</script></head><body style='margin:0;padding:0;'><img src='#AD_IMAGE_URL#' onError='document.getElementById(\"message\").innerHTML=\"error\"' width=\"100%\" height=\"100%\"/><div id='message' style='display:none;'>ok</div></body></html>".replace("#AD_IMAGE_URL#", (new StringBuilder()).append(CreateAdLayoutDisplay.b(a).g()).append("?v").append((new Random()).nextInt(0xdbba0)).toString());
                JavascriptInterface javascriptinterface = a. new JavascriptInterface();
                CreateAdLayoutDisplay.l(a).addJavascriptInterface(javascriptinterface, "android");
                CreateAdLayoutDisplay.l(a).setWebViewClient(a. new b());
                CreateAdLayoutDisplay.l(a).loadDataWithBaseURL(null, s1, "text/html", "UTF-8", null);
                CreateAdLayoutDisplay.l(a).setId(21);
                CreateAdLayoutDisplay.a(a, null);
                if (jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).i()[0])) goto _L2; else goto _L1
_L1:
                CreateAdLayoutDisplay.a(a, new AbsoluteLayout(CreateAdLayoutDisplay.c(a)));
                android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, (int)CreateAdLayoutDisplay.o(a));
                layoutparams1.addRule(3, 25);
                CreateAdLayoutDisplay.m(a).setLayoutParams(layoutparams1);
                CreateAdLayoutDisplay.m(a).setId(26);
                FrameLayout framelayout = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[0]);
                FrameLayout framelayout1 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[1]);
                FrameLayout framelayout2 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[2]);
                FrameLayout framelayout3 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[3]);
                FrameLayout framelayout4 = CreateAdLayoutDisplay.b(a, CreateAdLayoutDisplay.b(a).i()[4]);
                if (framelayout != null)
                {
                    CreateAdLayoutDisplay.m(a).addView(framelayout);
                }
                if (framelayout1 != null)
                {
                    CreateAdLayoutDisplay.m(a).addView(framelayout1);
                }
                if (framelayout2 != null)
                {
                    CreateAdLayoutDisplay.m(a).addView(framelayout2);
                }
                if (framelayout3 != null)
                {
                    CreateAdLayoutDisplay.m(a).addView(framelayout3);
                }
                if (framelayout4 != null)
                {
                    CreateAdLayoutDisplay.m(a).addView(framelayout4);
                }
                CreateAdLayoutDisplay.a(a, framelayout, CreateAdLayoutDisplay.b(a).h()[0]);
                CreateAdLayoutDisplay.a(a, framelayout1, CreateAdLayoutDisplay.b(a).h()[1]);
                CreateAdLayoutDisplay.a(a, framelayout2, CreateAdLayoutDisplay.b(a).h()[2]);
                CreateAdLayoutDisplay.a(a, framelayout3, CreateAdLayoutDisplay.b(a).h()[3]);
                CreateAdLayoutDisplay.a(a, framelayout4, CreateAdLayoutDisplay.b(a).h()[4]);
_L4:
                CreateAdLayoutDisplay.q(a).set(true);
                return;
_L2:
                if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).h()[0]))
                {
                    CreateAdLayoutDisplay.l(a).setOnTouchListener(new android.view.View.OnTouchListener(this) {

                        final _cls4 a;

                        public boolean onTouch(View view, MotionEvent motionevent)
                        {
                            motionevent.getAction();
                            JVM INSTR tableswitch 0 0: default 24
                        //                                       0 26;
                               goto _L1 _L2
_L1:
                            return true;
_L2:
                            (new Thread(a.a. new a(CreateAdLayoutDisplay.b(a.a).h()[0]))).start();
                            if (true) goto _L1; else goto _L3
_L3:
                        }

            
            {
                a = _pcls4;
                super();
            }
                    });
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
        while (!q.get()) 
        {
            try
            {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedexception)
            {
                jp.co.yahoo.android.ads.h.a(3, (new StringBuilder()).append("InterruptedException: ").append(interruptedexception.getMessage()).toString());
            }
        }
    }

    static AtomicBoolean i(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.p;
    }

    private void i()
    {
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                if (CreateAdLayoutDisplay.n(a) != null)
                {
                    CreateAdLayoutDisplay.n(a).setVisibility(0);
                }
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
    }

    static FrameLayout j(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.k;
    }

    private void j()
    {
        ((Activity)a).runOnUiThread(new Runnable() {

            final CreateAdLayoutDisplay a;

            public void run()
            {
                if (CreateAdLayoutDisplay.n(a) != null)
                {
                    CreateAdLayoutDisplay.n(a).setVisibility(4);
                }
            }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
        });
    }

    static WebView k(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.i;
    }

    static WebView l(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.s;
    }

    static AbsoluteLayout m(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.h;
    }

    static ProgressBar n(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.d;
    }

    static double o(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.f;
    }

    static AtomicBoolean p(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.o;
    }

    static AtomicBoolean q(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        return createadlayoutdisplay.q;
    }

    static void r(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        createadlayoutdisplay.i();
    }

    static void s(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        createadlayoutdisplay.j();
    }

    static void t(CreateAdLayoutDisplay createadlayoutdisplay)
    {
        createadlayoutdisplay.f();
    }

    public void a()
    {
        if (c != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (b == null)
        {
            b = new jp.co.yahoo.android.ads.a();
        }
        if (c.has("csc"))
        {
            b.j(c.getString("csc"));
        }
        if (c.has("image"))
        {
            JSONObject jsonobject3 = c.getJSONObject("image");
            if (jsonobject3.has("banner"))
            {
                JSONObject jsonobject5 = jsonobject3.getJSONObject("banner");
                if (jsonobject5.has("portrait"))
                {
                    b.d(jsonobject5.getString("portrait"));
                }
                if (jsonobject5.has("flashprt"))
                {
                    b.m(jsonobject5.getString("flashprt"));
                }
                if (jsonobject5.has("height"))
                {
                    b.a(jsonobject5.getInt("height"));
                }
                if (jsonobject5.has("pr"))
                {
                    b.a(jsonobject5.getBoolean("pr"));
                }
            }
            if (jsonobject3.has("expand"))
            {
                JSONObject jsonobject4 = jsonobject3.getJSONObject("expand");
                if (jsonobject4.has("portrait"))
                {
                    b.e(jsonobject4.getString("portrait"));
                }
                if (jsonobject4.has("flashprt"))
                {
                    b.n(jsonobject4.getString("flashprt"));
                }
                if (jsonobject4.has("landscape"))
                {
                    b.f(jsonobject4.getString("landscape"));
                }
                if (jsonobject4.has("background"))
                {
                    b.g(jsonobject4.getString("background"));
                }
            }
        }
        if (!c.has("link")) goto _L4; else goto _L3
_L3:
        JSONArray jsonarray = c.getJSONArray("link");
        int i1 = 0;
_L5:
        if (i1 < jsonarray.length())
        {
            JSONObject jsonobject1 = jsonarray.getJSONObject(i1);
            if (jsonobject1.has("url") && !"".equals(jsonobject1.getString("url")))
            {
                b.a(jsonobject1.getString("url"), i1);
            }
            if (jsonobject1.has("coords"))
            {
                JSONObject jsonobject2 = jsonobject1.getJSONObject("coords");
                if (jsonobject2.has("portrait"))
                {
                    JSONArray jsonarray2 = jsonobject2.getJSONArray("portrait");
                    b.b(a(jsonarray2), i1);
                }
                if (jsonobject2.has("landscape"))
                {
                    JSONArray jsonarray1 = jsonobject2.getJSONArray("landscape");
                    b.c(a(jsonarray1), i1);
                }
            }
            break MISSING_BLOCK_LABEL_624;
        }
_L4:
        try
        {
            if (c.has("action"))
            {
                JSONObject jsonobject = c.getJSONObject("action");
                if (jsonobject.has("open"))
                {
                    b.h(jsonobject.getString("open"));
                }
            }
            if (!jp.co.yahoo.android.ads.h.d(b.g()))
            {
                b.i(c(b.l()));
                return;
            }
        }
        catch (JSONException jsonexception)
        {
            jp.co.yahoo.android.ads.h.a(6, (new StringBuilder()).append("JSONException Failed to parse display ad response:  ").append(c).toString(), jsonexception);
            return;
        }
          goto _L1
        i1++;
          goto _L5
    }

    public AdResponse b()
    {
_L2:
        return null;
        if (!jp.co.yahoo.android.ads.f.a(b.d(), false) || !jp.co.yahoo.android.ads.h.d(b.g()) && !jp.co.yahoo.android.ads.f.a(b.g(), false) || !jp.co.yahoo.android.ads.h.d(b.p()) && !jp.co.yahoo.android.ads.f.a(b.p(), false)) goto _L2; else goto _L1
_L1:
        l = new AdContainer(a) {

            final CreateAdLayoutDisplay b;

            protected void onDetachedFromWindow()
            {
                super.onDetachedFromWindow();
                CreateAdLayoutDisplay.d(b);
            }

            
            {
                b = CreateAdLayoutDisplay.this;
                super(context);
            }
        };
        AdResponse adresponse;
        if (jp.co.yahoo.android.ads.e.g(a) && !jp.co.yahoo.android.ads.h.d(b.o()))
        {
            if (!jp.co.yahoo.android.ads.f.a(b.o(), false))
            {
                continue; /* Loop/switch isn't completed */
            }
            WebView webview1 = e();
            ((Activity)a).runOnUiThread(new Runnable(webview1) {

                final WebView a;
                final CreateAdLayoutDisplay b;

                public void run()
                {
                    CreateAdLayoutDisplay.e(b).addView(a);
                }

            
            {
                b = CreateAdLayoutDisplay.this;
                a = webview;
                super();
            }
            });
            webview1.setOnTouchListener(new android.view.View.OnTouchListener() {

                final CreateAdLayoutDisplay a;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    this;
                    JVM INSTR monitorenter ;
                    int i1 = motionevent.getAction();
                    i1;
                    JVM INSTR tableswitch 0 0: default 28
                //                               0 32;
                       goto _L1 _L2
_L1:
                    this;
                    JVM INSTR monitorexit ;
                    return true;
_L2:
                    if (jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).g()) && jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).p())) goto _L4; else goto _L3
_L3:
                    CreateAdLayoutDisplay.a(a, view);
                    CreateAdLayoutDisplay.b(a, view);
                      goto _L1
                    Exception exception;
                    exception;
                    throw exception;
_L4:
                    (new Thread(a. new a(CreateAdLayoutDisplay.b(a).h()[0]))).start();
                      goto _L1
                }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
            });
        } else
        {
            WebView webview = d();
            if (webview == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            ((Activity)a).runOnUiThread(new Runnable(webview) {

                final WebView a;
                final CreateAdLayoutDisplay b;

                public void run()
                {
                    CreateAdLayoutDisplay.e(b).addView(a);
                }

            
            {
                b = CreateAdLayoutDisplay.this;
                a = webview;
                super();
            }
            });
            webview.setOnTouchListener(new android.view.View.OnTouchListener() {

                final CreateAdLayoutDisplay a;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 0: default 24
                //                               0 26;
                       goto _L1 _L2
_L1:
                    return true;
_L2:
                    if (!jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).g()) || !jp.co.yahoo.android.ads.h.d(CreateAdLayoutDisplay.b(a).p()))
                    {
                        CreateAdLayoutDisplay.a(a, view);
                        CreateAdLayoutDisplay.b(a, view);
                    } else
                    {
                        (new Thread(a. new a(CreateAdLayoutDisplay.b(a).h()[0]))).start();
                    }
                    if (true) goto _L1; else goto _L3
_L3:
                }

            
            {
                a = CreateAdLayoutDisplay.this;
                super();
            }
            });
        }
        adresponse = new AdResponse();
        adresponse.setAdlayout(l);
        adresponse.setRslog(jp.co.yahoo.android.ads.h.c(b.l()));
        adresponse.setCode("200");
        adresponse.setMessage("AdView Success");
        jp.co.yahoo.android.ads.f.a(a, b.l());
        return adresponse;
        if (true) goto _L2; else goto _L3
_L3:
    }
}
