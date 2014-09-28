// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.applicot.collector.CrashReportData;

// Referenced classes of package jp.co.yahoo.applicot:
//            ReportField

final class CrashReportPersister
{

    private static final int CONTINUE = 3;
    private static final int IGNORE = 5;
    private static final int KEY_DONE = 4;
    private static final String LINE_SEPARATOR = "\n";
    private static final int NONE = 0;
    private static final int SLASH = 1;
    private static final int UNICODE = 2;
    private final Context context;

    CrashReportPersister(Context context1)
    {
        context = context1;
    }

    private void dumpString(StringBuilder stringbuilder, String s, boolean flag)
    {
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
                        if (!flag && s == null)
                        {
                            s = "";
                        }
                        int i = 0;
                        if (!flag)
                        {
                            int k = s.length();
                            i = 0;
                            if (k < 0)
                            {
                                char c1 = s.charAt(0);
                                i = 0;
                                if (c1 == ' ')
                                {
                                    stringbuilder.append("\\ ");
                                    i = 0 + 1;
                                }
                            }
                        }
label4:
                        do
                        {
                            {
                                if (i >= s.length())
                                {
                                    break label0;
                                }
                                char c = s.charAt(i);
                                switch (c)
                                {
                                case 11: // '\013'
                                default:
                                    if ("\\#!=:".indexOf(c) >= 0 || flag && c == ' ')
                                    {
                                        stringbuilder.append('\\');
                                    }
                                    if (c >= ' ' && c <= '~')
                                    {
                                        stringbuilder.append(c);
                                    } else
                                    {
                                        String s1 = Integer.toHexString(c);
                                        stringbuilder.append("\\u");
                                        for (int j = 0; j < 4 - s1.length(); j++)
                                        {
                                            stringbuilder.append("0");
                                        }

                                        stringbuilder.append(s1);
                                    }
                                    break;

                                case 9: // '\t'
                                    break label4;

                                case 10: // '\n'
                                    break label3;

                                case 12: // '\f'
                                    break label2;

                                case 13: // '\r'
                                    break label1;
                                }
                            }
                            i++;
                        } while (true);
                        stringbuilder.append("\\t");
                        break MISSING_BLOCK_LABEL_165;
                    }
                    stringbuilder.append("\\n");
                    break MISSING_BLOCK_LABEL_165;
                }
                stringbuilder.append("\\f");
                break MISSING_BLOCK_LABEL_165;
            }
            stringbuilder.append("\\r");
            break MISSING_BLOCK_LABEL_165;
        }
    }

    private boolean isEbcdic(BufferedInputStream bufferedinputstream)
        throws IOException
    {
        byte byte0;
        do
        {
            byte0 = (byte)bufferedinputstream.read();
            if (byte0 == -1 || byte0 == 35 || byte0 == 10 || byte0 == 61)
            {
                return false;
            }
        } while (byte0 != 21);
        return true;
    }

    private CrashReportData load(Reader reader)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        int k;
        i = 0;
        j = 0;
        k = 0;
        char ac[] = new char[40];
        int l;
        boolean flag;
        l = -1;
        flag = true;
        CrashReportData crashreportdata;
        BufferedReader bufferedreader;
        crashreportdata = new CrashReportData();
        bufferedreader = new BufferedReader(reader, 8192);
        int i1 = 0;
_L10:
        int j1 = bufferedreader.read();
        if (j1 != -1) goto _L2; else goto _L1
_L1:
        if (i != 2 || k > 4) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("luni.08");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        char c = (char)j1;
        char ac1[];
        if (i1 != ac.length)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        ac1 = new char[2 * ac.length];
        System.arraycopy(ac, 0, ac1, 0, i1);
        ac = ac1;
        if (i != 2) goto _L6; else goto _L5
_L5:
        int k1 = Character.digit(c, 16);
        if (k1 < 0) goto _L8; else goto _L7
_L7:
        j = k1 + (j << 4);
        if (++k < 4) goto _L10; else goto _L9
_L9:
        int l1;
        i = 0;
        l1 = i1 + 1;
        ac[i1] = (char)j;
        if (c == '\n' || c == '\205') goto _L12; else goto _L11
_L11:
        i1 = l1;
        i = 0;
          goto _L10
_L8:
        if (k > 4) goto _L9; else goto _L13
_L13:
        throw new IllegalArgumentException("luni.09");
_L21:
        if (i == 4)
        {
            l = i1;
            i = 0;
        }
        i2 = i1 + 1;
        ac[i1] = c;
        i1 = i2;
        flag = false;
          goto _L10
_L23:
        if (!Character.isWhitespace(c)) goto _L15; else goto _L14
_L14:
        if (i == 3)
        {
            i = 5;
        }
          goto _L16
_L18:
        j2 = bufferedreader.read();
        if (j2 == -1) goto _L10; else goto _L17
_L17:
        c1 = (char)j2;
        if (c1 != '\r' && c1 != '\n' && c1 != '\205') goto _L18; else goto _L10
_L33:
        s = new String(ac, 0, i1);
        crashreportdata.put(Enum.valueOf(jp/co/yahoo/applicot/ReportField, s.substring(0, l)), s.substring(l));
          goto _L19
