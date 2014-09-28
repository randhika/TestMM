// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.ProgressDialog;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.timer.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.yolp.common.ApiBase;
import jp.co.yahoo.android.yolp.common.YolpError;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class ata
    implements jp.co.yahoo.android.yolp.common.
{

    final TransitBaseActivity this$0;
    final StationData val$objStation;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        Object obj1;
        TimeTableSearch timetablesearch1;
        TimeTableSearch timetablesearch2;
label0:
        {
label1:
            {
                obj1 = apibase.getResult();
                TimeTableSearch timetablesearch = (TimeTableSearch)apibase;
                if (obj1 == null || !(obj1 instanceof Bundle))
                {
                    YolpError yolperror = timetablesearch.getError();
                    if (yolperror.getCode() == null || !yolperror.getCode().equals(getString(0x7f0d0516)))
                    {
                        break label1;
                    }
                    obj1 = new Bundle();
                }
                TransitBaseActivity.access$702(TransitBaseActivity.this, timetablesearch.getTodayKind());
                timetablesearch1 = new TimeTableSearch(TransitBaseActivity.this);
                timetablesearch1.setStation(val$objStation);
                timetablesearch1.setCode(val$objStation.getStationId());
                timetablesearch1.setId(val$objStation.getDirid());
                timetablesearch2 = new TimeTableSearch(TransitBaseActivity.this);
                timetablesearch2.setStation(val$objStation);
                timetablesearch2.setCode(val$objStation.getStationId());
                timetablesearch2.setId(val$objStation.getDirid());
                if (TransitBaseActivity.access$700(TransitBaseActivity.this) == 1)
                {
                    TransitBaseActivity.access$802(TransitBaseActivity.this, (Bundle)obj1);
                    timetablesearch1.setKind(2);
                    timetablesearch1.executeAsync(TransitBaseActivity.this, false);
                    timetablesearch2.setKind(4);
                    timetablesearch2.executeAsync(TransitBaseActivity.this, false);
                    return false;
                }
                break label0;
            }
            TransitBaseActivity.access$502(TransitBaseActivity.this, true);
            TransitBaseActivity.access$600(TransitBaseActivity.this).dismiss();
            endGetAllTimetable(null, null, TransitBaseActivity.access$700(TransitBaseActivity.this));
            TransitBaseActivity.access$802(TransitBaseActivity.this, null);
            TransitBaseActivity.access$902(TransitBaseActivity.this, null);
            TransitBaseActivity.access$1002(TransitBaseActivity.this, null);
            return false;
        }
        if (TransitBaseActivity.access$700(TransitBaseActivity.this) == 4)
        {
            TransitBaseActivity.access$1002(TransitBaseActivity.this, (Bundle)obj1);
            timetablesearch1.setKind(1);
            timetablesearch1.executeAsync(TransitBaseActivity.this, false);
            timetablesearch2.setKind(2);
            timetablesearch2.executeAsync(TransitBaseActivity.this, false);
            return false;
        } else
        {
            timetablesearch1.setKind(1);
            timetablesearch1.executeAsync(TransitBaseActivity.this, false);
            timetablesearch2.setKind(4);
            timetablesearch2.executeAsync(TransitBaseActivity.this, false);
            TransitBaseActivity.access$902(TransitBaseActivity.this, (Bundle)obj1);
            return false;
        }
    }

    ata()
    {
        this$0 = final_transitbaseactivity;
        val$objStation = StationData.this;
        super();
    }
}
