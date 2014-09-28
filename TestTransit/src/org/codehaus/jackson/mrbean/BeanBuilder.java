// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.mrbean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.org.objectweb.asm.ClassWriter;
import org.codehaus.jackson.org.objectweb.asm.FieldVisitor;
import org.codehaus.jackson.org.objectweb.asm.MethodVisitor;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.type.JavaType;

// Referenced classes of package org.codehaus.jackson.mrbean:
//            BeanUtil

public class BeanBuilder
{
    private static class Property
    {

        protected final String _fieldName;
        protected Method _getter;
        protected final String _name;
        protected Method _setter;

        private TypeDescription getterType(TypeFactory typefactory)
        {
            Class class1 = _getter.getDeclaringClass();
            return new TypeDescription(typefactory.constructType(_getter.getGenericReturnType(), class1));
        }

        private TypeDescription setterType(TypeFactory typefactory)
        {
            Class class1 = _setter.getDeclaringClass();
            return new TypeDescription(typefactory.constructType(_setter.getGenericParameterTypes()[0], class1));
        }

        public String getFieldName()
        {
            return _fieldName;
        }

        public Method getGetter()
        {
            return _getter;
        }

        public String getName()
        {
            return _name;
        }

        public Method getSetter()
        {
            return _setter;
        }

        public boolean hasConcreteGetter()
        {
            return _getter != null && BeanUtil.isConcrete(_getter);
        }

        public boolean hasConcreteSetter()
        {
            return _setter != null && BeanUtil.isConcrete(_setter);
        }

        public TypeDescription selectType(TypeFactory typefactory)
        {
            TypeDescription typedescription2;
            if (_getter == null)
            {
                typedescription2 = setterType(typefactory);
            } else
            {
                if (_setter == null)
                {
                    return getterType(typefactory);
                }
                TypeDescription typedescription = setterType(typefactory);
                TypeDescription typedescription1 = getterType(typefactory);
                typedescription2 = TypeDescription.moreSpecificType(typedescription, typedescription1);
                if (typedescription2 == null)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Invalid property '").append(getName()).append("': incompatible types for getter/setter (").append(typedescription1).append(" vs ").append(typedescription).append(")").toString());
                }
            }
            return typedescription2;
        }

        public void setGetter(Method method)
        {
            _getter = method;
        }

        public void setSetter(Method method)
        {
            _setter = method;
        }

