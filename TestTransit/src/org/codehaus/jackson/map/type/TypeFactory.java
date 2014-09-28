// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

// Referenced classes of package org.codehaus.jackson.map.type:
//            TypeParser, CollectionType, HierarchicType, MapType, 
//            TypeBindings, TypeModifier, ArrayType, SimpleType, 
//            CollectionLikeType, MapLikeType

public final class TypeFactory
{

    private static final JavaType NO_TYPES[] = new JavaType[0];
    public static final TypeFactory instance = new TypeFactory();
    protected final TypeModifier _modifiers[];
    protected final TypeParser _parser;

    private TypeFactory()
    {
        _parser = new TypeParser(this);
        _modifiers = null;
    }

    protected TypeFactory(TypeParser typeparser, TypeModifier atypemodifier[])
    {
        _parser = typeparser;
        _modifiers = atypemodifier;
    }

    private JavaType _collectionType(Class class1)
    {
        JavaType ajavatype[] = findTypeParameters(class1, java/util/Collection);
        if (ajavatype == null)
        {
            return CollectionType.construct(class1, _unknownType());
        }
        if (ajavatype.length != 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Strange Collection type ").append(class1.getName()).append(": can not determine type parameters").toString());
        } else
        {
            return CollectionType.construct(class1, ajavatype[0]);
        }
    }

    protected static HierarchicType _findSuperClassChain(Type type1, Class class1)
    {
        HierarchicType hierarchictype = new HierarchicType(type1);
        Class class2 = hierarchictype.getRawClass();
        if (class2 == class1)
        {
            return hierarchictype;
        }
        Type type2 = class2.getGenericSuperclass();
        if (type2 != null)
        {
            HierarchicType hierarchictype1 = _findSuperClassChain(type2, class1);
            if (hierarchictype1 != null)
            {
                hierarchictype1.setSubType(hierarchictype);
                hierarchictype.setSuperType(hierarchictype1);
                return hierarchictype;
            }
        }
        return null;
    }

    protected static HierarchicType _findSuperInterfaceChain(Type type1, Class class1)
    {
        HierarchicType hierarchictype = new HierarchicType(type1);
        Class class2 = hierarchictype.getRawClass();
        if (class2 == class1)
        {
            return hierarchictype;
        }
        Type atype[] = class2.getGenericInterfaces();
        if (atype != null)
        {
            int i = atype.length;
            for (int j = 0; j < i; j++)
            {
                HierarchicType hierarchictype2 = _findSuperInterfaceChain(atype[j], class1);
                if (hierarchictype2 != null)
                {
                    hierarchictype2.setSubType(hierarchictype);
                    hierarchictype.setSuperType(hierarchictype2);
                    return hierarchictype;
                }
            }

        }
        Type type2 = class2.getGenericSuperclass();
        if (type2 != null)
        {
            HierarchicType hierarchictype1 = _findSuperInterfaceChain(type2, class1);
            if (hierarchictype1 != null)
            {
                hierarchictype1.setSubType(hierarchictype);
                hierarchictype.setSuperType(hierarchictype1);
                return hierarchictype;
            }
        }
        return null;
    }

    protected static HierarchicType _findSuperTypeChain(Class class1, Class class2)
    {
        if (class2.isInterface())
        {
            return _findSuperInterfaceChain(class1, class2);
        } else
        {
            return _findSuperClassChain(class1, class2);
        }
    }

    private JavaType _mapType(Class class1)
    {
        JavaType ajavatype[] = findTypeParameters(class1, java/util/Map);
        if (ajavatype == null)
        {
            return MapType.construct(class1, _unknownType(), _unknownType());
        }
        if (ajavatype.length != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Strange Map type ").append(class1.getName()).append(": can not determine type parameters").toString());
        } else
        {
            return MapType.construct(class1, ajavatype[0], ajavatype[1]);
        }
    }

    public static JavaType arrayType(Class class1)
    {
        return instance.constructArrayType(instance.constructType(class1));
    }

    public static JavaType arrayType(JavaType javatype)
    {
        return instance.constructArrayType(javatype);
    }

    public static JavaType collectionType(Class class1, Class class2)
    {
        return instance.constructCollectionType(class1, instance.constructType(class2));
    }

