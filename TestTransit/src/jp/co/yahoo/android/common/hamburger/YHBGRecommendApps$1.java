// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.FileNotFoundException;
import java.util.Date;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGRecommendApps, YHBGUtils, YIOUtils

class val.doneFunc
    implements Runnable
{

    final YHBGRecommendApps this$0;
    private final Runnable val$doneFunc;
    private final boolean val$notUseCache;

    public void run()
    {
        AssetManager assetmanager;
        String s;
        boolean flag;
        String s1;
        boolean flag1;
        if (val$notUseCache && !YHBGUtils.isOvertime(YHBGRecommendApps.access$0(), 0x493e0L))
        {
            YHBGUtils.debug("\u524D\u56DE\u30C1\u30A7\u30C3\u30AF\u3057\u3066\u304B\u30895\u5206\u4EE5\u5185\u3067\u3059\u3002");
            val$doneFunc.run();
            return;
        }
        YHBGRecommendApps.access$1(new Date());
        assetmanager = YHBGRecommendApps.access$2(YHBGRecommendApps.this).getResources().getAssets();
        s = YHBGRecommendApps.access$3(YHBGRecommendApps.this);
        flag = YHBGUtils.isDebuggable(YHBGRecommendApps.access$2(YHBGRecommendApps.this));
        s1 = null;
        flag1 = false;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        s1 = YIOUtils.readFromStream(assetmanager.open(s));
        YHBGUtils.debug((new StringBuilder("assets\u306B\u3042\u308B ")).append(s).append(" \u3092\u5229\u7528").toString());
        flag1 = true;
_L6:
        if (flag1) goto _L2; else goto _L1
_L1:
        Date date = YHBGRecommendApps.access$4(YHBGRecommendApps.this);
        YHBGUtils.debug((new StringBuilder("\u524D\u56DEJSON \u30AD\u30E3\u30C3\u30B7\u30E5\u4FDD\u5B58\u65E5\u6642 => ")).append(date).toString());
        if (flag && val$notUseCache || YHBGUtils.isOvertime(date, 0x5265c00L))
        {
            break MISSING_BLOCK_LABEL_220;
        }
        YHBGUtils.debug("\u30AD\u30E3\u30C3\u30B7\u30E5\u6E08\u307F\u306EJSON\u30D5\u30A1\u30A4\u30EB\u3092\u53D6\u5F97");
        String s2 = YIOUtils.readFromStream(YHBGRecommendApps.access$2(YHBGRecommendApps.this).openFileInput((new StringBuilder("hbg__")).append(s).toString()));
        s1 = s2;
_L4:
        if (s1 == null)
        {
            YHBGUtils.debug((new StringBuilder("\u30EA\u30E2\u30FC\u30C8\u306E ")).append(s).append(" \u3092\u5229\u7528").toString());
            s1 = YHBGRecommendApps.access$5(YHBGRecommendApps.this);
            YHBGRecommendApps.access$6(YHBGRecommendApps.this, s1);
        }
_L2:
        YHBGRecommendApps.access$7(YHBGRecommendApps.this, s1);
        YHBGRecommendApps.access$8(YHBGRecommendApps.this);
        val$doneFunc.run();
        return;
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        YHBGUtils.debug((new StringBuilder("assets\u306B ")).append(s).append(" \u306A\u3057").toString());
        flag1 = false;
        continue; /* Loop/switch isn't completed */
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        flag1 = false;
        if (true) goto _L6; else goto _L5
_L5:
    }

    ()
    {
        this$0 = final_yhbgrecommendapps;
        val$notUseCache = flag;
        val$doneFunc = Runnable.this;
        super();
    }
}
