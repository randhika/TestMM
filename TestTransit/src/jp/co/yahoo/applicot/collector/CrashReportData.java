// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.collector;

import java.util.EnumMap;
import jp.co.yahoo.applicot.ReportField;
import jp.co.yahoo.applicot.util.JSONReportBuilder;
import org.json.JSONObject;

public final class CrashReportData extends EnumMap
{

    private static final long serialVersionUID = 0x3912d07a70363e98L;

    public CrashReportData()
    {
        super(jp/co/yahoo/applicot/ReportField);
    }

    public String getProperty(ReportField reportfield)
    {
        return (String)super.get(reportfield);
    }

    public JSONObject toJSON()
        throws jp.co.yahoo.applicot.util.JSONReportBuilder.JSONReportException
    {
        return JSONReportBuilder.buildJSONReport(this);
    }
}