    public static JavaType collectionType(Class class1, JavaType javatype)
    {
        return instance.constructCollectionType(class1, javatype);
    }

    public static TypeFactory defaultInstance()
    {
        return instance;
    }

    public static JavaType fastSimpleType(Class class1)
    {
        return instance.uncheckedSimpleType(class1);
    }

    public static JavaType[] findParameterTypes(Class class1, Class class2)
    {
        return instance.findTypeParameters(class1, class2);
    }

    public static JavaType[] findParameterTypes(Class class1, Class class2, TypeBindings typebindings)
    {
        return instance.findTypeParameters(class1, class2, typebindings);
    }

    public static JavaType[] findParameterTypes(JavaType javatype, Class class1)
    {
        return instance.findTypeParameters(javatype, class1);
    }

    public static JavaType fromCanonical(String s)
        throws IllegalArgumentException
    {
        return instance.constructFromCanonical(s);
    }

    public static JavaType fromClass(Class class1)
    {
        return instance._fromClass(class1, null);
    }

    public static JavaType fromType(Type type1)
    {
        return instance._constructType(type1, null);
    }

    public static JavaType fromTypeReference(TypeReference typereference)
    {
        return type(typereference.getType());
    }

    public static JavaType mapType(Class class1, Class class2, Class class3)
    {
        return instance.constructMapType(class1, type(class2), instance.constructType(class3));
    }

    public static JavaType mapType(Class class1, JavaType javatype, JavaType javatype1)
    {
        return instance.constructMapType(class1, javatype, javatype1);
    }

    public static transient JavaType parametricType(Class class1, Class aclass[])
    {
        return instance.constructParametricType(class1, aclass);
    }

    public static transient JavaType parametricType(Class class1, JavaType ajavatype[])
    {
        return instance.constructParametricType(class1, ajavatype);
    }

    public static Class rawClass(Type type1)
    {
        if (type1 instanceof Class)
        {
            return (Class)type1;
        } else
        {
            return defaultInstance().constructType(type1).getRawClass();
        }
    }

    public static JavaType specialize(JavaType javatype, Class class1)
    {
        return instance.constructSpecializedType(javatype, class1);
    }

    public static JavaType type(Type type1)
    {
        return instance._constructType(type1, null);
    }

    public static JavaType type(Type type1, Class class1)
    {
        return instance.constructType(type1, class1);
    }

    public static JavaType type(Type type1, TypeBindings typebindings)
    {
        return instance._constructType(type1, typebindings);
    }

    public static JavaType type(Type type1, JavaType javatype)
    {
        return instance.constructType(type1, javatype);
    }

    public static JavaType type(TypeReference typereference)
    {
        return instance.constructType(typereference.getType());
    }

    public static JavaType unknownType()
    {
        return defaultInstance()._unknownType();
    }

    public JavaType _constructType(Type type1, TypeBindings typebindings)
    {
        JavaType javatype;
        if (type1 instanceof Class)
        {
            Class class1 = (Class)type1;
            if (typebindings == null)
            {
                typebindings = new TypeBindings(this, class1);
            }
            javatype = _fromClass(class1, typebindings);
        } else
        if (type1 instanceof ParameterizedType)
        {
            javatype = _fromParamType((ParameterizedType)type1, typebindings);
        } else
        if (type1 instanceof GenericArrayType)
        {
            javatype = _fromArrayType((GenericArrayType)type1, typebindings);
        } else
        if (type1 instanceof TypeVariable)
        {
            javatype = _fromVariable((TypeVariable)type1, typebindings);
        } else
        if (type1 instanceof WildcardType)
        {
            javatype = _fromWildcard((WildcardType)type1, typebindings);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unrecognized Type: ").append(type1.toString()).toString());
        }
        if (_modifiers != null && !javatype.isContainerType())
        {
            TypeModifier atypemodifier[] = _modifiers;
            int i = atypemodifier.length;
            for (int j = 0; j < i; j++)
            {
                javatype = atypemodifier[j].modifyType(javatype, type1, typebindings, this);
            }

        }
        return javatype;
    }

