// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.codehaus.jackson.io.NumberInput;

public class StdDateFormat extends DateFormat
{

    static final String ALL_FORMATS[] = {
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"
    };
    static final SimpleDateFormat DATE_FORMAT_ISO8601;
    static final SimpleDateFormat DATE_FORMAT_ISO8601_Z;
    static final SimpleDateFormat DATE_FORMAT_PLAIN;
    static final SimpleDateFormat DATE_FORMAT_RFC1123;
    static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final StdDateFormat instance = new StdDateFormat();
    transient SimpleDateFormat _formatISO8601;
    transient SimpleDateFormat _formatISO8601_z;
    transient SimpleDateFormat _formatPlain;
    transient SimpleDateFormat _formatRFC1123;

    public StdDateFormat()
    {
    }

    public static DateFormat getBlueprintISO8601Format()
    {
        return DATE_FORMAT_ISO8601;
    }

    public static DateFormat getBlueprintRFC1123Format()
    {
        return DATE_FORMAT_RFC1123;
    }

    public static DateFormat getISO8601Format(TimeZone timezone)
    {
        SimpleDateFormat simpledateformat = (SimpleDateFormat)DATE_FORMAT_ISO8601.clone();
        simpledateformat.setTimeZone(timezone);
        return simpledateformat;
    }

    public static DateFormat getRFC1123Format(TimeZone timezone)
    {
        SimpleDateFormat simpledateformat = (SimpleDateFormat)DATE_FORMAT_RFC1123.clone();
        simpledateformat.setTimeZone(timezone);
        return simpledateformat;
    }

    private static final boolean hasTimeZone(String s)
    {
        int i = s.length();
        if (i < 6) goto _L2; else goto _L1
_L1:
        char c = s.charAt(i - 6);
        if (c != '+' && c != '-') goto _L4; else goto _L3
_L3:
        char c1;
        return true;
_L4:
        char c2;
        if ((c1 = s.charAt(i - 5)) == '+' || c1 == '-' || ((c2 = s.charAt(i - 3)) == '+' || c2 == '-')) goto _L3; else goto _L2
_L2:
        return false;
    }

    public volatile Object clone()
    {
        return clone();
    }

    public StdDateFormat clone()
    {
        return new StdDateFormat();
    }

    public StringBuffer format(Date date, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (_formatISO8601 == null)
        {
            _formatISO8601 = (SimpleDateFormat)DATE_FORMAT_ISO8601.clone();
        }
        return _formatISO8601.format(date, stringbuffer, fieldposition);
    }

    protected boolean looksLikeISO8601(String s)
    {
        int i = s.length();
        boolean flag = false;
        if (i >= 5)
        {
            boolean flag1 = Character.isDigit(s.charAt(0));
            flag = false;
            if (flag1)
            {
                boolean flag2 = Character.isDigit(s.charAt(3));
                flag = false;
                if (flag2)
                {
                    char c = s.charAt(4);
                    flag = false;
                    if (c == '-')
                    {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public Date parse(String s)
        throws ParseException
    {
        String s1 = s.trim();
        ParsePosition parseposition = new ParsePosition(0);
        Date date = parse(s1, parseposition);
        if (date != null)
        {
            return date;
        }
        StringBuilder stringbuilder = new StringBuilder();
        String as[] = ALL_FORMATS;
        int i = as.length;
        int j = 0;
        while (j < i) 
        {
            String s2 = as[j];
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append("\", \"");
            } else
            {
                stringbuilder.append('"');
            }
            stringbuilder.append(s2);
            j++;
        }
        stringbuilder.append('"');
        Object aobj[] = new Object[2];
        aobj[0] = s1;
        aobj[1] = stringbuilder.toString();
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", aobj), parseposition.getErrorIndex());
    }

    public Date parse(String s, ParsePosition parseposition)
    {
        if (looksLikeISO8601(s))
        {
            return parseAsISO8601(s, parseposition);
        }
        int i = s.length();
        char c;
        do
        {
            if (--i < 0)
            {
                break;
            }
            c = s.charAt(i);
        } while (c >= '0' && c <= '9');
        if (i < 0 && NumberInput.inLongRange(s, false))
        {
            return new Date(Long.parseLong(s));
        } else
        {
            return parseAsRFC1123(s, parseposition);
        }
    }

    protected Date parseAsISO8601(String s, ParsePosition parseposition)
    {
        int i;
        char c;
        i = s.length();
        c = s.charAt(i - 1);
        if (i > 10 || !Character.isDigit(c)) goto _L2; else goto _L1
_L1:
        SimpleDateFormat simpledateformat;
        simpledateformat = _formatPlain;
        if (simpledateformat == null)
        {
            simpledateformat = (SimpleDateFormat)DATE_FORMAT_PLAIN.clone();
            _formatPlain = simpledateformat;
        }
_L9:
        return simpledateformat.parse(s, parseposition);
_L2:
        if (c == 'Z')
        {
            simpledateformat = _formatISO8601_z;
            if (simpledateformat == null)
            {
                simpledateformat = (SimpleDateFormat)DATE_FORMAT_ISO8601_Z.clone();
                _formatISO8601_z = simpledateformat;
            }
            if (s.charAt(i - 4) == ':')
            {
                StringBuilder stringbuilder3 = new StringBuilder(s);
                stringbuilder3.insert(i - 1, ".000");
                s = stringbuilder3.toString();
            }
            continue; /* Loop/switch isn't completed */
        }
        if (!hasTimeZone(s)) goto _L4; else goto _L3
_L3:
        char c1 = s.charAt(i - 3);
        if (c1 != ':') goto _L6; else goto _L5
_L5:
        StringBuilder stringbuilder1 = new StringBuilder(s);
        stringbuilder1.delete(i - 3, i - 2);
        s = stringbuilder1.toString();
_L7:
        int j = s.length();
        if (Character.isDigit(s.charAt(j - 9)))
        {
            StringBuilder stringbuilder2 = new StringBuilder(s);
            stringbuilder2.insert(j - 5, ".000");
            s = stringbuilder2.toString();
        }
        simpledateformat = _formatISO8601;
        if (_formatISO8601 == null)
        {
            simpledateformat = (SimpleDateFormat)DATE_FORMAT_ISO8601.clone();
            _formatISO8601 = simpledateformat;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (c1 == '+' || c1 == '-')
        {
            s = (new StringBuilder()).append(s).append("00").toString();
        }
        if (true) goto _L7; else goto _L4
_L4:
        StringBuilder stringbuilder = new StringBuilder(s);
        if (-1 + (i - s.lastIndexOf('T')) <= 8)
        {
            stringbuilder.append(".000");
        }
        stringbuilder.append('Z');
        s = stringbuilder.toString();
        simpledateformat = _formatISO8601_z;
        if (simpledateformat == null)
        {
            simpledateformat = (SimpleDateFormat)DATE_FORMAT_ISO8601_Z.clone();
            _formatISO8601_z = simpledateformat;
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected Date parseAsRFC1123(String s, ParsePosition parseposition)
    {
        if (_formatRFC1123 == null)
        {
            _formatRFC1123 = (SimpleDateFormat)DATE_FORMAT_RFC1123.clone();
        }
        return _formatRFC1123.parse(s, parseposition);
    }

    static 
    {
        TimeZone timezone = TimeZone.getTimeZone("GMT");
        DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        DATE_FORMAT_RFC1123.setTimeZone(timezone);
        DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DATE_FORMAT_ISO8601.setTimeZone(timezone);
        DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DATE_FORMAT_ISO8601_Z.setTimeZone(timezone);
        DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT_PLAIN.setTimeZone(timezone);
    }
}
