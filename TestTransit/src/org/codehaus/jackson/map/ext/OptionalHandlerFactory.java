// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.ext;

import java.util.Collection;
import java.util.Iterator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.codehaus.jackson.type.JavaType;

public class OptionalHandlerFactory
{

    private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
    private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLDeserializers";
    private static final String DESERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String PACKAGE_PREFIX_JODA_DATETIME = "org.joda.time.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "org.codehaus.jackson.map.ext.CoreXMLSerializers";
    private static final String SERIALIZERS_FOR_JODA_DATETIME = "org.codehaus.jackson.map.ext.JodaSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "org.codehaus.jackson.map.ext.DOMSerializer";
    public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();

    protected OptionalHandlerFactory()
    {
    }

    private boolean doesImplement(Class class1, String s)
    {
        for (Class class2 = class1; class2 != null; class2 = class2.getSuperclass())
        {
            while (class2.getName().equals(s) || hasInterface(class2, s)) 
            {
                return true;
            }
        }

        return false;
    }

    private boolean hasInterface(Class class1, String s)
    {
        Class aclass[];
        int i;
        int j;
        aclass = class1.getInterfaces();
        i = aclass.length;
        j = 0;
_L7:
        if (j >= i) goto _L2; else goto _L1
_L1:
        if (!aclass[j].getName().equals(s)) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        int k = aclass.length;
        int l = 0;
label0:
        do
        {
label1:
            {
                if (l >= k)
                {
                    break label1;
                }
                if (hasInterface(aclass[l], s))
                {
                    break label0;
                }
                l++;
            }
        } while (true);
        if (true) goto _L3; else goto _L5
_L5:
        return false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private boolean hasInterfaceStartingWith(Class class1, String s)
    {
        Class aclass[];
        int i;
        int j;
        aclass = class1.getInterfaces();
        i = aclass.length;
        j = 0;
_L7:
        if (j >= i) goto _L2; else goto _L1
_L1:
        if (!aclass[j].getName().startsWith(s)) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        int k = aclass.length;
        int l = 0;
label0:
        do
        {
label1:
            {
                if (l >= k)
                {
                    break label1;
                }
                if (hasInterfaceStartingWith(aclass[l], s))
                {
                    break label0;
                }
                l++;
            }
        } while (true);
        if (true) goto _L3; else goto _L5
_L5:
        return false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private boolean hasSupertypeStartingWith(Class class1, String s)
    {
        Class class2 = class1.getSuperclass();
_L7:
        if (class2 == null) goto _L2; else goto _L1
_L1:
        if (!class2.getName().startsWith(s)) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        class2 = class2.getSuperclass();
        continue; /* Loop/switch isn't completed */
_L2:
        Class class3 = class1;
label0:
        do
        {
label1:
            {
                if (class3 == null)
                {
                    break label1;
                }
                if (hasInterfaceStartingWith(class3, s))
                {
                    break label0;
                }
                class3 = class3.getSuperclass();
            }
        } while (true);
        if (true) goto _L3; else goto _L5
_L5:
        return false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private Object instantiate(String s)
    {
        Object obj = Class.forName(s).newInstance();
        return obj;
        Exception exception;
        exception;
_L2:
        return null;
        LinkageError linkageerror;
        linkageerror;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public JsonDeserializer findDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, DeserializerProvider deserializerprovider)
    {
        Class class1;
        String s;
        class1 = javatype.getRawClass();
        s = class1.getName();
        if (!s.startsWith("org.joda.time.")) goto _L2; else goto _L1
_L1:
        String s1 = "org.codehaus.jackson.map.ext.JodaDeserializers";
_L9:
        Object obj = instantiate(s1);
        if (obj != null) goto _L4; else goto _L3
_L3:
        return null;
_L2:
        if (s.startsWith("javax.xml.") || hasSupertypeStartingWith(class1, "javax.xml."))
        {
            s1 = "org.codehaus.jackson.map.ext.CoreXMLDeserializers";
            continue; /* Loop/switch isn't completed */
        }
        if (doesImplement(class1, "org.w3c.dom.Node"))
        {
            return (JsonDeserializer)instantiate("org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer");
        }
        if (doesImplement(class1, "org.w3c.dom.Node"))
        {
            return (JsonDeserializer)instantiate("org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer");
        }
        if (true) goto _L3; else goto _L4
_L4:
        Iterator iterator1;
        Collection collection = ((Provider)obj).provide();
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            StdDeserializer stddeserializer1 = (StdDeserializer)iterator.next();
            if (class1 == stddeserializer1.getValueClass())
            {
                return stddeserializer1;
            }
        }

        iterator1 = collection.iterator();
_L7:
        if (!iterator1.hasNext()) goto _L3; else goto _L5
_L5:
        StdDeserializer stddeserializer = (StdDeserializer)iterator1.next();
        if (!stddeserializer.getValueClass().isAssignableFrom(class1)) goto _L7; else goto _L6
_L6:
        return stddeserializer;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public JsonSerializer findSerializer(SerializationConfig serializationconfig, JavaType javatype)
    {
        Class class1;
        String s;
        class1 = javatype.getRawClass();
        s = class1.getName();
        if (!s.startsWith("org.joda.time.")) goto _L2; else goto _L1
_L1:
        String s1 = "org.codehaus.jackson.map.ext.JodaSerializers";
_L4:
        Object obj;
        obj = instantiate(s1);
        if (obj == null)
        {
            return null;
        }
        break MISSING_BLOCK_LABEL_89;
_L2:
        if (!s.startsWith("javax.xml.") && !hasSupertypeStartingWith(class1, "javax.xml."))
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = "org.codehaus.jackson.map.ext.CoreXMLSerializers";
        if (true) goto _L4; else goto _L3
_L3:
        if (doesImplement(class1, "org.w3c.dom.Node"))
        {
            return (JsonSerializer)instantiate("org.codehaus.jackson.map.ext.DOMSerializer");
        } else
        {
            return null;
        }
        Collection collection = ((Provider)obj).provide();
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
            if (class1 == entry1.getKey())
            {
                return (JsonSerializer)entry1.getValue();
            }
        }

        for (Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            if (((Class)entry.getKey()).isAssignableFrom(class1))
            {
                return (JsonSerializer)entry.getValue();
            }
        }

        return null;
    }

}