    protected JavaType _fromArrayType(GenericArrayType genericarraytype, TypeBindings typebindings)
    {
        return ArrayType.construct(_constructType(genericarraytype.getGenericComponentType(), typebindings));
    }

    protected JavaType _fromClass(Class class1, TypeBindings typebindings)
    {
        if (class1.isArray())
        {
            return ArrayType.construct(_constructType(class1.getComponentType(), null));
        }
        if (class1.isEnum())
        {
            return new SimpleType(class1);
        }
        if (java/util/Map.isAssignableFrom(class1))
        {
            return _mapType(class1);
        }
        if (java/util/Collection.isAssignableFrom(class1))
        {
            return _collectionType(class1);
        } else
        {
            return new SimpleType(class1);
        }
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedtype, TypeBindings typebindings)
    {
        Class class1 = (Class)parameterizedtype.getRawType();
        Type atype[] = parameterizedtype.getActualTypeArguments();
        int i;
        JavaType ajavatype[];
        if (atype == null)
        {
            i = 0;
        } else
        {
            i = atype.length;
        }
        if (i == 0)
        {
            ajavatype = NO_TYPES;
        } else
        {
            ajavatype = new JavaType[i];
            int j = 0;
            while (j < i) 
            {
                ajavatype[j] = _constructType(atype[j], typebindings);
                j++;
            }
        }
        if (java/util/Map.isAssignableFrom(class1))
        {
            JavaType ajavatype2[] = findTypeParameters(constructSimpleType(class1, ajavatype), java/util/Map);
            if (ajavatype2.length != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Could not find 2 type parameters for Map class ").append(class1.getName()).append(" (found ").append(ajavatype2.length).append(")").toString());
            } else
            {
                return MapType.construct(class1, ajavatype2[0], ajavatype2[1]);
            }
        }
        if (java/util/Collection.isAssignableFrom(class1))
        {
            JavaType ajavatype1[] = findTypeParameters(constructSimpleType(class1, ajavatype), java/util/Collection);
            if (ajavatype1.length != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Could not find 1 type parameter for Collection class ").append(class1.getName()).append(" (found ").append(ajavatype1.length).append(")").toString());
            } else
            {
                return CollectionType.construct(class1, ajavatype1[0]);
            }
        }
        if (i == 0)
        {
            return new SimpleType(class1);
        } else
        {
            return constructSimpleType(class1, ajavatype);
        }
    }

    protected JavaType _fromParameterizedClass(Class class1, List list)
    {
        if (class1.isArray())
        {
            return ArrayType.construct(_constructType(class1.getComponentType(), null));
        }
        if (class1.isEnum())
        {
            return new SimpleType(class1);
        }
        if (java/util/Map.isAssignableFrom(class1))
        {
            if (list.size() > 0)
            {
                JavaType javatype = (JavaType)list.get(0);
                JavaType javatype1;
                if (list.size() >= 2)
                {
                    javatype1 = (JavaType)list.get(1);
                } else
                {
                    javatype1 = _unknownType();
                }
                return MapType.construct(class1, javatype, javatype1);
            } else
            {
                return _mapType(class1);
            }
        }
        if (java/util/Collection.isAssignableFrom(class1))
        {
            if (list.size() >= 1)
            {
                return CollectionType.construct(class1, (JavaType)list.get(0));
            } else
            {
                return _collectionType(class1);
            }
        }
        if (list.size() == 0)
        {
            return new SimpleType(class1);
        } else
        {
            return constructSimpleType(class1, (JavaType[])list.toArray(new JavaType[list.size()]));
        }
    }

    protected JavaType _fromVariable(TypeVariable typevariable, TypeBindings typebindings)
    {
        JavaType javatype;
        if (typebindings == null)
        {
            javatype = _unknownType();
        } else
        {
            String s = typevariable.getName();
            javatype = typebindings.findType(s);
            if (javatype == null)
            {
                Type atype[] = typevariable.getBounds();
                typebindings._addPlaceholder(s);
                return _constructType(atype[0], typebindings);
            }
        }
        return javatype;
    }

    protected JavaType _fromWildcard(WildcardType wildcardtype, TypeBindings typebindings)
    {
        return _constructType(wildcardtype.getUpperBounds()[0], typebindings);
    }

