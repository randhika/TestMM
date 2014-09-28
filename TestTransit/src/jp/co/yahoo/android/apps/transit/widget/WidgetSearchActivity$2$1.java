// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.os.Handler;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.yjvoice.YJVONbestResult;
import jp.co.yahoo.android.yjvoice.YJVORecognizeListener;
import jp.co.yahoo.android.yjvoice.YJVORecognizeResult;
import jp.co.yahoo.android.yjvoice.YJVO_STATE;
import jp.co.yahoo.android.yjvoice.YJVO_TYPE;
import jp.co.yahoo.android.yjvoice.screen.YJVOVRecognizer;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            WidgetSearchActivity

class this._cls1
    implements Runnable
{

    final sh this$1;

    public void run()
    {
        YJVORecognizeResult yjvorecognizeresult = WidgetSearchActivity.access$100(_fld0).getResult(0);
        if (yjvorecognizeresult.type == YJVO_TYPE.NBEST)
        {
            String s = ((YJVONbestResult)yjvorecognizeresult.result).getTranscribe(0);
            if (TransitUtil.isEmpty(s))
            {
                showSimpleErrorMessageDialog(getString(0x7f0d0157));
                return;
            } else
            {
                String as[] = s.split(" ", 0);
                WidgetSearchActivity.access$202(_fld0, as[0]);
                WidgetSearchActivity.access$300(_fld0);
                return;
            }
        } else
        {
            finish();
            return;
        }
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/widget/WidgetSearchActivity$2

/* anonymous class */
    class WidgetSearchActivity._cls2
        implements YJVORecognizeListener
    {

        final WidgetSearchActivity this$0;

        public void onRecognizeResult(int i, YJVORecognizeResult yjvorecognizeresult)
        {
            (new Handler(getMainLooper())).post(new WidgetSearchActivity._cls2._cls1());
        }

        public void onRecognizeState(YJVO_STATE yjvo_state)
        {
            switch (WidgetSearchActivity._cls8.$SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_STATE[yjvo_state.ordinal()])
            {
            default:
                return;

            case 1: // '\001'
                finish();
                return;

            case 2: // '\002'
            case 3: // '\003'
                finish();
                break;
            }
        }

        public void onRecordingStart()
        {
        }

        public void onVolumeChanged(short word0)
        {
        }

            
            {
                this$0 = WidgetSearchActivity.this;
                super();
            }
    }

}
