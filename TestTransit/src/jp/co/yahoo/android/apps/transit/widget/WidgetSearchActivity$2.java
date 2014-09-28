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

class this._cls0
    implements YJVORecognizeListener
{

    final WidgetSearchActivity this$0;

    public void onRecognizeResult(int i, YJVORecognizeResult yjvorecognizeresult)
    {
        (new Handler(getMainLooper())).post(new Runnable() {

            final WidgetSearchActivity._cls2 this$1;

            public void run()
            {
                YJVORecognizeResult yjvorecognizeresult1 = WidgetSearchActivity.access$100(this$0).getResult(0);
                if (yjvorecognizeresult1.type == YJVO_TYPE.NBEST)
                {
                    String s = ((YJVONbestResult)yjvorecognizeresult1.result).getTranscribe(0);
                    if (TransitUtil.isEmpty(s))
                    {
                        showSimpleErrorMessageDialog(getString(0x7f0d0157));
                        return;
                    } else
                    {
                        String as[] = s.split(" ", 0);
                        WidgetSearchActivity.access$202(this$0, as[0]);
                        WidgetSearchActivity.access$300(this$0);
                        return;
                    }
                } else
                {
                    finish();
                    return;
                }
            }

            
            {
                this$1 = WidgetSearchActivity._cls2.this;
                super();
            }
        });
    }

    public void onRecognizeState(YJVO_STATE yjvo_state)
    {
        switch (.SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_STATE[yjvo_state.ordinal()])
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

    _cls1.this._cls1()
    {
        this$0 = WidgetSearchActivity.this;
        super();
    }
}
