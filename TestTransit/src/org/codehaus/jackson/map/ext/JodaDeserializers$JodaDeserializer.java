// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.deser.StdScalarDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

// Referenced classes of package org.codehaus.jackson.map.ext:
//            JodaDeserializers

static abstract class  extends StdScalarDeserializer
{

    static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

    protected DateTime parseLocal(JsonParser jsonparser)
        throws IOException, JsonProcessingException
    {
        String s = jsonparser.getText().trim();
        if (s.length() == 0)
        {
            return null;
        } else
        {
            return _localDateTimeFormat.parseDateTime(s);
        }
    }


    protected (Class class1)
    {
        super(class1);
    }
}
