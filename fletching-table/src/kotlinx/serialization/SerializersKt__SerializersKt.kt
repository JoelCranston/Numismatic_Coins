package kotlinx.serialization

import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.internal.HashSetSerializer
import kotlinx.serialization.internal.LinkedHashMapSerializer
import kotlinx.serialization.internal.LinkedHashSetSerializer
import kotlinx.serialization.internal.PlatformKt
import kotlinx.serialization.internal.Platform_commonKt
import kotlinx.serialization.internal.PrimitivesKt
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt

@SourceDebugExtension(["SMAP\nSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Serializers.kt\nkotlinx/serialization/SerializersKt__SerializersKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,449:1\n78#2:450\n78#2:451\n78#2:458\n78#2:459\n1563#3:452\n1634#3,3:453\n1563#3:460\n1634#3,3:461\n1563#3:464\n1634#3,3:465\n78#4:456\n78#4:457\n37#5:468\n36#5,3:469\n*S KotlinDebug\n*F\n+ 1 Serializers.kt\nkotlinx/serialization/SerializersKt__SerializersKt\n*L\n35#1:450\n54#1:451\n260#1:458\n284#1:459\n218#1:452\n218#1:453,3\n295#1:460\n295#1:461,3\n297#1:464\n297#1:465,3\n251#1:456\n258#1:457\n362#1:468\n362#1:469,3\n*E\n"])
@JvmSynthetic
internal class SerializersKt__SerializersKt {
   @JvmStatic
   public fun serializer(type: KType): KSerializer<Any?> {
      return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), type);
   }

   @ExperimentalSerializationApi
   @JvmStatic
   public fun serializer(kClass: KClass<*>, typeArgumentsSerializers: List<KSerializer<*>>, isNullable: Boolean): KSerializer<Any?> {
      return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), kClass, typeArgumentsSerializers, isNullable);
   }

   @JvmStatic
   public fun serializerOrNull(type: KType): KSerializer<Any?>? {
      return SerializersKt.serializerOrNull(SerializersModuleBuildersKt.EmptySerializersModule(), type);
   }

   @JvmStatic
   public fun SerializersModule.serializer(type: KType): KSerializer<Any?> {
      val var10000: KSerializer = serializerByKTypeImpl$SerializersKt__SerializersKt(`$this$serializer`, type, true);
      if (var10000 == null) {
         PlatformKt.platformSpecificSerializerNotRegistered(Platform_commonKt.kclass(type));
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @ExperimentalSerializationApi
   @JvmStatic
   public fun SerializersModule.serializer(kClass: KClass<*>, typeArgumentsSerializers: List<KSerializer<*>>, isNullable: Boolean): KSerializer<Any?> {
      val var10000: KSerializer = serializerByKClassImpl$SerializersKt__SerializersKt(`$this$serializer`, kClass, typeArgumentsSerializers, isNullable);
      if (var10000 == null) {
         PlatformKt.platformSpecificSerializerNotRegistered(kClass);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @JvmStatic
   public fun SerializersModule.serializerOrNull(type: KType): KSerializer<Any?>? {
      return serializerByKTypeImpl$SerializersKt__SerializersKt(`$this$serializerOrNull`, type, false);
   }

   @JvmStatic
   private fun SerializersModule.serializerByKTypeImpl(type: KType, failOnMissingTypeArgSerializer: Boolean): KSerializer<Any?>? {
      val rootClass: KClass = Platform_commonKt.kclass(type);
      val isNullable: Boolean = type.isMarkedNullable();
      val cachedSerializer: java.lang.Iterable = type.getArguments();
      val `$this$cast$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(cachedSerializer, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `$this$cast$iv`.add(Platform_commonKt.typeOrThrow(`$i$f$polymorphicIfInterface` as KTypeProjection));
      }

      val typeArguments: java.util.List = `$this$cast$iv` as java.util.List;
      var var10000: KSerializer;
      if ((`$this$cast$iv` as java.util.List).isEmpty()) {
         var10000 = if (PlatformKt.isInterface(rootClass)
               && SerializersModule.getContextual$default(`$this$serializerByKTypeImpl`, rootClass, null, 2, null) != null)
            null
            else
            SerializersCacheKt.findCachedSerializer(rootClass, isNullable);
      } else if (`$this$serializerByKTypeImpl`.getHasInterfaceContextualSerializers$kotlinx_serialization_core()) {
         var10000 = null;
      } else {
         val var17: Any = SerializersCacheKt.findParametrizedCachedSerializer(rootClass, typeArguments, isNullable);
         var10000 = (if (Result.isFailure-impl(var17)) null else var17) as KSerializer;
      }

      if (var10000 != null) {
         return var10000;
      } else {
         if (typeArguments.isEmpty()) {
            var10000 = SerializersKt.serializerOrNull(rootClass);
            if (var10000 == null) {
               var10000 = SerializersModule.getContextual$default(`$this$serializerByKTypeImpl`, rootClass, null, 2, null);
               if (var10000 == null) {
                  var10000 = if (PlatformKt.isInterface(rootClass)) new PolymorphicSerializer(rootClass) else null;
               }
            }
         } else {
            val var23: java.util.List = SerializersKt.serializersForParameters(`$this$serializerByKTypeImpl`, typeArguments, failOnMissingTypeArgSerializer);
            if (var23 == null) {
               return null;
            }

            var10000 = SerializersKt.parametrizedSerializerOrNull(
               rootClass, var23, SerializersKt__SerializersKt::serializerByKTypeImpl$lambda$0$SerializersKt__SerializersKt
            );
            if (var10000 == null) {
               var10000 = `$this$serializerByKTypeImpl`.getContextual(rootClass, var23);
               if (var10000 == null) {
                  var10000 = if (PlatformKt.isInterface(rootClass)) new PolymorphicSerializer(rootClass) else null;
               }
            }
         }

         return if (var10000 != null) nullable$SerializersKt__SerializersKt(var10000, isNullable) else null;
      }
   }

   @JvmStatic
   private fun SerializersModule.serializerByKClassImpl(rootClass: KClass<Any>, typeArgumentsSerializers: List<KSerializer<Any?>>, isNullable: Boolean): KSerializer<
         Any?
      >? {
      var var10000: KSerializer;
      if (typeArgumentsSerializers.isEmpty()) {
         var10000 = SerializersKt.serializerOrNull(rootClass);
         if (var10000 == null) {
            var10000 = SerializersModule.getContextual$default(`$this$serializerByKClassImpl`, rootClass, null, 2, null);
         }
      } else {
         var var5: KSerializer;
         try {
            var10000 = SerializersKt.parametrizedSerializerOrNull(
               rootClass, typeArgumentsSerializers, SerializersKt__SerializersKt::serializerByKClassImpl$lambda$1$SerializersKt__SerializersKt
            );
            if (var10000 == null) {
               var10000 = `$this$serializerByKClassImpl`.getContextual(rootClass, typeArgumentsSerializers);
            }

            var5 = var10000;
         } catch (var8: IndexOutOfBoundsException) {
            throw new SerializationException(
               "Unable to retrieve a serializer, the number of passed type serializers differs from the actual number of generic parameters", var8
            );
         }

         var10000 = var5;
      }

      return if (var10000 != null) nullable$SerializersKt__SerializersKt(var10000, isNullable) else null;
   }

   @JvmStatic
   internal fun SerializersModule.serializersForParameters(typeArguments: List<KType>, failOnMissingTypeArgSerializer: Boolean): List<KSerializer<Any?>>? {
      val var10000: java.util.List;
      if (failOnMissingTypeArgSerializer) {
         val `$this$map$iv`: java.lang.Iterable = typeArguments;
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));

         for (Object item$iv$iv : $this$map$iv) {
            `destination$iv$iv`.add(SerializersKt.serializer(`$this$serializersForParameters`, `item$iv$iv` as KType));
         }

         var10000 = `destination$iv$iv` as java.util.List;
      } else {
         val var14: java.lang.Iterable = typeArguments;
         val var16: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));

         for (Object item$iv$iv : $this$map$iv) {
            val var22: KSerializer = SerializersKt.serializerOrNull(`$this$serializersForParameters`, var19 as KType);
            if (var22 == null) {
               return null;
            }

            var16.add(var22);
         }

         var10000 = var16 as java.util.List;
      }

      return var10000;
   }

   @InternalSerializationApi
   @JvmStatic
   public fun <T : Any> KClass<T>.serializer(): KSerializer<T> {
      val var10000: KSerializer = SerializersKt.serializerOrNull(`$this$serializer`);
      if (var10000 == null) {
         Platform_commonKt.serializerNotRegistered(`$this$serializer`);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @InternalSerializationApi
   @JvmStatic
   public fun <T : Any> KClass<T>.serializerOrNull(): KSerializer<T>? {
      var var10000: KSerializer = PlatformKt.compiledSerializerImpl(`$this$serializerOrNull`);
      if (var10000 == null) {
         var10000 = PrimitivesKt.builtinSerializerOrNull(`$this$serializerOrNull`);
      }

      return var10000;
   }

   @JvmStatic
   internal fun KClass<Any>.parametrizedSerializerOrNull(serializers: List<KSerializer<Any?>>, elementClassifierIfArray: () -> KClassifier?): KSerializer<
         out Any
      >? {
      var var10000: KSerializer = builtinParametrizedSerializer$SerializersKt__SerializersKt(
         `$this$parametrizedSerializerOrNull`, serializers, elementClassifierIfArray
      );
      if (var10000 == null) {
         var10000 = compiledParametrizedSerializer$SerializersKt__SerializersKt(`$this$parametrizedSerializerOrNull`, serializers);
      }

      return var10000;
   }

   @JvmStatic
   private fun KClass<Any>.compiledParametrizedSerializer(serializers: List<KSerializer<Any?>>): KSerializer<out Any>? {
      val var2: Array<KSerializer> = serializers.toArray(new KSerializer[0]);
      return PlatformKt.constructSerializerForGivenTypeArgs(`$this$compiledParametrizedSerializer`, Arrays.copyOf(var2, var2.length));
   }

   @JvmStatic
   private fun KClass<Any>.builtinParametrizedSerializer(serializers: List<KSerializer<Any?>>, elementClassifierIfArray: () -> KClassifier?): KSerializer<
         out Any
      >? {
      val var10000: KSerializer;
      if (`$this$builtinParametrizedSerializer` == java.util.Collection::class
         || `$this$builtinParametrizedSerializer` == java.util.List::class
         || `$this$builtinParametrizedSerializer` == java.util.List::class
         || `$this$builtinParametrizedSerializer` == ArrayList::class) {
         var10000 = new ArrayListSerializer(serializers.get(0) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == HashSet::class) {
         var10000 = new HashSetSerializer(serializers.get(0) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == java.util.Set::class
         || `$this$builtinParametrizedSerializer` == java.util.Set::class
         || `$this$builtinParametrizedSerializer` == LinkedHashSet::class) {
         var10000 = new LinkedHashSetSerializer(serializers.get(0) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == HashMap::class) {
         var10000 = new HashMapSerializer(serializers.get(0) as KSerializer, serializers.get(1) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == java.util.Map::class
         || `$this$builtinParametrizedSerializer` == java.util.Map::class
         || `$this$builtinParametrizedSerializer` == LinkedHashMap::class) {
         var10000 = new LinkedHashMapSerializer(serializers.get(0) as KSerializer, serializers.get(1) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == Entry::class) {
         var10000 = BuiltinSerializersKt.MapEntrySerializer(serializers.get(0) as KSerializer, serializers.get(1) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == Pair::class) {
         var10000 = BuiltinSerializersKt.PairSerializer(serializers.get(0) as KSerializer, serializers.get(1) as KSerializer);
      } else if (`$this$builtinParametrizedSerializer` == Triple::class) {
         var10000 = BuiltinSerializersKt.TripleSerializer(
            serializers.get(0) as KSerializer, serializers.get(1) as KSerializer, serializers.get(2) as KSerializer
         );
      } else if (PlatformKt.isReferenceArray(`$this$builtinParametrizedSerializer`)) {
         val var4: Any = elementClassifierIfArray.invoke();
         var10000 = BuiltinSerializersKt.ArraySerializer(var4 as KClass, serializers.get(0) as KSerializer);
      } else {
         var10000 = null;
      }

      return var10000;
   }

   @JvmStatic
   private fun <T : Any> KSerializer<T>.nullable(shouldBeNullable: Boolean): KSerializer<T?> {
      if (shouldBeNullable) {
         return BuiltinSerializersKt.getNullable(`$this$nullable`);
      } else {
         return `$this$nullable`;
      }
   }

   @PublishedApi
   @JvmStatic
   internal fun noCompiledSerializer(forClass: String): KSerializer<*> {
      throw new SerializationException(Platform_commonKt.notRegisteredMessage(forClass));
   }

   @PublishedApi
   @JvmStatic
   internal fun noCompiledSerializer(module: SerializersModule, kClass: KClass<*>): KSerializer<*> {
      val var10000: KSerializer = SerializersModule.getContextual$default(module, kClass, null, 2, null);
      if (var10000 == null) {
         Platform_commonKt.serializerNotRegistered(kClass);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @PublishedApi
   @JvmStatic
   internal fun noCompiledSerializer(module: SerializersModule, kClass: KClass<*>, argSerializers: Array<KSerializer<*>>): KSerializer<*> {
      val var10000: KSerializer = module.getContextual(kClass, ArraysKt.asList(argSerializers));
      if (var10000 == null) {
         Platform_commonKt.serializerNotRegistered(kClass);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @PublishedApi
   @JvmStatic
   internal fun moduleThenPolymorphic(module: SerializersModule, kClass: KClass<*>): KSerializer<*> {
      var var10000: KSerializer = SerializersModule.getContextual$default(module, kClass, null, 2, null);
      if (var10000 == null) {
         var10000 = new PolymorphicSerializer(kClass);
      }

      return var10000;
   }

   @PublishedApi
   @JvmStatic
   internal fun moduleThenPolymorphic(module: SerializersModule, kClass: KClass<*>, argSerializers: Array<KSerializer<*>>): KSerializer<*> {
      var var10000: KSerializer = module.getContextual(kClass, ArraysKt.asList(argSerializers));
      if (var10000 == null) {
         var10000 = new PolymorphicSerializer(kClass);
      }

      return var10000;
   }

   @JvmStatic
   fun `serializerByKTypeImpl$lambda$0$SerializersKt__SerializersKt`(`$typeArguments`: java.util.List): KClassifier {
      return (`$typeArguments`.get(0) as KType).getClassifier();
   }

   @JvmStatic
   fun `serializerByKClassImpl$lambda$1$SerializersKt__SerializersKt`(): KClassifier {
      throw new SerializationException("It is not possible to retrieve an array serializer using KClass alone, use KType instead or ArraySerializer factory");
   }
}
