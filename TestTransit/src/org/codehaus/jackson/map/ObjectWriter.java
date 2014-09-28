// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.io.SegmentedStringWriter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.map:
//            ObjectMapper, JsonMappingException, SerializerProvider, SerializationConfig, 
//            SerializerFactory

public class ObjectWriter
    implements Versioned
{

    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    protected final SerializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final SerializerProvider _provider;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig)
    {
        _config = serializationconfig;
        _provider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _jsonFactory = objectmapper._jsonFactory;
        _rootType = null;
        _prettyPrinter = null;
        _schema = null;
    }

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig, FormatSchema formatschema)
    {
        _config = serializationconfig;
        _provider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _jsonFactory = objectmapper._jsonFactory;
        _rootType = null;
        _prettyPrinter = null;
        _schema = formatschema;
    }

    protected ObjectWriter(ObjectMapper objectmapper, SerializationConfig serializationconfig, JavaType javatype, PrettyPrinter prettyprinter)
    {
        _config = serializationconfig;
        _provider = objectmapper._serializerProvider;
        _serializerFactory = objectmapper._serializerFactory;
        _jsonFactory = objectmapper._jsonFactory;
        _rootType = javatype;
        _prettyPrinter = prettyprinter;
        _schema = null;
    }

    protected ObjectWriter(ObjectWriter objectwriter, SerializationConfig serializationconfig)
    {
        _config = serializationconfig;
        _provider = objectwriter._provider;
        _serializerFactory = objectwriter._serializerFactory;
        _jsonFactory = objectwriter._jsonFactory;
        _schema = objectwriter._schema;
        _rootType = objectwriter._rootType;
        _prettyPrinter = objectwriter._prettyPrinter;
    }

    protected ObjectWriter(ObjectWriter objectwriter, SerializationConfig serializationconfig, JavaType javatype, PrettyPrinter prettyprinter, FormatSchema formatschema)
    {
        _config = serializationconfig;
        _provider = objectwriter._provider;
        _serializerFactory = objectwriter._serializerFactory;
        _jsonFactory = objectwriter._jsonFactory;
        _rootType = javatype;
        _prettyPrinter = prettyprinter;
        _schema = formatschema;
    }

    private final void _configAndWriteCloseable(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        if (_rootType != null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        _provider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
_L1:
        if (_schema != null)
        {
            jsongenerator.setSchema(_schema);
        }
        JsonGenerator jsongenerator1;
        jsongenerator1 = jsongenerator;
        jsongenerator = null;
        jsongenerator1.close();
        Closeable closeable1;
        closeable1 = closeable;
        closeable = null;
        closeable1.close();
        Exception exception;
        SerializerProvider serializerprovider;
        JavaType javatype;
        SerializerFactory serializerfactory;
        if (false)
        {
            try
            {
                null.close();
            }
            catch (IOException ioexception3) { }
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        null.close();
        return;
        serializerprovider = _provider;
        javatype = _rootType;
        serializerfactory = _serializerFactory;
        serializerprovider.serializeValue(serializationconfig, jsongenerator, obj, javatype, serializerfactory);
          goto _L1
        exception;
        IOException ioexception2;
        if (jsongenerator != null)
        {
            try
            {
                jsongenerator.close();
            }
            catch (IOException ioexception1) { }
        }
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        ioexception2;
    }

    private final void _writeCloseableValue(JsonGenerator jsongenerator, Object obj, SerializationConfig serializationconfig)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        Closeable closeable = (Closeable)obj;
        if (_rootType != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        _provider.serializeValue(serializationconfig, jsongenerator, obj, _serializerFactory);
_L1:
        if (_config.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
        {
            jsongenerator.flush();
        }
        Closeable closeable1;
        closeable1 = closeable;
        closeable = null;
        closeable1.close();
        if (true)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        null.close();
        return;
        _provider.serializeValue(serializationconfig, jsongenerator, obj, _rootType, _serializerFactory);
          goto _L1
        Exception exception;
        exception;
        IOException ioexception1;
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        ioexception1;
    }

    protected final void _configAndWriteValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        if (_prettyPrinter == null) goto _L2; else goto _L1
_L1:
        PrettyPrinter prettyprinter = _prettyPrinter;
        if (prettyprinter == NULL_PRETTY_PRINTER)
        {
            prettyprinter = null;
        }
        jsongenerator.setPrettyPrinter(prettyprinter);
_L9:
        if (_schema != null)
        {
            jsongenerator.setSchema(_schema);
        }
        if (!_config.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) goto _L4; else goto _L3
_L3:
        _configAndWriteCloseable(jsongenerator, obj, _config);
_L6:
        return;
_L2:
        if (_config.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT))
        {
            jsongenerator.useDefaultPrettyPrinter();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        boolean flag = false;
        JavaType javatype = _rootType;
        flag = false;
        if (javatype != null)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        _provider.serializeValue(_config, jsongenerator, obj, _serializerFactory);
_L7:
        flag = true;
        jsongenerator.close();
        if (flag) goto _L6; else goto _L5
_L5:
        try
        {
            jsongenerator.close();
            return;
        }
        catch (IOException ioexception1)
        {
            return;
        }
        _provider.serializeValue(_config, jsongenerator, obj, _rootType, _serializerFactory);
          goto _L7
        Exception exception;
        exception;
        if (!flag)
        {
            try
            {
                jsongenerator.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public boolean canSerialize(Class class1)
    {
        return _provider.hasSerializerFor(_config, class1, _serializerFactory);
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectWriter withDefaultPrettyPrinter()
    {
        return withPrettyPrinter(new DefaultPrettyPrinter());
    }

    public ObjectWriter withFilters(FilterProvider filterprovider)
    {
        if (filterprovider == _config.getFilterProvider())
        {
            return this;
        } else
        {
            return new ObjectWriter(this, _config.withFilters(filterprovider));
        }
    }

    public ObjectWriter withPrettyPrinter(PrettyPrinter prettyprinter)
    {
        if (prettyprinter == _prettyPrinter)
        {
            return this;
        }
        if (prettyprinter == null)
        {
            prettyprinter = NULL_PRETTY_PRINTER;
        }
        SerializationConfig serializationconfig = _config;
        JavaType javatype = _rootType;
        FormatSchema formatschema = _schema;
        return new ObjectWriter(this, serializationconfig, javatype, prettyprinter, formatschema);
    }

    public ObjectWriter withSchema(FormatSchema formatschema)
    {
        if (_schema == formatschema)
        {
            return this;
        } else
        {
            return new ObjectWriter(this, _config, _rootType, _prettyPrinter, formatschema);
        }
    }

    public ObjectWriter withType(Class class1)
    {
        return withType(_config.constructType(class1));
    }

    public ObjectWriter withType(JavaType javatype)
    {
        if (javatype == _rootType)
        {
            return this;
        } else
        {
            return new ObjectWriter(this, _config, javatype, _prettyPrinter, _schema);
        }
    }

    public ObjectWriter withType(TypeReference typereference)
    {
        return withType(_config.getTypeFactory().constructType(typereference.getType()));
    }

    public ObjectWriter withView(Class class1)
    {
        if (class1 == _config.getSerializationView())
        {
            return this;
        } else
        {
            return new ObjectWriter(this, _config.withView(class1));
        }
    }

    public void writeValue(File file, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputstream, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(outputstream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        _configAndWriteValue(_jsonFactory.createJsonGenerator(writer), obj);
    }

    public void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        if (_config.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable))
        {
            _writeCloseableValue(jsongenerator, obj, _config);
        } else
        {
            if (_rootType == null)
            {
                _provider.serializeValue(_config, jsongenerator, obj, _serializerFactory);
            } else
            {
                _provider.serializeValue(_config, jsongenerator, obj, _rootType, _serializerFactory);
            }
            if (_config.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE))
            {
                jsongenerator.flush();
                return;
            }
        }
    }

    public byte[] writeValueAsBytes(Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        ByteArrayBuilder bytearraybuilder = new ByteArrayBuilder(_jsonFactory._getBufferRecycler());
        _configAndWriteValue(_jsonFactory.createJsonGenerator(bytearraybuilder, JsonEncoding.UTF8), obj);
        byte abyte0[] = bytearraybuilder.toByteArray();
        bytearraybuilder.release();
        return abyte0;
    }

    public String writeValueAsString(Object obj)
        throws IOException, JsonGenerationException, JsonMappingException
    {
        SegmentedStringWriter segmentedstringwriter = new SegmentedStringWriter(_jsonFactory._getBufferRecycler());
        _configAndWriteValue(_jsonFactory.createJsonGenerator(segmentedstringwriter), obj);
        return segmentedstringwriter.getAndClear();
    }

}
