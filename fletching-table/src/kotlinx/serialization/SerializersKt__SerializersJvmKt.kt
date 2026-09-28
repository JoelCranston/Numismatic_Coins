package kotlinx.serialization

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import java.util.ArrayList
import java.util.Arrays
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.internal.PlatformKt
import kotlinx.serialization.internal.PrimitivesKt
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

@SourceDebugExtension(["SMAP\nSerializersJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersJvm.kt\nkotlinx/serialization/SerializersKt__SerializersJvmKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,201:1\n11228#2:202\n11563#2,3:203\n1563#3:206\n1634#3,3:207\n37#4:210\n36#4,3:211\n1#5:214\n*S KotlinDebug\n*F\n+ 1 SerializersJvm.kt\nkotlinx/serialization/SerializersKt__SerializersJvmKt\n*L\n113#1:202\n113#1:203,3\n140#1:206\n140#1:207,3\n169#1:210\n169#1:211,3\n*E\n"])
@JvmSynthetic
internal class SerializersKt__SerializersJvmKt {
   @JvmStatic
   public fun serializer(type: Type): KSerializer<Any> {
      return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), type);
   }

   @JvmStatic
   public fun serializerOrNull(type: Type): KSerializer<Any>? {
      return SerializersKt.serializerOrNull(SerializersModuleBuildersKt.EmptySerializersModule(), type);
   }

   @JvmStatic
   public fun SerializersModule.serializer(type: Type): KSerializer<Any> {
      val var10000: KSerializer = serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(`$this$serializer`, type, true);
      if (var10000 == null) {
         PlatformKt.serializerNotRegistered(prettyClass$SerializersKt__SerializersJvmKt(type));
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @JvmStatic
   public fun SerializersModule.serializerOrNull(type: Type): KSerializer<Any>? {
      return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(`$this$serializerOrNull`, type, false);
   }

   @JvmStatic
   private fun SerializersModule.serializerByJavaTypeImpl(type: Type, failOnMissingTypeArgSerializer: Boolean = ...): KSerializer<Any>? {
      var var10000: KSerializer;
      if (type is GenericArrayType) {
         var10000 = genericArraySerializer$SerializersKt__SerializersJvmKt(
            `$this$serializerByJavaTypeImpl`, type as GenericArrayType, failOnMissingTypeArgSerializer
         );
      } else if (type is Class) {
         var10000 = typeSerializer$SerializersKt__SerializersJvmKt(`$this$serializerByJavaTypeImpl`, type as Class<?>, failOnMissingTypeArgSerializer);
      } else if (type is ParameterizedType) {
         val var33: Type = (type as ParameterizedType).getRawType();
         val rootClass: Class = var33 as Class;
         val args: Array<Type> = (type as ParameterizedType).getActualTypeArguments();
         val var35: java.util.List;
         if (failOnMissingTypeArgSerializer) {
            val var22: java.util.Collection = new ArrayList(args.length);

            for (Object item$iv$iv : args) {
               var22.add(SerializersKt.serializer(`$this$serializerByJavaTypeImpl`, (Type)var29));
            }

            var35 = var22 as java.util.List;
         } else {
            val `$this$mapTo$iv$iv`: java.util.Collection = new ArrayList(args.length);

            for (Object item$iv$iv : args) {
               var10000 = SerializersKt.serializerOrNull(`$this$serializerByJavaTypeImpl`, (Type)`item$iv$iv`);
               if (var10000 == null) {
                  return null;
               }

               `$this$mapTo$iv$iv`.add(var10000);
            }

            var35 = `$this$mapTo$iv$iv` as java.util.List;
         }

         if (java.util.Set.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.SetSerializer(var35.get(0) as KSerializer);
         } else if (java.util.List.class.isAssignableFrom(rootClass) || java.util.Collection.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.ListSerializer(var35.get(0) as KSerializer);
         } else if (java.util.Map.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.MapSerializer(var35.get(0) as KSerializer, var35.get(1) as KSerializer);
         } else if (Entry.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.MapEntrySerializer(var35.get(0) as KSerializer, var35.get(1) as KSerializer);
         } else if (Pair.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.PairSerializer(var35.get(0) as KSerializer, var35.get(1) as KSerializer);
         } else if (Triple.class.isAssignableFrom(rootClass)) {
            var10000 = BuiltinSerializersKt.TripleSerializer(var35.get(0) as KSerializer, var35.get(1) as KSerializer, var35.get(2) as KSerializer);
         } else {
            val var19: java.lang.Iterable = var35;
            val var24: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var35, 10));

            for (Object item$iv$iv : $this$map$iv) {
               val it: KSerializer = var30 as KSerializer;
               var24.add(it);
            }

            var10000 = reflectiveOrContextual$SerializersKt__SerializersJvmKt(
               `$this$serializerByJavaTypeImpl`, rootClass, var24 as MutableList<KSerializer<Object>>
            );
         }
      } else {
         if (type !is WildcardType) {
            throw new IllegalArgumentException(
               "type should be an instance of Class<?>, GenericArrayType, ParametrizedType or WildcardType, but actual argument $type has type ${type.getClass()::class}"
            );
         }

         var var10001: Array<Type> = (type as WildcardType).getUpperBounds();
         var10001 = ArraysKt.first(var10001);
         var10000 = serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt$default(`$this$serializerByJavaTypeImpl`, var10001 as Type, false, 2, null);
      }

      return var10000;
   }

   @JvmStatic
   private fun SerializersModule.typeSerializer(type: Class<*>, failOnMissingTypeArgSerializer: Boolean): KSerializer<Any>? {
      var var10000: KSerializer;
      if (type.isArray() && !type.getComponentType().isPrimitive()) {
         val var6: Class = type.getComponentType();
         if (failOnMissingTypeArgSerializer) {
            var10000 = SerializersKt.serializer(`$this$typeSerializer`, var6);
         } else {
            var10000 = SerializersKt.serializerOrNull(`$this$typeSerializer`, var6);
            if (var10000 == null) {
               return null;
            }
         }

         val var8: KClass = JvmClassMappingKt.getKotlinClass(var6);
         val arraySerializer: KSerializer = BuiltinSerializersKt.ArraySerializer(var8, var10000);
         var10000 = arraySerializer;
      } else {
         var10000 = reflectiveOrContextual$SerializersKt__SerializersJvmKt(`$this$typeSerializer`, type, CollectionsKt.emptyList());
      }

      return var10000;
   }

   @JvmStatic
   private fun <T : Any> SerializersModule.reflectiveOrContextual(jClass: Class<T>, typeArgumentsSerializers: List<KSerializer<Any?>>): KSerializer<T>? {
      val var4: Array<KSerializer> = typeArgumentsSerializers.toArray(new KSerializer[0]);
      val kClass: KSerializer = PlatformKt.constructSerializerForGivenTypeArgs(jClass, Arrays.copyOf(var4, var4.length));
      if (kClass != null) {
         return kClass;
      } else {
         val var8: KClass = JvmClassMappingKt.getKotlinClass(jClass);
         var var10000: KSerializer = PrimitivesKt.builtinSerializerOrNull(var8);
         if (var10000 == null) {
            var10000 = `$this$reflectiveOrContextual`.getContextual(var8, typeArgumentsSerializers);
            if (var10000 == null) {
               var10000 = if (jClass.isInterface()) new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(jClass)) else null;
            }
         }

         return var10000;
      }
   }

   @JvmStatic
   private fun SerializersModule.genericArraySerializer(type: GenericArrayType, failOnMissingTypeArgSerializer: Boolean): KSerializer<Any>? {
      val kclass: Type = type.getGenericComponentType();
      var var9: Type;
      if (kclass is WildcardType) {
         val var10000: Array<Type> = (kclass as WildcardType).getUpperBounds();
         var9 = ArraysKt.first(var10000);
      } else {
         var9 = kclass;
      }

      val var10: KSerializer;
      if (failOnMissingTypeArgSerializer) {
         var10 = SerializersKt.serializer(`$this$genericArraySerializer`, var9);
      } else {
         var10 = SerializersKt.serializerOrNull(`$this$genericArraySerializer`, var9);
         if (var10 == null) {
            return null;
         }
      }

      val var12: KClass;
      if (var9 is ParameterizedType) {
         var9 = (var9 as ParameterizedType).getRawType();
         var12 = JvmClassMappingKt.getKotlinClass(var9 as Class);
      } else {
         if (var9 !is KClass) {
            throw new IllegalStateException("unsupported type in GenericArray: ${var9.getClass()::class}");
         }

         var12 = var9 as KClass;
      }

      val var13: KSerializer = BuiltinSerializersKt.ArraySerializer(var12, var10);
      return var13;
   }

   @JvmStatic
   private fun Type.prettyClass(): Class<*> {
      val var10000: Class;
      if (`$this$prettyClass` is Class) {
         var10000 = `$this$prettyClass` as Class;
      } else if (`$this$prettyClass` is ParameterizedType) {
         val var2: Type = (`$this$prettyClass` as ParameterizedType).getRawType();
         var10000 = prettyClass$SerializersKt__SerializersJvmKt(var2);
      } else if (`$this$prettyClass` is WildcardType) {
         val var3: Array<Type> = (`$this$prettyClass` as WildcardType).getUpperBounds();
         val var4: Any = ArraysKt.first(var3);
         var10000 = prettyClass$SerializersKt__SerializersJvmKt(var4 as Type);
      } else {
         if (`$this$prettyClass` !is GenericArrayType) {
            throw new IllegalArgumentException(
               "type should be an instance of Class<?>, GenericArrayType, ParametrizedType or WildcardType, but actual argument $`$this$prettyClass` has type ${`$this$prettyClass`.getClass()::class}"
            );
         }

         val var5: Type = (`$this$prettyClass` as GenericArrayType).getGenericComponentType();
         var10000 = prettyClass$SerializersKt__SerializersJvmKt(var5);
      }

      return var10000;
   }
}
