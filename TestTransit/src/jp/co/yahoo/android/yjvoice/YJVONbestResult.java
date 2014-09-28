// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;

import java.lang.reflect.Array;

// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizeResult, YJVO_TYPE, YJVO_FILTER

public class YJVONbestResult extends YJVORecognizeResult
{
    public static final class YJVO_STATE_RESULT extends Enum
    {

        private static final YJVO_STATE_RESULT $VALUES[];
        public static final YJVO_STATE_RESULT RESULT_STATE_EOD;
        public static final YJVO_STATE_RESULT RESULT_STATE_NORMAL;
        public static final YJVO_STATE_RESULT RESULT_STATE_PARTIAL;
        public static final YJVO_STATE_RESULT RESULT_STATE_TIMEOUT;

        public static YJVO_STATE_RESULT valueOf(String s)
        {
            return (YJVO_STATE_RESULT)Enum.valueOf(jp/co/yahoo/android/yjvoice/YJVONbestResult$YJVO_STATE_RESULT, s);
        }

        public static YJVO_STATE_RESULT[] values()
        {
            return (YJVO_STATE_RESULT[])$VALUES.clone();
        }

        static 
        {
            RESULT_STATE_NORMAL = new YJVO_STATE_RESULT("RESULT_STATE_NORMAL", 0);
            RESULT_STATE_EOD = new YJVO_STATE_RESULT("RESULT_STATE_EOD", 1);
            RESULT_STATE_TIMEOUT = new YJVO_STATE_RESULT("RESULT_STATE_TIMEOUT", 2);
            RESULT_STATE_PARTIAL = new YJVO_STATE_RESULT("RESULT_STATE_PARTIAL", 3);
            YJVO_STATE_RESULT ayjvo_state_result[] = new YJVO_STATE_RESULT[4];
            ayjvo_state_result[0] = RESULT_STATE_NORMAL;
            ayjvo_state_result[1] = RESULT_STATE_EOD;
            ayjvo_state_result[2] = RESULT_STATE_TIMEOUT;
            ayjvo_state_result[3] = RESULT_STATE_PARTIAL;
            $VALUES = ayjvo_state_result;
        }

