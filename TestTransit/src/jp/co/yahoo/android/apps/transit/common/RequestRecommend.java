// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class RequestRecommend extends LinearLayout
{

    private static final int BORDER_COUNT = 10;
    public static final String PREF_RECOMMEND = "recommend";
    public static final String PREF_RECOMMEND_KEY = "recommend_key";
    private static final String PREF_RECOMMEND_VER_KEY = "ver_key";
    public static final int RECOMMEND_DONE = 2;
    private static final int RECOMMEND_NO = 0;
    private static final int RECOMMEND_NONE = 1;
    private Context context;
    private SharedPreferences pref;

    public RequestRecommend(Context context1)
    {
        super(context1);
        context = null;
        pref = null;
        context = context1;
        pref = context1.getSharedPreferences("recommend", 0);
    }

    public static void recommend(Context context1)
    {
        TransitUtil.shareAnotherAppExecute(context1, context1.getString(0x7f0d0460));
    }

    public void request()
    {
        pref = context.getSharedPreferences("recommend", 0);
        int i = pref.getInt("recommend_key", 1);
        if (pref.getInt("ver_key", 0) == TransitUtil.getVersionCode(context) && (i == 0 || i == 2))
        {
            return;
        } else
        {
            showDialog();
            return;
        }
    }

    public void showDialog()
    {
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(getContext());
        transitdialogbuilder.setMessage(context.getString(0x7f0d045e));
        transitdialogbuilder.setTitle(context.getString(0x7f0d0464));
        transitdialogbuilder.setPositiveButton(context.getString(0x7f0d0463), new android.content.DialogInterface.OnClickListener() {

            final RequestRecommend this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putInt("recommend_key", 2);
                editor.putInt("ver_key", TransitUtil.getVersionCode(context));
                editor.commit();
                TransitUtil.touchRD(context.getString(0x7f0d0559));
                RequestRecommend.recommend(context);
            }

            
            {
                this$0 = RequestRecommend.this;
                super();
            }
        });
        transitdialogbuilder.setNegativeButton(context.getString(0x7f0d0462), new android.content.DialogInterface.OnClickListener() {

            final RequestRecommend this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                android.content.SharedPreferences.Editor editor = pref.edit();
                editor.putInt("recommend_key", 0);
                editor.putInt("ver_key", TransitUtil.getVersionCode(context));
                editor.commit();
                TransitUtil.touchRD(context.getString(0x7f0d0558));
            }

            
            {
                this$0 = RequestRecommend.this;
                super();
            }
        });
        transitdialogbuilder.setCancelable(true);
        transitdialogbuilder.create().show();
    }


}