    protected JavaType _resolveVariableViaSubTypes(HierarchicType hierarchictype, String s, TypeBindings typebindings)
    {
        if (hierarchictype != null && hierarchictype.isGeneric())
        {
            TypeVariable atypevariable[] = hierarchictype.getRawClass().getTypeParameters();
            int i = 0;
            for (int j = atypevariable.length; i < j; i++)
            {
                if (s.equals(atypevariable[i].getName()))
                {
                    Type type1 = hierarchictype.asGeneric().getActualTypeArguments()[i];
                    if (type1 instanceof TypeVariable)
                    {
                        return _resolveVariableViaSubTypes(hierarchictype.getSubType(), ((TypeVariable)type1).getName(), typebindings);
                    } else
                    {
                        return _constructType(type1, typebindings);
                    }
                }
            }

        }
        return _unknownType();
    }

    protected JavaType _unknownType()
    {
        return new SimpleType(java/lang/Object, null, null);
    }

    protected ArrayType constructArrayType(Class class1)
    {
        return ArrayType.construct(_constructType(class1, null));
    }

    protected ArrayType constructArrayType(JavaType javatype)
    {
        return ArrayType.construct(javatype);
    }

    public CollectionLikeType constructCollectionLikeType(Class class1, Class class2)
    {
        return CollectionLikeType.construct(class1, constructType(class2));
    }

    public CollectionLikeType constructCollectionLikeType(Class class1, JavaType javatype)
    {
        return CollectionLikeType.construct(class1, javatype);
    }

    public CollectionType constructCollectionType(Class class1, Class class2)
    {
        return CollectionType.construct(class1, constructType(class2));
    }

    public CollectionType constructCollectionType(Class class1, JavaType javatype)
    {
        return CollectionType.construct(class1, javatype);
    }

    public JavaType constructFromCanonical(String s)
        throws IllegalArgumentException
    {
        return _parser.parse(s);
    }

    public MapLikeType constructMapLikeType(Class class1, Class class2, Class class3)
    {
        return MapType.construct(class1, constructType(class2), constructType(class3));
    }

    public MapLikeType constructMapLikeType(Class class1, JavaType javatype, JavaType javatype1)
    {
        return MapLikeType.construct(class1, javatype, javatype1);
    }

    public MapType constructMapType(Class class1, Class class2, Class class3)
    {
        return MapType.construct(class1, constructType(class2), constructType(class3));
    }

    public MapType constructMapType(Class class1, JavaType javatype, JavaType javatype1)
    {
        return MapType.construct(class1, javatype, javatype1);
    }

    public transient JavaType constructParametricType(Class class1, Class aclass[])
    {
        int i = aclass.length;
        JavaType ajavatype[] = new JavaType[i];
        for (int j = 0; j < i; j++)
        {
            ajavatype[j] = _fromClass(aclass[j], null);
        }

        return constructParametricType(class1, ajavatype);
    }

    public transient JavaType constructParametricType(Class class1, JavaType ajavatype[])
    {
        if (class1.isArray())
        {
            if (ajavatype.length != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 1 parameter type for arrays (").append(class1.getName()).append(")").toString());
            } else
            {
                return constructArrayType(ajavatype[0]);
            }
        }
        if (java/util/Map.isAssignableFrom(class1))
        {
            if (ajavatype.length != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 2 parameter types for Map types (").append(class1.getName()).append(")").toString());
            } else
            {
                return constructMapType(class1, ajavatype[0], ajavatype[1]);
            }
        }
        if (java/util/Collection.isAssignableFrom(class1))
        {
            if (ajavatype.length != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 1 parameter type for Collection types (").append(class1.getName()).append(")").toString());
            } else
            {
                return constructCollectionType(class1, ajavatype[0]);
            }
        } else
        {
            return constructSimpleType(class1, ajavatype);
        }
    }

    public JavaType constructSimpleType(Class class1, JavaType ajavatype[])
    {
        TypeVariable atypevariable[] = class1.getTypeParameters();
        if (atypevariable.length != ajavatype.length)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Parameter type mismatch for ").append(class1.getName()).append(": expected ").append(atypevariable.length).append(" parameters, was given ").append(ajavatype.length).toString());
        }
        String as[] = new String[atypevariable.length];
        int i = 0;
        for (int j = atypevariable.length; i < j; i++)
        {
            as[i] = atypevariable[i].getName();
        }

