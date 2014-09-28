// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.format;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.io.MergedStream;

// Referenced classes of package org.codehaus.jackson.format:
//            MatchStrength

public class DataFormatMatcher
{

    protected final byte _bufferedData[];
    protected final int _bufferedLength;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;

    protected DataFormatMatcher(InputStream inputstream, byte abyte0[], int i, JsonFactory jsonfactory, MatchStrength matchstrength)
    {
        _originalStream = inputstream;
        _bufferedData = abyte0;
        _bufferedLength = i;
        _match = jsonfactory;
        _matchStrength = matchstrength;
    }

    public JsonParser createParserWithMatch()
        throws IOException
    {
        if (_match == null)
        {
            return null;
        }
        if (_originalStream == null)
        {
            return _match.createJsonParser(_bufferedData, 0, _bufferedLength);
        } else
        {
            return _match.createJsonParser(getDataStream());
        }
    }

    public InputStream getDataStream()
    {
        if (_originalStream == null)
        {
            return new ByteArrayInputStream(_bufferedData, 0, _bufferedLength);
        } else
        {
            return new MergedStream(null, _originalStream, _bufferedData, 0, _bufferedLength);
        }
    }

    public JsonFactory getMatch()
    {
        return _match;
    }

    public MatchStrength getMatchStrength()
    {
        if (_matchStrength == null)
        {
            return MatchStrength.INCONCLUSIVE;
        } else
        {
            return _matchStrength;
        }
    }

    public String getMatchedFormatName()
    {
        return _match.getFormatName();
    }

    public boolean hasMatch()
    {
        return _match != null;
    }
}
