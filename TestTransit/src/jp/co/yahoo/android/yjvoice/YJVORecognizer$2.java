// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice;


// Referenced classes of package jp.co.yahoo.android.yjvoice:
//            YJVORecognizer, YJVO_ACCEPT, YJVO_SENSOR, YJVO_FILTER, 
//            YJVO_TYPE, YJVO_DOMAIN, YJVO_MODE, YJVO_SAMPLERATE

static class 
{

    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[];
    static final int $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[];

    static 
    {
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT = new int[YJVO_ACCEPT.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_ACCEPT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_POSITIVE.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_NEITHER.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_NEGATIVE.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_ACCEPT[YJVO_ACCEPT.ACCEPT_REJECT.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR = new int[YJVO_SENSOR.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[YJVO_SENSOR.SENSOR_NONE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SENSOR[YJVO_SENSOR.SENSOR_GPS.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER = new int[YJVO_FILTER.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NORMAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.NUMBER.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_FILTER[YJVO_FILTER.SENTENCE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE = new int[YJVO_TYPE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[YJVO_TYPE.NBEST.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_TYPE[YJVO_TYPE.LATTICE.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN = new int[YJVO_DOMAIN.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH_16K.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK_16K.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION_16K.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.SEARCH_8K.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.TALK_8K.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_DOMAIN[YJVO_DOMAIN.DICTATION_8K.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE = new int[YJVO_MODE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[YJVO_MODE.PHRASE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror21) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_MODE[YJVO_MODE.CONTINUOUS.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror22) { }
        $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE = new int[YJVO_SAMPLERATE.values().length];
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_8000.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror23) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_11025.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror24) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_16000.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror25) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_22050.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror26) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_32000.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror27) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_44100.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror28) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_48000.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror29) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_88200.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror30) { }
        try
        {
            $SwitchMap$jp$co$yahoo$android$yjvoice$YJVO_SAMPLERATE[YJVO_SAMPLERATE.SAMPLERATE_96000.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror31)
        {
            return;
        }
    }
}