        public Property(String s)
        {
            _name = s;
            _fieldName = (new StringBuilder()).append("_").append(s).toString();
        }
    }

    private static class TypeDescription
    {

        private final Type _asmType;
        private JavaType _jacksonType;

        public static TypeDescription moreSpecificType(TypeDescription typedescription, TypeDescription typedescription1)
        {
            Class class1 = typedescription.getRawClass();
            Class class2 = typedescription1.getRawClass();
            if (class1.isAssignableFrom(class2))
            {
                return typedescription1;
            }
            if (class2.isAssignableFrom(class1))
            {
                return typedescription;
            } else
            {
                return null;
            }
        }

        public String erasedSignature()
        {
            return _jacksonType.getErasedSignature();
        }

        public String genericSignature()
        {
            return _jacksonType.getGenericSignature();
        }

        public int getLoadOpcode()
        {
            return _asmType.getOpcode(21);
        }

        public Class getRawClass()
        {
            return _jacksonType.getRawClass();
        }

        public int getReturnOpcode()
        {
            return _asmType.getOpcode(172);
        }

        public boolean hasGenerics()
        {
            return _jacksonType.hasGenericTypes();
        }

        public String toString()
        {
            return _jacksonType.toString();
        }

        public TypeDescription(JavaType javatype)
        {
            _jacksonType = javatype;
            _asmType = Type.getType(javatype.getRawClass());
        }
    }


    protected Map _beanProperties;
    protected final Class _implementedType;
    protected final TypeFactory _typeFactory;
    protected LinkedHashMap _unsupportedMethods;

    public BeanBuilder(DeserializationConfig deserializationconfig, Class class1)
    {
        _beanProperties = new LinkedHashMap();
        _unsupportedMethods = new LinkedHashMap();
        _implementedType = class1;
        _typeFactory = deserializationconfig.getTypeFactory();
    }

    private void addGetter(Method method)
    {
        Property property = findProperty(getPropertyName(method.getName()));
        if (property.getGetter() == null)
        {
            property.setGetter(method);
        }
    }

    private void addSetter(Method method)
    {
        Property property = findProperty(getPropertyName(method.getName()));
        if (property.getSetter() == null)
        {
            property.setSetter(method);
        }
    }

    private static String buildGetterName(String s)
    {
        return (new StringBuilder()).append("get").append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString();
    }

    private static String buildSetterName(String s)
    {
        return (new StringBuilder()).append("set").append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString();
    }

    private static void createField(ClassWriter classwriter, Property property, TypeDescription typedescription)
    {
        String s;
        String s1;
        if (typedescription.hasGenerics())
        {
            s = typedescription.genericSignature();
        } else
        {
            s = null;
        }
        s1 = typedescription.erasedSignature();
        classwriter.visitField(1, property.getFieldName(), s1, s, null).visitEnd();
    }

    private static void createGetter(ClassWriter classwriter, String s, Property property, TypeDescription typedescription)
    {
        Method method = property.getGetter();
        String s1;
        String s2;
        String s3;
        MethodVisitor methodvisitor;
        if (method != null)
        {
            s1 = Type.getMethodDescriptor(method);
            s2 = method.getName();
        } else
        {
            s1 = (new StringBuilder()).append("()").append(typedescription.erasedSignature()).toString();
            s2 = buildGetterName(property.getName());
        }
        if (typedescription.hasGenerics())
        {
            s3 = (new StringBuilder()).append("()").append(typedescription.genericSignature()).toString();
        } else
        {
            s3 = null;
        }
        methodvisitor = classwriter.visitMethod(1, s2, s1, s3, null);
        methodvisitor.visitCode();
        methodvisitor.visitVarInsn(25, 0);
        methodvisitor.visitFieldInsn(180, s, property.getFieldName(), typedescription.erasedSignature());
        methodvisitor.visitInsn(typedescription.getReturnOpcode());
        methodvisitor.visitMaxs(0, 0);
        methodvisitor.visitEnd();
    }

    private static void createSetter(ClassWriter classwriter, String s, Property property, TypeDescription typedescription)
    {
        Method method = property.getSetter();
        String s1;
        String s2;
        String s3;
        MethodVisitor methodvisitor;
        if (method != null)
        {
            s1 = Type.getMethodDescriptor(method);
            s2 = method.getName();
        } else
        {
            s1 = (new StringBuilder()).append("(").append(typedescription.erasedSignature()).append(")V").toString();
            s2 = buildSetterName(property.getName());
        }
        if (typedescription.hasGenerics())
        {
            s3 = (new StringBuilder()).append("(").append(typedescription.genericSignature()).append(")V").toString();
        } else
        {
            s3 = null;
        }
        methodvisitor = classwriter.visitMethod(1, s2, s1, s3, null);
        methodvisitor.visitCode();
        methodvisitor.visitVarInsn(25, 0);
        methodvisitor.visitVarInsn(typedescription.getLoadOpcode(), 1);
        methodvisitor.visitFieldInsn(181, s, property.getFieldName(), typedescription.erasedSignature());
        methodvisitor.visitInsn(177);
        methodvisitor.visitMaxs(0, 0);
        methodvisitor.visitEnd();
    }

    private static void createUnimplementedMethod(ClassWriter classwriter, String s, Method method)
    {
        String s1 = getInternalClassName(java/lang/UnsupportedOperationException.getName());
        String s2 = Type.getMethodDescriptor(method);
        String s3 = method.getName();
        MethodVisitor methodvisitor = classwriter.visitMethod(1, s3, s2, null, null);
        methodvisitor.visitTypeInsn(187, s1);
        methodvisitor.visitInsn(89);
        methodvisitor.visitLdcInsn((new StringBuilder()).append("Unimplemented method '").append(s3).append("' (not a setter/getter, could not materialize)").toString());
        methodvisitor.visitMethodInsn(183, s1, "<init>", "(Ljava/lang/String;)V");
        methodvisitor.visitInsn(191);
        methodvisitor.visitMaxs(0, 0);
        methodvisitor.visitEnd();
    }

    private Property findProperty(String s)
    {
        Property property = (Property)_beanProperties.get(s);
        if (property == null)
        {
            property = new Property(s);
            _beanProperties.put(s, property);
        }
        return property;
    }

    private static void generateDefaultConstructor(ClassWriter classwriter, String s)
    {
        MethodVisitor methodvisitor = classwriter.visitMethod(1, "<init>", "()V", null, null);
        methodvisitor.visitCode();
        methodvisitor.visitVarInsn(25, 0);
        methodvisitor.visitMethodInsn(183, s, "<init>", "()V");
        methodvisitor.visitInsn(177);
        methodvisitor.visitMaxs(0, 0);
        methodvisitor.visitEnd();
    }

    private static String getInternalClassName(String s)
    {
        return s.replace(".", "/");
    }

    private static String getPropertyName(String s)
    {
        byte byte0;
        String s1;
        StringBuilder stringbuilder;
        if (s.startsWith("is"))
        {
            byte0 = 2;
        } else
        {
            byte0 = 3;
        }
        s1 = s.substring(byte0);
        stringbuilder = new StringBuilder(s1);
        stringbuilder.setCharAt(0, Character.toLowerCase(s1.charAt(0)));
        return stringbuilder.toString();
    }

    private static final boolean returnsBoolean(Method method)
    {
        Class class1 = method.getReturnType();
        return class1 == java/lang/Boolean || class1 == Boolean.TYPE;
    }

    public byte[] build(String s)
    {
        ClassWriter classwriter = new ClassWriter(1);
        String s1 = getInternalClassName(s);
        String s2 = getInternalClassName(_implementedType.getName());
        String s3;
        Iterator iterator;
        if (_implementedType.isInterface())
        {
            s3 = "java/lang/Object";
            classwriter.visit(49, 33, s1, null, s3, new String[] {
                s2
            });
        } else
        {
            s3 = s2;
            classwriter.visit(49, 33, s1, null, s2, null);
        }
        classwriter.visitSource((new StringBuilder()).append(s).append(".java").toString(), null);
        generateDefaultConstructor(classwriter, s3);
        iterator = _beanProperties.values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Property property = (Property)iterator.next();
            TypeDescription typedescription = property.selectType(_typeFactory);
            createField(classwriter, property, typedescription);
            if (!property.hasConcreteGetter())
            {
                createGetter(classwriter, s1, property, typedescription);
            }
            if (!property.hasConcreteSetter())
            {
                createSetter(classwriter, s1, property, typedescription);
            }
        } while (true);
        for (Iterator iterator1 = _unsupportedMethods.values().iterator(); iterator1.hasNext(); createUnimplementedMethod(classwriter, s1, (Method)iterator1.next())) { }
        classwriter.visitEnd();
        return classwriter.toByteArray();
    }

    public BeanBuilder implement(boolean flag)
    {
        Iterator iterator;
        ArrayList arraylist = new ArrayList();
        arraylist.add(_implementedType);
        BeanUtil.findSuperTypes(_implementedType, java/lang/Object, arraylist);
        iterator = arraylist.iterator();
_L4:
        Method amethod[];
        int i;
        int j;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_240;
        }
        amethod = ((Class)iterator.next()).getDeclaredMethods();
        i = amethod.length;
        j = 0;
_L1:
        Method method;
        String s;
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
label0:
        {
            method = amethod[j];
            s = method.getName();
            int k = method.getParameterTypes().length;
            if (k == 0)
            {
                if (!s.startsWith("get") && (!s.startsWith("is") || !returnsBoolean(method)))
                {
                    break label0;
                }
                addGetter(method);
            } else
            {
                if (k != 1 || !s.startsWith("set"))
                {
                    break label0;
                }
                addSetter(method);
            }
        }
_L2:
        j++;
          goto _L1
        continue; /* Loop/switch isn't completed */
        if (!BeanUtil.isConcrete(method) && !_unsupportedMethods.containsKey(s))
        {
            if (flag)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unrecognized abstract method '").append(s).append("' (not a getter or setter) -- to avoid exception, disable AbstractTypeMaterializer.Feature.FAIL_ON_UNMATERIALIZED_METHOD").toString());
            }
            _unsupportedMethods.put(s, method);
        }
          goto _L2
        return this;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