        return new SimpleType(class1, as, ajavatype);
    }

    public JavaType constructSpecializedType(JavaType javatype, Class class1)
    {
        if ((javatype instanceof SimpleType) && (class1.isArray() || java/util/Map.isAssignableFrom(class1) || java/util/Collection.isAssignableFrom(class1)))
        {
            if (!javatype.getRawClass().isAssignableFrom(class1))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getClass().getName()).append(" not subtype of ").append(javatype).toString());
            }
            JavaType javatype1 = instance._fromClass(class1, new TypeBindings(this, javatype.getRawClass()));
            Object obj = javatype.getValueHandler();
            if (obj != null)
            {
                javatype1.setValueHandler(obj);
            }
            Object obj1 = javatype.getTypeHandler();
            if (obj1 != null)
            {
                javatype1 = javatype1.withTypeHandler(obj1);
            }
            return javatype1;
        } else
        {
            return javatype.narrowBy(class1);
        }
    }

    public JavaType constructType(Type type1)
    {
        return _constructType(type1, null);
    }

    public JavaType constructType(Type type1, Class class1)
    {
        return _constructType(type1, new TypeBindings(this, class1));
    }

    public JavaType constructType(Type type1, TypeBindings typebindings)
    {
        return _constructType(type1, typebindings);
    }

    public JavaType constructType(Type type1, JavaType javatype)
    {
        return _constructType(type1, new TypeBindings(this, javatype));
    }

    public JavaType constructType(TypeReference typereference)
    {
        return _constructType(typereference.getType(), null);
    }

    public JavaType[] findTypeParameters(Class class1, Class class2)
    {
        return findTypeParameters(class1, class2, new TypeBindings(this, class1));
    }

    public JavaType[] findTypeParameters(Class class1, Class class2, TypeBindings typebindings)
    {
        HierarchicType hierarchictype = _findSuperTypeChain(class1, class2);
        if (hierarchictype == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" is not a subtype of ").append(class2.getName()).toString());
        }
        HierarchicType hierarchictype1;
        TypeBindings typebindings1;
        for (hierarchictype1 = hierarchictype; hierarchictype1.getSuperType() != null; typebindings = typebindings1)
        {
            hierarchictype1 = hierarchictype1.getSuperType();
            Class class3 = hierarchictype1.getRawClass();
            typebindings1 = new TypeBindings(this, class3);
            if (!hierarchictype1.isGeneric())
            {
                continue;
            }
            Type atype[] = hierarchictype1.asGeneric().getActualTypeArguments();
            TypeVariable atypevariable[] = class3.getTypeParameters();
            int i = atype.length;
            for (int j = 0; j < i; j++)
            {
                typebindings1.addBinding(atypevariable[j].getName(), instance._constructType(atype[j], typebindings));
            }

        }

        if (!hierarchictype1.isGeneric())
        {
            return null;
        } else
        {
            return typebindings.typesAsArray();
        }
    }

    public JavaType[] findTypeParameters(JavaType javatype, Class class1)
    {
        Class class2 = javatype.getRawClass();
        if (class2 == class1)
        {
            int i = javatype.containedTypeCount();
            JavaType ajavatype[];
            if (i == 0)
            {
                ajavatype = null;
            } else
            {
                ajavatype = new JavaType[i];
                int j = 0;
                while (j < i) 
                {
                    ajavatype[j] = javatype.containedType(j);
                    j++;
                }
            }
            return ajavatype;
        } else
        {
            return findTypeParameters(class2, class1, new TypeBindings(this, javatype));
        }
    }

    public JavaType uncheckedSimpleType(Class class1)
    {
        return new SimpleType(class1, null, null);
    }

    public TypeFactory withModifier(TypeModifier typemodifier)
    {
        if (_modifiers == null)
        {
            return new TypeFactory(_parser, new TypeModifier[] {
                typemodifier
            });
        } else
        {
            return new TypeFactory(_parser, (TypeModifier[])ArrayBuilders.insertInListNoDup(_modifiers, typemodifier));
        }
    }

}