_L4:
        if (l == -1 && i1 > 0)
        {
            l = i1;
        }
        if (l < 0)
        {
            break MISSING_BLOCK_LABEL_424;
        }
        s1 = new String(ac, 0, i1);
        reportfield = (ReportField)Enum.valueOf(jp/co/yahoo/applicot/ReportField, s1.substring(0, l));
        s2 = s1.substring(l);
        if (i != 1)
        {
            break MISSING_BLOCK_LABEL_414;
        }
        s2 = (new StringBuilder()).append(s2).append("\0").toString();
        crashreportdata.put(reportfield, s2);
        this;
        JVM INSTR monitorexit ;
        return crashreportdata;
_L12:
        i1 = l1;
_L6:
label0:
        {
            if (i != 1)
            {
                break MISSING_BLOCK_LABEL_603;
            }
            i = 0;
            switch (c)
            {
            case 10: // '\n'
            case 133: 
                break MISSING_BLOCK_LABEL_543;

            case 13: // '\r'
                break label0;

            case 98: // 'b'
                break MISSING_BLOCK_LABEL_548;

            case 102: // 'f'
                break MISSING_BLOCK_LABEL_557;

            case 110: // 'n'
                break MISSING_BLOCK_LABEL_566;

            case 114: // 'r'
                break MISSING_BLOCK_LABEL_575;

            case 116: // 't'
                break MISSING_BLOCK_LABEL_584;

            case 117: // 'u'
                break MISSING_BLOCK_LABEL_593;
            }
        }
        if (true) goto _L21; else goto _L20
_L20:
        if (true) goto _L10; else goto _L22
_L22:
          goto _L10
        i = 5;
          goto _L10
        c = '\b';
        i = 0;
          goto _L21
        c = '\f';
        i = 0;
          goto _L21
        c = '\n';
        i = 0;
          goto _L21
        c = '\r';
        i = 0;
          goto _L21
        c = '\t';
        i = 0;
          goto _L21
        i = 2;
        k = 0;
        j = 0;
          goto _L10
        c;
        JVM INSTR lookupswitch 8: default 237
    //                   10: 716
    //                   13: 726
    //                   33: 708
    //                   35: 708
    //                   58: 782
    //                   61: 782
    //                   92: 768
    //                   133: 726;
           goto _L23 _L24 _L25 _L26 _L26 _L27 _L27 _L28 _L25
_L27:
        continue; /* Loop/switch isn't completed */
_L16:
        if (i1 == 0 || i1 == l || i == 5) goto _L10; else goto _L29
_L29:
        if (l != -1) goto _L15; else goto _L30
_L30:
        i = 4;
          goto _L10
_L26:
        if (!flag) goto _L23; else goto _L18
_L24:
        if (i != 3) goto _L25; else goto _L31
_L31:
        i = 5;
          goto _L10
_L25:
        flag = true;
        if (i1 <= 0 && (i1 != 0 || l != 0)) goto _L19; else goto _L32
_L32:
        if (l == -1)
        {
            l = i1;
        }
          goto _L33
_L19:
        l = -1;
        i = 0;
        i1 = 0;
          goto _L10
_L28:
        if (i == 4)
        {
            l = i1;
        }
        i = 1;
          goto _L10
        if (l != -1) goto _L23; else goto _L34
_L34:
        l = i1;
        i = 0;
          goto _L10
_L15:
        if (i == 5 || i == 3)
        {
            i = 0;
        }
          goto _L21
    }

    public CrashReportData load(String s)
        throws IOException
    {
        FileInputStream fileinputstream;
        fileinputstream = context.openFileInput(s);
        if (fileinputstream == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid crash report fileName : ").append(s).toString());
        }
        BufferedInputStream bufferedinputstream;
        boolean flag;
        bufferedinputstream = new BufferedInputStream(fileinputstream, 8192);
        bufferedinputstream.mark(0x7fffffff);
        flag = isEbcdic(bufferedinputstream);
        bufferedinputstream.reset();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        CrashReportData crashreportdata = load(((Reader) (new InputStreamReader(bufferedinputstream, "ISO8859-1"))));
        fileinputstream.close();
        return crashreportdata;
        CrashReportData crashreportdata1 = load(((Reader) (new InputStreamReader(bufferedinputstream))));
        fileinputstream.close();
        return crashreportdata1;
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public void store(CrashReportData crashreportdata, String s)
        throws IOException
    {
        java.io.FileOutputStream fileoutputstream = context.openFileOutput(s, 0);
        OutputStreamWriter outputstreamwriter;
        StringBuilder stringbuilder = new StringBuilder(200);
        outputstreamwriter = new OutputStreamWriter(fileoutputstream, "ISO8859_1");
        for (Iterator iterator = crashreportdata.entrySet().iterator(); iterator.hasNext(); stringbuilder.setLength(0))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            dumpString(stringbuilder, ((ReportField)entry.getKey()).toString(), true);
            stringbuilder.append('=');
            dumpString(stringbuilder, (String)entry.getValue(), false);
            stringbuilder.append("\n");
            outputstreamwriter.write(stringbuilder.toString());
        }

        break MISSING_BLOCK_LABEL_148;
        Exception exception;
        exception;
        fileoutputstream.close();
        throw exception;
        outputstreamwriter.flush();
        fileoutputstream.close();
        return;
    }
}
