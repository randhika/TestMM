// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson;

import java.io.Serializable;

public class JsonLocation
    implements Serializable
{

    public static final JsonLocation NA = new JsonLocation("N/A", -1L, -1L, -1, -1);
    private static final long serialVersionUID = 1L;
    final int _columnNr;
    final int _lineNr;
    final Object _sourceRef;
    final long _totalBytes;
    final long _totalChars;

    public JsonLocation(Object obj, long l, int i, int j)
    {
        this(obj, -1L, l, i, j);
    }

    public JsonLocation(Object obj, long l, long l1, int i, int j)
    {
        _sourceRef = obj;
        _totalBytes = l;
        _totalChars = l1;
        _lineNr = i;
        _columnNr = j;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (obj != this) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = false;
        if (obj == null) goto _L4; else goto _L3
_L3:
        boolean flag2;
        flag2 = obj instanceof JsonLocation;
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        JsonLocation jsonlocation;
        Object obj1;
        jsonlocation = (JsonLocation)obj;
        if (_sourceRef != null)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        obj1 = jsonlocation._sourceRef;
        flag1 = false;
        if (obj1 != null) goto _L4; else goto _L6
_L6:
        if (_lineNr != jsonlocation._lineNr || _columnNr != jsonlocation._columnNr || _totalChars != jsonlocation._totalChars || getByteOffset() != jsonlocation.getByteOffset())
        {
            flag = false;
        }
        return flag;
        if (!_sourceRef.equals(jsonlocation._sourceRef))
        {
            return false;
        }
          goto _L6
    }

    public long getByteOffset()
    {
        return _totalBytes;
    }

    public long getCharOffset()
    {
        return _totalChars;
    }

    public int getColumnNr()
    {
        return _columnNr;
    }

    public int getLineNr()
    {
        return _lineNr;
    }

    public Object getSourceRef()
    {
        return _sourceRef;
    }

    public int hashCode()
    {
        int i;
        if (_sourceRef == null)
        {
            i = 1;
        } else
        {
            i = _sourceRef.hashCode();
        }
        return ((i ^ _lineNr) + _columnNr ^ (int)_totalChars) + (int)_totalBytes;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(80);
        stringbuilder.append("[Source: ");
        if (_sourceRef == null)
        {
            stringbuilder.append("UNKNOWN");
        } else
        {
            stringbuilder.append(_sourceRef.toString());
        }
        stringbuilder.append("; line: ");
        stringbuilder.append(_lineNr);
        stringbuilder.append(", column: ");
        stringbuilder.append(_columnNr);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

}
