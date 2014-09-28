// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.jaxrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.jaxrs:
//            Annotations, MapperConfigurator

public class JacksonJsonProvider
    implements MessageBodyReader, MessageBodyWriter, Versioned
{

    public static final Annotations BASIC_ANNOTATIONS[];
    public static final Class _unreadableClasses[] = {
        java/io/InputStream, java/io/Reader
    };
    public static final HashSet _untouchables;
    public static final Class _unwritableClasses[] = {
        java/io/OutputStream, java/io/Writer, javax/ws/rs/core/StreamingOutput, javax/ws/rs/core/Response
    };
    protected boolean _cfgCheckCanDeserialize;
    protected boolean _cfgCheckCanSerialize;
    protected HashSet _cfgCustomUntouchables;
    protected String _jsonpFunctionName;
    protected final MapperConfigurator _mapperConfig;
    protected Providers _providers;

    public JacksonJsonProvider()
    {
        this(null, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(ObjectMapper objectmapper)
    {
        this(objectmapper, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(ObjectMapper objectmapper, Annotations aannotations[])
    {
        _cfgCheckCanSerialize = false;
        _cfgCheckCanDeserialize = false;
        _mapperConfig = new MapperConfigurator(objectmapper, aannotations);
    }

    public transient JacksonJsonProvider(Annotations aannotations[])
    {
        this(null, aannotations);
    }

    protected static boolean _containedIn(Class class1, HashSet hashset)
    {
label0:
        {
            if (hashset == null)
            {
                break label0;
            }
            ClassKey classkey = new ClassKey(class1);
            if (hashset.contains(classkey))
            {
                return true;
            }
            Iterator iterator = ClassUtil.findSuperTypes(class1, null).iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                classkey.reset((Class)iterator.next());
            } while (!hashset.contains(classkey));
            return true;
        }
        return false;
    }

    public void addUntouchable(Class class1)
    {
        if (_cfgCustomUntouchables == null)
        {
            _cfgCustomUntouchables = new HashSet();
        }
        _cfgCustomUntouchables.add(new ClassKey(class1));
    }

    public void checkCanDeserialize(boolean flag)
    {
        _cfgCheckCanDeserialize = flag;
    }

    public void checkCanSerialize(boolean flag)
    {
        _cfgCheckCanSerialize = flag;
    }

    public JacksonJsonProvider configure(org.codehaus.jackson.JsonGenerator.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, flag);
        return this;
    }

    public JacksonJsonProvider configure(org.codehaus.jackson.JsonParser.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, flag);
        return this;
    }

    public JacksonJsonProvider configure(org.codehaus.jackson.map.DeserializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, flag);
        return this;
    }

    public JacksonJsonProvider configure(org.codehaus.jackson.map.SerializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, flag);
        return this;
    }

    public JacksonJsonProvider disable(org.codehaus.jackson.JsonGenerator.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(org.codehaus.jackson.JsonParser.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(org.codehaus.jackson.map.DeserializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(org.codehaus.jackson.map.SerializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider enable(org.codehaus.jackson.JsonGenerator.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(org.codehaus.jackson.JsonParser.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(org.codehaus.jackson.map.DeserializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(org.codehaus.jackson.map.SerializationConfig.Feature feature, boolean flag)
    {
        _mapperConfig.configure(feature, true);
        return this;
    }

    protected JsonEncoding findEncoding(MediaType mediatype, MultivaluedMap multivaluedmap)
    {
        return JsonEncoding.UTF8;
    }

    public long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
    {
        return -1L;
    }

    protected boolean isJsonType(MediaType mediatype)
    {
label0:
        {
            if (mediatype != null)
            {
                String s = mediatype.getSubtype();
                if (!"json".equalsIgnoreCase(s) && !s.endsWith("+json"))
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
    {
_L2:
        return false;
        if (!isJsonType(mediatype) || _untouchables.contains(new ClassKey(class1))) goto _L2; else goto _L1
_L1:
        Class aclass[];
        int i;
        int j;
        aclass = _unreadableClasses;
        i = aclass.length;
        j = 0;
_L4:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (aclass[j].isAssignableFrom(class1)) goto _L2; else goto _L3
_L3:
        j++;
          goto _L4
        if (_containedIn(class1, _cfgCustomUntouchables)) goto _L2; else goto _L5
_L5:
        ObjectMapper objectmapper;
        if (!_cfgCheckCanSerialize)
        {
            break; /* Loop/switch isn't completed */
        }
        objectmapper = locateMapper(class1, mediatype);
        if (!objectmapper.canDeserialize(objectmapper.constructType(class1))) goto _L2; else goto _L6
_L6:
        return true;
    }

    public boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
    {
_L2:
        return false;
        if (!isJsonType(mediatype) || _untouchables.contains(new ClassKey(class1))) goto _L2; else goto _L1
_L1:
        Class aclass[];
        int i;
        int j;
        aclass = _unwritableClasses;
        i = aclass.length;
        j = 0;
_L4:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (aclass[j].isAssignableFrom(class1)) goto _L2; else goto _L3
_L3:
        j++;
          goto _L4
        if (_containedIn(class1, _cfgCustomUntouchables) || _cfgCheckCanSerialize && !locateMapper(class1, mediatype).canSerialize(class1)) goto _L2; else goto _L5
_L5:
        return true;
    }

    public ObjectMapper locateMapper(Class class1, MediaType mediatype)
    {
        ObjectMapper objectmapper = _mapperConfig.getConfiguredMapper();
        if (objectmapper == null)
        {
            if (_providers != null)
            {
                ContextResolver contextresolver = _providers.getContextResolver(org/codehaus/jackson/map/ObjectMapper, mediatype);
                if (contextresolver == null)
                {
                    contextresolver = _providers.getContextResolver(org/codehaus/jackson/map/ObjectMapper, null);
                }
                if (contextresolver != null)
                {
                    objectmapper = (ObjectMapper)contextresolver.getContext(class1);
                }
            }
            if (objectmapper == null)
            {
                objectmapper = _mapperConfig.getDefaultMapper();
            }
        }
        return objectmapper;
    }

    public Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
        throws IOException
    {
        ObjectMapper objectmapper = locateMapper(class1, mediatype);
        JsonParser jsonparser = objectmapper.getJsonFactory().createJsonParser(inputstream);
        jsonparser.disable(org.codehaus.jackson.JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return objectmapper.readValue(jsonparser, objectmapper.constructType(type));
    }

    public void setAnnotationsToUse(Annotations aannotations[])
    {
        _mapperConfig.setAnnotationsToUse(aannotations);
    }

    public void setJSONPFunctionName(String s)
    {
        _jsonpFunctionName = s;
    }

    public void setMapper(ObjectMapper objectmapper)
    {
        _mapperConfig.setMapper(objectmapper);
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
        throws IOException
    {
        ObjectMapper objectmapper = locateMapper(class1, mediatype);
        JsonEncoding jsonencoding = findEncoding(mediatype, multivaluedmap);
        JsonGenerator jsongenerator = objectmapper.getJsonFactory().createJsonGenerator(outputstream, jsonencoding);
        jsongenerator.disable(org.codehaus.jackson.JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        if (objectmapper.getSerializationConfig().isEnabled(org.codehaus.jackson.map.SerializationConfig.Feature.INDENT_OUTPUT))
        {
            jsongenerator.useDefaultPrettyPrinter();
        }
        JavaType javatype = null;
        if (type != null)
        {
            javatype = null;
            if (obj != null)
            {
                Class class2 = type.getClass();
                javatype = null;
                if (class2 != java/lang/Class)
                {
                    javatype = objectmapper.getTypeFactory().constructType(type);
                    if (javatype.getRawClass() == java/lang/Object)
                    {
                        javatype = null;
                    }
                }
            }
        }
        if (_jsonpFunctionName != null)
        {
            objectmapper.writeValue(jsongenerator, new JSONPObject(_jsonpFunctionName, obj, javatype));
            return;
        }
        if (javatype != null)
        {
            objectmapper.typedWriter(javatype).writeValue(jsongenerator, obj);
            return;
        } else
        {
            objectmapper.writeValue(jsongenerator, obj);
            return;
        }
    }

    static 
    {
        Annotations aannotations[] = new Annotations[1];
        aannotations[0] = Annotations.JACKSON;
        BASIC_ANNOTATIONS = aannotations;
        _untouchables = new HashSet();
        _untouchables.add(new ClassKey(java/io/InputStream));
        _untouchables.add(new ClassKey(java/io/Reader));
        _untouchables.add(new ClassKey(java/io/OutputStream));
        _untouchables.add(new ClassKey(java/io/Writer));
        _untouchables.add(new ClassKey([B));
        _untouchables.add(new ClassKey([C));
        _untouchables.add(new ClassKey(java/lang/String));
        _untouchables.add(new ClassKey(javax/ws/rs/core/StreamingOutput));
        _untouchables.add(new ClassKey(javax/ws/rs/core/Response));
    }
}
