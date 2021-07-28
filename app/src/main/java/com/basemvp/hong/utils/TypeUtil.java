/*
 *
 * Copyright (C) 2018 Hong(tangweihong1069@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  　　　　http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.basemvp.hong.utils;


import com.google.gson.internal.$Gson$Types;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

import static com.google.gson.internal.$Gson$Preconditions.checkArgument;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * TypeUtil
 * Created by Luki on 2016/8/10.
 * Version:1
 * <p>
 * modify from {@link $Gson$Types}
 */
public class TypeUtil {


    static final Type[] EMPTY_TYPE_ARRAY = new Type[]{};

    @NonNull
    public static ParameterizedTypeImpl getParameterizedType(Type rawType, Type type) {
        return new ParameterizedTypeImpl(null, rawType, type);
    }

    /**
     * Returns the type from super class's type parameter in {@link $Gson$Types#canonicalize
     * canonical form}.
     */
    public static Type getSuperclassTypeParameter(Class<?> subclass) {
        return getSuperclassTypeParameter(subclass, 0);
    }

    /**
     * Returns the type from super class's type parameter in {@link $Gson$Types#canonicalize
     * canonical form}.
     */
    public static Type getSuperclassTypeParameter(Class<?> subclass, int index) {
        //getGenericSuperclass()获得带有类型的父类  即Person<Integer,Student>
        //Type是Java编程语言中所有类型的公共高级接口，它们包括原始类型，参数化类型，数组类型，类型变量和基本类型
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        //ParameterizedType参数化类型，即泛型
        //比如Person<java.lang.Integer, Student>
        ParameterizedType parameterized = (ParameterizedType) superclass;
        //getActualTypeArguments获取参数化类型的数组，泛型有两个，获取第二位
        return canonicalize(parameterized.getActualTypeArguments()[index]);
    }

    /**
     * Returns a type that is functionally equal but not necessarily equal
     * according to {@link Object#equals(Object) Object.equals()}. The returned
     * type is {@link Serializable}.
     */
    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class<?> c = (Class<?>) type;
            return c.isArray() ? new GenericArrayTypeImpl(canonicalize(c.getComponentType())) : c;

        } else if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) type;
            Type rawType = p.getRawType();
            //XXX
            rawType = rawType == List.class ? ArrayList.class : rawType;
            return new ParameterizedTypeImpl(p.getOwnerType(), rawType, p.getActualTypeArguments());

        } else if (type instanceof GenericArrayType) {
            GenericArrayType g = (GenericArrayType) type;
            return new GenericArrayTypeImpl(g.getGenericComponentType());

        } else if (type instanceof WildcardType) {
            WildcardType w = (WildcardType) type;
            return new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());

        } else {
            // type is either serializable as-is or unsupported
            return type;
        }
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class<?>) type).getName() : type.toString();
    }

    private static void checkNotPrimitive(Type type) {
        checkArgument(!(type instanceof Class<?>) || !((Class<?>) type).isPrimitive());
    }

    private static int hashCodeOrZero(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type ownerType, Type rawType, Type... typeArguments) {
            // require an owner type if the raw type needs it
            if (rawType instanceof Class<?>) {
                Class<?> rawTypeAsClass = (Class<?>) rawType;
                boolean isStaticOrTopLevelClass = Modifier.isStatic(rawTypeAsClass.getModifiers()) || rawTypeAsClass.getEnclosingClass() == null;
                checkArgument(ownerType != null || isStaticOrTopLevelClass);
            }

            this.ownerType = ownerType == null ? null : canonicalize(ownerType);
            this.rawType = canonicalize(rawType);
            this.typeArguments = typeArguments.clone();
            for (int t = 0; t < this.typeArguments.length; t++) {
                checkNotNull(this.typeArguments[t]);
                checkNotPrimitive(this.typeArguments[t]);
                this.typeArguments[t] = canonicalize(this.typeArguments[t]);
            }
        }

        public Type[] getActualTypeArguments() {
            return typeArguments.clone();
        }

        public Type getOwnerType() {
            return ownerType;
        }

        public Type getRawType() {
            return rawType;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof ParameterizedType && $Gson$Types.equals(this, (ParameterizedType) other);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(typeArguments) ^ rawType.hashCode() ^ hashCodeOrZero(ownerType);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(30 * (typeArguments.length + 1));
            stringBuilder.append(typeToString(rawType));

            if (typeArguments.length == 0) {
                return stringBuilder.toString();
            }

            stringBuilder.append("<").append(typeToString(typeArguments[0]));
            for (int i = 1; i < typeArguments.length; i++) {
                stringBuilder.append(", ").append(typeToString(typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }


    }

    private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type componentType) {
            this.componentType = canonicalize(componentType);
        }

        public Type getGenericComponentType() {
            return componentType;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof GenericArrayType && $Gson$Types.equals(this, (GenericArrayType) o);
        }

        @Override
        public int hashCode() {
            return componentType.hashCode();
        }

        @Override
        public String toString() {
            return typeToString(componentType) + "[]";
        }


    }

    /**
     * The WildcardType interface supports multiple upper bounds and multiple
     * lower bounds. We only support what the Java 6 language needs - at most one
     * bound. If a lower bound is set, the upper bound must be Object.class.
     */
    private static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type upperBound;
        private final Type lowerBound;

        public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            checkArgument(lowerBounds.length <= 1);
            checkArgument(upperBounds.length == 1);

            if (lowerBounds.length == 1) {
                checkNotNull(lowerBounds[0]);
                checkNotPrimitive(lowerBounds[0]);
                checkArgument(upperBounds[0] == Object.class);
                this.lowerBound = canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;

            } else {
                checkNotNull(upperBounds[0]);
                checkNotPrimitive(upperBounds[0]);
                this.lowerBound = null;
                this.upperBound = canonicalize(upperBounds[0]);
            }
        }

        public Type[] getUpperBounds() {
            return new Type[]{upperBound};
        }

        public Type[] getLowerBounds() {
            return lowerBound != null ? new Type[]{lowerBound} : EMPTY_TYPE_ARRAY;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof WildcardType && $Gson$Types.equals(this, (WildcardType) other);
        }

        @Override
        public int hashCode() {
            // this equals Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
            return (lowerBound != null ? 31 + lowerBound.hashCode() : 1) ^ (31 + upperBound.hashCode());
        }

        @Override
        public String toString() {
            if (lowerBound != null) {
                return "? super " + typeToString(lowerBound);
            } else if (upperBound == Object.class) {
                return "?";
            } else {
                return "? extends " + typeToString(upperBound);
            }
        }


    }
}