        private YJVO_STATE_RESULT(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int INDEX_FILTER_NONE = 0;
    public static final int INDEX_FILTER_NUMBER = 1;
    public static final int INDEX_FILTER_SENTENCE = 2;
    private static final String TAG = "YJVOICE:YJVONbestResult";
    public double RT;
    public int candCount;
    public String delimiter;
    public double endTime;
    public YJVO_FILTER filter;
    public int filterCount;
    public int index;
    public double phraseConfidence[];
    public double phraseProbability[];
    public String pronounce[][][];
    public double resultTime;
    public double startTime;
    public YJVO_STATE_RESULT status;
    public String transcribe[][][];
    public double updateTime;
    public String uttID;
    public double wordConfidence[][][];
    public int wordCount[][];
    public double wordEndTime[][][];
    public double wordStartTime[][][];
    public String xmlResult;

    public YJVONbestResult()
    {
        delimiter = " ";
        index = -32768;
    }

    public YJVONbestResult(int i, int j, String s, double d, double d1, 
            int k, int l, int i1, double d2, double d3, 
            double d4, int j1, double ad[], double ad1[], String s1)
    {
        delimiter = " ";
        i;
        JVM INSTR tableswitch 1 1: default 28
    //                   1 334;
           goto _L1 _L2
_L1:
        super.type = YJVO_TYPE.NBEST;
_L10:
        super.result = this;
        index = j;
        uttID = s;
        resultTime = d;
        updateTime = d1;
        k;
        JVM INSTR tableswitch 1 3: default 92
    //                   1 344
    //                   2 354
    //                   3 364;
           goto _L3 _L4 _L5 _L6
_L3:
        status = YJVO_STATE_RESULT.RESULT_STATE_NORMAL;
_L11:
        filterCount = l;
        candCount = i1;
        startTime = d2;
        endTime = d3;
        RT = d4;
        j1;
        JVM INSTR tableswitch 1 2: default 152
    //                   1 374
    //                   2 384;
           goto _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_384;
_L7:
        filter = YJVO_FILTER.NORMAL;
_L12:
        phraseConfidence = ad;
        phraseProbability = ad1;
        xmlResult = s1;
        wordCount = new int[filterCount][];
        wordConfidence = (double[][][])Array.newInstance([D, new int[] {
            filterCount, candCount
        });
        wordStartTime = (double[][][])Array.newInstance([D, new int[] {
            filterCount, candCount
        });
        wordEndTime = (double[][][])Array.newInstance([D, new int[] {
            filterCount, candCount
        });
        transcribe = (String[][][])Array.newInstance([Ljava/lang/String;, new int[] {
            filterCount, candCount
        });
        pronounce = (String[][][])Array.newInstance([Ljava/lang/String;, new int[] {
            filterCount, candCount
        });
        return;
_L2:
        super.type = YJVO_TYPE.LATTICE;
          goto _L10
_L4:
        status = YJVO_STATE_RESULT.RESULT_STATE_EOD;
          goto _L11
_L5:
        status = YJVO_STATE_RESULT.RESULT_STATE_TIMEOUT;
          goto _L11
_L6:
        status = YJVO_STATE_RESULT.RESULT_STATE_PARTIAL;
          goto _L11
_L8:
        filter = YJVO_FILTER.NUMBER;
          goto _L12
        filter = YJVO_FILTER.SENTENCE;
          goto _L12
    }

    private String cat3DString(String as[][][], int i, int j)
    {
        String s = "";
        if (as != null && i >= 0 && i < filterCount && j >= 0 && j < candCount)
        {
            int k = wordCount[i][j];
            if (k > 0)
            {
                StringBuilder stringbuilder = new StringBuilder();
                int l;
                for (l = 0; l < k - 1; l++)
                {
                    stringbuilder.append(as[i][j][l]);
                    stringbuilder.append(delimiter);
                }

                stringbuilder.append(as[i][j][l]);
                s = stringbuilder.toString();
            }
        }
        return s;
    }

    private int getFilterIndex(YJVO_FILTER yjvo_filter)
    {
        static class _cls1
        {

            static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[];

            static 
            {
                $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER = new int[YJVO_FILTER.values().length];
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NORMAL.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NUMBER.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.SENTENCE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.jp.co.yahoo.android.yjvoice.YJVO_FILTER[yjvo_filter.ordinal()])
        {
        default:
            return 0;

        case 2: // '\002'
            return 1;

        case 3: // '\003'
            return 2;
        }
    }

    public static final void setDoubleArrays(Object obj, int i, int j, double ad[], double ad1[], double ad2[])
    {
        YJVONbestResult yjvonbestresult = (YJVONbestResult)obj;
        yjvonbestresult.wordConfidence[i][j] = ad;
        yjvonbestresult.wordStartTime[i][j] = ad1;
        yjvonbestresult.wordEndTime[i][j] = ad2;
    }

    public static final void setStrings(Object obj, int i, int j, int k, String s, String s1)
    {
        YJVONbestResult yjvonbestresult = (YJVONbestResult)obj;
        yjvonbestresult.transcribe[i][j][k] = s;
        yjvonbestresult.pronounce[i][j][k] = s1;
    }

    public static final void setWordCount(Object obj, int i, int ai[])
    {
        YJVONbestResult yjvonbestresult = (YJVONbestResult)obj;
        yjvonbestresult.wordCount[i] = ai;
        for (int j = 0; j < yjvonbestresult.candCount; j++)
        {
            yjvonbestresult.transcribe[i][j] = new String[yjvonbestresult.wordCount[i][j]];
            yjvonbestresult.pronounce[i][j] = new String[yjvonbestresult.wordCount[i][j]];
        }

    }

    public String[] getListPronounce()
    {
        String as[] = new String[candCount];
        if (pronounce != null && filterCount > 0 && candCount > 0)
        {
            for (int i = 0; i < as.length; i++)
            {
                as[i] = cat3DString(pronounce, getFilterIndex(filter), i);
            }

        }
        return as;
    }

    public String[] getListTranscribe()
    {
        String as[] = new String[candCount];
        if (transcribe != null && filterCount > 0 && candCount > 0)
        {
            for (int i = 0; i < as.length; i++)
            {
                as[i] = cat3DString(transcribe, getFilterIndex(filter), i);
            }

        }
        return as;
    }

    public String getPronounce(int i)
    {
        return cat3DString(pronounce, getFilterIndex(filter), i);
    }

    public String getTranscribe(int i)
    {
        return cat3DString(transcribe, getFilterIndex(filter), i);
    }

    public void setDelimiter(String s)
    {
        delimiter = s;
    }
}
