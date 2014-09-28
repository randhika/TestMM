// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.History;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class RequestReview extends LinearLayout
{

    private static final int BORDER_COUNT = 10;
    public static final String PREF_REVIEW = "review";
    public static final String PREF_REVIEW_KEY = "review_key";
    private static final String PREF_REVIEW_VER_KEY = "ver_key";
    private static final int REVIEW_AFTER = 2;
    public static final int REVIEW_DONE = 3;
    private static final int REVIEW_NO = 0;
    private static final int REVIEW_NONE = 1;
    private Context context;
    private SharedPreferences pref;

    public RequestReview(Context context1)
    {
        super(context1);
        context = null;
        pref = null;
        context = context1;
        pref = context1.getSharedPreferences("review", 0);
    }

    public void leadToAppOnMarket()
    {
        try
        {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("market://details?id=").append(context.getPackageName()).toString()));
            getContext().startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Toast.makeText(getContext(), "market \u30A2\u30D7\u30EA\u304C\u5B58\u5728\u3057\u307E\u305B\u3093\u3002", 1);
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void request()
    {
        pref = context.getSharedPreferences("review", 0);
        int i = pref.getInt("review_key", 1);
        if (i != 0 && i != 3)
        {
            if (i == 2 && TransitUtil.getVersionCode(context) > pref.getInt("ver_key", 0))
            {
                i = 1;
            }
            if (i == 1)
            {
                List list = (new History(context)).getHistory();
                if (list != null && list.size() >= 10)
                {
                    showDialog();
                    return;
                }
            }
        }
    }

    public void showDialog()
    {
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(getContext());
        transitdialogbuilder.setTitle(context.getString(0x7f0d047e));
        transitdialogbuilder.setMessage(context.getString(0x7f0d047b));
        transitdialogbuilder.setPositiveButton(context.getString(0x7f0d047d), new android.content.DialogInterface.OnClickListener() {

            final RequestReview this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                leadToAppOnMarket();
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putInt("review_key", 3);
                editor.commit();
                TransitUtil.touchRD(context.getString(0x7f0d0559));
            }

            
            {
                this$0 = RequestReview.this;
                super();
            }
        });
        transitdialogbuilder.setNeutralButton(context.getString(0x7f0d047a), new android.content.DialogInterface.OnClickListener() {

            final RequestReview this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putInt("review_key", 2);
                editor.putInt("ver_key", TransitUtil.getVersionCode(context));
                editor.commit();
                TransitUtil.touchRD(context.getString(0x7f0d0557));
            }

            
            {
                this$0 = RequestReview.this;
                super();
            }
        });
        transitdialogbuilder.setNegativeButton(context.getString(0x7f0d047c), new android.content.DialogInterface.OnClickListener() {

            final RequestReview this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putInt("review_key", 0);
                editor.commit();
                TransitUtil.touchRD(context.getString(0x7f0d0558));
            }

            
            {
                this$0 = RequestReview.this;
                super();
            }
        });
        transitdialogbuilder.setCancelable(true);
        transitdialogbuilder.create().show();
    }


}
