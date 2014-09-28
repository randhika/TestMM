// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;

public class JsonMappingException extends JsonProcessingException
{
    public static class Reference
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        protected String _fieldName;
        protected Object _from;
        protected int _index;

        public String getFieldName()
        {
            return _fieldName;
        }

        public Object getFrom()
        {
            return _from;
        }

        public int getIndex()
        {
            return _index;
        }

        public void setFieldName(String s)
        {
            _fieldName = s;
        }

        public void setFrom(Object obj)
        {
            _from = obj;
        }

        public void setIndex(int i)
        {
            _index = i;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            Class class1;
            Package package1;
            if (_from instanceof Class)
            {
                class1 = (Class)_from;
            } else
            {
                class1 = _from.getClass();
            }
            package1 = class1.getPackage();
            if (package1 != null)
            {
                stringbuilder.append(package1.getName());
                stringbuilder.append('.');
            }
            stringbuilder.append(class1.getSimpleName());
            stringbuilder.append('[');
            if (_fieldName != null)
            {
                stringbuilder.append('"');
                stringbuilder.append(_fieldName);
                stringbuilder.append('"');
            } else
            if (_index >= 0)
            {
                stringbuilder.append(_index);
            } else
            {
                stringbuilder.append('?');
            }
            stringbuilder.append(']');
            return stringbuilder.toString();
        }

        protected Reference()
        {
            _index = -1;
        }

        public Reference(Object obj)
        {
            _index = -1;
            _from = obj;
        }

        public Reference(Object obj, int i)
        {
            _index = -1;
            _from = obj;
            _index = i;
        }

        public Reference(Object obj, String s)
        {
            _index = -1;
            _from = obj;
            if (s == null)
            {
                throw new NullPointerException("Can not pass null fieldName");
            } else
            {
                _fieldName = s;
                return;
            }
        }
    }


    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1L;
    protected LinkedList _path;

    public JsonMappingException(String s)
    {
        super(s);
    }

    public JsonMappingException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public JsonMappingException(String s, JsonLocation jsonlocation)
    {
        super(s, jsonlocation);
    }

    public JsonMappingException(String s, JsonLocation jsonlocation, Throwable throwable)
    {
        super(s, jsonlocation, throwable);
    }

    public static JsonMappingException from(JsonParser jsonparser, String s)
    {
        return new JsonMappingException(s, jsonparser.getTokenLocation());
    }

    public static JsonMappingException from(JsonParser jsonparser, String s, Throwable throwable)
    {
        return new JsonMappingException(s, jsonparser.getTokenLocation(), throwable);
    }

    public static JsonMappingException wrapWithPath(Throwable throwable, Object obj, int i)
    {
        return wrapWithPath(throwable, new Reference(obj, i));
    }

    public static JsonMappingException wrapWithPath(Throwable throwable, Object obj, String s)
    {
        return wrapWithPath(throwable, new Reference(obj, s));
    }

    public static JsonMappingException wrapWithPath(Throwable throwable, Reference reference)
    {
        JsonMappingException jsonmappingexception;
        if (throwable instanceof JsonMappingException)
        {
            jsonmappingexception = (JsonMappingException)throwable;
        } else
        {
            String s = throwable.getMessage();
            if (s == null || s.length() == 0)
            {
                s = (new StringBuilder()).append("(was ").append(throwable.getClass().getName()).append(")").toString();
            }
            jsonmappingexception = new JsonMappingException(s, null, throwable);
        }
        jsonmappingexception.prependPath(reference);
        return jsonmappingexception;
    }

    protected void _appendPathDesc(StringBuilder stringbuilder)
    {
        Iterator iterator = _path.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuilder.append(((Reference)iterator.next()).toString());
            if (iterator.hasNext())
            {
                stringbuilder.append("->");
            }
        } while (true);
    }

    public String getMessage()
    {
        String s = super.getMessage();
        if (_path == null)
        {
            return s;
        }
        StringBuilder stringbuilder;
        if (s == null)
        {
            stringbuilder = new StringBuilder();
        } else
        {
            stringbuilder = new StringBuilder(s);
        }
        stringbuilder.append(" (through reference chain: ");
        _appendPathDesc(stringbuilder);
        stringbuilder.append(')');
        return stringbuilder.toString();
    }

    public List getPath()
    {
        if (_path == null)
        {
            return Collections.emptyList();
        } else
        {
            return Collections.unmodifiableList(_path);
        }
    }

    public void prependPath(Object obj, int i)
    {
        prependPath(new Reference(obj, i));
    }

    public void prependPath(Object obj, String s)
    {
        prependPath(new Reference(obj, s));
    }

    public void prependPath(Reference reference)
    {
        if (_path == null)
        {
            _path = new LinkedList();
        }
        if (_path.size() < 1000)
        {
            _path.addFirst(reference);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(": ").append(getMessage()).toString();
    }
}
